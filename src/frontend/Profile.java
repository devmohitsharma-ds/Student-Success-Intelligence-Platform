package frontend;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Profile extends JPanel {

    private JTextField nameField;
    private JTextField idField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField departmentField;
    private JTextField semesterField;
    private JTextField cgpaField;

    private static final Color BG =
            new Color(245, 247, 250);

    private static final Color CARD =
            Color.WHITE;

    private static final Color BLUE =
            new Color(37, 99, 235);

    private static final Color GREEN =
            new Color(22, 163, 74);

    private static final Color TEXT =
            new Color(31, 41, 55);

    private static final Color MUTED =
            new Color(107, 114, 128);

    private static final Color BORDER =
            new Color(225, 228, 235);

    public Profile(int studentId) {

        setBackground(BG);

        setLayout(
                new BorderLayout(16, 16)
        );

        setBorder(
                new EmptyBorder(
                        18,
                        18,
                        18,
                        18
                )
        );

        createUI(studentId);
    }

    private void createUI(int studentId) {

        JPanel content =
                new JPanel(
                        new BorderLayout(16, 16)
                );

        content.setBackground(BG);

        // ================= PROFILE CARD =================

        JPanel profileCard =
                new JPanel();

        profileCard.setBackground(CARD);

        profileCard.setPreferredSize(
                new Dimension(300, 500)
        );

        profileCard.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                20,
                                20,
                                20,
                                20
                        )
                )
        );

        profileCard.setLayout(
                new BoxLayout(
                        profileCard,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel avatar =
                new JLabel("HR");

        avatar.setOpaque(true);

        avatar.setBackground(BLUE);

        avatar.setForeground(Color.WHITE);

        avatar.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        34
                )
        );

        avatar.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        avatar.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        avatar.setPreferredSize(
                new Dimension(100, 100)
        );

        avatar.setMaximumSize(
                new Dimension(100, 100)
        );

        profileCard.add(avatar);

        profileCard.add(
                Box.createVerticalStrut(18)
        );

        JLabel name =
                new JLabel(
                        "Student Profile"
                );

        name.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        21
                )
        );

        name.setForeground(TEXT);

        name.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        profileCard.add(name);

        profileCard.add(
                Box.createVerticalStrut(6)
        );

        JLabel role =
                new JLabel(
                        "B.Tech CSE - Data Science"
                );

        role.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        role.setForeground(MUTED);

        role.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        profileCard.add(role);

        profileCard.add(
                Box.createVerticalStrut(12)
        );

        JLabel status =
                new JLabel(
                        "● Active Student"
                );

        status.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        status.setForeground(GREEN);

        status.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        profileCard.add(status);

        profileCard.add(
                Box.createVerticalStrut(30)
        );

        addProfileStat(
                profileCard,
                "Student ID",
                String.valueOf(studentId)
        );

        addProfileStat(
                profileCard,
                "Semester",
                "3"
        );

        addProfileStat(
                profileCard,
                "Department",
                "CSE Data Science"
        );

        content.add(
                profileCard,
                BorderLayout.WEST
        );

        // ================= DETAILS =================

        JPanel detailsPanel =
                new JPanel();

        detailsPanel.setBackground(CARD);

        detailsPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                22,
                                25,
                                22,
                                25
                        )
                )
        );

        detailsPanel.setLayout(
                new GridBagLayout()
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        8,
                        8,
                        8,
                        8
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1.0;

        JLabel title =
                new JLabel(
                        "Personal & Academic Details"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        21
                )
        );

        title.setForeground(TEXT);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        detailsPanel.add(
                title,
                gbc
        );

        gbc.gridwidth = 1;

        // Full Name

        addFormLabel(
                detailsPanel,
                "Full Name",
                gbc,
                0,
                1
        );

        nameField =
                addTextField(
                        detailsPanel,
                        "Backend Test Student",
                        gbc,
                        1,
                        1
                );

        // Student ID

        addFormLabel(
                detailsPanel,
                "Student ID",
                gbc,
                0,
                2
        );

        idField =
                addTextField(
                        detailsPanel,
                        String.valueOf(studentId),
                        gbc,
                        1,
                        2
                );

        // Email

        addFormLabel(
                detailsPanel,
                "Email",
                gbc,
                0,
                3
        );

        emailField =
                addTextField(
                        detailsPanel,
                        "student@example.com",
                        gbc,
                        1,
                        3
                );

        // Phone

        addFormLabel(
                detailsPanel,
                "Phone",
                gbc,
                0,
                4
        );

        phoneField =
                addTextField(
                        detailsPanel,
                        "+91 XXXXX XXXXX",
                        gbc,
                        1,
                        4
                );

        // Department

        addFormLabel(
                detailsPanel,
                "Department",
                gbc,
                0,
                5
        );

        departmentField =
                addTextField(
                        detailsPanel,
                        "CSE Data Science",
                        gbc,
                        1,
                        5
                );

        // Semester

        addFormLabel(
                detailsPanel,
                "Semester",
                gbc,
                0,
                6
        );

        semesterField =
                addTextField(
                        detailsPanel,
                        "3",
                        gbc,
                        1,
                        6
                );

        // CGPA

        addFormLabel(
                detailsPanel,
                "Current CGPA",
                gbc,
                0,
                7
        );

        cgpaField =
                addTextField(
                        detailsPanel,
                        "3.2",
                        gbc,
                        1,
                        7
                );

        // ================= BUTTONS =================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT
                        )
                );

        buttonPanel.setBackground(CARD);

        JButton editButton =
                new JButton(
                        "Edit Profile"
                );

        JButton saveButton =
                new JButton(
                        "Save Changes"
                );

        JButton passwordButton =
                new JButton(
                        "Change Password"
                );

        styleButton(
                editButton,
                BLUE
        );

        styleButton(
                saveButton,
                GREEN
        );

        buttonPanel.add(editButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(passwordButton);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;

        detailsPanel.add(
                buttonPanel,
                gbc
        );

        content.add(
                detailsPanel,
                BorderLayout.CENTER
        );

        add(
                content,
                BorderLayout.CENTER
        );

        // ================= BUTTON ACTIONS =================

        editButton.addActionListener(
                e -> {

                    nameField.setEditable(true);
                    emailField.setEditable(true);
                    phoneField.setEditable(true);
                    departmentField.setEditable(true);

                    JOptionPane.showMessageDialog(
                            this,
                            "Profile editing enabled."
                    );
                }
        );

        saveButton.addActionListener(
                e -> {

                    JOptionPane.showMessageDialog(
                            this,
                            "Profile updated successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
        );

        passwordButton.addActionListener(
                e -> {

                    JOptionPane.showMessageDialog(
                            this,
                            "Password change screen will be connected later.",
                            "Change Password",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
        );
    }

    private void addProfileStat(
            JPanel panel,
            String title,
            String value
    ) {

        JPanel row =
                new JPanel(
                        new BorderLayout()
                );

        row.setBackground(CARD);

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setForeground(MUTED);

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        JLabel valueLabel =
                new JLabel(value);

        valueLabel.setForeground(BLUE);

        valueLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        row.add(
                titleLabel,
                BorderLayout.WEST
        );

        row.add(
                valueLabel,
                BorderLayout.EAST
        );

        row.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        35
                )
        );

        panel.add(row);

        panel.add(
                Box.createVerticalStrut(8)
        );
    }

    private void addFormLabel(
            JPanel panel,
            String text,
            GridBagConstraints gbc,
            int x,
            int y
    ) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        label.setForeground(TEXT);

        gbc.gridx = x;
        gbc.gridy = y;

        panel.add(
                label,
                gbc
        );
    }

    private JTextField addTextField(
            JPanel panel,
            String text,
            GridBagConstraints gbc,
            int x,
            int y
    ) {

        JTextField field =
                new JTextField(text);

        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        field.setEditable(false);

        gbc.gridx = x;
        gbc.gridy = y;

        panel.add(
                field,
                gbc
        );

        return field;
    }

    private void styleButton(
            JButton button,
            Color color
    ) {

        button.setBackground(color);

        button.setForeground(Color.WHITE);

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        button.setFocusPainted(false);

        button.setBorder(
                new EmptyBorder(
                        9,
                        15,
                        9,
                        15
                )
        );
    }
}