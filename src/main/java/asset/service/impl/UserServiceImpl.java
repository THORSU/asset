package asset.service.impl;

import asset.mapper.UserMapper;
import asset.pojo.SenManager;
import asset.pojo.Teacher;
import asset.pojo.Unit;
import asset.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by su on 18-2-5.
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public Teacher teacherLogin(Teacher teacher) {
        return userMapper.teacherLogin(teacher);
    }

    @Override
    public Teacher checkUserName(Teacher teacher) {
        return userMapper.checkUserName(teacher);
    }

    @Override
    public int signUp(Teacher teacher) {
        return userMapper.signUp(teacher);
    }

    @Override
    public SenManager senManagerLogin(SenManager senManager) {
        return userMapper.senManagerLogin(senManager);
    }

    @Override
    public int addSenManger(SenManager senManager) {
        return userMapper.addSenManger(senManager);
    }

    @Override
    public int delSenManger(SenManager senManager) {
        return userMapper.delSenManger(senManager);
    }
}
