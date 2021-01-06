package com.w3big.test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloWorld
 */
@WebServlet(
		urlPatterns = { "/HelloWorld" }, 
		initParams = { 
				@WebInitParam(name = "HelloWorld", value = "")
		})
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String FOLDER_PATH_LEFT = "C:\\";
	private String FOLDER_PATH_RIGHT = "C:\\";
	private File filesLeft = new File(FOLDER_PATH_LEFT);
	private File filesRight = new File(FOLDER_PATH_RIGHT);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWorld() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("restart");
		//set folder path
		FOLDER_PATH_LEFT = request.getParameter("submitLeft_name") == null || request.getParameter("fromWhere") == null ? 
				FOLDER_PATH_LEFT : 
					request.getParameter("submitLeft_name").equals("prev") && request.getParameter("fromWhere").equals("left") ?
							filesLeft.getParent() + "\\" : 
										FOLDER_PATH_LEFT + request.getParameter("submitLeft_name") + "\\";
		
		FOLDER_PATH_RIGHT = request.getParameter("submitRight_name") == null || request.getParameter("fromWhere") == null ? 
				FOLDER_PATH_RIGHT : 
					request.getParameter("submitRight_name").equals("prev") && request.getParameter("fromWhere").equals("right") ?
							filesRight.getParent() + "\\" :
										FOLDER_PATH_RIGHT + request.getParameter("submitRight_name") + "\\";
		
		//get request uri
		String requestUri = request.getRequestURI();
		
		//System.out.println("requestPram:" + request.getParameter("submit_name"));
		//System.out.println("requestUri:" + requestUri);
		
		//find all file in this path folder
		filesLeft =  new File(FOLDER_PATH_LEFT);
		filesRight =  new File(FOLDER_PATH_RIGHT);
		
		//get file list in the folder
		String[] fileListLeft = filesLeft.list();
		String[] fileListRight = filesRight.list();
		
		//forward to jsp
		request.setAttribute("FOLDER_PATH_LEFT", FOLDER_PATH_LEFT);
		request.setAttribute("FOLDER_PATH_RIGHT", FOLDER_PATH_RIGHT);
		request.setAttribute("requestUri", requestUri);
		request.setAttribute("fileListLeft", fileListLeft);
		request.setAttribute("fileListRight", fileListRight);
		//System.out.println(request.getParameter("submitLeft_name")+FOLDER_PATH_LEFT);

		//response.sendRedirect(request.getContextPath() + "/test.jsp");
		request.getRequestDispatcher("/webCommander.jsp").forward(request, response);
		
		//System.out.println(filesLeft.getParent());
		
//		for(String path: paths) {
//			File data = new File(ROOT_PATH+path);
//			String extension = "";
//			System.out.println("name:" + data.getName());
//			int i = data.getName().lastIndexOf('.');
//			if (i > 0) {
//			    extension = data.getName().substring(i+1);
//			}
//			System.out.println("extension:" + extension);
//		}
//		response.setCharacterEncoding("UTF-8");
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
		
