<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>jelly idcode demo</title>
    <c:set var="basepath" value="${pageContext.request.contextPath}" />
  </head>
  <body>
    <img src="idCode" onclick="this.src+=''" style="cursor:pointer;" width="115" height="30">
  </body>
</html>