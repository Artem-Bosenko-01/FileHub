<%--
  Created by IntelliJ IDEA.
  User: Artem
  Date: 14.05.2021
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
  <meta charset="utf-8">
  <title>Calculator</title>

</head>
<body class="index-page">
<div>
  <form action="app" method="POST">
    <h3>Please input your code for Booby compiler</h3><br>
    <input name="input" size="100"/>
    <input type="submit" value="Submit" />
    <h3>Result is</h3><br>
    <textarea name="output" cols="40" rows="3" readonly></textarea>
    <style> textarea{resize: none;}</style>
  </form>
</div>
</body>
</html>
