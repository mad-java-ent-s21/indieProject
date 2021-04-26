<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="title" value="Search"/>


<html>
    <head>
        <title>Budget Tracker</title>
        <jsp:include page="head.jsp" />
    </head>

    <body>
    <%-- NAV BAR --%>
    <jsp:include page="navbar.jsp" />

    <h3>Welcome to the Budget Tracker!<br>
        Here you can track any expense and income.<br>
        Make your finance matter!</h3>

    <%
        if (session.getAttribute("user") == null) {
    %>
        <a href="userLogin.jsp" class="waves-effect waves-light btn">Log In</a>
        <a href="signup.jsp" class="waves-effect waves-light btn">Sign Up</a>
    <%
        } else {
    %>
        View your entries!!
    <%
        }
    %>

    <jsp:include page="scripts.jsp" />
    </body>
</html>
