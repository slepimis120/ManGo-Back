package com.mango.mapper;

import com.mango.dto.LocationDTO;
import com.mango.model.Location;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class LocationDTOMapper {

    private static ModelMapper modelMapper;

    @Autowired
    public LocationDTOMapper(ModelMapper modelMapper) {
        LocationDTOMapper.modelMapper = modelMapper;
    }
    public static Location fromDTOtoLocation(LocationDTO dto) {
        return modelMapper.map(dto, Location.class);
    }

    public static LocationDTO fromLocationtoDTO(Location dto) {
        return modelMapper.map(dto, LocationDTO.class);
    }
}
