<%--
  Created by IntelliJ IDEA.
  User: kostasD
  Date: 11/12/2020
  Time: 9:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<jsp:useBean id = "user" scope = "request" class = "model.User"/>
<jsp:useBean id = "track" scope = "request" class = "model.Track"/>

<form action="Serv" method="post">
    <input type="hidden" name="username" value="<%=user.getUsername()%>">
    <input type="hidden" name="track" value="<%=track%>">
    <br />
    <input type="submit" value="ok" />
</form>

<%
out.print(user.getUsername());
%>


</body>
</html>
