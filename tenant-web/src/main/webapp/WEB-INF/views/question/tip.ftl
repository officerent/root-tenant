<#include "../web_common/_layout.ftl" />

<@layoutHead>
    <style>
        .wrapkit-wrapper-extend {
            min-height: 600px;
        }
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
                <ul class="timeline">
                    <li class="timeline-line"></li><!-- /.timeline-line -->

                    <li class="timeline-item">
                        <div class="timeline-badge">
                            <a class="border-orange" rel="tooltip" data-container="body" href="#" title="" data-original-title="1"></a>
                        </div>
                        <div class="timeline-panel panel fade in panel-default panel-fill" data-fill-color="true" data-init-panel="true">
                            <div class="panel-body">
                                <p>
                                    ${manager!""}经理已经为您下完订单，如果之前未在soho3q官方网站进行注册的用户，请先进行注册，然后进入手机版soho3q官方网站http://www.soho3q.com->我的订单中->众包订单进行确认->进行支付,或者通过soho3q平台给您发送的短信中的链接进入.
                                </p>
                            </div><!-- /.panel-body -->
                        </div><!-- /.timeline-panel -->
                    </li><!-- /.timeline-item -->
                    <li class="timeline-item">
                        <div class="timeline-badge">
                            <a class="border-orange" rel="tooltip" data-container="body" href="#" title="" data-original-title="2"></a>
                        </div>
                        <div class="timeline-panel panel fade in panel-default panel-fill" data-fill-color="true" data-init-panel="true">
                            <div class="panel-body">
                                <p>
                                    您支付完成后，我们网站会在两个小时以内进行订单确认进行返利.
                                </p>
                            </div><!-- /.panel-body -->
                        </div><!-- /.timeline-panel -->
                    </li><!-- /.timeline-item -->
                    <li class="timeline-line"></li><!-- /.timeline-line -->

                    <li class="timeline-item">
                        <div class="timeline-badge">
                            <a class="border-orange" rel="tooltip" data-container="body" href="#" title="" data-original-title="3"></a>
                        </div>
                        <div class="timeline-panel panel fade in panel-default panel-fill" data-fill-color="true" data-init-panel="true">
                            <div class="panel-body">
                                <p>
                                    返给您的金钱会在48小时内到达您预留的支付宝账户内.
                                </p>
                            </div><!-- /.panel-body -->
                        </div><!-- /.timeline-panel -->
                    </li><!-- /.timeline-item -->
                </ul>
                <div class="row">
                    <div class="col-md-5"></div>
                    <div class="col-md-4">
                        <div class="btn-divider mr-2x mb-1x" role="divider">
                            <a href="${path}/question/question_list.html" type="button" class="btn btn-lg btn-default">看看问题</a>
                            <span class="label-divider">or</span>
                            <a href="${path}/${url!""}" type="button" class="btn btn-lg btn-success">再来一单</a>
                        </div>
                    </div>
                    <div class="col-md-3"></div>
                </div>

            </div><!-- /.content-body -->
        </div><!-- /.content -->
    </section><!-- /MAIN -->



    <!-- ============================================
    FOOTER SECTION
    =============================================== -->
    <footer class="footer-wrapper footer-default" role="contentinfo" data-init-footer="true">
        <div class="footer">
            <div class="pull-right text-muted"><small>Currently v1.0</small></div>
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