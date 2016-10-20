//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.root.tenant.modules.gen.util;

import java.io.*;

public class GenUtils {
    public static String versionType = "jeeplus";
    public static String versionNum = "2.4";
    public static String computer = null;

    static {
        computer = e();
    }

    public GenUtils() {
    }

    public static String a() {
        String result = "";

        try {
            File file = File.createTempFile("realhowto", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new FileWriter(file);
            String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\nSet colItems = objWMIService.ExecQuery _ \n   (\"Select * from Win32_BaseBoard\") \nFor Each objItem in colItems \n    Wscript.Echo objItem.SerialNumber \n    exit for  \' do the first cpu only! \nNext \n";
            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());

            BufferedReader input;
            String line;
            for(input = new BufferedReader(new InputStreamReader(p.getInputStream())); (line = input.readLine()) != null; result = result + line) {
                ;
            }

            input.close();
        } catch (Exception var7) {
            ;
        }

        return result.trim();
    }

    public static String b(String drive) {
        String result = "";

        try {
            File file = File.createTempFile("realhowto", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new FileWriter(file);
            String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\nSet colDrives = objFSO.Drives\nSet objDrive = colDrives.item(\"" + drive + "\")\n" + "Wscript.Echo objDrive.SerialNumber";
            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());

            BufferedReader input;
            String line;
            for(input = new BufferedReader(new InputStreamReader(p.getInputStream())); (line = input.readLine()) != null; result = result + line) {
                ;
            }

            input.close();
        } catch (Exception var8) {
            ;
        }

        return result.trim();
    }

    public static String c() {
        String result = "";

        try {
            File file = File.createTempFile("tmp", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new FileWriter(file);
            String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\nSet colItems = objWMIService.ExecQuery _ \n   (\"Select * from Win32_Processor\") \nFor Each objItem in colItems \n    Wscript.Echo objItem.ProcessorId \n    exit for  \' do the first cpu only! \nNext \n";
            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());

            BufferedReader input;
            String line;
            for(input = new BufferedReader(new InputStreamReader(p.getInputStream())); (line = input.readLine()) != null; result = result + line) {
                ;
            }

            input.close();
            file.delete();
        } catch (Exception var7) {
            ;
        }

        return result.trim();
    }

    public static String d() {
        String result = "";

        try {
            Process process = Runtime.getRuntime().exec("ipconfig /all");
            InputStreamReader ir = new InputStreamReader(process.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);

            String line;
            while((line = input.readLine()) != null) {
                if(line.indexOf("Physical Address") > 0) {
                    String MACAddr = line.substring(line.indexOf("-") - 2);
                    result = MACAddr;
                }
            }
        } catch (IOException var6) {
            ;
        }

        return result;
    }

    public static String e() {
        return c() + a() + b("c") + d();
    }
}
