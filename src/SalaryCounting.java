import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SalaryCounting {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Salary Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);
        frame.setLayout(new BorderLayout());

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(44, 62, 80));
        headerPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        JLabel headerLabel = new JLabel("Salary Calculator");
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        headerLabel.setForeground(Color.BLACK);
        headerPanel.add(headerLabel);

        // Input Panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
        inputPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Margin antar elemen
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        JTextField nameField = new JTextField(20);
        inputPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(new JLabel("Position:"), gbc);

        gbc.gridx = 1;
        JTextField positionField = new JTextField(20);
        inputPanel.add(positionField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        inputPanel.add(new JLabel("Hours Worked:"), gbc);

        gbc.gridx = 1;
        JTextField hoursField = new JTextField(20);
        inputPanel.add(hoursField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        inputPanel.add(new JLabel("Hourly Rate:"), gbc);

        gbc.gridx = 1;
        JTextField rateField = new JTextField(20);
        inputPanel.add(rateField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        inputPanel.add(new JLabel("Permanent?"), gbc);

        gbc.gridx = 1;
        JCheckBox permanentCheckBox = new JCheckBox();
        inputPanel.add(permanentCheckBox, gbc);

        // Button
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        JButton addButton = new JButton("Add Employee");
        addButton.setBackground(new Color(41, 128, 185));
        addButton.setForeground(Color.BLACK);
        inputPanel.add(addButton, gbc);

        // Table Panel
        String[] columns = {"Name", "Position", "Hours", "Rate", "Permanent", "Salary"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Button Action
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText().trim(); // Ambil input dan hilangkan spasi ekstra
                    String position = positionField.getText().trim();

                    // Validasi nama dan posisi tidak boleh kosong
                    if (name.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Name cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (position.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Position cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int hours = Integer.parseInt(hoursField.getText());
                    double rate = Double.parseDouble(rateField.getText());
                    boolean permanent = permanentCheckBox.isSelected();

                    double salary = hours * rate;
                    if (permanent) salary += salary * 0.10;

                    // Tambahkan baris ke tabel
                    tableModel.addRow(new Object[]{
                            name, position, hours, rate, permanent ? "Yes" : "No", salary
                    });

                    // Clear input fields
                    nameField.setText("");
                    positionField.setText("");
                    hoursField.setText("");
                    rateField.setText("");
                    permanentCheckBox.setSelected(false);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid numbers for hours and rate.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        // Add Components to Frame
        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}