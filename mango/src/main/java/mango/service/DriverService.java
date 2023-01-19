package mango.service;
import mango.dto.*;
import mango.mapper.DriverDocumentDTOMapper;
import mango.mapper.WorkHourDTOMapper;
import mango.model.*;
import mango.repository.DriverDocumentsRepository;
import mango.repository.DriverRepository;
import mango.repository.RideRepository;
import mango.repository.WorkHourRepository;
import mango.repository.UserRepository;
import mango.service.interfaces.IUserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	private UserRepository userRepository;

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
		if(emailExists(data.getEmail())) return null;
		Driver driver = new Driver(data);
		driver = driverRepository.save(driver);
		//userRepository.save(driver);
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
			if(update.getProfilePicture() != null) 
				driver.setProfilePicture(update.getProfilePicture());
			if(update.getTelephoneNumber() != null)
				driver.setTelephoneNumber(update.getTelephoneNumber());
			driver = driverRepository.save(driver);
			return new UserDTO(driver);
			
		}
		return null;
	}

	public ArrayList<DriverDocumentDTO> findDocuments(Integer id) {
		if(driverRepository.findById(id).orElse(null) == null){
			return null;
		}
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
		if(driver == null) return null;
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
			return null;
		}
	}
	
	public WorkHourResponseDTO findWorkHours(Integer id, Integer page, Integer size, String from, String to) {
		if(driverRepository.findById(id).orElse(null) == null) return null;
		int offset = (page - 1) * size;
		ArrayList<WorkHourDTO> returnList = new ArrayList<WorkHourDTO>();
		WorkHourDTOMapper mapper = new WorkHourDTOMapper(new ModelMapper());
		List<WorkHour> workHours = workHourRepository.findWorkHours(id, to, from, offset, size);

		for(int i = 0; i < workHours.size(); i++) {
			WorkHourDTO newWorkHour = mapper.fromWorkHourtoDTO(workHours.get(i));
			returnList.add(newWorkHour);
		}
		WorkHourResponseDTO response = new WorkHourResponseDTO(returnList, returnList.size());
		return response;
	}
	
	public WorkHourDTO insertWorkHour(Integer idDriver, String  start) {
		Driver driver = driverRepository.findById(idDriver).orElse(null);
		if (driver == null)return  null;
		Date parsedDate = null;
		try {
			parsedDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(start);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		WorkHour workHour = new WorkHour(parsedDate, driver);
		workHour = workHourRepository.save(workHour);
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
			workHour = workHourRepository.save(workHour);

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

	public ResponseEntity getVehicle(Integer id){
		if(driverRepository.findById(id).orElse(null) == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Driver does not exist");
		Vehicle vehicle = findVehicle(id);
		if(vehicle == null) return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vehicle is not assigned!");
		return ResponseEntity.status(HttpStatus.OK).body(vehicle);
	}

	public Vehicle findVehicle(int id){
		Vehicle vehicle = null;
		for (Vehicle entry : vehicleService.findAll()) {
			if(entry.getDriver().getId().equals(id)){
				vehicle = entry;
			}
		}
		return vehicle;
	}

	public VehicleDTO changeVehicle(Integer id,VehicleDTO update){
		if(driverRepository.findById(id).orElse(null) == null) return null;
		Vehicle vehicle = findVehicle(id);
		vehicle.setVehicleType(update.getVehicleType());
		vehicle.setModel(update.getModel());
		vehicle.setLicenseNumber(update.getLicenseNumber());
		vehicle.setPassengerSeats(update.getPassengerSeats());
		vehicle.setBabyTransport(update.isBabyTransport());
		vehicle.setPetTransport(update.isPetTransport());
		vehicleService.save(vehicle);
		VehicleDTO returnVal = new VehicleDTO(vehicle);
		return returnVal;
	}

	public RideCountDTO getRides(Integer id, Integer page, Integer size, String sort, String from, String to){
		if(driverRepository.findById(id).orElse(null) == null) return null;
		RideCountDTO count = new RideCountDTO();
		ArrayList<Ride> rideList = new ArrayList<Ride>();
		List<Ride> rides = rideRepository.findRidesByDriver(id);
		count.setResults(rides);
		count.setTotalCount(rides.size());
		return count;
	}


	public boolean ifDriverExists(Integer id){
		return driverRepository.findById(id).orElse(null) != null;
	}


	public boolean emailExists(String email){
		for(Driver driver : driverRepository.findAll()){
			if(driver.getEmail().equals(email)) return true;
		}
		return false;
	}
}
