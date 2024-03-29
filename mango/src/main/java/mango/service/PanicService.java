package mango.service;

import mango.dto.PanicResponseDTO;
import mango.model.Panic;
import mango.repository.PanicRepository;
import mango.service.interfaces.IPanicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PanicService{

    @Autowired
    private PanicRepository panicRepository;
    DriverService driverService;

    @Autowired
    public PanicService(DriverService driverService){
        this.driverService = driverService;
    }


    public PanicResponseDTO getAllResponse() {
        PanicResponseDTO response = new PanicResponseDTO();
        response.setTotalCount(this.getAll().size());
        response.setResults(this.getAll());
        return response;
    }

    public List<Panic> getAll(){
        return panicRepository.findAll();
    }

    public void insertNewPanic(Panic panic){
        panicRepository.save(panic);
    }
}
