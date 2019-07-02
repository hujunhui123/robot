package hust.plane.service.impl;

import hust.plane.mapper.mapper.VideoMapper;
import hust.plane.mapper.pojo.Video;
import hust.plane.service.interFace.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hujunhui
 * @date 2019/6/9 22:40
 * @description 功能：
 */
@Service
public class VideoServiceImpl implements VideoService {


    @Autowired
    VideoMapper videoMapper;

    @Override
    public boolean insertVideo(Video video) {
        if(videoMapper.insertVideo(video) == 1){
            return true;
        }
        else{
            return false;
        }
    }
}
