<#include "../../common/_layout.ftl" />

<@layoutHead title="回答用户问题">

</@layoutHead>
<@layoutBody>
    <form id="form1" method="post" action="${path}/admin/user/save_user.html" role="form" class="form-horizontal form-bordered">
        <section class="content-wrapper" role="main">
            <div class="content">
                <div class="content-bar">
                    <ul class="breadcrumb breadcrumb-angle">
                        <li><a href="#" aria-label="home"><i class="fa fa-home"></i></a></li>
                        <li class="active">用户管理</li>
                        <li class="active">添加用户</li>
                    </ul>
                </div><!-- /.content-bar -->

                <div class="col-md-12">
                    <div class="panel" data-fill-color="true">
                        <div class="panel-heading">
                            <h3 class="panel-title">用户基本信息</h3>
                        </div>
                        <div class="panel-body">
                            <!--正文内容 开始-->
                            <div class="form-group">
                                <label class="col-sm-3 control-label">
                                    用户昵称<span class="text-danger">*</span>
                                </label>
                                <div class="col-md-5">
                                    <input name="name" id="name" type="text"  class="form-control validate[required]"  />
                                    </br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">
                                    用户密码<span class="text-danger">*</span>
                                </label>
                                <div class="col-md-5">
                                    <input name="password"  type="password" class="form-control validate[required]"  />
                                    </br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">
                                    是否是管理员<span class="text-danger">*</span>
                                </label>
                                <div class="col-md-5">
                                    <input name="isNotAdmin" type="checkbox" class="js-switch" data-class-name="switchery switchery-alt" checked="checked" />
                                    </br>
                                </div>
                            </div>
                            <br/>
                            <div class="col-md-offset-3 col-md-9">
                                <button class="btn btn-info" type="submit" style="left: 35%;">
                                    提交
                                </button>
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



