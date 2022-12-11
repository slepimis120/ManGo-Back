package mango.service;
import mango.dto.DriverDocumentDTO;
import mango.dto.ExpandedUserDTO;
import mango.dto.UserDTO;
import mango.dto.UserResponseDTO;
import mango.dto.WorkHourDTO;
import mango.dto.WorkHourResponseDTO;
import mango.mapper.DriverDocumentDTOMapper;
import mango.mapper.LocationDTOMapper;
import mango.mapper.WorkHourDTOMapper;
import mango.model.Driver;
import mango.model.DriverDocument;
import mango.model.Location;
import mango.model.WorkHour;
import mango.service.interfaces.IUserService;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DriverService implements IUserService {

	public static Map<Integer, Driver> allDrivers = new HashMap<Integer, Driver>();
	public static Map<Integer, DriverDocument> allDocuments = new HashMap<Integer, DriverDocument>();
	public static Map<Integer, WorkHour> allWorkHours = new HashMap<Integer, WorkHour>();
	
	
	@Override
	public UserResponseDTO getArray(Integer page, Integer size) {
		int start = (page - 1) * size;
		int end = page * size;
		ArrayList<UserDTO> returnList = new ArrayList<UserDTO>();
		int i = 0;
		for (Map.Entry<Integer, Driver> entry : allDrivers.entrySet()) {
			if(i >= start && i < end){
				Driver currentDriver = entry.getValue();
				UserDTO currentUserDTO = new UserDTO(currentDriver.getId(), currentDriver.getName(), currentDriver.getSurname(),
						currentDriver.getProfilePicture(), currentDriver.getTelephoneNumber(), currentDriver.getEmail(), currentDriver.getAddress());
				returnList.add(currentUserDTO);
			}
			i++;
		}
		return new UserResponseDTO(allDrivers.size(), returnList);
	}

	@Override
	public UserDTO find(Integer id) {
		Driver driver = allDrivers.get(id);
		if (driver != null) {
			UserDTO userDTO = new UserDTO(driver);
			return userDTO;
		}
		return null;
	}

	@Override
	public UserDTO insert(ExpandedUserDTO data) {
		Driver driver = new Driver(data);
		int size = UserService.allUsers.size();
		driver.setId(size + 1);
		allDrivers.put(driver.getId(), driver);
		UserService.allUsers.put(driver.getId(), driver);
		return new UserDTO(driver);
	}

	@Override
	public UserDTO update(Integer id, ExpandedUserDTO update) {
		Driver driver = allDrivers.get(id);
		if (driver != null) {
			driver.setName(update.getName());
			driver.setSurname(update.getSurname());
			driver.setAddress(update.getAddress());
			driver.setEmail(update.getEmail());
			driver.setPassword(update.getPassword());
			if(update.getProfilePicture() != null) 
				driver.setProfilePicture(update.getProfilePicture());
			if(update.getTelephoneNumber() != null)
				driver.setTelephoneNumber(update.getTelephoneNumber());
			return new UserDTO(driver);
			
		}
		throw new RuntimeException();
	}
	
	

	public ArrayList<DriverDocumentDTO> findDocuments(Integer id) {
		ArrayList<DriverDocumentDTO> documents = new ArrayList<DriverDocumentDTO>();
		for(Map.Entry<Integer, DriverDocument> entry : allDocuments.entrySet()) {
			if(entry.getValue().getDriverId() == id){
				DriverDocumentDTOMapper mapper = new DriverDocumentDTOMapper(new ModelMapper());
				DriverDocumentDTO newDriverDocument = mapper.fromDriverDocumenttoDTO(entry.getValue());
				documents.add(newDriverDocument);
			}
		}
		return documents;
	}
	
	public DriverDocumentDTO insertDocument(Integer id, String name, String documentImage) {
		int size = allDocuments.size();
		size++;
		DriverDocument document = new DriverDocument(size, name, documentImage, id);
		allDocuments.put(size, document);
		DriverDocumentDTOMapper mapper = new DriverDocumentDTOMapper(new ModelMapper());
		DriverDocumentDTO newDriverDocument = mapper.fromDriverDocumenttoDTO(document);
		return newDriverDocument;
	}

	public DriverDocument deleteDocument(Integer id) {
		return allDocuments.remove(id);
		
	}
	
	public WorkHourResponseDTO findWorkHours(Integer id, Integer page, Integer size, String from, String to) {
		WorkHour tmp = null;
        WorkHourDTOMapper mapper = new WorkHourDTOMapper(new ModelMapper());
		ArrayList<WorkHourDTO> workHours = new ArrayList<WorkHourDTO>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm:ssZ");
		LocalDateTime fromTime = LocalDateTime.parse(from);
		LocalDateTime toTime = LocalDateTime.parse(to);
		
		for(Map.Entry<Integer, WorkHour> entry : allWorkHours.entrySet()) {
			tmp = entry.getValue();
			if(tmp.getDriverId() == id){
				if((tmp.getStart().isAfter(fromTime) || tmp.getStart().isEqual(fromTime)) &&
						(tmp.getEnd().isBefore(toTime) || tmp.getEnd().isEqual(toTime))) {
			        WorkHourDTO newWorkHour = mapper.fromWorkHourtoDTO(tmp);
					workHours.add(newWorkHour);
				}
			}
		}
		WorkHourResponseDTO response = new WorkHourResponseDTO(workHours, workHours.size());
		return response;
	}
	
	public WorkHourDTO insertWorkHour(Integer idDriver, Integer id, String start, String end) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm:ssZ");
		LocalDateTime startTime = LocalDateTime.parse(start);
		LocalDateTime endTime = LocalDateTime.parse(end);
		WorkHour workHour = new WorkHour(id, startTime, endTime, idDriver);
		allWorkHours.put(id, workHour);
		WorkHourDTOMapper mapper = new WorkHourDTOMapper(new ModelMapper());
		WorkHourDTO newWorkHour = mapper.fromWorkHourtoDTO(workHour);
		return newWorkHour;
	}

	public WorkHourDTO findWorkHour(Integer id) {
		WorkHour tmp = null;
		for(Map.Entry<Integer, WorkHour> entry : allWorkHours.entrySet()) {
			tmp = entry.getValue();
			if(tmp.getId() == id){
				WorkHourDTOMapper mapper = new WorkHourDTOMapper(new ModelMapper());
				WorkHourDTO newWorkHour = mapper.fromWorkHourtoDTO(tmp);
				return newWorkHour;
			}
		}
		return null;
	}

	public WorkHourDTO updateWorkHour(Integer workingHourId, Integer id, String start, String end) {
		WorkHour workHour = allWorkHours.get(id);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm:ssZ");
		LocalDateTime startTime = LocalDateTime.parse(start);
		LocalDateTime endTime = LocalDateTime.parse(end);
		if(workHour != null) {
			workHour.setId(workingHourId);
			workHour.setEnd(endTime);
			workHour.setStart(startTime);
			WorkHourDTOMapper mapper = new WorkHourDTOMapper(new ModelMapper());
			WorkHourDTO newWorkHour = mapper.fromWorkHourtoDTO(workHour);
			return newWorkHour;
		}
		throw new RuntimeException();
	}
}