//		//HTML header
//		out.println("<!DOCTYPE html>\r\n" + 
//				"<html lang=\"en\">\r\n" + 
//				"<head>\r\n" + 
//				"    <meta charset=\"UTF-8\">\r\n" + 
//				"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
//				"    <title>Document</title>\r\n" );
//		
//		//HTML style
//		out.println("<style>\r\n" + 
//				"      table,\r\n" + 
//				"      th,\r\n" + 
//				"      td {\r\n" + 
//				"        border: 1px solid black;\r\n" + 
//				"      }\r\n" + 
//				"      .inline {\r\n" + 
//				"        display: inline;\r\n" + 
//				"      }\r\n" + 
//				"      .link-button {\r\n" + 
//				"        background: none;\r\n" + 
//				"        border: none;\r\n" + 
//				"        color: blue;\r\n" + 
//				"        text-decoration: underline;\r\n" + 
//				"        cursor: pointer;\r\n" + 
//				"        font-family: serif;\r\n" + 
//				"        padding: 0;\r\n" + 
//				"      }\r\n" + 
//				"      .link-button:focus {\r\n" + 
//				"        outline: none;\r\n" + 
//				"      }\r\n" + 
//				"      .link-button:active {\r\n" + 
//				"        color: red;\r\n" + 
//				"      }\r\n" + 
//				"      .flex {\r\n" + 
//				"        width: 100%;\r\n" + 
//				"        display: flex;\r\n" + 
//				"      }\r\n" + 
//				"      .flex > div {\r\n" + 
//				"        width: 50%;\r\n" + 
//				"        margin: 10px;\r\n" + 
//				"      }\r\n" + 
//				"      .footer {\r\n" + 
//				"        position: relative;\r\n" + 
//				"      }\r\n" + 
//				"      .list {\r\n" + 
//				"        position: absolute;\r\n" + 
//				"        left: 23%;\r\n" + 
//				"      }\r\n" + 
//				"      .list > ul li {\r\n" + 
//				"        display: inline;\r\n" + 
//				"        padding: 5px 40px;\r\n" + 
//				"        margin: 12px;\r\n" + 
//				"        border: 1px solid rgba(0, 0, 0, 0);\r\n" + 
//				"        background-color: rgb(239, 239, 255);\r\n" + 
//				"      }\r\n" + 
//				"      .list > ul li:hover {\r\n" + 
//				"        background-color: rgb(193, 193, 255);\r\n" + 
//				"        cursor: pointer;\r\n" + 
//				"      }\r\n" + 
//				"    </style>  "
//				+ "</head>"); 
//				
//		//HTML body & table-01
//		out.println("<body>\r\n" + 
//				"    <div class='flex'>\r\n" + 
//				"		<div>"+
//				"        <table style=\"width:100%\">\r\n" + 
//				"            <tr>\r\n" + 
//				"                <th>name</th>\r\n" + 
//				"                <th>ext</th>\r\n" + 
//				"                <th>size</th>\r\n" + 
//				"                <th>date</th>\r\n" + 
//				"            </tr>");
//
//		//prev page
//		out.println("<tr>");
//		out.println("<td>");
//		out.println("<form method='get' action='" + requestUri + "' class='inline'>");
//		out.println("<input type='hidden' name='submitLeft_name' value='prev'>");
//		out.println("<button type='submit' name='fromWhere' value='left' class='link-button'>");
//		out.println("...");
//		out.println("</button></form></td>");
//		out.println("<td></td>");
//		out.println("<td>DIR</td>");
//		out.println("<td></td>");
//		out.println("</tr>");
//		
//		//td
//		for(String file: fileListLeft) {
//			
//			//get the file's data
//			File fileData = new File(FOLDER_PATH_LEFT+file);
//
//			out.println("<tr>");
//			
//			//name of if folder or file
//			if(fileData.isDirectory()) {
//				out.println("<td>");
//				out.println("<form method='get' action='" + requestUri + "' class='inline'>");
//				out.println("<input type='hidden' name='submitLeft_name' value='" + fileData.getName() + "'>");
//				out.println("<button type='submit' name='fromWhere' value='left' class='link-button'>");
//				out.println(fileData.getName());
//				out.println("</button></form></td>");
//			}else {
//				out.println("<td>" + fileData.getName() + "</td>");
//			}
//			
//			//extension
//			String extension = "";
//			int i = fileData.getName().lastIndexOf('.');
//			if (i > 0) {
//			    extension = fileData.getName().substring(i+1);
//			}
//			out.println("<td>" + extension + "</td>"); 
//			
//			//size
//			if(!fileData.isDirectory()) {
//				out.println("<td>" + String.format("%,d",fileData.length()) + "</td>"); 
//			}else {
//				out.println("<td>DIR</td>"); 
//			}
//			
//			//date
//			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//			out.println("<td>" + dateFormat.format(fileData.lastModified()) + "</td>"); 
//			out.println("</tr>"); 
//		}
//		
//		out.println("</table>\r\n" + 
//				"	</div>");
//		
//		
//		//table-02
//		out.println("<div>"
//				+ "<table style=\"width:100%\">" + 
//				"	<tr>" + 
//				"	<th>name</th>" + 
//				"	<th>ext</th>" + 
//				"	<th>size</th>" + 
//				"	<th>date</th>" + 
//				"	</tr>");
//		
//		//prev page
//		out.println("<tr>");
//		out.println("<td>");
//		out.println("<form method='get' action='" + requestUri + "' class='inline'>");
//		out.println("<input type='hidden' name='submitRight_name' value='prev'>");
//		out.println("<button type='submit' name='fromWhere' value='right' class='link-button'>");
//		out.println("...");
//		out.println("</button></form></td>");
//		out.println("<td></td>");
//		out.println("<td>DIR</td>");
//		out.println("<td></td>");
//		out.println("</tr>");
//		
//		//td
//		for(String file: fileListRight) {
//					
//		//get the file's data
//		File fileData = new File(FOLDER_PATH_RIGHT+file);
//
//		out.println("<tr>");
//					
//		//name of if folder or file
//		if(fileData.isDirectory()) {
//		out.println("<td>");
//		out.println("<form method='get' action='" + requestUri + "' class='inline'>");
//		out.println("<input type='hidden' name='submitRight_name' value='" + fileData.getName() + "'>");
//		out.println("<button type='submit' name='fromWhere' value='right'  class='link-button'>");
//		out.println(fileData.getName());
//		out.println("</button></form></td>");
//			}else {
//		out.println("<td>" + fileData.getName() + "</td>");
//		}
//					
//		//extension
//		String extension = "";
//		int i = fileData.getName().lastIndexOf('.');
//		if (i > 0) {
//		extension = fileData.getName().substring(i+1);
//		}
//		out.println("<td>" + extension + "</td>"); 
//					
//		//size
//		if(!fileData.isDirectory()) {
//		out.println("<td>" + String.format("%,d",fileData.length()) + "</td>"); 
//		}else {
//		out.println("<td>DIR</td>"); 
//		}
//					
//		//date
//		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//		out.println("<td>" + dateFormat.format(fileData.lastModified()) + "</td>"); 
//		out.println("</tr>"); 
//		}
//		out.println("</table>\r\n" + 
//		"	</div>");
//		out.println("</div>");
//		
//		//footer button
//				out.println("<div class='footer'>");
//				out.println("<div class='list'>");
//				out.println("<ul>");
//				out.println("<li>NEW</li>");
//				out.println("<li>DELETE</li>");
//				out.println("<li>EDIT</li>");
//				out.println("<li>VIEW</li>");
//				out.println("</ul></div></div>");
//				
//				
//		out.println("</body>\r\n" + 
//				"</html>");
				
//		out.flush();
//		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		for(String source: request.getParameterValues("submitCopy[]")) {
			System.out.println(source);
			File sourceFile = new File(FOLDER_PATH_LEFT + source);
			File targetFile = new File(FOLDER_PATH_RIGHT + source);
			try {
				Files.copy(sourceFile.toPath(), targetFile.toPath());
			} catch (Exception e) {
				System.out.println("error");
			}
		}
	}

}
