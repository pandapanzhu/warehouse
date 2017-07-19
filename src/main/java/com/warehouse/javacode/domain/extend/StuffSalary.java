package com.warehouse.javacode.domain.extend;

import com.warehouse.javacode.domain.Salary;

public class StuffSalary extends Salary{
	private String stuffname;
	private String basesalary;
	private String position;
	
	
	public String getStuffname() {
		return stuffname;
	}

	public void setStuffname(String stuffname) {
		this.stuffname = stuffname;
	}

	public String getBasesalary() {
		return basesalary;
	}

	public void setBasesalary(String basesalary) {
		this.basesalary = basesalary;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	
}
