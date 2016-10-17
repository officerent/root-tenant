<#include "../../common/_layout.ftl" />

<@layoutHead title="订单列表">

</@layoutHead>
<@layoutBody>
<form id="form1" method="post">
    <section class="content-wrapper" role="main">
        <div class="content">
            <div class="content-bar">
                <ul class="breadcrumb breadcrumb-angle">
                    <li><a href="#" aria-label="home"><i class="fa fa-home"></i></a></li>
                    <li class="active">订单管理</li>
                    <li class="active">订单列表</li>
                </ul>
            </div><!-- /.content-bar -->


            <div class="content-body">
                <!--正文内容 开始-->
                <div class="col-md-12">
                    <h3>订单列表</h3>
                    <div class="panel fade in panel-default panel-fill" data-fill-color="true" data-init-panel="true">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="panel-body">
                                    <div class="form-group">
                                        <label class="col-md-2 control-label">客户姓名:</label>
                                        <div class="col-md-4">
                                            <div class="input-group input-group-in">
                                                <input name="customerName" class="form-control" value = "${order.customerName!''}"/>                                        </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-2 control-label">客户手机号:</label>
                                        <div class="col-md-4">
                                            <div class="input-group input-group-in">
                                                <input name="customerMobile" class="form-control" value = "${order.customerMobile!''}"/>                                        </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-2 control-label"></label>
                                        <div class="col-md-4">
                                            <button class="btn btn-success mb-1x mr-1x">搜索</button>
                                        </div>
                                    </div>
                                </div><!-- /panel-body -->
                            </div>
                            </br>
                        </div>
                    </div>
                    <div class="table-responsive">

                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>客户姓名</th>
                                <th>客户手机号</th>
                                <th>客户支付宝账号</th>
                                <th>销售名称</th>
                                <th>订单状态</th>
                                <th>税后佣金</th>
                                <th>返利金额</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                                <#list data as d >
                                <tr class="odd" role="row">
                                    <td>${d.customerName!''}</td>
                                    <td>${d.customerMobile!''}</td>
                                    <td>${d.customerAlipay!''}</td>
                                    <td>${d.userName!""}</td>
                                    <td>
                                        <#if d.status == 0>
                                            待确认
                                        </#if>
                                        <#if d.status == 1>
                                            已确认
                                        </#if>
                                        <#if d.status == 2>
                                            已支付
                                        </#if>
                                        <#if d.status == 3>
                                            已收佣金
                                        </#if>
                                        <#if d.status == 4>
                                            收到返利
                                        </#if>
                                        <#if d.status == 5>
                                            已取消
                                        </#if>
                                    </td>
                                    <td>${d.bonusAfterTax/100!""}</td>
                                    <td>${d.rebatesAmount/100!""}</td>
                                    <td>
                                        <a class="btn btn-info mb-1x mr-1x" href="${path}/admin/order/see_one_order.html?orderId=${d.orderId}">查看详情</a>
                                        <#if d.status == 3>
                                            <a class="btn btn-danger mb-1x mr-1x" href="${path}/admin/order/update_status.html?orderId=${d.orderId}">已打款</a>
                                        </#if>
                                    </td>
                                </tr>
                                </#list>
                            </tbody>
                        </table>
                    </div><!-- /.table-responsive -->
                </div><!-- /.cols -->
                <!--正文内容 结束-->
                <#include "../../common/page.ftl">
            </div><!-- /.panel -->
        </div><!-- /.content-body -->


        <!-- Template Setups -->
        <div class="modal fade" id="templateSetup">
            <div class="modal-dialog">
                <!-- modal-content -->
                <div class="modal-content"></div>
            </div><!-- /.modal-dialog -->
        </div><!-- /.templateSetup -->

        </div><!-- /.content -->
    </section><!-- /MAIN -->
<form>

</@layoutBody>

<@layoutFooter>

</@layoutFooter>
