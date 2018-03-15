package su.msk.jet.phonebook.main;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import su.msk.jet.phonebook.config.ClientConfig;
import su.msk.jet.phonebook.controller.Requests;
import su.msk.jet.phonebook.controller.ServicesClient;
import su.msk.jet.phonebook.model.PersonService;

import java.util.List;

class ServiceClientMain {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
        ClientConfig config = new ClientConfig();
        config.configureMessageConverters(converters);
        restTemplate.setMessageConverters(converters);

        ServicesClient personClient = new ServicesClient(restTemplate);
        PersonService service = new PersonService();

        if (args.length > 3) {
            service.setPersonId(Integer.parseInt(args[1]));
            service.setId(Integer.parseInt(args[2]));
            service.setDescription(args[3]);
        }

        switch (Requests.valueOf(args[0])) {
            case POST:
                personClient.postEntity(service);
                break;
            case GET:
                personClient.getEntity(Integer.parseInt(args[1]));
                break;
            case DELETE:
                personClient.deleteEntity(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
        }
    }
}
