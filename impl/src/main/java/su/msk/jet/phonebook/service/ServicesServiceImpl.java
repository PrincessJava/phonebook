package su.msk.jet.phonebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import su.msk.jet.phonebook.dao.ServiceDao;
import su.msk.jet.phonebook.exception.DaoException;
import su.msk.jet.phonebook.model.PersonService;

import java.util.List;

@Service
@Transactional
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class ServicesServiceImpl implements ServicesService {

    private final ServiceDao serviceDao;

    @Autowired
    public ServicesServiceImpl(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }

    @Override
    public void addServices(PersonService personService) throws DaoException {
        serviceDao.addServices(personService);
    }

    @Override
    public List<PersonService> getServices(int personId) throws DaoException {
        return serviceDao.getServices(personId);
    }

    @Override
    public void removeService(int personId, int id) throws DaoException {
        serviceDao.removeService(personId, id);
    }

    @Override
    public void removeAllServices(int personId) throws DaoException {
        serviceDao.removeAllServices(personId);
    }
}
