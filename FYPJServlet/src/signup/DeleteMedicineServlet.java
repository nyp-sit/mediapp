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
import com.google.gson.Gson;

public class DeleteMedicineServlet extends HttpServlet {

	
	 public void doPost(HttpServletRequest req, HttpServletResponse resp) 
	            throws IOException { 
DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	List<String> results = new ArrayList<String>();
	String usernric = req.getParameter("NRIC");
	String elderName = req.getParameter("elderName");
	String medName = req.getParameter("medName");
	String medRemarks = req.getParameter("medRemarks");
	String retrievedName,retrievedRemarks,retrievedImage,retrievedDosage,retrievedDate;
	String xPin = "qwe";
	double i = 0;
	String[] arrayNames = null ;
	Filter Nric = new FilterPredicate("ELDERNRIC",FilterOperator.EQUAL,usernric);
	Filter NAME = new FilterPredicate("ELDERNAME",FilterOperator.EQUAL,elderName);
	Filter REMARKS = new FilterPredicate("MEDREMARKS",FilterOperator.EQUAL,medRemarks);
	Filter MEDNAME = new FilterPredicate("MEDNAME",FilterOperator.EQUAL,medName);
	Filter success =CompositeFilterOperator.and(NAME,Nric,REMARKS,MEDNAME);
	Query q = new Query("Medicine").setFilter(success);//.addSort("MEDDATE", SortDirection.ASCENDING);
	PreparedQuery pq = datastore.prepare(q);
	results.add(Double.toString(i));
	Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));
	Key keyforuser;
	Date currentLocalTime = cal.getTime();
	 DateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss z");   
	 date.setTimeZone(TimeZone.getTimeZone("GMT+8")); 
	 
	 String localTime = date.format(currentLocalTime); 
	Date d1 = null;
	Date d2 = null;
	int ii = 1;
	
	for (Entity result : pq.asIterable()) {
		
		keyforuser = result.getKey();
		datastore.delete(keyforuser);
	}
	 }

}
