package ua.edunung.se.servlets;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import ua.edunung.se.config.FreeMarkerConfig;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/change-password")
public class NewPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Configuration cfg = FreeMarkerConfig.getConfig(getServletContext());
        Map<String, Object> model = new HashMap<>();
        model.put("contextPath", req.getContextPath());
        model.put("session", req.getSession());

        // Якщо є повідомлення про помилку
        Object error = req.getAttribute("error");
        if (error != null) {
            model.put("error", error.toString());
        }

        Template template = cfg.getTemplate("change-password.ftl");

        resp.setContentType("text/html; charset=UTF-8");
        try (Writer out = new OutputStreamWriter(resp.getOutputStream(), "UTF-8")) {
            template.process(model, out);
        } catch (TemplateException e) {
            throw new ServletException(e);
        }
    }
}