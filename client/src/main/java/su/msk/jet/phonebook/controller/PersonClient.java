package su.msk.jet.phonebook.controller;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import su.msk.jet.phonebook.model.Person;

import static java.util.Collections.singletonList;

public class PersonClient {

    private RestTemplate restTemplate;
    private String url = "http://localhost:8080/phonebook/obj/";

    public PersonClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void postEntity(Person person) {
        System.out.println("POST");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(singletonList(MediaType.APPLICATION_JSON));

        String postURL = url;
        HttpEntity<Person> request = new HttpEntity<>(person, headers);
        ResponseEntity<Person> response = restTemplate.exchange(postURL, HttpMethod.POST, request, Person.class);
        System.out.println("Response for POST request: " + response.getBody());
    }

    public void getEntity(int id) {
        System.out.println("GET");
        String getURL = url + Integer.toString(id);
        Person person = restTemplate.getForObject(getURL, Person.class, 200);
        if (person != null) {
            System.out.println("Response for GET request: " + person.toString());
        } else {
            System.out.println("Response for GET request: NULL");
        }
    }

    public void putEntity(int id, Person person) {
        System.out.println("PUT");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String putURL = url + Integer.toString(id);
        HttpEntity<Person> request = new HttpEntity<>(person, headers);
        ResponseEntity<Person> response = restTemplate.exchange(putURL, HttpMethod.PUT, request, Person.class);

        System.out.println("Response for PUT request: " + response.getBody());
    }

    public void deleteEntity(int id) {
        System.out.println("DELETE");
        String deleteURL = url + Integer.toString(id);
        restTemplate.delete(deleteURL);
    }
}
