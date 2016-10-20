//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.apache.jsp.tag.web.sys;

import com.root.tenant.common.config.Global;
import org.apache.jasper.el.ELContextWrapper;
import org.apache.jasper.el.JspValueExpression;
import org.apache.jasper.runtime.*;
import org.apache.taglibs.standard.tag.rt.core.SetTag;
import org.apache.tomcat.InstanceManager;

import javax.el.ExpressionFactory;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagAdapter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

public final class iconselect_tag extends SimpleTagSupport implements JspSourceDependent, JspSourceImports {
    private static ProtectedFunctionMapper _jspx_fnmap_0 = ProtectedFunctionMapper.getMapForFunction("fns:getAdminPath", Global.class, "getAdminPath", new Class[0]);
    private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();
    private static Map<String, Long> _jspx_dependants = new HashMap(11);
    private static final Set<String> _jspx_imports_packages;
    private static final Set<String> _jspx_imports_classes;
    private JspContext jspContext;
    private Writer _jspx_sout;
    private TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;
    private volatile ExpressionFactory _el_expressionfactory;
    private volatile InstanceManager _jsp_instancemanager;
    private String id;
    private String name;
    private String value;

    static {
        _jspx_dependants.put("jar:file:/D:/workspace-all/.metadata/.plugins/org.eclipse.wst.server.core/tmp2/wtpwebapps/root/tenant/WEB-INF/lib/spring-webmvc-4.0.8.RELEASE.jar!/META-INF/spring-form.tld", Long.valueOf(1415629298000L));
        _jspx_dependants.put("/WEB-INF/tlds/menu.tld", Long.valueOf(1462184011815L));
        _jspx_dependants.put("/WEB-INF/tlds/echarts.tld", Long.valueOf(1464506993423L));
        _jspx_dependants.put("/WEB-INF/lib/jstl-1.2.jar", Long.valueOf(1462184010615L));
        _jspx_dependants.put("/WEB-INF/lib/spring-webmvc-4.0.8.RELEASE.jar", Long.valueOf(1462184008765L));
        _jspx_dependants.put("jar:file:/D:/workspace-all/.metadata/.plugins/org.eclipse.wst.server.core/tmp2/wtpwebapps/root/tenant/WEB-INF/lib/jstl-1.2.jar!/META-INF/fmt.tld", Long.valueOf(1153356282000L));
        _jspx_dependants.put("/webpage/include/taglib.jsp", Long.valueOf(1466525160094L));
        _jspx_dependants.put("jar:file:/D:/workspace-all/.metadata/.plugins/org.eclipse.wst.server.core/tmp2/wtpwebapps/root/tenant/WEB-INF/lib/jstl-1.2.jar!/META-INF/c.tld", Long.valueOf(1153356282000L));
        _jspx_dependants.put("jar:file:/D:/workspace-all/.metadata/.plugins/org.eclipse.wst.server.core/tmp2/wtpwebapps/root/tenant/WEB-INF/lib/jstl-1.2.jar!/META-INF/fn.tld", Long.valueOf(1153356282000L));
        _jspx_dependants.put("/WEB-INF/tlds/fns.tld", Long.valueOf(1461504589115L));
        _jspx_dependants.put("/WEB-INF/tlds/shiros.tld", Long.valueOf(1462184011835L));
        _jspx_imports_packages = new HashSet();
        _jspx_imports_packages.add("javax.servlet");
        _jspx_imports_packages.add("javax.servlet.http");
        _jspx_imports_packages.add("javax.servlet.jsp");
        _jspx_imports_classes = null;
    }

    public iconselect_tag() {
    }

    public void setJspContext(JspContext ctx) {
        super.setJspContext(ctx);
        Object _jspx_nested = null;
        Object _jspx_at_begin = null;
        Object _jspx_at_end = null;
        this.jspContext = new JspContextWrapper(this, ctx, (ArrayList)_jspx_nested, (ArrayList)_jspx_at_begin, (ArrayList)_jspx_at_end, (Map)null);
    }

    public JspContext getJspContext() {
        return this.jspContext;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
        this.jspContext.setAttribute("id", id);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
        this.jspContext.setAttribute("name", name);
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
        this.jspContext.setAttribute("value", value);
    }

    public Map<String, Long> getDependants() {
        return _jspx_dependants;
    }

    public Set<String> getPackageImports() {
        return _jspx_imports_packages;
    }

    public Set<String> getClassImports() {
        return _jspx_imports_classes;
    }

    public ExpressionFactory _jsp_getExpressionFactory() {
        return this._el_expressionfactory;
    }

    public InstanceManager _jsp_getInstanceManager() {
        return this._jsp_instancemanager;
    }

    private void _jspInit(ServletConfig config) {
        this._005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = TagHandlerPool.getTagHandlerPool(config);
        this._el_expressionfactory = _jspxFactory.getJspApplicationContext(config.getServletContext()).getExpressionFactory();
        this._jsp_instancemanager = InstanceManagerFactory.getInstanceManager(config);
    }

    public void _jspDestroy() {
        this._005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    }

