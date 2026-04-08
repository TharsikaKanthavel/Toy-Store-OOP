package com.toystore.controller;

import com.toystore.model.Toy;
import com.toystore.service.ToyService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ToyController", value = "/toys/*")
public class ToyController extends HttpServlet {
    private ToyService toyService;

    @Override
    public void init() {
        this.toyService = new ToyService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getPathInfo();
        if (action == null) action = "/";

        try {
            switch (action) {
                case "/":
                case "/home":
                    homepage(request, response);
                    break;
                case "/products":
                    productList(request, response);
                    break;
                case "/view":
                    viewToy(request, response);
                    break;
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/search":
                    searchToys(request, response);
                    break;
                case "/category":
                    showCategory(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getPathInfo();

        try {
            switch (action) {
                case "/add":
                    addToy(request, response);
                    break;
                case "/update":
                    updateToy(request, response);
                    break;
                case "/delete":
                    deleteToy(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/toys/products");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void homepage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Toy> featuredToys = toyService.getAllToys().subList(0, Math.min(4, toyService.getAllToys().size()));
        request.setAttribute("featuredToys", featuredToys);
        request.getRequestDispatcher("/homepage.jsp").forward(request, response);
    }

    private void productList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Toy> toys = toyService.getAllToys();
        request.setAttribute("toys", toys);
        request.getRequestDispatcher("/productList.jsp").forward(request, response);
    }

    private void viewToy(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Toy toy = toyService.getToyById(id);
        request.setAttribute("toy", toy);
        request.getRequestDispatcher("/viewToy.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("categories", toyService.getAllCategories());
        request.getRequestDispatcher("/addToy.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Toy toy = toyService.getToyById(id);
        request.setAttribute("toy", toy);
        request.setAttribute("categories", toyService.getAllCategories());
        request.getRequestDispatcher("/editToy.jsp").forward(request, response);
    }

    private void searchToys(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchTerm = request.getParameter("searchTerm");
        List<Toy> toys = toyService.searchToysByName(searchTerm);
        request.setAttribute("toys", toys);
        request.getRequestDispatcher("/productList.jsp").forward(request, response);
    }

    private void showCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String category = request.getParameter("name");
        List<Toy> toys = toyService.getToysByCategory(category);
        request.setAttribute("toys", toys);
        request.setAttribute("category", category);
        request.getRequestDispatcher("/category.jsp").forward(request, response);
    }

    private void addToy(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Toy toy = createToyFromRequest(request);
            toyService.addToy(toy);
            request.getSession().setAttribute("message", "Toy added successfully!");
            response.sendRedirect(request.getContextPath() + "/toys/products");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            showNewForm(request, response);
        }
    }

    private void updateToy(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Toy toy = createToyFromRequest(request);
            toyService.updateToy(toy);
            request.getSession().setAttribute("message", "Toy updated successfully!");
            response.sendRedirect(request.getContextPath() + "/toys/products");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            showEditForm(request, response);
        }
    }

    private void deleteToy(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            toyService.deleteToy(id);
            request.getSession().setAttribute("message", "Toy deleted successfully!");
            response.sendRedirect(request.getContextPath() + "/toys/products");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            productList(request, response);
        }
    }

    private Toy createToyFromRequest(HttpServletRequest request) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String category = request.getParameter("category");

        if (request.getParameter("id") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            return new Toy(id, name, description, price, quantity, category);
        }
        return new Toy(0, name, description, price, quantity, category);
    }
}