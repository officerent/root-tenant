package com.root.tenant.controller.ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.root.tenant.controller.RouteKey;
import com.root.tenant.service.UserService;
import com.alibaba.fastjson.JSON;



@Controller
@RequestMapping(RouteKey.PREFIX_AJAX)
public class VerificationCodeController {
    @Autowired
    UserService userService;
    
    
    static Logger logger = LoggerFactory.getLogger(VerificationCodeController.class);
	
    //获取验证码图片
    @ResponseBody
	@RequestMapping(value = RouteKey.GET_VERIFICATION_CODE, method = RequestMethod.GET)
	public void getVerificationImage(HttpServletRequest request,HttpServletResponse response) {
		InputStream fis = null;
	    //response.setContentType("image/jpg");
	    try {		    
	        OutputStream out = response.getOutputStream();
	        BufferedImage bufferedImage=userService.getVerificationImage(request);
	        //将图片输出到浏览器
	        //将内存的图片通过浏览器输出流输出成jpg图片
	        ImageIO.write(bufferedImage, "jpg", response.getOutputStream());	        
	      } catch (Exception e) {
	    	logger.error("fail to read image",e);
			//response.sendError(404, "image doesn't exist");
	    } 
	}
    
}
