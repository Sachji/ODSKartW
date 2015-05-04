<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>Move medium #${medium.id}</h1>
<c:if test="${not empty error}">
    <div class="alert alert-danger">
        <c:out value="${error}"/>
    </div>
</c:if>
Movies on medium:
<ul>
    <c:forEach items="${medium.movies}" var="movie">
        <li><c:out value="${movie.name}"/></li>
    </c:forEach>
</ul>
<form class="col-md-4" action="<c:url value="/MoveMedium?id=${medium.category.id}&medium_id=${medium.id}"/>" method="post">
    <div class="form-group">
        <label for="dest_category">Destination category</label>
        <select class="form-control" name="dest_category">
            <c:forEach items="${categories}" var="category">
                <c:if test="${category.id != medium.category.id}">
                    <option value="<c:out value="${category.id}"/>"><c:out value="${category.name}"/></option>
                </c:if>
            </c:forEach>
        </select>
    </div>
    <input type="hidden" value="${medium.id}" name="medium_id">
    <input type="hidden" value="${medium.category.id}" name="id">

    <button type="submit" name="submit" class="btn btn-primary">Move</button>
</form>