<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
    <title>First lesson</title>
</head>
<body>
    <%
        String num = request.getAttribute("num").toString();
    %>
    <h1>Product count: <%= num %></h1>
    <p>${payload}</p>
</body>
</html>
