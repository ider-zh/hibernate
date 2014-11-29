package com.hibernate.test;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.pojo.Users;

public class Hibernate {
	SessionFactory factory;
	Session session;
	/*
	 * ����֮ǰ��ʼ������
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println("-----���Կ�ʼ��ʼ������-------");
		Configuration config = new Configuration().configure();	
		ServiceRegistry sr = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		factory = config.buildSessionFactory(sr);				
		session = factory.openSession();
	}
	/*
	 * ���Խ����ͷ�����
	 */
	@After
	public void tearDown() throws Exception {
		System.out.println("-----���Խ����ͷ�����-------");
		if(session.isOpen()){
			session.close();
		}
	}

	@Test
	public void test() {
		Users u = new Users("root", "123456", 12, new java.util.Date());
		Transaction tx=session.beginTransaction();
		session.persist(u);
		tx.commit();
	}

}
