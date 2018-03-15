package su.msk.jet.phonebook.dao;

import su.msk.jet.phonebook.exception.DaoException;
import su.msk.jet.phonebook.model.Person;

public interface PersonDao {
    Person addPerson(Person person) throws DaoException;

    Person getPerson(int id) throws DaoException;

    void updatePerson(Person person, int id) throws DaoException;

    void removePerson(int id) throws DaoException;
}
