package asset.controller;

import asset.pojo.DeviceForm;
import asset.service.IDeviceService;
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
 * Author:QuincySu
 * Date:Created in 2018/3/26
 */
@Controller
@RequestMapping("asset")
public class CheckDeviceNameController {
    private static Logger logger = Logger.getLogger(CheckDeviceNameController.class);
    @Autowired
    private IDeviceService deviceService;
    private DeviceForm deviceForm;
    @RequestMapping(value = "/checkDeviceName.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object checkDeviceName(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
        String deviceName = new String(request.getParameter("deviceName").getBytes("iso-8859-1"),"utf-8");
        logger.info(deviceName);
        deviceForm = new DeviceForm();
        deviceForm.setDeviceName(deviceName);
        DeviceForm deviceForm1=deviceService.getDevice(deviceForm);
        if (deviceForm1==null){
            return "check failure";
        }
        else{
            return "";
        }

    }
}
