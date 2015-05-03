<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<li><a href="<c:url value="/Categories" />">Categories</a></li>
<li><a href="<c:url value="/Category?id=${category.id}" />"><c:out value="${category.name}" /></a></li>
<li class="active">Add medium</li>