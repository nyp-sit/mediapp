package signup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.gson.Gson;

public class RetrieveAlarmServlet extends HttpServlet { 
	
	 public void doGet(HttpServletRequest req, HttpServletResponse resp) 
	            throws IOException { 
		 
	        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			List<String> results = new ArrayList<String>();
			String usernric = "111";//req.getParameter("NRIC");
			String elderName = "111";//req.getParameter("elderName");
			
			double i = 0;
			String[] arrayNames = null ;
			Filter Nric = new FilterPredicate("ELDERNRIC",FilterOperator.EQUAL,usernric);
			Filter NAME = new FilterPredicate("ELDERNAME",FilterOperator.EQUAL,elderName);
			Filter success =CompositeFilterOperator.and(NAME, Nric);
			Query q = new Query("MedicineAlarm").setFilter(success); //.addSort("MEDDATE", SortDirection.DESCENDING);
			PreparedQuery pq = datastore.prepare(q);
			results.add(Double.toString(i));
			for (Entity result : pq.asIterable()) {
				i++;
				results.set(0, Double.toString(i));
				
				results.add((String) result.getProperty("MEDNAME").toString());
				results.add((String) result.getProperty("MEDREMARKS").toString());
				results.add((String) result.getProperty("MEDIMAGE").toString());
				results.add((String) result.getProperty("DOSAGE").toString());
				results.add((String) result.getProperty("MEDDATE").toString());
				results.add((String) result.getProperty("DAY").toString());
				results.add((String) result.getProperty("MONTH").toString());
				results.add((String) result.getProperty("YEAR").toString());
				results.add((String) result.getProperty("MINUTE").toString());
				results.add((String) result.getProperty("HOUR").toString());

				  }
			
			String json = new Gson().toJson(results);
			resp.setContentType("application/json");
			//resp.setCharacterEncoding("UTF-8");
			resp.getWriter().println(json);
			 // response.getOutputStream().println(i);
		 
		 
		 
	 }
	 public void doPost(HttpServletRequest req, HttpServletResponse resp) 
	            throws IOException { 
		 

	        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			List<String> results = new ArrayList<String>();
			String usernric = req.getParameter("NRIC");
			String elderName = req.getParameter("elderName");
			String xPin = "qwe";
			double i = 0;
			String[] arrayNames = null ;
			Filter Nric = new FilterPredicate("ELDERNRIC",FilterOperator.EQUAL,usernric);
			Filter NAME = new FilterPredicate("ELDERNAME",FilterOperator.EQUAL,elderName);
			Filter success =CompositeFilterOperator.and(NAME, Nric);
			Query q = new Query("MedicineAlarm").setFilter(success); //.addSort("MEDDATE", SortDirection.DESCENDING);
			PreparedQuery pq = datastore.prepare(q);
			results.add(Double.toString(i));
			for (Entity result : pq.asIterable()) {
				i++;
				results.set(0, Double.toString(i));
				
				results.add((String) result.getProperty("MEDNAME").toString());
				results.add((String) result.getProperty("MEDREMARKS").toString());
				results.add((String) result.getProperty("MEDIMAGE").toString());
				results.add((String) result.getProperty("DOSAGE").toString());
				results.add((String) result.getProperty("MEDDATE").toString());
				results.add((String) result.getProperty("DAY").toString());
				results.add((String) result.getProperty("MONTH").toString());
				results.add((String) result.getProperty("YEAR").toString());
				results.add((String) result.getProperty("MINUTE").toString());
				results.add((String) result.getProperty("HOUR").toString());
				  
				  }
			
			String json = new Gson().toJson(results);
			resp.setContentType("application/json");
			//resp.setCharacterEncoding("UTF-8");
			resp.getWriter().println(json);
			 // response.getOutputStream().println(i);
		 
		 
		 
		 
		 
	 }
	 
	 

}