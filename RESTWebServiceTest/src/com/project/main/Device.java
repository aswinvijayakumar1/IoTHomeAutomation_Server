package com.project.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.JSONObject;

import com.project.model.DeviceModel;

@Path("device")
public class Device {

	DeviceModel device;
	
	public JSONObject getJsonFromUrl() {
		JSONObject json = null;
		try { 
            URL url = new URL("http://Default-Environment-qnbzq3unpn.elasticbeanstalk.com/1/getTemp"); 
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true); 
            connection.setInstanceFollowRedirects(false); 
            connection.setRequestMethod("GET"); 
            connection.setRequestProperty("Content-Type", "application/json");

            //input = connection.getInputStream();
            
            String line;
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while((line = reader.readLine()) != null) {
             builder.append(line);
            }

            json = new JSONObject(builder.toString());
            connection.disconnect(); 
        } catch(Exception e) { 
        	System.out.println("Exception in getJsonFromUrl" + e);
        }
		
		return json;
	}

	public DeviceModel readFrom(JSONObject json) {
		device = new DeviceModel();
		try {
				device.setId(json.get("devID").toString());
				device.setTemperature(json.get("temperature").toString());
				Date date = new Date();
				device.setDate((new Timestamp(date.getTime())).toString());
			} catch (Exception e) {
				System.out.println("Exception in readFrom" + e);
		}
		return device;
	}

	/*
	public DeviceModel readFrom(InputStream in) {
		device = new DeviceModel();
		JsonParser parser = null;
		try {
				parser = Json.createParser(in);
				while (parser.hasNext()) {
				switch (parser.next()) {
					case KEY_NAME:
						String key = parser.getString();
						parser.next();
						switch (key) {
							case "id":
								device.setId(parser.getString());
								break;
							case "temperature":
								device.setTemperature(parser.getString());
								break;
							case "today":
								Date date = new Date();
								device.setDate((new Timestamp(date.getTime())).toString());
								break;
							default:
								break;
						}
						break;
					default:
						break;
				}
			}
		} catch (Exception e) {
			System.out.println("Exception in readFrom" + e);
		}
		parser.close();
		return device;
	}
*/	
	@GET
	@Path("test")
	public String getDeviceDetails1() {
	    return "test";
	}

	@GET
	@Path("getTemp")
	@Produces("application/json")
	public DeviceModel getDeviceDetails() {
		JSONObject json = getJsonFromUrl();
		DeviceModel device = readFrom(json);
	    return device;
	}
}