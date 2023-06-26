package com.example.biblioteca;

public enum BookStatus {
    AVAILABLE("Disponível"),
    RESERVED("Reservado"),
    BORROWED("Emprestado");

    private final String status;

    BookStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

