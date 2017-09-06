package com.example;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.query.Query;

import Entities.User_Entities;

@Path("/login")
public class HelloService {
	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	@Path("/getAll")
	public List<User_Entities> getMsg() {
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		Query q = session.createQuery("From User_Entities");

		List<User_Entities> resultList = q.list();
		System.out.println("num of Topics :" + resultList.size());
		for (User_Entities next : resultList) {
			System.out.println(next.getUsername());
		}

		session.getTransaction().commit();

		session.close();

		return resultList;
	}
}
