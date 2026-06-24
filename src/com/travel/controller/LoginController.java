package com.travel.controller;
import com.travel.dao.AdminDAO;
import com.travel.model.Admin;
import com.travel.view.LoginForm;
import com.travel.view.MainFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
public class LoginController {
    private LoginForm view;
    private AdminDAO dao;
    public LoginController(LoginForm view, AdminDAO dao) {
        this.view = view;
        this.dao = dao;
        this.view.addLoginListener(new LoginListener());
        this.view.addCancelListener(new CancelListener());
    }
    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = view.getUsername();
            String password = view.getPassword();
            if (username.isEmpty() || password.isEmpty()) {
                view.showErrorMessage("Username dan password tidak boleh kosong!");
                return;
            }
            try {
                Admin admin = dao.login(username, password);
                if (admin != null) {
                    view.dispose();
                    java.awt.EventQueue.invokeLater(() -> {
                        MainFrame mainFrame = new MainFrame(admin);
                        mainFrame.setVisible(true);
                    });
                } else {
                    view.showErrorMessage("Username atau password salah!");
                }
            } catch (SQLException ex) {
                view.showErrorMessage("Error database: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
    class CancelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
