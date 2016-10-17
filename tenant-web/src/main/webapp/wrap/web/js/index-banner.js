//×Ô¶¯ÇÐ»»
var xxxban = function(obj1, obj4, left, right, mode, auto) {
    var list,
        imgul = obj1,
        bonul = obj4,
        numx = 0,
        maxnum = bonul.find("span").size() - 1,
        speed = auto,
        iil = 0,
        untl = "left",
        animt = true,
        randomTextArray = [],
        tempRandomTextArray = randomTextArray,
        isHome = $('body').hasClass('int') && imgul.parent().parent().hasClass('in-banner'),
        rtaLen = tempRandomTextArray.length;


	imgul.find("li:gt(0)").css({
        opacity: 0,
        zIndex: 0
	});

	if(isHome) {
		imgul.find("li:not(.safety-area)").each(function() {
			var $this = $(this),
				this_text = $.trim($this.find('i').html());

			if(this_text.length > 0) {
				randomTextArray.push(this_text);
			}
		});

		/*imgul.find("li").each(function() {
			var $this = $(this),
				this_text = $.trim($this.find('i').html());

			if(this_text.length > 0) {
				$this.find('i').html(getRandomText());

				return false;
			}
		});*/

		/*left.find('dl').each(function(e) {
			var $this = $(this),
				isSafety = false,
				old_flag = isSafety;

			for(var i = 0; i < randomTextArray.length; i++) {

				if(randomTextArray[i] != $.trim($this.find('i').html() ) ) {
					isSafety = true;
				} else {
					isSafety = false;
					break;
				}
			}
			if(isSafety) {
				$this.addClass('safety-area');
			}
			

		});

		right.find('dl').each(function(e) {
			var $this = $(this),
				isSafety = false,
				old_flag = isSafety;

			for(var i = 0; i < randomTextArray.length; i++) {

				if(randomTextArray[i] != $.trim($this.find('i').html() ) ) {
					isSafety = true;
				} else {
					isSafety = false;
					break;
				}
			}
			if(isSafety) {
				$this.addClass('safety-area');
			}
			
		});*/

		imgul.find("li:not(.safety-area)").each(function(e) {
			var $this = $(this),
				isSafety = false,
				old_flag = isSafety;

			for(var i = 0; i < randomTextArray.length; i++) {

				if(randomTextArray[i] != $.trim($this.find('i').html() ) ) {
					isSafety = true;
				} else {
					isSafety = false;
					break;
				}
			}
			if(isSafety) {
				$this.addClass('safety-area');
			}
			
		});



		tempRandomTextArray = randomTextArray;

		rtaLen = tempRandomTextArray.length;


		for(var j = rtaLen - 1; j >= 0; j--) {

			var _random = parseInt(Math.random() * (j - 0 + 1) + 0);

			left.find('dl:not(.safety-area)').eq(j).find('i').html(tempRandomTextArray[_random]);
			right.find('dl:not(.safety-area)').eq(j).find('i').html(tempRandomTextArray[_random]);
			imgul.find("li:not(.safety-area)").eq(j).find('i').html(tempRandomTextArray[_random]);
			if(_random == 0) {
				tempRandomTextArray.splice(_random, 1);
			} else {
				tempRandomTextArray.splice(_random, _random);
			}
		}
	}
	left.mouseenter(function(){
		clearInterval(interv);
		
		var xx=numx-1
			xx<0?xx=maxnum:0;
			$(this).find(".blist dl").eq(xx).show().siblings().hide()
		$(this).find(".blist").css({left:-376}).stop().animate({left:50},600);
	})
	right.mouseenter(function(){
		clearInterval(interv)
		
		var xx=numx+1
			xx>maxnum?xx=0:0;

			$(this).find(".blist dl").eq(xx).show().siblings().hide()
			$(this).find(".blist").css({left:50}).stop().animate({left:-326},600);
			
	})
	left.mouseleave(function(){
		interv=setInterval(function(){
		xx();
		},speed*1000);
		
		$(this).find(".blist").stop().animate({left:-376},500);
		
	})
	right.mouseleave(function(){
		interv=setInterval(function(){
		xx();
		},speed*1000)
		
		$(this).find(".blist").stop().animate({left:50},500);
		
	})
	

    bonul.find("span").eq(0).addClass("on");
    bonul.find("span").each(function(e) {
        $(this).unbind('click');
        $(this).click(function(x) {
            if (animt) {
                animt = false;
                x.preventDefault();
                x.stopPropagation();
                if (e == numx) {
                    return false;
                } else {
                    iil++;
                    bonul.find("span").removeClass("on");
                    bonul.find("span").eq(e).addClass("on");
                    var xl = 0,
                        xl2 = 0;
                    if (iil % 2) {
                        xl = -10;
                    } else {
                        xl = 10;
                    }
                    switch (untl) {
                        case "left":
                            xl2 = "100%";
                            break;
                        case "right":
                            xl2 = "-100%";
                            break;
                    }
                    switch (mode) {
                        case "F1":
                            imgul.find("li").eq(numx).css({
                                zIndex: 1,
                                left: 0
                            }).stop().animate({
                                opacity: 0,
                                left: 0
                            }, 1000);
                            imgul.find("li").eq(e).css({
                                zIndex: 10,
                                opacity: 0,
                                left: xl
                            }).stop().animate({
                                opacity: 1,
                                marginLeft: 0,
                                left: 0
                            }, 1000, function() {
                                animt = true;
                            });
                            break;
                        case "F2":
                            imgul.find("li").eq(numx).css({
                                zIndex: 1,
                                left: 0
                            }).stop().animate({
                                opacity: 1,
                                left: xl2
                            }, 1000);
                            var lt = xl2 == "100%" ? "-100%" : "100%";
                            imgul.find("li").eq(e).css({
                                zIndex: 10,
                                opacity: 1,
                                left: lt
                            }).stop().animate({
                                opacity: 1,
                                marginLeft: 0,
                                left: 0
                            }, 1000, function() {
                                animt = true;
                            });
                            break;
                    }
                    numx = e
                }
            }

            return false;
        });
    });
    
    if(!!(speed)) {
        var interv = setInterval(function() {
            xx();
        }, speed * 1000);
    }
    

    function xx() {
        if (auto) {
            var xx = numx + 1
            xx > maxnum ? xx = 0 : 0;
            bonul.find("span").eq(xx).click()
        }
    }

    left.mouseenter(function() {
        clearInterval(interv);
        var xx = numx - 1
        xx < 0 ? xx = maxnum : 0;
        $(this).find(".blist dl").eq(xx).show().siblings().hide()
        $(this).find(".blist").css({
            left: -376
        }).stop().animate({
            left: 50
        }, 600);
    });

    right.mouseenter(function() {
        clearInterval(interv)
        var xx = numx + 1
        xx > maxnum ? xx = 0 : 0;
        $(this).find(".blist dl").eq(xx).show().siblings().hide()
        $(this).find(".blist").css({
            left: 50
        }).stop().animate({
            left: -326
        }, 600);
    });

    left.mouseleave(function() {
        if(!!(speed)) {
            interv = setInterval(function() {
                xx();
            }, speed * 1000);
        }
        $(this).find(".blist").stop().animate({
            left: -376
        }, 500);
    });

    right.mouseleave(function() {
        if(!!(speed)) {
            interv = setInterval(function() {
                xx();
            }, speed * 1000);
        }
        $(this).find(".blist").stop().animate({
            left: 50
        }, 500);
    });

    left.click(function() {
        if (animt) {
            untl = "left";
            var xx = numx - 1
            xx < 0 ? xx = maxnum : 0;
            bonul.find("span").eq(xx).click()
            $(this).find(".blist").stop().animate({
                left: -376
            }, 500);
        }
    });

    right.click(function() {
        if (animt) {
            untl = "right";
            var xx = numx + 1
            xx > maxnum ? xx = 0 : 0;
            bonul.find("span").eq(xx).click()
            $(this).find(".blist").stop().animate({
                left: 50
            }, 500);
        }
    });



    function getRandomText() {
    	var _random = parseInt(Math.random() * (2 - 0 + 1) + 0);

    	if(!!(randomTextArray[_random])) {
    		return randomTextArray[_random];
    	} else {
    		return false;
    	}
    	
    }
}