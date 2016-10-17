package com.office.rebates.service.admin;

import com.office.rebates.dal.dataobj.RebatesArticle;
import com.office.rebates.model.common.Page;

import java.util.List;

/**
 * 后台文章服务接口
 * Created by xxm on 2016/7/25.
 */
public interface AdminArticleService {


    /**
     * 从数据库中查询文章信息列表
     * @param page 分页
     * @return
     */
    public List<RebatesArticle> getArticleList(Page page,String type, Boolean isDeleted);
    
    /**
     * 创建文章
     * @param page 分页
     * @return
     */
    public void createArticle(RebatesArticle article);

	public void updateArticle(RebatesArticle article);

	public void deleteArticle(Long articleId);

	public void reopenArticle(Long articleId);
}
