<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Adicionar Livro</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f2f2f2;
      margin: 0;
      padding: 0;
    }

    h1 {
      text-align: center;
      color: #333333;
      padding: 20px 0;
    }

    form {
      max-width: 400px;
      margin: 0 auto;
      background-color: #ffffff;
      padding: 30px;
      border-radius: 6px;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    label {
      display: block;
      margin-bottom: 10px;
      color: #333333;
    }

    input[type="text"],
    input[type="date"] {
      width: 100%;
      padding: 10px;
      border: 1px solid #cccccc;
      border-radius: 4px;
      margin-bottom: 20px;
    }

    button[type="submit"] {
      display: block;
      width: 100%;
      padding: 10px;
      background-color: #007bff;
      color: #ffffff;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      font-size: 16px;
    }

    button[type="submit"]:hover {
      background-color: #0056b3;
    }
  </style>
</head>
<body>
<h1>Adicionar Livro</h1>
<form action="livros" method="POST">
  <input type="hidden" name="action" value="add">
  <label for="id">ID:</label>
  <input type="text" name="id" id="id" required><br>
  <label for="name">Nome:</label>
  <input type="text" name="name" id="name" required><br>
  <label for="date">Data:</label>
  <input type="date" name="date" id="date" required><br>
  <button type="submit">Adicionar</button>
</form>
</body>
</html>
