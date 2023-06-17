package project.mango.controller;

import mango.dto.UserLoginDTO;
import mango.dto.UserLoginResponseDTO;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RideControllerTest {

    private String RIDE_URL = "/api/ride";

    @Autowired
    private TestRestTemplate restTemplate;

    private String JWT_DRIVER_01;
    private String JTW_DRIVER_02;
    private String JWT_DRIVER_03;
    private String JWT_PASSENGER_01;
    private String JWT_PASSENGER_02;
    private String JWT_PASSENGER_03;
    private String JWT_ADMIN;


    private HttpHeaders getHeader(String jwt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (jwt != null) {
            headers.setBearerAuth(jwt);
        }

        return headers;
    }

    private String login(String email, String password) {
        final String URL = "/api/user/login";

        UserLoginDTO payload = new UserLoginDTO(email, password);
        HttpEntity<UserLoginDTO> requestBody = new HttpEntity<UserLoginDTO>(payload, getHeader(null));
        ResponseEntity<UserLoginResponseDTO> response = restTemplate.exchange(
                URL, HttpMethod.POST,requestBody,new ParameterizedTypeReference<UserLoginResponseDTO>() {}
        );

        return response.getBody().getAccessToken();
    }

    public void login(){
        JWT_DRIVER_01 = login("momir@gmail.com", "momir@gmail.com");
        JWT_DRIVER_01 = login("marinara@gmail.com", "marinara@gmail.com");
        JWT_DRIVER_01 = login("adamdriver@gmail.com", "adamdriver@gmail.com");

    }

}
