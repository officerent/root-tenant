//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.root.tenant.modules.gen.service;

import com.root.tenant.common.persistence.Page;
import com.root.tenant.common.service.BaseService;
import com.root.tenant.common.utils.StringUtils;
import com.root.tenant.modules.gen.dao.GenSchemeDao;
import com.root.tenant.modules.gen.dao.GenTableColumnDao;
import com.root.tenant.modules.gen.dao.GenTableDao;
import com.root.tenant.modules.gen.entity.*;
import com.root.tenant.modules.gen.util.a;
import com.root.tenant.modules.sys.entity.Menu;
import com.root.tenant.modules.sys.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
@Transactional(
        readOnly = true
)
public class GenSchemeService extends BaseService {
    @Autowired
    private GenSchemeDao genSchemeDao;
    @Autowired
    private GenTableDao genTableDao;
    @Autowired
    private GenTableColumnDao genTableColumnDao;
    @Autowired
    private SystemService systemService;

    public GenSchemeService() {
    }

    public GenScheme get(String id) {
        return (GenScheme)this.genSchemeDao.get(id);
    }

    public Page<GenScheme> find(Page<GenScheme> page, GenScheme genScheme) {
        a.a();
        genScheme.setPage(page);
        page.setList(this.genSchemeDao.findList(genScheme));
        return page;
    }

    @Transactional(
            readOnly = false
    )
    public String save(GenScheme genScheme) {
        if(StringUtils.isBlank(genScheme.getId())) {
            genScheme.preInsert();
            this.genSchemeDao.insert(genScheme);
        } else {
            genScheme.preUpdate();
            this.genSchemeDao.update(genScheme);
        }

        return this.generateCode(genScheme);
    }

    @Transactional(
            readOnly = false
    )
    public void createMenu(GenScheme exportMenu, Menu topMenu) {
        String permissionPrefix = StringUtils.lowerCase(exportMenu.getModuleName()) + (StringUtils.isNotBlank(exportMenu.getSubModuleName())?":" + StringUtils.lowerCase(exportMenu.getSubModuleName()):"") + ":" + StringUtils.uncapitalize(exportMenu.getGenTable().getClassName());
        String url = "/" + StringUtils.lowerCase(exportMenu.getModuleName()) + (StringUtils.isNotBlank(exportMenu.getSubModuleName())?"/" + StringUtils.lowerCase(exportMenu.getSubModuleName()):"") + "/" + StringUtils.uncapitalize(exportMenu.getGenTable().getClassName());
        topMenu.setName(exportMenu.getFunctionName());
        topMenu.setHref(url);
        topMenu.setIsShow("1");
        topMenu.setPermission(permissionPrefix + ":list");
        this.systemService.saveMenu(topMenu);
        Menu exportMenu1;
        (exportMenu1 = new Menu()).setName("增加");
        exportMenu1.setIsShow("0");
        exportMenu1.setSort(Integer.valueOf(30));
        exportMenu1.setPermission(permissionPrefix + ":add");
        exportMenu1.setParent(topMenu);
        this.systemService.saveMenu(exportMenu1);
        (exportMenu1 = new Menu()).setName("删除");
        exportMenu1.setIsShow("0");
        exportMenu1.setSort(Integer.valueOf(60));
        exportMenu1.setPermission(permissionPrefix + ":del");
        exportMenu1.setParent(topMenu);
        this.systemService.saveMenu(exportMenu1);
        (exportMenu1 = new Menu()).setName("编辑");
        exportMenu1.setIsShow("0");
        exportMenu1.setSort(Integer.valueOf(90));
        exportMenu1.setPermission(permissionPrefix + ":edit");
        exportMenu1.setParent(topMenu);
        this.systemService.saveMenu(exportMenu1);
        (exportMenu1 = new Menu()).setName("查看");
        exportMenu1.setIsShow("0");
        exportMenu1.setSort(Integer.valueOf(120));
        exportMenu1.setPermission(permissionPrefix + ":view");
        exportMenu1.setParent(topMenu);
        this.systemService.saveMenu(exportMenu1);
        (exportMenu1 = new Menu()).setName("导入");
        exportMenu1.setIsShow("0");
        exportMenu1.setSort(Integer.valueOf(150));
        exportMenu1.setPermission(permissionPrefix + ":import");
        exportMenu1.setParent(topMenu);
        this.systemService.saveMenu(exportMenu1);
        (exportMenu1 = new Menu()).setName("导出");
        exportMenu1.setIsShow("0");
        exportMenu1.setSort(Integer.valueOf(180));
        exportMenu1.setPermission(permissionPrefix + ":export");
        exportMenu1.setParent(topMenu);
        this.systemService.saveMenu(exportMenu1);
    }

    @Transactional(
            readOnly = false
    )
    public void delete(GenScheme genScheme) {
        this.genSchemeDao.delete(genScheme);
    }

    public String generateCode(GenScheme genScheme) {
        StringBuilder result = new StringBuilder();
        GenTable genTable;
        (genTable = (GenTable)this.genTableDao.get(genScheme.getGenTable().getId())).setColumnList(this.genTableColumnDao.findList(new GenTableColumn(new GenTable(genTable.getId()))));
        GenConfig childTableTemplateList;
        List templateList = a.a(childTableTemplateList = a.b(), genScheme.getCategory(), false);
        GenTable model;
        List childTableTemplateList1;
        if((childTableTemplateList1 = a.a(childTableTemplateList, genScheme.getCategory(), true)).size() > 0) {
            (model = new GenTable()).setParentTable(genTable.getName());
            genTable.setChildList(this.genTableDao.findList(model));
        }

        Iterator tpl = genTable.getChildList().iterator();

        while(tpl.hasNext()) {
            (model = (GenTable)tpl.next()).setParent(genTable);
            model.setColumnList(this.genTableColumnDao.findList(new GenTableColumn(new GenTable(model.getId()))));
            genScheme.setGenTable(model);
            Map childTableModel = a.a(genScheme);
            Iterator var9 = childTableTemplateList1.iterator();

            while(var9.hasNext()) {
                GenTemplate model1 = (GenTemplate)var9.next();
                result.append(a.a(model1, childTableModel, true));
            }
        }

        genScheme.setGenTable(genTable);
        Map model2 = a.a(genScheme);
        Iterator childTableModel1 = templateList.iterator();

        while(childTableModel1.hasNext()) {
            GenTemplate tpl1 = (GenTemplate)childTableModel1.next();
            result.append(a.a(tpl1, model2, true));
        }

        return result.toString();
    }

    public GenScheme findUniqueByProperty(String propertyName, String value) {
        return (GenScheme)this.genSchemeDao.findUniqueByProperty(propertyName, value);
    }
}
