package com.example.biblioteca;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationManager {
    private Map<String, String> users;

    public AuthenticationManager() {
        users = new HashMap<>();
        // Inicialize o mapa com usuários registrados (nome de usuário e senha)
        users.put("Elizeu", "123456");
    }

    public boolean authenticate(String username, String password) {
        String storedPassword = users.get(username);
        return storedPassword != null && storedPassword.equals(password);
    }

    public String getUserType(String username) {
        if (users.containsKey(username)) {
            return "user";
        } else if (username.equals("admin")) {
            return "admin";
        } else {
            return null;
        }
    }
}
