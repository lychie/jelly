<%@ page pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>MultiFileUpload</title>
    <c:set var="basepath" value="${pageContext.request.contextPath}" />
    <link rel="stylesheet" type="text/css" href="${basepath}/css/style.css" />
    <script type="text/javascript" src="${basepath}/js/jquery-1.6.2.min.js"></script>
    <script type="text/javascript" src="${basepath}/js/jquery.form.min.js"></script>
    <script type="text/javascript" src="${basepath}/js/fileupload/fileupload.js"></script>
  </head>
  <body>
    <div id="main" align="center">
      <form id="multiFileuploadForm" action="${basepath}/simplefileupload" method="post" enctype="multipart/form-data">
        <input type="file" name="file" >
        <input type="file" name="file2" >
        <input type="submit" value=" 上传 ">
      </form>
      <div id="imgbox">
        <img id="image0" />
        <br><br>
        <img id="image1" />
      </div>
    </div>
  </body>
</html>