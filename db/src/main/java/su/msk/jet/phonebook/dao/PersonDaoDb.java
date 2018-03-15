package su.msk.jet.phonebook.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import su.msk.jet.phonebook.exception.DaoException;
import su.msk.jet.phonebook.mapper.PersonMapper;
import su.msk.jet.phonebook.model.Person;


@Component("personDaoDb")
public class PersonDaoDb implements PersonDao {

    private final SqlSessionFactory sessionFactory;

    @Autowired
    public PersonDaoDb(final SqlSessionFactoryBean sessionFactoryBean) throws DaoException {
        assert sessionFactoryBean != null;
        try {
            this.sessionFactory = sessionFactoryBean.getObject();
        } catch (Exception e) {
            throw new DaoException("Error while connecting to database", e);
        }
    }

    @Override
    public Person addPerson(final Person person) throws DaoException {
        try (SqlSession session = sessionFactory.openSession()) {
            PersonMapper personMapper = session.getMapper(PersonMapper.class);
            person.assignUuid();
            personMapper.addPerson(person);
            session.commit();
        } catch (Exception e) {
            throw new DaoException("Error while adding user to database", e);
        }
        return person;
    }

    @Override
    public Person getPerson(final int id) throws DaoException {
        try (SqlSession session = sessionFactory.openSession()) {
            PersonMapper personMapper = session.getMapper(PersonMapper.class);
            Person person = personMapper.getPerson(id);
            session.commit();

            return person;
        } catch (Exception e) {
            throw new DaoException("Error while getting user from database", e);
        }
    }

    @Override
    public void updatePerson(final Person person, final int id) throws DaoException {
        try (SqlSession session = sessionFactory.openSession()) {
            PersonMapper personMapper = session.getMapper(PersonMapper.class);
            personMapper.updatePerson(person);
            session.commit();

        } catch (Exception e) {
            throw new DaoException("Error while updating user in database", e);
        }
    }

    @Override
    public void removePerson(final int id) throws DaoException {
        try (SqlSession session = sessionFactory.openSession()) {
            PersonMapper personMapper = session.getMapper(PersonMapper.class);
            personMapper.removePerson(id);
            session.commit();

        } catch (Exception e) {
            throw new DaoException("Error while removing user from database", e);
        }
    }
}
