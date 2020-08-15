// testing by amitc
package com.test;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class VehicleTest {

	/* If there is only one class implemetning Vehicle interface then below code work but in case
	 * of more than one class implementing Vehicle interface then it would failed as BeanCreationException
	 * 
	 * 
	 * 	@Autowired
		Vehicle vehicle;
	 * 
	 */
	
	@PostConstruct
	public void init(){
		System.out.println("----INIT method called in VehicleTest class");
	}
	
	
/*	@Autowired
	Vehicle vehicleImpl;
	
	public void callMe(){
		vehicleImpl.meth();
	}

	public Vehicle getVehicleImpl() {
		return vehicleImpl;
	}

	public void setVehicleImpl(Vehicle vehicleImpl) {
		this.vehicleImpl = vehicleImpl;
	}*/

	//	Other option as
	
	@Autowired
	@Qualifier("vehicleImpl")
	Vehicle vehicle;

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	public void callMe(){
		vehicle.meth();
	}

	
	
	
	
	
}
