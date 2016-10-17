package com.office.rebates.util;

/**
 * 身份证工具
 * Created by liyongfeng on 2015/12/22.
 */
public class CoverUtils {

    /**
     * 身份证/手机等加密（加星）
     * @param identity 身份证号
     * @param firstLength 开头长度
     * @param lastLength 结尾长度
     * @return
     */
    public static String coverEncryption(String identity,int firstLength,int lastLength){
        StringBuffer stringBuffer = new StringBuffer();
        if(identity.length()>0){
            int length = identity.length()-firstLength-lastLength;
            StringBuffer star = new StringBuffer();
            for (int i=0;i<length;i++) {
                star.append("*");
            }
            stringBuffer.append(identity.substring(0,firstLength));
            stringBuffer.append(star.toString());
            stringBuffer.append(identity.substring(identity.length()-lastLength));
        }else{
            stringBuffer.append("");
        }
        return stringBuffer.toString();
    }
}
