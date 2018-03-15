package su.msk.jet.phonebook.controller;

import org.junit.Test;
import su.msk.jet.phonebook.exception.BadRequestException;
import su.msk.jet.phonebook.exception.DaoException;
import su.msk.jet.phonebook.model.Person;
import su.msk.jet.phonebook.service.PhoneService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class PhoneControllerTest {

    private PhoneService phoneService = mock(PhoneService.class);

    @Test(expected = BadRequestException.class)
    public void postPersonTestWhenExists() throws DaoException, BadRequestException {
        Person person = new Person(1, "x", "y");
        PhoneController phoneController = new PhoneController(phoneService);
        phoneController.postPerson(person);
    }

    @Test(expected = DaoException.class)
    public void postPersonError() throws DaoException, BadRequestException {
        doThrow(new DaoException()).when(phoneService).addPerson(any(Person.class));
        PhoneController phoneController = new PhoneController(phoneService);
        Person person = new Person(0, "x", "y");
        phoneController.postPerson(person);
    }

    @Test
    public void postPersonTestWhenNotExists() throws DaoException, BadRequestException {
        PhoneController phoneController = new PhoneController(phoneService);
        Person person = new Person(0, "x", "y");
        Person result = phoneController.postPerson(person);
        assertNotNull(result);
        assertEquals(person, result);
    }

    @Test(expected = DaoException.class)
    public void getPersonByIdTestError() throws DaoException {
        doThrow(new DaoException()).when(phoneService).getPerson(anyInt());
        PhoneController phoneController = new PhoneController(phoneService);
        phoneController.getPerson(1);
    }

    @Test
    public void getPersonByIdOk() throws DaoException {
        PhoneController phoneController = new PhoneController(phoneService);
        Person person = new Person(1, "x", "y");
        when(phoneService.getPerson(1)).thenReturn(person);
        Person result = phoneController.getPerson(1);
        assertNotNull(result);
        assertEquals(person, result);
    }

    @Test
    public void putPersonTestWhenExists() throws DaoException, BadRequestException {
        PhoneController phoneController = new PhoneController(phoneService);
        Person person = new Person(1, "y", "x");
        phoneController.putPerson(1, person);
    }

    @Test(expected = BadRequestException.class)
    public void putPersonTestWhenNotExists() throws DaoException, BadRequestException {
        Person person = new Person(0, "y", "x");
        PhoneController phoneController = new PhoneController(phoneService);
        phoneController.putPerson(0, person);
    }

    @Test(expected = BadRequestException.class)
    public void putPersonTestWhenDiffId() throws BadRequestException, DaoException {
        PhoneController phoneController = new PhoneController(phoneService);
        Person person = new Person(1, "y", "x");
        phoneController.putPerson(0, person);
    }

    @Test(expected = DaoException.class)
    public void putPersonTestError() throws DaoException, BadRequestException {
        doThrow(new DaoException()).when(phoneService).updatePerson(any(Person.class), anyInt());
        Person person = new Person(1, "", "");
        PhoneController phoneController = new PhoneController(phoneService);
        phoneController.putPerson(1, person);
    }

    @Test
    public void deletePersonTest() throws DaoException {
        PhoneController phoneController = new PhoneController(phoneService);
        phoneController.deletePerson(1);
    }

    @Test(expected = DaoException.class)
    public void deletePersonTestError() throws DaoException {
        String errorMessage = "Не удалось удалить пользователя из базы данных";
        doThrow(new DaoException(errorMessage)).when(phoneService).removePerson(anyInt());
        PhoneController phoneController = new PhoneController(phoneService);
        phoneController.deletePerson(anyInt());
    }
}
