var to0=0;



var isStandardBrowser = !!(window.applicationCache);

$(function(){
	var url=window.location.href;
	var s=url.split("/");
	for(var i in s)
	{
		if(s[i]=="en")
		{
			$("body").addClass("en");
		}
	}//添加ENclass


	
	
	$(window).resize(function(){
		
		var rheight=$(".main").width(),//获取当前内容区宽度
		 sth=1898,//设计稿画布宽度
		 winh=$(window).width();
		 
		 $(".main > .warp").css({"min-height":$(window).height()-160<500?500:$(window).height()-160});
		 
		 //容器高度自适应
		 $(".soho3Q-act ul").height(Math.floor((rheight/sth)*670));
		 $(".soho3Q-map .nr-map").height(Math.floor((rheight/sth)*761));
		 $(".act-top li .img").height(Math.floor((rheight/sth)*700));
		 $(".act-bot ul li .img").height(Math.floor((rheight/sth)*339));
		 $(".m-act-list ul").height(Math.floor((rheight/sth)*492));
		 $(".soho3Q .top-banner").height(Math.floor((rheight/sth)*763));
		 $(".officebox dd li").height(Math.floor((rheight/sth)*310));
		 $(".new-in-b3 ul").height(Math.floor((rheight/sth)*700));
		 
		 $(".q3-box .list-ul .list-li .img .pic").height(Math.floor((rheight/sth)*460));
		 //
		 
		 
		
		//图像等比例缩放
		var ent=setInterval(function(){
			$(".auto-img img").one('load', function() {
			mainBgResize(this, 1, 1);
			}).each(function() {
				if(this.complete) $(this).load();
			});
			clearInterval(ent)
		},50)
		//
		 
		 //3Q活动a边框
		$(".soho3Q-act li .box a").attr({style:""});
		$(".soho3Q-act li ").each(function(i) {
			$(this).find("a").width($(this).width()-6)
			$(this).find("a").height($(this).height()-6)
		});
		
		$(".m-act-list li .box a").attr({style:""});
		$(".m-act-list li ").each(function(i) {
			$(this).find("a").width($(this).width()-6)
			$(this).find("a").height($(this).height()-6)
		});
		//
	})
	$(window).resize();
	
	
//--更多筛选	
$(document).on("click",".down-select dt a",function(e){
	$(".down-select").toggleClass("open");
	e.preventDefault();
	e.stopPropagation();
})
//筛选事件；
$(document).on("click",".down-select dd .list .nr span",function(e){
	e.preventDefault();
	e.stopPropagation();

	var $this = $(this);

	if(!$this.hasClass('on')) {
		$(this).addClass("on").siblings().removeClass("on");
	} else {
		$(this).removeClass("on")
	}
	
	
})

var isSupportTouch = !!('ontouchend' in document) || navigator.userAgent.indexOf('Windows Phone') > 0;
//搜索滚动
if($(".banner-search").is(":visible"))
{

	$(document).on("mouseenter",".search-fiexd .warp .tab_t",function(){
		$(this).addClass("hover")
	})
	$(document).on("mouseleave",".search-fiexd .warp .tab_t",function(){
		$(this).removeClass("hover")
	})
	$(document).on("click",".search-fiexd .warp .tab_t span",function(){
		$(this).parents(".tab_t").removeClass("hover")
	})
	
	to0=$(".banner-search").offset().top
	
	$(window).scroll(function(){
		var sto=$(window).scrollTop();
		if(!isSupportTouch) {
			if(sto>to0)
			{
				$(".banner-search").addClass("search-fiexd");
			}
			else
			{
				$(".banner-search").removeClass("search-fiexd");
			}
		}
	})
}


//---周选择
$(document).on("click",".data-selt01 dt",function(e){
	e.preventDefault();
	e.stopPropagation();
	$(this).parents(".data-selt01").find("dd").show();
})


$(".data-selt01 .other-chidl .b input").click(function(e){
	e.preventDefault();
	e.stopPropagation();
})


$(document).on("click",".data-selt01 .child-box a",function(){
	$(this).addClass("on").siblings().removeClass("on");
	if(!$(this).parents(".data-selt01").find("dd div").hasClass("child-box cf zhangbing")){
		$(this).parents(".data-selt01").find("dt span").text($(this).text());
	}
	$(this).parents(".data-selt01").next(".select-hidden").val($(this).attr("data-val"));
	$(".data-selt01 dt").removeClass("open");
	
	if(!$(this).parents(".data-selt01").find("dd div").hasClass("child-box cf zhangbing")){
		$(".data-selt01 dd").hide();
	}	
	var value = $(this).text().replace(/[^0-9]/ig,""); 
	//getalldate(value*7)
})


$(document).on("click",".data-selt01 .zhangbing a",function(){
	$(this).addClass("on").siblings().removeClass("on");
	if(!$(this).parents(".data-selt01").find("dd div").hasClass("zhangbing")){
		$(this).parents(".data-selt01").find("dt span").text($(this).text());
	}
	$(this).parents(".data-selt01").next(".select-hidden").val($(this).attr("data-val"));
	$(".data-selt01 dt").removeClass("open");
	
	if(!$(this).parents(".data-selt01").find("dd div").hasClass("zhangbing")){
		$(".data-selt01 dd").hide();
	}
	
	if($(this).parents(".data-selt01").find("dd div").hasClass("zhangbing")){
		$(this).parents(".data-selt01").find("dd div[class='zhangbing'] a").css("background","#fff");
		$(this).parents(".data-selt01").find("dd div[class='zhangbing'] a").css("color","#ff9e2c");
		$(this).css("background","#ff9e2c");
		$(this).css("color","#fff");
	}
	
	var value = $(this).text().replace(/[^0-9]/ig,""); 
	//getalldate(value*7)
})

$(document).on("click",".data-selt01 .zhangbing1 a",function(){
	$(this).addClass("on").siblings().removeClass("on");
	if(!$(this).parents(".data-selt01").find("dd div").hasClass("zhangbing")){
		$(this).parents(".data-selt01").find("dt span").text($(this).text());
	}
	$(this).parents(".data-selt01").next(".select-hidden").val($(this).attr("data-val"));
	$(".data-selt01 dt").removeClass("open");
	
	if(!$(this).parents(".data-selt01").find("dd div").hasClass("zhangbing")){
		$(".data-selt01 dd").hide();
	}
	
	if($(this).parents(".data-selt01").find("dd div").hasClass("zhangbing1")){
		$(this).parents(".data-selt01").find("dd div[class='zhangbing1'] a").css("background","#fff");
		$(this).parents(".data-selt01").find("dd div[class='zhangbing1'] a").css("color","#ff9e2c");
		$(this).css("background","#ff9e2c");
		$(this).css("color","#fff");
	}
	
	var value = $(this).text().replace(/[^0-9]/ig,""); 
	//getalldate(value*7)
})


$(document).on("click",".data-selt01 .sub",function(){
	$(this).parents(".data-selt01").find("dt span").text($(this).parents(".data-selt01").find(".b input").val()+"周");
	$(this).parents(".data-selt01").next(".select-hidden").val($(this).parents(".data-selt01").find(".b input").val());
	$(".data-selt01 dt").removeClass("open");
	$(".data-selt01 dd").hide();
	getalldate($(this).parents(".data-selt01").find(".b input").val()*7)
})

function getNextDay(d,num){
	d = new Date(d);
	d = +d + 1000*60*60*24*num;
	d = new Date(d);
	//return d;
	//格式化
	return d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();  
}
function getalldate(num){
	var val=$(".ul2 .data-down .select-data").val()
	var aft=getNextDay(val,num)
	$(".banner-search .warp .ul1 li.i4 .p").html(val+"至"+aft)	
}

var select_box_flag = false;
			
//--------------模拟下拉
$(document).on("click",".select-box dt",function(e){
	/*e.preventDefault();
	e.stopPropagation();*/
	$(".select-box dt").removeClass("open");
	$(".select-box dd").hide();	
	$(this).addClass("open");
	$(this).parents(".select-box").find("dd").show();

	return false;
})
$(document).on("click",".select-box dd a",function(e){
	$(this).addClass("on").siblings().removeClass("on");
	$(this).parents(".select-box").find("dt b").text($(this).text());
	$(this).parents(".select-box").next(".select-hidden").val($(this).attr("data-val"));

	$(".select-box dt").removeClass("open");
	$(".select-box dd").hide();

	return false;
})

$(document).on('mouseenter', '.select-box, .data-selt01, .down-select', function() {
	select_box_flag = false;
}).on('mouseleave', '.select-box, .data-selt01, .down-select', function() {
	select_box_flag = true;
});

$(document).click(function(){
	if(select_box_flag) {
		$(".select-box dt").removeClass("open");
		$(".select-box dd").hide();
		$(".data-selt01 dt").removeClass("open");
		$(".data-selt01 dd").hide()
		$(".down-select").removeClass("open");
	}
})
//end
/** 这段不要  update by wanghuai@misquest.com  2015-01-23 beging
$(document).on("click",".banner-search .warp .tab_b:eq(0) .ul1 li.i5 a,.sblet .sub-bottom li.i6 .sub-t",function(e){
	var tcbox=""
	$("body").append('<div class="img-5tb"><div class="box"><a href="javascript:;" class="close"></a><dl><dt></dt><dd><h2>您已预约成功</h2><p>如有问题请咨询<em>400-815-9888</em></p></dd></dl></div></div>')
	var lt=setInterval(function(){
			$(".img-5tb").remove();
			clearInterval(lt);
		},5000)
})*/
/**
$(document).on("click",".img-5tb",function(){
	$(".img-5tb").remove();
})
*/
//这段不要  update by wanghuai@misquest.com  2015-01-23 ending
//
//领取优惠活动
/**这段不要  update by zhangtao
$(document).on("click",".new-in-b2 .lab ul li:first-child + li a,.member-bak1 .user-r2 .xbox dd a",function(e){
	var tcbox=""
	$("body").append('<div class="tpis-5tb"><div class="box"><a href="javascript:;" class="close"></a><dl><dt>恭喜你，已经成功领取红包！</dt><dd><h2>红包代码(复制下面代码使用优惠券,注意不要有多余空格)：</h2><p>201477585200</p><h2>您所领取的红包在"用户中心"一直都可以查到：</h2><p>我领取的优惠券</p> <a href="member/index.html">查看</a></dd></dl></div></div>')
	var lt=setInterval(function(){
			$(".img-5tb").remove();
			clearInterval(lt);
		},5000)
		
		
		$(".tpis-5tb").css("background","none");
})

*/
$(document).on("click",".tpis-5tb",function(){
	$(".tpis-5tb").remove();
})

//带有label.clew的标签的文本区域，会自动隐藏提示字符
    var inputs = 'textarea, input:text, input:password',
        notKeyCode = '16,17,18,9,27,45,46,112,113,114,115,116,117,118,119,120,121,122,123,33,34,35,36,37,38,39,40,',
        focus_flag = false;

    $(inputs).focus(function () {
    	focus_flag = true;
        $(this).siblings('label.clew').hide();
        if($(this).parent().hasClass('error')) {
            $(this).parent().removeClass('error').end().next().html('');
        }
    });
    
    $(inputs).blur(function () {
    	var $this = $(this);
    	focus_flag = false;

    	if ($(this).val().length == 0 && (typeof $this.attr('placeholder') == 'undefined' || !isStandardBrowser)) {
	        $(this).siblings('label.clew').show();
	    }
    });

    $(inputs).keydown(function (e) {
        var kc = e.keyCode;
        var $siblings = $(this).siblings('input:text, input:password, textarea');

        if (notKeyCode.indexOf(kc + ',') == -1 && !isStandardBrowser || (isStandardBrowser && typeof $siblings.attr('placeholder') == 'undefined')) {
            $(this).siblings('label.clew').hide();
            return;
        }
    });

    $(inputs).keyup(function () {
    	var $siblings = $(this).siblings('input:text, input:password, textarea');

        if ($(this).val().length != 0 && !isStandardBrowser) {
            $(this).siblings('label.clew').hide();
        }
    });

    $(inputs).on('change drop dropend input propertychange', function () {
    	var $siblings = $(this).siblings('input:text, input:password, textarea');

    	/*if(!isStandardBrowser || (isStandardBrowser && typeof $siblings.attr('placeholder') == 'undefined')) {
    		if ($(this).val().length != 0 && !focus_flag) {
	            $(this).siblings('label.clew').hide();
	        } else if(!focus_flag) {
	        	$(this).siblings('label.clew').show();
	        }
    	}*/
        
    });

    $('label.clew').click(function () {
        $(this).hide();
        $(this).siblings('input:text, input:password, textarea').focus();
    });

	$('label.clew').each(function(i) {
		var $siblings = $(this).siblings('input:text, input:password, textarea');

		if(!isStandardBrowser || (isStandardBrowser && typeof $siblings.attr('placeholder') == 'undefined')) {
			if ($siblings.val().length != 0 && !focus_flag) {
	            $(this).hide();
	        } else  {
	        	$(this).show();
	        }
		}
        
    });
	
//end



//公用选项卡切换
$(document).on("click",".tab_list .tab_a",function(){
	$(this).addClass("on").siblings().removeClass("on");
	var ii=$(this).index();
	$(this).parents(".tab_list").find(".tab_b").eq(ii).show().siblings().hide();
	$(this).parents(".tab_list").find(".ht .tab_b").eq(ii).show().siblings().hide();
	$(this).parents(".tab_list").find(".picvid .tab_b").eq(ii).show().siblings().hide();
})
//
$(".tab_list").each(function(i) {
    $(this).find(".tab_a:eq(0)").click();
});


//分享
	//分享
	$(".share .weibo").click(function(){//新浪微博
		var url_boolean = typeof $(this).parents('.share').data('url') != 'undefined';
		var url= !!(url_boolean) ? $(this).parents('.share').data('url') : window.location.href 
		//alert(encodeURIComponent(url))
		var img=""
		var title=$(this).data("tit")
		var op="http://service.weibo.com/share/share.php?url="+ encodeURIComponent(url) +"&title="+ encodeURIComponent(title) +""
		window.open(op)
	})
	
	$(".share .tqweibo").click(function(){//腾讯微博
		var url_boolean = typeof $(this).parents('.share').data('url') != 'undefined';
		var url= !!(url_boolean) ? $(this).parents('.share').data('url') : window.location.href;
		var img=""
		var title=$(this).data("tit")
		var op="http://share.v.t.qq.com/index.php?c=share&a=index&title="+ encodeURIComponent(title) +"&url="+ encodeURIComponent(url) +""
		window.open(op)
	})
	$(".share .qq").click(function(){//QQ
		var url_boolean = typeof $(this).parents('.share').data('url') != 'undefined';
		var url= !!(url_boolean) ? $(this).parents('.share').data('url') : window.location.href;
		var img=""
		var title=$(this).data("tit")
		var op="http://connect.qq.com/widget/shareqq/index.html?url="+ encodeURIComponent(url) +"&title="+ encodeURIComponent(title) +"&desc=&summary=&site=hongru"
		window.open(op)
	})
	$(".share .baidu").click(function(){//baidu
		var url_boolean = typeof $(this).parents('.share').data('url') != 'undefined';
		var url= !!(url_boolean) ? $(this).parents('.share').data('url') : window.location.href;
		var img=""
		var title=$(this).data("tit")
		var op="http://cang.baidu.com/do/add?iu="+ encodeURIComponent(url) +"&it="+ encodeURIComponent(title) +"&linkid=hz0zk7x8wzm"
		window.open(op)
		
	})

	
	$(".share .weixina").click(function(){//微信
		var url=$(this).data("img")
		var title=""
		title+="<div class='weixin'>"
		title+="<i class='c'>x</i>"
		title+="<h2>分享到微信朋友圈</h2>"
		title+="<div class='img'><img src='"+ url +"' width='100px;' height='100px;'></div>"
		title+="<p>打开微信，点击底部的“发现”，使用 “扫一扫” 即可将网页分享到我的朋友圈。</p>"
		title+="</div>"
		$("body").remove(".weixin");
		$("body").append(title);
		//var op="http://service.weibo.com/share/share.php?url='"+ url +"'&title='"+ title +"'&searchPic=false"
		//window.open(op)
	})
	
	$(document).on("click",".weixin .c",function(){
		//alert(0)
		$(".weixin").remove();
	})
//


//导航展开

//end
})
//end





