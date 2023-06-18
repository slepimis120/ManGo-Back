package com.mango.service;
import com.mango.dto.*;
import com.mango.model.*;
import com.mango.repository.DriverDocumentsRepository;
import com.mango.repository.DriverRepository;
import com.mango.repository.UserRepository;
import com.mango.repository.WorkHourRepository;
import com.mango.dto.*;
import com.mango.mapper.DriverDocumentDTOMapper;
import com.mango.mapper.WorkHourDTOMapper;
import com.mango.model.*;
import com.mango.repository.RideRepository;
import com.mango.service.interfaces.IUserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
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
		return mapper.fromDriverDocumenttoDTO(document);
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

	public Vehicle postVehicle(VehicleDTO vehicleDTO, Integer id){
		Vehicle vehicle = new Vehicle(vehicleDTO);
		vehicle.setDriver(driverRepository.findById(id).orElse(null));
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

	public StatisticsDTO getStatistics(Integer id, StatisticsDatesDTO statisticsDatesDTO) throws ParseException {
		List<Ride> rideList = rideRepository.findAll();
		Integer rejectCount = 0;
		Integer acceptCount = 0;
		Integer workHours = 0;
		Integer earnings = 0;
		for(Ride ride : rideList){
			Date date1=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(ride.getStartTime().toString());
			if(ride.getDriver() != null && Objects.equals(ride.getDriver().getId(), id)){
				if(statisticsDatesDTO.getStartDate().compareTo(date1) < 0 && statisticsDatesDTO.getEndDate().compareTo(date1) > 0){
					System.out.println(ride.getStatus().toString());
					if(Objects.equals(ride.getStatus().toString(), "rejected")){
						rejectCount++;
					}else if(Objects.equals(ride.getStatus().toString(), "finished")){
						acceptCount++;
						earnings = earnings + ride.getTotalCost();
					}
				}
			}
		}
		for(WorkHour workHour : workHourRepository.findAll()) {
			Date startDate = workHour.getStart();
			Date endDate = workHour.getEnd();
			if (startDate.compareTo(statisticsDatesDTO.getEndDate()) < 0 && startDate.compareTo(statisticsDatesDTO.getStartDate()) > 0) {
				if (endDate.compareTo(statisticsDatesDTO.getEndDate()) < 0) {
					workHours = workHours + (int) (startDate.getTime() - endDate.getTime() / 60000);
				} else {
					workHours = workHours + (int) (startDate.getTime() - statisticsDatesDTO.getEndDate().getTime() / 60000);
				}
			} else if (endDate.compareTo(statisticsDatesDTO.getEndDate()) < 0 && endDate.compareTo(statisticsDatesDTO.getStartDate()) > 0) {
				if (startDate.compareTo(statisticsDatesDTO.getStartDate()) < 0) {
					workHours = workHours + (int) (statisticsDatesDTO.getStartDate().getTime() - endDate.getTime() / 60000);
				} else {
					workHours = workHours + (int) (startDate.getTime() - endDate.getTime() / 60000);
				}
			}
		}
		workHours = workHours / 36000000;
		return new StatisticsDTO(rejectCount, acceptCount, workHours, earnings);
	}

	public ReportDTO getReport(Integer id, StatisticsDatesDTO statisticsDatesDTO) throws ParseException, IOException {
		List<Ride> rideList = rideRepository.findAll();
		ReportDTO reportDTO = new ReportDTO();
		List<ReportCounterDTO> kilometresCount = new ArrayList<ReportCounterDTO>();
		List<ReportCounterDTO> ridesCount = new ArrayList<ReportCounterDTO>();
		for(Ride ride : rideList){
			Date date1=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(ride.getStartTime().toString());
			if(date1.before(statisticsDatesDTO.getEndDate()) && date1.after(statisticsDatesDTO.getStartDate()) && ride.getDriver() != null && Objects.equals(ride.getDriver().getId(), id)){
				if(ridesCount.size() == 0){
					ReportCounterDTO newReport = new ReportCounterDTO(date1, 1f);
					ridesCount.add(newReport);
				}else{
					for(ReportCounterDTO reportCounterDTO : ridesCount){
						System.out.println(reportCounterDTO.getDate());
						SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
						if (fmt.format(date1).equals(fmt.format(reportCounterDTO.getDate()))){
							System.out.println("A");
							reportCounterDTO.setCount(reportCounterDTO.getCount() + 1);
						}else{
							System.out.println("b");
							ReportCounterDTO newReport = new ReportCounterDTO(date1, 1f);
							ridesCount.add(newReport);
						}
					}
				}
				if(kilometresCount.size() == 0){
					Float totalKmold = 0f;
					String url = "http://router.project-osrm.org/route/v1/driving/" + ride.getLocations().get(0).getDeparture().getLongitude() + "," + ride.getLocations().get(0).getDeparture().getLatitude()  + ";" + ride.getLocations().get(0).getDestination().getLongitude() + "," + ride.getLocations().get(0).getDestination().getLatitude() + "?overview=false";
					URL GETDISTANCEURL = new URL(url);
					URLConnection website = GETDISTANCEURL.openConnection();
					BufferedReader in = new BufferedReader(
							new InputStreamReader(
									website.getInputStream()));
					String inputLine;

					while ((inputLine = in.readLine()) != null)
						totalKmold = Float.parseFloat(inputLine.split("distance\":")[1].split("}")[0]) / 100;
					in.close();

					ReportCounterDTO newReport = new ReportCounterDTO(date1, totalKmold);
					kilometresCount.add(newReport);
				}else{
					for(ReportCounterDTO reportCounterDTO : kilometresCount){
						SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
						Float totalKm = 0f;

						String url = "http://router.project-osrm.org/route/v1/driving/" + ride.getLocations().get(0).getDeparture().getLongitude() + "," + ride.getLocations().get(0).getDeparture().getLatitude()  + ";" + ride.getLocations().get(0).getDestination().getLongitude() + "," + ride.getLocations().get(0).getDestination().getLatitude() + "?overview=false";
						URL GETDISTANCEURL = new URL(url);
						URLConnection website = GETDISTANCEURL.openConnection();
						BufferedReader in = new BufferedReader(
								new InputStreamReader(
										website.getInputStream()));
						String inputLine;

						while ((inputLine = in.readLine()) != null)
							totalKm = Float.parseFloat(inputLine.split("distance\":")[1].split("}")[0]);
						in.close();


						if (fmt.format(date1).equals(fmt.format(reportCounterDTO.getDate()))){
							reportCounterDTO.setCount(reportCounterDTO.getCount() + totalKm);
						}else{
							ReportCounterDTO newReport = new ReportCounterDTO(date1, totalKm);
							kilometresCount.add(newReport);
						}
					}
				}
			}
		}
		reportDTO.setTotalKilometres(kilometresCount);
		reportDTO.setTotalRides(ridesCount);
		return reportDTO;
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