    public void doTag() throws JspException, IOException {
        PageContext _jspx_page_context = (PageContext)this.jspContext;
        HttpServletRequest var10000 = (HttpServletRequest)_jspx_page_context.getRequest();
        HttpServletResponse var10 = (HttpServletResponse)_jspx_page_context.getResponse();
        _jspx_page_context.getSession();
        _jspx_page_context.getServletContext();
        ServletConfig config = _jspx_page_context.getServletConfig();
        JspWriter out = this.jspContext.getOut();
        this._jspInit(config);
        this.jspContext.getELContext().putContext(JspContext.class, this.jspContext);
        if(this.getId() != null) {
            _jspx_page_context.setAttribute("id", this.getId());
        }

        if(this.getName() != null) {
            _jspx_page_context.setAttribute("name", this.getName());
        }

        if(this.getValue() != null) {
            _jspx_page_context.setAttribute("value", this.getValue());
        }

        try {
            out.write(10);
            out.write("\n");
            out.write("\n");
            out.write("\n");
            out.write("\n");
            out.write("\n");
            out.write("\n");
            out.write("\n");
            out.write("\n");
            out.write("\n");
            out.write("\n");
            if(this._jspx_meth_c_005fset_005f0(_jspx_page_context)) {
                return;
            }

            out.write(10);
            if(!this._jspx_meth_c_005fset_005f1(_jspx_page_context)) {
                out.write("\n");
                out.write("\n");
                out.write("\n");
                out.write("\n");
                out.write("<i id=\"");
                out.write((String)PageContextImpl.proprietaryEvaluate("${id}", String.class, (PageContext)this.getJspContext(), (ProtectedFunctionMapper)null));
                out.write("Icon\" class=\"icon-");
                out.write((String)PageContextImpl.proprietaryEvaluate("${not empty value?value:\' hide\'}", String.class, (PageContext)this.getJspContext(), (ProtectedFunctionMapper)null));
                out.write("\"></i>&nbsp;<span id=\"");
                out.write((String)PageContextImpl.proprietaryEvaluate("${id}", String.class, (PageContext)this.getJspContext(), (ProtectedFunctionMapper)null));
                out.write("IconLabel\">");
                out.write((String)PageContextImpl.proprietaryEvaluate("${not empty value?value:\'无\'}", String.class, (PageContext)this.getJspContext(), (ProtectedFunctionMapper)null));
                out.write("</span>&nbsp;\n");
                out.write("<input id=\"");
                out.write((String)PageContextImpl.proprietaryEvaluate("${id}", String.class, (PageContext)this.getJspContext(), (ProtectedFunctionMapper)null));
                out.write("\" name=\"");
                out.write((String)PageContextImpl.proprietaryEvaluate("${name}", String.class, (PageContext)this.getJspContext(), (ProtectedFunctionMapper)null));
                out.write("\" type=\"hidden\" value=\"");
                out.write((String)PageContextImpl.proprietaryEvaluate("${value}", String.class, (PageContext)this.getJspContext(), (ProtectedFunctionMapper)null));
                out.write("\"/><a id=\"");
                out.write((String)PageContextImpl.proprietaryEvaluate("${id}", String.class, (PageContext)this.getJspContext(), (ProtectedFunctionMapper)null));
                out.write("Button\" href=\"javascript:\" class=\"btn btn-primary\">选择</a>&nbsp;&nbsp;\n");
                out.write("<input id=\"");
                out.write((String)PageContextImpl.proprietaryEvaluate("${id}", String.class, (PageContext)this.getJspContext(), (ProtectedFunctionMapper)null));
                out.write("clear\" class=\"btn btn-default\" type=\"button\" value=\"清除\" onclick=\"clear()\"/>\n");
                out.write("<script type=\"text/javascript\">\n");
                out.write("\t$(\"#");
                out.write((String)PageContextImpl.proprietaryEvaluate("${id}", String.class, (PageContext)this.getJspContext(), (ProtectedFunctionMapper)null));
                out.write("Button\").click(function(){\n");
                out.write("\t\n");
                out.write("\t\ttop.layer.open({\n");
                out.write("\t\t\ttype: 2, \n");
                out.write("\t\t\ttitle:\"选择图标\",\n");
                out.write("\t\t\tarea: [\'700px\',  $(top.document).height()-180+\"px\"],\n");
                out.write("\t\t    content: \'");
                out.write((String)PageContextImpl.proprietaryEvaluate("${ctx}", String.class, (PageContext)this.getJspContext(), (ProtectedFunctionMapper)null));
                out.write("/tag/iconselect?value=\"+$(\"#");
                out.write((String)PageContextImpl.proprietaryEvaluate("${id}", String.class, (PageContext)this.getJspContext(), (ProtectedFunctionMapper)null));
                out.write("\").val()\',\n");
                out.write("\t\t    btn: [\'确定\', \'关闭\'],\n");
                out.write("\t\t    yes: function(index, layero){ //或者使用btn1\n");
                out.write("\t\t    \tvar icon = layero.find(\"iframe\")[0].contentWindow.$(\"#icon\").val();\n");
                out.write("            \t$(\"#");
                out.write((String)PageContextImpl.proprietaryEvaluate("${id}", String.class, (PageContext)this.getJspContext(), (ProtectedFunctionMapper)null));
                out.write("Icon\").attr(\"class\", \"fa \"+icon);\n");
                out.write("                $(\"#");
                out.write((String)PageContextImpl.proprietaryEvaluate("${id}", String.class, (PageContext)this.getJspContext(), (ProtectedFunctionMapper)null));
                out.write("IconLabel\").text(icon);\n");
                out.write("                $(\"#");
                out.write((String)PageContextImpl.proprietaryEvaluate("${id}", String.class, (PageContext)this.getJspContext(), (ProtectedFunctionMapper)null));
                out.write("\").val(icon);\n");
                out.write("                top.layer.close(index);\n");
                out.write("\t\t    },cancel: function(index){ //或者使用btn2\n");
                out.write("\t\t    \t\n");
                out.write("\t\t    }\n");
                out.write("\t\t});\n");
                out.write("\t});\n");
                out.write("\t$(\"#");
                out.write((String)PageContextImpl.proprietaryEvaluate("${id}", String.class, (PageContext)this.getJspContext(), (ProtectedFunctionMapper)null));
                out.write("clear\").click(function(){\n");
                out.write("\t\t $(\"#");
                out.write((String)PageContextImpl.proprietaryEvaluate("${id}", String.class, (PageContext)this.getJspContext(), (ProtectedFunctionMapper)null));
                out.write("Icon\").attr(\"class\", \"icon- hide\");\n");
                out.write("         $(\"#");
                out.write((String)PageContextImpl.proprietaryEvaluate("${id}", String.class, (PageContext)this.getJspContext(), (ProtectedFunctionMapper)null));
                out.write("IconLabel\").text(\"无\");\n");
                out.write("         $(\"#");
                out.write((String)PageContextImpl.proprietaryEvaluate("${id}", String.class, (PageContext)this.getJspContext(), (ProtectedFunctionMapper)null));
                out.write("\").val(\"\");\n");
                out.write("\n");
                out.write("\t});\n");
                out.write("</script>");
                return;
            }
        } catch (Throwable var8) {
            if(var8 instanceof SkipPageException) {
                throw (SkipPageException)var8;
            }

            if(var8 instanceof IOException) {
                throw (IOException)var8;
            }

            if(var8 instanceof IllegalStateException) {
                throw (IllegalStateException)var8;
            }

            if(var8 instanceof JspException) {
                throw (JspException)var8;
            }

            throw new JspException(var8);
        } finally {
            this.jspContext.getELContext().putContext(JspContext.class, super.getJspContext());
            ((JspContextWrapper)this.jspContext).syncEndTagFile();
            this._jspDestroy();
        }

    }

