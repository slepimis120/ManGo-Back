package com.mango.mapper;

import com.mango.dto.UserDTO;
import com.mango.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDTOMapper {

	private static ModelMapper modelMapper;

    @Autowired
    public UserDTOMapper(ModelMapper modelMapper) {
    	UserDTOMapper.modelMapper = modelMapper;
    }
    public static User fromDTOtoUser(UserDTO dto) {
        return modelMapper.map(dto, User.class);
    }

    public static UserDTO fromUsertoDTO(User dto) {
        return modelMapper.map(dto, UserDTO.class);
    }
}
