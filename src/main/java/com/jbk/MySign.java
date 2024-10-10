package com.jbk;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/signin")
public class MySign extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Retrieve username and password from frontend
		
		String name=req.getParameter("name1");
		String pass=req.getParameter("pass1");
		System.out.println(name+" "+pass);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "root");
			PreparedStatement ps = con.prepareStatement("insert into logininfo values(?,?)");
			ps.setString(1, name);
			ps.setString(2, pass);
			int status = ps.executeUpdate();
			if(status>0) {
				System.out.println("Success");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		PrintWriter out =resp.getWriter();
		out.println("Welcome "+name);
	}

}
