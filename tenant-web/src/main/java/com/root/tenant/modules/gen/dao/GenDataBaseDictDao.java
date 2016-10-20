//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.root.tenant.modules.gen.dao;

import com.root.tenant.common.persistence.CrudDao;
import com.root.tenant.common.persistence.annotation.MyBatisDao;
import com.root.tenant.modules.gen.entity.GenTable;
import com.root.tenant.modules.gen.entity.GenTableColumn;

import java.util.List;

@MyBatisDao
public interface GenDataBaseDictDao extends CrudDao<GenTableColumn> {
    List<GenTable> findTableList(GenTable var1);

    List<GenTableColumn> findTableColumnList(GenTable var1);

    List<String> findTablePK(GenTable var1);
}
