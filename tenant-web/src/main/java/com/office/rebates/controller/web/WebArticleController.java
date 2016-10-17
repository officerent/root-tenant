package com.office.rebates.controller.web;

import com.office.rebates.controller.RouteKey;
import com.office.rebates.dal.dao.RebatesArticleMapper;
import com.office.rebates.model.common.Constants;
import com.office.rebates.model.common.Page;
import com.office.rebates.service.admin.AdminArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 文章控制器
 * Created by xxm on 16/8/1.
 */
@Controller
@RequestMapping(RouteKey.WEB_ARTICLE)
public class WebArticleController {

    /**
     * 文章dao
     */
    @Autowired
    private RebatesArticleMapper rebatesArticleMapper;
    
    @Autowired
    private AdminArticleService adminArticleService;

    /**
     * 文章详情页
     */
    @RequestMapping(RouteKey.ARTICLE_DETAIL)
    public void getArticleDetail(Long articleId,Model model){
        model.addAttribute("data",rebatesArticleMapper.selectByPrimaryKey(articleId));
        //model.addAttribute("manager",manager);
    }
    
    /**
     * 3q动态列表页
     */
    @RequestMapping(RouteKey.SOHO3Q_NEWS)
    public void getSoho3qNews(Page page,Model model){
    	model.addAttribute("articleList",adminArticleService.getArticleList(page, Constants.ARTICLE_TYPE_SOHO3Q, false));
        model.addAttribute("page",page);
    }
    

}
