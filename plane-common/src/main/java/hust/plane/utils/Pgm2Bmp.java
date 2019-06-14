package hust.plane.utils;

import java.awt.*;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author hujunhui
 * @date 2019/6/10 21:02
 * @description 功能：
 */
public class Pgm2Bmp {

    //默认文件都是P5格式的PGM文件
    //开头如下:
    //   P5
    //   # xxx xxx
    //   int1 int2
    //   255

    public static void pgmConvert() {
        String filePath = "F:\\map.pgm";
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner scan = new Scanner(fileInputStream);

        // 跳过 魔数
        scan.nextLine();
        // 跳过 描述行
        scan.nextLine();

        int picWidth = scan.nextInt();
        int picHeight = scan.nextInt();
        int maxvalue = scan.nextInt();

        try {   //关闭文件流
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        DataInputStream dis;
        try {
            fileInputStream = new FileInputStream(filePath);
            dis = new DataInputStream(fileInputStream);
            int numnewlines = 4;

            while (numnewlines > 0) { //跳过前面四行文件头
                char c;
                do {
                    c = (char) (dis.readUnsignedByte());
                } while (c != '\n');
                numnewlines--;
            }

            // 开始读取数据
            int[][] data2D = new int[picHeight][picWidth];
            for (int row = 0; row < picHeight; row++) {
                for (int col = 0; col < picWidth; col++) {
                    data2D[row][col] = dis.readUnsignedByte();
                    System.out.print(data2D[row][col]+",");
                }
                System.out.println("");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args){

        pgmConvert();
    }


}
