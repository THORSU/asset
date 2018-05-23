package asset.controller;

import asset.pojo.SenManager;
import asset.pojo.Teacher;
import asset.pojo.Unit;
import asset.service.IUnitService;
import asset.service.IUserService;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

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

    //教师注册
    @RequestMapping(value = "/SignUp.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object SignUp(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String username = new String(request.getParameter("username").getBytes("iso-8859-1"),"utf-8");
        String password = request.getParameter("password").trim();
        String password1 = request.getParameter("password1").trim();
        String unitName = new String(request.getParameter("unit").getBytes("iso-8859-1"),"utf-8");
        unit=new Unit();
        unit.setUnitName(unitName);
        Unit res1=unitService.getUnitId(unit);
//        System.out.println(res1.getUnitId());
        if (!password1.equals(password)) {
            return "password error";
        }
        if (res1==null){
            return "search unitId fail";
        }else {
            teacher=new Teacher();
            teacher.setUnitId(res1.getUnitId());
            teacher.setName(username);
            teacher.setPassword(password);
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
        System.out.println(JSON.toJSONString(senManager1));
        //todo 状态位（0：账号正常，1：已删除）
        senManager.setStatus("0");
        if (senManager1==null){
//            senManager.setId(RandomAccessUtil.getRandom("SenManger"));
            int num = userService.addSenManager(senManager);
            if (num==1){
                return "add success";
            }else {
                return "add fail";
            }
        } else if ("1".equals(senManager1.getStatus())) {
            int num = userService.updateManager(senManager);
            if (num == 1) {
                return "add success";
            } else {
                return "add fail";
            }
        } else {
            return "manager exist";
        }
    }
    //删除业务管理员
    @RequestMapping(value = "/delSenManger.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object delSenManger(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String username = new String(request.getParameter("username").getBytes("iso-8859-1"), "utf-8");
        senManager.setName(username);
        //是否已存在
        SenManager senManager1 = userService.senManagerLogin(senManager);
        if (senManager1 != null && "0".equals(senManager1.getStatus())) {
            //todo 此处暂定为逻辑删除
            senManager.setStatus("1");
            int num = userService.delSenManager(senManager);
            if (num == 1) {
                return "del success";
            } else {
                return "del fail";
            }
        } else if (senManager1 != null && "1".equals(senManager1.getStatus())) {
            return "already del";
        } else {
            return "del fail";
        }
    }
}
