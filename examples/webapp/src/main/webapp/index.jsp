<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>jelly demo welcome</title>
    <c:set var="basepath" value="${pageContext.request.contextPath}" />
    <link rel="stylesheet" type="text/css" href="${basepath}/css/style.css" />
  </head>
  <body>
    <div id="main">
      <ul>
        <li><a href="json-demo.jsp" target="_blank">json demo</a></li>
      </ul>
    </div>
  </body>
</html>