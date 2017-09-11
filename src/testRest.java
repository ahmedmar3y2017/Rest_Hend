import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Entities.User_Entities;

public class testRest {

	public static String Main_Url = "http://localhost:8080/Restfull2/rest";

	public static void main(String[] args) {

		// ************ how to make rest client **************************
		// Login_Client();

		// signUp_CheckClient();

		// User_Entities user_Entities = new User_Entities();
		// user_Entities.setEmail("ahmedmar3y108108@gmail.com");
		// user_Entities.setUsername("ahmed mido");
		// user_Entities.setCity("tanta");
		// user_Entities.setCountry("daldamoun");
		// user_Entities.setPassword("123456");
		// user_Entities.setPhone("01015136837");
		// user_Entities.setStatus(1);
		// user_Entities.setV_code("1111111");
		//
		// signUp_SaveClient(user_Entities);

	}

	public static void signUp_SaveClient(User_Entities user_Entities) {

		// ************* signup save client *********************
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(Main_Url + "/signup/save");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(user_Entities, MediaType.APPLICATION_JSON));

		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
	}

	public static void signUp_CheckClient() {

		// *************************** signup check client ********************
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(Main_Url + "/signup/check").path("ahmedmar3y108108@gmail.com");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();

		User_Entities user_Entities = response.readEntity(User_Entities.class);
		System.out.println(response.getStatus());
		if (user_Entities == null) {

			System.out.println("Error UserName or Password ");
		}

	}

	public static void Login_Client() {

		// ******* client login get **************
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(Main_Url + "/login/get").path("ahmedmar3y108108@gmail.com").path("123456");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();

		User_Entities user_Entities = response.readEntity(User_Entities.class);
		System.out.println(response.getStatus());
		if (user_Entities == null) {

			System.out.println("Error UserName or Password ");
		}

	}

}
