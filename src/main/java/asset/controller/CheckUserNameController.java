package asset.controller;

import asset.pojo.Teacher;
import asset.service.IUserService;
import asset.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/asset")
public class CheckUserNameController {
    private static Logger logger = Logger.getLogger(CheckUserNameController.class);
    @Autowired
    private IUserService userService;
    private Teacher teacher;
    @RequestMapping(value = "/CheckUserName.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object CheckUserName(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String username = new String(request.getParameter("username").getBytes("iso-8859-1"),"utf-8");
        logger.info(username);
        teacher=new Teacher();
        teacher.setName(username);
        Teacher res=userService.checkUserName(teacher);
        if (res==null){
            return "check success";
        }else{
            return "check failure";
        }
    }
}
