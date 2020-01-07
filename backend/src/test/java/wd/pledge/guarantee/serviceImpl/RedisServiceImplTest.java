package wd.pledge.guarantee.serviceImpl;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wd.pledge.guarantee.GuaranteeApplication;
import wd.pledge.guarantee.util.PhysicalState;
import wd.pledge.guarantee.service.RedisService;
import org.junit.runner.RunWith;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GuaranteeApplication.class)
class RedisServiceImplTest {

    @Autowired
    private RedisService redisService;

    @Test
    void testRedis() {
        String id = "equipment1";
        String state = "stable";

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
        PhysicalState ret = (PhysicalState) redisService.get(id);

        Assert.assertEquals(PhysicalState.STABLE, ret);
    }
}