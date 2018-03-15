package su.msk.jet.phonebook.controller;

import org.junit.Before;
import org.junit.Test;
import su.msk.jet.phonebook.exception.BadRequestException;
import su.msk.jet.phonebook.exception.DaoException;
import su.msk.jet.phonebook.model.PersonService;
import su.msk.jet.phonebook.service.ServicesService;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ServicesControllerTest {

    PersonService service = new PersonService(1, 1, "one");
    private ServicesService servicesService = mock(ServicesService.class);
    private ServicesController controller;

    @Before
    public void init() {
        controller = new ServicesController(servicesService);
    }

    @Test(expected = BadRequestException.class)
    public void addWhenDifferentId() {
        controller.addService(2, service);
    }

    @Test(expected = DaoException.class)
    public void addingError() {
        doThrow(new DaoException()).when(servicesService).addServices(any(PersonService.class));
        controller.addService(1, service);
    }

    @Test
    public void addingOk() {
        PersonService result = controller.addService(1, service);
        assertNotNull(result);
        assertEquals(service, result);
    }

    @Test(expected = DaoException.class)
    public void gettingError() {
        doThrow(new DaoException()).when(servicesService).getServices(anyInt());
        controller.getServices(1);
    }

    @Test
    public void gettingOk() {
        List<PersonService> services = new LinkedList<>();
        services.add(service);
        when(servicesService.getServices(1)).thenReturn(services);
        List<PersonService> result = controller.getServices(1);
        assertNotNull(result);
        assertEquals(services.size(), result.size());
        PersonService resultService = services.get(0);
        assertEquals(service, resultService);
    }

    @Test(expected = DaoException.class)
    public void deletingError() {
        doThrow(new DaoException()).when(servicesService).removeService(anyInt(), anyInt());
        controller.deleteService(1, 1);
    }

    @Test
    public void deletingOk() {
        controller.deleteService(1, 1);
    }
}


