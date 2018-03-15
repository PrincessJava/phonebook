package su.msk.jet.phonebook.controller;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import su.msk.jet.phonebook.model.PersonService;

import java.util.Arrays;

public class ServicesClient {
    private RestTemplate restTemplate;
    private String url = "http://localhost:8080/phonebook/service/";

    public ServicesClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void postEntity(PersonService service) {
        System.out.println("POST");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String postURL = url + Integer.toString(service.getPersonId());
        HttpEntity<PersonService> request = new HttpEntity<>(service, headers);
        ResponseEntity<PersonService> response = restTemplate.exchange(postURL, HttpMethod.POST, request, PersonService.class);
        System.out.println("Response for POST request: " + response.getBody());
    }

    public void getEntity(int id) {
        System.out.println("GET");
        String getURL = url + Integer.toString(id);
        PersonService[] services = restTemplate.getForObject(getURL, PersonService[].class, 200);
        if (services != null) {
            System.out.println("Response for GET request: ");
            for (PersonService service : services) {
                System.out.println(service.toString());
            }
        } else {
            System.out.println("Response for GET request: NULL");
        }
    }

    public void deleteEntity(int id, int person_id) {
        System.out.println("DELETE");
        String deleteURL = url + Integer.toString(id) + Integer.toString(person_id);
        restTemplate.delete(deleteURL);
    }
}
