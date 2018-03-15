package su.msk.jet.phonebook.dao;

import org.springframework.stereotype.Component;
import su.msk.jet.phonebook.exception.DaoException;
import su.msk.jet.phonebook.model.Person;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component("personDaoMap")
public class PersonDaoMap implements PersonDao {

    private final HashMap<Integer, Person> phones = new HashMap<>();
    private final AtomicInteger counter = new AtomicInteger();

    @Override
    public Person addPerson(final Person person) throws DaoException {
        if (person == null) {
            throw new DaoException("Empty request");
        }
        person.setId(counter.incrementAndGet());
        phones.put(person.getId(), person);
        return person;
    }

    @Override
    public Person getPerson(final int id) {
        return phones.get(id);
    }

    @Override
    public void updatePerson(final Person person, final int id) {
        phones.put(id, person);
    }

    @Override
    public void removePerson(final int id) {
        phones.remove(id);
    }


}
