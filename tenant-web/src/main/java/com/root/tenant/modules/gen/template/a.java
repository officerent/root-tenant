//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.root.tenant.modules.gen.template;

import freemarker.template.Configuration;

import java.io.StringWriter;
import java.util.Map;

public class a {
    private static Configuration a;

    static {
        (a = new Configuration()).setClassForTemplateLoading(com.root.tenant.modules.gen.template.a.class, "/");
    }

    public a() {
    }

    public static String a(String tplName, String encoding, Map<String, Object> paras) {
        try {
            StringWriter e = new StringWriter();
            a.getTemplate(tplName, encoding).process(paras, e);
            return e.toString();
        } catch (Exception var4) {
            var4.printStackTrace();
            return var4.toString();
        }
    }

    public final String a(String tplName, Map<String, Object> paras) {
        return a(tplName, "utf-8", paras);
    }
}
