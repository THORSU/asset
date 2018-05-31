package asset.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


@Controller
@RequestMapping("/asset")
public class CookieController {
    private static Logger logger = Logger.getLogger(CookieController.class);
    @RequestMapping(value = "/getCookie.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object getCookie(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        String username="";
        String identify="";
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().trim().equals("username")) {
                    try {
                        username = URLDecoder.decode(c.getValue().trim(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        logger.error("-----转码失败！");
                    }
                }
               else if (c.getName().trim().equals("identify")) {
                    try {
                        identify = URLDecoder.decode(c.getValue().trim(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        logger.error("-----转码失败！");
                    }
                }
            }
            if (username.equals("")||identify.equals("")) {
                logger.error("---cookie失效！");
                return "0";
            } else {
                return username+"$&"+identify;
            }
        } else {
            logger.error("---cookie失效！");
            return "0";
        }
    }
}
