//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.root.tenant.modules.gen.service;

import com.root.tenant.common.persistence.Page;
import com.root.tenant.common.service.BaseService;
import com.root.tenant.common.utils.StringUtils;
import com.root.tenant.modules.gen.dao.GenTemplateDao;
import com.root.tenant.modules.gen.entity.GenTemplate;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(
        readOnly = true
)
public class GenTemplateService extends BaseService {
    @Autowired
    private GenTemplateDao genTemplateDao;

    public GenTemplateService() {
    }

    public GenTemplate get(String id) {
        return (GenTemplate)this.genTemplateDao.get(id);
    }

    public Page<GenTemplate> find(Page<GenTemplate> page, GenTemplate genTemplate) {
        genTemplate.setPage(page);
        page.setList(this.genTemplateDao.findList(genTemplate));
        return page;
    }

    @Transactional(
            readOnly = false
    )
    public void save(GenTemplate genTemplate) {
        if(genTemplate.getContent() != null) {
            genTemplate.setContent(StringEscapeUtils.unescapeHtml4(genTemplate.getContent()));
        }

        if(StringUtils.isBlank(genTemplate.getId())) {
            genTemplate.preInsert();
            this.genTemplateDao.insert(genTemplate);
        } else {
            genTemplate.preUpdate();
            this.genTemplateDao.update(genTemplate);
        }
    }

    @Transactional(
            readOnly = false
    )
    public void delete(GenTemplate genTemplate) {
        this.genTemplateDao.delete(genTemplate);
    }
}
