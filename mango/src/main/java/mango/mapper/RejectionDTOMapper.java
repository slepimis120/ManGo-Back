package mango.mapper;

import mango.dto.CreateRideDTO;
import mango.dto.RejectionDTO;
import mango.model.Rejection;
import mango.model.Ride;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class RejectionDTOMapper {

    private static ModelMapper modelMapper;

    @Autowired
    public RejectionDTOMapper(ModelMapper modelMapper) {
        RejectionDTOMapper.modelMapper = modelMapper;
    }
    public static Rejection fromDTOtoRejection(RejectionDTO dto) {
        return modelMapper.map(dto, Rejection.class);
    }

    public static RejectionDTO fromRejectiontoDTO(Ride dto) {
        return modelMapper.map(dto, RejectionDTO.class);
    }

}
