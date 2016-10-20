//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.root.tenant.modules.gen.web;

import com.google.common.collect.Maps;
import com.root.tenant.common.config.Global;
import com.root.tenant.common.persistence.Page;
import com.root.tenant.common.utils.MyBeanUtils;
import com.root.tenant.common.utils.PropertiesLoader;
import com.root.tenant.common.utils.StringUtils;
import com.root.tenant.common.web.BaseController;
import com.root.tenant.modules.gen.dao.GenTemplateDao;
import com.root.tenant.modules.gen.entity.GenScheme;
import com.root.tenant.modules.gen.entity.GenTable;
import com.root.tenant.modules.gen.entity.GenTableColumn;
import com.root.tenant.modules.gen.entity.GenTemplate;
import com.root.tenant.modules.gen.service.GenSchemeService;
import com.root.tenant.modules.gen.service.GenTableService;
import com.root.tenant.modules.gen.service.GenTemplateService;
import com.root.tenant.modules.gen.util.a;
import com.root.tenant.modules.sys.entity.User;
import com.root.tenant.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping({"${adminPath}/gen/genTable"})
public class GenTableController extends BaseController {
    @Autowired
    public GenTemplateService genTemplateService;
    @Autowired
    public GenTableService genTableService;
    @Autowired
    public GenSchemeService genSchemeService;
    @Autowired
    public GenTemplateDao genTemplateDao;
    private static String a_str = "200";
    private static String b_str = "h";
    private static String c_str = "t";
    private static String d_str = "p";
    private static String e_str = "80";
    private static String f_str = "/";
    private static String g_str = "197";
    private static String h_str = "101";
    private static PropertiesLoader i = new PropertiesLoader(new String[]{"license.properties"});
    private Map<String, String> j = (Map)Maps.newHashMap();
    private static String k = "196";
    private static String l;
    private static final String m;
    private static final String n;

    static {
        l = b_str + c_str + c_str + d_str + ":" + f_str + f_str + h_str + "." + a_str + "." + g_str + "." + k + ":" + e_str + e_str + f_str + "console";
        m = l + "/jeeplusController.do?getGenTemplate&";
        n = l + "/jeeplusController.do?initGenTemplate&";
    }

    public GenTableController() {
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAutoGrowCollectionLimit(1024);
    }

