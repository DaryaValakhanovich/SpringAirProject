
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Plane</title>
</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>
<c:if test="${loginedUser==null}">
    <jsp:include page="_menu.jsp"></jsp:include>
</c:if>
<c:if test="${loginedUser.role=='USER'}">
    <jsp:include page="_user_menu.jsp"></jsp:include>
</c:if>
<c:if test="${loginedUser.role=='ADMIN'}">
    <jsp:include page="_admin_menu.jsp"></jsp:include>
</c:if>

<h3>Create Plane</h3>

<p style="color: red;">${errorString}</p>

<form method="POST" action="${pageContext.request.contextPath}/createPlane">

    <table border="0">
        <tr>
            <td>Number of seats</td>
            <td><input type="number" name="numberOfSeats" value="${numberOfSeats}" /></td>
        </tr>
        <tr>
            <td>Weight</td>
            <td><input type="number" step="any" name="weight" value="${weight}" /></td>
        </tr>
        <tr>
            <td>Cruising Speed</td>
            <td><input type="number" step="any" name="cruisingSpeed" value="${cruisingSpeed}" /></td>
        </tr>
        <tr>
            <td>Model</td>
            <td><input type="text" name="model" value="${model}" /></td>
        </tr>
        <tr>
            <td>Company</td>
            <td><input type="text" name="company" value="${company}" /></td>
        </tr>
        <tr>
            <td>Max Flight Altitude</td>
            <td><input type="number" step="any" name="maxFlightAltitude" value="${maxFlightAltitude}" /></td>
        </tr>
        <tr>
            <td>Max Range Of Flight</td>
            <td><input type="number" step="any" name="maxRangeOfFlight" value="${maxRangeOfFlight}" /></td>
        </tr>

        <tr>
            <td colspan="2">
                <a href="${pageContext.request.contextPath}/">Cancel</a>
                <input type="submit" value="Submit" />
            </td>
        </tr>
    </table>
</form>

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>
