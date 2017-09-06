package com.example;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.query.Query;

import Entities.User_Entities;

@Path("/login")
public class Login {

	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	@Path("/get/{email}/{pass}")
	public User_Entities getMsg(@PathParam("email") String email, @PathParam("pass") String pass) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		Query q = session.createQuery("From User_Entities where email=:email and password =:pass");
		q.setParameter("email", email);
		q.setParameter("pass", pass);
		User_Entities resultList = (User_Entities) q.uniqueResult();

		session.getTransaction().commit();

		session.close();

		return resultList;
	}
}
