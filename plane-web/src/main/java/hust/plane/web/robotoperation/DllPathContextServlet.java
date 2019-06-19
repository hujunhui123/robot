package hust.plane.web.robotoperation;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.lang.reflect.Field;

/**
 * Created by hp on 2019/6/19.
 */
public class DllPathContextServlet implements ServletContextListener {

    private void addDirTopath(String s)  {
        try{
            //获取系统变量
            Field syspathFeild = ClassLoader.class.getDeclaredField("sys_paths");
            syspathFeild.setAccessible(true);
            //获得此变量对象的值
            String[] path = (String[]) syspathFeild.get(null);
            //创建字符串数组，在原来的数组长度上增加一个，用于存放增加的目录
            String[] tem=new String[path.length+1];
            //将原来的path变量复制到tem中
            System.arraycopy(path,0,tem,0,path.length);
            //将增加的目录存入新的变量数组中
            tem[path.length]=s;
            //将增加目录后的数组赋给path变量对象
            syspathFeild.set(null,tem);

        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        //获取存放dll文件的绝对路径
        String path = sce.getServletContext().getRealPath("WEB-INF");
        addDirTopath(path);
        //加载相应的dll文件，注意要将'\'替换为'/'
        System.load(path.replaceAll("\\\\","/")+"/RosWeb.dll");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
