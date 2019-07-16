package hust.plane.web.robotoperation;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by hp on 2019/6/18.
 */
//用于管理robot的管理配置
public class RobotManager {
    //结果保存
    public final static ConcurrentHashMap<String,CLibrary.ResultStruct.ByReference> resultStructMap = new ConcurrentHashMap<>();

    //添加
    public static void addResultStruct(String robotId,CLibrary.ResultStruct.ByReference r)
    {
        resultStructMap.put(robotId,r);
    }
    //获得
    public static CLibrary.ResultStruct.ByReference getResultStruct(String robotId)
    {
        return resultStructMap.get(robotId);
    }


}
