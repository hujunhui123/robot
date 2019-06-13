package hust.plane.service.impl;

import hust.plane.mapper.mapper.FlyingPathMapper;
import hust.plane.mapper.mapper.InfoPointMapper;
import hust.plane.mapper.mapper.RouteMapper;
import hust.plane.mapper.pojo.FlyingPath;
import hust.plane.mapper.pojo.InfoPoint;
import hust.plane.mapper.pojo.Route;
import hust.plane.service.interFace.FileService;
import hust.plane.utils.ExcelUtil;
import hust.plane.utils.InfoPointStaticVO;
import hust.plane.utils.JsonUtils;
import hust.plane.utils.TxtReaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private RouteMapper routeMapper;

    @Autowired
    private InfoPointMapper infoPointMapper;

    @Autowired
    private FlyingPathMapper flyingPathMapper;

    // 插入路由数据 不更新信息点
    @Override
	public boolean insertRoute(File file) {
		
    	Route route = new Route();
        List<InfoPoint> infoPoints = new ArrayList<>();

        if (ExcelUtil.readExcel(file, route, infoPoints) == false) {
            return false;
        }
        // 设置创建时间
        Date date = new Date();
        route.setCreatetime(date);
        int count = routeMapper.countByName(route.getName());
        if (count > 0)   //如果该路由存在的话
            return false;

        if (routeMapper.insert(route) == 1) {
        	
            int id = routeMapper.getIdByName(route.getName());
            Iterator<InfoPoint> iterator = infoPoints.iterator();
            
            while (iterator.hasNext()) {
                InfoPoint infoPoint = iterator.next();
                infoPoint.setRouteId(id);    //修改光缆id
              } 
            infoPointMapper.insertInfoPointList(infoPoints);

            return true;
        } else
            return false;
	}

    // 插入路由数据 并更新信息点文件   infoPoint.js
    @Override
    public boolean insertRouteAndUpdateJS(String basepath,File file) {

        Route route = new Route();
        List<InfoPoint> infoPoints = new ArrayList<>();

        if (ExcelUtil.readExcel(file, route, infoPoints) == false) {
            return false;
        }
        // 设置创建时间
        Date date = new Date();
        route.setCreatetime(date);

        int count = routeMapper.countByName(route.getName());

        if (count > 0)
            return false;

        if (routeMapper.insert(route) == 1) {
        	
        	List<InfoPointStaticVO> infoPointVOList = new ArrayList<>();
            int id = routeMapper.getIdByName(route.getName());
            Iterator<InfoPoint> iterator = infoPoints.iterator();
            while (iterator.hasNext()) {
            	
              InfoPoint infoPoint = iterator.next();
              infoPoint.setRouteId(id);    //修改光缆id
              InfoPointStaticVO infoPointVO = new InfoPointStaticVO(infoPoint, 2);
              infoPointVOList.add(infoPointVO);
            }           
            
            infoPointMapper.insertInfoPointList(infoPoints);
            
            //在这个地方更新（在文件尾追加信息点） infoPoint.js文件，用于加快访问速度
            //获取文件路径
            StringBuffer stringBuffer = new StringBuffer(basepath);
            stringBuffer.append("res").append(File.separator).append("js").append(File.separator).append("infoPoint.js");
                   
            try {
            	RandomAccessFile  randomFile = new RandomAccessFile(stringBuffer.toString(), "rw");
				long fileLength = randomFile.length();

				randomFile.seek(fileLength - 1);   //覆盖最后的一个 ] 号
				StringBuffer stringBuffer2 = new StringBuffer(",");
				String string = JsonUtils.objectToJson(infoPointVOList);				
				stringBuffer2.append(string.substring(1, string.length()-1));
				stringBuffer2.append("]");
							
                randomFile.write(stringBuffer2.toString().getBytes());                
                randomFile.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
            } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}                 
          
            return true;
        } else
            return false;
    }
    
    
    
    //从excel读取飞行路径，并且写入数据库
    @Override
    public boolean insertFlyingPath(File f) {

        FlyingPath flyingPath = new FlyingPath();
        if (TxtReaderUtil.readFlyingPathFromTxt(f, flyingPath) == false) {
            return false;
        }
        flyingPath.setCreatetime(new Date());
        flyingPath.setUpdatetime(new Date());
        int count = flyingPathMapper.countByName(flyingPath.getName());
        if (count > 0) {
            return false;
        }
        if (flyingPathMapper.insertFlyingPath(flyingPath) == 1) {
            return true;
        }
        return false;
    }

	

}
