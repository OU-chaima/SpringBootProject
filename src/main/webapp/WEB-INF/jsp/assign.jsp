<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Assign Employee to Project</title>
</head>
<body>
<h2>Assign Employee to Project</h2>
<sec:authorize access="hasRole('ROLE_MANAGER')">
    <form action="/assign" method="post">
        <label for="employeeId">Employee:</label>
        <select id="employeeId" name="employeeId">
            <c:forEach var="employee" items="${employees}">
                <option value="${employee.id}">${employee.name}</option>
            </c:forEach>
        </select>
        <br/>
        <label for="projectId">Project:</label>
        <select id="projectId" name="projectId">
            <c:forEach var="project" items="${projects}">
                <option value="${project.id}">${project.name}</option>
            </c:forEach>
        </select>
        <br/>
        <button type="submit">Assign</button>
    </form>
</sec:authorize>
</body>
</html>
