<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>jelly fileupload demo welcome</title>
    <c:set var="basepath" value="${pageContext.request.contextPath}" />
    <link rel="stylesheet" type="text/css" href="${basepath}/css/style.css" />
  </head>
  <body>
    <div id="main">
      <ul>
        <li><a href="simple-fileupload.jsp" target="_blank">simple-fileupload demo</a></li>
        <li><a href="multi-fileupload.jsp" target="_blank">multi-fileupload demo</a></li>
        <li><a href="md5-fileupload.jsp" target="_blank">md5-fileupload demo</a></li>
      </ul>
    </div>
  </body>
</html>