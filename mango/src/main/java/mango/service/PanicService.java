package mango.service;

import mango.dto.PanicResponseDTO;
import mango.model.Panic;
import mango.service.interfaces.IPanicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PanicService implements IPanicService {
    private Map<Integer, Panic> allPanic = new HashMap<Integer, Panic>();
    DriverService driverService;

    @Autowired
    public PanicService(DriverService driverService){
        this.driverService = driverService;
    }


    @Override
    public PanicResponseDTO getAll() {
        PanicResponseDTO response = new PanicResponseDTO();
        response.setTotalCount(allPanic.size());
        List<Panic> list = new ArrayList<Panic>(allPanic.values());
        response.setResults(list);
        return response;
    }

    public Map<Integer, Panic> getAllPanic(){
        return allPanic;
    }
}
