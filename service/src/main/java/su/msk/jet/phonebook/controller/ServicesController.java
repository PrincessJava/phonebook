package su.msk.jet.phonebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import su.msk.jet.phonebook.exception.BadRequestException;
import su.msk.jet.phonebook.exception.DaoException;
import su.msk.jet.phonebook.model.PersonService;
import su.msk.jet.phonebook.service.ServicesService;

import java.util.List;

@RestController
@RequestMapping("/service")
public class ServicesController {

    private final ServicesService servicesService;

    @Autowired
    public ServicesController(ServicesService servicesService) {
        this.servicesService = servicesService;
    }

    @PostMapping(value = "/{personId}")
    public @ResponseBody
    PersonService addService(@PathVariable int personId, @RequestBody PersonService personService) throws BadRequestException, DaoException {
        if (personId != personService.getPersonId()) {
            throw new BadRequestException("ID не совпадают");
        }
        this.servicesService.addServices(personService);
        return personService;
    }

    @GetMapping(value = "/{personId}")
    public @ResponseBody
    List<PersonService> getServices(@PathVariable int personId) throws DaoException {
        return this.servicesService.getServices(personId);
    }

    @DeleteMapping(value = "/{personId}/{id}")
    public @ResponseBody
    void deleteService(@PathVariable int personId, @PathVariable int id) throws DaoException {
        this.servicesService.removeService(personId, id);
    }

    @DeleteMapping(value = "/{personId}")
    public @ResponseBody
    void deleteAllUserServices(@PathVariable int personId) throws DaoException {
        this.servicesService.removeAllServices(personId);
    }
}
