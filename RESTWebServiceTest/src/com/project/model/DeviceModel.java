package com.project.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class DeviceModel {
	
	private String id;
	private String temperature;
	private String date;
	
	public DeviceModel() {
		super();
	}
	
	public DeviceModel(String id, String temperature, String date) {
		super();
		this.id = id;
		this.temperature = temperature;
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String today) {
		this.date = today;
	}
}
