<#include "../../common/_layout.ftl" />

<@layoutHead title="编辑文章">

<!-- END COMPONENTS -->
</@layoutHead>
<@layoutBody>
    <form id="form1" method="post" action="${path}/admin/article/update_article.html" role="form" class="form-horizontal form-bordered">
        <section class="content-wrapper" role="main">
            <div class="content">
                <div class="content-bar">
                    <ul class="breadcrumb breadcrumb-angle">
                        <li><a href="#" aria-label="home"><i class="fa fa-home"></i></a></li>
                        <li class="active">文章管理</li>
                        <li class="active">编辑文章</li>
                    </ul>
                </div><!-- /.content-bar -->

				<!--商品名称-->
                <label class="control-label" for="title">文章标题</label>
                <div class="input-group input-group-in">
                    <span class="input-group-addon"><i class="icon-paper-plane"></i></span>
                    <input class="form-control" name="title" id="title" value="${data.title}">
                    <span class="form-control-feedback"></span>
                </div>
                
                <!--关键字-->
                <label class="control-label" for="keywords">关键字</label>
                <div class="input-group input-group-in">
                    <span class="input-group-addon"><i class="icon-paper-plane"></i></span>
                    <input class="form-control" name="keywords" id="keywords"
                           value="${data.keywords}">
                    <span class="form-control-feedback"></span>
                </div>
                
                 <!--文章描述-->
                <label class="control-label" for="description">文章概要</label>
                <div class="input-group input-group-in">
                    <span class="input-group-addon"><i class="icon-paper-plane"></i></span>
                    <input class="form-control" name="description" id="description"
                           value="${data.description}">
                    <span class="form-control-feedback"></span>
                </div>
                
               <label class="control-label" for="type">文章类型</label>
               <label class="select select-o">
                   <select id="type" name="type">
                       <option value="">- 选择类型 -</option>
                       <option value="SOHO3Q" <#if data.type=='SOHO3Q'> selected="selected" </#if>>3Q动态</option>
                       <option value="PROJ" <#if data.type=='PROJ'> selected="selected" </#if>>项目介绍</option>
                       <option value="OFFICE" <#if data.type=='OFFICE'> selected="selected" </#if>>共享办公</option>
                       <option value="OTHER" <#if data.type=='OTHER'> selected="selected" </#if>>其他</option>
                   </select>
               </label>
                                    
				<label for="commodity-detail-description-summernote">文章正文</label>
                <div id="summernote-panel" class="panel" data-fill-color="true">
                    <div class="panel-body">
                        <div id="commodity-detail-description-summernote"
                             style="font-family:'Open Sans'"></div>
                    </div><!-- /.panel-body -->
                </div><!-- /.panel -->                
                <input id="content" name="content" hidden></input>
                <input id="articleId" name="articleId" hidden></input>
                
                <div class="col-md-offset-3 col-md-9">
                   <button class="btn btn-info" onclick="saveArticle()" style="left: 35%;">
                       	提交
                   </button>
                   <button class="btn" type="reset" onclick="javascript:history.go(-1)">
                      	 返回
                   </button>
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
	<!-- COMPONENTS -->
	<script src="${path}/wrap/scripts/epiceditor.js"></script>
	<script src="${path}/wrap/scripts/summernote.js"></script>
	<script>
    $(window).load(function () {
		//alert(${data.title});
        //富文本编辑器
        var $summernote = $("#commodity-detail-description-summernote");
        $summernote.summernote({
            height: 300,
            toolbar: [
                ['style', ['style']],
                ['style', ['bold', 'italic', 'underline', 'strikethrough', 'superscript', 'subscript', 'clear']],
                ['fontname', ['fontname']],
                ['fontsize', ['fontsize']],
                ['color', ['color']],
                ['para', ['ul', 'ol', 'paragraph']],
                ['height', ['height']],
                ['table', ['table']],
                ['insert', ['link', 'picture', 'video', 'hr', 'readmore']],
                ['genixcms', ['elfinder']],
                ['view', ['fullscreen', 'codeview']],
                ['help', ['help']]
            ]
          });
		//alert('${data.content}');
        $summernote.code('${data.content}');
        //$("#title").val('xxxxxxxxxxxxxx');
        });
    
       window.saveArticle = function () {
                var content = $("#commodity-detail-description-summernote").code();
                $("#content").val(content);
                $("#articleId").val(${data.articleId});
                $("#form1").submit();
            

        };
</script>
</@layoutFooter>



