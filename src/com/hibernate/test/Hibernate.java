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
	 * 测试之前初始化数据
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println("-----测试开始初始化数据-------");
		Configuration config = new Configuration().configure();	
		ServiceRegistry sr = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		factory = config.buildSessionFactory(sr);				
		session = factory.openSession();
	}
	/*
	 * 测试结束释放数据
	 */
	@After
	public void tearDown() throws Exception {
		System.out.println("-----测试结束释放数据-------");
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
