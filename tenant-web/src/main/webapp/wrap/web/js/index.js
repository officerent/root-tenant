// JavaScript Document
$(function(){
	
	
	$(window).resize(function(){
		
		var rheight=$(".main").width(),//��ȡ��ǰ���������
		 sth=1898,//��Ƹ廭�����
		 winh=$(window).width();
		 
		 //�����߶�����Ӧ
		 $(".in-bak-2 ul").height(Math.floor((rheight/sth)*875));
		 $(".in-banner .banner-warp ul").height(Math.floor((rheight/sth)*700));
		 $(".in-banner").height(Math.floor((rheight/sth)*700));
		 $(".int .main,.int .footer").css({top:Math.floor((rheight/sth)*700)});
		
		 $(".banner-search").css({top:Math.floor((rheight/sth)*700)-45})
		 $(".new-in-b1 .lab ul li").height(Math.floor(($(".new-in-b1 .lab ul").width()/1420)*355))
		 $(".new-in-b2 .lab ul li .img").height(Math.floor(($(".new-in-b2 .lab ul").width()/1420)*355))
		 
		 //
		 to0=$(".banner-search").offset().top;//��������top
		 
		 //3Q�a�߿�
		$(".in-bak-2 li a").attr({style:""});
		$(".in-bak-2 li").each(function(i) {
			$(this).find("a").width($(this).width()-6)
			$(this).find("a").height($(this).height()-6)
		});
		//
	})
	$(window).resize();
	xxxban($(".banner-warp ul"),$(".banner-warp .hide"),$(".banner-warp .left"),$(".banner-warp .right"),"F1", 3);
	xxxban($(".new-in-b3 ul"),$(".new-in-b3 .hide"),$(".new-in-b3 .left"),$(".new-in-b3 .right"),"F1");
	
	
	$(window).scroll(function(){
		var to=$(window).scrollTop();
		if(to<50)
		{
			$(".int .in-banner").css({"top":50-to});
		}
		else
		{
			$(".int .in-banner").css({"top":0});
		}
	})
	
	
})

