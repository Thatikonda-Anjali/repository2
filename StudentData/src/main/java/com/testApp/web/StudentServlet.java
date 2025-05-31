package com.testApp.web;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testApp.dao.StudentDAO;
import com.testApp.model.Student;


@WebServlet("/student")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<com.testApp.model.Student> studentList = new ArrayList<>();
	int index = 0;

  
    	@Override
    	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    	        throws ServletException, IOException {
    	    String idParam = request.getParameter("id");
    	    String name = request.getParameter("name");
    	    int age = Integer.parseInt(request.getParameter("age"));
    	    String email = request.getParameter("email");
    	    String mobile = request.getParameter("mobile");
    	    StudentDAO studentDAO = new StudentDAO();

    	    try {
    	        if (idParam != null && !idParam.isEmpty()) {
    	            // If ID is present, update the existing student
    	            int id = Integer.parseInt(idParam);
    	            Student student = new Student(id, name, age, email, mobile);
    	            studentDAO.updateStudent(student);
    	        } else {
    	            // No ID => Add new student
    	            Student student = new Student(0, name, age, email, mobile); // ID is auto-generated in DB
    	            studentDAO.addStudent(student);
    	        }

    	        // Get updated list of students and forward to JSP
    	        List<Student> students = studentDAO.getAllStudents();
    	        request.setAttribute("students", students);
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("ViewStudent.jsp");
    	        dispatcher.forward(request, response);

    	    } catch (SQLException e) {
    	        e.printStackTrace(); // You can log this error properly
    	        request.setAttribute("error", "Database error: " + e.getMessage());
    	        request.getRequestDispatcher("ViewStudent.jsp").forward(request, response);
    	    }
    	}

    	
    	
    	
   
    	@Override
    	protected void doGet(HttpServletRequest request, HttpServletResponse response)
    	        throws ServletException, IOException {
    	    String action = request.getParameter("action");
    	    StudentDAO studentDAO = new StudentDAO();
    	    RequestDispatcher dispatcher = null;

    	    try {
    	        if ("delete".equals(action)) {
    	            int id = Integer.parseInt(request.getParameter("id"));
    	            studentDAO.deleteStudent(id);

    	            // Fetch updated list of students
    	            List<Student> students = studentDAO.getAllStudents();
    	            request.setAttribute("students", students);
    	            dispatcher = request.getRequestDispatcher("ViewStudent.jsp");
    	        } else if ("edit".equals(action)) {
    	            int id = Integer.parseInt(request.getParameter("id"));
    	            Student student = studentDAO.getStudentById(id);
    	            request.setAttribute("student", student);
    	            dispatcher = request.getRequestDispatcher("AddStudent.jsp");
    	        } else {
    	            List<Student> students = studentDAO.getAllStudents();
    	            request.setAttribute("students", students);
    	            dispatcher = request.getRequestDispatcher("ViewStudent.jsp");
    	        }
    	        dispatcher.forward(request, response);

    	    } catch (SQLException e) {
    	        e.printStackTrace(); // Handle error properly
    	        request.setAttribute("error", "Database error: " + e.getMessage());
    	        request.getRequestDispatcher("ViewStudent.jsp").forward(request, response);
    	    }
    	}}





   