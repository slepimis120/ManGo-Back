package mango.service;
import mango.dto.*;
import mango.mapper.DriverDocumentDTOMapper;
import mango.mapper.LocationDTOMapper;
import mango.mapper.WorkHourDTOMapper;
import mango.model.*;
import mango.service.interfaces.IUserService;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class DriverService implements IUserService {

	public static Map<Integer, Driver> allDrivers = new HashMap<Integer, Driver>();
	public static Map<Integer, DriverDocument> allDocuments = new HashMap<Integer, DriverDocument>();
	public static Map<Integer, WorkHour> allWorkHours = new HashMap<Integer, WorkHour>();

	VehicleService vehicleService;
	RideService rideService;


	@Autowired
	public DriverService(@Lazy VehicleService vehicleService, @Lazy RideService rideService){
		this.vehicleService = vehicleService;
		this.rideService = rideService;
	}
	
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
			if(entry.getValue().getDriverId().getId() == id){
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
		Driver driver = new Driver();
		driver.setId(id);
		DriverDocument document = new DriverDocument(size, name, documentImage, driver);
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
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
		LocalDateTime fromTime = LocalDateTime.parse(from, format);
		LocalDateTime toTime = LocalDateTime.parse(to, format);
		
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
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
		LocalDateTime startTime = LocalDateTime.parse(start, format);
		LocalDateTime endTime = LocalDateTime.parse(end, format);
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
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
		LocalDateTime startTime = LocalDateTime.parse(start, format);
		LocalDateTime endTime = LocalDateTime.parse(end, format);
		if(workHour != null) {
			workHour.setId(workingHourId);
			workHour.setEnd(endTime);
			workHour.setStart(startTime);
			WorkHourDTOMapper mapper = new WorkHourDTOMapper(new ModelMapper());
			WorkHourDTO newWorkHour = mapper.fromWorkHourtoDTO(workHour);
			return newWorkHour;
		}
		return null;
	}

	public Vehicle postVehicle(Vehicle vehicle, Integer id){
		vehicle.getDriverId().setId(id);
		vehicle.setId(vehicleService.allVehicles().size() + 1);
		vehicleService.allVehicles().put(vehicle.getId(), vehicle);
		return vehicle;
	}

	public Vehicle getVehicle(Integer id){
		Vehicle vehicle = null;
		for (Map.Entry<Integer, Vehicle> entry : vehicleService.allVehicles().entrySet()) {
			if(entry.getValue().getDriverId().equals(id)){
				vehicle = entry.getValue();
			}
		}
		return vehicle;
	}

	public Vehicle changeVehicle(Integer id,Vehicle vehicle){
		for (Map.Entry<Integer, Vehicle> entry : vehicleService.allVehicles().entrySet()) {
			if(entry.getValue().getDriverId().equals(id)){
				vehicle.setId(entry.getValue().getId());
				vehicle.setDriverId(entry.getValue().getDriverId());
				vehicleService.allVehicles().put(entry.getValue().getId(), vehicle);
			}
		}
		return vehicle;
	}

	public RideCountDTO getRides(Integer id, Integer page, Integer size, String sort, String from, String to){
		RideCountDTO count = new RideCountDTO();
		ArrayList<Ride> rideList = new ArrayList<Ride>();
		Integer rideCount = 0;
		for (Map.Entry<Integer, Ride> entry : rideService.getAllRides().entrySet()) {
			if(entry.getValue().getDriver().getId().equals(id)){
				rideList.add(entry.getValue());
				rideCount = rideCount + 1;
			}
		}
		count.setResults(rideList);
		count.setTotalCount(rideCount);
		return count;
	}
}
