package hust.plane.web.robotoperation;

/**
 * Created by hp on 2019/6/18.
 */
public interface RobotStatusList {

    String ST_LOCATION = "location";        //	机器人当前位置	以(x, y)的形式返回, x, y的类型都是double
    String ST_DANGLE = "dAngle";        //	机器人底盘角度	double类型, 相对于地图上的x轴的角度
    String ST_SPEED = "speed";        //	机器人机器人当前的移动速度	类型是double, 单位m/s
    String ST_TEMPTURE = "tempreture";    //	当前温F度	机器人内部温度
    String ST_BAT_VOL = "batteryVoltage";//	电池电压	float
    String ST_CHR_VOL = "chargeVoltage";//	充电电压	充电时的电池电压
    String ST_CHR_STA = "chargeStatus";    //	充电状态	BOOL, 返回值要么是true, 要么是false
    String ST_WORKEDTIME = "workedTime";    //	已工作时间	（本次启动已经工作时间）long,  单位秒
    String ST_REMAINBAT = "remainBattery";//	剩余电量百分比	float, 0.2012 保留4位小数
    String ST_REMAINTIME = "remainTime";    //	预计剩余时间	long, 预计还能工作多少时间, 单位秒
    String ST_CEMARALIFT = "cemaraLift";    //	升降杆状态	BOOL true : 已上升状态 false, 已下降状态
    String ST_WORKEDDIS = "WorkedDis";            //	运行里程double类型, 单位m，3位小数
    String ST_CTRLMODE = "controlMode";
    String CmdNameList[] = {ST_LOCATION, ST_DANGLE, ST_SPEED, ST_TEMPTURE, ST_BAT_VOL,
            ST_CHR_VOL, ST_CHR_STA, ST_WORKEDTIME, ST_REMAINBAT, ST_REMAINTIME, ST_CEMARALIFT,
            ST_WORKEDDIS, ST_CTRLMODE};

}
