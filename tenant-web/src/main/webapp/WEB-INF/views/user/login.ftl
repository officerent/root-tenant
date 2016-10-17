<#include "../common/base.ftl">
<!DOCTYPE html>
<html class="no-js">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="initial-scale=1,minimum-scale=1,maximum-scale=1,width=device-width,height=device-height,target-densitydpi=device-dpi,user-scalable=yes">

    <title>3Q返利登录</title>


    <!-- favicon.ico and apple-touch-icon.png -->
    <link rel="manifest" href="images/favicons/manifest.json">
    <meta name="msapplication-TileColor" content="#2d89ef">
    <meta name="msapplication-TileImage" content="images/favicons/mstile-144x144.png">
    <meta name="theme-color" content="#ffffff">

    <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,700,600,300" rel="stylesheet" type="text/css">

    <link rel="stylesheet" href="${path}/wrap/styles/bootstrap.css">

    <link rel="stylesheet" href="${path}/wrap/styles/dependencies.css">

    <link rel="stylesheet" href="${path}/wrap/styles/wrapkit.css">

    <link rel="stylesheet" href="${path}/wrap/styles/pages.css">

    <#--自定义样式-->
    <link rel="stylesheet" href="${path}/wrap/styles/defined.css">
</head>
<body class="bg-grd-white">
<!--[if lt IE 9]>
<p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
<![endif]-->

<main class="signin-wrapper">
    <div class="tab-content">
        <p class="text-center p-4x">
        </p>
        <div class="tab-pane fade in active" id="signin">
            <form id="signinForm" action="index.html" role="form">
                <p class="lead">登录你的账户</p>
                <div class="form-group">
                    <div class="input-group input-group-in">
                        <span class="input-group-addon"><i class="icon-user"></i></span>
                        <input name="username" id="username" class="form-control" placeholder="用户名">
                    </div>
                </div><!-- /.form-group -->
                <div class="form-group">
                    <div class="input-group input-group-in">
                        <span class="input-group-addon"><i class="icon-lock"></i></span>
                        <input type="password" name="passwd" id="passwd" class="form-control" placeholder="密码">
                    </div>
                </div><!-- /.form-group -->
                <div class="form-group clearfix">
                    <div class="animated-hue pull-right">
                        <a id="doSubmit"  type="submit" class="btn btn-soho-danger">登录</a>
                    </div>
                <#--<div class="nice-checkbox nice-checkbox-inline">-->
                <#--<input type="checkbox" name="keepSignin" id="keepSignin" checked="checked">-->
                <#--<label for="keepSignin">Keep me sign in</label>-->
                <#--</div>-->
                </div><!-- /.form-group -->

            <#--<hr>-->

            <#--<p><a href="#recoverAccount" data-toggle="modal">Can't Access your Account?</a></p>-->
            <#--<p class="lead">Signin with another account?</p>-->
            <#--<div class="signin-alt">-->
            <#--<a href="#" class="btn btn-sm btn-success"><i class="fa fa-facebook"></i></a>-->
            <#--<a href="#" class="btn btn-sm btn-info"><i class="fa fa-twitter"></i></a>-->
            <#--<a href="#" class="btn btn-sm btn-danger"><i class="fa fa-google-plus"></i></a>-->
            <#--<a href="#" class="btn btn-sm btn-default"><i class="fa fa-github"></i></a>-->
            <#--</div>-->

            <#--<hr>-->

                <p>还没账户? <a href="#signup" data-toggle="tab">注册一个</a></p>
            </form><!-- /#signinForm -->
        </div><!-- /.tab-pane -->

        <div class="tab-pane fade" id="signup">
            <form id="signupForm" action="index.html" role="form">
                <p class="lead">注册一个账户</p>
                <hr>
                <p class="text-muted"><strong>输入你的账户信息</strong></p>
                <div class="form-group has-feedback">
                    <div class="input-group input-group-in">
                        <span class="input-group-addon"><i class="icon-user"></i></span>
                        <input name="name" id="name" class="form-control" placeholder="用户名">
                        <span class="form-control-feedback"></span>
                    </div>
                </div><!-- /.form-group -->
                <div class="form-group has-feedback">
                    <div class="input-group input-group-in">
                        <span class="input-group-addon"><i class="icon-key"></i></span>
                        <input type="password" name="npassword" id="npassword" class="form-control" placeholder="密码">
                        <span class="form-control-feedback"></span>
                    </div>
                </div><!-- /.form-group -->
                <div class="form-group has-feedback">
                    <div class="input-group input-group-in">
                        <span class="input-group-addon"><i class="icon-check"></i></span>
                        <input type="password" name="cpassword" id="cpassword" class="form-control" placeholder="再次输入密码">
                        <span class="form-control-feedback"></span>
                    </div>
                </div><!-- /.form-group -->
                <div class="form-group animated-hue clearfix">
                    <div class="pull-right">
                        <a id="doRegister" class="btn btn-soho-danger">创建账户</a>
                    </div>
                    <div class="pull-left">
                        <a id="signinButton" href="#signin" class="btn btn-default" data-toggle="tab">登录</a>
                    </div>
                </div><!-- /.form-group -->
            </form><!-- /#signupForm -->
            <hr>
        </div><!-- /.tab-pane -->
    </div><!-- /.tab-content -->
