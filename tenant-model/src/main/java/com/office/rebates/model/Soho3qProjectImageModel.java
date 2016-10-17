package com.office.rebates.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by qiuliang on 15/9/14.
 */
public class Soho3qProjectImageModel {
    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @JSONField(name = "img_path")
    private String imgPath;
}
