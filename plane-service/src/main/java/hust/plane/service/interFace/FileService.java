package hust.plane.service.interFace;


import hust.plane.mapper.pojo.FlyingPath;

import java.io.File;

public interface FileService {

    boolean insertRoute(File file);
    
    boolean insertFlyingPath(File f);

    boolean insetFlyingPath(FlyingPath flyingPath);
}
