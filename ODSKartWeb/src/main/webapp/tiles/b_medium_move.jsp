<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<li><a href="<c:url value="/Categories" />">Categories</a></li>
<li><a href="<c:url value="/Category?id=${medium.category.id}" />"><c:out value="${medium.category.name}" /></a></li>
<li class="active">Move medium #${medium.id}</li>