package com.dslz.application;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.dslz.beans.Contact;

public class HibernateTables {
	
	public static void main(String[] args) {
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Contact contact1 = new Contact(
				1,
				"Tashfeen Afzal",
				"1701 West 3rd Street",
				"Brooklyn",
				"NY",
				"11235",
				"802-555-8735");
		session.persist(contact1);
		transaction.commit();
		session.close();
	}

}
