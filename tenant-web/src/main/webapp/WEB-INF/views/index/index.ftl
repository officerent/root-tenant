<!DOCTYPE html>
<html class="no-js">
<#include "../web_common/base.ftl">
<!-- ============================================
SIDEBAR SECTION
=============================================== -->
<head>


    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="Keywords" content="SOHO 3Q,work,SOHO 中国,3Q,轻松办公,办公室,返利">
    <meta name="Description" content="3Q返利">
    <title>${title!""}</title>


    <link rel="stylesheet" href="${path}/wrap/styles/bootstrap.css">

    <link rel="stylesheet" href="${path}/wrap/styles/dependencies.css">

    <link rel="stylesheet" href="${path}/wrap/styles/wrapkit.css">

    <link rel="stylesheet" href="${path}/wrap/styles/demo.css">


</head>

<body class="wrapkit-sidebar-left wrapkit-sidebar-lg bg-grd-dark wrapkit-sidebar-horizontal">
<!--[if lt IE 9]>
<p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
<![endif]-->


<main class="wrapkit-wrapper" id="wrapper">

    <nav class="header navbar">
        <div class="container-fluid">
            <!-- use .pull-*, couse we need this float on any screen size -->
            <div class="pull-left">
                <a class="navbar-brand" href="#" role="logo" aria-label="Logo">
                    <span class='logo' alt="Brand">快乐的工作</span>
                </a>
                <button data-sidebar="toggleVisible" class="btn btn-icon navbar-btn">
                    <i class="fa fa-bars"></i>
                    <i class="fa fa-caret-down"></i>
                </button>
            </div>

        </div><!-- /.container-fluid -->
    </nav><!-- /navigation -->
    <aside class="sidebar sidebar-orange" role="complementary" data-init-sidebar="true">
        <!-- /navigation -->
        <div class="nav-wrapper">
            <ul class="nav nav-left nav-tabs nav-contrast-teal" role="navigation">
                <li class="nav-item" role="presentation">
                    <a href="#">首页</a>
                </li>
            </ul>
            <div class="nav-ctrl btn-group hide"><a class="btn hover-teal btn-icon btn-xs"><span class="icon-arrow-left"></span></a><a class="btn hover-teal btn-icon btn-xs"><span class="icon-arrow-right"></span></a></div><div class="nav-fake-padding bg-dark hide"></div></div>
        <div class="sidebar-resize-handler hide"></div>
    </aside>
    <main class="wrapkit-wrapper wrapkit-wrapper-extend" id="wrapper" data-init-layout="true">

        <!-- ============================================
        MAIN CONTENT SECTION
        =============================================== -->
        <section class="content-wrapper wrapkit-wrapper-extend" role="main" data-init-content="true">
            <div class="content">

                <div class="content-body">
                    <div class="row">
                        <div class="col-md-3">
                            <h1>工作态度</h1>
                            <ol>
                                <li class="bg-danger"><h4>积极乐观</h4></li>
                                <li class="bg-danger"><h4>努力勤奋</h4></li>
                                <li class="bg-danger"><h4>永不放弃</h4></li>
                            </ol>
                        </div>
                        <div class="col-md-6">
                            <div id="myCarousel" class="carousel slide">
                                <!-- 轮播（Carousel）指标 -->
                                <ol class="carousel-indicators">
                                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                                    <li data-target="#myCarousel" data-slide-to="1"></li>
                                    <li data-target="#myCarousel" data-slide-to="2"></li>
                                    <li data-target="#myCarousel" data-slide-to="3"></li>
                                </ol>
                                <!-- 轮播（Carousel）项目 -->
                                <div class="carousel-inner">
                                    <div class="item active">
                                        <img src="${path}/wrap/images/dummy/desk1.jpg" alt="1">
                                    </div>
                                    <div class="item">
                                        <img src="${path}/wrap/images/dummy/desk2.jpg" alt="2">
                                    </div>
                                    <div class="item">
                                        <img src="${path}/wrap/images/dummy/desk3.jpg" alt="3">
                                    </div>
                                    <div class="item">
                                        <img src="${path}/wrap/images/dummy/desk4.jpg" alt="4">
                                    </div>
                                </div>
                                <!-- 轮播（Carousel）导航 -->
                                <a class="carousel-control left" href="#myCarousel"
                                   data-slide="prev">&lsaquo;</a>
                                <a class="carousel-control right" href="#myCarousel"
                                   data-slide="next">&rsaquo;</a>
                            </div>

                        </div>
                        <div class="col-md-3">
                            <h1>喜爱图书</h1>
                            <p>除了工作以外的工具书,还需要进行其他生活类,哲学类的图书</p>
                            <h1>工作准则</h1>
                            <p>追求最美好的生活,才能开心工作</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">

                        </div>
                        <div class="col-md-6">

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
                <div class="pull-right text-muted"><small>Currently v1.0</small></div>
                <div>&copy;<em id="currentYear"></em>  XL</div>
            </div>
        </footer><!-- /.FOOTER -->

    </main>

