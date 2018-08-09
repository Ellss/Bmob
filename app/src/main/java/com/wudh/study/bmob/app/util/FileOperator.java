package com.wudh.study.bmob.app.util;

import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by wudh on 2018/7/26.
 **/
public class FileOperator {
    private static FileOperator fileOperator;
    private Context context;
    private final static String TAG="IOUtil";

    public FileOperator(Context context){
        this.context=context;
    }

    public static FileOperator getInstance(Context context){
        if (fileOperator==null){
            fileOperator=new FileOperator(context);
        }
        return fileOperator;
    }

    /**
     * 读取文件的字符串
     * @param path
     * @return 字符串
     */
    public String file2StringReader(String path){
        StringBuilder sb=new StringBuilder();
        try{
            File file=new File(path);
            FileReader fr=new FileReader(file);
            char[] c=new char[512];
            int len=-1;
            while ((len=fr.read(c))!=-1){
                sb.append(new String(c,0,len));
            }
            fr.close();
        }catch (FileNotFoundException e){
            Log.e(TAG, "file2String: "+e.getMessage());
        }catch (IOException e){
            Log.e(TAG, "file2String: "+e.getMessage());
        }
        return sb.toString();
    }

    /**
     * 将字符串写到文件中
     * @param path 路径
     * @param str 字符串
     * @return 是否写入
     */
    public boolean string2FileWriter (String path,String str){
        try{
            File file=new File(path);
            FileWriter fw=new FileWriter(file);
            fw.write(str,0,str.length());
            fw.close();
            return true;
        }catch (FileNotFoundException e){
            Log.e(TAG, "file2String: "+e.getMessage());
            return false;
        }catch (IOException e){
            Log.e(TAG, "file2String: "+e.getMessage());
            return false;
        }
    }
    public boolean copyFile(String from,String to){
        File f=new File(from);
        File t=new File(to);

        boolean flag=false;

        if (f.exists()) {
            BufferedInputStream bis;
            BufferedOutputStream bos;
            try {
                if (!t.exists()){
                    t.createNewFile();
                }
                bis = new BufferedInputStream(new FileInputStream(f));
                bos = new BufferedOutputStream(new FileOutputStream(t));
                int len = -1;
                byte[] b = new byte[1024];
                while ((len = bis.read(b)) != -1) {
                    bos.write(b, 0, len);
                }
                bis.close();
                bos.close();
                flag = true;
            } catch (FileNotFoundException e) {
                Log.e(TAG, "copyFile: " + e.getMessage());
            } catch (IOException e) {
                Log.e(TAG, "copyFile: " + e.getMessage());
            }
        }else {
            Log.e(TAG, "copyFile: 文件不存在!");
        }
        return flag;
    }
    public String file2StringBuffer (String path){
        File file=new File(path);
        StringBuilder sb=new StringBuilder();
        BufferedInputStream bis;
        try{
            bis =new BufferedInputStream(new FileInputStream(file));
            int len=-1;
            byte[] b=new byte[1024];
            while ((len=bis.read(b))!=-1){
                sb.append(new String(b,0,len,"UTF-8"));
            }
            bis.close();
        }catch (FileNotFoundException e){
            Log.e(TAG, "file2String: "+e.getMessage());
        }catch (IOException e){
            Log.e(TAG, "file2String: "+e.getMessage());
        }
        return sb.toString();
    }
    public boolean string2FileBuffer(String path,String str){
        File file=new File(path);
        BufferedOutputStream bos;
        boolean flag=false;
        try{
            bos=new BufferedOutputStream(new FileOutputStream(file));
            bos.write(str.getBytes("UTF-8"),0,str.length());
            bos.flush();
            bos.close();
            flag=true;
        }catch (FileNotFoundException e){
            Log.e(TAG, "file2String: "+e.getMessage());
        }catch (IOException e){
            Log.e(TAG, "file2String: "+e.getMessage());
        }
        return flag;
    }

}
