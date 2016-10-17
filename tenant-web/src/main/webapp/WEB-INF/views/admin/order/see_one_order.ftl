<#include "../../common/_layout.ftl" />

<@layoutHead title="查看订单详情">

</@layoutHead>
<@layoutBody>
<form id="form1" method="post" action="" role="form" class="form-horizontal form-bordered">
    <section class="content-wrapper" role="main">
        <div class="content">
            <div class="content-bar">
                <ul class="breadcrumb breadcrumb-angle">
                    <li><a href="#" aria-label="home"><i class="fa fa-home"></i></a></li>
                    <li class="active">订单管理</li>
                    <li class="active">查看订单</li>
                </ul>
            </div><!-- /.content-bar -->

            <div class="col-md-12">
                <div class="panel" data-fill-color="true">
                    <div class="panel-heading">
                        <h3 class="panel-title">订单信息</h3>
                    </div>
                    <div class="panel-body">
                        <!--正文内容 开始-->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">
                                3Q订单id
                            </label>
                            <div class="col-md-5">
                                <input type="text"  class="form-control validate[required]" value="${order.soho3qOrderId!''}" disabled="disabled" />
                                </br>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">
                                客户名称
                            </label>
                            <div class="col-md-5">
                                <input type="text" class="form-control validate[required]" value="${order.customerName!''}" disabled="disabled"/>
                                </br>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">
                                客户手机号
                            </label>
                            <div class="col-md-5">
                                <input type="text" class="form-control validate[required]" value="${order.customerMobile!''}" disabled="disabled"/>
                                </br>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">
                                客户支付宝账号
                            </label>
                            <div class="col-md-5">
                                <input type="text" class="form-control validate[required]" value="${order.customerAlipay!''}" disabled="disabled"/>
                                </br>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">
                                项目名称
                            </label>
                            <div class="col-md-5">
                                <input type="text" class="form-control validate[required]" value="${order.porjectName!''}" disabled="disabled"/>
                                </br>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">
                                入驻时间
                            </label>
                            <div class="col-md-5">
                                <input type="text" class="form-control validate[required]" value="${order.checkinDate?date}" disabled="disabled"/>
                                </br>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">
                                离场时间
                            </label>
                            <div class="col-md-5">
                                <input type="text" class="form-control validate[required]" value="${order.checkoutDate?date}" disabled="disabled"/>
                                </br>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">
                                租赁时长
                            </label>
                            <div class="col-md-5">
                                <#if order.periodMonth != 0>
                                    <input type="text" class="form-control validate[required]" value='${order.periodMonth!""}月${order.periodWeek!""}周' disabled="disabled"/>
                                <#else>
                                    <input type="text" class="form-control validate[required]" value='${order.periodWeek!""}周' disabled="disabled"/>
                                </#if>
                                </br>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">
                                租金金额
                            </label>
                            <div class="col-md-5">
                                <input type="text" class="form-control validate[required]" value="${order.leaseAmount/100!''}" disabled="disabled"/>
                                </br>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">
                                押金金额
                            </label>
                            <div class="col-md-5">
                                <input type="text" class="form-control validate[required]" value="${order.depositAmount/100!''}" disabled="disabled"/>
                                </br>
                            </div>
                        </div>
                        <br/>
                        <div class="table-responsive">

                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th>产品名称</th>
                                    <th>产品数量</th>
                                    <th>产品总价</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#list orderItemList as d >
                                    <tr class="odd" role="row">
                                        <td>
                                            ${d.productSubType!''}人
                                            <#if d.productType =="ROOM">
                                                办公室
                                            <#else>
                                                办公桌
                                            </#if>
                                        </td>
                                        <td>${d.bookNum!''}</td>
                                        <td>${d.totalPrice/100!''}</td>
                                    </tr>
                                    </#list>
                                </tbody>
                            </table>
                        </div>
                        <div class="col-md-offset-3 col-md-9">
                            <button class="btn" type="reset" onclick="javascript:history.go(-1)">
                                返回
                            </button>
                        </div>
                    </div><!-- /.content-body -->
                </div>
            </div>

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
