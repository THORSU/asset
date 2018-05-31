package asset.controller;

import asset.pojo.Unit;
import asset.service.IUnitService;
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
public class DelUnitController {
    private static Logger logger = Logger.getLogger(DelUnitController.class);
    @Autowired
    private IUnitService unitService;

    private Unit unit = new Unit();

    //删除单位信息
    @RequestMapping(value = "/delUnit.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object addUnit(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String unitName = new String(request.getParameter("unitName").getBytes("iso-8859-1"), "utf-8");
        unit.setUnitName(unitName);
        Unit res = unitService.getUnitId(unit);
        if (res == null) {
            return "unitName error";
        } else {
            int num = unitService.delUnit(unit);
            if (num == 1) {
                return "unit del success";
            } else {
                return "unit del fail";
            }
        }
    }
}
