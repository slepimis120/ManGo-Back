package com.mango.controller;

import com.mango.dto.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RideControllerTest {

    private String RIDE_URL = "/ride";

    @Autowired
    private TestRestTemplate restTemplate;

    private String JWT_DRIVER_01;
    private String JWT_DRIVER_02;
    private String JWT_DRIVER_03;
    private String JWT_DRIVER_04;
    private String JWT_DRIVER_05;
    private String JWT_PASSENGER_01;
    private String JWT_PASSENGER_02;
    private String JWT_PASSENGER_03;
    private String JWT_PASSENGER_04;

    private String JWT_ADMIN;


    private HttpHeaders getHeader(String jwt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (jwt != null) {
            headers.setBearerAuth(jwt);
        }

        return headers;
    }

    private String login(String email, String password) throws JSONException {

        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity("/user/login",
                        new UserLoginDTO(email, password),
                        String.class);
        JSONObject json4 = new JSONObject(responseEntity.getBody());
        System.out.println(json4.getString("accessToken"));
        return json4.getString("accessToken");

    }

    @Before
    public void login() throws JSONException {
        JWT_DRIVER_01 = login("momir@gmail.com", "momir@gmail.com");
        JWT_DRIVER_02 = login("marinara@gmail.com", "marinara@gmail.com");
        JWT_DRIVER_03 = login("adamdriver@gmail.com", "adamdriver@gmail.com");
        JWT_DRIVER_04 = login("jasambabydriver@gmail.com", "jasambabydriver@gmail.com");
        JWT_DRIVER_05 = login("moremornar@gmail.com", "moremornar@gmail.com");
        JWT_PASSENGER_01 = login("marko@gmail.com", "marko@gmail.com");
        JWT_PASSENGER_02 = login("milica@gmail.com", "milica@gmail.com");
        JWT_PASSENGER_03 = login("meraklovski@gmail.com", "meraklovski@gmail.com");
        JWT_PASSENGER_04 = login("milamilosevski@gmail.com", "milamilosevski@gmail.com");
        JWT_ADMIN = login("milosobrenovic@hotmail.com", "milosobrenovic@hotmail.com");
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //Creating a ride

    @Test
    public void testCreateRideUnauthenticatedAccess(){
        RideLocationDTO route = new RideLocationDTO(new LocationDTO("Ledinacka 2", 45.248667F, 19.816370F), new LocationDTO("Kulturni Centar LAB", 45.24951752043822F, 19.825208148679025F));
        CreateRideDTO dto = new CreateRideDTO(
                Arrays.asList(route),
                Arrays.asList(new RidePassengerDTO(8, "marko@gmail.com")),
                "LUXURY", true, false, null);

        HttpEntity<CreateRideDTO> requestBody = new HttpEntity<>(dto, null);
        ResponseEntity<String> response = restTemplate.exchange(
                RIDE_URL, HttpMethod.POST, requestBody, new ParameterizedTypeReference<>() {
                }
        );
        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void testCreateRideUnauthorizedAccess(){
        RideLocationDTO route = new RideLocationDTO(new LocationDTO("Ledinacka 2", 45.248667F, 19.816370F), new LocationDTO("Kulturni Centar LAB", 45.24951752043822F, 19.825208148679025F));
        CreateRideDTO dto = new CreateRideDTO(
                Arrays.asList(route),
                Arrays.asList(new RidePassengerDTO(8, "marko@gmail.com")),
                "LUXURY", true, false, null);

        HttpEntity<CreateRideDTO> requestBody = new HttpEntity<>(dto, getHeader(JWT_DRIVER_01));
        ResponseEntity<String> response = restTemplate.exchange(
                RIDE_URL, HttpMethod.POST, requestBody, new ParameterizedTypeReference<>() {
                }
        );
        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void testCreateRideNoLocation(){
        RideLocationDTO route = new RideLocationDTO(new LocationDTO("Ledinacka 2", 45.248667F, 19.816370F), new LocationDTO("Kulturni Centar LAB", 45.24951752043822F, 19.825208148679025F));
        CreateRideDTO dto = new CreateRideDTO(
                null,
                Arrays.asList(new RidePassengerDTO(8, "marko@gmail.com")),
                "LUXURY", true, false, null);

        HttpEntity<CreateRideDTO> requestBody = new HttpEntity<>(dto, getHeader(JWT_PASSENGER_01));
        ResponseEntity<String> response = restTemplate.exchange(
                RIDE_URL, HttpMethod.POST, requestBody, new ParameterizedTypeReference<>() {
                }
        );
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testCreateRideNoPassengers(){
        RideLocationDTO route = new RideLocationDTO(new LocationDTO("Ledinacka 2", 45.248667F, 19.816370F), new LocationDTO("Kulturni Centar LAB", 45.24951752043822F, 19.825208148679025F));
        CreateRideDTO dto = new CreateRideDTO(
                Arrays.asList(route),
                null,
                "LUXURY", true, false, null);

        HttpEntity<CreateRideDTO> requestBody = new HttpEntity<>(dto, getHeader(JWT_PASSENGER_01));
        ResponseEntity<String> response = restTemplate.exchange(
                RIDE_URL, HttpMethod.POST, requestBody, new ParameterizedTypeReference<>() {
                }
        );
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testCreateRideNoVehicleType(){
        RideLocationDTO route = new RideLocationDTO(new LocationDTO("Ledinacka 2", 45.248667F, 19.816370F), new LocationDTO("Kulturni Centar LAB", 45.24951752043822F, 19.825208148679025F));
        CreateRideDTO dto = new CreateRideDTO(
                Arrays.asList(route),
                Arrays.asList(new RidePassengerDTO(8, "marko@gmail.com")),
                null, true, false, null);

        HttpEntity<CreateRideDTO> requestBody = new HttpEntity<>(dto, getHeader(JWT_PASSENGER_01));
        ResponseEntity<String> response = restTemplate.exchange(
                RIDE_URL, HttpMethod.POST, requestBody, new ParameterizedTypeReference<>() {
                }
        );
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testCreateRideVehicleTypeOver50CharactersLong(){
        RideLocationDTO route = new RideLocationDTO(new LocationDTO("Ledinacka 2", 45.248667F, 19.816370F), new LocationDTO("Kulturni Centar LAB", 45.24951752043822F, 19.825208148679025F));
        CreateRideDTO dto = new CreateRideDTO(
                Arrays.asList(route),
                Arrays.asList(new RidePassengerDTO(8, "marko@gmail.com")),
                "THISVEHICLEISALUXURYVEHICLEANDITHASOVER50CHARACTERSNOW", true, false, null);

        HttpEntity<CreateRideDTO> requestBody = new HttpEntity<>(dto, getHeader(JWT_PASSENGER_01));
        ResponseEntity<String> response = restTemplate.exchange(
                RIDE_URL, HttpMethod.POST, requestBody, new ParameterizedTypeReference<>() {
                }
        );
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testCreateRideNoBaby(){
        RideLocationDTO route = new RideLocationDTO(new LocationDTO("Ledinacka 2", 45.248667F, 19.816370F), new LocationDTO("Kulturni Centar LAB", 45.24951752043822F, 19.825208148679025F));
        CreateRideDTO dto = new CreateRideDTO(
                Arrays.asList(route),
                Arrays.asList(new RidePassengerDTO(8, "marko@gmail.com")),
                "LUXURY", null, false, null);

        HttpEntity<CreateRideDTO> requestBody = new HttpEntity<>(dto, getHeader(JWT_PASSENGER_01));
        ResponseEntity<String> response = restTemplate.exchange(
                RIDE_URL, HttpMethod.POST, requestBody, new ParameterizedTypeReference<>() {
                }
        );
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testCreateRideNoPet(){
        RideLocationDTO route = new RideLocationDTO(new LocationDTO("Ledinacka 2", 45.248667F, 19.816370F), new LocationDTO("Kulturni Centar LAB", 45.24951752043822F, 19.825208148679025F));
        CreateRideDTO dto = new CreateRideDTO(
                Arrays.asList(route),
                Arrays.asList(new RidePassengerDTO(8, "marko@gmail.com")),
                "LUXURY", true, null, null);

        HttpEntity<CreateRideDTO> requestBody = new HttpEntity<>(dto, getHeader(JWT_PASSENGER_01));
        ResponseEntity<String> response = restTemplate.exchange(
                RIDE_URL, HttpMethod.POST, requestBody, new ParameterizedTypeReference<>() {
                }
        );
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testCreateRide() throws JSONException {
        RideLocationDTO route = new RideLocationDTO(new LocationDTO("Ledinacka 2", 45.248667F, 19.816370F), new LocationDTO("Kulturni Centar LAB", 45.24951752043822F, 19.825208148679025F));
        CreateRideDTO dto = new CreateRideDTO(
                Arrays.asList(route),
                Arrays.asList(new RidePassengerDTO(12, "aleksandrab@gmail.com")),
                "LUXURY", true, false, null);

        HttpEntity<CreateRideDTO> requestBody = new HttpEntity<>(dto, getHeader(JWT_PASSENGER_01));
        ResponseEntity<String> response = restTemplate.exchange(
                RIDE_URL, HttpMethod.POST, requestBody, new ParameterizedTypeReference<>() {
                }
        );
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONObject expectedJson = new JSONObject("{\"id\":10,\"startTime\":\"2023-06-20T00:10:25.410+00:00\",\"endTime\":null,\"totalCost\":0,\"driver\":null,\"passengers\":[{\"id\":12,\"email\":\"aleksandrab@gmail.com\"}],\"estimatedTimeInMinutes\":0,\"vehicleType\":\"LUXURY\",\"babyTransport\":true,\"petTransport\":false,\"locations\":[{\"departure\":{\"address\":\"Ledinacka 2\",\"latitude\":45.24867,\"longitude\":19.81637},\"destination\":{\"address\":\"Kulturni Centar LAB\",\"latitude\":45.24952,\"longitude\":19.825209}}],\"status\":\"pending\",\"rejection\":{\"reason\":null,\"timeOfRejection\":null}}");
        expectedJson.remove("startTime");
        JSONObject actualJson = new JSONObject(response.getBody());
        expectedJson.remove("startTime");
        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //Active Ride for Driver

    @Test
    public void testGetDriverActiveRideNotFound() {
        HttpEntity<Object> httpEntity = new HttpEntity<>(null, getHeader(JWT_ADMIN));

        ResponseEntity<String> responseEntity =
                restTemplate.exchange(RIDE_URL + "/driver/7/active", HttpMethod.GET,
                        httpEntity, String.class);

        //assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testGetDriverActiveRideFound() {
        HttpEntity<Object> httpEntity = new HttpEntity<>(null, getHeader(JWT_ADMIN));

        ResponseEntity<String> responseEntity =
                restTemplate.exchange(RIDE_URL + "/driver/4/active", HttpMethod.GET,
                        httpEntity, String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //Active ride for passenger

    @Test
    public void testGetPassengerActiveRideUnauthenticated() {
        HttpEntity<Object> httpEntity = new HttpEntity<>(null, null);

        ResponseEntity<String> responseEntity =
                restTemplate.exchange(RIDE_URL + "/passenger/12/active", HttpMethod.GET,
                        httpEntity, String.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void testGetPassengerActiveRideUnauthorized() {
        HttpEntity<Object> httpEntity = new HttpEntity<>(null, getHeader(JWT_PASSENGER_01));

        ResponseEntity<String> responseEntity =
                restTemplate.exchange(RIDE_URL + "/passenger/12/active", HttpMethod.GET,
                        httpEntity, String.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }
    @Test
    public void testGetPassengerActiveRideNotFound() {
        HttpEntity<Object> httpEntity = new HttpEntity<>(null, getHeader(JWT_ADMIN));

        ResponseEntity<String> responseEntity =
                restTemplate.exchange(RIDE_URL + "/passenger/12/active", HttpMethod.GET,
                        httpEntity, String.class);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testGetPassengerActiveRideFound() {
        HttpEntity<Object> httpEntity = new HttpEntity<>(null, getHeader(JWT_ADMIN));

        ResponseEntity<String> responseEntity =
                restTemplate.exchange(RIDE_URL + "/passenger/11/active", HttpMethod.GET,
                        httpEntity, String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //Ride Details

    @Test
    public void testFindRideUnauthenticated() {
        HttpEntity<Object> httpEntity = new HttpEntity<>(null, null);

        ResponseEntity<String> responseEntity =
                restTemplate.exchange(RIDE_URL + "/1", HttpMethod.GET,
                        httpEntity, String.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void testFindRideUnauthorized() {
        HttpEntity<Object> httpEntity = new HttpEntity<>(null, getHeader(JWT_PASSENGER_01));

        ResponseEntity<String> responseEntity =
                restTemplate.exchange(RIDE_URL + "/1", HttpMethod.GET,
                        httpEntity, String.class);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void testFindRideBadIDFormat() {
        HttpEntity<Object> httpEntity = new HttpEntity<>(null, getHeader(JWT_ADMIN));

        ResponseEntity<String> responseEntity =
                restTemplate.exchange(RIDE_URL + "/id", HttpMethod.GET,
                        httpEntity, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testFindRideNotFound() {
        HttpEntity<Object> httpEntity = new HttpEntity<>(null, getHeader(JWT_ADMIN));

        ResponseEntity<String> responseEntity =
                restTemplate.exchange(RIDE_URL + "/30", HttpMethod.GET,
                        httpEntity, String.class);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testFindRide() {
        HttpEntity<Object> httpEntity = new HttpEntity<>(null, getHeader(JWT_ADMIN));

        ResponseEntity<String> responseEntity =
                restTemplate.exchange(RIDE_URL + "/1", HttpMethod.GET,
                        httpEntity, String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //Cancel Existing Ride

    @Test
    public void testCancelRideNotAccepted() {
        HttpEntity<Object> httpEntity = new HttpEntity<>(null, getHeader(JWT_PASSENGER_02));

        ResponseEntity<String> responseEntity =
                restTemplate.exchange(RIDE_URL + "/2/withdraw", HttpMethod.PUT,
                        httpEntity, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testCancelRideNotExisting() {
        HttpEntity<Object> httpEntity = new HttpEntity<>(null, getHeader(JWT_PASSENGER_01));

        ResponseEntity<String> responseEntity =
                restTemplate.exchange(RIDE_URL + "/20/withdraw", HttpMethod.PUT,
                        httpEntity, String.class);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testCancelRide() {
        HttpEntity<Object> httpEntity = new HttpEntity<>(null, getHeader(JWT_PASSENGER_03));

        ResponseEntity<String> responseEntity =
                restTemplate.exchange(RIDE_URL + "/6/withdraw", HttpMethod.PUT,
                        httpEntity, String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //Panic Procedure For the Ride

    @Test
    public void testPanicRideNotExist(){
        PanicDTO panic = new PanicDTO("Kola smrde");

        HttpEntity<PanicDTO> requestBody = new HttpEntity<>(panic, getHeader(JWT_PASSENGER_03));
        ResponseEntity<String> response = restTemplate.exchange(
                RIDE_URL + "/30/panic", HttpMethod.PUT, requestBody, new ParameterizedTypeReference<>() {
                }
        );
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testPanicRideReasonOver500CharactersLong(){
        PanicDTO panic = new PanicDTO("l0yRkXVLGPO4eBENa89eyB1WWmg0Yg6k48p4Ce6gWwLimP485aB4QjLvKxZSenlkVcEEBtbaUcQbWNysYBXDi5sar1UpUyvwdC7Z9qEBOHwwIFpzqg6v3sI3ZrwJWZ1UpSttRKbyqLryTNkaTdMppVYmatCIdibJjQdQA7PKGnfvHubuRtFkhxYy1f2GRl73LXtwjEueBhA8MjjW3KqziEy2QJ6YFPMSVKLDDUTyTp3PJxvTufyJelBWULBlGXl1njP3g0XT8JflU9KyyoWNJRjWisibhUybevByw9er9Gcb6KTZi1SIWH23xfcQB2K1R54oR3K4KtHsUUdPtGWv4Pt0gl8D5ZDy8joHKmO1UbKsm3mJ1gCFRm20Ah1jeAKw09hxwNLu3xSSk9t4yoG1cmoF5VBvLbPdpbvcb9k7Wy0HdqSK4RO8auMhUVdanIft1u4ara7dJBQyDKM9ZcYUWJsyHbXQfwwhRBR21baiRgehp1OcBKMXsEeS4xfFYz3llEFvPdJ0UbtTOcWBbiS6CvZ94FwLQZ0K79MSer0QZYxXnSMn4btsx3JM484cCZ7ITik3SZ6es7423MV2OUGA8cv0\n");

        HttpEntity<PanicDTO> requestBody = new HttpEntity<>(panic, getHeader(JWT_PASSENGER_03));
        ResponseEntity<String> response = restTemplate.exchange(
                RIDE_URL + "/6/panic", HttpMethod.PUT, requestBody, new ParameterizedTypeReference<>() {
                }
        );
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testPanicRideNoReason(){
        PanicDTO panic = new PanicDTO(null);

        HttpEntity<PanicDTO> requestBody = new HttpEntity<>(panic, getHeader(JWT_PASSENGER_03));
        ResponseEntity<String> response = restTemplate.exchange(
                RIDE_URL + "/6/panic", HttpMethod.PUT, requestBody, new ParameterizedTypeReference<>() {
                }
        );
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testPanic(){
        PanicDTO panic = new PanicDTO("Kola smrde");

        HttpEntity<PanicDTO> requestBody = new HttpEntity<>(panic, getHeader(JWT_PASSENGER_03));
        ResponseEntity<String> response = restTemplate.exchange(
                RIDE_URL + "/6/panic", HttpMethod.PUT, requestBody, new ParameterizedTypeReference<>() {
                }
        );
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //Start Ride

    @Test
    public void startRideNotAccepted(){
        HttpEntity<Object> requestBody = new HttpEntity<>(null, getHeader(JWT_DRIVER_01));
        ResponseEntity<String> response = restTemplate.exchange(
                RIDE_URL + "/5/start", HttpMethod.PUT, requestBody, new ParameterizedTypeReference<>() {
                }
        );
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void startRideNotExist(){
        HttpEntity<Object> requestBody = new HttpEntity<>(null, getHeader(JWT_DRIVER_02));
        ResponseEntity<String> response = restTemplate.exchange(
                RIDE_URL + "/30/start", HttpMethod.PUT, requestBody, new ParameterizedTypeReference<>() {
                }
        );
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void startRide(){
        HttpEntity<Object> requestBody = new HttpEntity<>(null, getHeader(JWT_DRIVER_02));
        ResponseEntity<String> response = restTemplate.exchange(
                RIDE_URL + "/7/start", HttpMethod.PUT, requestBody, new ParameterizedTypeReference<>() {
                }
        );
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //Accept Ride

    @Test
    public void acceptRideNotPending(){
        HttpEntity<Object> requestBody = new HttpEntity<>(null, getHeader(JWT_DRIVER_04));
        ResponseEntity<String> response = restTemplate.exchange(
                RIDE_URL + "/6/accept", HttpMethod.PUT, requestBody, new ParameterizedTypeReference<>() {
                }
        );
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void acceptRideNotExist(){
        HttpEntity<Object> requestBody = new HttpEntity<>(null, getHeader(JWT_DRIVER_01));
        ResponseEntity<String> response = restTemplate.exchange(
                RIDE_URL + "/30/accept", HttpMethod.PUT, requestBody, new ParameterizedTypeReference<>() {
                }
        );
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void acceptRide(){
        HttpEntity<Object> requestBody = new HttpEntity<>(null, getHeader(JWT_DRIVER_05));
        ResponseEntity<String> response = restTemplate.exchange(
                RIDE_URL + "/9/accept", HttpMethod.PUT, requestBody, new ParameterizedTypeReference<>() {
                }
        );
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //End Ride

    @Test
    public void endRideNotStarted(){
        HttpEntity<Object> requestBody = new HttpEntity<>(null, getHeader(JWT_DRIVER_01));
        ResponseEntity<String> response = restTemplate.exchange(
                RIDE_URL + "/5/end", HttpMethod.PUT, requestBody, new ParameterizedTypeReference<>() {
                }
        );
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void endRideNotExist(){
        HttpEntity<Object> requestBody = new HttpEntity<>(null, getHeader(JWT_DRIVER_03));
        ResponseEntity<String> response = restTemplate.exchange(
                RIDE_URL + "/30/end", HttpMethod.PUT, requestBody, new ParameterizedTypeReference<>() {
                }
        );
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void endRide(){
        HttpEntity<Object> requestBody = new HttpEntity<>(null, getHeader(JWT_DRIVER_03));
        ResponseEntity<String> response = restTemplate.exchange(
                RIDE_URL + "/8/end", HttpMethod.PUT, requestBody, new ParameterizedTypeReference<>() {
                }
        );
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //Cancel the ride with an explanation

    @Test
    public void cancelRideNotPendingOrAccepted(){
        RejectionDTO dto = new RejectionDTO("Mrzi me");

        HttpEntity<RejectionDTO> requestBody = new HttpEntity<>(dto, getHeader(JWT_DRIVER_03));
        ResponseEntity<String> response = restTemplate.exchange(
                RIDE_URL + "/8/cancel", HttpMethod.PUT, requestBody, new ParameterizedTypeReference<>() {
                }
        );
        //Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void cancelRideNotExist(){
        RejectionDTO dto = new RejectionDTO("Mrzi me");

        HttpEntity<RejectionDTO> requestBody = new HttpEntity<>(dto, getHeader(JWT_DRIVER_03));
        ResponseEntity<String> response = restTemplate.exchange(
                RIDE_URL + "/30/cancel", HttpMethod.PUT, requestBody, new ParameterizedTypeReference<>() {
                }
        );
        //Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void cancelRide(){
        RejectionDTO dto = new RejectionDTO("Mrzi me");

        HttpEntity<RejectionDTO> requestBody = new HttpEntity<>(dto, getHeader(JWT_DRIVER_04));
        ResponseEntity<String> response = restTemplate.exchange(
                RIDE_URL + "/5/cancel", HttpMethod.PUT, requestBody, new ParameterizedTypeReference<>() {
                }
        );
        //Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //Create favorite locations for quick selection

    @Test
    public void testCreateFavoriteLocationNoName(){
        RideLocationDTO route = new RideLocationDTO(new LocationDTO("Ledinacka 2", 45.248667F, 19.816370F), new LocationDTO("Kulturni Centar LAB", 45.24951752043822F, 19.825208148679025F));
        GetFavoriteLocationsDTO dto = new GetFavoriteLocationsDTO(
                null,
                Arrays.asList(route),
                Arrays.asList(new RidePassengerDTO(8, "marko@gmail.com")),
                "LUXURY", true, false);

        HttpEntity<GetFavoriteLocationsDTO> requestBody = new HttpEntity<>(dto, getHeader(JWT_PASSENGER_01));
        ResponseEntity<String> response = restTemplate.exchange(
                RIDE_URL + "/favorites", HttpMethod.POST, requestBody, new ParameterizedTypeReference<>() {
                }
        );
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testCreateFavoriteLocationNoLocation(){
        RideLocationDTO route = new RideLocationDTO(new LocationDTO("Ledinacka 2", 45.248667F, 19.816370F), new LocationDTO("Kulturni Centar LAB", 45.24951752043822F, 19.825208148679025F));
        GetFavoriteLocationsDTO dto = new GetFavoriteLocationsDTO(
                "Ime moje omiljeno",
                null,
                Arrays.asList(new RidePassengerDTO(8, "marko@gmail.com")),
                "LUXURY", true, false);

        HttpEntity<GetFavoriteLocationsDTO> requestBody = new HttpEntity<>(dto, getHeader(JWT_PASSENGER_01));
        ResponseEntity<String> response = restTemplate.exchange(
                RIDE_URL + "/favorites", HttpMethod.POST, requestBody, new ParameterizedTypeReference<>() {
                }
        );
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testCreateFavoriteLocationNoPassengers(){
        RideLocationDTO route = new RideLocationDTO(new LocationDTO("Ledinacka 2", 45.248667F, 19.816370F), new LocationDTO("Kulturni Centar LAB", 45.24951752043822F, 19.825208148679025F));
        GetFavoriteLocationsDTO dto = new GetFavoriteLocationsDTO(
                "Ime moje omiljeno",
                Arrays.asList(route),
                null,
                "LUXURY", true, false);

        HttpEntity<GetFavoriteLocationsDTO> requestBody = new HttpEntity<>(dto, getHeader(JWT_PASSENGER_01));
        ResponseEntity<String> response = restTemplate.exchange(
                RIDE_URL + "/favorites", HttpMethod.POST, requestBody, new ParameterizedTypeReference<>() {
                }
        );
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testCreateFavoriteLocationNoVehicleType(){
        RideLocationDTO route = new RideLocationDTO(new LocationDTO("Ledinacka 2", 45.248667F, 19.816370F), new LocationDTO("Kulturni Centar LAB", 45.24951752043822F, 19.825208148679025F));
        GetFavoriteLocationsDTO dto = new GetFavoriteLocationsDTO(
                "Ime moje omiljeno",
                Arrays.asList(route),
                Arrays.asList(new RidePassengerDTO(8, "marko@gmail.com")),
                null, true, false);

        HttpEntity<GetFavoriteLocationsDTO> requestBody = new HttpEntity<>(dto, getHeader(JWT_PASSENGER_01));
        ResponseEntity<String> response = restTemplate.exchange(
                RIDE_URL + "/favorites", HttpMethod.POST, requestBody, new ParameterizedTypeReference<>() {
                }
        );
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testCreateFavoriteLocationNoBaby(){
        RideLocationDTO route = new RideLocationDTO(new LocationDTO("Ledinacka 2", 45.248667F, 19.816370F), new LocationDTO("Kulturni Centar LAB", 45.24951752043822F, 19.825208148679025F));
        GetFavoriteLocationsDTO dto = new GetFavoriteLocationsDTO(
                "Ime moje omiljeno",
                Arrays.asList(route),
                Arrays.asList(new RidePassengerDTO(8, "marko@gmail.com")),
                "LUXURY", null, false);

        HttpEntity<GetFavoriteLocationsDTO> requestBody = new HttpEntity<>(dto, getHeader(JWT_PASSENGER_01));
        ResponseEntity<String> response = restTemplate.exchange(
                RIDE_URL + "/favorites", HttpMethod.POST, requestBody, new ParameterizedTypeReference<>() {
                }
        );
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testCreateFavoriteLocationNoPet(){
        RideLocationDTO route = new RideLocationDTO(new LocationDTO("Ledinacka 2", 45.248667F, 19.816370F), new LocationDTO("Kulturni Centar LAB", 45.24951752043822F, 19.825208148679025F));
        GetFavoriteLocationsDTO dto = new GetFavoriteLocationsDTO(
                "Ime moje omiljeno",
                Arrays.asList(route),
                Arrays.asList(new RidePassengerDTO(8, "marko@gmail.com")),
                "LUXURY", false, null);

        HttpEntity<GetFavoriteLocationsDTO> requestBody = new HttpEntity<>(dto, getHeader(JWT_PASSENGER_01));
        ResponseEntity<String> response = restTemplate.exchange(
                RIDE_URL + "/favorites", HttpMethod.POST, requestBody, new ParameterizedTypeReference<>() {
                }
        );
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testCreateFavoriteLocation(){
        RideLocationDTO route = new RideLocationDTO(new LocationDTO("Ledinacka 2", 45.248667F, 19.816370F), new LocationDTO("Kulturni Centar LAB", 45.24951752043822F, 19.825208148679025F));
        GetFavoriteLocationsDTO dto = new GetFavoriteLocationsDTO(
                "Ime moje omiljeno",
                Arrays.asList(route),
                Arrays.asList(new RidePassengerDTO(8, "marko@gmail.com")),
                "LUXURY", true, false);

        HttpEntity<GetFavoriteLocationsDTO> requestBody = new HttpEntity<>(dto, getHeader(JWT_PASSENGER_01));
        ResponseEntity<String> response = restTemplate.exchange(
                RIDE_URL + "/favorites", HttpMethod.POST, requestBody, new ParameterizedTypeReference<>() {
                }
        );
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //Get favorite locations

    @Test
    public void testGetFavoriteLocations(){
        HttpEntity<Object> requestBody = new HttpEntity<>(null, getHeader(JWT_PASSENGER_01));
        ResponseEntity<String> response = restTemplate.exchange(
                RIDE_URL + "/favorites", HttpMethod.GET, requestBody, new ParameterizedTypeReference<>() {
                }
        );
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //Delete existing favorite ride

    @Test
    public void testDeleteFavoriteLocationNotExist(){
        HttpEntity<Object> requestBody = new HttpEntity<>(null, getHeader(JWT_PASSENGER_01));
        ResponseEntity<String> response = restTemplate.exchange(
                RIDE_URL + "/favorites/500", HttpMethod.DELETE, requestBody, new ParameterizedTypeReference<>() {
                }
        );
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteFavoriteLocation(){
        HttpEntity<Object> requestBody = new HttpEntity<>(null, getHeader(JWT_PASSENGER_01));
        ResponseEntity<String> response = restTemplate.exchange(
                RIDE_URL + "/favorites/1", HttpMethod.DELETE, requestBody, new ParameterizedTypeReference<>() {
                }
        );
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}