package mango.mapper;

import mango.dto.CreateRideDTO;
import mango.dto.ResponseRideDTO;
import mango.model.Ride;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class RideDTOMapper {

    private static ModelMapper modelMapper;

    @Autowired
    public RideDTOMapper(ModelMapper modelMapper) {
        RideDTOMapper.modelMapper = modelMapper;
    }
    public static Ride fromCreateDTOtoRide(CreateRideDTO dto) {
        return modelMapper.map(dto, Ride.class);
    }

    public static CreateRideDTO fromRidetoCreateDTO(Ride dto) {
        return modelMapper.map(dto, CreateRideDTO.class);
    }

    public static Ride fromResponseDTOtoRide(ResponseRideDTO dto) {
        return modelMapper.map(dto, Ride.class);
    }

    public static ResponseRideDTO fromRidetoResponseDTO(Ride dto) {
        return modelMapper.map(dto, ResponseRideDTO.class);
    }

}
