package com.toystore.controller;

import com.toystore.model.User;
import com.toystore.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserController", value = "/users/*")
public class UserController extends HttpServlet {
    private UserService userService;

    @Override
    public void init() {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        try {
            switch (action) {
                case "/register":
                    showRegisterForm(request, response);
                    break;
                case "/login":
                    showLoginForm(request, response);
                    break;
                case "/profile":
                    showProfile(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/delete":
                    deleteUser(request, response);
                    break;
                default:
                    response.sendRedirect("../index.jsp");
                    break;
            }
        } catch (IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        try {
            switch (action) {
                case "/register":
                    registerUser(request, response);
                    break;
                case "/login":
                    loginUser(request, response);
                    break;
                case "/update":
                    updateUser(request, response);
                    break;
                default:
                    response.sendRedirect("../index.jsp");
                    break;
            }
        } catch (IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void showRegisterForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/users/signup.jsp");
        dispatcher.forward(request, response);
    }

    private void showLoginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/users/login.jsp");
        dispatcher.forward(request, response);
    }

    private void showProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = (int) request.getSession().getAttribute("userId");
        User user = userService.getUserById(userId);
        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/users/profile.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = (int) request.getSession().getAttribute("userId");
        User user = userService.getUserById(userId);
        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/users/edit.jsp");
        dispatcher.forward(request, response);
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String role = "customer"; // Default role

        User user = userService.registerUser(username, password, email, role);

        if (user != null) {
            request.getSession().setAttribute("userId", user.getId());
            request.getSession().setAttribute("username", user.getUsername());
            request.getSession().setAttribute("role", user.getRole());
            response.sendRedirect("../index.jsp");
        } else {
            request.setAttribute("error", "Username already exists");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/users/register.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.loginUser(username, password);

        if (user != null) {
            request.getSession().setAttribute("userId", user.getId());
            request.getSession().setAttribute("username", user.getUsername());
            request.getSession().setAttribute("role", user.getRole());
            response.sendRedirect("../index.jsp");
        } else {
            request.setAttribute("error", "Invalid username or password");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/users/login.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int userId = (int) request.getSession().getAttribute("userId");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        User user = userService.getUserById(userId);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        userService.updateUser(user);
        response.sendRedirect("profile");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int userId = (int) request.getSession().getAttribute("userId");
        userService.deleteUser(userId);
        request.getSession().invalidate();
        response.sendRedirect("../index.jsp");
    }
}