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

@Path("/validateuser")
public class DatabaseService {
 
	@Path("{u}{p}")
	@GET
	@Produces("application/text")
	public boolean convertCtoFfromInput(@PathParam("u") String u, @PathParam("p") String p) {
		
		String sql = "SELECT email, firstname, lastname, lastmod_ts FROM Users where email='" + u + "' and password_hash ='"+p+"';";
		return DatabaseHelper.validateUser(sql);
	}
	
}