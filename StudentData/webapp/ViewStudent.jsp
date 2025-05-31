<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
   <%@ include file="/WEB-INF/add.css" %>
    <title>View Students</title>
   
</head>
<body>

    <a class="top-link" href="<%= request.getContextPath() %>/AddStudent.jsp">Add New Student</a>

    <h2>Student List</h2> 
<input type="text" id="searchInput" placeholder="Search students..." onkeyup="filterStudents()" style="margin: 10px; padding: 5px; width: 200px;">
<input type = "submit" value = "search">

       <table id="studentTable">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Age</th>
            <th>Email</th>
            <th>Mobile</th>
            <th>Actions</th>
        </tr>

        <c:forEach var="student" items="${students}">
            <tr>
                <td>${student.id}</td>
                <td>${student.name}</td>
                <td>${student.age}</td>
                <td>${student.email}</td>
                <td>${student.mobile}</td>
                <td class="actions">
                    <a href="student?action=edit&id=${student.id}">Edit</a>
                    &nbsp;
                    <a class="delete" href="student?action=delete&id=${student.id}" onclick="return confirm('ARE YOU SURE?');">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
<script>
function filterStudents() {
    var input = document.getElementById("searchInput");
    var filter = input.value.toLowerCase();
    var table = document.getElementById("studentTable");
    var rows = table.getElementsByTagName("tr");

    for (var i = 1; i < rows.length; i++) {
        var cells = rows[i].getElementsByTagName("td");
        var match = false;

        if (filter.length < 3) {
            rows[i].style.display = ""; // Show all rows
            continue;
        }

        for (var j = 0; j < cells.length - 1; j++) {
            if (cells[j].innerText.toLowerCase().includes(filter)) {
                match = true;
                break;
            }
        }
        rows[i].style.display = match ? "" : "none";
    }
}
</script>
</body>
</html>
