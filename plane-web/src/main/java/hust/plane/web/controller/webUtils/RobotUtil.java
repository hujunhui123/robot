package hust.plane.web.controller.webUtils;

/**
 * @author hujunhui
 * @date 2019/7/20 17:04
 * @description 功能：
 */
public class RobotUtil {

    public  static double getDistance(double x1,double y1,double x2,double y2){

        double disX = Math.abs(x1 - x2);
        double disY = Math.abs(y1 - y2);
        return Math.sqrt(disX * disX + disY*disY);


    }
    public  static double getAngle(double x,double y){
         //计算该点相对于x轴的角度
        // 反 正切函数
       return Math.toDegrees(Math.atan(y/x));
    }
    public static void main(String[] args){
        System.out.println(getAngle(1,1));
        System.out.println(getAngle(1,-1));
    }


}