</main><!-- /#MAIN -->


<!-- VENDORS : jQuery & Bootstrap -->
<script src="${path}/wrap/scripts/vendor.js"></script>
<!-- END VENDORS -->

<!-- DEPENDENCIES : Required plugins -->
<script src="${path}/wrap/scripts/dependencies.js"></script>
<!-- END DEPENDENCIES -->

<!-- WRAPKIT -->
<script src="${path}/wrap/scripts/wrapkit.js"></script>
<!-- END WRAPKIT -->

<!-- WRAPKIT SETUPS -->
<script src="${path}/wrap/scripts/wrapkit-setup.js"></script>
<!-- end WRAPKIT SETUPS -->

<!-- PLUGIN SETUPS: vendors & dependencies setups -->
<script src="${path}/wrap/scripts/plugin-setups.js"></script>
<!-- END PLUGIN SETUPS -->


<!-- COMPONENTS -->
<script src="${path}/wrap/scripts/dataTables.bootstrap.js"></script>
<script src="${path}/wrap/scripts/jquery.validate.js"></script>
<script src="${path}/wrap/scripts/bootbox.js"></script>
<!-- END COMPONENTS -->

<!-- DUMMY: Use for demo -->
<!--
<script src="/wrap/scripts/demo/table-datatables-extras.js"></script>
<script src="/wrap/scripts/demo/table-datatables-demo.js"></script>
-->
<!-- Google Analytics: change UA-48454066-1 to be your site's ID. -->
<script>
    (function(b,o,i,l,e,r){b.GoogleAnalyticsObject=l;b[l]||(b[l]=
            function(){(b[l].q=b[l].q||[]).push(arguments)});b[l].l=+new Date;
        e=o.createElement(i);r=o.getElementsByTagName(i)[0];
        e.src='//www.google-analytics.com/analytics.js';
        r.parentNode.insertBefore(e,r)}(window,document,'script','ga'));
    ga('create','UA-48454066-1');ga('send','pageview');
</script>

<!-- pageSubmit -->
<script src="${path}/wrap/scripts/submitSelect.js"></script>

<script src="${path}/wrap/scripts/date-time/bootstrap-datepicker.min.js"></script>
<script src="${path}/wrap/scripts/date-time/bootstrap-timepicker.min.js"></script>
<script src="${path}/wrap/scripts/date-time/moment.min.js"></script>
<script src="${path}/wrap/scripts/date-time/daterangepicker.min.js"></script>

<script src="${path}/wrap/scripts/date-time/bootstrap-datetimepicker.js"></script>
<script src="${path}/wrap/scripts/date-time/locales/bootstrap-datetimepicker.fr.js"></script>




<#--公共js方法-->
<script src="${path}/wrap/common/js/common.js"></script>
<script type="text/javascript" src="${path}/wrap/template/template.js"></script>
<!--轮播图-->
<script>
    $('.carousel').carousel({
        interval: 3000
    });
</script>
</body>
</html>
