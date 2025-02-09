package myform.entity;

import javax.persistence.Embeddable;

@Embeddable							// Agar One to one hai to bas agar se dusra table banake @OneToOne mapping karne koi zarurat nhi, direct main Student table hi Address ko embedd kar do.
public class Address {				// But if we require to insert multiple Address then we need to do @OneToMany mapping for Address proeprty in Student class, then ye lagake cascase.all karne padega, taaki Address wala table entries bhi Student table me sath me hi auto save hojaye. Dobara se isko save na karna apade.   

	private String street;
	private String city;
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "Address [street=" + street + ", city=" + city + "]";
	}
	
	
	
}
