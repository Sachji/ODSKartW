<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h1 style="display:inline;">${category.name}</h1>
<c:if test="${not empty error}">
    <div class="alert alert-danger">
        <c:out value="${error}"/>
    </div>
</c:if>
<a href="<c:url value="/AddMedium?id=${category.id}"/>">
    <button class="btn btn-sm btn-link">Add medium</button>
</a>
<table class="table table-striped">
    <thead>
        <tr><th>#</th>
                <c:forEach begin="1" end="${category.maxMediumMovies}" varStatus="loop">
                <th>Movie # <c:out value="${loop.index}"/></th>
                </c:forEach>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${mediums}" var="medium" varStatus="loopStatus">
            <tr>
                <td><c:out value="${medium.id}"/></td>
                <c:forEach items="${medium.movies}" var="movie">
                    <td><c:out value="${movie.name}"/></td>
                </c:forEach>
                <c:forEach begin="${fn:length(medium.movies) + 1}" end="${category.maxMediumMovies}" varStatus="loop">
                    <td></td>
                </c:forEach>
                <td>
                    <div class="btn-group btn-group-sm" role="group">
                        <a class="btn btn-warning" href="<c:url value="/MoveMedium?id=${category.id}&medium_id=${medium.id}"/>">Move</a>
                        <input type="hidden" class="btn"><!-- fake sibling to right -->
                    </div>
                    <form class="btn-group btn-group-sm" method="post" action="${pageContext.request.contextPath}/DeleteMedium?id=${category.id}&medium_id=${medium.id}">
                        <input type="hidden" class="btn"><!-- fake sibling to left -->
                        <button class="btn btn-danger" type="Submit" name="Delete">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<form method="post" action="${pageContext.request.contextPath}/DeleteCategory?id=${category.id}">
    <input class="btn btn-sm btn-danger" type="Submit" value="Delete this category" name="Delete">
</form>

<style type="text/css">
    div.btn-group + form.btn-group {margin-left: -5px;}
</style>