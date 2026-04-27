package db;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LibraryManagement extends JFrame {

    JTextField titleField, authorField, quantityField, studentField, bookIdField;
    JTextArea displayArea;

    public LibraryManagement() {
        setTitle("Library Management System");
        setSize(600, 500);
        setLayout(new FlowLayout());

        // Add Book
        add(new JLabel("Title:"));
        titleField = new JTextField(10);
        add(titleField);

        add(new JLabel("Author:"));
        authorField = new JTextField(10);
        add(authorField);

        add(new JLabel("Quantity:"));
        quantityField = new JTextField(5);
        add(quantityField);

        JButton addBtn = new JButton("Add Book");
        add(addBtn);

        // View Books
        JButton viewBtn = new JButton("View Books");
        add(viewBtn);

        // Issue Book
        add(new JLabel("Book ID:"));
        bookIdField = new JTextField(5);
        add(bookIdField);

        add(new JLabel("Student Name:"));
        studentField = new JTextField(10);
        add(studentField);

        JButton issueBtn = new JButton("Issue Book");
        add(issueBtn);

        displayArea = new JTextArea(15, 50);
        add(new JScrollPane(displayArea));

        // Actions
        addBtn.addActionListener(e -> addBook());
        viewBtn.addActionListener(e -> viewBooks());
        issueBtn.addActionListener(e -> issueBook());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    void addBook() {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO books(title, author, quantity) VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, titleField.getText());
            ps.setString(2, authorField.getText());
            ps.setInt(3, Integer.parseInt(quantityField.getText()));

            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Book Added!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void viewBooks() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM books");

            displayArea.setText("");
            while (rs.next()) {
                displayArea.append(
                    rs.getInt("id") + " | " +
                    rs.getString("title") + " | " +
                    rs.getString("author") + " | Qty: " +
                    rs.getInt("quantity") + "\n"
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void issueBook() {
        try {
            Connection con = DBConnection.getConnection();

            int bookId = Integer.parseInt(bookIdField.getText());

            // Check quantity
            PreparedStatement check = con.prepareStatement(
                "SELECT quantity FROM books WHERE id=?"
            );
            check.setInt(1, bookId);
            ResultSet rs = check.executeQuery();

            if (rs.next() && rs.getInt("quantity") > 0) {

                // Issue book
                PreparedStatement issue = con.prepareStatement(
                    "INSERT INTO issued_books(book_id, student_name) VALUES(?,?)"
                );
                issue.setInt(1, bookId);
                issue.setString(2, studentField.getText());
                issue.executeUpdate();

                // Reduce quantity
                PreparedStatement update = con.prepareStatement(
                    "UPDATE books SET quantity=quantity-1 WHERE id=?"
                );
                update.setInt(1, bookId);
                update.executeUpdate();

                JOptionPane.showMessageDialog(this, "Book Issued!");
            } else {
                JOptionPane.showMessageDialog(this, "Book not available!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LibraryManagement();
    }
}