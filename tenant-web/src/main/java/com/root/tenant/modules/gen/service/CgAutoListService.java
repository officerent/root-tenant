//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.root.tenant.modules.gen.service;

import com.root.tenant.common.persistence.Page;
import com.root.tenant.common.service.BaseService;
import com.root.tenant.common.utils.StringUtils;
import com.root.tenant.modules.gen.dao.GenDataBaseDictDao;
import com.root.tenant.modules.gen.dao.GenTableColumnDao;
import com.root.tenant.modules.gen.dao.GenTableDao;
import com.root.tenant.modules.gen.entity.GenScheme;
import com.root.tenant.modules.gen.entity.GenTable;
import com.root.tenant.modules.gen.entity.GenTableColumn;
import com.root.tenant.modules.gen.util.a;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(
        readOnly = true
)
public class CgAutoListService extends BaseService {
    @Autowired
    private GenTableDao genTableDao;
    @Autowired
    private GenTableColumnDao genTableColumnDao;
    @Autowired
    private GenDataBaseDictDao genDataBaseDictDao;

    public CgAutoListService() {
    }

    public GenTable get(String genTable) {
        GenTable genTable1 = (GenTable)this.genTableDao.get(genTable);
        GenTableColumn genTableColumn;
        (genTableColumn = new GenTableColumn()).setGenTable(new GenTable(genTable1.getId()));
        genTable1.setColumnList(this.genTableColumnDao.findList(genTableColumn));
        return genTable1;
    }

    public Page<GenTable> find(Page<GenTable> page, GenTable genTable) {
        genTable.setPage(page);
        page.setList(this.genTableDao.findList(genTable));
        return page;
    }

    public List<GenTable> findAll() {
        return this.genTableDao.findAllList(new GenTable());
    }

    public List<GenTable> findTableListFormDb(GenTable genTable) {
        return this.genDataBaseDictDao.findTableList(genTable);
    }

    public boolean checkTableName(String tableName) {
        if(StringUtils.isBlank(tableName)) {
            return true;
        } else {
            GenTable genTable;
            (genTable = new GenTable()).setName(tableName);
            return this.genTableDao.findList(genTable).size() == 0;
        }
    }

    public boolean checkTableNameFromDB(String tableName) {
        if(StringUtils.isBlank(tableName)) {
            return true;
        } else {
            GenTable genTable;
            (genTable = new GenTable()).setName(tableName);
            return this.genDataBaseDictDao.findTableList(genTable).size() == 0;
        }
    }

    public String generateCode(GenScheme model) {
        new StringBuilder();
        GenTable genTable;
        (genTable = (GenTable)this.genTableDao.get(model.getGenTable().getId())).setColumnList(this.genTableColumnDao.findList(new GenTableColumn(new GenTable(genTable.getId()))));
        a.b();
        model.setGenTable(genTable);
        Map model1 = a.a(model);
        new com.root.tenant.modules.gen.template.a();
        String genTable1 = "/com/root/tenant/modules/gen/template/viewList.ftl";
        return com.root.tenant.modules.gen.template.a.a(genTable1, "utf-8", model1);
    }

    public String generateListCode(GenScheme model) {
        new StringBuilder();
        GenTable genTable;
        (genTable = (GenTable)this.genTableDao.get(model.getGenTable().getId())).setColumnList(this.genTableColumnDao.findList(new GenTableColumn(new GenTable(genTable.getId()))));
        a.b();
        model.setGenTable(genTable);
        Map model1 = a.a(model);
        new com.root.tenant.modules.gen.template.a();
        String genTable1 = "/com/root/tenant/modules/gen/template/findList.ftl";
        return com.root.tenant.modules.gen.template.a.a(genTable1, "utf-8", model1);
    }
}
