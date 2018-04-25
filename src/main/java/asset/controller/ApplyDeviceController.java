package asset.controller;

import asset.pojo.ApplyForm;
import asset.pojo.DeviceForm;
import asset.pojo.Unit;
import asset.service.IDeviceService;
import asset.service.IUnitService;
import asset.utils.RandomAccessUtil;
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
 * Author:QuincySu
 * Date:Created in 2018/3/26
 */
@Controller
@RequestMapping("/asset")
public class ApplyDeviceController {
    private static Logger logger = Logger.getLogger(ApplyDeviceController.class);
    @Autowired
    private IDeviceService deviceService;
    @Autowired
    private IUnitService unitService;

    private DeviceForm deviceForm;
    private ApplyForm applyForm;
    private Unit unit;

    @RequestMapping(value = "/applyDevice.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object applyDevice(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
//        String username = new String(request.getParameter("username").getBytes("iso-8859-1"), "utf-8");
        String unitName = new String(request.getParameter("unitName").getBytes("iso-8859-1"), "utf-8");
        String deviceName = new String(request.getParameter("deviceName").getBytes("iso-8859-1"), "utf-8");
        final Cookie[] cookies = request.getCookies();
        String username="";
        if (cookies!=null){
            for (final Cookie cookie:cookies){
                if ("username".equals(cookie.getName())){
                    username=cookie.getValue();
                }
            }
        }
        deviceForm = new DeviceForm();
        deviceForm.setDeviceName(deviceName);
        DeviceForm deviceForm1 = deviceService.getDevice(deviceForm);

        unit = new Unit();
        unit.setUnitName(unitName);
        Unit unit1 = unitService.getUnitId(unit);

        applyForm = new ApplyForm();
        applyForm.setDeviceName(deviceName);
        applyForm.setUnitName(unitName);

        applyForm.setApplyName(username);

        if (unit1 != null) {
            applyForm.setUnitId(unit1.getUnitId());
        }
        if (deviceForm1 != null) {
            applyForm.setDeviceId(deviceForm1.getDeviceId());
        }
        applyForm.setId(RandomAccessUtil.getRandom("Apply"));
        if ("1".equals(deviceForm1.getUseStatus())) {
            Integer num = deviceService.applyDevice(applyForm);
            deviceForm.setUseStatus("3");
            int num1=deviceService.modifyStatus(deviceForm);
            if (num == 1 && num1 == 1) {
                return "apply success";
            } else {
                return "apply fail";
            }
        } else {
            return "useStatus error";
        }
    }
}
