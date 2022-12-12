package mango.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import mango.dto.RejectionDTO;
import mango.dto.WorkHourDTO;
import mango.model.Rejection;
import mango.model.Ride;
import mango.model.WorkHour;

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
