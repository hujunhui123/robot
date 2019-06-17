package hust.plane.service.impl;

import hust.plane.mapper.mapper.FlyingPathMapper;
import hust.plane.mapper.mapper.TaskMapper;
import hust.plane.mapper.mapper.UavMapper;
import hust.plane.mapper.mapper.UserMapper;
import hust.plane.mapper.pojo.Task;
import hust.plane.mapper.pojo.TaskExample;
import hust.plane.mapper.pojo.TaskExample.Criteria;
import hust.plane.mapper.pojo.Uav;
import hust.plane.mapper.pojo.User;
import hust.plane.service.interFace.TaskService;
import hust.plane.utils.page.TailPage;
import hust.plane.utils.page.TaskPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UavMapper uavMapper;
    @Autowired
    private FlyingPathMapper flyingPathMapper;

    @Override
    public List<TaskPojo> getALLTask() {
        TaskExample example = new TaskExample();
        // 得到所有的任务
        example.setOrderByClause("CreateTime desc");
        List<Task> taskList = taskMapper.selectByExample(example);
        List<TaskPojo> list = null;
        // 得到每个人的名称
        if (taskList != null) {
            list = new ArrayList<TaskPojo>();
            for (Task task : taskList) {
                TaskPojo taskPojo = new TaskPojo();
                // 查询姓名
                User user1 = userMapper.selectByPrimaryKey(task.getUsercreator());
                User user2 = userMapper.selectByPrimaryKey(task.getUserA());
                User user3 = userMapper.selectByPrimaryKey(task.getUserZ());

                if (task.getUavId() == null || task.getUavId() == 0) {
                    taskPojo.setUavName("");
                    taskPojo.setDeviceId("");
                } else {
                    Uav uav = uavMapper.getUavById(task.getUavId());
                    taskPojo.setUavName(uav.getName());
                    taskPojo.setDeviceId(uav.getDeviceid());
                }

                taskPojo.setTask(task);
                taskPojo.setUserCreatorName(user1.getName());
                taskPojo.setUserAName(user2.getName());
                taskPojo.setUserZName(user3.getName());


                taskPojo.setFlyingPathName(flyingPathMapper.getNameById(task.getId()));
                list.add(taskPojo);
            }
        }
        return list;
    }

    // 分页查询
    @Override
    public TailPage<TaskPojo> queryPage(Task task, TailPage<TaskPojo> page) {

        List<TaskPojo> items = null;

        int itemsTotalCount = taskMapper.countByTask(task);

        //查看当前条目的分页的页数
        int totalPageNum = itemsTotalCount % page.getPageSize() == 0 ? itemsTotalCount / page.getPageSize() : itemsTotalCount / page.getPageSize() + 1;

        if (page.getPageNum() == 0 || page.getPageNum() > totalPageNum) {
            page.setPageNum(1);
        }

        // 包装数据
        if (itemsTotalCount > 0) {
            List<Task> taskList = taskMapper.queryPage(task, page);
            items = new ArrayList<TaskPojo>();
            for (Task task1 : taskList) {
                TaskPojo taskPojo = new TaskPojo();
                // 查询姓名
                User user1 = userMapper.selectByPrimaryKey(task1.getUsercreator());

                if (task1.getUavId() == null || task1.getUavId() == 0) {
                    taskPojo.setUavName("");
                    taskPojo.setDeviceId("");
                } else {
                    Uav uav = uavMapper.getUavById(task1.getUavId());
                    taskPojo.setUavName(uav.getName());
                    taskPojo.setDeviceId(uav.getDeviceid());
                }

                taskPojo.setTask(task1);
                taskPojo.setUserCreatorName(user1.getName());

                taskPojo.setFlyingPathName(flyingPathMapper.getNameById(task1.getFlyingpathId()));
                items.add(taskPojo);
            }
        }
        page.setItemsTotalCount(itemsTotalCount);
        page.setItems(items);
        return page;
    }

    // 保存任务
    @Override
    public boolean saveTask(Task task) {

        if (task.getName() == null || task.getName() == "") {
            return false;
        }
        Task task2 = getTaskByName(task.getName());
        if (task2 == null || task2.getId() == 0) {

            if (taskMapper.insertSelective(task) == 1) {
                return true;
            } else {
                return false;
            }
        } else {
            task.setId(task2.getId());
            if (taskMapper.updateByPrimaryKeyTaskCreate(task) == 1) {
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public boolean setStatusTaskByTask(Task task, int status) {

        task.setStatus(status);
        if (taskMapper.updateStatusByTask(task) == 1)
            return true;
        else
            return false;

    }

    @Override
    public String getStatusByTask(Task task) {
        return taskMapper.getStatusByTask(task);
    }

    @Override
    public boolean setFinishStatusTaskByTask(Task task, int finishstatus) {
        
        Task task2 = taskMapper.selectByPrimaryKey(task.getId());
        task2.setFinishstatus(finishstatus);

        if (taskMapper.updateByPrimaryKey(task2) == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean setTaskOver(Task task) {
        if (taskMapper.setTaskOver(task) == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Task getTaskByTask(Task task) {
        return taskMapper.selectByPrimaryKey(task.getId());
    }

    @Override
    public List<Task> getTasklistByAuser(User aUser) {

        return taskMapper.getTasklistByUserCreator(aUser.getId());
    }

    //倒序查看任务列表
    @Override
    public TailPage<TaskPojo> queryPageWithTime(Task task, TailPage<TaskPojo> page) {

        int itemsTotalCount = taskMapper.countByTask(task);

        //查看当前条目的分页的页数
        int totalPageNum = itemsTotalCount % page.getPageSize() == 0 ? itemsTotalCount / page.getPageSize() : itemsTotalCount / page.getPageSize() + 1;

        if (page.getPageNum() == 0 || page.getPageNum() > totalPageNum) {
            page.setPageNum(1);
        }

        // 包装数据
        List<TaskPojo> items = null;

        if (itemsTotalCount > 0) {
            List<Task> taskList = taskMapper.queryPageWithTime(task, page);
            items = new ArrayList<TaskPojo>();
            for (Task task1 : taskList) {
                TaskPojo taskPojo = new TaskPojo();
                // 查询姓名
                User user1 = userMapper.selectByPrimaryKey(task1.getUsercreator());
                User user2 = userMapper.selectByPrimaryKey(task1.getUserA());
                User user3 = userMapper.selectByPrimaryKey(task1.getUserZ());
                Uav uav = uavMapper.getUavById(task1.getUavId());

                taskPojo.setTask(task1);
                taskPojo.setUserCreatorName(user1.getName());
                taskPojo.setUserAName(user2.getName());
                taskPojo.setUserZName(user3.getName());
                taskPojo.setUavName(uav.getName());
                taskPojo.setDeviceId(uav.getDeviceid());

                taskPojo.setFlyingPathName(flyingPathMapper.getNameById(task1.getFlyingpathId()));
                items.add(taskPojo);
            }
        }
        page.setItemsTotalCount(itemsTotalCount);
        page.setItems(items);
        return page;
    }

    @Override
    public boolean deleteByTask(Task task) {

        if (taskMapper.deleteByPrimaryKey(task.getId()) == 1) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public void updataImgFolderByTask(Task task) {
        taskMapper.updateImgFolderByTask(task);

    }

    @Override
    public String selectImgFolderWithId(Integer taskId) {
        Task task = taskMapper.selectByPrimaryKey(taskId.intValue());
        if (task != null) {
            return task.getImgfolder();
        }
        return null;
    }

    @Override
    public Task getTaskByName(String name) {
        Task task = taskMapper.getTaskByName(name);
        return task;
    }

    @Override
    public List<Task> getFlyingPathByFlyingId(Integer id) {
        TaskExample example = new TaskExample();
        Criteria createCriteria = example.createCriteria();
        createCriteria.andFlyingpathIdEqualTo(id);
        List<Task> list = taskMapper.selectByExample(example);
        return list.size() > 0 ? list : null;
    }


    //根据用户作为任务管理员和正在飞行中的任务查询所有的任务
    @Override
    public List<Task> getTaskByCreatorAndStatus(User user, Integer status) {

        TaskExample example = new TaskExample();
        Criteria createCriteria = example.createCriteria();

        createCriteria.andStatusEqualTo(status);
        createCriteria.andUsercreatorEqualTo(user.getId());

        List<Task> list = taskMapper.selectByExample(example);
        return list.size() > 0 ? list : null;

    }

    @Override
    public List<Task> getAllTaskByRole(Task task) {

        List<Task> taskList = taskMapper.getAllTaskByRole(task);
        return taskList;
    }
}
