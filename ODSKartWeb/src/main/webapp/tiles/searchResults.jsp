<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h1 style="display:inline;">Search </h1>
<c:if test="${empty mediums}">
    <div class="alert alert-info">
        No results found!
    </div>
</c:if>

<table class="table table-striped">
    <thead>
        <tr>
            <th>Category</th>
            <th>Medium ID</th>
            <th>Movies</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${mediums}" var="medium">
            <tr>
                <td>
                    <a href="<c:url value="/Category?id=${medium.category.id}"/>"><c:out value="${medium.category.name}"/></a>
                </td>
                <td><c:out value="${medium.id}"/></td>
                <td>
                    <c:forEach items="${medium.movies}" var="movie" varStatus="movieLoop">
                        <c:out value="${movie.name}"/><c:if test="${!movieLoop.last}">,</c:if>
                    </c:forEach>
                </td>
                <td>
                    <div class="btn-group btn-group-sm" role="group">
                        <a class="btn btn-warning" href="<c:url value="/MoveMedium?id=${medium.category.id}&medium_id=${medium.id}"/>">Move</a>
                        <input type="hidden" class="btn"><!-- fake sibling to right -->
                    </div>
                    <form class="btn-group btn-group-sm" method="post" action="${pageContext.request.contextPath}/DeleteMedium?id=${medium.category.id}&medium_id=${medium.id}">
                        <input type="hidden" class="btn"><!-- fake sibling to left -->
                        <button class="btn btn-danger" type="Submit" name="Delete">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<style type="text/css">
    div.btn-group + form.btn-group {margin-left: -5px;}
</style>