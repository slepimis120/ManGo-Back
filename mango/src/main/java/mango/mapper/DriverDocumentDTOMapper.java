package mango.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import mango.dto.DriverDocumentDTO;
import mango.dto.LocationDTO;
import mango.model.DriverDocument;
import mango.model.Location;

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
