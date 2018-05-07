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

/**
 * @Author: QuincySu
 * @Date: 2018/5/7
 */
@Controller
@RequestMapping("/asset")
public class AddUnitController {
    private static Logger logger = Logger.getLogger(AddUnitController.class);
    @Autowired
    private IUnitService unitService;
    private Unit unit = new Unit();

    @RequestMapping(value = "/addUnit.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object addUnit(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String unitId = new String(request.getParameter("unitId").getBytes("iso-8859-1"), "utf-8");
        String unitName = new String(request.getParameter("unitName").getBytes("iso-8859-1"), "utf-8");
        unit.setUnitId(unitId);
        unit.setUnitName(unitName);
        Unit n1 = unitService.getUnitId(unit);
        Unit n2 = unitService.getUnitName(unit);
        if (n1 != null || n2 != null) {
            return "already exist";
        }
        int num = unitService.addUnit(unit);
        if (num == 1) {
            return "add success";
        } else {
            return "add fail";
        }
    }
}
