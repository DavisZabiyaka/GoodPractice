package com.dslz.dbConnection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.dslz.beans.Contact;
import com.dslz.beans.Order;
import com.dslz.beans.User;
import com.dslz.factories.DAOFactory;
import com.dslz.factories.MySQLDAOFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "hibernate5cfg.xml")
@Transactional
public class RollBackExample {
	
	private static SessionFactory sessionFactory;
	
	@Autowired
	public static void setSessionFactory(SessionFactory sessionFactory) {
		RollBackExample.sessionFactory = sessionFactory;
	}
	
	@Ignore
	@Test
	public void rollBackDB() {
		try (ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("hibernate5cfg.xml");
				Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			Contact contact1 = new Contact();
			contact1.setName("Tashfeen Afzal");
			contact1.setStreet("1701 West 3rd Street");
			contact1.setCity("Brooklyn");
			contact1.setState("NY");
			contact1.setZipcode("11235");
			contact1.setPhoneNumber("802-555-8735");
			Order order1 = new Order();
			order1.setDateOrdered(LocalDateTime.now());
			order1.setDateFulfilled(LocalDateTime.now());
			order1.setTaxState(new BigDecimal(0.08));
			order1.setTaxFederal(new BigDecimal(0.075));
			order1.setTaxCurrency(new BigDecimal(0.01));
			order1.setShipToContactId(1);
			order1.setBillToContactId(1);
			session.persist(contact1);
			session.persist(order1);
			transaction.commit();
		}
	}
	
	@Ignore
	@Test
	public void noRollBackDB() {
		try (ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("hibernate5cfg.xml");
				Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			Contact contact1 = new Contact();
			contact1.setName("Tashfeen Afzal");
			contact1.setStreet("1701 West 3rd Street");
			contact1.setCity("Brooklyn");
			contact1.setState("NY");
			contact1.setZipcode("11235");
			contact1.setPhoneNumber("802-555-8735");
			Order order1 = new Order();
			order1.setDateOrdered(LocalDateTime.now());
			order1.setDateFulfilled(LocalDateTime.now());
			order1.setTaxState(new BigDecimal(0.08));
			order1.setTaxFederal(new BigDecimal(0.075));
			order1.setTaxCurrency(new BigDecimal(0.01));
			order1.setShipToContactId(1);
			order1.setBillToContactId(1);
			session.persist(contact1);
			session.persist(order1);
			transaction.commit();
		}
	}
	
	@Test
	public void noRollBackWithServiceLayer() {
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("factory-beans.xml")) {
			DAOFactory daoFactory = context.getBean("mySQLDAOFactory", MySQLDAOFactory.class);
			daoFactory.getUserService().createUser(new User(100, "Frasure Mayer", "FrasureMy@fakeemail.com", "susunok112"));
			daoFactory.getUserService().findAllUsers();
		}
	}

}
