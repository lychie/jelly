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
        <li><a href="person/list/1.html" target="_blank">orm demo</a></li>
        <li><a href="fileupload-demo.jsp" target="_blank">fileupload demo</a></li>
        <li><a href="filedownload-demo.jsp" target="_blank">filedownload demo</a></li>
        <li><a href="idcode-demo.jsp" target="_blank">IdentificationCode demo</a></li>
        <li><a href="qrcode-demo.jsp" target="_blank">Qrcode demo</a></li>
      </ul>
    </div>
  </body>
</html>