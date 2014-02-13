package signup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.gson.Gson;



public class EmergencyInfoservlet extends HttpServlet { 
 
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
	        response.getOutputStream().println("This is SPARTA!");
	 
	    }
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException { 
		List<String> results = new ArrayList<String>();
	String Name = req.getParameter("Name");
	String nric = req.getParameter("NRIC");
	String EName = req.getParameter("EName");
	String Ephone = req.getParameter("EPhone");
	String EAddress = req.getParameter("EAddress");
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	Filter Nric = new FilterPredicate("Nric",FilterOperator.EQUAL,nric);
	Transaction txn = datastore.beginTransaction();
	try{
		Key elder = KeyFactory.createKey("Elder", nric);
		Entity User = datastore.get(elder);
		User.setProperty("Ename", EName);
		User.setProperty("EPhone", Ephone);
		User.setProperty("EAddress", EAddress);
		 datastore.put(User);
		 txn.commit();	} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	finally {
	    if (txn.isActive()) {
	        txn.rollback();
	    }
	
	

}

	
	}
}
	

