<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>Search movie</h1>
<c:if test="${not empty error}">
    <div class="alert alert-danger">
        <c:out value="${error}"/>
    </div>
</c:if>

<form class="col-md-4" action="<c:url value="/SearchResults"/>" method="post">
    <div class="form-group">
        <label for="movie_name">Movie name</label>
        <input type="text" class="form-control" name="movie_name" id="movie_name" placeholder="Enter movie name">
    </div>

    <button type="submit" name="submit" class="btn btn-primary">Submit</button>
</form>