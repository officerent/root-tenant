package com.office.rebates.service.admin.impl;

import com.alibaba.fastjson.JSON;
import com.office.rebates.dal.dao.NewRebatesArticleMapper;
import com.office.rebates.dal.dao.RebatesArticleMapper;
import com.office.rebates.dal.dataobj.RebatesArticle;
import com.office.rebates.model.common.Constants;
import com.office.rebates.model.common.Page;
import com.office.rebates.service.admin.AdminArticleService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 文章服务实现类
 * Created by xxm on 2016/7/25.
 */
@Service("adminArticleService")
public class AdminArticleServiceImpl implements AdminArticleService {

    /**
     * 日志
     */
    private  Logger logger = LoggerFactory.getLogger(AdminArticleServiceImpl.class);
    
    /**
     * 新文章dao
     */
    @Autowired
    private NewRebatesArticleMapper newRebatesArticleMapper;
    
    @Autowired
    private RebatesArticleMapper rebatesArticleMapper;

 
    /**
     * 查询文章信息列表
     * @param page 分页
     * @param name 用户名称
     * @return
     */
    @Override
    public List<RebatesArticle> getArticleList(Page page,String type, Boolean isDeleted) {
        List<RebatesArticle> rebatesArticles = new ArrayList<RebatesArticle>();
        Map<String,Object> map = new HashMap<String,Object>();
        try{
        	if(type!=null){
        		map.put("type",type);
        	}
        	if(isDeleted!=null){
        		if(isDeleted){
        			map.put("is_deleted",1);
        		}else{
        			map.put("is_deleted",0);
        		}        		
        	}
            page.setTotalElements(newRebatesArticleMapper.getArticleCount(map));
            map.put("start",page.getNumber()*page.getSize());
            map.put("size",page.getSize());
            rebatesArticles = newRebatesArticleMapper.getArticleList(map);
        }catch (Exception e){
            logger.error("fail to get article list",e);
        }
        return rebatesArticles;
    }


	@Override
	public void createArticle(RebatesArticle article) {
		Date now=new Date();
		//article.setCreateTime(now);
		article.setIsDeleted(false);
		article.setLastUpdateTime(now);
		rebatesArticleMapper.insert(article);
		logger.info("new article was created:"+JSON.toJSONString(article));
	}


	@Override
	public void updateArticle(RebatesArticle article) {
		Date now=new Date();
		article.setLastUpdateTime(now);
		rebatesArticleMapper.updateByPrimaryKeySelective(article);
		logger.info("article was updated:"+JSON.toJSONString(article));
		//logger.info("article was updated2:"+JSON.toJSONString(rebatesArticleMapper.selectByPrimaryKey(article.getArticleId())));
	}


	@Override
	public void deleteArticle(Long articleId) {
		Date now=new Date();
		RebatesArticle article=rebatesArticleMapper.selectByPrimaryKey(articleId);
		article.setLastUpdateTime(now);
		article.setIsDeleted(true);
		rebatesArticleMapper.updateByPrimaryKeySelective(article);
		logger.info("article was deleted:"+JSON.toJSONString(article));
	}


	@Override
	public void reopenArticle(Long articleId) {
		Date now=new Date();
		RebatesArticle article=rebatesArticleMapper.selectByPrimaryKey(articleId);
		article.setLastUpdateTime(now);
		article.setIsDeleted(false);
		rebatesArticleMapper.updateByPrimaryKeySelective(article);
		logger.info("article was re-opened:"+JSON.toJSONString(article));
		
	}
}
