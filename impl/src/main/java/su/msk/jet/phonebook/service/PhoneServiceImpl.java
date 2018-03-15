package su.msk.jet.phonebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import su.msk.jet.phonebook.dao.PersonDao;
import su.msk.jet.phonebook.exception.DaoException;
import su.msk.jet.phonebook.model.Person;

@Service
@Transactional
public class PhoneServiceImpl implements PhoneService {

    private final PersonDao personDao;

    @Autowired
    public PhoneServiceImpl(@Qualifier("personDaoDb") PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public void addPerson(Person person) throws DaoException {
        this.personDao.addPerson(person);
    }

    @Override
    public Person getPerson(int id) throws DaoException {
        return this.personDao.getPerson(id);
    }

    @Override
    public void updatePerson(Person person, int id) throws DaoException {
        this.personDao.updatePerson(person, id);
    }

    @Override
    public void removePerson(int id) throws DaoException {
        this.personDao.removePerson(id);
    }

}
