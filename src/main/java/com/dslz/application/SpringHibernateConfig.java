package com.dslz.application;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.dslz.beans.Contact;
import com.dslz.beans.Order;
import com.dslz.beans.User;
import com.dslz.factories.DAOFactory;
import com.dslz.factories.MySQLDAOFactory;

/*@Configuration
@EnableTransactionManagement
@ImportResource({"classpath:hibernate5cfg.xml"})*/

public class SpringHibernateConfig {
	
	private static SessionFactory sessionFactory;
	
	@Autowired
	public static void setSessionFactory(SessionFactory sessionFactory) {
		SpringHibernateConfig.sessionFactory = sessionFactory;
	}
	
	public static void main(String[] args) {
		try (ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("hibernate5cfg.xml");
				Session session = sessionFactory.openSession()) {
			System.out.println(sessionFactory);
			Transaction transaction = session.beginTransaction();
			Contact contact1 = new Contact();
			Contact contact2 = (Contact) applicationContext.getBean("contact");
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
			contact2.setName("Fucker");
			/*DAOFactory daoFactory = applicationContext.getBean("mySQLDAOFactory", MySQLDAOFactory.class);
			User user1 = new User();
			user1.setFullname("Frasure Mayer");
			user1.setEmail("FrasureMy@fakeemail.com");
			user1.setPassword("susunok112");
			session.persist(user1);
			daoFactory.getUserService().createUser(user1);
			daoFactory.getUserService().findAllUsers();*/
			transaction.commit();
			
		}
	}

}
