//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.apache.jsp.tag.web.sys;

import com.root.tenant.common.config.Global;
import org.apache.jasper.el.ELContextWrapper;
import org.apache.jasper.el.JspValueExpression;
import org.apache.jasper.runtime.*;
import org.apache.taglibs.standard.functions.Functions;
import org.apache.taglibs.standard.tag.rt.core.IfTag;
import org.apache.taglibs.standard.tag.rt.core.SetTag;
import org.apache.tomcat.InstanceManager;

import javax.el.ExpressionFactory;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagAdapter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

public final class message_tag extends SimpleTagSupport implements JspSourceDependent, JspSourceImports {
    private static ProtectedFunctionMapper _jspx_fnmap_0 = ProtectedFunctionMapper.getMapForFunction("fns:getAdminPath", Global.class, "getAdminPath", new Class[0]);
    private static ProtectedFunctionMapper _jspx_fnmap_1 = ProtectedFunctionMapper.getMapForFunction("fn:indexOf", Functions.class, "indexOf", new Class[]{String.class, String.class});
    private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();
    private static Map<String, Long> _jspx_dependants = new HashMap(11);
    private static final Set<String> _jspx_imports_packages;
    private static final Set<String> _jspx_imports_classes;
    private JspContext jspContext;
    private Writer _jspx_sout;
    private TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;
    private TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;
    private volatile ExpressionFactory _el_expressionfactory;
    private volatile InstanceManager _jsp_instancemanager;
    private String hideType;
    private String content;
    private String type;

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

