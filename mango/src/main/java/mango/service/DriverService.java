package mango.service;
import mango.dto.*;
import mango.mapper.DriverDocumentDTOMapper;
import mango.mapper.WorkHourDTOMapper;
import mango.model.*;
import mango.repository.DriverDocumentsRepository;
import mango.repository.DriverRepository;
import mango.repository.RideRepository;
import mango.repository.WorkHourRepository;
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
	VehicleService vehicleService;
	@Autowired
	private DriverRepository driverRepository;
	@Autowired
	private RideRepository rideRepository;
	@Autowired
	private DriverDocumentsRepository documentsRepository;
	@Autowired
	private WorkHourRepository workHourRepository;
	@Autowired
	public DriverService(@Lazy VehicleService vehicleService){
		this.vehicleService = vehicleService;
	}
	
	@Override
	public UserResponseDTO getArray(Integer page, Integer size) {
		int offset = (page - 1) * size;
		ArrayList<UserDTO> returnList = new ArrayList<UserDTO>();
		List<Driver> drivers = driverRepository.fetchDriversOffset(offset, size);
		for(int i = 0; i < drivers.size(); i++){
			UserDTO currentUser = new UserDTO(drivers.get(i));
			returnList.add(currentUser);
		}
		return new UserResponseDTO(drivers.size(), returnList);
	}

	@Override
	public UserDTO find(Integer id) {
		Driver driver = driverRepository.findById(id).orElse(null);
		if (driver != null) {
			UserDTO userDTO = new UserDTO(driver);
			return userDTO;
		}
		return null;
	}

	@Override
	public UserDTO insert(ExpandedUserDTO data) {
		Driver driver = new Driver(data);
		driverRepository.save(driver);
		UserService.allUsers.put(driver.getId(), driver);
		return new UserDTO(driver);
	}

	@Override
	public UserDTO update(Integer id, ExpandedUserDTO update) {
		Driver driver = driverRepository.findById(id).orElse(null);
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
		ArrayList<DriverDocumentDTO> returnList = new ArrayList<DriverDocumentDTO>();
		List<DriverDocument> documents = documentsRepository.findDriverDocuments(id);

		DriverDocumentDTOMapper mapper = new DriverDocumentDTOMapper(new ModelMapper());

		for(int i = 0; i < documents.size(); i++){
			DriverDocumentDTO newDriverDocument = mapper.fromDriverDocumenttoDTO(documents.get(i));
			returnList.add(newDriverDocument);
		}
		return returnList;
	}
	
	public DriverDocumentDTO insertDocument(Integer driverId, String name, String documentImage) {
		Driver driver = driverRepository.findById(driverId).orElse(null);
		DriverDocument document = new DriverDocument(name, documentImage, driver);
		documentsRepository.save(document);
		DriverDocumentDTOMapper mapper = new DriverDocumentDTOMapper(new ModelMapper());
		DriverDocumentDTO newDriverDocument = mapper.fromDriverDocumenttoDTO(document);
		return newDriverDocument;
	}

	public String deleteDocument(Integer id) {
		if(documentsRepository.findById(id).orElse(null) != null){
			documentsRepository.deleteById(id);
			return "Driver document deleted successfully";
		}else {
			return "Document does not exist!";
		}
	}
	
	public WorkHourResponseDTO findWorkHours(Integer id, Integer page, Integer size, String from, String to) {
		int offset = (page - 1) * size;
		ArrayList<WorkHourDTO> returnList = new ArrayList<WorkHourDTO>();
		WorkHourDTOMapper mapper = new WorkHourDTOMapper(new ModelMapper());
		Date startTime= null;
		Date endTime = null;
		try {
			 startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(from);
			 endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(to);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		List<WorkHour> workHours = workHourRepository.findWorkHours(endTime.toString(), startTime.toString(), offset, size);

		for(int i = 0; i < workHours.size(); i++) {
			WorkHourDTO newWorkHour = mapper.fromWorkHourtoDTO(workHours.get(i));
			returnList.add(newWorkHour);
		}
		WorkHourResponseDTO response = new WorkHourResponseDTO(returnList, returnList.size());
		return response;
	}
	
	public WorkHourDTO insertWorkHour(Integer idDriver, String start) {
		Date startTime = null;
		try {
			startTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(start);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		Driver driver = driverRepository.findById(idDriver).orElse(null);
		WorkHour workHour = new WorkHour(startTime, driver);
		workHourRepository.save(workHour);
		WorkHourDTOMapper mapper = new WorkHourDTOMapper(new ModelMapper());
		WorkHourDTO newWorkHour = mapper.fromWorkHourtoDTO(workHour);
		return newWorkHour;
	}

	public WorkHourDTO findWorkHour(Integer id) {
		WorkHour workHour = workHourRepository.findById(id).orElse(null);
		WorkHourDTOMapper mapper = new WorkHourDTOMapper(new ModelMapper());
		WorkHourDTO newWorkHour = mapper.fromWorkHourtoDTO(workHour);
		return newWorkHour;
	}

	public WorkHourDTO updateWorkHour(Integer workingHourId, String end) {
		WorkHour workHour = workHourRepository.findById(workingHourId).orElse(null);
		Date endTime = null;
		try {
			endTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(end);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		if(workHour != null) {
			workHour.setEnd(endTime);
			workHourRepository.save(workHour);

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
