<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
        
       <c:choose>
               <c:when test="${max >=now}">
                       <c:forEach begin="${max-5 }" end="${max }" step="1" varStatus="t">
                                ${t.index }++
                       </c:forEach>
               
               </c:when>
               <c:otherwise>
                    <c:forEach begin="${now-5 }" end="${now }" step="1" varStatus="t">
                                ${t.index }++
                       </c:forEach>
               </c:otherwise>
       </c:choose>
  
  <body>
         
  </body>
</html>
