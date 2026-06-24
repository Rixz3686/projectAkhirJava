package com.travel;
import com.formdev.flatlaf.FlatDarkLaf;
import com.travel.controller.LoginController;
import com.travel.dao.AdminDAO;
import com.travel.view.LoginForm;
import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
            UIManager.put("Button.arc", 8);
            UIManager.put("Component.arc", 8);
            UIManager.put("TextComponent.arc", 8);
            UIManager.put("Component.focusWidth", 1);
            UIManager.put("Component.innerFocusWidth", 0);
        } catch (Exception ex) {
            System.err.println("Failed to initialize FlatLaf, using default L&F.");
            ex.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(() -> {
            LoginForm loginForm = new LoginForm();
            AdminDAO adminDAO = new AdminDAO();
            new LoginController(loginForm, adminDAO);
            loginForm.setVisible(true);
        });
    }
}