//无图图像
var nullimg='../images/error.jpg';
function lod(t){
	t.onerror = null;
	t.src=nullimg
}
$(document).ready(function(){
	$("img").each(function(){
	if($(this).attr("src")=="")
	{
		$(this).attr({"src":nullimg})
	}
	})
})
//


var browser_w, browser_h;
function mainBgResize(img, w_r, h_r) {
	
	var $img = $(img);
	if(img.complete || img.width) {
		imgResize();
	} else {
		$img.load(function() {
			imgResize();
		});
	}
	

	function imgResize() {
		//browser_w = $(".main").width() < 768 ? 768 * w_r : $(".main").width() * w_r;
		//browser_h = $(".main").height() < 650 ? 650 * h_r : $(".main").height() * h_r;
		browser_w = $img.parents(".auto-img").width() * w_r;
		browser_h = $img.parents(".auto-img").height() * h_r;
		
		if($img.width() < browser_w || $img.width() > browser_w) {
				$img.css({width: browser_w, height: 'auto'});
				if($img.height() < browser_h) {

					$img.css({width: 'auto', height: browser_h});
				}
			} else if($img.height() < browser_h || $img.height() > browser_h) {
				$img.css({width: 'auto', height: browser_h});
				if($img.width() < browser_w) {
					$img.css({width: browser_w, height: 'auto'});
				}
			}

			$img.css({marginTop: -($img.height() - browser_h) / 2, marginLeft: -($img.width() - browser_w) / 2,'visibility':'visible',opacity:0}).animate({opacity:1},400);
	}
}






