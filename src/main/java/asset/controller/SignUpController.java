package asset.controller;

import asset.mapper.UserMapper;
import asset.pojo.SenManager;
import asset.pojo.Teacher;
import asset.pojo.Unit;
import asset.service.IUnitService;
import asset.service.IUserService;
import asset.service.impl.UserServiceImpl;
import asset.utils.RandomAccessUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * Author:QuincySu
 * Date:Created in 2018/3/22
 */
@Controller
@RequestMapping("/asset")
public class SignUpController {
    private static Logger logger = Logger.getLogger(SignUpController.class);
    @Autowired
    private IUserService userService;
    private Teacher teacher;
    private Unit unit;
    private SenManager senManager=new SenManager();
    @Autowired
    private IUnitService unitService;
    @RequestMapping(value = "/SignUp.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object SignUp(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String username = new String(request.getParameter("username").getBytes("iso-8859-1"),"utf-8");
        String password = request.getParameter("password").trim();
        String unitName = new String(request.getParameter("unit").getBytes("iso-8859-1"),"utf-8");
        unit=new Unit();
        unit.setUnitName(unitName);
        Unit res1=unitService.getUnitId(unit);
        System.out.println(res1.getUnitId());
        if (res1==null){
            return "search unitId fail";
        }else {
            teacher=new Teacher();
            teacher.setUnitId(res1.getUnitId());
            teacher.setName(username);
            teacher.setPassword(password);
            teacher.setId(RandomAccessUtil.getRandom("Teacher"));
            int res=userService.signUp(teacher);
            if (res==1){
                logger.info(res);
                return "SignUp success";
            }else{
                logger.error(res);
                return "SignUp fail";
            }
        }
    }
    //添加业务管理员
    @RequestMapping(value = "/addSenManger.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object addSenManger(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
        String username = new String(request.getParameter("username").getBytes("iso-8859-1"),"utf-8");
        String password = request.getParameter("password").trim();
        senManager.setName(username);
        senManager.setPassword(password);
        //是否已存在
        SenManager senManager1=userService.senManagerLogin(senManager);
        if (senManager1==null){
            senManager.setId(RandomAccessUtil.getRandom("SenManger"));
            int num=userService.addSenManger(senManager1);
            if (num==1){
                return "add success";
            }else {
                return "add fail";
            }
        }else{
            return "manager exist";
        }
    }
    //删除业务管理员
    @RequestMapping(value = "/delSenManger.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object delSenManger(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
        String username = new String(request.getParameter("username").getBytes("iso-8859-1"),"utf-8");
        senManager.setName(username);
        int num= userService.delSenManger(senManager);
        if (num==1){
            return "del success";
        }else{
            return "del fail";
        }
    }
}
