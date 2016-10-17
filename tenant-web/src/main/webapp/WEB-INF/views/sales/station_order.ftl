<#include "../web_common/_layout.ftl" />

<@layoutHead>

    <#assign station="active" />
</@layoutHead>
<@layoutBody classBody="wrapkit-sidebar-left wrapkit-sidebar-lg bg-grd-dark wrapkit-sidebar-horizontal" >

</@layoutBody>


<main class="wrapkit-wrapper" id="wrapper" data-init-layout="true">

    <!-- ============================================
    MAIN CONTENT SECTION
    =============================================== -->
    <section class="content-wrapper" role="main" data-init-content="true">
        <div class="content">

            <div class="content-body">
                <div class="panel fade in panel-default panel-fill" data-fill-color="true" data-init-panel="true">
                    <div class="panel-body">
                        <form class="form-inline">
                            选择项目：
                            <div class="form-group">
                                <label class="select">
                                    <select class="form-control" style="width: 120px" id="projectId" onchange="reacquireRoom();">
                                    <#list project as p>
                                        <option value="${p.projectId!''}" >${p.projectName!''}</option>
                                    </#list>
                                    </select>
                                </label>
                            </div><!-- /form-group -->
                            选择入驻时间：
                            <div class="form-group" >
                                <label class="select">
                                    <input id="startTime" onclick="validatepicker(this)" onchange="reacquireRoom()" data-date-format="yyyy-MM-dd" class="form-control date form_date validate[required]" name="startTime" placeholder="开始时间" value="${startTime!''}"/>
                                </label>
                            </div><!-- /form-group -->
                            入驻时长：
                            <div class="form-group" style="width: 100px">
                                <label class="select">
                                    <select id="month" onchange="reacquireRoom()">
                                        <option value="0">0</option>
                                        <option value="1" selected="selected">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                        <option value="5">5</option>
                                        <option value="6">6</option>
                                        <option value="7">7</option>
                                        <option value="8">8</option>
                                        <option value="9">9</option>
                                        <option value="10">10</option>
                                        <option value="11">11</option>
                                        <option value="12">12</option>
                                    </select>
                                </label>
                            </div><!-- /form-group -->
                            （月）
                            <div class="form-group" style="width: 100px">
                                <label class="select">
                                    <select id="week" onchange="reacquireRoom()">
                                        <option value="0" selected="selected">0</option>
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                    </select>
                                </label>
                            </div><!-- /form-group -->
                            （周）
                        </form>
                    </div><!-- /.panel-body -->
                </div><!-- /.panel -->

                <div class = "row">
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <div class="table-responsive">

                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th>选择</th>
                                    <th>产品名称</th>
                                    <th>产品剩余数量</th>
                                    <th>选择数量</th>
                                    <th>价格</th>
                                </tr>
                                </thead>
                                <tbody id ="productList">
                                <#if productList??>
                                    <#list productList as product>
                                    <tr>
                                        <td name='roomId'>
                                            <#if product.remainedNum  gt 0 >
                                                <input type="checkbox" name="selectRoom" value="${product.price!""}-${product.finalPrice!""}-${product.deposit!""}-${product.productType!""}-${product.productSubtype!""}-${product.remainedNum!""}"/>
                                            <#else >
                                                <input type="checkbox" name="selectRoom" value="${product.price!""}-${product.finalPrice!""}-${product.deposit!""}-${product.productType!""}-${product.productSubtype!""}-${product.remainedNum!""}" disabled = disabled/>
                                            </#if>
                                        </td>
                                        <td>${product.title!""}</td>
                                        <td>${product.remainedNum!""}</td>
                                        <td>
                                            <#if product.remainedNum  gt 0 >
                                                <span onclick="plus('${product.price!""}-${product.finalPrice!""}-${product.deposit!""}-${product.productType!""}-${product.productSubtype!""}-${product.remainedNum!""}')" class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                                <input class="input-center" id="number-${product.price!""}-${product.finalPrice!""}-${product.deposit!""}-${product.productType!""}-${product.productSubtype!""}-${product.remainedNum!""}" name="number" style="width: 100px;" onblur="input('number-${product.price!""}-${product.finalPrice!""}-${product.deposit!""}-${product.productType!""}-${product.productSubtype!""}-${product.remainedNum!""}');" value = "0"/>
                                                <span onclick="minus('${product.price!""}-${product.finalPrice!""}-${product.deposit!""}-${product.productType!""}-${product.productSubtype!""}-${product.remainedNum!""}')" class="glyphicon glyphicon-minus" aria-hidden="true"></span>
                                            <#else >
                                                暂无工位
                                            </#if>
                                        </td>
                                        <td>￥${product.finalPrice!""}/${product.priceTypeStr!""}</td>
                                    </tr>
                                    </#list>
                                </#if>
                                </tbody>
                            </table>
                        </div></div><!-- /.cols -->
                    </div>
                    <div class="col-md-3"></div>
                </div>
                </br>

                <div class = "row">
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">
                                手机号
                                <span class="icon-info" aria-hidden="true" rel="tooltip" title="" data-original-title="需用该手机号登陆官网完成支付"></span>
                                <span class="text-danger">*</span>
                            </label>
                            <div class="col-md-9">
                                <input name="customerMobile" type="text"  class="form-control validate[required]"  />
                                </br>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">
                                姓名<span class="text-danger">*</span>
                            </label>
                            <div class="col-md-9">
                                <input name="customerName" type="text"  class="form-control validate[required]"  />
                                </br>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">
                                公司名称
                            </label>
                            <div class="col-md-9">
                                <input name="customerCompany" type="text"  class="form-control "  />
                                </br>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">
                                支付宝账号
                                <span class="icon-info" aria-hidden="true" rel="tooltip" title="" data-original-title="返利金额直接转入该支付宝"></span>
                                <span class="text-danger">*</span>
                            </label>
                            <div class="col-md-9">
                                <input name="customerAlipay" type="text"  class="form-control validate[required]"  />
                                </br>
                            </div>
                        </div>
                        <div class="panel-body">
                            <div class="tab-content">
                                <div id="top-pages" class="tab-pane active fade in">
                                    <hr class="mt-2x">
                                    <ul class="media-list">
                                        <li class="media">
                                            <div class="media-body">
                                                <p class="pull-right" id="depositAmount">0</p>
                                                <p class="media-heading">
                                                    预计押金金额: &nbsp;
                                                    <span class="icon-info" aria-hidden="true" rel="tooltip" title="" data-original-title="工位数 * 1000元/位"></span>
                                                </p>
                                            </div>
                                        </li>
                                        <li class="media">
                                            <div class="media-body">
                                                <p class="pull-right" id="leaseAmount">0</p>
                                                <p class="media-heading">
                                                    预计租金金额:&nbsp;
                                                    <span class="icon-info" aria-hidden="true" rel="tooltip" title="" data-original-title="返利基数"></span>
                                                </p>
                                            </div>
                                        </li>
                                        <li class="media">
                                            <div class="media-body">
                                                <p class="pull-right" id="totalTax">0</p>
                                                <p class="media-heading">
                                                    预计税金:&nbsp;
                                                    <span class="icon-info" aria-hidden="true" rel="tooltip" title="" data-original-title="租金 * 税金率6%"></span>
                                                </p>
                                            </div>
                                        </li>
                                        <li class="media">
                                            <div class="media-body">
                                                <p class="pull-right" id="totalAmount">0</p>
                                                <p class="media-heading">
                                                    预计总金额:&nbsp;
                                                    <span class="icon-info" aria-hidden="true" rel="tooltip" title="" data-original-title="总金额 = 租金 + 押金 + 税金"></span>
                                                </p>
                                            </div>
                                        </li>
                                        <li class="media">
                                            <div class="media-body">
                                                <p class="pull-right" id="totalRebates">0</p>
                                                <p class="media-heading">
                                                    预计返利:&nbsp;
                                                    <span class="icon-info" aria-hidden="true" rel="tooltip" title="" data-original-title="返利基数 * 返利比率"></span>
                                                </p>
                                            </div>
                                        </li>
                                        <li class="media">
                                            <div class="media-body">
                                                <p class="media-heading" >
                                                   	 说明:<br>
                                                   	 1,以上所有金额都是预计值，最终付款金额以SOHO3Q官网订单价格为准；<br>                          
                                                   	 2,最终返利金额以实际支付<strong style="font-size:24px;color:red">租金</strong>作为返利基数。
                                                </p>
                                            </div>
                                        </li>
                                    </ul>
                                </div><!-- /.tab-pane -->
                            </div><!-- /.tab-content -->
                        </div><!-- /.panel-body -->
                    </div>
                    <div class="col-md-3"></div>
                </div>
                </br>
                <div class="col-md-offset-3 col-md-9">
                    <a id="confirmButton" type="hidden"  data-toggle="modal" data-target="#customModal3">
                    </a>
                    <button onclick="confirmInformation()" class="btn soho-orange" style="left: 35%;">
                        确认信息
                    </button>
                    <button class="btn" type="reset" onclick="javascript:history.go(-1)">
                        返回
                    </button>
                </div>
            </div><!-- /.content-body -->

        <!-- customModal3 -->
        <div class="modal" id="customModal3" data-transition="flipYIn" tabindex="-1" role="dialog" aria-labelledby="customModal3Label" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header soho-orange">
                        <h4 class="modal-title">
                            <a href="#" data-dismiss="modal" class="pull-right" title="Continue shopping" data-toggle="tooltip" data-container="body">
                                <i class="icon-basket-loaded"></i>
                            </a>我的返利
                        </h4>
                    </div>
                    <div class="panel-body">
                        <div class="tab-content">
                            <div id="top-pages" class="tab-pane active fade in">
                                <hr class="mt-2x">
                                <ul class="media-list">
                                    <li class="media">
                                        <div class="media-body">
                                            <p class="pull-right" id="confirmDepositAmount"></p>
                                            <p class="media-heading">
                                                预计押金金额: &nbsp;
                                                <span class="icon-info" aria-hidden="true" rel="tooltip" title="" data-original-title="工位数 * 1000元/位"></span>
                                            </p>
                                        </div>
                                    </li>
                                    <li class="media">
                                        <div class="media-body">
                                            <p class="pull-right" id="confirmLeaseAmount"></p>
                                            <p class="media-heading">
                                                预计租金金额:&nbsp;
                                                <span class="icon-info" aria-hidden="true" rel="tooltip" title="" data-original-title="返利基数"></span>
                                            </p>
                                        </div>
                                    </li>

                                    <li class="media">
                                        <div class="media-body">
                                            <p class="pull-right" id="confirmTotalTax"></p>
                                            <p class="media-heading">
                                                预计税金:&nbsp;
                                                <span class="icon-info" aria-hidden="true" rel="tooltip" title="" data-original-title="租金 * 税率6%"></span>
                                            </p>
                                        </div>
                                    </li>
                                    <li class="media">
                                        <div class="media-body">
                                            <p class="pull-right" id="confirmTotalAmount"></p>
                                            <p class="media-heading">
                                                预计总金额:&nbsp;
                                                <span class="icon-info" aria-hidden="true" rel="tooltip" title="" data-original-title="该总金额 = 租金 + 押金 + 税金"></span>
                                            </p>
                                        </div>
                                    </li>
                                    <li class="media">
                                        <div class="media-body">
                                            <p class="pull-right" id="confirmTotalRebates"></p>
                                            <p class="media-heading">
                                                预计返利:&nbsp;
                                                <span class="icon-info" aria-hidden="true" rel="tooltip" title="" data-original-title="返利基数 * 返利百分比"></span>
                                            </p>
                                        </div>
                                    </li>
                                       <li class="media">
                                            <div class="media-body">
                                                <p class="media-heading" >
                                                   	 说明:<br>
                                                   	 1,以上所有金额都是预计值，最终付款金额以SOHO3Q官网订单价格为准；<br>                          
                                                   	 2,最终返利金额以实际支付<strong style="font-size:24px;color:red">租金</strong>作为返利基数。
                                                </p>
                                            </div>
                                        </li>
                                </ul>
                            </div><!-- /.tab-pane -->
                        </div><!-- /.tab-content -->
                    </div>
                    <table class="table no-margin">
                        <tbody id="confirmList">

                        </tbody>
                    </table>
                    <div class="modal-footer">
                        <a href="#" onclick="submitOrder();" class="btn soho-orange btn-nofill">申请返利</a>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->

            <!-- Template Setups -->
            <div class="modal fade" id="templateSetup">
                <div class="modal-dialog">
                    <!-- modal-content -->
                    <div class="modal-content"></div>
                </div><!-- /.modal-dialog -->
            </div><!-- /.templateSetup -->

        </div><!-- /.content -->
    </section><!-- /MAIN -->



    <!-- ============================================
    FOOTER SECTION
    =============================================== -->
    <footer class="footer-wrapper footer-default" role="contentinfo" data-init-footer="true">
        <div class="footer">
            <div class="pull-right text-muted"><small>京ICP备16037216号</small></div>
            <div>&copy;<em id="currentYear"></em>  XL</div>
        </div>
    </footer><!-- /.FOOTER -->

