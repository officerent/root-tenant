<#include "./web_common/_layout.ftl" />

<@layoutHead>
    <style>
        .wrapkit-wrapper-extend {
            min-height: 600px;
        }

        .font-color {
            color:#FF9011;
        }
        .font-size {
            font-size:150%;
        }
    </style>
    <script src="${path}/soho/js/main.js"></script>
    <link rel="stylesheet" href="${path}/soho/css/reset.css">
	<link rel="stylesheet" href="${path}/soho/css/style.css"> 
    <#assign index='active' />
</@layoutHead>
<@layoutBody mainClass="wrapkit-wrapper-extend" classBody="wrapkit-sidebar-left wrapkit-sidebar-lg bg-grd-dark wrapkit-sidebar-horizontal" >


    <main class="wrapkit-wrapper wrapkit-wrapper-extend" id="wrapper" data-init-layout="true">

    <!-- ============================================
    MAIN CONTENT SECTION
    =============================================== -->


    <section class="content-wrapper wrapkit-wrapper-extend" role="main" data-init-content="true">
        <div class="content">
            <div class="content-body" >
                <!-- <div class="row" > -->
                <section class="cd-horizontal-timeline"  style="height:50px;margin-top: 0px;">
                    <div class="timeline" style="width:100%;max-width:1500px;padding:0px;height:60px">
                        <div class="events-wrapper" >
                            <div class="events" >
                                <ol style="font-size:24px">
                                    <li><a href="#0" data-date="16/01/2014" class="selected">注册登陆</a></li>
                                    <li><a href="#0" data-date="28/02/2014">选择产品</a></li>
                                    <li><a href="#0" data-date="20/04/2014">提交订单</a></li>
                                    <li><a href="#0" data-date="20/05/2014">确认订单</a></li>
                                    <li><a href="#0" data-date="09/07/2014">完成支付</a></li>
                                    <li><a href="#0" data-date="30/08/2014">返利到帐</a></li>
                                </ol>

                                <span class="filling-line" aria-hidden="true"></span>
                            </div> <!-- .events -->
                        </div> <!-- .events-wrapper -->

                        <ul class="cd-timeline-navigation">
                            <li><a href="#0" class="prev inactive">Prev</a></li>
                            <li><a href="#0" class="next">Next</a></li>
                        </ul> <!-- .cd-timeline-navigation -->
                    </div> <!-- .timeline -->

                    <div class="events-content"  style="margin:0px">
                        <ol>
                            <li class="selected" data-date="16/01/2014">
                                <p>
                                    注册成为soho3q返利网用户。
                                </p>
                            </li>

                            <li data-date="28/02/2014">
                                <p>
                                    可以根据自身需要，选择soho3q的固定长租或者灵活短租的办公空间产品。
                                </p>
                            </li>

                            <li data-date="20/04/2014">
                                <p>
                                    向本站提交购买意向，我们会分派专门的官方注册经纪人即时代您向soho3q下单，并且登记此订单的返利信息。
                                </p>
                            </li>

                            <li data-date="20/05/2014">
                                <p>
                                    用下单手机号登陆soho3q官方app或者手机版官网m.soho3q.com，确认刚才提交的订单。
                                </p>
                            </li>

                            <li data-date="09/07/2014">
                                <p>
                                    在soho3q官网完成订单支付；全官网完成操作，100%安全可靠。
                                </p>
                            </li>

                            <li data-date="30/08/2014">
                                <p>
                                    48小时返利自动转到您的支付宝帐户，返利状态全程可查。
                                </p>
                            </li>

                        </ol>
                    </div> <!-- .events-content -->
                </section>

                    <hr>
              <!--   </div> -->

                <div class="row">
                    <div class="col-md-3">
                        <h3>返利排行榜</h3>
                        <table class="table table-condensed">
                            <thead>
                                <tr>
                                    <th>手机号</th>
                                    <th>返利</th>
                                    <th>日期</th>
                                </tr>
                            </thead>
                            <tbody id="dataList">
                                <#--<#if data ??>-->
                                    <#--<#list data as d>-->
                                <#--<tr>-->
                                    <#--<td>${d.customerMobile !""}</td>-->
                                    <#--<td>${d.rebatesAmount !""}元</td>-->
                                    <#--<td>${d.lastUpdateTime !""}</td>-->
                                <#--</tr>-->
                                    <#--</#list>-->
                                <#--</#if>-->
                            </tbody>
                        </table>
                    </div>
                    <div class="col-md-6">
                        <div id="myCarousel" class="carousel slide">
                            <!-- 轮播（Carousel）指标 -->
                            <ol class="carousel-indicators">
                                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                                <li data-target="#myCarousel" data-slide-to="1"></li>
                                <li data-target="#myCarousel" data-slide-to="2"></li>
                            </ol>
                            <!-- 轮播（Carousel）项目 -->
                            <div class="carousel-inner">
                                <div class="item active">
                                    <img src="${path}/wrap/images/rebate/1.jpg" width="950" alt="First slide">
                                </div>
                                <div class="item">
                                    <img src="${path}/wrap/images/rebate/2.jpg" width="950" alt="Second slide">
                                </div>
                                <div class="item">
                                    <img src="${path}/wrap/images/rebate/3.jpg" width="950" alt="Third slide">
                                </div>
                            </div>
                            <!-- 轮播（Carousel）导航 -->
                            <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                                <span class="sr-only">Previous</span>
                            </a>
                            <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                                <span class="sr-only">Next</span>
                            </a>
                            <#--<a class="carousel-control left" href="#myCarousel"-->
                               <#--data-slide="prev">&lsaquo;</a>-->
                            <#--<a class="carousel-control right" href="#myCarousel"-->
                               <#--data-slide="next">&rsaquo;</a>-->
                        </div>

                    </div>
                    <div class="col-md-3">
                        <div class="row">
                            <h2>固定长租 <strong class="font-color">返利<span><i id="station_order_radio" class="font-size"></i></span></strong></h2>
                            <p>一周起租,选择心仪的3Q中心,直接拎包入驻吧!</p>
                            <a class="btn btn-soho-danger" href="${path}/sales/station_order.html" role="button">去购买，拿返利</a>
                        </div>
                        <div class="row">
                            
                            <hr>
                           
                        </div>
                        <div class="row">
                            <h2>工位短租 <strong class="font-color">返利<span><i id="coupon_order_radio1" class="font-size"></i></span></strong></h2>
                            <p>按天使用工位,任意3Q中心,随到随用哦!</p>
                            <a class="btn btn-soho-danger" href="${path}/sales/coupon_order.html?source=OPEN_STATION" role="button">去购买，拿返利</a>
                        </div>
                        <div class="row">
                           
                            <hr>
                            
                        </div>
                        <div class="row">
                            <h2>会议室短租 <strong class="font-color">返利<span><i id="coupon_order_radio2" class="font-size"></i></span></strong></h2>
                            <p>按小时使用会议室,任意3Q中心,随到随用哦!</p>
                            <a class="btn btn-soho-danger" href="${path}/sales/coupon_order.html?source=ROOM" role="button">去购买，拿返利</a>
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
</@layoutBody>

