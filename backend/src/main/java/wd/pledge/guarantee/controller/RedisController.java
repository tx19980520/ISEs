package wd.pledge.guarantee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wd.pledge.guarantee.util.PhysicalState;
import wd.pledge.guarantee.service.RedisService;

@RestController
@RequestMapping("redis")
public class RedisController {
    @Autowired
    private RedisService redisService;

    @RequestMapping("/setPhysicalState")
    public int setPhysicalState(String id, String state) {
        PhysicalState pstate = PhysicalState.EMPTY;

        switch (state) {
            case "stable":
                pstate = PhysicalState.STABLE;
                break;
            case "shocking":
                pstate = PhysicalState.SHOCKING;
                break;
            case "moving":
                pstate = PhysicalState.MOVING;
                break;
            case "empty":
                pstate = PhysicalState.EMPTY;
                break;
            default:
                break;
        }

        redisService.set(id, pstate);
        return 0;
    }


}
