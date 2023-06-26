<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Livros</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid #dddddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        form {
            display: inline;
        }

        select, input[type="submit"] {
            margin-top: 5px;
            padding: 5px;
            border-radius: 4px;
        }

        .update-button {
            display: inline-block;
            background-color: #007bff;
            color: #ffffff;
            padding: 5px 10px;
            border-radius: 4px;
            border: none;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .update-button:hover {
            background-color: #0056b3;
        }

        .actions a {
            margin-right: 5px;
            padding: 5px 10px;
            border-radius: 4px;
            color: #ffffff;
            text-decoration: none;
        }

        .edit-button {
            background-color: #FF5722;
        }

        .delete-button {
            background-color: #FF9800;
        }

        .add-button {
            display: inline-block;
            background-color: #4CAF50;
            color: #ffffff;
            padding: 5px 10px;
            border-radius: 4px;
            text-decoration: none;
        }

        .add-button:hover,
        .actions a:hover,
        .update-button:hover {
            opacity: 0.8;
        }
    </style>
</head>
<body>
<h1>Lista de Livros</h1>
<table>
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Data</th>
        <th>Autor</th>
        <th>Status</th>
        <th>Ações</th>
    </tr>
    <c:forEach var="book" items="${books}">
    <tr>
        <td>${book.id}</td>
        <td>${book.name}</td>
        <td>${book.date}</td>
        <td>${book.author.name}</td>
        <td>${book.status}</td>
        <td class="actions">
            <form action="livros" method="post">
                <input type="hidden" name="action" value="updateStatus">
                <input type="hidden" name="id" value="${book.id}">
                <select name="status">
                    <option value="AVAILABLE" ${book.status == 'AVAILABLE' ? 'selected' : ''}>Disponível</option>
                    <option value="RESERVED" ${book.status == 'RESERVED' ? 'selected' : ''}>Reservado</option>
                    <option value="BORROWED" ${book.status == 'BORROWED' ? 'selected' : ''}>Emprestado</option>
                </select>
                <button type="submit" class="update-button">Atualizar</button>
            </form>
            <a href="livros?action=edit&amp;id=${book.id}" class="edit-button">Editar</a>
            <a href="livros?action=delete&amp;id=${book.id}" class="delete-button">Excluir</a>
        </td>
    </tr>
    </c:forEach>
</table>
<br>
<a href="livros?action=add" class="add-button">Adicionar Livro</a>
</body>
</html>

