<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>jelly filedownload demo welcome</title>
    <c:set var="basepath" value="${pageContext.request.contextPath}" />
    <link rel="stylesheet" type="text/css" href="${basepath}/css/style.css" />
  </head>
  <body>
    <div id="main">
      <a href="${basepath}/filedownload?filename=路飞 - 发誓要成为海贼王的男人.jpeg" target="_blank">路飞 - 发誓要成为海贼王的男人.jpeg</a>
    </div>
  </body>
</html>