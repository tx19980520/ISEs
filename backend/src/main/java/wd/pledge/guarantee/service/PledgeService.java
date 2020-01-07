package wd.pledge.guarantee.service;

import com.alibaba.fastjson.JSONObject;
import wd.pledge.guarantee.entity.Pledge;
import wd.pledge.guarantee.util.PhysicalState;

public interface PledgeService {

    public String setInWarehoused(Integer pledgeId);

    public String setExWarehousing(Integer pledgeId);

    public String setExWarehoused(Integer pledgeId);

    public String createPledge(JSONObject jsonObject);

    public void setPhysicalState(Integer id, PhysicalState physicalState);

    Pledge get_one_pledge_info(Integer pledgeid);

    Iterable<Pledge> findAll();

    String confirmLocation(JSONObject jsonObject);

}
