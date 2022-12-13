<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="ImageServlet" method="post" enctype="multipart/form-data">
    <label>Put your image here</label>
    <input type="file" name="image">
    <button type="submit">Add image</button>
</form>

<form action="ImageServlet" method="get">
    <button type="submit">GET PICTURESee</button>
</form>
</body>
</html>
