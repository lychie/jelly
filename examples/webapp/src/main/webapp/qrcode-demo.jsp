<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>jelly qrcode demo</title>
    <c:set var="basepath" value="${pageContext.request.contextPath}" />
  </head>
  <body>
    <img src="qrcode?text=http://lychie.github.io/index.html">
  </body>
</html>