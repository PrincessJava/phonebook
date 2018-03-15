package su.msk.jet.phonebook.dao;

import su.msk.jet.phonebook.exception.DaoException;
import su.msk.jet.phonebook.model.PersonService;

import java.util.List;

public interface ServiceDao {

    void addServices(PersonService personService) throws DaoException;

    List<PersonService> getServices(int personId) throws DaoException;

    void removeService(int personId, int id) throws DaoException;

    void removeAllServices(int personId) throws DaoException;
}
