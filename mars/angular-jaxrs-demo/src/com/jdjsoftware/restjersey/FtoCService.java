package com.jdjsoftware.restjersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

import com.jdjsoftware.dao.*;
import java.sql.*;

@Path("/ftocservice")
public class FtoCService {
 
	  @GET
	  @Produces("application/json")
	  public Response convertFtoC() throws JSONException {
 
		JSONObject jsonObject = new JSONObject();
		Double fahrenheit = 98.24;
		Double celsius;
		celsius = (fahrenheit - 32)*5/9; 
		jsonObject.put("fahrenheit", fahrenheit); 
		jsonObject.put("celsius", celsius);
		//"@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n"
		String result = jsonObject.toString();
		return Response.status(200).entity(result).build();
		
	  }
 
	  @Path("{f}")
	  @GET
	  @Produces("application/json")
	  public Response convertFtoCfromInput(@PathParam("f") float f) throws JSONException {
 
		JSONObject jsonObject = new JSONObject();
		float celsius =  (f - 32)*5/9; 
		jsonObject.put("fahrenheit", f); 
		jsonObject.put("celsius", celsius);
		//"@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + 
		String result = jsonObject.toString();
		return Response.status(200).entity(result).build();
	  }
}