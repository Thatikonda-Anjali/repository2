<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Student Details</title>
   <%@ include file="/WEB-INF/Style.css" %>
</head>
<body>
    <h2>Student Details</h2>
    <form action="student" method="post">
        <input type="hidden" name="id" value="${student.id}" />

        <div class="form-group">
            <label for="name">Name:</label>
               
        </div>

        <div class="form-group">
            <label for="age">Age:</label>
            <input type="number" name="age" id="age" value="${student.age}" required />
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" name="email" id="email" value="${student.email}" required />
        </div>

        <div class="form-group">
            <label for="mobile">Mobile:</label>
            <input type="tel" name="mobile" id="mobile" value="${student.mobile}" pattern="[0-9]{10}" required />
        </div>

        <div>
            <button type="submit">Save</button>
        </div>
    </form>

    <a href="student?action=view">View Students</a>
</body>
</html>
