package com.mycompany.productappgui;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ProductApp extends JFrame {
    private final JTextField txtCustomerName;
    private final JComboBox<String> cmbProduct;
    private final JTextField txtPrice;
    private final JButton btnProcess;

    public ProductApp() {
        setTitle("Product App");
        setSize(420, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem processItem = new JMenuItem("Process");
        JMenuItem clearItem = new JMenuItem("Clear");

        processItem.addActionListener(e -> processSale());
        clearItem.addActionListener(e -> clearFields());

        toolsMenu.add(processItem);
        toolsMenu.add(clearItem);

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        setJMenuBar(menuBar);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        panel.add(new JLabel("Customer Name:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        txtCustomerName = new JTextField(15);
        panel.add(txtCustomerName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        panel.add(new JLabel("Select Product:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        String[] products = {
            "Cozy Nights Monarch",
            "Restonic Barbados",
            "Sealy Phuket"
        };
        cmbProduct = new JComboBox<>(products);
        panel.add(cmbProduct, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        panel.add(new JLabel("Enter Price:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        txtPrice = new JTextField(15);
        panel.add(txtPrice, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        btnProcess = new JButton("PROCESS");
        btnProcess.addActionListener(e -> processSale());
        panel.add(btnProcess, gbc);

        add(panel);
    }

    private void processSale() {
        String customerName = txtCustomerName.getText().trim();
        String productName = (String) cmbProduct.getSelectedItem();
        String priceText = txtPrice.getText().trim();

        if (customerName.isEmpty() || priceText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both customer name and price.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric price.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ProductCalculations calc = new ProductCalculations(price);
        double vat = calc.GetVat();
        double commission = calc.GetCommission();
        double total = calc.GetTotal();

        
        String summary = "Customer Name: " + customerName + "\n" +
                         "Product: " + productName + "\n" +
                         "VAT: R " + vat + "\n" +
                         "COMMISSION: R " + commission + "\n" +
                         "TOTAL: R " + total;

        JOptionPane.showMessageDialog(this, summary, "Message", JOptionPane.INFORMATION_MESSAGE);

        
        saveToFile(summary);
    }

    private void saveToFile(String summary) {
        try (PrintWriter out = new PrintWriter(new FileWriter("data.txt", true))) {
            out.println("DATA LOG");
            out.println("***************************************");
            out.println(summary);
            out.println("***************************************");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving to data.txt: " + ex.getMessage(),
                    "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    private void clearFields() {
        txtCustomerName.setText("");
        txtPrice.setText("");
        cmbProduct.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ProductApp().setVisible(true);
        });
    }
}