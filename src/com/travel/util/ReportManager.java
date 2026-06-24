package com.travel.util;
import javax.swing.*;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
public class ReportManager {
    public static void printTable(JTable table, String title) {
        try {
            MessageFormat header = new MessageFormat(title);
            MessageFormat footer = new MessageFormat("Halaman {0}");
            boolean complete = table.print(
                JTable.PrintMode.FIT_WIDTH,
                header,
                footer,
                true,
                null,
                true
            );
            if (complete) {
                JOptionPane.showMessageDialog(null, 
                    "Laporan berhasil dicetak!", 
                    "Print Sukses", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, 
                    "Pencetakan dibatalkan.", 
                    "Print Dibatalkan", 
                    JOptionPane.WARNING_MESSAGE);
            }
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(null, 
                "Gagal mencetak laporan: " + ex.getMessage(), 
                "Print Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
