//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.root.tenant.modules.gen.entity;

import com.root.tenant.modules.sys.entity.Dict;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(
        name = "category"
)
public class GenCategory extends Dict {
    private static final long serialVersionUID = 1L;
    private List<String> template;
    private List<String> childTableTemplate;
    public static String CATEGORY_REF = "category-ref:";

    public GenCategory() {
    }

    @XmlElement(
            name = "template"
    )
    public List<String> getTemplate() {
        return this.template;
    }

    public void setTemplate(List<String> template) {
        this.template = template;
    }

    @XmlElementWrapper(
            name = "childTable"
    )
    @XmlElement(
            name = "template"
    )
    public List<String> getChildTableTemplate() {
        return this.childTableTemplate;
    }

    public void setChildTableTemplate(List<String> childTableTemplate) {
        this.childTableTemplate = childTableTemplate;
    }
}
