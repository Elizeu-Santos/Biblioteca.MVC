<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Livro</title>
</head>
<body>
<h1>Editar Livro</h1>
<form action="livros" method="POST">
    <input type="hidden" name="action" value="edit">
    <input type="hidden" name="id" value="${book.id}">
    <label for="name">Nome:</label>
    <input type="text" name="name" id="name" value="${book.name}" required><br>
    <label for="date">Data:</label>
    <input type="date" name="date" id="date" value="${book.date}" required><br>
    <button type="submit">Salvar</button>
</form>
</body>
</html>
