package asset.controller;

import asset.pojo.Unit;
import asset.service.IUnitService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * Author:QuincySu
 * Date:Created in 2018/3/26
 */
@Controller
@RequestMapping("/asset")
public class CheckUnitNameController {
    private static Logger logger = Logger.getLogger(CheckUnitNameController.class);
    @Autowired
    private IUnitService unitService;
    private Unit unit;
    @RequestMapping(value = "/checkUnitName.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object checkUnitName(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String unitName = new String(request.getParameter("unitName").getBytes("iso-8859-1"),"utf-8");
        logger.info(unitName);
        unit=new Unit();
        unit.setUnitName(unitName);
        Unit unit1=unitService.getUnitName(unit);
        if (unit1==null){
            return "check failure";
        }
        else{
            return "";
        }
    }
}
