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
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.gson.Gson;

public class AddAlarmServlet extends HttpServlet { 
	
	
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException { 
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		List<String> results = new ArrayList<String>();
	
		
	String userName = req.getParameter("nric");
	Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
	 
	Date currentLocalTime = cal.getTime();
	 DateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss z");   
	 date.setTimeZone(TimeZone.getTimeZone("GMT+8")); 
	 String localTime = date.format(currentLocalTime); 
	 
	 String nric = req.getParameter("nric");
	String name = req.getParameter("name");
	String medName = req.getParameter("medName");
	String dosage = req.getParameter("dosage");
	String year =req.getParameter("year");
String month = req.getParameter("month");
String day = req.getParameter("day");
String hour = req.getParameter("hour");
String min = req.getParameter("min");
String datesummary = year+"/"+month+"/"+day+" "+ hour+":"+min+":00 GMT+8:00";
String retrievedimage = null;
String retrievedremarks = null;

Filter Nric = new FilterPredicate("ELDERNRIC",FilterOperator.EQUAL,nric);
Filter elderName1 = new FilterPredicate("ELDERNAME",FilterOperator.EQUAL,name);
Filter medname = new FilterPredicate("MEDNAME",FilterOperator.EQUAL,medName);
Filter success =CompositeFilterOperator.and(elderName1, Nric);
Query q = new Query("Medicine").setFilter(success);
PreparedQuery pq = datastore.prepare(q);

for (Entity result : pq.asIterable()) {
	
	retrievedimage=result.getProperty("MEDIMAGE").toString();
	retrievedremarks = result.getProperty("MEDREMARKS").toString();
}








	
	
	Entity Medicine = new Entity("MedicineAlarm");
	
	Medicine.setProperty("ELDERNRIC",userName);
	Medicine.setProperty("ELDERNAME",name);
	Medicine.setProperty("MEDNAME",medName);
	Medicine.setProperty("MEDREMARKS",retrievedremarks);
	Medicine.setProperty("MEDIMAGE",retrievedimage);
	Medicine.setProperty("MEDDATE",datesummary);
	Medicine.setProperty("YEAR",year);
	Medicine.setProperty("MONTH",month);
	Medicine.setProperty("DAY",day);
	Medicine.setProperty("HOUR",hour);
	Medicine.setProperty("MINUTE",min);
	Medicine.setProperty("DOSAGE",dosage);
	
	
	datastore.put(Medicine);
	
	String json = new Gson().toJson(results);
	resp.setContentType("application/json");
	//resp.setCharacterEncoding("UTF-8");
	resp.getWriter().println(json);
	
	
}
	}
	
	
	