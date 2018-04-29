package asset.mapper;

import asset.pojo.PriManage;
import asset.pojo.SenManager;
import asset.pojo.Teacher;
import asset.pojo.Unit;


/**
 * Created by su on 18-2-5.
 */
public interface UserMapper {
    //登录
    public Teacher teacherLogin(Teacher teacher);
    //验证用户名是否被注册
    public Teacher checkUserName(Teacher teacher);
    //老师注册
    public int signUp(Teacher teacher);
    //业务管理员登录
    public SenManager senManagerLogin(SenManager senManager);
    //添加业务管理员
    public int addSenManger(SenManager senManager);
    //删除业务管理员
    public int delSenManger(SenManager senManager);
    //超级管理员登陆
    public PriManage superMangerLogin(PriManage priManage);
}
