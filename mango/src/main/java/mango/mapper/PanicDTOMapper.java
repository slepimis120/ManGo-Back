package mango.mapper;

import mango.dto.CreateRideDTO;
import mango.dto.PanicDTO;
import mango.dto.RejectionDTO;
import mango.model.Panic;
import mango.model.Rejection;
import mango.model.Ride;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class PanicDTOMapper {

    private static ModelMapper modelMapper;

    @Autowired
    public PanicDTOMapper(ModelMapper modelMapper) {
        PanicDTOMapper.modelMapper = modelMapper;
    }
    public static Panic fromDTOtoPanic(PanicDTO dto) {
        return modelMapper.map(dto, Panic.class);
    }

    public static PanicDTO fromPanictoDTO(Panic dto) {
        return modelMapper.map(dto, PanicDTO.class);
    }


}
