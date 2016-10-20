//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.root.tenant.modules.gen.web;

import com.root.tenant.common.utils.StringUtils;
import com.root.tenant.common.web.BaseController;
import com.root.tenant.modules.gen.entity.GenScheme;
import com.root.tenant.modules.gen.service.CgAutoListService;
import com.root.tenant.modules.gen.service.GenSchemeService;
import com.root.tenant.modules.gen.service.GenTableService;
import com.root.tenant.modules.gen.template.a;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping({"${adminPath}/gen/cgAutoList"})
public class CgAutoListController extends BaseController {
    private static Logger a = Logger.getLogger(com.root.tenant.modules.gen.web.CgAutoListController.class);
    @Autowired
    private GenSchemeService b;
    @Autowired
    private GenTableService c;
    @Autowired
    private CgAutoListService d;

    public CgAutoListController() {
    }

    @ModelAttribute
    private GenScheme a(@RequestParam(required = false) String id) {
        return StringUtils.isNotBlank(id)?b.get(id):new GenScheme();
    }

    @RequestMapping({"list"})
    private void a(GenScheme genScheme, HttpServletResponse response) {
        long start = System.currentTimeMillis();
        new a();
        com.root.tenant.modules.gen.util.a.a(genScheme);
        String html = b.generateCode(genScheme);
        d.generateListCode(genScheme);

        try {
            response.setContentType("text/html");
            response.setHeader("Cache-Control", "no-store");
            PrintWriter end;
            (end = response.getWriter()).println(html);
            end.flush();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

        long end1 = System.currentTimeMillis();
        a.debug("动态列表生成耗时：" + (end1 - start) + " ms");
    }

    @RequestMapping({"test", ""})
    private static ModelAndView a() {
        return new ModelAndView("/com/root/tenant/modules/gen/template/viewList");
    }
}