</main>
<@layoutFooter>
<script>
    var date = new Date;
    $("#currentYear").append(date.getFullYear());

    var rebates = "";
    var tax = ${tax!""};
    window.onload=function(){
        $.ajax({
            url:"${path}/ajax/info/rebates_ratio",
            type:"get",
            dataType:'json',
            success:function(data){
                if(data.errCode==0){
                    rebates = data.data.ratio;

                }else{
                    alertMessage(data.errCode);
                }
            },
            error:function (xhr, type, exception) {
                alert(type, "Failed");
            }
        });
    }

    //订单实体
    var createOrder = {
        customerMobile : "",
        customerName : "",
        customerCompany : "",
        customerAlipay : "",
        projectId : "",
        projectName : "",
        checkInDate : "",
        checkOutDate : "",
        periodMonth : "",
        periodWeek : "",
        leaseAmount : "",
        depositAmount : "",
        orderItems : [

        ]
    }



    /**
     * 确认信息页,对象装载
     */
    function confirmInformation(){
        var flag = true;
        var customerMobile = getValueByName("customerMobile");
        var customerName = getValueByName("customerName");
        var customerCompany = getValueByName("customerCompany");
        var customerAlipay = getValueByName("customerAlipay");
        var projectId = getValueById("projectId");
        var projectName = $("#projectId").find("option:selected").text();
        var checkInDate = getValueById("startTime");
        var periodMonth = getValueById("month");
        var periodWeek = getValueById("week");
        var checkOutDate = changeDate(checkInDate,periodMonth,periodWeek);
        var leaseAmount= getAmountValue("leaseAmount");
        var depositAmount = getAmountValue("depositAmount");

        createOrder.customerMobile = customerMobile;
        createOrder.customerName = customerName;
        createOrder.customerCompany = customerCompany;
        createOrder.customerAlipay = customerAlipay;
        createOrder.projectId = projectId;
        createOrder.projectName = projectName;
        createOrder.checkInDate = checkInDate;
        createOrder.checkOutDate = checkOutDate;
        createOrder.periodMonth = periodMonth;
        createOrder.periodWeek = periodWeek;
        createOrder.leaseAmount = leaseAmount;
        createOrder.depositAmount = depositAmount;
        createOrder.orderItems.splice(0,createOrder.orderItems.length);
        $("input[type='checkbox'][name='selectRoom']:checked").each(
                function(){
                    var checkBoxValue = $(this).val();
                    var roomValueArray =  checkBoxValue.split("-");
                    var originalPrice = roomValueArray[0];
                    var finalPrice = roomValueArray[1];
                    var depositPrice = roomValueArray[2];
                    var productType = roomValueArray[3];
                    var productSubType = roomValueArray[4];
                    var bookNum = $("#number-"+checkBoxValue).val();
                    if(bookNum > 0){
                        flag = false;
                        var orderItem = {
                            projectId : "",
                            originalPrice : "",
                            finalPrice : "",
                            depositPrice : "",
                            productType : "",
                            productSubType : "",
                            bookNum : ""
                        }
                        orderItem.projectId = projectId;
                        orderItem.bookNum = bookNum;
                        orderItem.depositPrice = depositPrice;
                        orderItem.finalPrice = finalPrice;
                        orderItem.originalPrice = originalPrice;
                        orderItem.productSubType = productSubType;
                        orderItem.productType = productType;
                       // var length = createOrder.orderItems.length;
                        //查询push为什么会覆盖问题
                        //createOrder.orderItems[length]=orderItem;
                        createOrder.orderItems.push(orderItem);

                    }
                }
        );
        if(flag){
            messageBox("提示信息","请至少选择一个商品");
        }else{
            $("#confirmButton").click();
            var str = "";
            var stationNumber = 0;
            var depositPrice = parseInt(createOrder.orderItems[0].depositPrice);
            var list = createOrder.orderItems;
            for(var i = 0;i<list.length;i++){
                var title = "";
                if(list[i].productSubType == 1){
                    title = list[i].productSubType + "人办公桌";
                }else{
                    title = list[i].productSubType + "人独立办公室";
                }
                stationNumber += parseInt(list[i].productSubType);
                var content = list[i].finalPrice + "/周 * " + (parseInt(createOrder.periodMonth) * 4 + parseInt(createOrder.periodWeek)) + "(周) * "+list[i].bookNum;
                var price = list[i].finalPrice * (parseInt(createOrder.periodMonth) * 4 + parseInt(createOrder.periodWeek)) * parseInt(list[i].bookNum);
                str +='<tr>'+
                        '<td>'+title+'</td>'+
                        '<td>¥'+content+'</td>'+
                       // '<td class="text-muted"><strong>¥'+price+'</strong></td>'+
                        '</tr>';
            }
            str +='<tr>'+
                    '<td>押金</td>'+
                    '<td><sup>¥</sup>'+depositPrice+'/位*'+stationNumber+'</td>'+
                    //'<td class="text-muted"><strong>¥'+parseInt(depositPrice) * parseInt(stationNumber)+'</strong></td>'+
                    '</tr>';
            setAmountValue("confirmLeaseAmount",createOrder.leaseAmount);
            setAmountValue("confirmDepositAmount",createOrder.depositAmount);
            setAmountValue("confirmTotalRebates",(createOrder.leaseAmount * getRebates()).toFixed(2));
            var taxTotal = (createOrder.leaseAmount * tax).toFixed(2);
            setAmountValue("confirmTotalTax",taxTotal);
            setAmountValue("confirmTotalAmount",parseInt(createOrder.leaseAmount)+parseInt(createOrder.depositAmount)+parseFloat(taxTotal));
            $("#confirmList").empty();
            $("#confirmList").append(str);
        }

    }

    function submitOrder(){
        if(createOrder.customerCompany == null || createOrder.customerCompany == ""){
            createOrder.customerCompany = createOrder.customerName;
        }
        $.ajax({
            url:"${path}/ajax/order/create",
            type:"post",
            dataType:'json',
            contentType:"application/json;charset=UTF-8",
            data:JSON.stringify(createOrder),
            success:function(data){
                if(data.errCode == 0){
                    window.location.href="${path}/question/station_tip.html";
                }
            },
            error:function(xhr, type, exception){
                alert(type, "Failed");
            }
        })
    }

    /**
     * 获取项目列表
     * @param projectId 项目id
     * @param checkInDate 开始时间
     * @param checkOutDate 结束时间
     */
    function getProductList(projectId,checkInDate,checkOutDate){
        //清零
        setAmountValue("leaseAmount",0);
        setAmountValue("depositAmount",0);
        setAmountValue("totalAmount",0);
        setAmountValue("totalRebates",0);
        setAmountValue("totalTax",0);

        var formDate = {
            projectId:projectId,
            checkInDate:checkInDate,
            checkOutDate:checkOutDate
        }
        $.ajax({
            url:"${path}/ajax/soho3q/product_list",
            type:"get",
            dataType:'json',
            data:formDate,
            success:function(data){
                if(data.errCode==0){
                    var list = data.data;
                    $("#productList").empty();
                    var str = "";
                    for(var i = 0;i<list.length;i++){
                        var product = list[i];
                        if(product.remainedNum > 0){
                            str += '<tr>'+
                                    '<td name="roomId"><input type="checkbox" name="selectRoom" value="'+product.price+'-'+product.finalPrice+'-'+product.deposit+'-'+product.productType+'-'+product.productSubtype+'-'+product.remainedNum+'"/></td>'+
                                    '<td>'+product.title+'</td>'+
                                    '<td>'+product.remainedNum+'</td>'+
                                    '<td><span onclick="plus(\''+product.price+'-'+product.finalPrice+'-'+product.deposit+'-'+product.productType+'-'+product.productSubtype+'-'+product.remainedNum+'\')" class="glyphicon glyphicon-plus" aria-hidden="true"></span>'+
                                    '<input id="number-'+product.price+'-'+product.finalPrice+'-'+product.deposit+'-'+product.productType+'-'+product.productSubtype+'-'+product.remainedNum+'" name="number" style="width: 100px;" onblur="input(\'number-'+product.price+'-'+product.finalPrice+'-'+product.deposit+'-'+product.productType+'-'+product.productSubtype+'-'+product.remainedNum+'\');" value = "0"/>'+
                                    '<span onclick="minus(\''+product.price+'-'+product.finalPrice+'-'+product.deposit+'-'+product.productType+'-'+product.productSubtype+'-'+product.remainedNum+'\')" class="glyphicon glyphicon-minus" aria-hidden="true"></span></td>'+
                                    '<td>￥'+product.finalPrice+'/'+product.priceTypeStr+'</td>'+
                                    '</tr>';
                        }else{
                            str += '<tr>'+
                                    '<td name="roomId"><input type="checkbox" name="selectRoom" value="'+product.price+'-'+product.finalPrice+'-'+product.deposit+'-'+product.productType+'-'+product.productSubtype+'-'+product.remainedNum+'" disabled = disabled/></td>'+
                                    '<td>'+product.title+'</td>'+
                                    '<td>'+product.remainedNum+'</td>'+
                                    '<td>暂无工位</td>'+
                                    '<td>￥'+product.finalPrice+'/'+product.priceTypeStr+'</td>'+
                                    '</tr>';
                        }
                    }
                    $("#productList").append(str);
                }else{
                    alertMessage(data.errCode);
                }
            },
            error:function (xhr, type, exception) {
                alert(type, "Failed");
            }
        });
    }

    function getRebates(){
        var number = (parseFloat(rebates.replace("%",""))/100).toFixed(3);
        return number;
    }


    /**
     * 错误提示
     */
    function alertMessage(code){
        var key ={
            "106":"请填写账号密码",
            "104":"请重新登录账户",
            "112":"账户已存在",
            "unknow":"#"+code
        }
        if(key[code]) message=key[code];
        else message=key.unknow;
        if(code == 0){
            messageBox("提示信息","恭喜您已成功下单");
        }else{
            messageBox("错误提示",message);
        }
    }

    /**
     * 重新获取房源
     */
    function reacquireRoom(){
        var projectId = $("#projectId").val();
        var startTime = $("#startTime").val();
        var month = $("#month").val();
        var week = $("#week").val();
        var endTime = changeDate(startTime,month,week);
        getProductList(projectId,startTime,endTime);
    }

    /**
     *  获取数值
     */
    function getAmountValue(id){
       var amount = $("#"+id).text();
        if(amount != null && amount != "" && amount !=0){
            return parseFloat(amount);
        }else{
            return 0;
        }
    }

    /**
     * 通过id获取值
     */
    function getValueById(id){
        var value = $("#"+id).val()
        if(value != undefined && value != null && value != ""){
            return value;
        }else{
            return "";
        }
    }

    /**
     * 通过名字
     */
    function getValueByName(name){
        var value = $("input[name='"+name+"']").val()
        if(value != undefined && value != null && value != ""){
            return value;
        }else{
            return "";
        }
    }

    /**
     * 设置数值
     */
    function setAmountValue(id,value){
        var amount = $("#"+id);
        amount.empty();
        if(value != undefined && value != null && value != ""){
            amount.append(value);
        }else{
            amount.append(0);
        }
    }

