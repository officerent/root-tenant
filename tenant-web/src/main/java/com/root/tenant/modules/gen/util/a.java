//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.root.tenant.modules.gen.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.root.tenant.common.config.Global;
import com.root.tenant.common.mapper.JaxbMapper;
import com.root.tenant.common.utils.DateUtils;
import com.root.tenant.common.utils.FileUtils;
import com.root.tenant.common.utils.FreeMarkers;
import com.root.tenant.common.utils.StringUtils;
import com.root.tenant.modules.gen.entity.*;
import com.root.tenant.modules.sys.entity.Area;
import com.root.tenant.modules.sys.entity.Office;
import com.root.tenant.modules.sys.entity.User;
import com.root.tenant.modules.sys.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class a {
    private static Logger d = LoggerFactory.getLogger(com.root.tenant.modules.gen.util.a.class);
    private static final String e = "http://www.jeeplus.org";
    public static String a = "jeeplus";
    public static String b = "2.5";
    public static String c = null;

    static {
        c = f() + e() + c("c") + g();
    }

    public a() {
    }

    public static String a(String suf, String need) {
        Calendar type;
        (type = Calendar.getInstance()).add(1, 3);
        type.add(6, -1);
        need = (new DecimalFormat("000")).format(Integer.valueOf(need));
        String type1 = "YE3MP-";
        need = suf.substring(0, 1) + type1 + "300" + need + "1";
        int suf1 = a(need + "http://www.jeeplus.org" + suf);
        return b(need + String.valueOf(suf1));
    }

    private static int a(String ac) {
        int i = 0;
        char[] var4 = ac.toCharArray();
        int j = 0;

        for(int k = var4.length; j < k; ++j) {
            i = i * 31 + var4[j];
        }

        return Math.abs(i);
    }

    private static String b(String ac) {
        byte[] abyte0 = ac.getBytes();
        char[] var5 = new char[ac.length()];
        int i = 0;

        for(int k = abyte0.length; i < k; ++i) {
            int j;
            if((j = abyte0[i]) >= 48 && j <= 57) {
                j = (j - 48 + 5) % 10 + 48;
            } else if(j >= 65 && j <= 90) {
                j = (j - 65 + 13) % 26 + 65;
            } else if(j >= 97 && j <= 122) {
                j = (j - 97 + 13) % 26 + 97;
            }

            var5[i] = (char)j;
        }

        return String.valueOf(var5);
    }

    public static void a(GenTable genTable) {
        Iterator var2 = genTable.getColumnList().iterator();

        while(true) {
            while(true) {
                GenTableColumn column;
                do {
                    if(!var2.hasNext()) {
                        return;
                    }
                } while(StringUtils.isNotBlank((column = (GenTableColumn)var2.next()).getId()));

                if(StringUtils.isBlank(column.getComments())) {
                    column.setComments(column.getName());
                }

                if(!StringUtils.startsWithIgnoreCase(column.getJdbcType(), "CHAR") && !StringUtils.startsWithIgnoreCase(column.getJdbcType(), "VARCHAR") && !StringUtils.startsWithIgnoreCase(column.getJdbcType(), "NARCHAR")) {
                    if(!StringUtils.startsWithIgnoreCase(column.getJdbcType(), "DATETIME") && !StringUtils.startsWithIgnoreCase(column.getJdbcType(), "DATE") && !StringUtils.startsWithIgnoreCase(column.getJdbcType(), "TIMESTAMP")) {
                        if(StringUtils.startsWithIgnoreCase(column.getJdbcType(), "BIGINT") || StringUtils.startsWithIgnoreCase(column.getJdbcType(), "NUMBER")) {
                            String[] ss;
                            if((ss = StringUtils.split(StringUtils.substringBetween(column.getJdbcType(), "(", ")"), ",")) != null && ss.length == 2 && Integer.parseInt(ss[1]) > 0) {
                                column.setJavaType("Double");
                            } else if(ss != null && ss.length == 1 && Integer.parseInt(ss[0]) <= 10) {
                                column.setJavaType("Integer");
                            } else {
                                column.setJavaType("Long");
                            }
                        }
                    } else {
                        column.setJavaType("java.util.Date");
                        column.setShowType("dateselect");
                    }
                } else {
                    column.setJavaType("String");
                }

                column.setJavaField(StringUtils.toCamelCase(column.getName()));
                column.setIsPk(genTable.getPkList().contains(column.getName())?"1":"0");
                column.setIsInsert("1");
                if(!StringUtils.equalsIgnoreCase(column.getName(), "id") && !StringUtils.equalsIgnoreCase(column.getName(), "create_by") && !StringUtils.equalsIgnoreCase(column.getName(), "create_date") && !StringUtils.equalsIgnoreCase(column.getName(), "del_flag")) {
                    column.setIsEdit("1");
                } else {
                    column.setIsEdit("0");
                }

                if(!StringUtils.equalsIgnoreCase(column.getName(), "name") && !StringUtils.equalsIgnoreCase(column.getName(), "title") && !StringUtils.equalsIgnoreCase(column.getName(), "remarks") && !StringUtils.equalsIgnoreCase(column.getName(), "update_date")) {
                    column.setIsList("0");
                } else {
                    column.setIsList("1");
                }

                if(!StringUtils.equalsIgnoreCase(column.getName(), "name") && !StringUtils.equalsIgnoreCase(column.getName(), "title")) {
                    column.setIsQuery("0");
                } else {
                    column.setIsQuery("1");
                }

                if(!StringUtils.equalsIgnoreCase(column.getName(), "name") && !StringUtils.equalsIgnoreCase(column.getName(), "title")) {
                    column.setQueryType("=");
                } else {
                    column.setQueryType("like");
                }

                if(StringUtils.startsWithIgnoreCase(column.getName(), "user_id")) {
                    column.setJavaType(User.class.getName());
                    column.setJavaField(column.getJavaField().replaceAll("Id", ".id|name"));
                    column.setShowType("userselect");
                } else if(StringUtils.startsWithIgnoreCase(column.getName(), "office_id")) {
                    column.setJavaType(Office.class.getName());
                    column.setJavaField(column.getJavaField().replaceAll("Id", ".id|name"));
                    column.setShowType("officeselect");
                } else if(StringUtils.startsWithIgnoreCase(column.getName(), "area_id")) {
                    column.setJavaType(Area.class.getName());
                    column.setJavaField(column.getJavaField().replaceAll("Id", ".id|name"));
                    column.setShowType("areaselect");
                } else {
                    if(!StringUtils.startsWithIgnoreCase(column.getName(), "create_by") && !StringUtils.startsWithIgnoreCase(column.getName(), "update_by")) {
                        label144: {
                            if(!StringUtils.startsWithIgnoreCase(column.getName(), "create_date") && !StringUtils.startsWithIgnoreCase(column.getName(), "update_date")) {
                                if(!StringUtils.equalsIgnoreCase(column.getName(), "remarks") && !StringUtils.equalsIgnoreCase(column.getName(), "content")) {
                                    if(StringUtils.equalsIgnoreCase(column.getName(), "parent_id")) {
                                        column.setJavaType("This");
                                        column.setJavaField("parent.id|name");
                                        column.setShowType("treeselect");
                                        continue;
                                    }

                                    if(StringUtils.equalsIgnoreCase(column.getName(), "parent_ids")) {
                                        column.setShowType("input");
                                        column.setQueryType("like");
                                        continue;
                                    }

                                    if(StringUtils.equalsIgnoreCase(column.getName(), "del_flag")) {
                                        column.setShowType("radiobox");
                                        column.setDictType("del_flag");
                                        continue;
                                    }
                                    break label144;
                                }

                                column.setShowType("textarea");
                                continue;
                            }

                            column.setShowType("dateselect");
                            continue;
                        }
                    } else {
                        column.setJavaType(User.class.getName());
                        column.setJavaField(column.getJavaField() + ".id");
                    }

                    column.setShowType("input");
                }
            }
        }
    }

    public static String a() {
        try {
            File e;
            if((e = (new DefaultResourceLoader()).getResource("").getFile()) != null) {
                return e.getAbsolutePath() + File.separator + StringUtils.replaceEach(com.root.tenant.modules.gen.util.a.class.getName(), new String[]{"util." + com.root.tenant.modules.gen.util.a.class.getSimpleName(), "."}, new String[]{"template", File.separator});
            }
        } catch (Exception var1) {
            d.error("{}", var1);
        }

        return "";
    }

    private static <T> T a(String e, Class<?> clazz) {
        try {
            e = "/templates/modules/gen/" + e;
            InputStream e1 = (new ClassPathResource(e)).getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(e1, "UTF-8"));
            StringBuilder sb = new StringBuilder();

            String line;
            while((line = br.readLine()) != null) {
                sb.append(line).append("\r\n");
            }

            if(e1 != null) {
                e1.close();
            }

            br.close();
            return (T) JaxbMapper.fromXml(sb.toString(), clazz);
        } catch (IOException var5) {
            d.warn("Error file convert: {}", var5.getMessage());
            return null;
        }
    }

    public static GenConfig b() {
        return (GenConfig)a("config.xml", GenConfig.class);
    }

    public static List<GenTemplate> a(GenConfig config, String template, boolean isChildTable) {
        List templateList = (List)Lists.newArrayList();
        if(config != null && config.getCategoryList() != null && template != null) {
            Iterator var5 = config.getCategoryList().iterator();

            while(var5.hasNext()) {
                GenCategory e = (GenCategory)var5.next();
                if(template.equals(e.getValue())) {
                    List template1;
                    if(!isChildTable) {
                        template1 = e.getTemplate();
                    } else {
                        template1 = e.getChildTableTemplate();
                    }

                    if(template1 != null) {
                        Iterator isChildTable1 = template1.iterator();

                        while(isChildTable1.hasNext()) {
                            if(StringUtils.startsWith(template = (String)isChildTable1.next(), GenCategory.CATEGORY_REF)) {
                                templateList.addAll(a(config, StringUtils.replace(template, GenCategory.CATEGORY_REF, ""), false));
                            } else {
                                GenTemplate template2;
                                if((template2 = (GenTemplate)a(template, GenTemplate.class)) != null) {
                                    templateList.add(template2);
                                }
                            }
                        }
                    }
                    break;
                }
            }
        }

        return templateList;
    }

    public static Map<String, Object> a(GenScheme genScheme) {
        Map model;
        (model = (Map)Maps.newHashMap()).put("packageName", StringUtils.lowerCase(genScheme.getPackageName()));
        model.put("lastPackageName", StringUtils.substringAfterLast((String)model.get("packageName"), "."));
        model.put("moduleName", StringUtils.lowerCase(genScheme.getModuleName()));
        model.put("subModuleName", StringUtils.lowerCase(genScheme.getSubModuleName()));
        model.put("className", StringUtils.uncapitalize(genScheme.getGenTable().getClassName()));
        model.put("ClassName", StringUtils.capitalize(genScheme.getGenTable().getClassName()));
        model.put("functionName", genScheme.getFunctionName());
        model.put("functionNameSimple", genScheme.getFunctionNameSimple());
        model.put("functionAuthor", StringUtils.isNotBlank(genScheme.getFunctionAuthor())?genScheme.getFunctionAuthor():UserUtils.getUser().getName());
        model.put("functionVersion", DateUtils.getDate());
        model.put("urlPrefix", model.get("moduleName") + (StringUtils.isNotBlank(genScheme.getSubModuleName())?"/" + StringUtils.lowerCase(genScheme.getSubModuleName()):"") + "/" + model.get("className"));
        model.put("viewPrefix", model.get("urlPrefix"));
        model.put("permissionPrefix", model.get("moduleName") + (StringUtils.isNotBlank(genScheme.getSubModuleName())?":" + StringUtils.lowerCase(genScheme.getSubModuleName()):"") + ":" + model.get("className"));
        model.put("dbType", Global.getConfig("jdbc.type"));
        model.put("table", genScheme.getGenTable());
        return model;
    }

    public static String a(GenTemplate content, Map<String, Object> model, boolean fileName) {
        String fileName1 = Global.getProjectPath() + File.separator + StringUtils.replaceEach(FreeMarkers.renderString(content.getFilePath() + "/", model), new String[]{"//", "/", "."}, new String[]{File.separator, File.separator, File.separator}) + FreeMarkers.renderString(content.getFileName(), model);
        d.debug(" fileName === " + fileName1);
        String content1 = FreeMarkers.renderString(StringUtils.trimToEmpty(content.getContent()), model);
        d.debug(" content === \r\n" + content1);
        FileUtils.deleteFile(fileName1);
        if(FileUtils.createFile(fileName1)) {
            FileUtils.writeToFile(fileName1, content1, true);
            d.debug(" file create === " + fileName1);
            return "生成成功：" + fileName1 + "<br/>";
        } else {
            d.debug(" file extents === " + fileName1);
            return "文件已存在：" + fileName1 + "<br/>";
        }
    }

    private static void d() {
        try {
            GenConfig config = b();
            System.out.println(config);
            System.out.println(JaxbMapper.toXml(config));
        } catch (Exception var1) {
            var1.printStackTrace();
        }
    }

    private static String a(Date date, String str) {
        return (new SimpleDateFormat(str)).format(date);
    }

    public static synchronized String c() {
        String date;
        try {
            Locale df1 = Locale.CHINA;
            date = "yyyy-MM-dd";
            SimpleDateFormat df2 = new SimpleDateFormat(date, df1);
            new Date();
            df2.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            HttpURLConnection date1;
            (date1 = (HttpURLConnection)(new URL("http://open.baidu.com/special/time/")).openConnection()).setReadTimeout(2000);
            date1.setConnectTimeout(2000);
            date1.connect();
            long ld = date1.getDate();
            Date date2 = new Date(ld);
            return df2.format(date2);
        } catch (UnknownHostException var8) {
            var8.printStackTrace();
        } catch (IOException var9) {
            var9.printStackTrace();
        } catch (Exception var10) {
            var10.printStackTrace();
        }

        Date var10000 = new Date();
        date = "yyyy-MM-dd";
        Date df = var10000;
        return (new SimpleDateFormat(date)).format(df);
    }

    private static String e() {
        String result = "";

        try {
            File input;
            (input = File.createTempFile("realhowto", ".vbs")).deleteOnExit();
            FileWriter line = new FileWriter(input);
            String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\nSet colItems = objWMIService.ExecQuery _ \n   (\"Select * from Win32_BaseBoard\") \nFor Each objItem in colItems \n    Wscript.Echo objItem.SerialNumber \n    exit for  \' do the first cpu only! \nNext \n";
            line.write(vbs);
            line.close();
            Process input1 = Runtime.getRuntime().exec("cscript //NoLogo " + input.getPath());

            BufferedReader input2;
            String line1;
            for(input2 = new BufferedReader(new InputStreamReader(input1.getInputStream())); (line1 = input2.readLine()) != null; result = result + line1) {
                ;
            }

            input2.close();
        } catch (Exception var4) {
            ;
        }

        return result.trim();
    }

    private static String c(String input) {
        String result = "";

        try {
            File line;
            (line = File.createTempFile("realhowto", ".vbs")).deleteOnExit();
            FileWriter fw = new FileWriter(line);
            input = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\nSet colDrives = objFSO.Drives\nSet objDrive = colDrives.item(\"" + input + "\")\nWscript.Echo objDrive.SerialNumber";
            fw.write(input);
            fw.close();
            Process input1 = Runtime.getRuntime().exec("cscript //NoLogo " + line.getPath());

            BufferedReader input2;
            String line1;
            for(input2 = new BufferedReader(new InputStreamReader(input1.getInputStream())); (line1 = input2.readLine()) != null; result = result + line1) {
                ;
            }

            input2.close();
        } catch (Exception var4) {
            ;
        }

        return result.trim();
    }

    private static String f() {
        String result = "";

        try {
            File file;
            (file = File.createTempFile("tmp", ".vbs")).deleteOnExit();
            FileWriter input = new FileWriter(file);
            String line = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\nSet colItems = objWMIService.ExecQuery _ \n   (\"Select * from Win32_Processor\") \nFor Each objItem in colItems \n    Wscript.Echo objItem.ProcessorId \n    exit for  \' do the first cpu only! \nNext \n";
            input.write(line);
            input.close();
            Process input1 = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());

            BufferedReader input2;
            for(input2 = new BufferedReader(new InputStreamReader(input1.getInputStream())); (line = input2.readLine()) != null; result = result + line) {
                ;
            }

            input2.close();
            file.delete();
        } catch (Exception var4) {
            ;
        }

        return result.trim();
    }

    private static String g() {
        String result = "";

        try {
            Process input = Runtime.getRuntime().exec("ipconfig /all");
            InputStreamReader input1 = new InputStreamReader(input.getInputStream());
            LineNumberReader input2 = new LineNumberReader(input1);

            String line;
            while((line = input2.readLine()) != null) {
                if(line.indexOf("Physical Address") > 0) {
                    result = line.substring(line.indexOf("-") - 2);
                }
            }
        } catch (IOException var3) {
            ;
        }

        return result;
    }

    private static String h() {
        return f() + e() + c("c") + g();
    }
}
