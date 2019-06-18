package hust.plane.web.robotoperation;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by hp on 2019/6/18.
 */
//用于管理robot的管理配置
public class RobotManager {
    //结果保存
    public static ConcurrentHashMap<String,CLibrary.ResultStruct> resultStructMap = new ConcurrentHashMap<>();


}
