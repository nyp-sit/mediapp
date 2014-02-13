package signup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

public class RetrieveMedAck extends HttpServlet {
	
	
	 public void doGet(HttpServletRequest req, HttpServletResponse resp) 
	            throws IOException { 
		 

	        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			List<String> results = new ArrayList<String>();
			String day = "27";
			String month = "01";
			String year = "2014";
			String hour = "15";
			String min  = "00";
			String elderName = "111";
			String elderNric = "111";
			String xPin = "qwe";
			double i = 0;
			String[] arrayNames = null ;
			Filter DAY = new FilterPredicate("DAY",FilterOperator.EQUAL,day);
			Filter MONTH = new FilterPredicate("MONTH",FilterOperator.EQUAL,month);
			Filter YEAR = new FilterPredicate("YEAR",FilterOperator.EQUAL,year);
			Filter HOUR = new FilterPredicate("HOUR",FilterOperator.EQUAL,hour);
			Filter MIN = new FilterPredicate("MINUTE",FilterOperator.EQUAL,min);
			Filter Nric = new FilterPredicate("ELDERNRIC",FilterOperator.EQUAL,"111");
			Filter NAME = new FilterPredicate("ELDERNAME",FilterOperator.EQUAL,"111");
			Filter success = CompositeFilterOperator.and(FilterOperator.EQUAL.of("YEAR", year),CompositeFilterOperator.and(FilterOperator.EQUAL.of("MONTH", month),FilterOperator.EQUAL.of("DAY", day)));
			Filter success2 =CompositeFilterOperator.and(HOUR, MIN);
			Filter success3 =CompositeFilterOperator.and(Nric, NAME);
			Filter success4 =CompositeFilterOperator.and(success, success2);
			Filter success5 =CompositeFilterOperator.and(success3, success4);

			Query q = new Query("MedicineAlarm").setFilter(success5); //.addSort("MEDDATE", SortDirection.DESCENDING);
			PreparedQuery pq = datastore.prepare(q);
			
			for (Entity result : pq.asIterable()) {
				i++;
				
				
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
			String day = req.getParameter("day");
			String month = req.getParameter("month");
			String year = req.getParameter("year");
			String hour = req.getParameter("hour");
			String min  = req.getParameter("min");
			String elderName = req.getParameter("elderName");
			String elderNric = req.getParameter("NRIC");
			String xPin = "qwe";
			double i = 0;
			String[] arrayNames = null ;
			Filter DAY = new FilterPredicate("DAY",FilterOperator.EQUAL,day);
			Filter MONTH = new FilterPredicate("MONTH",FilterOperator.EQUAL,month);
			Filter YEAR = new FilterPredicate("YEAR",FilterOperator.EQUAL,year);
			Filter HOUR = new FilterPredicate("HOUR",FilterOperator.EQUAL,hour);
			Filter MIN = new FilterPredicate("MINUTE",FilterOperator.EQUAL,min);
			Filter Nric = new FilterPredicate("ELDERNRIC",FilterOperator.EQUAL,elderNric);
			Filter NAME = new FilterPredicate("ELDERNAME",FilterOperator.EQUAL,elderName);
			Filter success = CompositeFilterOperator.and(FilterOperator.EQUAL.of("YEAR", year),CompositeFilterOperator.and(FilterOperator.EQUAL.of("MONTH", month),FilterOperator.EQUAL.of("DAY", day)));
			Filter success2 =CompositeFilterOperator.and(HOUR, MIN);
			Filter success3 =CompositeFilterOperator.and(Nric, NAME);
			Filter success4 =CompositeFilterOperator.and(success, success2);
			Filter success5 =CompositeFilterOperator.and(success3, success4);

			Query q = new Query("MedicineAlarm").setFilter(success5); //.addSort("MEDDATE", SortDirection.DESCENDING);
			PreparedQuery pq = datastore.prepare(q);
			
			for (Entity result : pq.asIterable()) {
				
				
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
