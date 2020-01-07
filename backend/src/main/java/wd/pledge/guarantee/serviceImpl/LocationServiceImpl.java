package wd.pledge.guarantee.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wd.pledge.guarantee.entity.Location;
import wd.pledge.guarantee.repository.LocationRepository;
import wd.pledge.guarantee.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public Iterable<Location> getLocations()
    {
        return locationRepository.findAll();
    }
}
