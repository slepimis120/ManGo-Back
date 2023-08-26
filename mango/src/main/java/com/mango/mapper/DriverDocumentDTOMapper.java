package com.mango.mapper;

import com.mango.dto.DriverDocumentDTO;
import com.mango.model.DriverDocument;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class DriverDocumentDTOMapper {
	 private static ModelMapper modelMapper;

	    @Autowired
	    public DriverDocumentDTOMapper(ModelMapper modelMapper) {
	        DriverDocumentDTOMapper.modelMapper = modelMapper;
	    }
	    public static DriverDocument fromDTOtoDriverDocument(DriverDocumentDTO dto) {
	        return modelMapper.map(dto, DriverDocument.class);
	    }

	    public static DriverDocumentDTO fromDriverDocumenttoDTO(DriverDocument dto) {
	        return modelMapper.map(dto, DriverDocumentDTO.class);
	    }
}
