package com.resort.booking.view;

import com.resort.booking.dao.HostDao;
import com.resort.booking.formatter.RateFormater;
import com.resort.booking.model.Host;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HostView extends JFrame {

    private static final String TITLE = "Gestion des hôtes";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private Host selectedHost;
    private JFormattedTextField idTextField;
    private JTextField nameTextField;
    private JTextField dateTextField;
    private JTextField responseTimeTextField;
    private JFormattedTextField acceptanceRateTextField;
    private JCheckBox superHostCheckBox;
    private DefaultTableModel tableModel;
    private JTable table;
    private final HostDao hostDao;
    private static final int COLUMN_SIZE = 20;

    private void updateValues() {
        selectedHost.setId((int) idTextField.getValue());
        selectedHost.setName(nameTextField.getText());
        selectedHost.setCreationDate(LocalDateTime.parse(dateTextField.getText(), DATE_FORMAT));
        selectedHost.setResponseTime(responseTimeTextField.getText());
        selectedHost.setAcceptanceRate((Float) acceptanceRateTextField.getValue());
        selectedHost.setSuperHost(superHostCheckBox.isSelected());
    }

    private void updateFormValues(int selectedId) {
        selectedHost = this.hostDao.get(selectedId);
        idTextField.setValue(selectedHost.getId());
        nameTextField.setText(selectedHost.getName());
        responseTimeTextField.setText(selectedHost.getResponseTime());
        acceptanceRateTextField.setValue(selectedHost.getAcceptanceRate());
        superHostCheckBox.setSelected(selectedHost.isSuperHost());
        dateTextField.setText(selectedHost.getCreationDate().format(DATE_FORMAT));
    }

    private JPanel creationForm() {
        JPanel panel = new JPanel(new GridLayout(0, 2));

        panel.add(new JLabel("Entrez un id: "));
        idTextField = new JFormattedTextField(new NumberFormatter());
        panel.add(idTextField);

        panel.add(new JLabel("Entrez le nom: "));
        nameTextField = new JTextField(COLUMN_SIZE);
        panel.add(nameTextField);

        panel.add(new JLabel("Entrez la date de création: (MM/dd/YYYY) "));
        dateTextField = new JTextField(COLUMN_SIZE);
        panel.add(dateTextField);

        panel.add(new JLabel("Entrez le temps de réponse: "));
        responseTimeTextField = new JTextField(COLUMN_SIZE);
        panel.add(responseTimeTextField);

        panel.add(new JLabel("Entrez le taux d'acceptation: "));
        acceptanceRateTextField = new JFormattedTextField(new RateFormater());
        panel.add(acceptanceRateTextField);

        superHostCheckBox = new JCheckBox("Superhost");
        panel.add(superHostCheckBox);

        panel.add(updateButton());
        panel.add(deleteButton());
        panel.add(addButton());

        return panel;
    }

    private JButton updateButton() {
        JButton updateButton = new JButton("Modifier");
        updateButton.addActionListener(event -> {
            updateValues();
            this.hostDao.update(selectedHost);
            refreshTableValues();
            tableModel.fireTableDataChanged();
            table.setModel(tableModel);
        });
        return updateButton;
    }

    private JButton deleteButton() {
        JButton deleteButton = new JButton("Supprimer");
        deleteButton.addActionListener(event -> {
            this.hostDao.delete(selectedHost.getId());
            refreshTableValues();
            tableModel.fireTableDataChanged();
            table.setModel(tableModel);
        });
        return deleteButton;
    }

    private JButton addButton() {
        JButton addButton = new JButton("Ajouter");
        addButton.addActionListener(event -> {
            updateValues();
            this.hostDao.add(selectedHost);
            refreshTableValues();
            tableModel.fireTableDataChanged();
            table.setModel(tableModel);
        });
        return addButton;
    }

    private void refreshTableValues() {
        tableModel = new DefaultTableModel(Host.getColumnNames(), 0);
        for (Host host : this.hostDao.getAll()) {
            tableModel.addRow(host.toRowData());
        }
    }

    private JScrollPane hostTable() {
        refreshTableValues();
        table = new JTable(tableModel);
        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()){

                int selectedId = getSelectedId(table);
                updateFormValues(selectedId);

                System.out.println("Selected host ID: " + selectedId);

            }
        });
        return new JScrollPane(table);
    }

    private static int getSelectedId(JTable table) {
        try {
            return (int) table.getValueAt(table.getSelectedRow(), 0);
        } catch (ArrayIndexOutOfBoundsException e) {
            return (int) table.getValueAt(0, 0);
        }
    }

    public HostView() {
        super(TITLE);
        this.hostDao = new HostDao();

        add(hostTable(), BorderLayout.CENTER);
        add(creationForm(), BorderLayout.SOUTH);

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

}
