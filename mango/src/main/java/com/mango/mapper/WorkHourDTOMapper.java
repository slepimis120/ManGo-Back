package com.mango.mapper;

import com.mango.dto.WorkHourDTO;
import com.mango.model.WorkHour;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class WorkHourDTOMapper {
    private static ModelMapper modelMapper;

    @Autowired
    public WorkHourDTOMapper(ModelMapper modelMapper) {
    	WorkHourDTOMapper.modelMapper = modelMapper;
    }
    public static WorkHour fromDTOtoWorkHour(WorkHourDTO dto) {
        return modelMapper.map(dto, WorkHour.class);
    }

    public static WorkHourDTO fromWorkHourtoDTO(WorkHour dto) {
        return modelMapper.map(dto, WorkHourDTO.class);
    }
}
