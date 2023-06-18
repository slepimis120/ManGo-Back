package com.mango.controller;

import com.mango.dto.ResponseRideDTO;
import com.mango.dto.UserLoginDTO;
import com.mango.dto.UserLoginResponseDTO;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

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
    private String JTW_DRIVER_02;
    private String JWT_DRIVER_03;
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
        JTW_DRIVER_02 = login("marinara@gmail.com", "marinara@gmail.com");
        JWT_DRIVER_03 = login("adamdriver@gmail.com", "adamdriver@gmail.com");
        JWT_PASSENGER_01 = login("marko@gmail.com", "marko@gmail.com");
        JWT_PASSENGER_02 = login("milica@gmail.com", "milica@gmail.com");
        JWT_PASSENGER_03 = login("meraklovski@gmail.com", "meraklovski@gmail.com");
        //JWT_PASSENGER_04 = login("slepimis120@gmail.com", "slepimis120@gmail.com");
        JWT_PASSENGER_04 = login("milosobrenovic@hotmail.com", "milosobrenovic@hotmail.com");
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //Active Ride for Driver

    @Test
    public void testGetDriverActiveRideNotFound() {
        HttpEntity<Object> httpEntity = new HttpEntity<>(null, getHeader(JWT_DRIVER_03));

        ResponseEntity<String> responseEntity =
                restTemplate.exchange(RIDE_URL + "/driver/3/active", HttpMethod.GET,
                        httpEntity, String.class);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }


}
