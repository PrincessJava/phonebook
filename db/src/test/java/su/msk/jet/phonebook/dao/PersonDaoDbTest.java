package su.msk.jet.phonebook.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mybatis.spring.SqlSessionFactoryBean;
import su.msk.jet.phonebook.exception.DaoException;
import su.msk.jet.phonebook.mapper.PersonMapper;
import su.msk.jet.phonebook.model.Person;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonDaoDbTest {

    private final Person person = new Person(null, 1, "x", "y", null);

    @Mock
    private SqlSessionFactoryBean sqlSessionFactoryBean;

    @Mock
    private PersonMapper personMapper;

    @Before
    public void initMocks() throws Exception {
        SqlSessionFactory sessionFactory = mock(SqlSessionFactory.class);
        SqlSession session = mock(SqlSession.class);
        when(session.getMapper(PersonMapper.class)).thenReturn(personMapper);
        when(sessionFactory.openSession()).thenReturn(session);
        when(sqlSessionFactoryBean.getObject()).thenReturn(sessionFactory);
    }

    @Test
    public void addNewPersonTest() throws DaoException {
        PersonDaoDb personDaoDb = new PersonDaoDb(sqlSessionFactoryBean);
        personDaoDb.addPerson(person);
    }

    @Test
    public void getPersonByIdTest() {
        when(personMapper.getPerson(1)).thenReturn(person);
        Person result = personMapper.getPerson(1);
        assertNotNull(result);
        assertEquals(person, result);
    }

    @Test
    public void getPersonByIdNullTest() {
        when(personMapper.getPerson(2)).thenReturn(null);
        Person result = personMapper.getPerson(2);
        assertNull(result);
    }

    @Test
    public void updateExistingPersonTest() {
        PersonDaoDb personDaoDb = new PersonDaoDb(sqlSessionFactoryBean);
        personDaoDb.updatePerson(person, 1);
    }

    @Test
    public void removePersonTest() {
        PersonDaoDb personDaoDb = new PersonDaoDb(sqlSessionFactoryBean);
        personDaoDb.removePerson(1);
    }
}
