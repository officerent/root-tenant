<#include "../web_common/_layout.ftl" />

<@layoutHead>
    <#if source == 'ROOM'>
        <#assign meeting="active" />
    <#else>
        <#assign coupon="active" />
    </#if>
</@layoutHead>
<@layoutBody classBody="wrapkit-sidebar-left wrapkit-sidebar-lg bg-grd-dark wrapkit-sidebar-horizontal" >
<main class="wrapkit-wrapper" id="wrapper" data-init-layout="true">

    <!-- ============================================
    MAIN CONTENT SECTION
    =============================================== -->
    <section class="content-wrapper" role="main" data-init-content="true">
        <div class="content">

            <div class="content-body">
                <input type="hidden" id="memberId" value="${member.couponId!""}">
                <input type="hidden" id="memberPrice" value="${member.price!""}">

                <div class="row">
                    <div class="col-md-3">

                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">
                                手机号:
                                <span class="text-danger">*</span>
                                <span class="icon-info" aria-hidden="true" rel="tooltip" title="" data-original-title="需用该手机号登陆官网完成支付"></span>
                            </label>
                            <div class="col-md-9">
                                <input name="customerMobile" onblur="checkUser();" type="text" class="form-control validate[required]"/>
                                </br>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">
                                姓名:
                                <span class="text-danger">*</span>
                            </label>
                            <div class="col-md-9">
                                <input name="customerName" type="text" class="form-control validate[required]"/>
                                </br>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">
                                公司名称:
                            </label>
                            <div class="col-md-9">
                                <input name="customerCompany" type="text" class="form-control "/>
                                </br>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">
                                支付宝账号:
                                <span class="text-danger">*</span>
                                <span class="icon-info" aria-hidden="true" rel="tooltip" title="" data-original-title="返利金额直接转入该支付宝"></span>
                            </label>
                            <div class="col-md-9">
                                <input name="customerAlipay" type="text" class="form-control validate[required]"/>
                                </br>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3"></div>
                </div>

                </br>
                <div class="row">
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th>产品名称</th>
                                    <th>选择数量</th>
                                    <th>价格</th>
                                </tr>
                                </thead>
                                <tbody >
                                    <#if couponList??>
                                        <#list couponList as coupon>
                                        <tr>
                                            <td class="col-md-2" id="coupon">${coupon.name!""}</td>
                                            <td class="col-md-2">
                                                <span id="spanPlus" onclick="plusCoupon();" class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                                <input class="input-center" id="number" name="number" style="width: 100px;" onblur="inputCoupon();" value="1"/>
                                                <span id="spanMinus" onclick="minusCoupon();" class="glyphicon glyphicon-minus" aria-hidden="true"></span>
                                            </td>
                                            <td class="col-md-2">
                                                <span id="displayPrice">￥${coupon.price!""}</span>
                                                <span id="discountPrice"></span>/张
                                                <span id="alertTip" class="icon-info display-none" aria-hidden="true" rel="tooltip" title="" data-original-title="第一次购买,享有99优惠"></span>
                                                <input type="hidden" id="couponId" value="${coupon.couponId!""}">
                                                <input type="hidden" id="price" value="${coupon.price!""}">
                                            </td>
                                        </tr>
                                        </#list>
                                    </#if>
                                </tbody>
                            </table>
                        </div>
                        <div class="panel-body">
                            <div class="tab-content">
                                <div id="top-pages" class="tab-pane active fade in">
                                    <hr class="mt-2x">
                                    <ul class="media-list">
                                        <li class="media">
                                            <div class="media-body">
                                                <p class="pull-right" id="payAmount">0</p>
                                                <p class="media-heading">
                                                    预计商品金额: &nbsp;
                                                    <span class="icon-info" aria-hidden="true" rel="tooltip" title="" data-original-title="返利基数"></span>
                                                </p>
                                            </div>
                                        </li>
                                        <li class="media">
                                            <div class="media-body">
                                                <p class="pull-right" id="taxAmount">0</p>
                                                <p class="media-heading">
                                                    预计税金: &nbsp;
                                                    <span class="icon-info" aria-hidden="true" rel="tooltip" title="" data-original-title="商品金额*税率6%"></span>
                                                </p>
                                            </div>
                                        </li>
                                        <li class="media">
                                            <div class="media-body">
                                                <p class="pull-right" id="payTotalAmount">0</p>
                                                <p class="media-heading">
                                                    预计支付总金额: &nbsp;
                                                    <span class="icon-info" aria-hidden="true" rel="tooltip" title="" data-original-title="商品金额+税金"></span>
                                                </p>
                                            </div>
                                        </li>
                                        <li class="media">
                                            <div class="media-body">
                                                <p class="pull-right" id="totalRebates">0</p>
                                                <p class="media-heading">
                                                    预计返利:&nbsp;
                                                    <span class="icon-info" aria-hidden="true" rel="tooltip" title="" data-original-title="返利基数 * 返利百分比"></span>
                                                </p>
                                            </div>
                                        </li>
                                        <li class="media">
                                            <div class="media-body">
                                                <p class="media-heading">
                                                   	 说明:<br>
                                                   	 1,以上所有金额都是预计值，最终付款金额以SOHO3Q官网订单价格为准；<br>                          
                                                   	 2,最终返利金额以实际支付<strong style="font-size:24px;color:red">商品金额</strong>作为返利基数。
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
                <div class="col-md-offset-3 col-md-9">
                    <a id="confirmButton" type="hidden" data-toggle="modal" data-target="#customModal3">
                    </a>
                    <button onclick="confirmInformation()" class="btn soho-orange" style="left: 35%;">
                        确认信息
                    </button>
                    <button class="btn" type="reset" onclick="javascript:history.go(-1)">
                        返回
                    </button>
                </div>
            </div>
            <!-- /.content-body -->

            <!-- customModal3 -->
            <div class="modal" id="customModal3" data-transition="flipYIn" tabindex="-1" role="dialog"
                 aria-labelledby="customModal3Label" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header soho-orange">
                            <h4 class="modal-title">
                                <a href="#" data-dismiss="modal" class="pull-right" title="Continue shopping"
                                   data-toggle="tooltip" data-container="body">
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
                                                <p class="pull-right" id="confirmName"></p>
                                                <p class="media-heading">
                                                    用户名: &nbsp;
                                                </p>
                                            </div>
                                        </li>
                                        <li class="media">
                                            <div class="media-body">
                                                <p class="pull-right" id="confirmMobile"></p>
                                                <p class="media-heading">
                                                    手机号:&nbsp;
                                                    <span class="icon-info" aria-hidden="true" rel="tooltip" title="" data-original-title="代下单手机号"></span>
                                                </p>
                                            </div>
                                        </li>
                                        <li class="media">
                                            <div class="media-body">
                                                <p class="pull-right" id="confirmCompanyName"></p>
                                                <p class="media-heading">
                                                    公司名称:&nbsp;
                                                </p>
                                            </div>
                                        </li>
                                        <li class="media">
                                            <div class="media-body">
                                                <p class="pull-right" id="confirmAlipay"></p>
                                                <p class="media-heading">
                                                    支付宝账号:&nbsp;
                                                    <span class="icon-info" aria-hidden="true" rel="tooltip" title="" data-original-title="返利金额至该支付宝"></span>
                                                </p>
                                            </div>
                                        </li>
                                        <li class="media">
                                            <div class="media-body">
                                                <p class="pull-right" id="confirmPayAmount"></p>
                                                <p class="media-heading">
                                                    预计商品金额:&nbsp;
                                                    <span class="icon-info" aria-hidden="true" rel="tooltip" title="" data-original-title="返利基数"></span>
                                                </p>
                                            </div>
                                        </li>
                                        <li class="media">
                                            <div class="media-body">
                                                <p class="pull-right" id="confirmTaxAmount"></p>
                                                <p class="media-heading">
                                                    预计税金:&nbsp;
                                                    <span class="icon-info" aria-hidden="true" rel="tooltip" title="" data-original-title="商品金额 * 税率6%"></span>
                                                </p>
                                            </div>
                                        </li>
                                        <li class="media">
                                            <div class="media-body">
                                                <p class="pull-right" id="confirmTotalAmount"></p>
                                                <p class="media-heading">
                                                    预计总金额:&nbsp;
                                                    <span class="icon-info" aria-hidden="true" rel="tooltip" title="" data-original-title="商品金额+税金"></span>
                                                </p>
                                            </div>
                                        </li>
                                        <li class="media">
                                            <div class="media-body">
                                                <p class="pull-right" id="confirmRebatesAmount"></p>
                                                <p class="media-heading">
                                                    预计返利:&nbsp;
                                                    <span class="icon-info" aria-hidden="true" rel="tooltip" title="" data-original-title="返利基数*返利比率"></span>
                                                </p>
                                            </div>
                                        </li>
                                        <li class="media">
                                            <div class="media-body">
                                                <p class="media-heading" >
                                                   	 说明:<br>
                                                   	 1,以上所有金额都是预计值，最终付款金额以SOHO3Q官网订单价格为准；<br>                          
                                                   	 2,最终返利金额以实际支付<strong style="font-size:24px;color:red">商品金额</strong>作为返利基数。
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
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
            <!-- /.modal -->

            <!-- Template Setups -->
            <div class="modal fade" id="templateSetup">
                <div class="modal-dialog">
                    <!-- modal-content -->
                    <div class="modal-content"></div>
                </div>
                <!-- /.modal-dialog -->
            </div>
            <!-- /.templateSetup -->

        </div>
        <!-- /.content -->
    </section>
    <!-- /MAIN -->


    <!-- ============================================
    FOOTER SECTION
    =============================================== -->
    <footer class="footer-wrapper footer-default" role="contentinfo" data-init-footer="true">
        <div class="footer">
            <div class="pull-right text-muted">
                <div class="pull-right text-muted"><small>京ICP备16037216号</small></div>
            </div>
            <div>&copy;<em id="currentYear"></em> XL</div>
        </div>
    </footer>
    <!-- /.FOOTER -->

