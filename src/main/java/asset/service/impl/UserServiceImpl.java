package asset.service.impl;

import asset.mapper.UserMapper;
import asset.pojo.PriManager;
import asset.pojo.SenManager;
import asset.pojo.Teacher;
import asset.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public int addSenManager(SenManager senManager) {
        return userMapper.addSenManager(senManager);
    }

    @Override
    public int updateManager(SenManager senManager) {
        return userMapper.updateManager(senManager);
    }

    @Override
    public int delSenManager(SenManager senManager) {
        return userMapper.delSenManager(senManager);
    }

    @Override
    public PriManager superManagerLogin(PriManager priManager) {
        return userMapper.superManagerLogin(priManager);
    }

    @Override
    public String getUnitId(String username) {
        return userMapper.getUnitId(username);
    }

    @Override
    public List<SenManager> getManagerList() {
        return userMapper.getManagerList();
    }
}
