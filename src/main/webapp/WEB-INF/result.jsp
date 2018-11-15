<%--
  Created by IntelliJ IDEA.
  User: malli
  Date: 11/12/2018
  Time: 10:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result Page</title>
</head>
<body>
<%
    if(request.getAttribute("res") ==null){
        out.println(request.getAttribute("failed").toString());
    }else{
        out.println(request.getAttribute("res").toString());
    }
%>
</body>
</html>
