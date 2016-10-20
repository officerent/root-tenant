//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.root.tenant.modules.gen.web;

import com.google.common.collect.Lists;
import com.root.tenant.common.persistence.Page;
import com.root.tenant.common.utils.StringUtils;
import com.root.tenant.common.web.BaseController;
import com.root.tenant.modules.gen.entity.GenScheme;
import com.root.tenant.modules.gen.service.GenSchemeService;
import com.root.tenant.modules.gen.service.GenTableService;
import com.root.tenant.modules.gen.util.a;
import com.root.tenant.modules.sys.entity.Menu;
import com.root.tenant.modules.sys.entity.User;
import com.root.tenant.modules.sys.service.SystemService;
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
import java.util.List;

@Controller
@RequestMapping({"${adminPath}/gen/genScheme"})
public class GenSchemeController extends BaseController {
    @Autowired
    public GenSchemeService genSchemeService;
    @Autowired
    public GenTableService genTableService;
    @Autowired
    public SystemService systemService;

    public GenSchemeController() {
    }

    @ModelAttribute
    private GenScheme a(@RequestParam(required = false) String id) {
        return StringUtils.isNotBlank(id)?this.genSchemeService.get(id):new GenScheme();
    }

    @RequiresPermissions({"gen:genScheme:view"})
    @RequestMapping({"list", ""})
    private String a(GenScheme page, HttpServletRequest request, HttpServletResponse response, Model model) {
        User user;
        if(!(user = UserUtils.getUser()).isAdmin()) {
            page.setCreateBy(user);
        }

        Page page1 = this.genSchemeService.find(new Page(request, response), page);
        model.addAttribute("page", page1);
        return "modules/gen/genSchemeList";
    }

    @RequiresPermissions({"gen:genScheme:view"})
    @RequestMapping({"form"})
    private String a(GenScheme genScheme, Model model) {
        if(StringUtils.isBlank(genScheme.getPackageName())) {
            genScheme.setPackageName("com.root.tenant.modules");
        }

        model.addAttribute("genScheme", genScheme);
        model.addAttribute("config", a.b());
        model.addAttribute("tableList", this.genTableService.findAll());
        return "modules/gen/genSchemeForm";
    }

    @RequiresPermissions({"gen:genScheme:edit"})
    @RequestMapping({"save"})
    private String a(GenScheme genScheme, Model result, RedirectAttributes redirectAttributes) {
        if(!this.beanValidator(result, genScheme, new Class[0])) {
            if(StringUtils.isBlank(genScheme.getPackageName())) {
                genScheme.setPackageName("com.root.tenant.modules");
            }

            result.addAttribute("genScheme", genScheme);
            result.addAttribute("config", a.b());
            result.addAttribute("tableList", this.genTableService.findAll());
            return "modules/gen/genSchemeForm";
        } else {
            String result1 = this.genSchemeService.save(genScheme);
            this.addMessage(redirectAttributes, new String[]{"操作生成方案\'" + genScheme.getName() + "\'成功<br/>" + result1});
            return "redirect:" + this.adminPath + "/gen/genScheme/?repage";
        }
    }

    @RequiresPermissions({"gen:genScheme:edit"})
    @RequestMapping({"delete"})
    private String a(GenScheme genScheme, RedirectAttributes redirectAttributes) {
        this.genSchemeService.delete(genScheme);
        this.addMessage(redirectAttributes, new String[]{"删除生成方案成功"});
        return "redirect:" + this.adminPath + "/gen/genScheme/?repage";
    }

    @RequestMapping({"menuForm"})
    private String a(String gen_table_id, Menu menu, Model model) {
        if(menu.getParent() == null || menu.getParent().getId() == null) {
            menu.setParent(new Menu(Menu.getRootId()));
        }

        menu.setParent(this.systemService.getMenu(menu.getParent().getId()));
        if(StringUtils.isBlank(menu.getId())) {
            List genScheme = (List)Lists.newArrayList();
            List sourcelist = this.systemService.findAllMenu();
            Menu.sortList(genScheme, sourcelist, menu.getParentId(), false);
            if(genScheme.size() > 0) {
                menu.setSort(Integer.valueOf(((Menu)genScheme.get(genScheme.size() - 1)).getSort().intValue() + 30));
            }
        }

        GenScheme genScheme1;
        if((genScheme1 = this.genSchemeService.findUniqueByProperty("gen_table_id", gen_table_id)) != null) {
            menu.setName(genScheme1.getFunctionName());
        }

        model.addAttribute("menu", menu);
        model.addAttribute("gen_table_id", gen_table_id);
        return "modules/gen/genMenuForm";
    }

    @RequestMapping({"createMenu"})
    private String a(String gen_table_id, Menu menu, RedirectAttributes redirectAttributes) {
        GenScheme genScheme;
        if((genScheme = this.genSchemeService.findUniqueByProperty("gen_table_id", gen_table_id)) == null) {
            this.addMessage(redirectAttributes, new String[]{"创建菜单失败,请先生成代码!"});
            return "redirect:" + this.adminPath + "/gen/genTable/?repage";
        } else {
            genScheme.setGenTable(this.genTableService.get(gen_table_id));
            this.genSchemeService.createMenu(genScheme, menu);
            this.addMessage(redirectAttributes, new String[]{"创建菜单\'" + genScheme.getFunctionName() + "\'成功<br/>"});
            return "redirect:" + this.adminPath + "/gen/genTable/?repage";
        }
    }
}