</main>
</@layoutBody>
<@layoutFooter>

<script>
    var date = new Date;
    $("#currentYear").append(date.getFullYear());
    var isMember = false;
    var ratio = "";
    var displayPrice = "";
    var tax = ${tax!""};

    window.onload=function(){
        $.ajax({
            url:"${path}/ajax/info/rebates_ratio",
            type:"get",
            dataType:'json',
            success:function(data){
                if(data.errCode==0){
                    ratio = data.data.ratio;
                    sumTotal();
                }else{
                    alertMessage(data.errCode);
                }
            },
            error:function (xhr, type, exception) {
                alert(type, "Failed");
            }
        });

    }

    function checkUser(){
        var customerMobile = getValueByName("customerMobile");
        $.ajax({
            url:"${path}/ajax/member/is_member",
            type:"post",
            dataType:'json',
            data:{
                mobile:customerMobile
            },
            success:function(data){
                if(data.errCode==0){
                    var member = data.data;
                    isMember = member.isMember;
                    if(!isMember){
                        displayPrice = replaceStr(getTextValueById("displayPrice"),"￥","");
                        var memberPrice = getValueById("memberPrice");
                        setValueById("price",memberPrice);
                        setAmountValue("discountPrice","￥"+memberPrice);
                        setAmountValue("displayPrice","<s>￥"+displayPrice+"</s>");
                        $("#spanPlus").addClass("display-none");
                        $("#spanMinus").addClass("display-none");
                        $("#alertTip").removeClass("display-none");
                        $('#number').attr("disabled",true);
                        setValueById('number',1);
                    }else{
                        if(!displayPrice == ""){
                            setValueById("price",displayPrice);
                            setAmountValue("displayPrice","￥"+displayPrice);
                            setTextValueById("discountPrice","");
                            $("#spanPlus").removeClass("display-none");
                            $("#spanMinus").removeClass("display-none");
                            $("#alertTip").addClass("display-none");
                            $('#number').attr("disabled",false);

                        }
                    }
                    sumTotal();
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
    var createCouponOrder = {
        customerMobile: "",
        customerName: "",
        customerCompany: "",
        customerAlipay: "",
        couponOrderItems: []
    }

    var couponOrderItems = {
        couponId: 0,
        amount: 0,
        price: 0,
        giftCouponId: 0
    }

    /**
     * 获取数值型返利率
     */
    function getRebates(){
        var number = (parseFloat(ratio.replace("%",""))/100).toFixed(3);
        return number;
    }


    /**
     * 确认信息页,对象装载
     */
    function confirmInformation() {
        var title = $("#coupon").text();
        var payAmount = getAmountValue("payAmount");
        var customerMobile = getValueByName("customerMobile");
        var customerName = getValueByName("customerName");
        var customerCompany = getValueByName("customerCompany");
        var customerAlipay = getValueByName("customerAlipay");
        createCouponOrder.customerAlipay = customerAlipay;
        createCouponOrder.customerCompany = customerCompany;
        createCouponOrder.customerMobile = customerMobile;
        createCouponOrder.customerName = customerName;
        createCouponOrder.couponOrderItems.splice(0,createCouponOrder.couponOrderItems.length);
        if(!isMember){
            couponOrderItems.couponId = getValueById("memberId");
            couponOrderItems.giftCouponId = getValueById("couponId");
        }else{
            couponOrderItems.couponId = getValueById("couponId");
        }
        couponOrderItems.price = getValueById("price");
//        var coupon = getValueById("coupon");
//        var couponArray = coupon.split("-");
        var number = getValueById("number");
//        if(couponArray[2] != ""){
//            couponOrderItems.couponId = couponArray[0];
//            couponOrderItems.price = couponArray[1];
//        }else{
//            giftCoupon = $('input[name="giftCouponId"][checked]');
//            if(giftCoupon.length == 0){
//                messageBox("提示信息","请至少选择一种赠品");
//                return;
//            }
//            couponOrderItems.couponId = couponArray[0];
//            couponOrderItems.price = couponArray[1];
//            couponOrderItems.giftCouponId = giftCoupon.val();
//        }
        couponOrderItems.amount = number;
        createCouponOrder.couponOrderItems.push(couponOrderItems);

        $("#confirmButton").click();
        var str = "";
        var list = createCouponOrder.couponOrderItems;
        for(var i = 0; i<list.length ; i++){
            var content = list[i].price + "/张" + " * " + list[i].amount +"(张)";
            var price = list[i].price * (parseInt(list[i].amount));
            str += '<tr>' +
                    '<td>' + title + '</td>' +
                    '<td>¥' + content + '</td>' +
                   // '<td class="text-muted"><strong>¥' + price + '</strong></td>' +
                    '</tr>';
        }
        var payAmount = parseInt(payAmount);
        var rebatesAmount = (parseInt(payAmount) * getRebates()).toFixed(2);
        var taxAmount = (parseInt(payAmount) * tax).toFixed(2);
        var totalAmount = parseInt(payAmount) + parseFloat(taxAmount);

        setAmountValue("confirmPayAmount", payAmount);
        setAmountValue("confirmTaxAmount",taxAmount);
        setAmountValue("confirmRebatesAmount",rebatesAmount);
        setAmountValue("confirmTotalAmount",totalAmount);
        setTextValueById("confirmName", createCouponOrder.customerName);
        setTextValueById("confirmMobile", createCouponOrder.customerMobile);
        setTextValueById("confirmCompanyName", createCouponOrder.customerCompany);
        setTextValueById("confirmAlipay", createCouponOrder.customerAlipay);
        $("#confirmList").empty();
        $("#confirmList").append(str);
    }

    function submitOrder() {
        $.ajax({
            url: "${path}/ajax/order/create",
            type: "post",
            dataType: 'json',
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(createCouponOrder),
            success: function (data) {
                if(data.errCode == 0){
                    window.location.href="${path}/question/coupon_tip.html";
                }else{
                    alertMessage(data.errCode);
                }
            },
            error: function (xhr, type, exception) {
                alert(type, "Failed");
            }
        })
    }

    /**
     * 错误提示
     */
    function alertMessage(code) {
        var key = {
            "106": "请填写账号密码",
            "104": "请重新登录账户",
            "112": "账户已存在",
            "unknow": "#" + code
        }
        if (key[code]) message = key[code];
        else message = key.unknow;
        if (code == 0) {
            alert("恭喜您已成功申请返利");
        } else {
            alert(message);
        }
    }

    //    $(function(){
    //        reacquireCoupon();
    //    });
    //
    //    /**
    //     * 选择工位券
    //     */
    //    function reacquireCoupon() {
    //        var couponArray = $("#coupon").val().split("-");
    //        setValueById("number", 1);
    //        setAmountValue("payAmount", couponArray[1]);
    //        setAmountValue("price", couponArray[1]);
    //        if(couponArray[2] == null || couponArray[2] ==""){
    //            $("#selectCouponList").prop("style","");
    //            $("#selectCoupon").empty();
    //            getCouponList();
    //        }else{
    //            $("#selectCouponList").prop("style","display: none");
    //        }
    //    }

    /**
     *  获取数值
     */
    function getAmountValue(id) {
        var amount = $("#" + id).text();
        if (amount != null && amount != "" && amount != 0) {
            return parseFloat(amount);
        } else {
            return 0;
        }
    }



    /**
     * 设置数值
     */
    function setAmountValue(id, value) {
        var amount = $("#" + id);
        amount.empty();
        if (value != undefined && value != null && value != "") {
            amount.append(value);
        } else {
            amount.append(0);
        }
    }

    //    点击加号
    function plusCoupon() {
        var number = parseInt($("#number").val());
        number++;
        $("#number").val(number);
        sumTotal();
    }

    //    点击减号
    function minusCoupon() {
        var number = parseInt($("#number").val());
        if (number > 1) {
            number--;
        } else {
            messageBox("提示信息","工位数不能小于等于零");
        }
        $("#number").val(number);
        sumTotal();
    }

    //    手动输入
    function inputCoupon() {
        var number = parseInt($("#number").val());
        if (number >= 0){
            $("#number").val(number);
        } else {
            $("#number").val(0);
            alert("工位数不能小于零,并且不能大于剩余工位数");
        }
        sumTotal();
    }

    <#--function getCouponList() {-->
    <#--$.ajax({-->
    <#--url: "${path}/ajax/soho3q/coupon_list",-->
    <#--type: "get",-->
    <#--dataType: 'json',-->
    <#--contentType: "application/json;charset=UTF-8",-->
    <#--success: function (data) {-->
    <#--if (data.errCode == 0) {-->
    <#--var couponArray = {-->
    <#--couponList:[],-->
    <#--};-->
    <#--var arrayCoupon = data.data;-->
    <#--for (var i = 0; i < arrayCoupon.length; i++) {-->
    <#--var coupon = arrayCoupon[i];-->
    <#--if(coupon.productType == "ROOM" || coupon.productType == "OPEN_STATION"){-->
    <#--couponArray.couponList.push(coupon);-->
    <#--}-->
    <#--}-->
    <#--var html = template('couponRadio', couponArray);-->
    <#--$("#selectCoupon").append(html);-->
    <#--} else {-->
    <#--alertMessage(data.errCode);-->
    <#--}-->
    <#--},-->
    <#--error: function (xhr, type, exception) {-->
    <#--alert(type, "Failed");-->
    <#--}-->
    <#--})-->
    <#--}-->

    function sumTotal() {
        var payAmount = 0;
        var price = parseFloat(getValueById("price"));
        var number = parseInt(getValueById("number"));
        payAmount = (price * number).toFixed(2);
        var ratioValue = getRebates();
        var rebates = payAmount * ratioValue;
        var taxAmount = (payAmount * tax).toFixed(2);
        var sumTotalAmount = parseFloat(payAmount) + parseFloat(taxAmount);
        setAmountValue("payAmount", payAmount);
        setAmountValue("taxAmount", taxAmount);
        setAmountValue("payTotalAmount", sumTotalAmount);
        setAmountValue("totalRebates",rebates);
    }

    function checkedItem(value, status) {
        if (status == "cancel") {
            $("input[type='checkbox'][name='selectRoom']").each(
                    function () {
                        var checkBoxValue = $(this).val();
                        if (checkBoxValue == value) {
                            $(this).prop("checked", false);
                        }
                    }
            );
        } else {
            $("input[type='checkbox'][name='selectRoom']").each(
                    function () {
                        var checkBoxValue = $(this).val();
                        if (checkBoxValue == value) {
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
    function changeDate(startTime, month, day) {
        var now = new Date(startTime);
        // now.setDate(startTime);
        now.setMonth(now.getMonth() + parseInt(month))
        now.setDate(now.getDate() + parseInt(day));
        return $.format.date(now, "yyyy-MM-dd");
    }

</script>
</@layoutFooter>

