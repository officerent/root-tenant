<#include "../../common/_layout.ftl" />

<@layoutHead title="文章列表">

</@layoutHead>
<@layoutBody>
<form id="form1" method="post">
    <section class="content-wrapper" role="main">
        <div class="content">
            <div class="content-bar">
                <ul class="breadcrumb breadcrumb-angle">
                    <li><a href="#" aria-label="home"><i class="fa fa-home"></i></a></li>
                    <li class="active">文章管理</li>
                    <li class="active">文章列表</li>
                </ul>
            </div><!-- /.content-bar -->


            <div class="content-body">
                <!--正文内容 开始-->
                <div class="col-md-12">
                    <h3>文章列表</h3>
                    <div class="row">
                        <div class="col-md-12">
                            <a class="btn btn-success mb-1x mr-1x" href="${path}/admin/article/add_article.html">添加文章</a>
                        </div>
                    </div>
                    <div class="table-responsive">

                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>文章标题</th>
                                <th>文章类别</th>
                                <th>状态</th>
                                <th>创建时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                                <#list data as d >
                                <tr class="odd" role="row">
                                    <td><a href="${path}/article/article_detail.html?articleId=${d.articleId}">${d.title!''}</a></td>                                   
                                    <td>
                                        <#if d.type=='SOHO3Q'>
                                            	3Q动态
                                        </#if>
                                        <#if d.type=='PROJ'>
                                            	项目介绍
                                        </#if>
                                        <#if d.type=='OFFICE'>
                                            	共享办公
                                        </#if>
                                    </td>
                                    <td>
                                        <#if d.isDeleted==false>
                                            	正常
                                        </#if>
                                        <#if d.isDeleted==true>
                                            	已删除
                                        </#if>
                                    </td>
                                    <td>${d.createTime?date}</td>                                  
                                    <td>
                                        <a class="btn btn-info mb-1x mr-1x" href="${path}/admin/article/modify_article.html?articleId=${d.articleId}">修改</a>
                                         <#if d.isDeleted==false>
                                            	<a class="btn btn-info mb-1x mr-1x" href="${path}/admin/article/delete_article.html?articleId=${d.articleId}">删除</a>
                                        </#if>
                                        <#if d.isDeleted==true>
                                            	<a class="btn btn-info mb-1x mr-1x" href="${path}/admin/article/reopen_article.html?articleId=${d.articleId}">上线</a>
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