    private boolean _jspx_meth_c_005fset_005f0(PageContext _jspx_page_context) throws Throwable {
        _jspx_page_context.getOut();
        SetTag _jspx_th_c_005fset_005f0 = (SetTag)this._005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(SetTag.class);

        try {
            _jspx_th_c_005fset_005f0.setPageContext(_jspx_page_context);
            _jspx_th_c_005fset_005f0.setParent(new TagAdapter(this));
            _jspx_th_c_005fset_005f0.setVar("ctx");
            _jspx_th_c_005fset_005f0.setValue((new JspValueExpression("/webpage/include/taglib.jsp(11,0) \'${pageContext.request.contextPath}${fns:getAdminPath()}\'", this._jsp_getExpressionFactory().createValueExpression(new ELContextWrapper(this.getJspContext().getELContext(), _jspx_fnmap_0), "${pageContext.request.contextPath}${fns:getAdminPath()}", Object.class))).getValue(this.getJspContext().getELContext()));
            _jspx_th_c_005fset_005f0.doStartTag();
            if(_jspx_th_c_005fset_005f0.doEndTag() == 5) {
                throw new SkipPageException();
            }
        } finally {
            this._005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
        }

        return false;
    }

    private boolean _jspx_meth_c_005fset_005f1(PageContext _jspx_page_context) throws Throwable {
        _jspx_page_context.getOut();
        SetTag _jspx_th_c_005fset_005f1 = (SetTag)this._005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(SetTag.class);

        try {
            _jspx_th_c_005fset_005f1.setPageContext(_jspx_page_context);
            _jspx_th_c_005fset_005f1.setParent(new TagAdapter(this));
            _jspx_th_c_005fset_005f1.setVar("ctxStatic");
            _jspx_th_c_005fset_005f1.setValue((new JspValueExpression("/webpage/include/taglib.jsp(12,0) \'${pageContext.request.contextPath}/static\'", this._jsp_getExpressionFactory().createValueExpression(this.getJspContext().getELContext(), "${pageContext.request.contextPath}/static", Object.class))).getValue(this.getJspContext().getELContext()));
            _jspx_th_c_005fset_005f1.doStartTag();
            if(_jspx_th_c_005fset_005f1.doEndTag() == 5) {
                throw new SkipPageException();
            }
        } finally {
            this._005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f1);
        }

        return false;
    }
}
