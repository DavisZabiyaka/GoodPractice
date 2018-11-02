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
			Transaction transaction = session.beginTransaction();
			Contact contact1 = new Contact();
			contact1.setName("Tashfeen Afzal");
			contact1.setStreet("1701 West 3rd Street");
			contact1.setCity("Brooklyn");
			contact1.setState("NY");
			contact1.setZipcode("11235");
			contact1.setPhoneNumber("802-555-8735");/*
			Order order1 = new Order();
			order1.setDateOrdered(LocalDateTime.now());
			order1.setDateFulfilled(LocalDateTime.now());
			order1.setTaxState(new BigDecimal(0.08));
			order1.setTaxFederal(new BigDecimal(0.075));
			order1.setTaxCurrency(new BigDecimal(0.01));
			order1.setShipToContactId(1);
			order1.setBillToContactId(1);*/
			session.persist(contact1);
			/*session.persist(order1);*/
			transaction.commit();
		}
	}
	
	/*public static void main(String[] args) {
		System.out.println(sessionFactory.isOpen());
		try (Session session = sessionFactory.openSession();) {
			Transaction transaction = session.beginTransaction();
			ContactInformation contact1 = new ContactInformation(
					1,
					"Tashfeen Afzal",
					"1701 West 3rd Street",
					"Brooklyn",
					"NY",
					"11235",
					"802-555-8735");
			session.persist(contact1);
			transaction.commit();
		}
	}*/

}
