package frontend;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Attendance extends JPanel {

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

    private JLabel attendanceValue;
    private JLabel statusValue;
    private JLabel messageLabel;

    private JTextField attendanceField;

    private JButton updateButton;
    private JButton refreshButton;

    public Attendance(int studentId) {

        this.studentId = studentId;

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

        createUI();

        loadAttendance();
    }

    private void createUI() {

        JPanel main =
                new JPanel();

        main.setBackground(BG);

        main.setLayout(
                new BoxLayout(
                        main,
                        BoxLayout.Y_AXIS
                )
        );

        // ================= HEADER =================

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

        JPanel headerText =
                new JPanel();

        headerText.setOpaque(false);

        headerText.setLayout(
                new BoxLayout(
                        headerText,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel title =
                new JLabel(
                        "Attendance Management"
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
                        "Monitor and update student attendance"
                );

        subtitle.setForeground(
                new Color(219, 234, 254)
        );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
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

        // ================= ATTENDANCE CARD =================

        JPanel attendanceCard =
                new JPanel();

        attendanceCard.setBackground(CARD);

        attendanceCard.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                25,
                                25,
                                25,
                                25
                        )
                )
        );

        attendanceCard.setLayout(
                new BoxLayout(
                        attendanceCard,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel cardTitle =
                new JLabel(
                        "Current Attendance"
                );

        cardTitle.setForeground(TEXT);

        cardTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        19
                )
        );

        cardTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        attendanceCard.add(cardTitle);

        attendanceCard.add(
                Box.createVerticalStrut(15)
        );

        attendanceValue =
                new JLabel("...");

        attendanceValue.setForeground(BLUE);

        attendanceValue.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        42
                )
        );

        attendanceValue.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        attendanceCard.add(
                attendanceValue
        );

        statusValue =
                new JLabel(
                        "Loading..."
                );

        statusValue.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15
                )
        );

        statusValue.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        attendanceCard.add(
                Box.createVerticalStrut(5)
        );

        attendanceCard.add(
                statusValue
        );

        messageLabel =
                new JLabel(
                        " "
                );

        messageLabel.setForeground(MUTED);

        messageLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        messageLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        attendanceCard.add(
                Box.createVerticalStrut(8)
        );

        attendanceCard.add(
                messageLabel
        );

        main.add(attendanceCard);

        main.add(
                Box.createVerticalStrut(16)
        );

        // ================= UPDATE PANEL =================

        JPanel updatePanel =
                new JPanel();

        updatePanel.setBackground(CARD);

        updatePanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                20,
                                22,
                                20,
                                22
                        )
                )
        );

        updatePanel.setLayout(
                new FlowLayout(
                        FlowLayout.LEFT,
                        12,
                        10
                )
        );

        JLabel updateLabel =
                new JLabel(
                        "Update Attendance (%)"
                );

        updateLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        updateLabel.setForeground(TEXT);

        attendanceField =
                new JTextField(8);

        attendanceField.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        updateButton =
                new JButton(
                        "Update"
                );

        refreshButton =
                new JButton(
                        "Refresh"
                );

        styleButton(
                updateButton,
                GREEN
        );

        styleButton(
                refreshButton,
                BLUE
        );

        updatePanel.add(updateLabel);

        updatePanel.add(
                attendanceField
        );

        updatePanel.add(
                updateButton
        );

        updatePanel.add(
                refreshButton
        );

        main.add(updatePanel);

        main.add(
                Box.createVerticalStrut(16)
        );

        // ================= GUIDELINES =================

        JPanel guideline =
                new JPanel(
                        new BorderLayout()
                );

        guideline.setBackground(CARD);

        guideline.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                20,
                                22,
                                20,
                                22
                        )
                )
        );

        JLabel guidelineTitle =
                new JLabel(
                        "Attendance Guidelines"
                );

        guidelineTitle.setForeground(TEXT);

        guidelineTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        JTextArea guidelineText =
                new JTextArea(
                        "90% and above  → Excellent\n"
                                + "75% – 89%      → Good\n"
                                + "Below 75%       → Average / Needs Attention"
                );

        guidelineText.setEditable(false);

        guidelineText.setBackground(CARD);

        guidelineText.setForeground(MUTED);

        guidelineText.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        guidelineText.setBorder(
                new EmptyBorder(
                        10,
                        0,
                        0,
                        0
                )
        );

        guideline.add(
                guidelineTitle,
                BorderLayout.NORTH
        );

        guideline.add(
                guidelineText,
                BorderLayout.CENTER
        );

        main.add(guideline);

        JScrollPane scroll =
                new JScrollPane(main);

        scroll.setBorder(null);

        scroll.getVerticalScrollBar()
                .setUnitIncrement(16);

        add(
                scroll,
                BorderLayout.CENTER
        );

        // ================= ACTIONS =================

        updateButton.addActionListener(
                e -> updateAttendance()
        );

        refreshButton.addActionListener(
                e -> loadAttendance()
        );
    }

    private void loadAttendance() {

        attendanceValue.setText(
                "Loading..."
        );

        statusValue.setText(
                "Loading..."
        );

        messageLabel.setText("");

        SwingWorker<String, Void> worker =
                new SwingWorker<>() {

            @Override
            protected String doInBackground()
                    throws Exception {

                return ApiClient.getAttendance(
                        studentId
                );
            }

            @Override
            protected void done() {

                try {

                    String json = get();

                    updateDisplay(json);

                } catch (Exception error) {

                    attendanceValue.setText(
                            "N/A"
                    );

                    statusValue.setText(
                            "Backend Error"
                    );

                    statusValue.setForeground(
                            RED
                    );

                    messageLabel.setText(
                            "Could not load attendance data."
                    );
                }
            }
        };

        worker.execute();
    }

    private void updateDisplay(
            String json
    ) {

        String percentage =
                extractNumber(
                        json,
                        "attendance_percentage"
                );

        if (percentage == null) {

            percentage =
                    extractNumber(
                            json,
                            "percentage"
                    );
        }

        String status =
                extractString(
                        json,
                        "status"
                );

        attendanceValue.setText(
                percentage == null
                        ? "N/A"
                        : percentage + "%"
        );

        statusValue.setText(
                status == null
                        ? calculateStatus(percentage)
                        : status
        );

        statusValue.setForeground(
                getStatusColor(
                        status == null
                                ? calculateStatus(
                                        percentage
                                )
                                : status
                )
        );

        if (percentage != null) {
            attendanceField.setText(
                    percentage
            );
        }

        messageLabel.setText(
                "Student ID: "
                        + studentId
        );
    }

    private void updateAttendance() {

        String value =
                attendanceField
                        .getText()
                        .trim();

        if (value.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter attendance percentage.",
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            double percentage =
                    Double.parseDouble(value);

            if (percentage < 0
                    || percentage > 100) {

                JOptionPane.showMessageDialog(
                        this,
                        "Attendance must be between 0 and 100.",
                        "Invalid Input",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            String json =
                    "{\"attendance_percentage\":"
                            + percentage
                            + "}";

            updateButton.setEnabled(false);

            SwingWorker<String, Void> worker =
                    new SwingWorker<>() {

                @Override
                protected String doInBackground()
                        throws Exception {

                    return ApiClient.updateAttendance(
                            studentId,
                            json
                    );
                }

                @Override
                protected void done() {

                    updateButton.setEnabled(true);

                    try {

                        get();

                        JOptionPane.showMessageDialog(
                                Attendance.this,
                                "Attendance updated successfully!",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                        loadAttendance();

                    } catch (Exception error) {

                        JOptionPane.showMessageDialog(
                                Attendance.this,
                                "Could not update attendance.\n\n"
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
                    "Please enter a valid number.",
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private String calculateStatus(
            String percentage
    ) {

        if (percentage == null) {
            return "No Data";
        }

        double value =
                Double.parseDouble(
                        percentage
                );

        if (value >= 90) {
            return "Excellent";
        }

        if (value >= 75) {
            return "Good";
        }

        return "Average";
    }

    private Color getStatusColor(
            String status
    ) {

        if (status == null) {
            return MUTED;
        }

        String value =
                status.toUpperCase();

        if (value.contains("EXCELLENT")
                || value.contains("GOOD")) {

            return GREEN;
        }

        if (value.contains("AVERAGE")
                || value.contains("MODERATE")) {

            return ORANGE;
        }

        if (value.contains("POOR")
                || value.contains("LOW")) {

            return RED;
        }

        return MUTED;
    }

    private String extractNumber(
            String json,
            String key
    ) {

        Pattern pattern =
                Pattern.compile(
                        "\""
                                + Pattern.quote(key)
                                + "\"\\s*:\\s*(-?\\d+(?:\\.\\d+)?)"
                );

        Matcher matcher =
                pattern.matcher(json);

        return matcher.find()
                ? matcher.group(1)
                : null;
    }

    private String extractString(
            String json,
            String key
    ) {

        Pattern pattern =
                Pattern.compile(
                        "\""
                                + Pattern.quote(key)
                                + "\"\\s*:\\s*\"([^\"]*)\""
                );

        Matcher matcher =
                pattern.matcher(json);

        return matcher.find()
                ? matcher.group(1)
                : null;
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
                        16,
                        9,
                        16
                )
        );
    }
}