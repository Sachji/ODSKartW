<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>Add medium to ${category.name}</h1>
<c:if test="${not empty error}">
    <div class="alert alert-danger">
        <c:out value="${error}"/>
    </div>
</c:if>

<form class="col-md-4" action="<c:url value="/AddMedium?id=${category.id}"/>" method="post">
    <c:forEach begin="1" end="${category.maxMediumMovies}" varStatus="loop">
        <div class="form-group">
            <label for="movie<c:out value="${loop.index}"/>">Movie #<c:out value="${loop.index}"/></label>
            <input type="text" class="form-control" name="movie<c:out value="${loop.index}"/>" id="movie<c:out value="${loop.index}"/>" placeholder="Enter movie name">
        </div>
    </c:forEach>
    <input type="hidden" value="${category.id}" name="category_id">

    <button type="submit" name="submit" class="btn btn-primary">Submit</button>
</form>