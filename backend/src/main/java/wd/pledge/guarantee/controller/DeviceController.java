package wd.pledge.guarantee.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wd.pledge.guarantee.service.DeviceService;
import wd.pledge.guarantee.util.PhysicalState;

import javax.validation.Valid;

//  just for test

@Controller
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping(value = "/test")
    @ResponseBody
    public String changeTest(@Valid @RequestBody JSONObject jsonObject) {

        System.out.println(jsonObject.getString("physicalState"));
        String ss = jsonObject.getString("physicalState");
        PhysicalState physicalState = PhysicalState.valueOf(ss);
        Integer deviceId = jsonObject.getInteger("deviceId");
        Integer pledgeId = jsonObject.getInteger("pledgeId");
        deviceService.changePledgeStatus(deviceId,pledgeId,physicalState);
        return ss;
        /*
        if (deviceService.changePledgeStatus(deviceId,pledgeId,physicalState)) {
            return "yes!";
        }
        else return "no!";

         */
    }
}