//    点击加号
    function plus(id){
        var number = parseInt($("#number-"+id).val());
        var array = id.split("-");
        var remainedNum = parseInt(array[5]);
        if(number < remainedNum){
            number ++;
            checkedItem(id,'checked');
        }else{
            messageBox("提示信息","不能超过工位剩余数");
        }
        $("#number-"+id).val(number);
        sumTotal();
    }

//    点击减号
    function minus(id){
        var number = parseInt($("#number-"+id).val());
        if(number > 0){
            number --;
            if(number == 0 ){
                checkedItem(id,'cancel');
            }
        }else{
            checkedItem(id,'cancel');
            messageBox("提示信息","工位数不能小于零");
        }
        $("#number-"+id).val(number);
        sumTotal();
    }

//    手动输入
    function input(id){
        var number = parseInt($("#number-"+id).val());
        var array = id.split("-");
        var remainedNum = parseInt(array[5]);
        if(number >= 0 && number <= remainedNum){
            $("#number-"+id).val(number);
            checkedItem(id,'checked');
        }else{
            $("#number-"+id).val(0);
            checkedItem(id,'cancel');
            messageBox("提示信息","工位数不能小于零,并且不能大于剩余工位数");
        }

        sumTotal();
    }

    function sumTotal(){
        var sumLeaseAmount = 0;
        var sumDepositAmount = 0;
        var sumTotalAmount = 0;
        var totalRebates = 0;
        var totalTax = 0;
        var periodMonth = getValueById("month");
        var periodWeek = getValueById("week");
        $("input[type='checkbox'][name='selectRoom']:checked").each(
                function(){
                    var checkBoxValue = $(this).val();
                    var roomValueArray =  checkBoxValue.split("-");
                    var checkedNumber =  $("#number-"+checkBoxValue).val();
                    sumLeaseAmount += roomValueArray[1] * checkedNumber *(parseInt(periodMonth) * 4 + parseInt(periodWeek));
                    sumDepositAmount += roomValueArray[2] * checkedNumber;
                }
        );
        totalRebates = (sumLeaseAmount * getRebates()).toFixed(2);
        totalTax = (sumLeaseAmount * tax).toFixed(2);
        sumTotalAmount = sumLeaseAmount + sumDepositAmount + parseFloat(totalTax);
        setAmountValue("leaseAmount",sumLeaseAmount);
        setAmountValue("depositAmount",sumDepositAmount);
        setAmountValue("totalAmount",sumTotalAmount);
        setAmountValue("totalRebates",totalRebates);
        setAmountValue("totalTax",totalTax);

    }

    function checkedItem(value,status){
        if(status == "cancel"){
            $("input[type='checkbox'][name='selectRoom']").each(
                    function(){
                        var checkBoxValue = $(this).val();
                        if(checkBoxValue == value){
                            $(this).prop("checked",false);
                        }
                    }
            );
        }else{
            $("input[type='checkbox'][name='selectRoom']").each(
                    function(){
                        var checkBoxValue = $(this).val();
                        if(checkBoxValue == value){
                            $(this).prop("checked", true);
                        }
                    }
            );
        }
    }


    /**
     * 修改时间方法
     * @param startTime 开始时间
     * @param month 添加月份
     * @param day 添加天数
     */
    function changeDate(startTime,month,day){
        var now = new Date(startTime);
       // now.setDate(startTime);
        now.setMonth(now.getMonth() + parseInt(month))
        now.setDate(now.getDate() +  parseInt(day));
        return $.format.date(now,"yyyy-MM-dd");
    }

</script>
</@layoutFooter>