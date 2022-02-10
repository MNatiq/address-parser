package de.friday.controller;

import de.friday.controller.apimodel.AddressRequest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext
public class AddressParserRestTest {

    @LocalServerPort
    private int port;

    @Autowired private TestRestTemplate restTemplate;
    @Autowired private AddressParserController controller;

    @Test
    @Order(1)
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    @Order(2)
    void parseAddress() throws IOException, InterruptedException {
    AddressRequest addressRequest = new AddressRequest("Auf der Vogelwiese 23 b");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<AddressRequest> request = new HttpEntity<>(addressRequest, headers);
        String url = "http://localhost:" + port + "/v1/address-parser/parse";
        String[] parsedAddress = restTemplate.postForObject(url, request, String[].class);
        Assertions.assertNotNull(parsedAddress);
        assertThat(parsedAddress).hasSize(2).containsExactly("Auf der Vogelwiese", "23 b");

    }
}
