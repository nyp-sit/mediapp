package signup;

import java.util.Date;

import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.Key; 
@Entity
public class UserEntity { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key key; 
    @PrimaryKey
	private String NRIC; 
    private String pin; 
    private String name; 
    private String phone;
    @Persistent
    Blob image;
    public Key getKey() {
		return key;
	}
	public UserEntity(String NRIC, String pin, String name, String phone,Blob image) {
		super();
		this.NRIC = NRIC;
		this.pin = pin;
		this.name = name;
		this.phone = phone;
	}
	public void setKey(Key key) {
		this.key = key;
	}
	public String getNRIC() {
		return NRIC;
	}
	public void setNRIC(String NRIC) {
		this.NRIC = NRIC;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Blob getImage()              { return image; }
    public void setImage(Blob image)    { this.image = image; }

 
}