</main><!--/#wrapper-->
<p class="signin-cr text-light">&copy;<em id="currentYear"></em>  XL</p>


<!-- Modal Recover -->
<div class="modal fade" id="recoverAccount" tabindex="-1" role="dialog" aria-labelledby="recoverAccountLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form id="recoverForm" action="index.html">
                <div class="modal-header">
                    <h4 class="modal-title" id="recoverAccountLabel">Reset Password</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <div class="input-group input-group-in">
                            <span class="input-group-addon"><i class="fa fa-envelope-o text-muted"></i></span>
                            <input type="email" name="recoverEmail" id="recoverEmail" class="form-control" placeholder="Enter your email address">
                        </div>
                    </div><!-- /.form-group -->
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Send reset link</button>
                </div>
            </form><!-- /#recoverForm -->
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /#recoverAccount -->


<!-- VENDORS : jQuery & Bootstrap -->
<script src="${path}/wrap/scripts/vendor.js"></script>
<!-- END VENDORS -->

<!-- DEPENDENCIES : Required plugins -->
<script src="${path}/wrap/scripts/dependencies.js"></script>
<!-- END DEPENDENCIES -->


<!-- PLUGIN SETUPS: vendors & dependencies setups -->
<script src="${path}/wrap/scripts/plugin-setups.js"></script>
<!-- END PLUGIN SETUPS -->


<!-- Google Analytics: change UA-48454066-1 to be your site's ID. -->
<script>
    (function(b,o,i,l,e,r){b.GoogleAnalyticsObject=l;b[l]||(b[l]=
            function(){(b[l].q=b[l].q||[]).push(arguments)});b[l].l=+new Date;
        e=o.createElement(i);r=o.getElementsByTagName(i)[0];
        e.src='//www.google-analytics.com/analytics.js';
        r.parentNode.insertBefore(e,r)}(window,document,'script','ga'));
    ga('create','UA-48454066-1');ga('send','pageview');
</script>
<script src="${path}/wrap/base64/base64.js"></script>
<script src="${path}/wrap/sha1/sha1.js"></script>
<script src="${path}/wrap/jqueryui/jqueryUI.js"></script>
<script>
    function setCookie (name, value){
        //设置名称为name,值为value的Cookie
        document.cookie = name+"="+value+";path=/";
        //即document.cookie= name+"="+value+";path=/";
        // 时间可以不要，但路径(path)必须要填写，因为JS的默认路径是当前页，如果不填，此cookie只在当前页面生效！~
    }

    function encodePass(value){
        var basePassword = Base64.encode(value);
        var encodeSha1 = sha1(basePassword);
        return encodeSha1;
    }
    $("#doSubmit").on("click",function(){
        var userName = $("#username").val();
        var password = $("#passwd").val();
        var encodePassword = encodePass(password);
        var formData = {
            userName:userName,
            password:encodePassword
        }
        $.ajax({
            url:"${path}/ajax/user/login",
            type:"post",
            dataType:'json',
            data:formData,
            success:function(data){
                var message = "";
                if(data.errCode == 0){
                    window.location.href="${path}/index.html";
                }else{
                    var key ={
                        "100":"请重新输入账号密码",
                        "101":"账户已被冻结",
                        "102":"请重新登录",
                        "113":"请重新输入账号密码",
                        "114":"请重新输入账号密码",
                        "104":"你的账号出现问题，请联系我们",
                        "unknow":"#"+data.errCode
                    }
                    if(key[data.errCode]) message=key[data.errCode];
                    else message=key.unknow;
                    alert(message);
                }

            },
            error:function (xhr, type, exception) {
                alert(type, "Failed");
            }
        });
    });
    $("#doRegister").on("click",function(){
        var userName = $("#name").val();
        var npassword = $("#npassword").val();
        var cpassword = $("#cpassword").val();
        if(npassword === cpassword){
            var encodePassword = encodePass(npassword);
            var formData = {
                userName:userName,
                password:encodePassword
            }
            $.ajax({
                url:"${path}/ajax/user/register",
                type:"post",
                dataType:'json',
                data:formData,
                success:function(data){
                    if(data.errCode==0){
                        $("#signinButton").click();
                    }else{
                        var key ={
                            "106":"请填写账号密码",
                            "112":"账户已存在",
                            "unknow":"#"+data.errCode
                        }
                        if(key[data.errCode]) message=key[data.errCode];
                        else message=key.unknow;
                        alert(message);
                    }
                },
                error:function (xhr, type, exception) {
                    alert(type, "Failed");
                }
            });
        }else{
            alert("两次密码不相同，请重新输入");
        }
    });

    var date = new Date;
    $("#currentYear").append(date.getFullYear());
</script>
</body>
</html>
