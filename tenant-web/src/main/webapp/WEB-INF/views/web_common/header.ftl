   <!-- ============================================
    HEADER SECTION
    =============================================== -->
    <!-- navigation -->
    <nav class="header navbar">
        <div class="container-fluid">
            <!-- use .pull-*, couse we need this float on any screen size -->
            <div class="pull-left">
                <a class="navbar-brand" href="${path}/index.html" role="logo" aria-label="Logo">
                    <span class='logo' alt="Brand">SOHO3Q</span>
                </a>
                <button data-sidebar="toggleVisible" class="btn btn-icon navbar-btn">
                    <i class="fa fa-bars"></i>
                    <i class="fa fa-caret-down"></i>
                </button>
            </div>

            <!-- use .pull-*, couse we need this float on any screen size -->
            <div class="pull-right" role="navigation">
                <span id = "loginStatus">
                    <a  class="btn btn-icon navbar-btn" href="${path}/user/login.html">登录</a>
                </span>
                <span id = "logoutStatus">
                    <a id="signOut" aria-label="Signout" class="btn btn-icon navbar-btn" href="${path}/user/logout.html"><i class="fa fa-sign-out"></i></a>
                </span>
            </div><!-- /navigation -->

        </div>
      </div><!-- /.container-fluid -->
    </nav><!-- /navigation -->

<script>
    var name = getCookie("user_name");
    if(name != ""){
        $("#loginStatus").empty();
        $("#loginStatus").append(name);
    }else{
        $("#logoutStatus").empty();
    }
    $("#signOut").click(function(){
        delCookie("user_name");
        delCookie("user_token");
    });
</script>