$(function() {

	banner('new-in-b5 .contents', 3000);

    function banner(name, speed){
        var banner_name=$("."+name);
        $(banner_name).each(function() {
            var $this = $(this);
            var timer=null;
            var intervalCount=0;
            var banner_size=$this.find(".panel").size();

            for(var createSpan=0; createSpan<banner_size; createSpan++){
                $this.find(".control").append('<span class="icon-sequence"></span>');
            }
            $this.find(".control span:eq(0)").removeClass("active").addClass("active").siblings().removeClass("active");
            $this.find(".panel").eq(0).css("zIndex",2).show().siblings().css("zIndex",1).hide();
            $this.find(".control span").bind("click",function(){
                if(!$this.find(".panel").is(":animated")){
                    var nIndex = $(this).index();
                    filp(nIndex);
                }
            });

            $this.find(".prev").bind("click", function() {
                if(!$this.find(".panel").is(":animated")){
                    var nIndex=$this.find("li:visible").index();
                    nIndex = nIndex == 0 ? banner_size-1 : --nIndex;
                    filp(nIndex);
                }
            });

            $this.find(".next").bind("click", function() {
                if(!$this.find(".panel").is(":animated")){
                    var nIndex=$this.find("li:visible").index();
                    nIndex = nIndex == banner_size-1 ? 0 : ++nIndex;
                    filp(nIndex);
                }
            });

            function move(nIndex){
                if(!$this.find(".panel").is(":animated")){
                    nIndex = nIndex == banner_size-1 ? 0 : ++nIndex;
                    filp(nIndex);
                }
            }

            function filp(nindex) {
                $this.find(".panel").eq(nindex).css({position: 'relative'}).fadeIn().siblings().css({position: 'absolute'}).fadeOut();
                $this.find(".panel").eq(nindex).css("zIndex",2).siblings().css("zIndex",1);
                $this.find(".control span:eq("+nindex+")").removeClass("active").addClass("active").siblings().removeClass("active");

                intervalCount=nindex;

            }

            $("."+name).bind("mouseover",function(){
                clearInterval(timer);

            });
            $("."+name).bind("mouseout",function(){
                clearInterval(timer);
                timer=setInterval(function(){
                    move(intervalCount);
                }, speed);
            });

            timer = setInterval(function(){
                move(intervalCount);
            }, speed);
        });
    }

});












