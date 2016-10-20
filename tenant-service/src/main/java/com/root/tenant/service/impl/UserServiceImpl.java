package com.root.tenant.service.impl;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.root.tenant.dal.dao.UserMapper;
import com.root.tenant.dal.dataobj.User;
import com.root.tenant.model.common.Constants;
import com.root.tenant.model.common.TenantException;
import com.root.tenant.service.UserService;


@Service
public class UserServiceImpl implements UserService{

	static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	static String content = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcedfghijklmnopqrstuvwxyz1234567890";

    @Autowired
    private UserMapper userMapper;
    
	@Override
	public void addUser(User user) throws TenantException {
		user.setStatus(Constants.USER_STATUS_NORMAL);
		Date now=new Date();
		user.setCreateTime(now);
		user.setUpdateTime(now);
		userMapper.insert(user);
		logger.info("new user was inserted:"+JSON.toJSONString(user));
		
	}

	@Override
	public BufferedImage getVerificationImage(HttpServletRequest request) {
		int width = 100;
        int height = 30;
         
        //创建一张内存中的缓存图片
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
         
        ///背景色
        //通过graphics绘制图像
        Graphics graphics = bufferedImage.getGraphics();
        //设置颜色
        graphics.setColor(Color.lightGray);
        //填充
        graphics.fillRect(0, 0, width, height);
         
        ///画边框
        graphics.setColor(Color.blue);
        graphics.drawRect(0, 0, width-1, height-1);
         
        //写字母
        
        Random random = new Random();
        //设置字体颜色
        graphics.setColor(Color.blue);
        //设置字体及大小
        graphics.setFont(new Font("宋体", Font.BOLD, 20));
        int x=20;
        int y=20;
        String verificationCode="";
        for(int i = 0; i < 4; i++)
        {
            int index = random.nextInt(content.length());
            char letter = content.charAt(index);
            graphics.drawString(letter+" ", x, y);
            x = x+20;
            verificationCode=verificationCode+letter;
        }
        
        
        //把验证码保留到session中
        HttpSession session=request.getSession();
        session.setAttribute("verification_code", verificationCode);
         
        //绘制干扰线
        int x1;
        int x2;
        int y1;
        int y2;
        graphics.setColor(Color.LIGHT_GRAY);
        for(int i = 0;i <10;i++)
        {
            x1=random.nextInt(width);
            x2=random.nextInt(width);
            y1=random.nextInt(height);
            y2=random.nextInt(height);
            graphics.drawLine(x1, y1, x2, y2);
        }
         
        
		return bufferedImage;
	} 
	
	
}
