<#include "../web_common/_layout.ftl" />

<@layoutHead title="3Q动态" meta_keywords="SOHO3Q,新闻动态"
   meta_description="SOHO3Q最新动态">
    <style>
        .btn-soho-danger {
            color: #ffffff;
            background-color: #FF9011;
            border-color: transparent;
        }
        .font-color {
            color:#FF9011;
        }
        .font-size {
            font-size:150%;
        }
        
    </style>
    <#assign soho3q_news='active' />
</@layoutHead>
<@layoutBody mainClass="wrapkit-wrapper-extend" classBody="wrapkit-sidebar-left wrapkit-sidebar-lg bg-grd-dark wrapkit-sidebar-horizontal" >

</@layoutBody>

    <main class="wrapkit-wrapper wrapkit-wrapper-extend" id="wrapper" data-init-layout="true">

    <!-- ============================================
    MAIN CONTENT SECTION
    =============================================== -->
  <form id="form1" method="post">
    <section class="content-wrapper wrapkit-wrapper-extend" role="main" data-init-content="true">
        <div class="content">

            <div class="content-body">
				<div class="panel-group mt-1x mb-4x" id="getStarted" role="tablist" aria-multiselectable="true">
                <!-- <div class="col-md-6"> -->
              <table class="table table-striped" style="font-size:24px">
                <thead style="font-size:24px">
                  <tr>
                    <th>标题</th>
                    <th>发布时间</th>
                  </tr>
                </thead>
                <tbody>
                <#if articleList??>
                      <#list articleList as article>
                          <tr>
                              <td><a href="${path}/article/article_detail.html?articleId=${article.articleId}" style="color:black">${article.title!""}</a></td>
                              <td>${article.createTime?date}</td>                              
                          </tr>
                      </#list>
                  </#if>
                  
                </tbody>
              </table>
            <!-- </div> -->
                
              </div>
			<#include "../web_common/page.ftl">
            </div><!-- /.content-body -->
        </div><!-- /.content -->
    </section><!-- /MAIN -->
<form>


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
</script>


</@layoutFooter>