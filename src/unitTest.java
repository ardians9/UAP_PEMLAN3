import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RentalConsoleFrameTest {
    private RentalConsoleFrame rentalConsoleFrame;

    @BeforeEach
    void setUp() {
        // Initialize the frame for testing
        rentalConsoleFrame = new RentalConsoleFrame();
    }

    @Test
    void testCalculatePrice_NintendoWithMember() {
        double price = rentalConsoleFrame.calculatePrice("Nintendo", 5, true);
        assertEquals(45000, price, "Price should be discounted for members.");
    }

    @Test
    void testCalculatePrice_PlayStationWithoutMember() {
        double price = rentalConsoleFrame.calculatePrice("PlayStation", 3, false);
        assertEquals(60000, price, "Price should not include any discount.");
    }

    @Test
    void testCalculatePrice_InvalidConsole() {
        double price = rentalConsoleFrame.calculatePrice("Unknown", 3, false);
        assertEquals(0, price, "Price should be 0 for an unknown console.");
    }

    @Test
    void testAddRental() {
        rentalConsoleFrame.nameField.setText("John Doe");
        rentalConsoleFrame.consoleBox.setSelectedItem("Nintendo");
        rentalConsoleFrame.typeBox.setSelectedItem("Nintendo Switch");
        rentalConsoleFrame.daysField.setText("3");
        rentalConsoleFrame.membershipBox.setSelected(true);

        rentalConsoleFrame.addRental();

        assertEquals(1, rentalConsoleFrame.tableModel.getRowCount(), "Table should have one row after adding rental.");
        assertEquals("John Doe", rentalConsoleFrame.tableModel.getValueAt(0, 0), "Name should be John Doe.");
    }

    @Test
    void testUpdateRental() {
        // Simulate adding a rental
        rentalConsoleFrame.nameField.setText("John Doe");
        rentalConsoleFrame.consoleBox.setSelectedItem("Nintendo");
        rentalConsoleFrame.typeBox.setSelectedItem("Nintendo Switch");
        rentalConsoleFrame.daysField.setText("3");
        rentalConsoleFrame.membershipBox.setSelected(true);
        rentalConsoleFrame.addRental();

        // Simulate selecting the row to update
        rentalConsoleFrame.rentalTable.setRowSelectionInterval(0, 0);

        // Update rental details
        rentalConsoleFrame.nameField.setText("Jane Doe");
        rentalConsoleFrame.consoleBox.setSelectedItem("PlayStation");
        rentalConsoleFrame.typeBox.setSelectedItem("PlayStation 5");
        rentalConsoleFrame.daysField.setText("2");
        rentalConsoleFrame.membershipBox.setSelected(false);
        rentalConsoleFrame.updateRental();

        // Assertions
        assertEquals("Jane Doe", rentalConsoleFrame.tableModel.getValueAt(0, 0), "Name should be updated to Jane Doe.");
        assertEquals("PlayStation", rentalConsoleFrame.tableModel.getValueAt(0, 1), "Console should be updated to PlayStation.");
        assertEquals(2, rentalConsoleFrame.tableModel.getValueAt(0, 3), "Days should be updated to 2.");
    }

    @Test
    void testDeleteRental() {
        // Simulate adding a rental
        rentalConsoleFrame.nameField.setText("John Doe");
        rentalConsoleFrame.consoleBox.setSelectedItem("Nintendo");
        rentalConsoleFrame.typeBox.setSelectedItem("Nintendo Switch");
        rentalConsoleFrame.daysField.setText("3");
        rentalConsoleFrame.membershipBox.setSelected(true);
        rentalConsoleFrame.addRental();

        // Simulate selecting the row to delete
        rentalConsoleFrame.rentalTable.setRowSelectionInterval(0, 0);
        rentalConsoleFrame.deleteRental();

        // Assertions
        assertEquals(0, rentalConsoleFrame.tableModel.getRowCount(), "Table should have no rows after deletion.");
    }
}