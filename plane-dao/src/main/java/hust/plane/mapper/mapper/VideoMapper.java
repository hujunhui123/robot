package hust.plane.mapper.mapper;

import hust.plane.mapper.pojo.Video;

import java.util.List;

public interface VideoMapper {

    List<Video> selectALLVideo();

    String getNameById(Integer id);

    Video getVideoById(Integer id);

    int insertVideo(Video video);
}
