
package hust.plane.web.controller;

import hust.plane.mapper.pojo.FlyingPath;
import hust.plane.service.interFace.FileService;
import hust.plane.utils.ExcelUtil;
import hust.plane.utils.pojo.JsonView;
import hust.plane.web.controller.webUtils.PythonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class FileController {

    @Autowired
    private FileService FileServiceImpl;

//    // 导入光缆功能
//    @RequestMapping(value = "routeFileImport", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
//    @ResponseBody
//    public String importOneFile(@RequestParam(value = "routePathExcel", required = true) MultipartFile[] files,
//                                HttpServletRequest request) {
//
//        //并且在这把相关的路由 保存在数据库
//
//        // 记录有错误的文件名字，并返回前台
//        List<String> errfile = new ArrayList<String>();
//        List<String> succfile = new ArrayList<String>();
//        String basepath = request.getSession().getServletContext().getRealPath("");
//        if (files.length > 0) {
//            for (int i = 0; i < files.length; i++) {
//                String fileName = files[i].getOriginalFilename();// 获取到上传文件的名字
//                File f = null; // 把MultipartFile转化成File
//                if (files[i].getSize() <= 0 || files.equals("")) {
//                    errfile.add(fileName + "文件内容为空；");
//                } else {
//                    InputStream ins;
//                    try {
//                        ins = files[i].getInputStream();
//
//                        f = new File(basepath + fileName);
//                        ExcelUtil.inputStreamToFile(ins, f);
//
//                        // 上面的为更新信息点文件的方法
//                        // if (FileServiceImpl.insertRouteAndUpdateJS(basepath, f){
//                        if (FileServiceImpl.insertRoute(f) == false) {
//                            errfile.add(fileName + "的格式错误或路由名称重复");
//                        } else {
//                            succfile.add(fileName);
//                        }
//                        File del = new File(f.toURI());
//                        del.delete(); // 删除临时文件
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        } else {
//            return JsonView.render(0, "导入文件为空！");
//        }
//        String reString = "";
//        if (succfile.size() > 0) {
//            reString = reString + succfile.toString().replace("[", "").replace("]", "") + ",等文件导入成功!</br>";
//        }
//        if (errfile.size() > 0) {
//            reString = reString + errfile.toString().replace("[", "").replace("]", "") + ",等文件导入失败。";
//        }
//
//        return JsonView.render(0, reString);
//    }

    // 导入飞行路径功能，使用txt文件

    @RequestMapping(value = "flyingPathfileImport", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public String flyingPathfileImport(@RequestParam(value = "flyingPathExcel", required = true) MultipartFile[] files,
                                       HttpServletRequest request) {

        // 记录有错误的文件名字，并返回前台
        List<String> errfile = new ArrayList<String>();
        List<String> succfile = new ArrayList<String>();
        String basepath = request.getSession().getServletContext().getRealPath("");
        if (files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getOriginalFilename();// 获取到上传文件的名字
                File f = null; // 把MultipartFile转化成File
                if (files[i].getSize() <= 0 || files.equals("")) {
                    errfile.add(fileName + "的文件内容为空；");
                } else {
                    InputStream ins;
                    try {
                        ins = files[i].getInputStream();

                        f = new File(basepath + fileName);
                        ExcelUtil.inputStreamToFile(ins, f);

                        if (FileServiceImpl.insertFlyingPath(f) == false) {
                            errfile.add(fileName + "的格式错误或飞行路径名称重复");
                        } else {
                            succfile.add(fileName);
                        }
                        File del = new File(f.toURI());
                        del.delete(); // 删除临时文件
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            return JsonView.render(0, "导入文件为空！");
        }
        String reString = "";
        if (succfile.size() > 0) {
            reString = reString + succfile.toString().replace("[", "").replace("]", "") + ",等文件导入成功!</br>";
        }
        if (errfile.size() > 0) {
            reString = reString + errfile.toString().replace("[", "").replace("]", "") + ",等文件导入失败。";
        }

        return JsonView.render(0, reString);
    }



    @RequestMapping(value = "/uploadMap", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam(value = "file", required = true) MultipartFile file,
                         @RequestParam(value = "pathDesp")String pathDesp,
                         HttpServletRequest request) throws IOException {

        //保存到项目的文件夹中
        String path = request.getSession().getServletContext().getRealPath("/")+"res\\upload\\";
        String realName = file.getOriginalFilename();
        String fileName = realName.substring(0,realName.length() - 4);
        File dir = new File(path, realName);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        file.transferTo(dir);
        String originPath = path+realName;
        String targetDir = path;


        if(PythonUtil.PGM2BMP(originPath,targetDir)){

            //以照片名称作为路径名称
            FlyingPath flyingPath = new FlyingPath();
            flyingPath.setCreatetime(new Date());
            flyingPath.setDescription(pathDesp);
            flyingPath.setName(fileName);
            if(FileServiceImpl.insetFlyingPath(flyingPath)){
                return JsonView.render(1, fileName);
            }else{
                return JsonView.render(0, "插入数据库失败！");
            }

        }else{
            return JsonView.render(0, "上传失败");
        }


    }

}
