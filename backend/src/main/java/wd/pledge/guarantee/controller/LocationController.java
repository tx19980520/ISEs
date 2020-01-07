package wd.pledge.guarantee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wd.pledge.guarantee.entity.Location;
import wd.pledge.guarantee.service.LocationService;

@Controller
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/all")
    @ResponseBody
    public Iterable<Location> getLocations()
    {
        return locationService.getLocations();
    }

}
