package frontend;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentManagement extends JPanel {

    private JTextField idField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField searchField;
    private JTextField admissionYearField;

    private JComboBox<String> genderBox;
    private JComboBox<String> departmentBox;
    private JComboBox<String> semesterBox;

    private JTable studentTable;
    private DefaultTableModel tableModel;

    private JLabel statusLabel;

    public StudentManagement(int studentId) {

        setLayout(new BorderLayout(20, 20));

        setBackground(
                new Color(
                        245,
                        247,
                        250
                )
        );

        setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        30,
                        25,
                        30
                )
        );

        // =====================================================
        // HEADER
        // =====================================================

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setOpaque(false);

        JPanel titlePanel =
                new JPanel();

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        titlePanel.setOpaque(false);

        JLabel title =
                new JLabel(
                        "Student Management"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        JLabel subtitle =
                new JLabel(
                        "Add, update, search and manage student records."
                );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        titlePanel.add(title);

        titlePanel.add(
                Box.createVerticalStrut(5)
        );

        titlePanel.add(subtitle);

        header.add(
                titlePanel,
                BorderLayout.WEST
        );

        // =====================================================
        // SEARCH
        // =====================================================

        JPanel searchPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        searchPanel.setOpaque(false);

        searchField =
                new JTextField(18);

        JButton searchButton =
                new JButton("Search");

        JButton refreshButton =
                new JButton("Refresh");

        searchButton.setFocusPainted(false);
        refreshButton.setFocusPainted(false);

        searchPanel.add(
                new JLabel("Search:")
        );

        searchPanel.add(
                searchField
        );

        searchPanel.add(
                searchButton
        );

        searchPanel.add(
                refreshButton
        );

        header.add(
                searchPanel,
                BorderLayout.EAST
        );

        // =====================================================
        // FORM
        // =====================================================

        JPanel formPanel =
                new JPanel();

        formPanel.setLayout(
                new BoxLayout(
                        formPanel,
                        BoxLayout.Y_AXIS
                )
        );

        formPanel.setBackground(
                Color.WHITE
        );

        formPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        220,
                                        225,
                                        230
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                18,
                                18,
                                18,
                                18
                        )
                )
        );

        formPanel.setPreferredSize(
                new Dimension(
                        330,
                        500
                )
        );

        JLabel formTitle =
                new JLabel(
                        "Student Information"
                );

        formTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        formPanel.add(formTitle);

        formPanel.add(
                Box.createVerticalStrut(15)
        );

        // Student ID

        idField =
                new JTextField();

        idField.setEditable(false);

        formPanel.add(
                createField(
                        "Student ID",
                        idField
                )
        );

        // Full name

        nameField =
                new JTextField();

        formPanel.add(
                createField(
                        "Full Name",
                        nameField
                )
        );

        // Gender

        genderBox =
                new JComboBox<>(
                        new String[]{
                                "Male",
                                "Female",
                                "Other"
                        }
                );

        formPanel.add(
                createComboField(
                        "Gender",
                        genderBox
                )
        );

        // Department

        departmentBox =
                new JComboBox<>(
                        new String[]{
                                "CSE Data Science",
                                "Computer Science",
                                "Information Technology",
                                "Artificial Intelligence",
                                "Electronics",
                                "Mechanical",
                                "Civil",
                                "Other"
                        }
                );

        formPanel.add(
                createComboField(
                        "Department",
                        departmentBox
                )
        );

        // Semester

        semesterBox =
                new JComboBox<>(
                        new String[]{
                                "1",
                                "2",
                                "3",
                                "4",
                                "5",
                                "6",
                                "7",
                                "8"
                        }
                );

        formPanel.add(
                createComboField(
                        "Semester",
                        semesterBox
                )
        );

        // Email

        emailField =
                new JTextField();

        formPanel.add(
                createField(
                        "Email",
                        emailField
                )
        );

        // Phone

        phoneField =
                new JTextField();

        formPanel.add(
                createField(
                        "Phone",
                        phoneField
                )
        );

        // Admission Year

        admissionYearField =
                new JTextField();

        formPanel.add(
                createField(
                        "Admission Year",
                        admissionYearField
                )
        );

        formPanel.add(
                Box.createVerticalStrut(10)
        );

        // =====================================================
        // BUTTONS
        // =====================================================

        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                2,
                                8,
                                8
                        )
                );

        buttonPanel.setOpaque(false);

        JButton addButton =
                new JButton(
                        "Add Student"
                );

        JButton updateButton =
                new JButton(
                        "Update"
                );

        JButton deleteButton =
                new JButton(
                        "Delete"
                );

        JButton clearButton =
                new JButton(
                        "Clear"
                );

        addButton.setFocusPainted(false);
        updateButton.setFocusPainted(false);
        deleteButton.setFocusPainted(false);
        clearButton.setFocusPainted(false);

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        formPanel.add(
                buttonPanel
        );

        // =====================================================
        // TABLE
        // =====================================================

        String[] columns = {
                "ID",
                "Name",
                "Gender",
                "Department",
                "Semester",
                "Email",
                "Phone",
                "Admission Year"
        };

        tableModel =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {
                        return false;
                    }
                };

        studentTable =
                new JTable(
                        tableModel
                );

        studentTable.setRowHeight(30);

        studentTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        studentTable.getTableHeader()
                .setReorderingAllowed(false);

        JScrollPane tableScrollPane =
                new JScrollPane(
                        studentTable
                );

        tableScrollPane.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                220,
                                225,
                                230
                        )
                )
        );

        // =====================================================
        // TABLE PANEL
        // =====================================================

        JPanel tablePanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        tablePanel.setOpaque(false);

        JLabel tableTitle =
                new JLabel(
                        "Student Records"
                );

        tableTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        tablePanel.add(
                tableTitle,
                BorderLayout.NORTH
        );

        tablePanel.add(
                tableScrollPane,
                BorderLayout.CENTER
        );

        // =====================================================
        // CENTER
        // =====================================================

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(
                                15,
                                15
                        )
                );

        centerPanel.setOpaque(false);

        centerPanel.add(
                formPanel,
                BorderLayout.WEST
        );

        centerPanel.add(
                tablePanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // FOOTER
        // =====================================================

        statusLabel =
                new JLabel(
                        "Ready"
                );

        statusLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        // =====================================================
        // ADD TO PAGE
        // =====================================================

        add(
                header,
                BorderLayout.NORTH
        );

        add(
                centerPanel,
                BorderLayout.CENTER
        );

        add(
                statusLabel,
                BorderLayout.SOUTH
        );

        // =====================================================
        // ACTIONS
        // =====================================================

        addButton.addActionListener(
                e -> addStudent()
        );

        updateButton.addActionListener(
                e -> updateStudent()
        );

        deleteButton.addActionListener(
                e -> deleteStudent()
        );

        clearButton.addActionListener(
                e -> clearForm()
        );

        refreshButton.addActionListener(
                e -> loadStudents()
        );

        searchButton.addActionListener(
                e -> searchStudents()
        );

        searchField.addActionListener(
                e -> searchStudents()
        );

        studentTable.getSelectionModel()
                .addListSelectionListener(
                        e -> {

                            if (!e.getValueIsAdjusting()) {

                                loadSelectedStudent();
                            }
                        }
                );

        // =====================================================
        // INITIAL LOAD
        // =====================================================

        loadStudents();
    }

    // =====================================================
    // LOAD STUDENTS
    // =====================================================

    private void loadStudents() {

        statusLabel.setText(
                "Loading students..."
        );

        new Thread(() -> {

            try {

                String response =
                        ApiClient.getStudents();

                ArrayList<String[]> students =
                        parseStudents(
                                response
                        );

                SwingUtilities.invokeLater(() -> {

                    tableModel.setRowCount(0);

                    for (String[] student :
                            students) {

                        tableModel.addRow(
                                student
                        );
                    }

                    statusLabel.setText(
                            students.size()
                                    + " student(s) loaded"
                    );
                });

            } catch (Exception ex) {

                SwingUtilities.invokeLater(() -> {

                    statusLabel.setText(
                            "Failed to load students"
                    );

                    JOptionPane.showMessageDialog(
                            this,
                            "Could not load students.\n\n"
                                    + ex.getMessage(),
                            "Student Management Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                });
            }

        }).start();
    }

    // =====================================================
    // ADD STUDENT
    // =====================================================

    private void addStudent() {

        if (!validateForm()) {
            return;
        }

        String json =
                buildJson();

        new Thread(() -> {

            try {

                String response =
                        ApiClient.createStudent(
                                json
                        );

                SwingUtilities.invokeLater(() -> {

                    JOptionPane.showMessageDialog(
                            this,
                            "Student added successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    clearForm();

                    loadStudents();
                });

            } catch (Exception ex) {

                SwingUtilities.invokeLater(() -> {

                    JOptionPane.showMessageDialog(
                            this,
                            "Could not add student.\n\n"
                                    + ex.getMessage(),
                            "Add Student Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                });
            }

        }).start();
    }

    // =====================================================
    // UPDATE STUDENT
    // =====================================================

    private void updateStudent() {

        if (idField.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Select a student from the table first.",
                    "No Student Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (!validateForm()) {
            return;
        }

        int id;

        try {

            id =
                    Integer.parseInt(
                            idField.getText().trim()
                    );

        } catch (NumberFormatException ex) {

            showError(
                    "Invalid student ID."
            );

            return;
        }

        String json =
                buildJson();

        final int studentId = id;

        new Thread(() -> {

            try {

                ApiClient.updateStudent(
                        studentId,
                        json
                );

                SwingUtilities.invokeLater(() -> {

                    JOptionPane.showMessageDialog(
                            this,
                            "Student updated successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    loadStudents();
                });

            } catch (Exception ex) {

                SwingUtilities.invokeLater(() -> {

                    JOptionPane.showMessageDialog(
                            this,
                            "Could not update student.\n\n"
                                    + ex.getMessage(),
                            "Update Student Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                });
            }

        }).start();
    }

    // =====================================================
    // DELETE STUDENT
    // =====================================================

    private void deleteStudent() {

        if (idField.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Select a student from the table first.",
                    "No Student Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int id;

        try {

            id =
                    Integer.parseInt(
                            idField.getText().trim()
                    );

        } catch (NumberFormatException ex) {

            showError(
                    "Invalid student ID."
            );

            return;
        }

        int confirm =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete student "
                                + id
                                + "?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

        if (
                confirm
                        != JOptionPane.YES_OPTION
        ) {

            return;
        }

        final int studentId = id;

        new Thread(() -> {

            try {

                ApiClient.deleteStudent(
                        studentId
                );

                SwingUtilities.invokeLater(() -> {

                    JOptionPane.showMessageDialog(
                            this,
                            "Student deleted successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    clearForm();

                    loadStudents();
                });

            } catch (Exception ex) {

                SwingUtilities.invokeLater(() -> {

                    JOptionPane.showMessageDialog(
                            this,
                            "Could not delete student.\n\n"
                                    + ex.getMessage(),
                            "Delete Student Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                });
            }

        }).start();
    }

    // =====================================================
    // LOAD SELECTED STUDENT
    // =====================================================

    private void loadSelectedStudent() {

        int row =
                studentTable.getSelectedRow();

        if (row < 0) {
            return;
        }

        idField.setText(
                valueAt(row, 0)
        );

        nameField.setText(
                valueAt(row, 1)
        );

        genderBox.setSelectedItem(
                valueAt(row, 2)
        );

        departmentBox.setSelectedItem(
                valueAt(row, 3)
        );

        semesterBox.setSelectedItem(
                valueAt(row, 4)
        );

        emailField.setText(
                valueAt(row, 5)
        );

        phoneField.setText(
                valueAt(row, 6)
        );

        admissionYearField.setText(
                valueAt(row, 7)
        );
    }

    // =====================================================
    // SEARCH
    // =====================================================

    private void searchStudents() {

        String query =
                searchField.getText()
                        .trim()
                        .toLowerCase();

        if (query.isEmpty()) {

            loadStudents();

            return;
        }

        for (
                int row = tableModel.getRowCount() - 1;
                row >= 0;
                row--
        ) {

            boolean match = false;

            for (
                    int column = 0;
                    column < tableModel.getColumnCount();
                    column++
            ) {

                Object value =
                        tableModel.getValueAt(
                                row,
                                column
                        );

                if (
                        value != null
                                && value.toString()
                                .toLowerCase()
                                .contains(query)
                ) {

                    match = true;

                    break;
                }
            }

            if (!match) {

                tableModel.removeRow(
                        row
                );
            }
        }

        statusLabel.setText(
                tableModel.getRowCount()
                        + " matching student(s)"
        );
    }

    // =====================================================
    // VALIDATE FORM
    // =====================================================

    private boolean validateForm() {

        String name =
                nameField.getText().trim();

        String email =
                emailField.getText().trim();

        String phone =
                phoneField.getText().trim();

        String year =
                admissionYearField
                        .getText()
                        .trim();

        if (name.isEmpty()) {

            showError(
                    "Full name is required."
            );

            return false;
        }

        if (email.isEmpty()) {

            showError(
                    "Email is required."
            );

            return false;
        }

        if (phone.isEmpty()) {

            showError(
                    "Phone number is required."
            );

            return false;
        }

        if (year.isEmpty()) {

            showError(
                    "Admission year is required."
            );

            return false;
        }

        try {

            int admissionYear =
                    Integer.parseInt(
                            year
                    );

            if (
                    admissionYear < 1900
                            || admissionYear > 2100
            ) {

                showError(
                        "Enter a valid admission year."
                );

                return false;
            }

        } catch (NumberFormatException ex) {

            showError(
                    "Admission year must be a number."
            );

            return false;
        }

        return true;
    }

    // =====================================================
    // BUILD JSON
    // =====================================================

    private String buildJson() {

        return "{"
                + "\"full_name\":\""
                + escapeJson(
                        nameField.getText().trim()
                )
                + "\","

                + "\"gender\":\""
                + escapeJson(
                        genderBox.getSelectedItem()
                                .toString()
                )
                + "\","

                + "\"department\":\""
                + escapeJson(
                        departmentBox.getSelectedItem()
                                .toString()
                )
                + "\","

                + "\"semester\":"
                + Integer.parseInt(
                        semesterBox.getSelectedItem()
                                .toString()
                )
                + ","

                + "\"email\":\""
                + escapeJson(
                        emailField.getText().trim()
                )
                + "\","

                + "\"phone\":\""
                + escapeJson(
                        phoneField.getText().trim()
                )
                + "\","

                + "\"admission_year\":"
                + Integer.parseInt(
                        admissionYearField
                                .getText()
                                .trim()
                )

                + "}";
    }

    // =====================================================
    // PARSE STUDENTS
    // =====================================================

    private ArrayList<String[]> parseStudents(
            String json
    ) {

        ArrayList<String[]> result =
                new ArrayList<>();

        Pattern objectPattern =
                Pattern.compile(
                        "\\{([^{}]*)\\}"
                );

        Matcher objectMatcher =
                objectPattern.matcher(
                        json
                );

        while (objectMatcher.find()) {

            String object =
                    objectMatcher.group(1);

            String id =
                    getJsonValue(
                            object,
                            "student_id"
                    );

            String name =
                    getJsonValue(
                            object,
                            "full_name"
                    );

            String gender =
                    getJsonValue(
                            object,
                            "gender"
                    );

            String department =
                    getJsonValue(
                            object,
                            "department"
                    );

            String semester =
                    getJsonValue(
                            object,
                            "semester"
                    );

            String email =
                    getJsonValue(
                            object,
                            "email"
                    );

            String phone =
                    getJsonValue(
                            object,
                            "phone"
                    );

            String admissionYear =
                    getJsonValue(
                            object,
                            "admission_year"
                    );

            if (
                    !id.isEmpty()
                            && !name.isEmpty()
            ) {

                result.add(
                        new String[]{
                                id,
                                name,
                                gender,
                                department,
                                semester,
                                email,
                                phone,
                                admissionYear
                        }
                );
            }
        }

        return result;
    }

    // =====================================================
    // JSON VALUE
    // =====================================================

    private String getJsonValue(
            String json,
            String key
    ) {

        Pattern stringPattern =
                Pattern.compile(
                        "\"" +
                                Pattern.quote(key) +
                                "\"\\s*:\\s*\"((?:\\\\.|[^\"\\\\])*)\""
                );

        Matcher stringMatcher =
                stringPattern.matcher(
                        json
                );

        if (stringMatcher.find()) {

            return cleanJson(
                    stringMatcher.group(1)
            );
        }

        Pattern numberPattern =
                Pattern.compile(
                        "\"" +
                                Pattern.quote(key) +
                                "\"\\s*:\\s*(-?\\d+(?:\\.\\d+)?)"
                );

        Matcher numberMatcher =
                numberPattern.matcher(
                        json
                );

        if (numberMatcher.find()) {

            return numberMatcher.group(1);
        }

        return "";
    }

    // =====================================================
    // CLEAN JSON
    // =====================================================

    private String cleanJson(
            String text
    ) {

        return text
                .replace("\\\"", "\"")
                .replace("\\n", "\n")
                .replace("\\r", "\r")
                .replace("\\t", "\t")
                .trim();
    }

    // =====================================================
    // ESCAPE JSON
    // =====================================================

    private String escapeJson(
            String text
    ) {

        return text
                .replace("\\", "\\\\")
                .replace("\"", "\\\"");
    }

    // =====================================================
    // CLEAR FORM
    // =====================================================

    private void clearForm() {

        idField.setText("");

        nameField.setText("");

        emailField.setText("");

        phoneField.setText("");

        admissionYearField.setText("");

        genderBox.setSelectedIndex(0);

        departmentBox.setSelectedIndex(0);

        semesterBox.setSelectedIndex(0);

        studentTable.clearSelection();

        statusLabel.setText(
                "Form cleared"
        );
    }

    // =====================================================
    // UI HELPERS
    // =====================================================

    private JPanel createField(
            String labelText,
            JTextField field
    ) {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                8,
                                5
                        )
                );

        panel.setOpaque(false);

        panel.setMaximumSize(
                new Dimension(
                        300,
                        42
                )
        );

        JLabel label =
                new JLabel(
                        labelText
                );

        label.setPreferredSize(
                new Dimension(
                        105,
                        30
                )
        );

        panel.add(
                label,
                BorderLayout.WEST
        );

        panel.add(
                field,
                BorderLayout.CENTER
        );

        return panel;
    }

    private JPanel createComboField(
            String labelText,
            JComboBox<String> combo
    ) {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                8,
                                5
                        )
                );

        panel.setOpaque(false);

        panel.setMaximumSize(
                new Dimension(
                        300,
                        42
                )
        );

        JLabel label =
                new JLabel(
                        labelText
                );

        label.setPreferredSize(
                new Dimension(
                        105,
                        30
                )
        );

        panel.add(
                label,
                BorderLayout.WEST
        );

        panel.add(
                combo,
                BorderLayout.CENTER
        );

        return panel;
    }

    private String valueAt(
            int row,
            int column
    ) {

        Object value =
                tableModel.getValueAt(
                        row,
                        column
                );

        return value == null
                ? ""
                : value.toString();
    }

    private void showError(
            String message
    ) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Invalid Input",
                JOptionPane.WARNING_MESSAGE
        );
    }
}