package com.travel.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
public class LoginForm extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    public LoginForm() {
        setTitle("Travel Management - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();
    }
    private void initComponents() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel lblHeader = new JLabel("ADMIN LOGIN", JLabel.CENTER);
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblHeader.setForeground(new Color(0, 150, 136));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(lblHeader, gbc);
        JLabel lblSub = new JLabel("Aplikasi Manajemen Travel", JLabel.CENTER);
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        gbc.gridy = 1;
        mainPanel.add(lblSub, gbc);
        gbc.gridwidth = 1;
        gbc.gridy = 2;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("Username:"), gbc);
        txtUsername = new JTextField(15);
        txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 1;
        mainPanel.add(txtUsername, gbc);
        gbc.gridy = 3;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("Password:"), gbc);
        txtPassword = new JPasswordField(15);
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 1;
        mainPanel.add(txtPassword, gbc);
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.setBackground(new Color(0, 150, 136));
        btnLogin.setForeground(Color.WHITE);
        btnCancel = new JButton("Batal");
        btnCancel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnPanel.add(btnCancel);
        btnPanel.add(btnLogin);
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        mainPanel.add(btnPanel, gbc);
        add(mainPanel);
    }
    public String getUsername() {
        return txtUsername.getText().trim();
    }
    public String getPassword() {
        return new String(txtPassword.getPassword());
    }
    public void addLoginListener(ActionListener listener) {
        btnLogin.addActionListener(listener);
    }
    public void addCancelListener(ActionListener listener) {
        btnCancel.addActionListener(listener);
    }
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Login Error", JOptionPane.ERROR_MESSAGE);
    }
}
