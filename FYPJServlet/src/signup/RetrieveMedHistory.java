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
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.gson.Gson;

public class RetrieveMedHistory extends HttpServlet { 
	
	 public void doGet(HttpServletRequest req, HttpServletResponse resp) 
	            throws IOException { 
		 
	        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			List<String> results = new ArrayList<String>();
			String usernric = "111";//req.getParameter("NRIC");
			String elderName = "111";//req.getParameter("elderName");
			String xPin = "qwe";
			double i = 0;
			String[] arrayNames = null ;
			Filter Nric = new FilterPredicate("ELDERNRIC",FilterOperator.EQUAL,usernric);
			Filter NAME = new FilterPredicate("ELDERNAME",FilterOperator.EQUAL,elderName);
			Filter date = new FilterPredicate("CREATEDATE",FilterOperator.NOT_EQUAL,"1");
			Filter success =CompositeFilterOperator.and(NAME, Nric);
			Filter success2 =CompositeFilterOperator.and(success, date);
			Query q = new Query("MedicineHistory").setFilter(success2).addSort("CREATEDATE", SortDirection.DESCENDING);
			PreparedQuery pq = datastore.prepare(q);
			results.add(Double.toString(i));
			for (Entity result : pq.asIterable()) {
				i++;
				results.set(0, Double.toString(i));
				
				results.add((String) result.getProperty("MEDNAME").toString());
				results.add((String) result.getProperty("MEDSTATUS").toString());
				results.add((String) result.getProperty("MEDIMAGE").toString());
				results.add((String) result.getProperty("MEDDOSAGE").toString());
				results.add((String) result.getProperty("MEDDATE").toString());
				  
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
			//Filter DATE = new FilterPredicate("MEDDATE",FilterOperator.NOT_EQUAL,xPin);
			Filter success =CompositeFilterOperator.and(NAME,Nric);
			Query q = new Query("MedicineHistory").setFilter(success);//addSort("CREATEDATE",SortDirection.DESCENDING);
			PreparedQuery pq = datastore.prepare(q);
			results.add(Double.toString(i));
			for (Entity result : pq.asIterable()) {
				i++;
				results.set(0, Double.toString(i));
				
				results.add((String) result.getProperty("MEDNAME").toString());
				results.add((String) result.getProperty("MEDSTATUS").toString());
				results.add((String) result.getProperty("MEDIMAGE").toString());
				results.add((String) result.getProperty("MEDDOSAGE").toString());
				results.add((String) result.getProperty("MEDDATE").toString());
				  
				  }
			
			String json = new Gson().toJson(results);
			resp.setContentType("application/json");
			//resp.setCharacterEncoding("UTF-8");
			resp.getWriter().println(json);
			 // response.getOutputStream().println(i);
		 
		 
		 
		 
		 
	 }
	 
	 

}
