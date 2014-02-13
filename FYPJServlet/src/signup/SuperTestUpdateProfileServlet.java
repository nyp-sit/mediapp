package signup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Transaction;


public class SuperTestUpdateProfileServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException { 
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		List<String> results = new ArrayList<String>();
		String userName = req.getParameter("NRIC");
		String email = req.getParameter("Email");
		String phone = req.getParameter("Phone");
		String Name= req.getParameter("Name");
	
		Key keyforuser;
		Filter Nric = new FilterPredicate("Nric",FilterOperator.EQUAL,userName);
		Transaction txn = datastore.beginTransaction();
		
		try{
			Key nric = KeyFactory.createKey("User", userName);
			Entity User = datastore.get(nric);
			User.setProperty("Email", email);
			User.setProperty("Phone", phone);
			User.setProperty("Name", Name);
			
			 datastore.put(User);
			 txn.commit();	
			 
			 
				Filter NAME1 = new FilterPredicate("cgNRIC",FilterOperator.EQUAL,userName);
				Query q = new Query("Elder").setFilter(NAME1);
				PreparedQuery pq = datastore.prepare(q);
				for (Entity result : pq.asIterable()) {
					
					keyforuser = result.getKey();
					Entity Elder = datastore.get(keyforuser);
					//Elder.setProperty("caregivername", Name);
					Elder.setProperty("cgName", Name);
					Elder.setProperty("cgEmail", email);
					Elder.setProperty("cgPhone", phone);
					datastore.put(Elder);
					  }
		
				
		
				
				
		
				
		
		
		} catch (EntityNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		finally {
		    if (txn.isActive()) {
		        txn.rollback();
		    }
		
		

}
	}}