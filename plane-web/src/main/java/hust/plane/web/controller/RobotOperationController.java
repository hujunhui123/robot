package hust.plane.web.controller;

import hust.plane.utils.pojo.JsonView;
import hust.plane.web.robotoperation.CLibrary;
import hust.plane.web.robotoperation.RobotManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hp on 2019/6/18.
 */
//机器人的操作controller
@Controller
public class RobotOperationController {
    @RequestMapping("/robotOperationInit")
    @ResponseBody
    public String robotInit(String RemoteAddr,String UserName,String Pass,String RobotId)
    {
        //封装
        CLibrary.ResultStruct resultStruct = new CLibrary.ResultStruct();
        CLibrary.INSTANCE.init(resultStruct,RemoteAddr,UserName,Pass,RobotId);
        if(resultStruct.success)
        {
            //成功
            RobotManager.resultStructMap.put(RobotId,resultStruct);
            return JsonView.render(1, "机器人初始化成功！");
        }else{
           return JsonView.render(0, "机器人初始化失败！");
        }
    }

}
