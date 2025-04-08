<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>People</title>
</head>
<body>
    <h1>People</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Age</th>
        </tr>
        <%--@elvariable id="people" type="java.util.List"--%>
        <c:forEach items="${people}" var="person">
            <tr>
                <td>${person.id}</td>
                <td>${person.name}</td>
                <td>${person.age}</td>
            </tr>
        </c:forEach>
    </table>

    <form action="test" method="post">
        <label for="id">ID:</label>
        <input type="number" name="id" id="id"><br>

        <label for="name">Name:</label>
        <input type="text" name="name" id="name"><br>

        <label for="age">Age:</label>
        <input type="number" name="age" id="age"><br>

        <input type="submit" value="Save" name="save">
    </form>
</body>
</html>
