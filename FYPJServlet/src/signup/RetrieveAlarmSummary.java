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
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.gson.Gson;

public class RetrieveAlarmSummary extends HttpServlet {

		
	
	
	 public void doPost(HttpServletRequest req, HttpServletResponse resp) 
	            throws IOException { 
   DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	List<String> results = new ArrayList<String>();
	String usernric = req.getParameter("NRIC");
	String elderName = req.getParameter("elderName");
	String retrievedName,retrievedRemarks,retrievedImage,retrievedDosage,retrievedDate;
	String xPin = "qwe";
	double i = 0;
	String[] arrayNames = null ;
	Filter Nric = new FilterPredicate("ELDERNRIC",FilterOperator.EQUAL,usernric);
	Filter NAME = new FilterPredicate("ELDERNAME",FilterOperator.EQUAL,elderName);
	Filter success =CompositeFilterOperator.and(NAME, Nric);
	Query q = new Query("MedicineAlarm").setFilter(success);//.addSort("MEDDATE", SortDirection.ASCENDING);
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
		
		
		results.set(0, Double.toString(i));
		keyforuser = result.getKey();
		retrievedName= result.getProperty("MEDNAME").toString();
		 retrievedRemarks=result.getProperty("MEDREMARKS").toString();
		retrievedImage=result.getProperty("MEDIMAGE").toString();
		retrievedDosage = result.getProperty("DOSAGE").toString();
		retrievedDate=result.getProperty("MEDDATE").toString();
		try {
			d1 = date.parse(localTime);
			d2 = date.parse(retrievedDate);

			//in milliseconds
			long diff = d2.getTime() - d1.getTime();
			
			long diffMinutes = diff / (60 * 1000) ;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);
			
			if(diffMinutes < -1 ){
				
				Entity MedicineHistory = new Entity("MedicineHistory");
				MedicineHistory.setProperty("ELDERNRIC",usernric);
				MedicineHistory.setProperty("ELDERNAME",elderName);
				MedicineHistory.setProperty("MEDNAME",retrievedName);
				MedicineHistory.setProperty("MEDDOSAGE",retrievedDosage);
				MedicineHistory.setProperty("MEDIMAGE",retrievedImage);
				MedicineHistory.setProperty("MEDDATE",retrievedDate);
				MedicineHistory.setProperty("MEDSTATUS","Missed");
				MedicineHistory.setProperty("CREATEDATE",d2);
				datastore.put(MedicineHistory);
				
				
				datastore.delete(keyforuser);
				
					
			}else{
				
				i++;
				results.set(0, Double.toString(i));
				results.add(retrievedName);
				results.add(retrievedRemarks);
				results.add(retrievedImage);
				results.add(retrievedDosage);
				results.add(retrievedDate);
				
				
				
				
				
				
				
			}
			
			
		}catch(Exception e){
			
			
		}
		
		
		//results.add();
		  }
	
	String json = new Gson().toJson(results);
	resp.setContentType("application/json");
	//resp.setCharacterEncoding("UTF-8");
	resp.getWriter().println(json);
	 // response.getOutputStream().println(i);





}
}