package su.msk.jet.phonebook.service;

import org.junit.Test;
import su.msk.jet.phonebook.dao.PersonDao;
import su.msk.jet.phonebook.model.Person;

import static org.mockito.Mockito.mock;

public class PhoneServiceTest {

    private final Person person = new Person(null, 1, "x", "y", null);

    @Test
    public void addNewPerson() throws Exception {
        PersonDao personDao = mock(PersonDao.class);
        PhoneServiceImpl phoneService = new PhoneServiceImpl(personDao);
        phoneService.addPerson(person);
    }

    @Test
    public void getPersonById() throws Exception {
        PersonDao personDao = mock(PersonDao.class);
        PhoneServiceImpl phoneService = new PhoneServiceImpl(personDao);
        phoneService.getPerson(1);
    }

    @Test
    public void updateExistingPerson() throws Exception {
        PersonDao personDao = mock(PersonDao.class);
        PhoneServiceImpl phoneService = new PhoneServiceImpl(personDao);
        phoneService.updatePerson(person, 1);
    }

    @Test
    public void removePerson() throws Exception {
        PersonDao personDao = mock(PersonDao.class);
        PhoneServiceImpl phoneService = new PhoneServiceImpl(personDao);
        phoneService.removePerson(1);
    }
}
