package su.msk.jet.phonebook.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import su.msk.jet.phonebook.exception.DaoException;
import su.msk.jet.phonebook.mapper.ServiceMapper;
import su.msk.jet.phonebook.model.PersonService;

import java.util.List;

@Component
public class ServiceDaoImpl implements ServiceDao {

    private final SqlSessionFactory sessionFactory;

    @Autowired
    public ServiceDaoImpl(SqlSessionFactoryBean sessionFactoryBean) throws DaoException {
        assert sessionFactoryBean != null;
        try {
            this.sessionFactory = sessionFactoryBean.getObject();
        } catch (Exception e) {
            throw new DaoException("Error while connecting to database", e);
        }
    }

    @Override
    public void addServices(PersonService personService) throws DaoException {
        try (SqlSession session = sessionFactory.openSession()) {
            ServiceMapper serviceMapper = session.getMapper(ServiceMapper.class);
            serviceMapper.addService(personService);
        } catch (Exception e) {
            throw new DaoException("Error while adding service to database", e);
        }
    }

    @Override
    public List<PersonService> getServices(int personId) throws DaoException {
        try (SqlSession session = sessionFactory.openSession()) {
            ServiceMapper serviceMapper = session.getMapper(ServiceMapper.class);
            return serviceMapper.showServices(personId);
        } catch (Exception e) {
            throw new DaoException("Error while getting services to database", e);
        }
    }

    @Override
    public void removeService(int personId, int id) throws DaoException {
        try (SqlSession session = sessionFactory.openSession()) {
            ServiceMapper serviceMapper = session.getMapper(ServiceMapper.class);
            serviceMapper.removeService(personId, id);
        } catch (Exception e) {
            throw new DaoException("Error while removing service from database", e);
        }
    }

    @Override
    public void removeAllServices(int personId) throws DaoException {
        try (SqlSession session = sessionFactory.openSession()) {
            ServiceMapper serviceMapper = session.getMapper(ServiceMapper.class);
            serviceMapper.removeAllServices(personId);
        } catch (Exception e) {
            throw new DaoException("Error while removing user services from database", e);
        }
    }


}
