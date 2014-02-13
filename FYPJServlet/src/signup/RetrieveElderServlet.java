package signup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.gson.Gson;
public class RetrieveElderServlet  extends HttpServlet { 
	 
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
	        response.getOutputStream().println("faggot servlet");
	        
	        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			List<String> results = new ArrayList<String>();
			String userName = "pokemon";
			String pin = "123456";
			String retrievedNRIC;
			String retrievedPass="";
			String retrievedName = "";
			String retrievedPhone;
			String noSuchAccount = "No such Account";
			String xPin = "qwe";
			int i = 0;
			String[] arrayNames = null ;
			Filter Nric = new FilterPredicate("cgNRIC",FilterOperator.EQUAL,userName);
			//Filter success =CompositeFilterOperator.and(password, Nric);
			Query q = new Query("Elder").setFilter(Nric);
			PreparedQuery pq = datastore.prepare(q);
			
			for (Entity result : pq.asIterable()) {
					
				//arrayNames[i] = (String) result.getProperty("Name").toString();
				 // retrievedNRIC = (String) result.getProperty("Nric").toString();
				 // retrievedPhone=(String)result.getProperty("Phone").toString();
				  //response.getOutputStream().println(retrievedNRIC + retrievedName);
				results.add((String) result.getProperty("Name").toString());
				String json = new Gson().toJson(results);
				response.setContentType("application/json");
				//resp.setCharacterEncoding("UTF-8");
				response.getWriter().println(json);
				 // response.getOutputStream().println(i);
				  i++;
				  }

			//response.getOutputStream().println(retrievedPass + retrievedName);
	 
	    }
	 
	 
	 public void doPost(HttpServletRequest req, HttpServletResponse resp) 
	            throws IOException { 
		 
	        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			List<String> results = new ArrayList<String>();
			String userName = req.getParameter("NRIC");
			
			String retrievedNRIC;
			String retrievedPass="";
			String retrievedName = "";
			String retrievedPhone;
			String noSuchAccount = "No such Account";
			String xPin = "qwe";
			int i = 0;
			String[] arrayNames = null ;
			Filter Nric = new FilterPredicate("cgNRIC",FilterOperator.EQUAL,userName);
			//Filter success =CompositeFilterOperator.and(password, Nric);
			Query q = new Query("Elder").setFilter(Nric);
			PreparedQuery pq = datastore.prepare(q);
Blob pic = null;String testing = null;
			for (Entity result : pq.asIterable()) {
					
				//arrayNames[i] = (String) result.getProperty("Name").toString();
				 // retrievedNRIC = (String) result.getProperty("Nric").toString();
				 // retrievedPhone=(String)result.getProperty("Phone").toString();
				  //response.getOutputStream().println(retrievedNRIC + retrievedName);
				results.add((String) result.getProperty("Name").toString());
				  pic = (Blob)result.getProperty("Image");
				  byte[] haha = pic.getBytes();
		           testing= new String(haha, "UTF-8");
		           results.add(testing);
					
				  i++;
				  }
			
			String json = new Gson().toJson(results);
			resp.setContentType("application/json");
			//resp.setCharacterEncoding("UTF-8");
			resp.getWriter().println(json);
			 // response.getOutputStream().println(i);
		 
		 
		 
	 }
	 
	 
}