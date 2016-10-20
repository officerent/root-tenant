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
import com.root.tenant.modules.gen.entity.GenTable;
import com.root.tenant.modules.gen.entity.GenTableColumn;
import com.root.tenant.modules.gen.util.a;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Service
@Transactional(
        readOnly = true
)
public class GenTableService extends BaseService {
    @Autowired
    private GenTableDao genTableDao;
    @Autowired
    private GenTableColumnDao genTableColumnDao;
    @Autowired
    private GenDataBaseDictDao genDataBaseDictDao;

    public GenTableService() {
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

    public GenTable getTableFormDb(GenTable genTable) {
        List columnList;
        if(StringUtils.isNotBlank(genTable.getName()) && (columnList = this.genDataBaseDictDao.findTableList(genTable)).size() > 0) {
            if(StringUtils.isBlank(genTable.getId())) {
                if(StringUtils.isBlank((genTable = (GenTable)columnList.get(0)).getComments())) {
                    genTable.setComments(genTable.getName());
                }

                genTable.setClassName(StringUtils.toCapitalizeCamelCase(genTable.getName()));
            }

            Iterator var4 = (columnList = this.genDataBaseDictDao.findTableColumnList(genTable)).iterator();

            GenTableColumn e;
            boolean b;
            Iterator var7;
            while(var4.hasNext()) {
                e = (GenTableColumn)var4.next();
                b = false;
                var7 = genTable.getColumnList().iterator();

                while(var7.hasNext()) {
                    GenTableColumn e1;
                    if((e1 = (GenTableColumn)var7.next()).getName() != null && e1.getName().equals(e.getName())) {
                        b = true;
                    }
                }

                if(!b) {
                    genTable.getColumnList().add(e);
                }
            }

            var4 = genTable.getColumnList().iterator();

            while(var4.hasNext()) {
                e = (GenTableColumn)var4.next();
                b = false;
                var7 = columnList.iterator();

                while(var7.hasNext()) {
                    if(((GenTableColumn)var7.next()).getName().equals(e.getName())) {
                        b = true;
                    }
                }

                if(!b) {
                    e.setDelFlag("1");
                }
            }

            genTable.setPkList(this.genDataBaseDictDao.findTablePK(genTable));
            a.a(genTable);
        }

        return genTable;
    }

    @Transactional(
            readOnly = false
    )
    public void save(GenTable genTable) {
        boolean isSync = true;
        if(StringUtils.isBlank(genTable.getId())) {
            isSync = false;
        } else {
            GenTable column;
            if((column = this.get(genTable.getId())).getColumnList().size() == genTable.getColumnList().size() && column.getName().equals(genTable.getName()) && column.getComments().equals(genTable.getComments())) {
                Iterator column2 = genTable.getColumnList().iterator();

                label52:
                while(true) {
                    GenTableColumn column1;
                    GenTableColumn oldColumn;
                    do {
                        if(!column2.hasNext()) {
                            break label52;
                        }
                    } while(!StringUtils.isBlank((column1 = (GenTableColumn)column2.next()).getId()) && (oldColumn = (GenTableColumn)this.genTableColumnDao.get(column1.getId())).getName().equals(column1.getName()) && oldColumn.getJdbcType().equals(column1.getJdbcType()) && oldColumn.getIsPk().equals(column1.getIsPk()) && oldColumn.getComments().equals(column1.getComments()));

                    isSync = false;
                }
            } else {
                isSync = false;
            }
        }

        if(!isSync) {
            genTable.setIsSync("0");
        }

        if(StringUtils.isBlank(genTable.getId())) {
            genTable.preInsert();
            this.genTableDao.insert(genTable);
        } else {
            genTable.preUpdate();
            this.genTableDao.update(genTable);
        }

        this.genTableColumnDao.deleteByGenTable(genTable);
        Iterator column4 = genTable.getColumnList().iterator();

        while(column4.hasNext()) {
            GenTableColumn column3;
            (column3 = (GenTableColumn)column4.next()).setGenTable(genTable);
            column3.setId((String)null);
            column3.preInsert();
            this.genTableColumnDao.insert(column3);
        }

    }

    @Transactional(
            readOnly = false
    )
    public void syncSave(GenTable genTable) {
        genTable.setIsSync("1");
        this.genTableDao.update(genTable);
    }

    @Transactional(
            readOnly = false
    )
    public void saveFromDB(GenTable genTable) {
        genTable.preInsert();
        this.genTableDao.insert(genTable);
        Iterator var3 = genTable.getColumnList().iterator();

        while(var3.hasNext()) {
            GenTableColumn column;
            (column = (GenTableColumn)var3.next()).setGenTable(genTable);
            column.setId((String)null);
            column.preInsert();
            this.genTableColumnDao.insert(column);
        }

    }

    @Transactional(
            readOnly = false
    )
    public void delete(GenTable genTable) {
        this.genTableDao.delete(genTable);
        this.genTableColumnDao.deleteByGenTable(genTable);
    }

    @Transactional(
            readOnly = false
    )
    public void buildTable(String sql) {
        this.genTableDao.buildTable(sql);
    }
}
