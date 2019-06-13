package hust.plane.web.controller;

import hust.plane.mapper.pojo.Uav;
import hust.plane.service.interFace.UavService;
import hust.plane.utils.page.TailPage;
import hust.plane.utils.pojo.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @author hujunhui
 * @date 2019/6/8 12:52
 * @description 功能：
 */
@Controller
public class RobotController {

    @Autowired
    UavService uavService;

    //返回 机器人注册页面
    @RequestMapping("robotRegister")
    public String roboRegister(Model model) {
        model.addAttribute("curNav", "robotRegister");
        return "robotRegister";
    }


    //返回 所有的机器人
    @RequestMapping("robotList")
    public String robotAllList(Model model) {

        List<Uav> uavList = uavService.getAllPlane();

        model.addAttribute("curNav", "robotAllList");
        model.addAttribute("uavlist",uavList);
        return "robotAllList";
    }


    @RequestMapping(value = "robotInfoRegister", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String robotInfoRegister(Uav uav) {

        uav.setCreatetime(new Date());

        if (uavService.insertUav(uav) == true)

            return JsonView.render(1, "机器人注册成功！");

        return JsonView.render(0, "机器人注册失败！");
    }


}
