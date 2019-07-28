package hust.plane.web.controller;

import hust.plane.utils.pojo.JsonView;
import hust.plane.web.controller.vo.RobotStatusVo;
import hust.plane.web.robotoperation.CLibrary;
import hust.plane.web.robotoperation.RobotManager;
import hust.plane.web.robotoperation.RobotStatusList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


//单机版测试用
//@Controller
public class RobotOperationController {

    public void robotInit(String RemoteAddr,String UserName,String Pass,String RobotId)
    {
        //封装
        //  1 个像素点是 50mm，规划地图的时候计算比例就可以，1m = 20像素
        // 以m为单位运动
        // 所以一个像素的距离是 0.005 m
        System.out.println("初始化：");

        CLibrary.CheckPointStruct.ByReference[] pointssss = new  CLibrary.CheckPointStruct.ByReference[2];
        pointssss[0] =  new CLibrary.CheckPointStruct.ByReference();
        pointssss[1] =  new CLibrary.CheckPointStruct.ByReference();


        byte [] a = "as".getBytes();
        System.arraycopy(a,0,pointssss[0].batchId,0,a.length);

        CLibrary.ResultStruct.ByReference resultStruct = new CLibrary.ResultStruct.ByReference();
        CLibrary.INSTANCE.init(resultStruct,RemoteAddr,UserName,Pass,RobotId);

        System.out.println(resultStruct.toString());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /*System.out.println("获取状态：");
        //通过机器人设备id获取socket句柄对象
        //CLibrary.ResultStruct.ByReference ResultStruct = RobotManager.getResultStruct("123");
        RobotStatusVo statusVo = new RobotStatusVo();
        CLibrary.RobotStatusStruct.ByReference robotStatusStruct = new CLibrary.RobotStatusStruct.ByReference();
        for(int i=0;i< RobotStatusList.CmdNameList.length;i++) {

            CLibrary.INSTANCE.getRobotStatus(robotStatusStruct, resultStruct.socketHandle,RobotStatusList.CmdNameList[i]);
            String statusValue = robotStatusStruct.statusValue;

            System.out.println(robotStatusStruct.toString());

            if(!robotStatusStruct.success)
            {
                System.out.println("未查询成功");
                continue;
            }else {

                System.out.println("查询成功");

                switch (RobotStatusList.CmdNameList[i]) {
                    case RobotStatusList.ST_LOCATION:
                        statusVo.setLocation(statusValue);
                        break;
                    case RobotStatusList.ST_DANGLE:
                        statusVo.setdAngle(statusValue);
                        break;
                    case RobotStatusList.ST_SPEED:
                        statusVo.setSpeed(statusValue);
                        break;
                    case RobotStatusList.ST_TEMPTURE:
                        statusVo.setTempreture(statusValue);
                        break;
                    case RobotStatusList.ST_BAT_VOL:
                        statusVo.setBatteryVoltage(statusValue);
                        break;
                    case RobotStatusList.ST_CHR_VOL:
                        statusVo.setChargeVoltage(statusValue);
                        break;
                    case RobotStatusList.ST_CHR_STA:
                        statusVo.setChargeStatus(statusValue);
                        break;
                    case RobotStatusList.ST_WORKEDTIME: {
                        long ulv = Long.parseLong(statusValue);
                        int iHour = (int) (ulv / 3600);
                        int iMunite = (int) (ulv % 3600) / 60;
                        int iSecond = (int) (ulv % 60);
                        statusValue = iHour + "小时" + iMunite + "分" + iSecond + "秒";
                        statusVo.setWorkedTime(statusValue);
                    }
                    break;
                    case RobotStatusList.ST_REMAINBAT:
                        statusVo.setRemainBattery(statusValue);
                        break;
                    case RobotStatusList.ST_CEMARALIFT:
                        statusVo.setCemaraLift(statusValue);
                        break;
                    case RobotStatusList.ST_WORKEDDIS:
                        statusVo.setWorkedDis(statusValue);
                        break;
                    case RobotStatusList.ST_CTRLMODE:
                        statusVo.setControlMode(statusValue);
                        break;
                    default:break;
                }
            }
        }
        System.out.println(statusVo.toString());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/



//        CLibrary.INSTANCE.startTask(resultStruct,resultStruct.socketHandle,"1234");
        System.out.println("启动：");
        System.out.println(resultStruct.toString());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("动一下看看：");
        CLibrary.INSTANCE.moveToRelativePoint(resultStruct, resultStruct.socketHandle,0.1, 45);  //单位是m
        System.out.println(resultStruct.toString());

        System.out.println("回转角度：");
        CLibrary.INSTANCE.rotateToAngle(resultStruct,resultStruct.socketHandle,0);
        System.out.println(resultStruct.toString());


//
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("结束任务：");
        //调用结束任务函数
        CLibrary.INSTANCE.stopTask(resultStruct,resultStruct.socketHandle);
        System.out.println(resultStruct.toString());


    }

    public static void main(String[] args){
      RobotOperationController  robotOperationController = new RobotOperationController();
      robotOperationController.robotInit("10.12.137.81","ROS","ROS","123");

    }

}
