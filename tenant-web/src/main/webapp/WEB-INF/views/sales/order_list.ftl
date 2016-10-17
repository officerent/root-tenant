<#include "../web_common/_layout.ftl" />

<@layoutHead>

    <#assign order="active" />
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
                <div class="table-responsive">

                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>返利单号</th>
                            <th>下单日期</th>
                            <th>shoh3q官网订单号</th>
                            <th>状态</th>
                            <th>返利金额(元)</th>
                        </tr>
                        </thead>
                        <tbody id ="productList">
                            <#if orderList??>
                                <#list orderList as order>
                                    <tr>
                                        <td>${order.orderId!""}</td>
                                        <td>${order.orderDate!""}</td>
                                        <td>
	                                        <#if order.soho3qOrderNum??>
	    										${order.soho3qOrderNum!""}
	    									<#else>
	    										<span class="icon-info" aria-hidden="true" rel="tooltip" title="" data-original-title="您需要用soho3q官方app或者手机网页m.soho3q.com完成订单确认才有订单号哦。"></span>
											</#if>
										</td>
                                        <td>
	                                        <#if order.status==0>
		    										<a rel="tooltip"  title="" data-original-title="请用手机号登陆soho3q官方app或者手机版官网m.soho3q.com:个人中心->我的订单->查看众包订单">订单待确认</a>
		    								<#elseif order.status==1>	    								
		    									<a rel="tooltip"  title="" data-original-title="请用手机号登陆soho3q官方app或者手机版官网m.soho3q.com:个人中心->我的订单">订单待支付</a>		    										
		    								<#elseif order.status==2>
		    										返利待转帐
		    								<#elseif order.status==3>
		    										返利已转帐
											</#if>
                                        </td>
                                        <td>
                                        	<#if order.rebatesAmount??>
                                        		￥${order.rebatesAmount!""}
                                        	<#else>
                                        		<span class="icon-info" aria-hidden="true" rel="tooltip" title="" data-original-title="您需要用soho3q官方app或者手机网页m.soho3q.com完成支付才能看到返利金额哦。"></span>
											</#if>
                                        	
                                        </td>
                                    </tr>
                                </#list>
                            </#if>
                        </tbody>
                    </table>
                </div><!-- /.table-responsive -->
                </div><!-- /.cols -->
				<#include "../web_common/page.ftl">
            </div><!-- /.content-body -->

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
                        '<td class="text-muted"><strong>¥'+price+'</strong></td>'+
                        '</tr>';
            }
            str +='<tr>'+
                    '<td>押金</td>'+
                    '<td><sup>¥</sup>'+depositPrice+'/位*'+stationNumber+'</td>'+
                    '<td class="text-muted"><strong>¥'+parseInt(depositPrice) * parseInt(stationNumber)+'</strong></td>'+
                    '</tr>';
            setAmountValue("confirmLeaseAmount",createOrder.leaseAmount);
            setAmountValue("confirmDepositAmount",createOrder.depositAmount);
            setAmountValue("confirmTotalAmount",parseInt(createOrder.leaseAmount)+parseInt(createOrder.depositAmount));
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
                alertMessage(data.errCode);
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
        var periodMonth = getValueById("month");
        var periodWeek = getValueById("week");
        $("input[type='checkbox'][name='selectRoom']:checked").each(
                function(){
                    var checkBoxValue = $(this).val();
                    var roomValueArray =  checkBoxValue.split("-");
                    var checkedNumber =  $("#number-"+checkBoxValue).val();
                    sumLeaseAmount += roomValueArray[1] * checkedNumber *(parseInt(periodMonth) * 4 + parseInt(periodWeek));
                    sumDepositAmount += roomValueArray[4] * roomValueArray[2] * checkedNumber;
                }
        );
        sumTotalAmount = sumLeaseAmount + sumDepositAmount;
        setAmountValue("leaseAmount",sumLeaseAmount);
        setAmountValue("depositAmount",sumDepositAmount);
        setAmountValue("totalAmount",sumTotalAmount);
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