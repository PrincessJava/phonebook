package su.msk.jet.phonebook.dao;

import org.junit.Test;
import su.msk.jet.phonebook.exception.DaoException;
import su.msk.jet.phonebook.model.Person;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PersonDaoMapTest {

    private final Person person = new Person(null, 1, "x", "y", null);

    @Test
    public void addNewPerson() throws DaoException {
        PersonDaoMap personDaoMap = new PersonDaoMap();
        Person actualPerson = personDaoMap.addPerson(person);
        assertThat(actualPerson, is(person));
    }

    @Test
    public void getPersonById() {
        PersonDaoMap personDaoMap = new PersonDaoMap();
        Person actualPerson = personDaoMap.getPerson(1);
        assertThat(actualPerson, is(person));
    }

    @Test
    public void updateExistingPerson() {
        PersonDaoMap personDaoMap = new PersonDaoMap();
        personDaoMap.updatePerson(person, 1);
    }

    @Test
    public void removePerson() {
        PersonDaoMap personDaoMap = new PersonDaoMap();
        personDaoMap.removePerson(1);
    }
}
