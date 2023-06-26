<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Dados do Usuário</title>
</head>
<body>
<h1>Dados do Usuário</h1>
<table>
  <tr>
    <td>ID:</td>
    <td>${user.id}</td>
  </tr>
  <tr>
    <td>Nome:</td>
    <td>${user.name}</td>
  </tr>
  <tr>
    <td>E-mail:</td>
    <td>${user.email}</td>
  </tr>
  <tr>
    <td>Tipo:</td>
    <td>${user.type}</td>
  </tr>
</table>
</body>
</html>
