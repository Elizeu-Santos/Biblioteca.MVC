package com.example.biblioteca.controller;

import com.example.biblioteca.AuthenticationManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private AuthenticationManager authManager;

    @Override
    public void init() throws ServletException {
        super.init();
        authManager = new AuthenticationManager();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (authManager.authenticate(username, password)) {
            // Autenticação bem-sucedida
            req.getSession().setAttribute("username", username);
            req.getSession().setAttribute("password", password);

            // Obtém o tipo de usuário a partir do AuthenticationManager
            String userType = authManager.getUserType(username);

            // Armazena o tipo de usuário na sessão
            req.getSession().setAttribute("userType", userType);

            resp.sendRedirect(req.getContextPath() + "/livros?action=list");
        } else {
            // Autenticação falhou
            resp.sendRedirect(req.getContextPath() + "/unauthorized.jsp");
        }
    }
}