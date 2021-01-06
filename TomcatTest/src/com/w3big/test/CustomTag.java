package com.w3big.test;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class CustomTag extends SimpleTagSupport {

	// Jsp body
	StringWriter sw = new StringWriter();

	// attribute
	private String FOLDER_PATH;
	private String requestUri;
	private String[] fileList;
	private int number;
	private String direction;

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getFOLDER_PATH() {
		return FOLDER_PATH;
	}

	public void setFOLDER_PATH(String fOLDER_PATH) {
		FOLDER_PATH = fOLDER_PATH;
	}

	public String getRequestUri() {
		return requestUri;
	}

	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}

	public String[] getFileList() {
		return fileList;
	}

	public void setFileList(String[] fileList) {
		this.fileList = fileList;
	}

	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		out.println("<div>" + "<table style=\"width:100%\">" + "<tr>");
		out.println("<th><input type=\"checkbox\"");
		out.println(direction == "left" ? "name='leftAll' id='leftAll' onclick='handleCheckAllLeftBox()'" : 
			"name='rightAll' id='rightAll' onclick='handleCheckAllRightBox()'");
		out.println("/></th>");
		out.println("<th>name</th> <th>ext</th>"
				+ "<th>size</th>" + "<th>date</th>" + "</tr>");

		out.println("<tr>");
		out.println("<td></td><td>");
		out.println("<form method='get' action='" + requestUri + "' class='inline'>");

		out.println("<input type='hidden' name=");
		out.println(direction == "left" ? "submitLeft_name" : "submitRight_name");
		out.println(" value='prev'>");
		out.println("<button type='submit' name='fromWhere' value=");
		out.println(direction == "left" ? "left" : "right");
		out.println(" class='link-button'>");
		out.println("...");
		out.println("</button></form></td>");
		out.println("<td></td>");
		out.println("<td>DIR</td>");
		out.println("<td></td>");
		out.println("</tr>");

		// td
		for (String file : fileList) {

			// get the file's data
			File fileData = new File(FOLDER_PATH + file);

			out.println("<tr>");
			
			out.println("<td>");
			out.println("<input type=\"checkbox\" name=");
			out.println(direction == "left" ? "checkBoxLeft" : "checkBoxRight");
			out.println("value='" + fileData.getName() + "' id='");
			out.println(direction == "left" ? 
					"left_" + fileData.getName() + "'" :
						"right_"+ fileData.getName() + "'");
			out.println("onclick=\"handleCheckBoxClick('" + direction == "left" ? 
					"left_" + fileData.getName() :
						"right_" + fileData.getName());
			out.print("')\" />");
			out.println("</td>");
			
			// name of if folder or file
			if (fileData.isDirectory()) {
				out.println("<td>");
				out.println("<form method='get' action='" + requestUri + "' class='inline'>");
				out.println("<input type='hidden' name=");
				out.println(direction == "left" ? "submitLeft_name" : "submitRight_name");
				out.println("value='" + fileData.getName() + "'>");
				out.println("<button type='submit' name='fromWhere' value=");
				out.println(direction == "left" ? "left" : "right");
				out.println(" class='link-button'>");
				out.println(fileData.getName());
				out.println("</button></form></td>");
			} else {
				out.println("<td>" + fileData.getName() + "</td>");
			}

			// extension
			String extension = "";
			int i = fileData.getName().lastIndexOf('.');
			if (i > 0) {
				extension = fileData.getName().substring(i + 1);
			}
			out.println("<td>" + extension + "</td>");

			// size
			if (!fileData.isDirectory()) {
				out.println("<td>" + String.format("%,d", fileData.length()) + "</td>");
			} else {
				out.println("<td>DIR</td>");
			}

			// date
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			out.println("<td>" + dateFormat.format(fileData.lastModified()) + "</td>");
			out.println("</tr>");
		}

		out.println("</table>\r\n" + "	</div>");
	}
}
