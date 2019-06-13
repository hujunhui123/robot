package hust.plane.service.interFace;


import java.io.File;

public interface FileService {

    boolean insertRouteAndUpdateJS(String basepath,File file);

    boolean insertRoute(File file);
    
    boolean insertFlyingPath(File f);
}
