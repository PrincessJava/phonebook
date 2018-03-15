package su.msk.jet.phonebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import su.msk.jet.phonebook.exception.BadRequestException;
import su.msk.jet.phonebook.exception.DaoException;
import su.msk.jet.phonebook.model.Person;
import su.msk.jet.phonebook.service.PhoneService;

import javax.validation.Valid;

@RestController
@RequestMapping("/obj")
public class PhoneController {

    private final PhoneService phoneService;

    @Autowired
    public PhoneController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @PostMapping
    public @ResponseBody
    Person postPerson(@RequestBody @Valid Person person) throws BadRequestException, DaoException {
        if (person.getId() != 0) {
            throw new BadRequestException("ID должен быть равен 0");
        }
        this.phoneService.addPerson(person);
        return person;
    }

    @GetMapping(value = "/{id}")
    public @ResponseBody
    Person getPerson(@PathVariable int id) throws DaoException {
        return this.phoneService.getPerson(id);
    }

    @PutMapping(value = "/{id}")
    public @ResponseBody
    Person putPerson(@PathVariable int id, @RequestBody @Valid Person person) throws BadRequestException, DaoException {
        if (person.getId() != id) {
            throw new BadRequestException("ID не совпадают");
        }
        if (person.getId() == 0) {
            throw new BadRequestException("ID не должен равняться 0");
        }
        this.phoneService.updatePerson(person, id);
        return this.phoneService.getPerson(id);
    }

    @DeleteMapping(value = "/{id}")
    public @ResponseBody
    void deletePerson(@PathVariable int id) throws DaoException {
        this.phoneService.removePerson(id);
    }
}

