package signup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Blob;
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

/**
 * GET requests return the promotional image associated with the myImage with the
 * title specified by the title query string parameter.
 */
public class ElderImageServlet extends HttpServlet {

/*    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String title = req.getParameter("title");
        MyImage myImage = getMyImage("charles ngiam123");

        if (myImage != null && myImage.getImageType() != null &&
                myImage.getImage() != null) {
            // Set the appropriate Content-Type header and write the raw bytes
            // to the response's output stream
            //resp.setContentType(myImage.getImageType());
           byte[] haha = myImage.getImage();
           String testing = new String(haha, "UTF-8");
            
           resp.getWriter().println(testing);
            
        } else {
            // If no image is found with the given title, redirect the user to
            // a static image
            resp.sendRedirect("/static/noimage.jpg");
        }
    }
    
    private MyImage getMyImage(String title) {
        PersistenceManager pm = PMF.get().getPersistenceManager();

        // Search for any MyImage object with the passed-in title; limit the number
        // of results returned to 1 since there should be at most one MyImage with
        // a given title
        Query query = pm.newQuery(MyImage.class, "title == titleParam");
        query.declareParameters("String titleParam");
        query.setRange(0, 1);

        try {
            List<MyImage> results = (List<MyImage>) query.execute(title);
            if (results.iterator().hasNext()) {
                // If the results list is non-empty, return the first (and only)
                // result
                return results.get(0);
            }
        } finally {
            query.closeAll();
            pm.close();
        }

        return null;
    }*/

    
    public void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException { 
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		List<String> results = new ArrayList<String>();
		String userName = req.getParameter("NRIC");
		String nameN = req.getParameter("Name");
		String retrievedNRIC="";
		String retrievedPass="";
		String retrievedName="";
		String retrievedPhone="";
		String retrievedEmail="";
		String noSuchAccount = "No such Account";
		String xPin = "qwe";
		Blob pic = null;
		
		
		Filter name = new FilterPredicate("Name", FilterOperator.EQUAL, nameN);
		Filter Nric = new FilterPredicate("Nric",FilterOperator.EQUAL,userName);
		Filter success =CompositeFilterOperator.and (Nric,name);
		Query q = new Query("Elder").setFilter(success);
		PreparedQuery pq = datastore.prepare(q);
		String testing = null ;
		for (Entity result : pq.asIterable()) {
		
			  pic = (Blob)result.getProperty("Image");
		
		}
		
			
			
	
			if (pic != null ) {
	            // Set the appropriate Content-Type header and write the raw bytes
	            // to the response's output stream
	            //resp.setContentType(myImage.getImageType());
	           byte[] haha = pic.getBytes();
	           testing= new String(haha, "UTF-8");
	           results.add(testing);
	           String json = new Gson().toJson(results);
				resp.setContentType("application/json");
				//resp.setCharacterEncoding("UTF-8");
				resp.getWriter().println(json);
			}
	           
			
	           
			else{
			
			results.add("Nothing");	
			String json = new Gson().toJson(results);
			resp.setContentType("application/json");
			//resp.setCharacterEncoding("UTF-8");
			resp.getWriter().println(json);
			}
			
		
			
			
			
			
		}
		
			
		
		
    
    
    
    
}
    //...