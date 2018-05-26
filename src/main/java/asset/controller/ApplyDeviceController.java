package asset.controller;

import asset.pojo.ApplyForm;
import asset.pojo.DeviceForm;
import asset.pojo.Unit;
import asset.service.IDeviceService;
import asset.service.IUnitService;
import asset.service.IUserService;
import asset.utils.DataUtil;
import com.alibaba.fastjson.JSON;
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
import java.util.List;

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
    @Autowired
    private IUserService userService;

    private DeviceForm deviceForm = new DeviceForm();
    private ApplyForm applyForm = new ApplyForm();
    private Unit unit = new Unit();

    @RequestMapping(value = "/applyDevice.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object applyDevice(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
//        String username = new String(request.getParameter("username").getBytes("iso-8859-1"), "utf-8");
        //todo 改为自动获取单位名
//        String unitName = new String(request.getParameter("unitName").getBytes("iso-8859-1"), "utf-8");
        String deviceName = new String(request.getParameter("deviceName").getBytes("iso-8859-1"), "utf-8");
        final Cookie[] cookies = request.getCookies();
        String username = "";
        if (cookies != null) {
            for (final Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    username = cookie.getValue();
                }
            }
        }
        deviceForm.setDeviceName(deviceName);
        DeviceForm deviceForm1=new DeviceForm();
        List<DeviceForm> deviceFormList = deviceService.getDeviceList(deviceForm);
        for (int i=0;i<deviceFormList.size();i++) {
            if ("1".equals(deviceFormList.get(i).getUseStatus())) {
                System.out.println(deviceFormList.get(i).toString());
                deviceForm1=deviceFormList.get(i);
            }

        }
        System.out.println(JSON.toJSONString(deviceForm1));
        System.out.println(deviceForm1.getDeviceId());

        String deviceId=deviceForm1.getDeviceId();
        applyForm.setDeviceId(deviceId);
        applyForm.setDeviceName(deviceName);

        //根据用户名获取单位id
        String unitId = userService.getUnitId(username);
        unit.setUnitId(unitId);
        //获取单位名
        Unit unit1 = unitService.getUnitName(unit);
        if (unit1 != null) {
            applyForm.setUnitId(unit1.getUnitId());
            applyForm.setUnitName(unit1.getUnitName());
        }
        applyForm.setApplyName(username);
//        applyForm.setId(RandomAccessUtil.getRandom("Apply"));
        String time= DataUtil.currentDate("yyyy-MM-dd HH:mm:ss");
        applyForm.setApplyTime(time);
        applyForm.setAuditName("");
        applyForm.setAuditTime("");
        Integer num = deviceService.applyDevice(applyForm);
        deviceForm1.setUseStatus("3");
        int num1 = deviceService.modifyStatus(deviceForm1);
        if (num == 1 && num1 == 1) {
            return "apply success";
        } else {
            return "apply fail";
        }

    }
}
