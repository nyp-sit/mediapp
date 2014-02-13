package signup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.gson.Gson;



public class SuperTestServletServlet extends HttpServlet { 
 
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
	        response.getOutputStream().println("faggot servlet");
	 
	    }
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException { 
		List<String> results = new ArrayList<String>();
	String userName = req.getParameter("nric");
	String pin = req.getParameter("pin");
	String name = req.getParameter("name");
	String phone = req.getParameter("phone");
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	Key nric = KeyFactory.createKey("User", userName);
	try {
		String noSuchAccount = "Account Already Exist";
		Entity user = datastore.get(nric);
		results.add(noSuchAccount);
		
		String json = new Gson().toJson(results);
		resp.setContentType("application/json");
		//resp.setCharacterEncoding("UTF-8");
		resp.getWriter().println(json);
	
		
	}
	catch(EntityNotFoundException e){
		Entity user = new Entity("User",userName);
		user.setProperty("Nric", userName);
		user.setProperty("Pin", pin);
		user.setProperty("Name",name );
		user.setProperty("Phone",phone);
		user.setProperty("Email", "-");
		datastore.put(user);
		results.add("Success");
		results.add("Success");
		String json = new Gson().toJson(results);
		resp.setContentType("application/json");
		//resp.setCharacterEncoding("UTF-8");
		resp.getWriter().println(json);
		
		
	}
	
	
	
	
	
	
	
   

//	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
//    Date date = new Date(); 
//    UserEntity user = new UserEntity(userName,pin,name,phone);
//
//    EntityManager em = EMF.get().createEntityManager(); 
//    try { 
//        em.persist(user); 
//    } finally { 
//        em.close(); 
//    } 
//   resp.getOutputStream().println("Hurray !! This Servlet Works");
//    OutputStreamWriter writer = new OutputStreamWriter(resp.getOutputStream());
//    writer.write("created " + user);
//    writer.flush();
//    writer.close();
} 
}

	
	
	

