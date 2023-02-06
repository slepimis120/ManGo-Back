package mango.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import mango.dto.UserDTO;
import mango.model.User;

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
