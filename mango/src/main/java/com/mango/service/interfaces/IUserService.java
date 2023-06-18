package com.mango.service.interfaces;

import com.mango.dto.ExpandedUserDTO;
import com.mango.dto.UserDTO;
import com.mango.dto.UserResponseDTO;

public interface IUserService {

	public UserResponseDTO getArray(Integer page, Integer size);
	
	public UserDTO find(Integer id);

	public UserDTO update(Integer id, ExpandedUserDTO update);



}
