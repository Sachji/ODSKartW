<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1>Categories</h1>
<c:if test="${not empty error}">
    <div class="alert alert-danger">
        <c:out value="${error}"/>
    </div>
</c:if>
<a href="<c:url value="/AddCategory" />">Add category</a>
<table class="table table-striped">
    <thead>
        <tr><th>#</th><th>Name</th><th>Max medium movies</th><th>View</th><th>Delete</th></tr>
    </thead>
    <tbody>
        <c:forEach items="${categories}" var="category" varStatus="loopStatus">
            <tr>
                <td><c:out value="${category.id}"/></td>
                <td><c:out value="${category.name}"/></td>
                <td><c:out value="${category.maxMediumMovies}"/></td>
                <td><a href="<c:url value="/Category?id=${category.id}" />">View category</a></td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/DeleteCategory?id=${category.id}">
                        <input class="btn btn-sm btn-danger" type="Submit" value="Delete" name="Delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
