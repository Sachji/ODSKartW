<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>Add category</h1>
<c:if test="${not empty error}">
    <div class="alert alert-danger">
        <c:out value="${error}"/>
    </div>
</c:if>

<form class="col-md-4" action="<c:url value="/AddCategory"/>" method="post">
    <div class="form-group">
        <label for="name">Name</label>
        <input type="text" class="form-control" name="category_name" id="name" placeholder="Enter category name">
    </div>
    <div class="form-group">
        <label for="maxMovies">Max movies on medium</label>
        <input type="number" class="form-control" name="maxMovies" id="maxMovies" placeholder="Enter max movies number">
    </div>

    <button type="submit" name="submit" class="btn btn-primary">Submit</button>
    <button type="submit" name="cancel" class="btn btn-default">Cancel</button>
</form>