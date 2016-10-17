<#macro layoutHead title="SOHO3Q返利网" meta_keywords="SOHO3Q返利网,SOHO3Q,SOHO中国,租办公室,联合办公,共享办公,租金返利"
   meta_description="SOHO3Q返利网是帮助用户快捷高效地下单购买SOHO3Q办公室，开放工位，会议室等灵活办公空间产品的平台，通过SOHO3Q返利网下单，用户还能获得一定比例的租金返利。">
<#include "base.ftl">
<!DOCTYPE html>
<html class="no-js">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="Keywords" content="${meta_keywords!""}">
    <meta name="Description" content="${meta_description!""}">
    <title>${title!""}</title>
	<link rel="shortcut icon" type="image/x-icon" href="${path}/soho/img/title.ico" media="screen" />

    <link rel="stylesheet" href="${path}/wrap/styles/bootstrap.css">

    <link rel="stylesheet" href="${path}/wrap/styles/dependencies.css">

    <link rel="stylesheet" href="${path}/wrap/styles/wrapkit.css">

    <link rel="stylesheet" href="${path}/wrap/styles/demo.css">
    <!-- datetimepicker css -->
    <link rel="stylesheet" href="${path}/wrap/styles/bootstrap-datetimepicker.min.css" />
    <!-- 表单验证css样式 -->
    <link rel="stylesheet" href="${path}/wrap/validationengine/css/validationEngine.jquery.css">
    <link rel="stylesheet" href="${path}/wrap/styles/jquery.gritter.css">
    <link rel="stylesheet" href="${path}/wrap/styles/pages.css">

    <#--自定义样式-->
    <link rel="stylesheet" href="${path}/wrap/styles/defined.css">

    <script src="${path}/wrap/web/js/jquery.js"></script>
    <script src="${path}/wrap/web/js/cookie.js"></script>
    <!-- 百度统计 -->
    <script>
		var _hmt = _hmt || [];
		(function() {
		  var hm = document.createElement("script");
		  hm.src = "//hm.baidu.com/hm.js?37e25ae551c86e2205de1ad83026cc0e";
		  var s = document.getElementsByTagName("script")[0]; 
		  s.parentNode.insertBefore(hm, s);
		})();
	</script>
	
	<!-- 百度自动推送 -->
	<script>
	(function(){
	    var bp = document.createElement('script');
	    var curProtocol = window.location.protocol.split(':')[0];
	    if (curProtocol === 'https') {
	        bp.src = 'https://zz.bdstatic.com/linksubmit/push.js';        
	    }
	    else {
	        bp.src = 'http://push.zhanzhang.baidu.com/push.js';
	    }
	    var s = document.getElementsByTagName("script")[0];
	    s.parentNode.insertBefore(bp, s);
	})();
	</script>
	<meta name="baidu-site-verification" content="5gYmMrJhHv" />
    <#nested>
</head>
</#macro>

<#--layout主体部分-->
<#macro layoutBody mainClass="" classBody="">
<body class="${classBody!""}">
    <!--[if lt IE 9]>
    <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
    <![endif]-->
    <main class="wrapkit-wrapper ${mainClass!""}" id="wrapper">

    <#include "header.ftl">

    <!-- 左边菜单栏 -->
    <#include "menu.ftl">


    <#nested>
</#macro>
<#--layout主体部分 end -->

<#--layout底部-->
<#macro layoutFooter>

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
<!--         <script>
            (function(b,o,i,l,e,r){b.GoogleAnalyticsObject=l;b[l]||(b[l]=
                    function(){(b[l].q=b[l].q||[]).push(arguments)});b[l].l=+new Date;
                e=o.createElement(i);r=o.getElementsByTagName(i)[0];
                e.src='//www.google-analytics.com/analytics.js';
                r.parentNode.insertBefore(e,r)}(window,document,'script','ga'));
            ga('create','UA-48454066-1');ga('send','pageview');
        </script> -->

        <!-- pageSubmit -->
        <script src="${path}/wrap/scripts/submitSelect.js"></script>

        <script src="${path}/wrap/scripts/date-time/bootstrap-datepicker.min.js"></script>
        <script src="${path}/wrap/scripts/date-time/bootstrap-timepicker.min.js"></script>
        <script src="${path}/wrap/scripts/date-time/moment.min.js"></script>
        <script src="${path}/wrap/scripts/date-time/daterangepicker.min.js"></script>

        <script src="${path}/wrap/scripts/date-time/bootstrap-datetimepicker.js"></script>
        <script src="${path}/wrap/scripts/date-time/locales/bootstrap-datetimepicker.fr.js"></script>

        <script type="text/javascript">
            $('.form_datetime').datetimepicker({
                format: 'yyyy-mm-dd hh:ii',
                autoclose: 1,
                todayBtn:  1
            });
            $('.form_date').datepicker({
                format: 'yyyy-mm-dd',
                autoclose: 1,
                todayBtn:  1
            });
            function validatepicker(obj){
                obj.focus();
                obj.blur();
            };

        </script>

        <script
                src="${path}/wrap/validationengine/js/jquery.validationEngine.js"></script>
        <script
                src="${path}/wrap/validationengine/js/languages/jquery.validationEngine-zh.js"></script>
        <script type="text/javascript">
            $(function(){
                $('#form1').validationEngine({scroll:false,focusFirstField:false});
            });
        </script>

        <#--日期格式化-->
        <script src="${path}/wrap/web/js/dateFormat.js"></script>

        <script src="${path}/wrap/web/js/jquery.dateFormat.js"></script>
        <script src="${path}/wrap/scripts/jquery.gritter.min.js"></script>

        <#--公共js方法-->
        <script src="${path}/wrap/common/js/common.js"></script>
        <script type="text/javascript" src="${path}/wrap/template/template.js"></script>

        <#nested>
    </body>
</html>

</#macro>
<#--layout底部 end-->