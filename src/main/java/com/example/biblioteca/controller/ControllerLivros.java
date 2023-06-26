package com.example.biblioteca.controller;

import com.example.biblioteca.AuthenticationManager;
import com.example.biblioteca.BookStatus;
import com.example.biblioteca.model.Author;
import com.example.biblioteca.model.Book;
import com.example.biblioteca.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ControllerLivros extends HttpServlet {
    private List<Book> books;
    private List<User> users;
    private AuthenticationManager authManager;

    @Override
    public void init() throws ServletException {
        super.init();
        books = new ArrayList<>();
        authManager = new AuthenticationManager();
        users = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (isAuthenticated(req)) {
            String action = req.getParameter("action");
            if (action != null && !action.isEmpty()) {
                switch (action) {
                    case "list":
                        listBooks(req, resp);
                        break;
                    case "add":
                        showAddForm(req, resp);
                        break;
                    case "edit":
                        showEditForm(req, resp);
                        break;
                    case "delete":
                        deleteBook(req, resp);
                        break;
                    case "addUser":
                        addUser(req, resp);
                        break;
                    case "addAdmin":
                        addAdminUser(req, resp);
                        break;
                    default:
                        redirectToBookList(req, resp);
                }
            } else {
                redirectToBookList(req, resp);
            }
        } else {
            redirectToLogin(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (isAuthenticated(req)) {
            String action = req.getParameter("action");
            if (action != null && !action.isEmpty()) {
                switch (action) {
                    case "addAdmin":
                        addAdminUser(req, resp);
                        break;
                    case "add":
                        addBook(req, resp);
                        break;
                    case "edit":
                        updateBook(req, resp);
                        break;
                    case "updateStatus":
                        updateBookStatus(req, resp);
                        break;
                    default:
                        redirectToBookList(req, resp);
                }
            } else {
                redirectToBookList(req, resp);
            }
        } else {
            redirectToLogin(req, resp);
        }
    }

    private void updateBookStatus(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String status = req.getParameter("status");

        Book book = findBookById(id);
        if (book != null) {
            book.setStatus(BookStatus.valueOf(status));
        }

        redirectToBookList(req, resp);
    }
    private boolean isAuthenticated(HttpServletRequest req) {
        String username = (String) req.getSession().getAttribute("username");
        String password = (String) req.getSession().getAttribute("password");

        return authManager.authenticate(username, password);
    }

    private void listBooks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("books", books);
        req.getRequestDispatcher("livros.jsp").forward(req, resp);
    }

    private void showAddForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("addLivro.jsp").forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookId = req.getParameter("id");
        Book book = findBookById(bookId);
        if (book != null) {
            req.setAttribute("book", book);
            req.getRequestDispatcher("editLivro.jsp").forward(req, resp);
        } else {
            redirectToBookList(req, resp);
        }
    }

    private void addBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String dateStr = req.getParameter("date");

        // Verificar se todos os campos foram preenchidos
        if (id.isEmpty() || name.isEmpty() || dateStr.isEmpty()) {
            // Se algum campo estiver vazio, redirecionar para a página de adição com uma mensagem de erro
            req.setAttribute("error", "Todos os campos devem ser preenchidos");
            req.getRequestDispatcher("addLivro.jsp").forward(req, resp);
            return;
        }

        // Verificar se o ID do livro já existe
        if (isDuplicateId(id)) {
            // Se o ID já existir, exibir uma mensagem de erro
            req.setAttribute("error", "O ID do livro já existe");
            req.getRequestDispatcher("livros.jsp").forward(req, resp);
            return;
        }

        // Verificar se o nome do livro já existe
        if (isDuplicateName(name)) {
            // Se o nome já existir, exibir uma mensagem de erro
            req.setAttribute("error", "O nome do livro já existe");
            req.getRequestDispatcher("livros.jsp").forward(req, resp);
            return;
        }

        LocalDate date;

        // Verificar se a data está em um formato válido
        try {
            date = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            // Se a data não estiver em um formato válido, redirecionar para a página de adição com uma mensagem de erro
            req.setAttribute("error", "Formato de data inválido");
            req.getRequestDispatcher("addLivro.jsp").forward(req, resp);
            return;
        }

        Author author = new Author("1", "Elizeu Santos");
        Book book = new Book(id, name, date);
        book.setAuthor(author);
        book.setStatus(BookStatus.AVAILABLE);

        books.add(book);
        redirectToBookList(req, resp);
    }

    private boolean isDuplicateId(String id) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    private boolean isDuplicateName(String name) {
        for (Book book : books) {
            if (book.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    private void updateBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        LocalDate date = LocalDate.parse(req.getParameter("date"));

        Book book = findBookById(id);
        if (book != null) {
            book.setName(name);
            book.setDate(date);
        }

        redirectToBookList(req, resp);
    }

    private void deleteBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookId = req.getParameter("id");
        Book book = findBookById(bookId);
        if (book != null) {
            books.remove(book);
        }
        redirectToBookList(req, resp);
    }

    private Book findBookById(String bookId) {
        for (Book book : books) {
            if (book.getId().equals(bookId)) {
                return book;
            }
        }
        return null;
    }

    private void redirectToBookList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(req.getContextPath() + "/livros?action=list");
    }

    private void redirectToLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }

    private boolean isAdminUser(HttpServletRequest req) {
        String username = (String) req.getSession().getAttribute("username");
        String password = (String) req.getSession().getAttribute("password");
        if (username != null && password != null && username.equals("Admin") && password.equals("123456")) {
            return true;
        } else {
            return false;
        }
    }

    private void redirectToUserList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(req.getContextPath() + "/users?action=list");
    }

    private void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String type = req.getParameter("type");

        if (isAdminUser(req)) {
            User user = new User(id, name, email, username, password, type);
            users.add(user);
            redirectToUserList(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/unauthorized.jsp");
        }
    }

    private void addAdminUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String type = "admin";

        User adminUser = new User(id, name, email, password, username, type);
        users.add(adminUser);
        redirectToUserList(req, resp);
    }
}
