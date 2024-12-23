import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RentalConsole {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(RentalConsoleFrame::new);
    }
}

class RentalConsoleFrame extends JFrame {
    public JComboBox<String> consoleBox;
    public JComboBox<String> typeBox;
    private JLabel consoleImage;
    public JTextField nameField;
    public JTextField daysField;
    public JCheckBox membershipBox;
    private JTextField priceField;
    public JTable rentalTable;
    public DefaultTableModel tableModel;
    private JPanel formPanel;

    public RentalConsoleFrame() {
        setTitle("Rental Console");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Rental Information"));
        formPanel.setBackground(Color.LIGHT_GRAY);

        formPanel.add(createLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(createLabel("Console:"));
        consoleBox = new JComboBox<>(new String[]{"Nintendo", "PlayStation", "Xbox"});
        consoleBox.addActionListener(e -> {
            updateTypeBox();
            updateBackground();
        });
        formPanel.add(consoleBox);

        formPanel.add(createLabel("Type:"));
        typeBox = new JComboBox<>();
        typeBox.addActionListener(e -> updateConsoleImage());
        formPanel.add(typeBox);

        formPanel.add(createLabel("Days:"));
        daysField = new JTextField();
        formPanel.add(daysField);

        formPanel.add(createLabel("Membership:"));
        membershipBox = new JCheckBox();
        formPanel.add(membershipBox);

        formPanel.add(createLabel("Price Total:"));
        priceField = new JTextField();
        priceField.setEditable(false);
        formPanel.add(priceField);

        add(formPanel, BorderLayout.WEST);

        consoleImage = new JLabel();
        consoleImage.setHorizontalAlignment(SwingConstants.CENTER);
        consoleImage.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5)); // Add border
        consoleImage.setPreferredSize(new Dimension(300, 300)); // Set fixed size
        consoleImage.setVerticalAlignment(SwingConstants.CENTER);
        consoleImage.setHorizontalAlignment(SwingConstants.CENTER);
        add(consoleImage, BorderLayout.CENTER);

        tableModel = new DefaultTableModel(new String[]{"Name", "Console", "Type", "Days", "Membership", "Price"}, 0);
        rentalTable = new JTable(tableModel);
        add(new JScrollPane(rentalTable), BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4, 10, 0));
        buttonPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> addRental());
        buttonPanel.add(addButton);

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(e -> updateRental());
        buttonPanel.add(updateButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> deleteRental());
        buttonPanel.add(deleteButton);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(e -> calculatePrice());
        buttonPanel.add(calculateButton);

        add(buttonPanel, BorderLayout.NORTH);

        updateTypeBox();
        updateBackground();

        setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    private void updateTypeBox() {
        String selectedConsole = (String) consoleBox.getSelectedItem();
        typeBox.removeAllItems();
        if ("Nintendo".equals(selectedConsole)) {
            typeBox.addItem("Nintendo 3DS");
            typeBox.addItem("Nintendo Switch");
        } else if ("PlayStation".equals(selectedConsole)) {
            typeBox.addItem("PlayStation 4");
            typeBox.addItem("PlayStation 5");
        } else if ("Xbox".equals(selectedConsole)) {
            typeBox.addItem("Xbox One");
            typeBox.addItem("Xbox Series X");
        }
    }

    private void updateConsoleImage() {
        String selectedType = (String) typeBox.getSelectedItem();
        if (selectedType == null) return;

        try {
            BufferedImage image = switch (selectedType) {
                case "Nintendo 3DS" -> ImageIO.read(new File("src/images/nintendo_3ds.png"));
                case "Nintendo Switch" -> ImageIO.read(new File("src/images/nintendo_switch.png"));
                case "PlayStation 4" -> ImageIO.read(new File("src/images/ps4.png"));
                case "PlayStation 5" -> ImageIO.read(new File("src/images/ps5.png"));
                case "Xbox One" -> ImageIO.read(new File("src/images/xbox_one.png"));
                case "Xbox Series X" -> ImageIO.read(new File("src/images/xbox_series_x.png"));
                default -> null;
            };

            if (image != null) {
                consoleImage.setIcon(new ImageIcon(image.getScaledInstance(300, 300, Image.SCALE_SMOOTH))); // Scale image
            } else {
                consoleImage.setIcon(null);
            }
        } catch (IOException e) {
            System.out.println("Error loading image: " + e.getMessage());
        }
    }

    private void updateBackground() {
        String selectedConsole = (String) consoleBox.getSelectedItem();
        if ("Nintendo".equals(selectedConsole)) {
            formPanel.setBackground(new Color(255, 204, 204)); // Light red gradient
        } else if ("PlayStation".equals(selectedConsole)) {
            formPanel.setBackground(new Color(204, 229, 255)); // Light blue gradient
        } else if ("Xbox".equals(selectedConsole)) {
            formPanel.setBackground(new Color(204, 255, 204)); // Light green gradient
        } else {
            formPanel.setBackground(Color.LIGHT_GRAY);
        }
    }

    public void addRental() {
        String name = nameField.getText();
        String console = (String) consoleBox.getSelectedItem();
        String type = (String) typeBox.getSelectedItem();
        String daysText = daysField.getText();
        boolean isMember = membershipBox.isSelected();

        if (name.isEmpty() || console == null || type == null || daysText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int days;
        try {
            days = Integer.parseInt(daysText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Days must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double price = calculatePrice(console, days, isMember);

        tableModel.addRow(new Object[]{name, console, type, days, isMember, price});
        clearFields();
    }

    public void updateRental() {
        int selectedRow = rentalTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a row to update.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String name = nameField.getText();
        String console = (String) consoleBox.getSelectedItem();
        String type = (String) typeBox.getSelectedItem();
        String daysText = daysField.getText();
        boolean isMember = membershipBox.isSelected();

        if (name.isEmpty() || console == null || type == null || daysText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int days;
        try {
            days = Integer.parseInt(daysText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Days must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double price = calculatePrice(console, days, isMember);

        tableModel.setValueAt(name, selectedRow, 0);
        tableModel.setValueAt(console, selectedRow, 1);
        tableModel.setValueAt(type, selectedRow, 2);
        tableModel.setValueAt(days, selectedRow, 3);
        tableModel.setValueAt(isMember, selectedRow, 4);
        tableModel.setValueAt(price, selectedRow, 5);

        clearFields();

    }

    public void deleteRental() {
        int selectedRow = rentalTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        tableModel.removeRow(selectedRow);
    }

    private void calculatePrice() {
        String console = (String) consoleBox.getSelectedItem();
        String daysText = daysField.getText();
        boolean isMember = membershipBox.isSelected();

        if (console == null || daysText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Console and Days must be selected.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int days;
        try {
            days = Integer.parseInt(daysText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Days must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double price = calculatePrice(console, days, isMember);
        priceField.setText(String.valueOf(price));
    }

    public double calculatePrice(String console, int days, boolean isMember) {
        double dailyRate = switch (console) {
            case "Nintendo" -> 10000;
            case "PlayStation" -> 20000;
            case "Xbox" -> 20000;
            default -> 0;
        };

        double total = dailyRate * days;
        if (isMember) {
            total *= 0.9; // 10% discount for members
        }

        return total;
    }

    private void clearFields() {
        nameField.setText("");
        daysField.setText("");
        membershipBox.setSelected(false);
        priceField.setText("");
    }
}