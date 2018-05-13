package asset.controller;

import asset.pojo.PriManager;
import asset.pojo.SenManager;
import asset.pojo.Teacher;
import asset.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;


/**
 * Created by su on 18-2-5.
 */
@Controller
@RequestMapping("/asset")
public class LoginController {
    private static Logger logger = Logger.getLogger(LoginController.class);
    @Autowired
    private IUserService userService;
    private Teacher teacher=new Teacher();
    private SenManager senManager=new SenManager();
    private PriManager priManager = new PriManager();
    //老师登录
    @RequestMapping(value = "/teacherLogin.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object teacherLogin(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String username = new String(request.getParameter("username").getBytes("iso-8859-1"),"utf-8");
        String password = request.getParameter("password").trim();

        teacher.setName(username);
        teacher.setPassword(password);

        Teacher res = userService.teacherLogin(teacher);
//        System.out.println(res.getPassword());
        if (res!=null){
            if (res.getPassword().equals(password)) {
                Cookie uname=new Cookie("username",res.getName());
                Cookie identify=new Cookie("identify","js");
                identify.setPath("/");
                uname.setPath("/");
                identify.setMaxAge(60*60*24);
                uname.setMaxAge(60*60*24);
                response.addCookie(uname);
                response.addCookie(identify);
                return "login success";
            } else {
                return "login failure";
            }
        }else{
            return "not register";
        }

    }
    //业务管理员登录
    @RequestMapping(value = "/senMangerLogin.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object senMangerLogin(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String username = new String(request.getParameter("username").getBytes("iso-8859-1"),"utf-8");
        String password = request.getParameter("password").trim();
        senManager.setName(username);
        senManager.setPassword(password);
        SenManager senManager1=userService.senManagerLogin(senManager);
        if (senManager1!=null) {
            if (senManager1.getPassword().equals(password)) {
                Cookie uname = new Cookie("username", senManager1.getName());
                Cookie identify = new Cookie("identify", "yw");
                identify.setPath("/");
                uname.setPath("/");
                identify.setMaxAge(60 * 60 * 24);
                uname.setMaxAge(60 * 60 * 24);
                response.addCookie(uname);
                response.addCookie(identify);
                return "login success";
            } else {
                return "login failure";
            }
        }else {
            return "not register";
        }
    }
    //超级管理员登录
    @RequestMapping(value = "/priMangerLogin.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object priMangerLogin(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
        String username = new String(request.getParameter("username").getBytes("iso-8859-1"),"utf-8");
        String password = request.getParameter("password").trim();
        priManager.setName(username);
        priManager.setPassword(password);
        PriManager priManager1 = userService.superManagerLogin(priManager);
        if (priManager1 != null) {
            if (priManager1.getPassword().equals(password)) {
                Cookie uname = new Cookie("username", priManager1.getName());
                Cookie identify = new Cookie("identify", "super");
                identify.setPath("/");
                uname.setPath("/");
                identify.setMaxAge(60 * 60 * 24);
                uname.setMaxAge(60 * 60 * 24);
                response.addCookie(uname);
                response.addCookie(identify);
                return "login success";
            } else {
                return "login failure";
            }
        }else {
            return "not register";
        }
    }
}