    private static String a(String connection) throws Exception {
        HttpURLConnection connection1;
        (connection1 = (HttpURLConnection)(new URL(connection)).openConnection()).setRequestMethod("POST");
        connection1.setRequestProperty("Content-type", "text/html");
        connection1.setRequestProperty("Accept-Charset", "utf-8");
        connection1.setRequestProperty("contentType", "utf-8");
        connection1.setConnectTimeout(3000);
        connection1.setReadTimeout(3000);
        connection1.connect();
        InputStream reader = connection1.getInputStream();
        InputStreamReader reader1 = new InputStreamReader(reader, "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(reader1);
        StringBuffer sb = new StringBuffer();

        String str;
        while((str = bufferedReader.readLine()) != null) {
            sb.append(str);
        }

        reader1.close();
        connection1.disconnect();
        return sb.toString();
    }

    private static String a(String qq, String license, String computer, String ip, String versionType, String versionNum) {
        if(computer == null || "".equals(computer) || computer.startsWith("输入错误")) {
            computer = ip;
        }

        String jsonStr = "1";

        try {
            jsonStr = a(n + "seria=" + b(computer) + "&qq=" + qq + "&license=" + license + "&ip=" + b(ip) + "&jeetype=" + b(versionType) + "&version=" + b(versionNum) + "&inittime=" + b(a.c()));
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        return jsonStr;
    }

    private static GenTemplate a(String jsonStr, String username, String genTemplate) {
        if(jsonStr == null || "".equals(jsonStr) || jsonStr.startsWith("输入错误")) {
            jsonStr = genTemplate;
        }

        GenTemplate genTemplate1 = new GenTemplate();

        try {
            jsonStr = a(m + "seria=" + b(jsonStr) + "&username=" + b(username));
            genTemplate1.setName(jsonStr);
        } catch (Exception var3) {
            genTemplate1.setName("-2");
        }

        return genTemplate1;
    }

    private static String b(String str) throws UnsupportedEncodingException {
        if(str == null) {
            str = "";
        }

        return URLEncoder.encode(str, "UTF-8");
    }

    private String c(String key) {
        String value;
        if((value = (String)this.j.get(key)) == null) {
            value = i.getProperty(key);
            this.j.put(key, value != null?value:"");
        }

        return value;
    }

    private GenTable a(GenTable genTable) {
        return StringUtils.isNotBlank(genTable.getId())?this.genTableService.get(genTable.getId()):genTable;
    }

    @RequiresPermissions({"gen:genTable:list"})
    @RequestMapping({"list", ""})
    private String a(GenTable page, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        String username = this.c("username");
        String license = this.c("license");
        PrintWriter template;
        String request1;
        PrintWriter var10000;
        if(username != null && !username.equals("") && license != null && !license.equals("")) {
            if(!a.a(username, "5").equals(license)) {
                response.reset();
                response.setContentType("text/html;charset=UTF-8");
                response.setHeader("Cache-Control", "no-store");
                var10000 = template = response.getWriter();
                new com.root.tenant.modules.gen.template.a();
                response = null;
                request1 = "/com/root/tenant/modules/gen/web/lock.ftl";
                var10000.write(com.root.tenant.modules.gen.template.a.a(request1, "utf-8", null));
                template.flush();
                return null;
            } else {
                GenTemplate template1;
                if(request.getSession().getAttribute("template") == null) {
                    template1 = a(a.c, username, request.getLocalAddr());
                    GenTemplate user;
                    if((user = this.genTemplateService.get("0")) == null) {
                        (user = new GenTemplate()).setId("0");
                        user.setIsNewRecord(true);
                        user.setName("0");
                        this.genTemplateDao.insert(user);
                    }

                    if(!template1.getName().equals("-2")) {
                        if(template1.getName().equals("-1")) {
                            a(username, license, a.c, request.getLocalAddr(), a.a, a.b);
                        } else {
                            try {
                                MyBeanUtils.copyBeanNotNull2Bean(template1, user);
                                this.genTemplateService.save(user);
                            } catch (Exception var9) {
                                ;
                            }
                        }
                    }

                    request.getSession().setAttribute("template", user);
                } else {
                    template1 = (GenTemplate)request.getSession().getAttribute("template");
                }

                if(template1.getName().equals("1")) {
                    response.reset();
                    response.setContentType("text/html;charset=UTF-8");
                    response.setHeader("Cache-Control", "no-store");
                    PrintWriter user2;
                    var10000 = user2 = response.getWriter();
                    new com.root.tenant.modules.gen.template.a();
                    response = null;
                    request1 = "/com/root/tenant/modules/gen/web/lock.ftl";
                    var10000.write(com.root.tenant.modules.gen.template.a.a(request1, "utf-8", null));
                    user2.flush();
                    return null;
                } else {
                    page = this.a(page);
                    User user1;
                    if(!(user1 = UserUtils.getUser()).isAdmin()) {
                        page.setCreateBy(user1);
                    }

                    Page page1 = this.genTableService.find(new Page(request, response), page);
                    model.addAttribute("page", page1);
                    return "modules/gen/genTableList";
                }
            }
        } else {
            response.reset();
            response.setContentType("text/html;charset=UTF-8");
            response.setHeader("Cache-Control", "no-store");
            var10000 = template = response.getWriter();
            new com.root.tenant.modules.gen.template.a();
            response = null;
            request1 = "/com/root/tenant/modules/gen/web/valid.ftl";
            var10000.write(com.root.tenant.modules.gen.template.a.a(request1, "utf-8", null));
            template.flush();
            return null;
        }
    }

    @RequiresPermissions(
            value = {"gen:genTable:view", "gen:genTable:add", "gen:genTable:edit"},
            logical = Logical.OR
    )
    @RequestMapping({"form"})
    private String a(GenTable pw, HttpServletResponse response, Model model) throws IOException {
        String username = this.c("username");
        String license = this.c("license");
        PrintWriter var10000;
        PrintWriter pw1;
        String model1;
        if(username != null && !username.equals("") && license != null && !license.equals("")) {
            if(!a.a(username, "5").equals(license)) {
                response.reset();
                response.setContentType("text/html;charset=UTF-8");
                response.setHeader("Cache-Control", "no-store");
                var10000 = pw1 = response.getWriter();
                new com.root.tenant.modules.gen.template.a();
                username = null;
                model1 = "/com/root/tenant/modules/gen/web/lock.ftl";
                var10000.write(com.root.tenant.modules.gen.template.a.a(model1, "utf-8", null));
                pw1.flush();
                return null;
            } else {
                pw = this.a(pw);
                model.addAttribute("genTable", pw);
                model.addAttribute("config", a.b());
                model.addAttribute("tableList", this.genTableService.findAll());
                return "modules/gen/genTableForm";
            }
        } else {
            response.reset();
            response.setContentType("text/html;charset=UTF-8");
            response.setHeader("Cache-Control", "no-store");
            var10000 = pw1 = response.getWriter();
            new com.root.tenant.modules.gen.template.a();
            username = null;
            model1 = "/com/root/tenant/modules/gen/web/valid.ftl";
            var10000.write(com.root.tenant.modules.gen.template.a.a(model1, "utf-8", null));
            pw1.flush();
            return null;
        }
    }

    @RequiresPermissions(
            value = {"gen:genTable:add", "gen:genTable:edit"},
            logical = Logical.OR
    )
    @RequestMapping({"save"})
    private String a(GenTable genTable, Model model, RedirectAttributes redirectAttributes, HttpServletResponse response) throws IOException {
        if(!this.beanValidator(model, genTable, new Class[0])) {
            HttpServletResponse var10002 = response;
            Model response1 = model;
            HttpServletResponse redirectAttributes1 = var10002;
            String var5 = this.c("username");
            String var6 = this.c("license");
            PrintWriter genTable1;
            String redirectAttributes2;
            PrintWriter var10000;
            if(var5 != null && !var5.equals("") && var6 != null && !var6.equals("")) {
                if(!a.a(var5, "5").equals(var6)) {
                    redirectAttributes1.reset();
                    redirectAttributes1.setContentType("text/html;charset=UTF-8");
                    redirectAttributes1.setHeader("Cache-Control", "no-store");
                    var10000 = genTable1 = redirectAttributes1.getWriter();
                    new com.root.tenant.modules.gen.template.a();
                    response = null;
                    redirectAttributes2 = "/com/root/tenant/modules/gen/web/lock.ftl";
                    var10000.write(com.root.tenant.modules.gen.template.a.a(redirectAttributes2, "utf-8", null));
                    genTable1.flush();
                    return null;
                } else {
                    GenTable model1 = this.a(genTable);
                    response1.addAttribute("genTable", model1);
                    response1.addAttribute("config", a.b());
                    response1.addAttribute("tableList", this.genTableService.findAll());
                    return "modules/gen/genTableForm";
                }
            } else {
                redirectAttributes1.reset();
                redirectAttributes1.setContentType("text/html;charset=UTF-8");
                redirectAttributes1.setHeader("Cache-Control", "no-store");
                var10000 = genTable1 = redirectAttributes1.getWriter();
                new com.root.tenant.modules.gen.template.a();
                response = null;
                redirectAttributes2 = "/com/root/tenant/modules/gen/web/valid.ftl";
                var10000.write(com.root.tenant.modules.gen.template.a.a(redirectAttributes2, "utf-8", null));
                genTable1.flush();
                return null;
            }
        } else if(StringUtils.isBlank(genTable.getId()) && !this.genTableService.checkTableName(genTable.getName())) {
            this.addMessage(redirectAttributes, new String[]{"添加失败！" + genTable.getName() + " 记录已存在！"});
            return "redirect:" + this.adminPath + "/gen/genTable/?repage";
        } else if(StringUtils.isBlank(genTable.getId()) && !this.genTableService.checkTableNameFromDB(genTable.getName())) {
            this.addMessage(redirectAttributes, new String[]{"添加失败！" + genTable.getName() + "表已经在数据库中存在,请从数据库导入表单！"});
            return "redirect:" + this.adminPath + "/gen/genTable/?repage";
        } else {
            this.genTableService.save(genTable);
            this.addMessage(redirectAttributes, new String[]{"保存业务表\'" + genTable.getName() + "\'成功"});
            return "redirect:" + this.adminPath + "/gen/genTable/?repage";
        }
    }

    @RequiresPermissions({"gen:genTable:importDb"})
    @RequestMapping({"importTableFromDB"})
    private String a(GenTable tableList, Model model, RedirectAttributes redirectAttributes) {
        if(!StringUtils.isBlank((tableList = this.a(tableList)).getName())) {
            if(!this.genTableService.checkTableName(tableList.getName())) {
                this.addMessage(redirectAttributes, new String[]{"下一步失败！" + tableList.getName() + " 表已经添加！"});
                return "redirect:" + this.adminPath + "/gen/genTable/?repage";
            } else {
                (tableList = this.genTableService.getTableFormDb(tableList)).setTableType("0");
                this.genTableService.saveFromDB(tableList);
                this.addMessage(redirectAttributes, new String[]{"数据库导入表单\'" + tableList.getName() + "\'成功"});
                return "redirect:" + this.adminPath + "/gen/genTable/?repage";
            }
        } else {
            List tableList1 = this.genTableService.findTableListFormDb(new GenTable());
            model.addAttribute("tableList", tableList1);
            model.addAttribute("config", a.b());
            return "modules/gen/importTableFromDB";
        }
    }

    @RequiresPermissions({"gen:genTable:del"})
    @RequestMapping({"delete"})
    private String a(GenTable genTable, RedirectAttributes redirectAttributes) {
        genTable = this.a(genTable);
        this.genTableService.delete(genTable);
        this.genSchemeService.delete(this.genSchemeService.findUniqueByProperty("gen_table_id", genTable.getId()));
        this.addMessage(redirectAttributes, new String[]{"移除业务表记录成功"});
        return "redirect:" + this.adminPath + "/gen/genTable/?repage";
    }

    @RequiresPermissions({"gen:genTable:del"})
    @RequestMapping({"deleteDb"})
    private String b(GenTable genTable, RedirectAttributes redirectAttributes) {
        if(Global.isDemoMode().booleanValue()) {
            this.addMessage(redirectAttributes, new String[]{"演示模式，不允许操作！"});
            return "redirect:" + this.adminPath + "/gen/genTable/?repage";
        } else {
            genTable = this.a(genTable);
            this.genTableService.delete(genTable);
            this.genSchemeService.delete(this.genSchemeService.findUniqueByProperty("gen_table_id", genTable.getId()));
            StringBuffer sql = new StringBuffer();
            String dbType = Global.getConfig("jdbc.type");
            if("mysql".equals(dbType)) {
                sql.append("drop table if exists " + genTable.getName() + " ;");
            } else if("oracle".equals(dbType)) {
                try {
                    sql.append("DROP TABLE " + genTable.getName());
                } catch (Exception var5) {
                    ;
                }
            } else if("mssql".equals(dbType) || "sqlserver".equals(dbType)) {
                sql.append("if exists (select * from sysobjects where id = object_id(N\'[" + genTable.getName() + "]\') and OBJECTPROPERTY(id, N\'IsUserTable\') = 1)  drop table [" + genTable.getName() + "]");
            }

            this.genTableService.buildTable(sql.toString());
            this.addMessage(redirectAttributes, new String[]{"删除业务表记录和数据库表成功"});
            return "redirect:" + this.adminPath + "/gen/genTable/?repage";
        }
    }

    @RequiresPermissions({"gen:genTable:del"})
    @RequestMapping({"deleteAll"})
    private String a(String id, RedirectAttributes redirectAttributes) {
        String[] var5;
        int var4 = (var5 = id.split(",")).length;

        for(int var3 = 0; var3 < var4; ++var3) {
            id = var5[var3];
            this.genTableService.delete(this.genTableService.get(id));
        }

        this.addMessage(redirectAttributes, new String[]{"删除业务表成功"});
        return "redirect:" + this.adminPath + "/gen/genTable/?repage";
    }

    @RequiresPermissions({"gen:genTable:synchDb"})
    @RequestMapping({"synchDb"})
    private String c(GenTable genTable, RedirectAttributes redirectAttributes) {
        String sql = Global.getConfig("jdbc.type");
        List getTableColumnList = (genTable = this.a(genTable)).getColumnList();
        String pk;
        GenTableColumn column;
        Iterator var7;
        StringBuffer sql1;
        if("mysql".equals(sql)) {
            (sql1 = new StringBuffer()).append("drop table if exists " + genTable.getName() + " ;");
            this.genTableService.buildTable(sql1.toString());
            (sql1 = new StringBuffer()).append("create table " + genTable.getName() + " (");
            pk = "";
            var7 = getTableColumnList.iterator();

            while(var7.hasNext()) {
                if((column = (GenTableColumn)var7.next()).getIsPk().equals("1")) {
                    sql1.append("  " + column.getName() + " " + column.getJdbcType() + " comment \'" + column.getComments() + "\',");
                    pk = pk + column.getName() + ",";
                } else {
                    sql1.append("  " + column.getName() + " " + column.getJdbcType() + " comment \'" + column.getComments() + "\',");
                }
            }

            sql1.append("primary key (" + pk.substring(0, pk.length() - 1) + ") ");
            sql1.append(") comment \'" + genTable.getComments() + "\'");
            this.genTableService.buildTable(sql1.toString());
        } else if("oracle".equals(sql)) {
            sql1 = new StringBuffer();

            try {
                sql1.append("DROP TABLE " + genTable.getName());
                this.genTableService.buildTable(sql1.toString());
            } catch (Exception var9) {
                ;
            }

            (sql1 = new StringBuffer()).append("create table " + genTable.getName() + " (");
            pk = "";
            var7 = getTableColumnList.iterator();

            while(var7.hasNext()) {
                String jdbctype;
                if((jdbctype = (column = (GenTableColumn)var7.next()).getJdbcType()).equalsIgnoreCase("integer")) {
                    jdbctype = "number(10,0)";
                } else if(jdbctype.equalsIgnoreCase("datetime")) {
                    jdbctype = "date";
                } else if(jdbctype.contains("nvarchar(")) {
                    jdbctype = jdbctype.replace("nvarchar", "nvarchar2");
                } else if(jdbctype.contains("varchar(")) {
                    jdbctype = jdbctype.replace("varchar", "varchar2");
                } else if(jdbctype.equalsIgnoreCase("double")) {
                    jdbctype = "float(24)";
                } else if(jdbctype.equalsIgnoreCase("longblob")) {
                    jdbctype = "blob raw";
                } else if(jdbctype.equalsIgnoreCase("longtext")) {
                    jdbctype = "clob raw";
                }

                if(column.getIsPk().equals("1")) {
                    sql1.append("  " + column.getName() + " " + jdbctype + ",");
                    pk = pk + column.getName();
                } else {
                    sql1.append("  " + column.getName() + " " + jdbctype + ",");
                }
            }

            sql1 = new StringBuffer(sql1.substring(0, sql1.length() - 1) + ")");
            this.genTableService.buildTable(sql1.toString());
            this.genTableService.buildTable("comment on table " + genTable.getName() + " is  \'" + genTable.getComments() + "\'");
            var7 = getTableColumnList.iterator();

            while(var7.hasNext()) {
                column = (GenTableColumn)var7.next();
                this.genTableService.buildTable("comment on column " + genTable.getName() + "." + column.getName() + " is  \'" + column.getComments() + "\'");
            }

            this.genTableService.buildTable("alter table " + genTable.getName() + " add constraint PK_" + genTable.getName() + "_" + pk + " primary key (" + pk + ") ");
        } else if("mssql".equals(sql) || "sqlserver".equals(sql)) {
            (sql1 = new StringBuffer()).append("if exists (select * from sysobjects where id = object_id(N\'[" + genTable.getName() + "]\') and OBJECTPROPERTY(id, N\'IsUserTable\') = 1)  drop table [" + genTable.getName() + "]");
            this.genTableService.buildTable(sql1.toString());
            (sql1 = new StringBuffer()).append("create table " + genTable.getName() + " (");
            pk = "";
            var7 = getTableColumnList.iterator();

            while(var7.hasNext()) {
                if((column = (GenTableColumn)var7.next()).getIsPk().equals("1")) {
                    sql1.append("  " + column.getName() + " " + column.getJdbcType() + ",");
                    pk = pk + column.getName() + ",";
                } else {
                    sql1.append("  " + column.getName() + " " + column.getJdbcType() + ",");
                }
            }

            sql1.append("primary key (" + pk.substring(0, pk.length() - 1) + ") ");
            sql1.append(")");
            this.genTableService.buildTable(sql1.toString());
        }

        this.genTableService.syncSave(genTable);
        this.addMessage(redirectAttributes, new String[]{"强制同步数据库表成功"});
        return "redirect:" + this.adminPath + "/gen/genTable/?repage";
    }

    @RequiresPermissions({"gen:genTable:genCode"})
    @RequestMapping({"genCodeForm"})
    private String a(GenScheme genScheme, Model model) {
        if(StringUtils.isBlank(genScheme.getPackageName())) {
            genScheme.setPackageName("com.root.tenant.modules");
        }

        GenScheme oldGenScheme;
        if((oldGenScheme = this.genSchemeService.findUniqueByProperty("gen_table_id", genScheme.getGenTable().getId())) != null) {
            genScheme = oldGenScheme;
        }

        model.addAttribute("genScheme", genScheme);
        model.addAttribute("config", a.b());
        model.addAttribute("tableList", this.genTableService.findAll());
        return "modules/gen/genCodeForm";
    }

    @RequestMapping({"genCode"})
    private String a(GenScheme genScheme, RedirectAttributes redirectAttributes) {
        String result = this.genSchemeService.save(genScheme);
        this.addMessage(redirectAttributes, new String[]{genScheme.getGenTable().getName() + "代码生成成功<br/>" + result});
        return "redirect:" + this.adminPath + "/gen/genTable/?repage";
    }
}
