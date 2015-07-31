package it.synclab.sms.dbfactory;

import org.hibernate.*;
import org.hibernate.cfg.*;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	static {
		try {
			/** CREATE THE SESSIONFACTORY FROM HIBERNATE.CFG.XML **/
			sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}