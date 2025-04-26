package ua.edunung.se.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.edunung.se.config.HibernateUtil;
import ua.edunung.se.entity.User;

import java.io.IOException;

@WebServlet("/update-password")
public class ChangePasswordServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User ) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        }

        String newPassword = req.getParameter("newPassword");

        try (Session hibernateSession = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = hibernateSession.beginTransaction();

            // Оновлення паролю користувача
            user.setPassword(newPassword);
            hibernateSession.update(user);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Не вдалося змінити пароль. Спробуйте ще раз.");
            req.getRequestDispatcher("/templates/change-password.ftl").forward(req, resp);
            return;
        }

        // Перенаправлення на сторінку
        resp.sendRedirect(req.getContextPath() + "/products");
    }
}