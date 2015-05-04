<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<body>
    <div class="jumbotron">
        <h1>Hello, welcome to ODSKartW!</h1>
        <p>ODSKartW is web application for storing information about your movie mediums (DVD, BlueRay) using ODS file as a storage.</p>
        <p>You can start exploring ODS File or search for specific movie.</p>
        <p><a class="btn btn-primary btn-lg" href="<c:url value="/Categories" />" role="button">Explore!</a>
            <a class="btn btn-info btn-lg" href="#">Search for movie</a></p>
    </div>
</body>