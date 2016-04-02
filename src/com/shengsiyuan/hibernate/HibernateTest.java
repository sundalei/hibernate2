package com.shengsiyuan.hibernate;

import java.util.Iterator;

import org.hibernate.Query;
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
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception{
		/*
		People people = new People();
		
		people.setUsername("zhangsan");
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
		*/
		
		/*
		Session session = sessionFactory.openSession();
		Transaction transation = null;
		
		try {
			transation = session.beginTransaction();
			Query query = session.createQuery("from People as p order by p.id asc").setFirstResult(1).setMaxResults(20);
			List<People> list = (List<People>)query.list();
			
			for(Iterator<People> iter = list.iterator(); iter.hasNext(); ) {
				People people = iter.next();
				
				System.out.println(people.getUsername());
				System.out.println(people.getPassword());
				System.out.println(people.getTelphone());
				System.out.println(people.getBirthday());
				System.out.println(people.getId());
				System.out.println(people.getMarryTime());
				System.out.println(people.getGender());
				System.out.println(people.isGraduation());
				System.out.println("----------------------------");
				
				byte[] buffer = people.getFile();
				OutputStream os = new FileOutputStream(new File("d:/" + people.getId() + ".xml"));
				os.write(buffer);
				os.close();
			}
			
			transation.commit();
		} catch(Exception ex) {
			if(null != transation) {
				transation.rollback();
			}
		} finally {
			if(null != session) {
				session.close();
			}
		}
		*/
		
		/*
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			
			People people = session.load(People.class, new Long(1));
			people.setUsername("lisi");
			people.setGender('M');
			
			people.setUsername("wangwu");
			people.setGender('F');
			
			people.setUsername("zhangsan");
			
			//session.update(people);
			
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
		*/
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		//List<People> list = null;
		Iterator<People> iter = null;
		
		try {
			tx = session.beginTransaction();
			
			/*
			Query query = session.createQuery("from People");
			
			Iterator<People> iter = (Iterator<People>)query.iterate();
			while(iter.hasNext()) {
				session.delete(iter.next());
			}
			*/
			
			Query query = session.createQuery("from People");
			iter = (Iterator<People>)query.iterate();
			
			//list = (List<People>)query.list();
			
//			for(Iterator<People> iter = list.iterator(); iter.hasNext(); ) {
//				session.delete(iter.next());
//			}
			
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
		
		while(iter.hasNext()) {
			People people = iter.next();
			System.out.println(people.getId());
		}
		
		sessionFactory.close();	
	}
}
