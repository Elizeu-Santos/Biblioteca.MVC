<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Página Inicial</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url("library.jpg");
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center;
            margin: 0;
            padding: 50px;
            color: #ffffff;
            text-align: center;
        }
        h1 {
            font-size: 36px;
            margin-bottom: 30px;
        }

        p {
            font-size: 16px;
            line-height: 1.6;
            margin-bottom: 20px;
        }

        .button-container {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-top: 20px;
        }

        .button {
            display: block;
            width: 200px;
            background-color: #007bff;
            color: #ffffff;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s ease;
            text-align: center;
            padding: 10px;
        }

        .button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h1>Bem-vindo à Biblioteca</h1>

    <p>Esta é a página inicial da biblioteca. Você pode fazer login, explorar os livros disponíveis e gerenciar sua conta.</p>
    <p>Clique no botão abaixo para adicionar um novo usuário administrador, caso seja um administrador:</p>

    <div class="button-container"> <a href="addAdminUser.jsp" class="button">Adicionar Usuário Administrador</a> </div>

    <p>Clique no botão abaixo para acessar a lista de livros, caso seja um usuário comum:</p>

    <div class="button-container"> <a href="livros.jsp" class="button">Acessar Lista de Livros</a> </div>
</body>
</html>