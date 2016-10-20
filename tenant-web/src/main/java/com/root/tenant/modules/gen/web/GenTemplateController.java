//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.root.tenant.modules.gen.web;

import com.root.tenant.common.persistence.Page;
import com.root.tenant.common.utils.StringUtils;
import com.root.tenant.common.web.BaseController;
import com.root.tenant.modules.gen.entity.GenTemplate;
import com.root.tenant.modules.gen.service.GenTemplateService;
import com.root.tenant.modules.sys.entity.User;
import com.root.tenant.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping({"${adminPath}/gen/genTemplate"})
public class GenTemplateController extends BaseController {
    @Autowired
    private GenTemplateService a;

    public GenTemplateController() {
    }

    @ModelAttribute
    private GenTemplate a(@RequestParam(required = false) String id) {
        return StringUtils.isNotBlank(id)?a.get(id):new GenTemplate();
    }

    @RequiresPermissions({"gen:genTemplate:view"})
    @RequestMapping({"list", ""})
    private String a(GenTemplate page, HttpServletRequest request, HttpServletResponse response, Model model) {
        User user;
        if(!(user = UserUtils.getUser()).isAdmin()) {
            page.setCreateBy(user);
        }

        Page page1 = a.find(new Page(request, response), page);
        model.addAttribute("page", page1);
        return "modules/gen/genTemplateList";
    }

    @RequiresPermissions({"gen:genTemplate:view"})
    @RequestMapping({"form"})
    private static String a(GenTemplate genTemplate, Model model) {
        model.addAttribute("genTemplate", genTemplate);
        return "modules/gen/genTemplateForm";
    }

    @RequiresPermissions({"gen:genTemplate:edit"})
    @RequestMapping({"save"})
    private String a(GenTemplate genTemplate, Model model, RedirectAttributes redirectAttributes) {
        if(!this.beanValidator(model, genTemplate, new Class[0])) {
            model.addAttribute("genTemplate", genTemplate);
            return "modules/gen/genTemplateForm";
        } else {
            a.save(genTemplate);
            this.addMessage(redirectAttributes, new String[]{"保存代码模板\'" + genTemplate.getName() + "\'成功"});
            return "redirect:" + this.adminPath + "/gen/genTemplate/?repage";
        }
    }

    @RequiresPermissions({"gen:genTemplate:edit"})
    @RequestMapping({"delete"})
    private String a(GenTemplate genTemplate, RedirectAttributes redirectAttributes) {
        a.delete(genTemplate);
        this.addMessage(redirectAttributes, new String[]{"删除代码模板成功"});
        return "redirect:" + this.adminPath + "/gen/genTemplate/?repage";
    }
}