<@layoutFooter>
<script>
    var date = new Date;
    $("#currentYear").append(date.getFullYear());
</script>

<!--轮播图-->
<script>
    $('.carousel').carousel({
        interval: 3000
    });

    window.onload=function(){
        $.ajax({
            url:"${path}/ajax/info/rebates_ratio",
            type:"get",
            dataType:'json',
            success:function(data){
                if(data.errCode==0){
                   var ratio = data.data.ratio;
                    $("#station_order_radio").append(ratio);
                    $("#coupon_order_radio1").append(ratio);
                    $("#coupon_order_radio2").append(ratio);
                }else{
                    alertMessage(data.errCode);
                }
            },
            error:function (xhr, type, exception) {
                alert(type, "Failed");
            }
        });
        $.ajax({
            url:"${path}/ajax/bonus/most_recent_list",
            type:"get",
            dataType:'json',
            success:function(data){
                if(data.errCode==0){
                    var str = "";
                    if(data.data!=null){
                        var list = data.data;
                        for(var i=0; i<list.length;i++){
                            str +='<tr>'+
                                    '<td>'+ list[i].customerMobile +'</td>'+
                                    '<td>'+ list[i].rebatesAmount +'元</td>'+
                                    '<td>'+ list[i].lastUpdateTime +'</td>'+
                                    '</tr>';
                        }
                    }
                    $("#dataList").append(str);
                }
            },
            error:function (xhr, type, exception) {
                alert(type, "Failed");
            }
        });
    }
</script>
</@layoutFooter>
