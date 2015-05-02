<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<body>
    <h1>Media database</h1>
    <div class="list-group col-lg-4">
        <a href="<c:url value="/Categories" />" class="list-group-item">View categories</a>
        <a href="#" class="list-group-item">Find movie</a>
    </div>
    <div class="clearfix"></div>
    <div class="text-info">Adding category/medium and deleting them can be done trough list of categories</div>
</body>