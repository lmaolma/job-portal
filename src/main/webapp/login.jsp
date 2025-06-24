<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="navbar.jsp" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
	<div class="main-content" align="center">
    <h2>Login</h2>
    <form action="login" method="post">
        Username: <input type="text" name="username" required /><br/>
        Password: <input type="password" name="password" required /><br/>
        <button type="submit">Login</button>
    </form>
    </div>
</body>
</html>
