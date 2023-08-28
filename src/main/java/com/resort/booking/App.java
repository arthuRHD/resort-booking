package com.resort.booking;

import com.resort.booking.view.HostView;

import javax.swing.*;

public class App
{
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new HostView().setVisible(true));
    }

}
