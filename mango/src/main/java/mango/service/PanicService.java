package mango.service;

import mango.model.Panic;
import mango.model.PanicResponse;
import mango.model.Ride;
import mango.model.User;
import mango.service.interfaces.IPanicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;
import java.util.concurrent.Executors;

@Service
public class PanicService implements IPanicService {
    private Map<Integer, Panic> allPanic = new HashMap<Integer, Panic>();
    DriverService driverService;

    @Autowired
    public PanicService(DriverService driverService){
        this.driverService = driverService;
    }


    @Override
    public PanicResponse getAll() {
        PanicResponse response = new PanicResponse();
        response.setTotalCount(allPanic.size());
        List<Panic> list = new ArrayList<Panic>(allPanic.values());
        response.setResults(list);
        return response;
    }

    public Map<Integer, Panic> getAllPanic(){
        return allPanic;
    }
}
