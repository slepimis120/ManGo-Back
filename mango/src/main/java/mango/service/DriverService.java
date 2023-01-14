package mango.service;
import mango.dto.*;
import mango.mapper.DriverDocumentDTOMapper;
import mango.mapper.WorkHourDTOMapper;
import mango.model.*;
import mango.repository.DriverRepository;
import mango.repository.RideRepository;
import mango.service.interfaces.IUserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class DriverService implements IUserService {

	@Autowired
	private DriverRepository driverRepository;

	@Autowired
	private RideRepository rideRepository;

	public static Map<Integer, Driver> allDrivers = new HashMap<Integer, Driver>();
	public static Map<Integer, DriverDocument> allDocuments = new HashMap<Integer, DriverDocument>();
	public static Map<Integer, WorkHour> allWorkHours = new HashMap<Integer, WorkHour>();

	VehicleService vehicleService;

	@Autowired
	public DriverService(@Lazy VehicleService vehicleService){
		this.vehicleService = vehicleService;
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
		Date fromTime= null;
		Date toTime = null;
		try {
			fromTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(from);
			toTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(to);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

		
		for(Map.Entry<Integer, WorkHour> entry : allWorkHours.entrySet()) {
			tmp = entry.getValue();
			if(Objects.equals(tmp.getDriver().getId(), id)){
				if((tmp.getStart().after(fromTime) || tmp.getStart().equals(fromTime)) &&
						(tmp.getEnd().before(toTime) || tmp.getEnd().equals(toTime))) {
			        WorkHourDTO newWorkHour = mapper.fromWorkHourtoDTO(tmp);
					workHours.add(newWorkHour);
				}
			}
		}
		WorkHourResponseDTO response = new WorkHourResponseDTO(workHours, workHours.size());
		return response;
	}
	
	public WorkHourDTO insertWorkHour(Integer idDriver, Integer id, String start, String end) {
		Date startTime = null;
		Date endTime = null;
		try {
			startTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(start);
			endTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(end);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

		Driver driver = new Driver();
		driver.setId(idDriver);
		WorkHour workHour = new WorkHour(id, startTime, endTime, driver);
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
		Date startTime = null;
		Date endTime = null;
		try {
			startTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(start);
			endTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(end);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
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
		vehicle.getDriver().setId(id);
		vehicle.setId(vehicleService.findAll().size() + 1);
		vehicleService.save(vehicle);
		return vehicle;
	}

	public Vehicle getVehicle(Integer id){
		Vehicle vehicle = null;
		for (Vehicle entry : vehicleService.findAll()) {
			if(entry.getDriver().equals(id)){
				vehicle = entry;
			}
		}
		return vehicle;
	}

	public Vehicle changeVehicle(Integer id,Vehicle vehicle){
		for (Vehicle entry : vehicleService.findAll()) {
			if(entry.getDriver().equals(id)){
				vehicle.setId(entry.getId());
				vehicle.setDriver(entry.getDriver());
				vehicleService.save(vehicle);
			}
		}
		return vehicle;
	}

	public RideCountDTO getRides(Integer id, Integer page, Integer size, String sort, String from, String to){
		RideCountDTO count = new RideCountDTO();
		ArrayList<Ride> rideList = new ArrayList<Ride>();
		Integer rideCount = 0;
		for (Ride ride : findRidesByDriver(id)) {
			rideList.add(ride);
			rideCount = rideCount + 1;
		}
		count.setResults(rideList);
		count.setTotalCount(rideCount);
		return count;
	}


	public boolean ifDriverExists(Integer id){
		return driverRepository.findById(id).orElse(null) != null;
	}

	public List<Ride> findRidesByDriver(Integer driverId){
		return rideRepository.findRidesByDriver(driverId);
	}
}
