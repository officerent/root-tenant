package com.office.rebates.dal.dao;


import java.util.List;
import java.util.Map;

import com.office.rebates.dal.dataobj.RebatesArticle;

/**
 * 新文章dao
 */
public interface NewRebatesArticleMapper {


    /**
     * 计算文章数
     * @param map 条件
     * @return
     */
    public Integer getArticleCount(Map<String,Object> map);
    
    public List<RebatesArticle> getArticleList(Map<String,Object> map);
}