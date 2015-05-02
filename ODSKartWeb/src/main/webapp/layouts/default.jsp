<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <tiles:insertAttribute name="css" />
        <title><tiles:insertAttribute name="title" /></title>
    </head>
    <body class="container">
        <ol class="breadcrumb">
            <li><a href="${pageContext.request.contextPath}/">Home</a></li>
            <tiles:insertAttribute name="breadcrumb" />
        </ol>
        <tiles:insertAttribute name="body" />
    </body>
</html>
