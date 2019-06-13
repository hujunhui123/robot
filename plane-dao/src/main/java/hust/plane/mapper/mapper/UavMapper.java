package hust.plane.mapper.mapper;

import hust.plane.mapper.pojo.Uav;

import java.util.Date;
import java.util.List;

public interface UavMapper {

    List<Uav> selectALLPlane();

    List<Uav> selectPlaneByOption(int id, Date starttime, Date endtime);

    List<Uav> selectByPlaneStatus(int status);

    Uav getPlaneByPlane(Uav uav);

    String getNameById(Integer id);

    Uav getUavById(Integer id);

    int insertUav(Uav uav);
}
