package myservlets;

import java.io.IOException;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {
	public void doGe(HttpServletRequest request, HttpServletResponse response) throws IOException{
				PrintWriter out = response.getWriter();
				out.println("<html>");
				out.println("<body>");
				out.println("<h1>Hello Servlet is runninig...</h1>");
				out.println("</body>");
				out.println("</html>");	
	}
	
	private String message;

	public void init() throws ServletException {
      // Do required initialization
	  message = "Hello World";
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	  // Set response content type
	  response.setContentType("text/html");
	  
	  String userAgent = request.getHeader("User-Agent");
	
	  // Actual logic goes here.
	  PrintWriter out = response.getWriter();
	  out.println("<h1>" + message + "</h1>");
	  out.println("<h2>" + userAgent + "</h2>");
	}
	  
	public void destroy(){
	      // do nothing.
	}
}
