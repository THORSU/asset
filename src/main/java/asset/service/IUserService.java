package asset.service;

import asset.pojo.PriManager;
import asset.pojo.SenManager;
import asset.pojo.Teacher;

/**
 * Created by su on 18-2-5.
 */
public interface IUserService {
    //老师登录接口
    public Teacher teacherLogin(Teacher teacher);
    //验证用户名是否被注册
    public Teacher checkUserName(Teacher teacher);
    //老师注册
    public int signUp(Teacher teacher);
    //业务管理员登录
    public SenManager senManagerLogin(SenManager senManager);
    //添加业务管理员
    public int addSenManager(SenManager senManager);

    public int updateManager(SenManager senManager);
    //删除业务管理员
    public int delSenManager(SenManager senManager);
    //超级管理员登陆
    public PriManager superManagerLogin(PriManager priManager);
}
