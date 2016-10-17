<#include "../web_common/_layout.ftl" />

<@layoutHead title=data.title meta_keywords=data.keywords
   meta_description=data.description>
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
    <link rel="stylesheet" href="${path}/soho/css/article-index.css">
    <#assign index='active' />
</@layoutHead>
<@layoutBody mainClass="wrapkit-wrapper-extend" classBody="wrapkit-sidebar-left wrapkit-sidebar-lg bg-grd-dark wrapkit-sidebar-horizontal" >

</@layoutBody>

    <main class="wrapkit-wrapper wrapkit-wrapper-extend" id="wrapper" data-init-layout="true">

    <!-- ============================================
    MAIN CONTENT SECTION
    =============================================== -->
    <section class="content-wrapper wrapkit-wrapper-extend" role="main" data-init-content="true">
        <div class="content">
            <div class="content-body">
				<div class="panel-group mt-1x mb-4x" id="getStarted" role="tablist" aria-multiselectable="true">
					<div class="container">
						<div class="wrap-left pull-left">
							<div class="left-main">	
							<h2>${data.title}</h2>	
							<p><small>${data.createTime?date}</small></p>
							<hr>											
							${data.content}									 
							</div>
						</div>
					</div>
				</div>		
            </div><!-- /.content-body -->
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
</script>



</@layoutFooter>