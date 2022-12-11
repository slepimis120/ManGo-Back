package mango.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import mango.dto.PassengerDTO;
import mango.model.Passenger;

public class PassengerDTOMapper {

	private static ModelMapper modelMapper;

    @Autowired
    public PassengerDTOMapper(ModelMapper modelMapper) {
    	PassengerDTOMapper.modelMapper = modelMapper;
    }
    public static Passenger fromDTOtoPassenger(PassengerDTO dto) {
        return modelMapper.map(dto, Passenger.class);
    }

    public static PassengerDTO fromPassengertoDTO(Passenger dto) {
        return modelMapper.map(dto, PassengerDTO.class);
    }
}
