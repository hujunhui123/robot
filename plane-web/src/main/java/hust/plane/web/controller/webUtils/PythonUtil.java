package hust.plane.web.controller.webUtils;

import java.io.*;

/**
 * @author hujunhui
 * @date 2019/6/25 9:28
 * @description 功能：
 */
public class PythonUtil {

    public static void main(String[] args){
        //执行的python命令
        String[] cmdArr = new String[] {"python", "D:\\IDEAworkspace\\robot\\plane-web\\target\\plane-web\\WEB-INF\\classes\\PGM2BMP.py",
                "D:\\IDEAworkspace\\robot\\plane-web\\target\\plane-web\\res\\upload\\789.pgm",
                "D:\\IDEAworkspace\\robot\\plane-web\\target\\plane-web\\res\\upload\\"};

        Process process = null;
        try {
            process = Runtime.getRuntime().exec(cmdArr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStreamReader is = new InputStreamReader(process.getInputStream());
        BufferedReader dis = new BufferedReader(is);
        String str = null;
        try {
            str = dis.readLine();
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
             process.waitFor();
             int resCode = process.exitValue();
            //若返回码是0,则代表正常执行
            //非0说明不正常
            System.out.println("执行返回码是："+resCode);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //如果转换成功则 输出 success
        //否则          输出 fail
        System.out.println(str);
    }


    //该方法把PGM文件转换成BMP文件  参数：originPath PGM文件路径   targetDir 目标文件夹
    public static boolean PGM2BMP(String originPath,String targetDir){

        String exe = "python";
        String command = Thread.currentThread().getContextClassLoader().getResource("").getPath()+"PGM2BMP.py";
        command = command.replace('/','\\').substring(1);
       // String param = "C:\\Users\\hujunhui\\Desktop\\机器人\\map\\B12_4L_20190115_01\\map.pgm";

        String[] cmdArr = new String[] {exe, command, originPath,targetDir};

        Process process = null;
        try {
            process = Runtime.getRuntime().exec(cmdArr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream is = process.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        String str = null;
        try {
            str = dis.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //如果转换成功则 输出 success
        //否则          输出 fail
        //System.out.println(str);
        if(str.equals("success"))
            return true;
        else
            return false;
    }


}
