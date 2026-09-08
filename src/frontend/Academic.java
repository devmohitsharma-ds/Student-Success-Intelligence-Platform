package frontend;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Academic extends JPanel {

    private static final Color BG =
            new Color(245, 247, 250);

    private static final Color CARD =
            Color.WHITE;

    private static final Color BLUE =
            new Color(37, 99, 235);

    private static final Color GREEN =
            new Color(22, 163, 74);

    private static final Color ORANGE =
            new Color(234, 88, 12);

    private static final Color RED =
            new Color(220, 38, 38);

    private static final Color TEXT =
            new Color(31, 41, 55);

    private static final Color MUTED =
            new Color(107, 114, 128);

    private static final Color BORDER =
            new Color(229, 231, 235);

    private final int studentId;

    private JLabel cgpaValue;
    private JLabel backlogValue;
    private JLabel quizValue;
    private JLabel statusValue;

    private JTextField cgpaField;
    private JTextField backlogField;
    private JTextField quizField;

    public Academic(int studentId) {

        this.studentId = studentId;

        setBackground(BG);
        setLayout(new BorderLayout());

        createUI();
        loadAcademicData();
    }

    // =====================================
    // UI
    // =====================================

    private void createUI() {

        JPanel main = new JPanel();

        main.setBackground(BG);

        main.setLayout(
                new BoxLayout(
                        main,
                        BoxLayout.Y_AXIS
                )
        );

        main.setBorder(
                new EmptyBorder(
                        18,
                        18,
                        18,
                        18
                )
        );

        // =====================================
        // HEADER
        // =====================================

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setBackground(BLUE);

        header.setBorder(
                new EmptyBorder(
                        20,
                        22,
                        20,
                        22
                )
        );

        JLabel title =
                new JLabel(
                        "Academic Performance"
                );

        title.setForeground(Color.WHITE);

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        JLabel subtitle =
                new JLabel(
                        "Track and update your academic progress"
                );

        subtitle.setForeground(
                new Color(
                        219,
                        234,
                        254
                )
        );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        JPanel headerText =
                new JPanel();

        headerText.setOpaque(false);

        headerText.setLayout(
                new BoxLayout(
                        headerText,
                        BoxLayout.Y_AXIS
                )
        );

        headerText.add(title);

        headerText.add(
                Box.createVerticalStrut(5)
        );

        headerText.add(subtitle);

        header.add(
                headerText,
                BorderLayout.WEST
        );

        main.add(header);

        main.add(
                Box.createVerticalStrut(16)
        );

        // =====================================
        // KPI CARDS
        // =====================================

        JPanel cards =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                12,
                                0
                        )
                );

        cards.setBackground(BG);

        cgpaValue =
                new JLabel("...");

        backlogValue =
                new JLabel("...");

        quizValue =
                new JLabel("...");

        statusValue =
                new JLabel("...");

        cards.add(
                createMetricCard(
                        "Current CGPA",
                        cgpaValue,
                        BLUE
                )
        );

        cards.add(
                createMetricCard(
                        "Backlogs",
                        backlogValue,
                        RED
                )
        );

        cards.add(
                createMetricCard(
                        "Quiz Average",
                        quizValue,
                        ORANGE
                )
        );

        cards.add(
                createMetricCard(
                        "Academic Status",
                        statusValue,
                        GREEN
                )
        );

        cards.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        120
                )
        );

        main.add(cards);

        main.add(
                Box.createVerticalStrut(16)
        );

        // =====================================
        // UPDATE ACADEMIC RECORD
        // =====================================

        JPanel updatePanel =
                createSection(
                        "Update Academic Record"
                );

        JPanel form =
                new JPanel(
                        new GridBagLayout()
                );

        form.setBackground(CARD);

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

        JLabel cgpaLabel =
                new JLabel("CGPA:");

        JLabel backlogLabel =
                new JLabel("Backlogs:");

        JLabel quizLabel =
                new JLabel("Quiz Average:");

        cgpaField =
                new JTextField();

        backlogField =
                new JTextField();

        quizField =
                new JTextField();

        addFormRow(
                form,
                gbc,
                0,
                cgpaLabel,
                cgpaField
        );

        addFormRow(
                form,
                gbc,
                1,
                backlogLabel,
                backlogField
        );

        addFormRow(
                form,
                gbc,
                2,
                quizLabel,
                quizField
        );

        updatePanel.add(
                form,
                BorderLayout.CENTER
        );

        main.add(updatePanel);

        main.add(
                Box.createVerticalStrut(16)
        );

        // =====================================
        // ACTION BUTTONS
        // =====================================

        JPanel actionPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT
                        )
                );

        actionPanel.setBackground(BG);

        JButton updateButton =
                new JButton(
                        "Update Academic Record"
                );

        JButton refreshButton =
                new JButton(
                        "Refresh"
                );

        styleButton(
                updateButton,
                BLUE
        );

        styleButton(
                refreshButton,
                GREEN
        );

        updateButton.addActionListener(
                e -> updateAcademicData()
        );

        refreshButton.addActionListener(
                e -> loadAcademicData()
        );

        actionPanel.add(updateButton);
        actionPanel.add(refreshButton);

        main.add(actionPanel);

        main.add(
                Box.createVerticalStrut(10)
        );

        // =====================================
        // INFORMATION
        // =====================================

        JPanel information =
                createSection(
                        "Academic Guidelines"
                );

        JTextArea text =
                new JTextArea();

        text.setEditable(false);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);

        text.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        text.setForeground(TEXT);
        text.setBackground(CARD);

        text.setText(
                "• CGPA should normally be between 0 and 10.\n\n"
                        + "• Backlogs represent the number of pending subjects.\n\n"
                        + "• Quiz Average should be between 0 and 100.\n\n"
                        + "• Maintain a strong CGPA and minimize backlogs "
                        + "for better academic performance."
        );

        information.add(
                new JScrollPane(text),
                BorderLayout.CENTER
        );

        information.setPreferredSize(
                new Dimension(
                        0,
                        180
                )
        );

        main.add(information);

        JScrollPane scroll =
                new JScrollPane(main);

        scroll.setBorder(null);

        scroll.getVerticalScrollBar()
                .setUnitIncrement(16);

        add(
                scroll,
                BorderLayout.CENTER
        );
    }

    // =====================================
    // FORM ROW
    // =====================================

    private void addFormRow(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            JLabel label,
            JTextField field
    ) {

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.2;

        panel.add(
                label,
                gbc
        );

        gbc.gridx = 1;
        gbc.weightx = 0.8;

        field.setPreferredSize(
                new Dimension(
                        250,
                        35
                )
        );

        panel.add(
                field,
                gbc
        );
    }

    // =====================================
    // METRIC CARD
    // =====================================

    private JPanel createMetricCard(
            String title,
            JLabel value,
            Color accent
    ) {

        JPanel card =
                new JPanel();

        card.setBackground(CARD);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                15,
                                15,
                                15,
                                15
                        )
                )
        );

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setForeground(MUTED);

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        value.setForeground(accent);

        value.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        23
                )
        );

        card.add(titleLabel);

        card.add(
                Box.createVerticalStrut(10)
        );

        card.add(value);

        return card;
    }

    // =====================================
    // SECTION
    // =====================================

    private JPanel createSection(
            String title
    ) {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        panel.setBackground(CARD);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                18,
                                18,
                                18,
                                18
                        )
                )
        );

        JLabel heading =
                new JLabel(title);

        heading.setForeground(TEXT);

        heading.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        panel.add(
                heading,
                BorderLayout.NORTH
        );

        return panel;
    }

    // =====================================
    // BUTTON STYLE
    // =====================================

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
                        10,
                        18,
                        10,
                        18
                )
        );
    }

    // =====================================
    // LOAD ACADEMIC DATA
    // =====================================

    private void loadAcademicData() {

        cgpaValue.setText("...");
        backlogValue.setText("...");
        quizValue.setText("...");
        statusValue.setText("Loading...");

        SwingWorker<String, Void> worker =
                new SwingWorker<>() {

                    @Override
                    protected String doInBackground()
                            throws Exception {

                        return ApiClient.getAcademic(
                                studentId
                        );
                    }

                    @Override
                    protected void done() {

                        try {

                            String json = get();

                            updateValues(json);

                        } catch (Exception error) {

                            cgpaValue.setText("N/A");
                            backlogValue.setText("N/A");
                            quizValue.setText("N/A");
                            statusValue.setText("Error");

                            JOptionPane.showMessageDialog(
                                    Academic.this,
                                    "Could not load academic data.\n\n"
                                            + error.getMessage(),
                                    "Academic Data Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }
                };

        worker.execute();
    }

    // =====================================
    // UPDATE ACADEMIC DATA
    // =====================================

    private void updateAcademicData() {

        try {

            double cgpa =
                    Double.parseDouble(
                            cgpaField.getText().trim()
                    );

            int backlogs =
                    Integer.parseInt(
                            backlogField.getText().trim()
                    );

            double quiz =
                    Double.parseDouble(
                            quizField.getText().trim()
                    );

            if (cgpa < 0 || cgpa > 10) {

                JOptionPane.showMessageDialog(
                        this,
                        "CGPA must be between 0 and 10.",
                        "Invalid CGPA",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            if (backlogs < 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Backlogs cannot be negative.",
                        "Invalid Backlogs",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            if (quiz < 0 || quiz > 100) {

                JOptionPane.showMessageDialog(
                        this,
                        "Quiz Average must be between 0 and 100.",
                        "Invalid Quiz Average",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            String json =
                    "{"
                            + "\"cgpa\":" + cgpa + ","
                            + "\"backlogs\":" + backlogs + ","
                            + "\"quiz_average\":" + quiz
                            + "}";

            SwingWorker<String, Void> worker =
                    new SwingWorker<>() {

                        @Override
                        protected String doInBackground()
                                throws Exception {

                            return ApiClient.updateAcademic(
                                    studentId,
                                    json
                            );
                        }

                        @Override
                        protected void done() {

                            try {

                                get();

                                JOptionPane.showMessageDialog(
                                        Academic.this,
                                        "Academic record updated successfully.",
                                        "Success",
                                        JOptionPane.INFORMATION_MESSAGE
                                );

                                loadAcademicData();

                            } catch (Exception error) {

                                JOptionPane.showMessageDialog(
                                        Academic.this,
                                        "Could not update academic record.\n\n"
                                                + error.getMessage(),
                                        "Update Error",
                                        JOptionPane.ERROR_MESSAGE
                                );
                            }
                        }
                    };

            worker.execute();

        } catch (NumberFormatException error) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid numeric values.",
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    // =====================================
    // UPDATE DISPLAY VALUES
    // =====================================

    private void updateValues(
            String json
    ) {

        String cgpa =
                extractNumber(
                        json,
                        "cgpa"
                );

        String backlogs =
                extractNumber(
                        json,
                        "backlogs"
                );

        String quiz =
                extractNumber(
                        json,
                        "quiz_average"
                );

        String status =
                extractString(
                        json,
                        "status"
                );

        cgpaValue.setText(
                cgpa == null
                        ? "N/A"
                        : cgpa
        );

        backlogValue.setText(
                backlogs == null
                        ? "N/A"
                        : backlogs
        );

        quizValue.setText(
                quiz == null
                        ? "N/A"
                        : quiz
        );

        if (status == null) {

            status =
                    calculateStatus(
                            cgpa,
                            backlogs,
                            quiz
                    );
        }

        statusValue.setText(status);

        cgpaField.setText(
                cgpa == null
                        ? ""
                        : cgpa
        );

        backlogField.setText(
                backlogs == null
                        ? ""
                        : backlogs
        );

        quizField.setText(
                quiz == null
                        ? ""
                        : quiz
        );
    }

    // =====================================
    // STATUS
    // =====================================

    private String calculateStatus(
            String cgpa,
            String backlogs,
            String quiz
    ) {

        try {

            double c =
                    Double.parseDouble(cgpa);

            int b =
                    Integer.parseInt(backlogs);

            double q =
                    Double.parseDouble(quiz);

            if (c >= 7.5 && b == 0 && q >= 70) {
                return "Excellent";
            }

            if (c >= 6.0 && b <= 1 && q >= 50) {
                return "Good";
            }

            if (c >= 5.0) {
                return "Needs Improvement";
            }

            return "At Risk";

        } catch (Exception error) {

            return "Unknown";
        }
    }

    // =====================================
    // JSON NUMBER
    // =====================================

    private String extractNumber(
            String json,
            String key
    ) {

        if (json == null) {
            return null;
        }

        String pattern =
                "\"" + key + "\"\\s*:\\s*(-?\\d+(?:\\.\\d+)?)";

        java.util.regex.Pattern p =
                java.util.regex.Pattern.compile(
                        pattern
                );

        java.util.regex.Matcher m =
                p.matcher(json);

        if (m.find()) {
            return m.group(1);
        }

        return null;
    }

    // =====================================
    // JSON STRING
    // =====================================

    private String extractString(
            String json,
            String key
    ) {

        if (json == null) {
            return null;
        }

        String pattern =
                "\"" + key + "\"\\s*:\\s*\"([^\"]*)\"";

        java.util.regex.Pattern p =
                java.util.regex.Pattern.compile(
                        pattern
                );

        java.util.regex.Matcher m =
                p.matcher(json);

        if (m.find()) {
            return m.group(1);
        }

        return null;
    }
}