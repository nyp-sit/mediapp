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
import com.google.gson.Gson;

public class AddMedicineServlet extends HttpServlet { 
	
	
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException { 
		List<String> results = new ArrayList<String>();
	String userName = req.getParameter("nric");
	Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
	 
	Date currentLocalTime = cal.getTime();
	 DateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss z");   
	 date.setTimeZone(TimeZone.getTimeZone("GMT+8")); 
	 String localTime = date.format(currentLocalTime); 
	String name = req.getParameter("name");
	String medName = req.getParameter("medName");
	String medRemarks = req.getParameter("medRemarks");
	String medImage = req.getParameter("medImage");
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	Entity Medicine = new Entity("Medicine");
	
	Medicine.setProperty("ELDERNRIC",userName);
	Medicine.setProperty("ELDERNAME",name);
	Medicine.setProperty("MEDNAME",medName);
	Medicine.setProperty("MEDREMARKS",medRemarks);
	Medicine.setProperty("MEDIMAGE",medImage);
	Medicine.setProperty("MEDDATE",localTime);
	datastore.put(Medicine);
	results.add("Success");
	results.add("Success");
	String json = new Gson().toJson(results);
	resp.setContentType("application/json");
	//resp.setCharacterEncoding("UTF-8");
	resp.getWriter().println(json);
	
	
}
	
	}
	