    public message_tag() {
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

    public String getHideType() {
        return this.hideType;
    }

    public void setHideType(String hideType) {
        this.hideType = hideType;
        this.jspContext.setAttribute("hideType", hideType);
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
        this.jspContext.setAttribute("content", content);
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
        this.jspContext.setAttribute("type", type);
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
        this._005fjspx_005ftagPool_005fc_005fif_0026_005ftest = TagHandlerPool.getTagHandlerPool(config);
        this._el_expressionfactory = _jspxFactory.getJspApplicationContext(config.getServletContext()).getExpressionFactory();
        this._jsp_instancemanager = InstanceManagerFactory.getInstanceManager(config);
    }

    public void _jspDestroy() {
        this._005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
        this._005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
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
        if(this.getHideType() != null) {
            _jspx_page_context.setAttribute("hideType", this.getHideType());
        }

        if(this.getContent() != null) {
            _jspx_page_context.setAttribute("content", this.getContent());
        }

        if(this.getType() != null) {
            _jspx_page_context.setAttribute("type", this.getType());
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
            if(this._jspx_meth_c_005fset_005f1(_jspx_page_context)) {
                return;
            }

            out.write("\n");
            out.write("<!-- 0:隐藏tip, 1隐藏box,不设置显示全部 -->\n");
            out.write("\n");
            out.write("\n");
            out.write("<script type=\"text/javascript\">top.$.jBox.closeTip();</script>\n");
            if(this._jspx_meth_c_005fif_005f0(_jspx_page_context)) {
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

    private boolean _jspx_meth_c_005fif_005f0(PageContext _jspx_page_context) throws Throwable {
        JspWriter out = _jspx_page_context.getOut();
        IfTag _jspx_th_c_005fif_005f0 = (IfTag)this._005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(IfTag.class);

        try {
            _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
            _jspx_th_c_005fif_005f0.setParent(new TagAdapter(this));
            _jspx_th_c_005fif_005f0.setTest(((Boolean)PageContextImpl.proprietaryEvaluate("${not empty content}", Boolean.TYPE, (PageContext)this.getJspContext(), (ProtectedFunctionMapper)null)).booleanValue());
            int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
            int evalDoAfterBody;
            if(_jspx_eval_c_005fif_005f0 != 0) {
                do {
                    out.write(10);
                    out.write(9);
                    if(this._jspx_meth_c_005fif_005f1(_jspx_th_c_005fif_005f0, _jspx_page_context)) {
                        return true;
                    }

                    out.write(10);
                    out.write(9);
                    if(this._jspx_meth_c_005fif_005f2(_jspx_th_c_005fif_005f0, _jspx_page_context)) {
                        return true;
                    }

                    out.write(10);
                    out.write(9);
                    if(this._jspx_meth_c_005fif_005f3(_jspx_th_c_005fif_005f0, _jspx_page_context)) {
                        return true;
                    }

                    out.write(32);
                    out.write(10);
                    out.write(9);
                    if(this._jspx_meth_c_005fif_005f4(_jspx_th_c_005fif_005f0, _jspx_page_context)) {
                        return true;
                    }

                    out.write(10);
                    evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
                } while(evalDoAfterBody == 2);
            }

            if(_jspx_th_c_005fif_005f0.doEndTag() != 5) {
                return false;
            } else {
                throw new SkipPageException();
            }
        } finally {
            this._005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
        }
    }

    private boolean _jspx_meth_c_005fif_005f1(JspTag _jspx_th_c_005fif_005f0, PageContext _jspx_page_context) throws Throwable {
        JspWriter out = _jspx_page_context.getOut();
        IfTag _jspx_th_c_005fif_005f1 = (IfTag)this._005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(IfTag.class);

        try {
            _jspx_th_c_005fif_005f1.setPageContext(_jspx_page_context);
            _jspx_th_c_005fif_005f1.setParent((Tag)_jspx_th_c_005fif_005f0);
            _jspx_th_c_005fif_005f1.setTest(((Boolean)PageContextImpl.proprietaryEvaluate("${not empty type}", Boolean.TYPE, (PageContext)this.getJspContext(), (ProtectedFunctionMapper)null)).booleanValue());
            int _jspx_eval_c_005fif_005f1 = _jspx_th_c_005fif_005f1.doStartTag();
            int evalDoAfterBody;
            if(_jspx_eval_c_005fif_005f1 != 0) {
                do {
                    out.write(10);
                    out.write(9);
                    if(this._jspx_meth_c_005fset_005f2(_jspx_th_c_005fif_005f1, _jspx_page_context)) {
                        return true;
                    }

                    evalDoAfterBody = _jspx_th_c_005fif_005f1.doAfterBody();
                } while(evalDoAfterBody == 2);
            }

            if(_jspx_th_c_005fif_005f1.doEndTag() == 5) {
                throw new SkipPageException();
            }
        } finally {
            this._005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
        }

        return false;
    }

    private boolean _jspx_meth_c_005fset_005f2(JspTag _jspx_th_c_005fif_005f1, PageContext _jspx_page_context) throws Throwable {
        _jspx_page_context.getOut();
        SetTag _jspx_th_c_005fset_005f2 = (SetTag)this._005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(SetTag.class);

        try {
            _jspx_th_c_005fset_005f2.setPageContext(_jspx_page_context);
            _jspx_th_c_005fset_005f2.setParent((Tag)_jspx_th_c_005fif_005f1);
            _jspx_th_c_005fset_005f2.setVar("ctype");
            _jspx_th_c_005fset_005f2.setValue((new JspValueExpression("/WEB-INF/tags/sys/message.tag(9,1) \'${type}\'", this._jsp_getExpressionFactory().createValueExpression(this.getJspContext().getELContext(), "${type}", Object.class))).getValue(this.getJspContext().getELContext()));
            _jspx_th_c_005fset_005f2.doStartTag();
            if(_jspx_th_c_005fset_005f2.doEndTag() == 5) {
                throw new SkipPageException();
            }
        } finally {
            this._005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f2);
        }

        return false;
    }

    private boolean _jspx_meth_c_005fif_005f2(JspTag _jspx_th_c_005fif_005f0, PageContext _jspx_page_context) throws Throwable {
        JspWriter out = _jspx_page_context.getOut();
        IfTag _jspx_th_c_005fif_005f2 = (IfTag)this._005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(IfTag.class);

        try {
            _jspx_th_c_005fif_005f2.setPageContext(_jspx_page_context);
            _jspx_th_c_005fif_005f2.setParent((Tag)_jspx_th_c_005fif_005f0);
            _jspx_th_c_005fif_005f2.setTest(((Boolean)PageContextImpl.proprietaryEvaluate("${empty type}", Boolean.TYPE, (PageContext)this.getJspContext(), (ProtectedFunctionMapper)null)).booleanValue());
            int _jspx_eval_c_005fif_005f2 = _jspx_th_c_005fif_005f2.doStartTag();
            int evalDoAfterBody;
            if(_jspx_eval_c_005fif_005f2 != 0) {
                do {
                    out.write(10);
                    out.write(9);
                    if(this._jspx_meth_c_005fset_005f3(_jspx_th_c_005fif_005f2, _jspx_page_context)) {
                        return true;
                    }

                    out.write(10);
                    out.write(9);
                    evalDoAfterBody = _jspx_th_c_005fif_005f2.doAfterBody();
                } while(evalDoAfterBody == 2);
            }

            if(_jspx_th_c_005fif_005f2.doEndTag() == 5) {
                throw new SkipPageException();
            }
        } finally {
            this._005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f2);
        }

        return false;
    }

    private boolean _jspx_meth_c_005fset_005f3(JspTag _jspx_th_c_005fif_005f2, PageContext _jspx_page_context) throws Throwable {
        _jspx_page_context.getOut();
        SetTag _jspx_th_c_005fset_005f3 = (SetTag)this._005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(SetTag.class);

        try {
            _jspx_th_c_005fset_005f3.setPageContext(_jspx_page_context);
            _jspx_th_c_005fset_005f3.setParent((Tag)_jspx_th_c_005fif_005f2);
            _jspx_th_c_005fset_005f3.setVar("ctype");
            _jspx_th_c_005fset_005f3.setValue((new JspValueExpression("/WEB-INF/tags/sys/message.tag(11,1) \'${fn:indexOf(content,\'失败\') eq -1?\'success\':\'danger\'}\'", this._jsp_getExpressionFactory().createValueExpression(new ELContextWrapper(this.getJspContext().getELContext(), _jspx_fnmap_1), "${fn:indexOf(content,\'失败\') eq -1?\'success\':\'danger\'}", Object.class))).getValue(this.getJspContext().getELContext()));
            _jspx_th_c_005fset_005f3.doStartTag();
            if(_jspx_th_c_005fset_005f3.doEndTag() == 5) {
                throw new SkipPageException();
            }
        } finally {
            this._005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f3);
        }

        return false;
    }

    private boolean _jspx_meth_c_005fif_005f3(JspTag _jspx_th_c_005fif_005f0, PageContext _jspx_page_context) throws Throwable {
        JspWriter out = _jspx_page_context.getOut();
        IfTag _jspx_th_c_005fif_005f3 = (IfTag)this._005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(IfTag.class);

        try {
            _jspx_th_c_005fif_005f3.setPageContext(_jspx_page_context);
            _jspx_th_c_005fif_005f3.setParent((Tag)_jspx_th_c_005fif_005f0);
            _jspx_th_c_005fif_005f3.setTest(((Boolean)PageContextImpl.proprietaryEvaluate("${hideType != \'1\'}", Boolean.TYPE, (PageContext)this.getJspContext(), (ProtectedFunctionMapper)null)).booleanValue());
            int _jspx_eval_c_005fif_005f3 = _jspx_th_c_005fif_005f3.doStartTag();
            int evalDoAfterBody;
            if(_jspx_eval_c_005fif_005f3 != 0) {
                do {
                    out.write("\n");
                    out.write("\t\t<div id=\"messageBox\" class=\"alert alert-");
                    out.write((String)PageContextImpl.proprietaryEvaluate("${ctype}", String.class, (PageContext)this.getJspContext(), (ProtectedFunctionMapper)null));
                    out.write("\">\n");
                    out.write("\t\t<button data-dismiss=\"alert\" class=\"close\">×</button>");
                    out.write((String)PageContextImpl.proprietaryEvaluate("${content}", String.class, (PageContext)this.getJspContext(), (ProtectedFunctionMapper)null));
                    out.write("</div>\n");
                    out.write("\t");
                    evalDoAfterBody = _jspx_th_c_005fif_005f3.doAfterBody();
                } while(evalDoAfterBody == 2);
            }

            if(_jspx_th_c_005fif_005f3.doEndTag() == 5) {
                throw new SkipPageException();
            }
        } finally {
            this._005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f3);
        }

        return false;
    }