// 设置为主页
function SetHome(obj,vrl){
try{
obj.style.behavior='url(#default#homepage)';obj.setHomePage(vrl);
}
catch(e){
if(window.netscape) {
try {
netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
}
catch (e) {
alert("此操作被浏览器拒绝！\n请在浏览器地址栏输入“about:config”并回车\n然后将 [signed.applets.codebase_principal_support]的值设置为'true',双击即可。");
}
var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch);
prefs.setCharPref('browser.startup.homepage',vrl);
}else{
alert("您的浏览器不支持，请按照下面步骤操作：1.打开浏览器设置。2.点击设置网页。3.输入："+vrl+"点击确定。");
}
}
}
// 加入收藏 兼容360和IE6
function shoucang(sTitle,sURL)
{
try
{
window.external.addFavorite(sURL, sTitle);
}
catch (e)
{
try
{
window.sidebar.addPanel(sTitle, sURL, "");
}
catch (e)
{
alert("加入收藏失败，请使用Ctrl+D进行添加");
}
}
}






$(document).ready(function() {
	var ssb_flag = true;
	$(document).on('click', '.simulation-select-box .curr', function() {
		var $this = $(this),
			$parent = $this.parent('.simulation-select-box'),
			data_index = $parent.data('index'),
			offset = $parent.offset();

		if(!$parent.hasClass('selected')) {
			$parent.addClass('selected');
			$('.simulation-select-options[data-index=' + data_index + ']').css({top: offset.top + $parent.height(), left: offset.left, width: $parent.width()}).show();
		} else {
			$parent.removeClass('selected');
			$('.simulation-select-options[data-index=' + data_index + ']').removeAttr('style');
		}
	});


	$('.simulation-select-box').each(function() {
		var $this = $(this),
			data_index = $this.data('index');

		$this.width($('.simulation-select-options[data-index=' + data_index + ']').width() + 17);

	});

	$(document).on('mouseenter', '.simulation-select-box, .simulation-select-options', function() {
		ssb_flag = false;
	}).on('mouseleave', '.simulation-select-box, .simulation-select-options', function() {
		ssb_flag = true;
	}).on('click', '.simulation-select-options .option', function(){
		var $this = $(this),
			$parent = $this.parent().parent(),
			data_index = $parent.data('index');
		
		$('.simulation-select-box[data-index=' + data_index + '] .curr').find('.t').html($.trim($this.find('a').html()));

		$('.simulation-select-box[data-index=' + data_index + ']').trigger('mouseleave');
		$(document).trigger('click');
	}).on('click', '.simulation-select-options', function(){
		var $this = $(this),
			data_index = $this.data('index');

		$('.simulation-select-box[data-index!=' + data_index + ']').removeClass('selected');
		$this.siblings('.simulation-select-options[data-index!=' + data_index + ']').removeAttr('style');
	});



	$(document).click(function() {
		if(ssb_flag) {

			$('.simulation-select-box.selected').removeClass('selected');
			$('.simulation-select-options').removeAttr('style');
		}
	});


	$('.foi-area').each(function() {
		var $foi_area = $(this),
			$control_option = $foi_area.find('.control .option'),
			$panels = $foi_area.find('.panels');

		$control_option.click(function() {
			var $this = $(this),
				ti = $this.index();

			if($this.find('.simulation-input').hasClass('radio')) {
				if(!$this.hasClass('selected')) {
					$this.addClass('selected').siblings('.selected').removeClass('selected');
					$('.foi-area').each(function() {
						$(this).find('.panels .panel').eq(ti).show().siblings(':visible').hide();
					})
					
				}
			}

			if($this.find('.simulation-input').hasClass('checkbox')) {
				if(!$this.hasClass('selected')) {
					$this.addClass('selected');
					$foi_area.find('.content').show();
				} else {
					$this.removeClass('selected');
					$foi_area.find('.content').hide();
				}
				return false;
			}

		});
		
	});


	$(document).on('click', '.radio-box', function() {
		var $this = $(this);

		if(!$this.hasClass('selected')) {
			$this.addClass('selected').parent().siblings().children('.radio-box.selected').removeClass('selected');
			$this.parent().siblings('.invoice').html($this.data('invoice'));
		}
	});

	$('.foi-area:eq(1) .row:eq(0) .inputs-wrap .radio-box.selected').parent().siblings('.invoice').html($('.foi-area:eq(1) .row:eq(0) .inputs-wrap .radio-box.selected').data('invoice'));

	$(document).on('click', '.simulation-input.checkbox:not(.disabled-public)', function() {
		var $this = $(this);

		if(!$this.parent().hasClass('checkbox-box')) {
			if(!$this.hasClass('selected')) {
				$this.addClass('selected');
			} else {
				$this.removeClass('selected');
			}
		}
	});

	$(document).on('click', '.foi-info .discount-boxes .title .icon', function() {
		var $this = $(this)
			$parent = $this.parent();

		if(!$parent.hasClass('selected')) {
			$parent.addClass('selected').next().show().siblings('.foi-voucher-box:visible').hide().end().end().siblings('.selected').removeClass('selected');
		} else {
			$parent.removeClass('selected').next().hide();
		}
	});

	// 支付遇到问题 链接点击事件
	$('.pay-tcbox .a1').click(function() {
		$('.pay-tcbox').hide();
	});

	if(isStandardBrowser) {
		var login_input = '.login-xg input:text, .login-xg input:password';

		$(login_input).each(function() {
			var $this = $(this);

			if($this.next('.clew').length) {
				$this.attr('placeholder', $this.next('.clew').html()).next('.clew').remove();
			}
		});
		
	}
	

});