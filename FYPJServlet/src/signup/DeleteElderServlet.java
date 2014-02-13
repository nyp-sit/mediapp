package signup;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

public class DeleteElderServlet extends HttpServlet {

	
	 public void doPost(HttpServletRequest req, HttpServletResponse resp) 
	            throws IOException { 
DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	List<String> results = new ArrayList<String>();
	String userName = req.getParameter("NRIC");
	String elderName = req.getParameter("elderName");
	String retrievedNric = null;
	String retrievedName,retrievedRemarks,retrievedImage,retrievedDosage,retrievedDate;
	String xPin = "qwe";
	double i = 0;
	String[] arrayNames = null ;
	Filter Nric = new FilterPredicate("cgNRIC",FilterOperator.EQUAL,userName);
	Filter NAME = new FilterPredicate("Name",FilterOperator.EQUAL,elderName);
	
	Filter success =CompositeFilterOperator.and(NAME,Nric);
	Query q = new Query("Elder").setFilter(success);//.addSort("MEDDATE", SortDirection.ASCENDING);
	
	PreparedQuery pq = datastore.prepare(q);
	results.add(Double.toString(i));
	Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));
	Key keyforuser,keyforuser1,keyforuser2,keyforuser3;
	Date currentLocalTime = cal.getTime();
	 DateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss z");   
	 date.setTimeZone(TimeZone.getTimeZone("GMT+8")); 
	 
	 String localTime = date.format(currentLocalTime); 
	Date d1 = null;
	Date d2 = null;
	int ii = 1;
	
	for (Entity result : pq.asIterable()) {
		
		keyforuser = result.getKey();
		retrievedNric= result.getProperty("Nric").toString();
		datastore.delete(keyforuser);
	}
	
	Filter Nric1 = new FilterPredicate("ELDERNRIC",FilterOperator.EQUAL,retrievedNric);
	Filter NAME1 = new FilterPredicate("ELDERNAME",FilterOperator.EQUAL,elderName);
	Filter success2 =CompositeFilterOperator.and(NAME1,Nric1);
	Query q1 = new Query("MedicineAlarm").setFilter(success2);
	PreparedQuery pq1 = datastore.prepare(q1);
for (Entity result : pq1.asIterable()) {
		
		keyforuser1 = result.getKey();
		
		datastore.delete(keyforuser1);
	}


Filter Nric2 = new FilterPredicate("ELDERNRIC",FilterOperator.EQUAL,retrievedNric);
Filter NAME2 = new FilterPredicate("ELDERNAME",FilterOperator.EQUAL,elderName);
Filter success3 =CompositeFilterOperator.and(NAME2,Nric2);
Query q2 = new Query("Medicine").setFilter(success3);
PreparedQuery pq2 = datastore.prepare(q2);
for (Entity result : pq2.asIterable()) {
	
	keyforuser2 = result.getKey();
	
	datastore.delete(keyforuser2);
}


Filter Nric3 = new FilterPredicate("ELDERNRIC",FilterOperator.EQUAL,retrievedNric);
Filter NAME3 = new FilterPredicate("ELDERNAME",FilterOperator.EQUAL,elderName);
Filter success4 =CompositeFilterOperator.and(NAME3,Nric3);
Query q3 = new Query("MedicineHistory").setFilter(success4);
PreparedQuery pq3 = datastore.prepare(q3);
for (Entity result : pq1.asIterable()) {
	
	keyforuser3 = result.getKey();
	
	datastore.delete(keyforuser3);
}





	
	 }
}
