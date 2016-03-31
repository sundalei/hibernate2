package com.shengsiyuan.hibernate;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Timestamp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateTest {
	private static SessionFactory sessionFactory;
	
	static {
		try {
			Configuration configuration = new Configuration();
		    configuration.configure();
		    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		    sessionFactory = configuration.buildSessionFactory(registry);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception{
		People people = new People();
		
		people.setUsername("username");
		people.setPassword("123456");
		people.setGender('F');
		people.setGraduation(false);
		java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
		System.out.println(date);
		people.setBirthday(date);
		people.setTelphone(987654);
		people.setMarryTime(new Timestamp(new java.util.Date().getTime()));
		
		InputStream is = new FileInputStream(new File("D:/People.hbm.xml"));
		int length = is.available();
		byte[] buffer = new byte[length];
		is.read(buffer);
		is.close();
		
		people.setFile(buffer);
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(people);
			tx.commit();
		} catch(Exception ex) {
			if(null != tx) {
				tx.rollback();
			}
		} finally {
			if(null != session) {
				session.close();
			}
		}
	}
}
