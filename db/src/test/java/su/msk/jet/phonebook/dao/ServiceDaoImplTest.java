package su.msk.jet.phonebook.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mybatis.spring.SqlSessionFactoryBean;
import su.msk.jet.phonebook.mapper.ServiceMapper;
import su.msk.jet.phonebook.model.PersonService;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ServiceDaoImplTest {

    private final PersonService service = new PersonService(1, 1, "one");

    @Mock
    private SqlSessionFactoryBean sqlSessionFactoryBean;

    @Mock
    private ServiceMapper serviceMapper;

    private ServiceDaoImpl serviceDao;

    @Before
    public void initMocks() throws Exception {
        SqlSessionFactory sessionFactory = mock(SqlSessionFactory.class);
        SqlSession session = mock(SqlSession.class);
        when(session.getMapper(ServiceMapper.class)).thenReturn(serviceMapper);
        when(sessionFactory.openSession()).thenReturn(session);
        when(sqlSessionFactoryBean.getObject()).thenReturn(sessionFactory);
        serviceDao = new ServiceDaoImpl(sqlSessionFactoryBean);
    }

    @Test
    public void addNewServiceTest() {
        serviceDao.addServices(service);
    }

    @Test
    public void getServiceByIdTest() {
        List<PersonService> services = new LinkedList<>();
        services.add(service);
        when(serviceMapper.showServices(1)).thenReturn(services);
        List<PersonService> result = serviceMapper.showServices(1);
        assertNotNull(result);
        assertEquals(services.size(), result.size());
        PersonService service1 = services.get(0);
        assertEquals(service1.getId(), service.getId());
        assertEquals(service1.getPersonId(), service.getPersonId());
        assertEquals(service1.getDescription(), service.getDescription());
    }

    @Test
    public void removeServiceTest() {
        serviceDao.removeService(1, 1);
    }
}
