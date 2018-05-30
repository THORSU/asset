package asset.controller;

import asset.pojo.Unit;
import asset.service.IUnitService;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: QuincySu
 * @Date: 2018/5/30
 */
@Controller
@RequestMapping("/asset")
public class QueryUnitController {
    private static Logger logger = Logger.getLogger(QueryUnitController.class);
    @Autowired
    private IUnitService unitService;

    private List<Unit> unitList = new ArrayList<>();

    //查询单位信息
    @RequestMapping(value = "/queryUnitList.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object queryUnitList(HttpServletRequest request, HttpServletResponse response) {
        unitList = unitService.getUnitList();
        return JSON.toJSONString(unitList);
    }
}
