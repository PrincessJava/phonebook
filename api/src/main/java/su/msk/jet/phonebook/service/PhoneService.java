package su.msk.jet.phonebook.service;

import su.msk.jet.phonebook.exception.DaoException;
import su.msk.jet.phonebook.model.Person;

public interface PhoneService {
    void addPerson(Person person) throws DaoException;

    Person getPerson(int id) throws DaoException;

    void updatePerson(Person person, int id) throws DaoException;

    void removePerson(int id) throws DaoException;
}
