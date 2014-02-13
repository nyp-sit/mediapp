package signup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.datanucleus.util.Base64;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.Blob;
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

public class StoreImageServlet extends HttpServlet{
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException { 
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		List<String> results = new ArrayList<String>();

		String image= "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";
		//byte[] imageAsBytes = Base64.decode(image, Base64.DEFAULT);	

		byte[] bytes = image.getBytes();
		
		String haha = bytes.toString();
		
		
	byte[] buff = image.getBytes();
		
		MyImage myImage = new MyImage();
		
         myImage.setImageType("image/jpeg");
         myImage.setTitle("testing la");
         myImage.setImage(buff);
		String testing = new String(bytes, "UTF-8");
		
	

		PersistenceManager pm = PMF.get().getPersistenceManager();
		 try {
             // Store the image in App Engine's datastore
             pm.makePersistent(myImage);
         } finally {
             pm.close();
         }		
		resp.getWriter().println(buff);
		resp.getWriter().println(testing);
		
		

		
		
		   
		
		

}
	
	/*public void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException { 
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		List<String> results = new ArrayList<String>();
		String image = "";
		image=req.getParameter("Image");
				//byte[] imageAsBytes = Base64.decode(image, Base64.DEFAULT);	
String name = req.getParameter("Name");
String nric = req.getParameter("NRIC");
		byte[] bytes = image.getBytes();
		String haha = bytes.toString();
	byte[] buff = image.getBytes();
		
		MyImage myImage = new MyImage();
		
         myImage.setImageType("image/jpeg");
         myImage.setTitle(name+nric);
         myImage.setImage(buff);
		String testing = new String(bytes, "UTF-8");
		
	

		PersistenceManager pm = PMF.get().getPersistenceManager();
		 try {
             // Store the image in App Engine's datastore
             pm.makePersistent(myImage);
         } finally {
             pm.close();
         }		
		resp.getWriter().println(buff);
		resp.getWriter().println(testing);
		
		

		
		
		   
		
		

}*/
	public void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException { 
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		List<String> results = new ArrayList<String>();
		String userName = req.getParameter("NRIC");
		String email = req.getParameter("Email");
		String phone = req.getParameter("Phone");
		String Name= req.getParameter("Name");
		String image= req.getParameter("Image");
		byte[] bytes = image.getBytes();
		Blob pic;
		pic = new Blob(bytes);
		
		
		Filter Nric = new FilterPredicate("Nric",FilterOperator.EQUAL,userName);
		Transaction txn = datastore.beginTransaction();
		
		try{
			Key nric = KeyFactory.createKey("User", userName);
			Entity User = datastore.get(nric);
			User.setProperty("Email", email);
			User.setProperty("Phone", phone);
			User.setProperty("Name", Name);
			User.setProperty("Image",pic);
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
