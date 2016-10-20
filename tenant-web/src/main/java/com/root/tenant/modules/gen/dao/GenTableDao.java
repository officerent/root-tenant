//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.root.tenant.modules.gen.dao;

import com.root.tenant.common.persistence.CrudDao;
import com.root.tenant.common.persistence.annotation.MyBatisDao;
import com.root.tenant.modules.gen.entity.GenTable;
import org.apache.ibatis.annotations.Param;

@MyBatisDao
public interface GenTableDao extends CrudDao<GenTable> {
    int buildTable(@Param("sql") String var1);
}