    private boolean _jspx_meth_c_005fif_005f4(JspTag _jspx_th_c_005fif_005f0, PageContext _jspx_page_context) throws Throwable {
        JspWriter out = _jspx_page_context.getOut();
        IfTag _jspx_th_c_005fif_005f4 = (IfTag)this._005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(IfTag.class);

        try {
            _jspx_th_c_005fif_005f4.setPageContext(_jspx_page_context);
            _jspx_th_c_005fif_005f4.setParent((Tag)_jspx_th_c_005fif_005f0);
            _jspx_th_c_005fif_005f4.setTest(((Boolean)PageContextImpl.proprietaryEvaluate("${hideType != \'0\'}", Boolean.TYPE, (PageContext)this.getJspContext(), (ProtectedFunctionMapper)null)).booleanValue());
            int _jspx_eval_c_005fif_005f4 = _jspx_th_c_005fif_005f4.doStartTag();
            int evalDoAfterBody;
            if(_jspx_eval_c_005fif_005f4 != 0) {
                do {
                    out.write("\n");
                    out.write("\t\t<script type=\"text/javascript\">if(!top.$.jBox.tip.mess){top.$.jBox.tip.mess=1;top.$.jBox.tip(\"");
                    out.write((String)PageContextImpl.proprietaryEvaluate("${content}", String.class, (PageContext)this.getJspContext(), (ProtectedFunctionMapper)null));
                    out.write(34);
                    out.write(44);
                    out.write(34);
                    out.write((String)PageContextImpl.proprietaryEvaluate("${ctype}", String.class, (PageContext)this.getJspContext(), (ProtectedFunctionMapper)null));
                    out.write("\",{persistent:true,opacity:0});$(\"#messageBox\").show();}</script>\n");
                    out.write("\t");
                    evalDoAfterBody = _jspx_th_c_005fif_005f4.doAfterBody();
                } while(evalDoAfterBody == 2);
            }

            if(_jspx_th_c_005fif_005f4.doEndTag() == 5) {
                throw new SkipPageException();
            }
        } finally {
            this._005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f4);
        }

        return false;
    }
}
