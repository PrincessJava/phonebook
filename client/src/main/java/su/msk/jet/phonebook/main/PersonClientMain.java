package su.msk.jet.phonebook.main;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import su.msk.jet.phonebook.config.ClientConfig;
import su.msk.jet.phonebook.controller.PersonClient;
import su.msk.jet.phonebook.controller.Requests;
import su.msk.jet.phonebook.model.Person;

import java.util.List;

class PersonClientMain {
    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
        ClientConfig config = new ClientConfig();
        config.configureMessageConverters(converters);
        restTemplate.setMessageConverters(converters);

        PersonClient personClient = new PersonClient(restTemplate);
        Person person = new Person();

        if (args.length == 4) {
            person.setId(Integer.parseInt(args[1]));
            person.setName(args[2]);
            person.setPhoneNumber(args[3]);
        }

        switch (Requests.valueOf(args[0])) {
            case POST:
                personClient.postEntity(person);
                break;
            case GET:
                personClient.getEntity(Integer.parseInt(args[1]));
                break;
            case PUT:
                personClient.putEntity(Integer.parseInt(args[1]), person);
                break;
            case DELETE:
                personClient.deleteEntity(Integer.parseInt(args[1]));
        }
    }

}