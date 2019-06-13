package hust.plane.web.controller;

import hust.plane.mapper.pojo.Video;
import hust.plane.service.interFace.VideoService;
import hust.plane.utils.pojo.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @author hujunhui
 * @date 2019/6/8 12:55
 * @description 功能：
 */
@Controller
public class VideoController {

    @Autowired
    VideoService videoService;

    //返回 摄像头注册页面
    @RequestMapping("videoRegister")
    public String videoRegister(Model model){
        model.addAttribute("curNav", "videoRegister");
        return "videoRegister";
    }


    @RequestMapping(value = "videoInfoRegister", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String videoInfoRegister(Video video){

        Date data = new Date();
        video.setCreatetime(data);
        if(videoService.insertVideo(video)==true)

             return JsonView.render(1, "摄像头注册成功！");

        return JsonView.render(0, "摄像头注册失败！");
    }

}
