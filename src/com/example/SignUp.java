package com.example;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Query;
import org.hibernate.Session;

import Entities.User_Entities;

@Path("/signup")
public class SignUp {

	// *************************** CheckMail *********************
	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	@Path("/check/{email}")
	public User_Entities CheckMail(@PathParam("email") String email) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		Query query = session.createQuery("from User_Entities where email=:email");
		query.setParameter("email", email);
		User_Entities user_Entities = (User_Entities) query.uniqueResult();
		session.getTransaction().commit();
		session.close();
		User_Entities userRestult = new User_Entities();

		if (user_Entities == null) {
			userRestult.setStatus(1);
		} else {

			userRestult.setStatus(0);
		}

		return userRestult;
	}

	@POST
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(value = MediaType.APPLICATION_JSON)
	@Path("/save")
	public Response save_User(User_Entities user_Entities) throws URISyntaxException {

		if (user_Entities == null) {
			return Response.status(400).entity("Please add employee details !!").build();
		}

		if (user_Entities.getEmail() == null || user_Entities.getPassword() == null) {
			return Response.status(400).entity("Please provide the Email and password  !!").build();
		}

		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		session.save(user_Entities);
		session.getTransaction().commit();
		session.close();

		return Response
				.created(new URI("/rest/login/get/" + user_Entities.getEmail() + "/" + user_Entities.getPassword()))
				.build();

	}

}
