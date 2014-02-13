package signup;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.gson.Gson;

public class MedAckServlet extends HttpServlet {
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
			DateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss z");   
			 date.setTimeZone(TimeZone.getTimeZone("GMT+8")); 
			Date d2 = null;
Key keytodelete;
			Query q = new Query("MedicineAlarm").setFilter(success5); //.addSort("MEDDATE", SortDirection.DESCENDING);
			PreparedQuery pq = datastore.prepare(q);
			String retrievedName,retrievedRemarks,retrievedImage,retrievedDosage,retrievedDate;
			for (Entity result : pq.asIterable()) {
				
				
				retrievedName= result.getProperty("MEDNAME").toString();
				 retrievedRemarks=result.getProperty("MEDREMARKS").toString();
				retrievedImage=result.getProperty("MEDIMAGE").toString();
				retrievedDosage = result.getProperty("DOSAGE").toString();
				retrievedDate=result.getProperty("MEDDATE").toString();

				try {
					d2 = date.parse(retrievedDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				keytodelete = result.getKey();
				datastore.delete(keytodelete);
				
				Entity MedicineHistory = new Entity("MedicineHistory");
				MedicineHistory.setProperty("ELDERNRIC",elderNric);
				MedicineHistory.setProperty("ELDERNAME",elderName);
				MedicineHistory.setProperty("MEDNAME",retrievedName);
				MedicineHistory.setProperty("MEDDOSAGE",retrievedDosage);
				MedicineHistory.setProperty("MEDIMAGE",retrievedImage);
				MedicineHistory.setProperty("MEDDATE",retrievedDate);
				MedicineHistory.setProperty("MEDSTATUS","Taken");
				MedicineHistory.setProperty("CREATEDATE",d2);
				datastore.put(MedicineHistory);
				
				  
				  }
			
			//String json = new Gson().toJson(results);
			//resp.setContentType("application/json");
			//resp.setCharacterEncoding("UTF-8");
			//resp.getWriter().println(json);
			 // response.getOutputStream().println(i);
		 
		 
		 
		 
		 
	 }
}
