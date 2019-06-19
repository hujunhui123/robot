package hust.plane.web.robotoperation;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

/**
 * Created by hp on 2019/6/18.
 */
public interface CLibrary extends Library {

     //CLibrary INSTANCE = Native.load("F:\\RosWebCtrl",CLibrary.class);
    //CLibrary INSTANCE = (CLibrary) Native.load("RosWebCtrl",CLibrary.class);

    CLibrary INSTANCE = Native.load("RosWebCtrl",CLibrary.class);

    //初始化函数
    void init(ResultStruct.ByReference pResultStruct,String RemoteAddr,String UserName,String Pass,String RobotId);
    //检查是否初始化
    void isInit(ResultStruct.ByReference pResultStruct,String SocketHandle);
    //获取机器人的状态
    void getRobotStatus(RobotStatusStruct.ByReference pRobotStatusStruct,String SocketHandle,String propertyName);

    //设置机器人属性
    void setRobotProperty(ResultStruct.ByReference pResultStruct,String SocketHandle,
                          String propertyName,String propertyValue,String dataType);
    //移动机器人到指定点
    void moveToPoint(ResultStruct.ByReference pResultStruct, String SocketHandle, double dx, double dy);
    //移动机器人到相对位置
    void moveToRelativePoint(ResultStruct.ByReference pResultStruct, String SocketHandle, double dDistance, double dAngle);
    //机器人控制
    void moveControl(ResultStruct.ByReference pResultStruct, String SocketHandle, int direction, double distance, double angle);
    //转动底盘到相对地图的x轴的指定角度
    void rotateToAngle(ResultStruct.ByReference pResultStruct, String SocketHandle, double dAngle);
    //升降杆控制
    void raiseLift(ResultStruct.ByReference pResultStruct, String SocketHandle, boolean direction);
    void cameraPower(ResultStruct.ByReference pResultStruct, String SocketHandle, boolean PowerOn);
    //设置任务
    void setTask(ResultStruct.ByReference pResultStruct, String SocketHandle, CheckPointStruct checkPoints[], int iTaskCount);
    //启动任务
    void startTask(ResultStruct.ByReference pResultStruct, String SocketHandle, String batchId);
    ///暂停任务
    void pauseTask(ResultStruct.ByReference pResultStruct, String SocketHandle);
    //停止任务
    void stopTask(ResultStruct.ByReference pResultStruct, String SocketHandle);
    //启动充电过程
    void startCharge(ResultStruct.ByReference pResultStruct, String SocketHandle, boolean charge);
    //停止正在进行的移动过程
    void StopMoving(ResultStruct.ByReference pResultStruct, String SocketHandle);
    //定义响应结构体
    public static class ResultStruct extends Structure {
        public boolean success;//是否成功初始化
        public String errorCode;//代pRobotStatusStruct表错误编码
        public String errorMessage;//代表不成功的原因
        public String socketHandle;//通信socket
        //指针
        public static class ByReference extends ResultStruct implements  Structure.ByReference{}
        //结构
        public static class ByValue extends ResultStruct implements  Structure.ByValue{}
        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList(new String[]{"success","errorCode","errorMessage","socketHandle"});
        }
        public String toString(){
           return "success:"+success+",errorCode:"+errorCode+",errorMessgae:"+errorMessage;
        }
    }

    //定义机器人状态结构体
    public static class RobotStatusStruct extends  Structure{
        public boolean success;//是否成功初始化
        public String errorCode;//代表错误编码
        public String errorMessage;//代表不成功的原因
        public String statusValue;//成功获取请求状态的值
        //指针
        public static class ByReference extends RobotStatusStruct implements  Structure.ByReference{}
        //结构
        public static class ByValue extends RobotStatusStruct implements  Structure.ByValue{}
        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList(new String[]{"success","errorCode","errorMessage","statusValue"});
        }

        @Override
        public String toString() {
            return "RobotStatusStruct{" +
                    "success=" + success +
                    ", errorCode='" + errorCode + '\'' +
                    ", errorMessage='" + errorMessage + '\'' +
                    ", statusValue='" + statusValue + '\'' +
                    '}';
        }
    }
    public static class CheckPointStruct extends Structure{
        public byte batchId[]= new byte[30];//代表命令对应的唯一标识
        public byte checkPointId[] = new byte[30];//平台这边的巡检点的ID
        public double dx;//目标点的x坐标
        public double dy;//目标点的y坐标
        public double aAngle;//底盘的角度
        double roadWith;//当前点到目标点之间连线的最小距离
        boolean raiseLift ;//升降杆是否升起
        //指针
        public static class ByReference extends CheckPointStruct implements  Structure.ByReference{}
        //结构
        public static class ByValue extends CheckPointStruct implements  Structure.ByValue{}
        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList(new String[]{"batchId","checkPointId","dx","dy","aAngle"
                    ,"roadWith","raiseLift"});
        }
    }


}
