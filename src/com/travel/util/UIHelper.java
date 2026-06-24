package com.travel.util;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class UIHelper {
    public static final Color COLOR_PRIMARY = new Color(0, 150, 136);
    public static final Color COLOR_PRIMARY_DARK = new Color(0, 121, 107);
    public static final Color COLOR_BACKGROUND_DARK = new Color(33, 33, 33);
    public static final Color COLOR_TEXT_LIGHT = new Color(245, 245, 245);
    public static final Color COLOR_ACCENT = new Color(255, 87, 34);
    public static void styleTable(JTable table) {
        table.setRowHeight(30);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setSelectionBackground(new Color(0, 150, 136, 100));
        table.setSelectionForeground(Color.WHITE);
        table.setGridColor(new Color(60, 60, 60));
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object val, boolean isSel, boolean hasFocus, int row, int col) {
                Component c = super.getTableCellRendererComponent(t, val, isSel, hasFocus, row, col);
                if (!isSel) {
                    if (row % 2 == 0) {
                        c.setBackground(new Color(45, 45, 45));
                    } else {
                        c.setBackground(new Color(35, 35, 35));
                    }
                }
                return c;
            }
        });
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(new Color(50, 50, 50));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(100, 35));
        ((DefaultTableCellRenderer)header.getDefaultRenderer()).setHorizontalAlignment(JLabel.LEFT);
    }
    public static JButton createModernButton(String text, boolean primary) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        if (primary) {
            btn.setBackground(COLOR_PRIMARY);
            btn.setForeground(Color.WHITE);
        } else {
            btn.setBackground(new Color(70, 70, 70));
            btn.setForeground(Color.WHITE);
        }
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (primary) {
                    btn.setBackground(COLOR_PRIMARY_DARK);
                } else {
                    btn.setBackground(new Color(90, 90, 90));
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (primary) {
                    btn.setBackground(COLOR_PRIMARY);
                } else {
                    btn.setBackground(new Color(70, 70, 70));
                }
            }
        });
        return btn;
    }
}
