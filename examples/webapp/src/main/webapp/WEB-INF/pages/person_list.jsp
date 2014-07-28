<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>jelly orm demo</title>
    <c:set var="basepath" value="${pageContext.request.contextPath}" />
    <link rel="stylesheet" type="text/css" href="${basepath}/css/style.css" />
    <link rel="stylesheet" type="text/css" href="${basepath}/css/pager/pagination.css" />
    <script type="text/javascript" src="${basepath}/js/jquery-1.6.2.min.js"></script>
    <script type="text/javascript" src="${basepath}/js/pager/jquery.pagination.js"></script>
    <script type="text/javascript">
    $(function(){
    	$("#pagination").pagination(${pager.totalCount}, {
        	items_per_page : ${pager.pageSize},	/* 每页显示的记录条数 */
        	current_page : ${pager.thisPage - 1},	/* 当前页码 */
        	num_edge_entries : 0,		/* 两侧的分页条目 */
        	num_display_entries : 10,	/* 连续分页主体部分分页条目数 */
        	prev_text : '上一页',	/* 上一页 */
        	next_text : '下一页',	/* 下一页 */
        	ellipse_text : "",
        	link_to : '__id__.html',
    		callback : function(){return true;}
        });
    });
    </script>
  </head>
  <body>
    <div id="main">
      <table id="result" width="700px" align="center">
        <c:forEach items="${pager.result}" var="person">
          <tr>
            <td width="15%">${person.id}</td>
            <td width="20%">${person.name}</td>
            <td width="15%">${person.sex}</td>
            <td width="30%">${person.address}</td>
            <td width="20%">${person.birthdayStr}</td>
         </tr>
        </c:forEach>
      </table>
    </div>
    <p> <div id="pagination" class="pagination" style="margin-left:410px;"> </p>
  </body>
</html>