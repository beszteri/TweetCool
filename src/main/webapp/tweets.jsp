<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>Tweets</title>
</head>
<body>
<c:forEach items="${tweetList}" var="tweet" varStatus="status">
    <p>content: </p><c:out value="${tweet.content}"></c:out>
    <p>poster: </p><c:out value="${tweet.poster}"></c:out>
</c:forEach>

<form method="get" action="index.html">
    <input type="submit" value="Home">
</form>
</body>
</html>