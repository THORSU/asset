package asset.controller;

import asset.pojo.SenManager;
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
import java.util.List;

/**
 * @author: QuincySu
 * @Date: 2018/5/31
 */
@Controller
@RequestMapping("/asset")
public class QueryMangerController {
    private static Logger logger = Logger.getLogger(QueryMangerController.class);
    @Autowired
    private IUserService userService;

    //查询业务管理员
    @RequestMapping(value = "/queryManagerList.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object queryManagerList(HttpServletRequest request, HttpServletResponse response) {
        List<SenManager> res = userService.getManagerList();
//        res.forEach(senManager -> {
//            if (senManager.getStatus().equals("0")){
//                return;
//            }
//        });
//    }
        return JSON.toJSONString(res);
    }
}
