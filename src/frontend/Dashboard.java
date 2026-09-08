package frontend;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dashboard extends JPanel {

    private static final Color BG = new Color(245, 247, 250);
    private static final Color CARD = Color.WHITE;
    private static final Color TEXT = new Color(31, 41, 55);
    private static final Color MUTED = new Color(107, 114, 128);
    private static final Color BLUE = new Color(37, 99, 235);
    private static final Color GREEN = new Color(22, 163, 74);
    private static final Color ORANGE = new Color(234, 88, 12);
    private static final Color RED = new Color(220, 38, 38);
    private static final Color BORDER = new Color(229, 231, 235);

    private final int studentId;

    private JLabel nameLabel;
    private JLabel infoLabel;
    private JLabel backendLabel;

    private JLabel cgpaValue;
    private JLabel cgpaStatus;
    private JLabel attendanceValue;
    private JLabel attendanceStatus;
    private JLabel riskValue;
    private JLabel riskScore;

    private JLabel backlogValue;
    private JLabel quizValue;
    private JLabel sleepValue;
    private JLabel waterValue;
    private JLabel exerciseValue;
    private JLabel screenValue;

    private JTextArea warningsArea;
    private JTextArea recommendationsArea;
    private JButton refreshButton;

    public Dashboard(int studentId) {
        this.studentId = studentId;

        setBackground(BG);
        setLayout(new BorderLayout(16, 16));
        setBorder(new EmptyBorder(18, 18, 18, 18));

        createUI();
        loadDashboardData();
    }

    private void createUI() {

        add(createHeader(), BorderLayout.NORTH);

        JPanel content = new JPanel();
        content.setBackground(BG);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        JPanel kpiRow = new JPanel(new GridLayout(1, 3, 14, 0));
        kpiRow.setBackground(BG);
        kpiRow.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 145)
        );

        JPanel cgpaCard = createCard("CGPA", "Loading...");
        cgpaValue = findValueLabel(cgpaCard);
        cgpaStatus = findStatusLabel(cgpaCard);

        JPanel attendanceCard =
                createCard("Attendance", "Loading...");
        attendanceValue = findValueLabel(attendanceCard);
        attendanceStatus = findStatusLabel(attendanceCard);

        JPanel riskCard =
                createCard("Overall Risk", "Loading...");
        riskValue = findValueLabel(riskCard);
        riskScore = findStatusLabel(riskCard);

        kpiRow.add(cgpaCard);
        kpiRow.add(attendanceCard);
        kpiRow.add(riskCard);

        content.add(kpiRow);
        content.add(Box.createVerticalStrut(14));

        JPanel detailsRow =
                new JPanel(new GridLayout(1, 2, 14, 0));
        detailsRow.setBackground(BG);

        detailsRow.add(createAcademicPanel());
        detailsRow.add(createLifestylePanel());

        content.add(detailsRow);
        content.add(Box.createVerticalStrut(14));

        JPanel bottomRow =
                new JPanel(new GridLayout(1, 2, 14, 0));
        bottomRow.setBackground(BG);

        bottomRow.add(createWarningsPanel());
        bottomRow.add(createRecommendationsPanel());

        content.add(bottomRow);

        JScrollPane scrollPane =
                new JScrollPane(content);

        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);

        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(BG);

        refreshButton =
                new JButton("↻  Refresh Dashboard");

        styleButton(refreshButton);

        refreshButton.addActionListener(
                e -> loadDashboardData()
        );

        footer.add(
                refreshButton,
                BorderLayout.EAST
        );

        add(footer, BorderLayout.SOUTH);
    }

    private JPanel createHeader() {

        JPanel header =
                new JPanel(new BorderLayout());

        header.setBackground(BLUE);

        header.setBorder(
                new EmptyBorder(
                        18,
                        22,
                        18,
                        22
                )
        );

        JPanel left = new JPanel();

        left.setOpaque(false);

        left.setLayout(
                new BoxLayout(
                        left,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel title =
                new JLabel(
                        "Student Success Dashboard"
                );

        title.setForeground(Color.WHITE);

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        nameLabel =
                new JLabel(
                        "Student: Loading..."
                );

        nameLabel.setForeground(Color.WHITE);

        nameLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        infoLabel =
                new JLabel(
                        "Loading student information..."
                );

        infoLabel.setForeground(
                new Color(219, 234, 254)
        );

        infoLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        left.add(title);
        left.add(
                Box.createVerticalStrut(6)
        );
        left.add(nameLabel);
        left.add(
                Box.createVerticalStrut(3)
        );
        left.add(infoLabel);

        backendLabel =
                new JLabel(
                        "● Connecting..."
                );

        backendLabel.setForeground(
                Color.WHITE
        );

        backendLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        header.add(
                left,
                BorderLayout.WEST
        );

        header.add(
                backendLabel,
                BorderLayout.EAST
        );

        return header;
    }

    private JPanel createCard(
            String title,
            String value
    ) {

        JPanel panel = new JPanel();

        panel.setBackground(CARD);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                15,
                                18,
                                15,
                                18
                        )
                )
        );

        panel.setLayout(
                new BoxLayout(
                        panel,
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

        JLabel valueLabel =
                new JLabel(value);

        valueLabel.setForeground(TEXT);

        valueLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30
                )
        );

        valueLabel.setName("value");

        JLabel statusLabel =
                new JLabel(" ");

        statusLabel.setForeground(MUTED);

        statusLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        statusLabel.setName("status");

        panel.add(titleLabel);
        panel.add(
                Box.createVerticalStrut(8)
        );
        panel.add(valueLabel);
        panel.add(
                Box.createVerticalStrut(5)
        );
        panel.add(statusLabel);

        return panel;
    }

    private JLabel findValueLabel(
            JPanel panel
    ) {

        for (Component component :
                panel.getComponents()) {

            if (component instanceof JLabel label
                    && "value".equals(
                            label.getName()
                    )) {

                return label;
            }
        }

        throw new IllegalStateException(
                "Dashboard value label not found"
        );
    }

    private JLabel findStatusLabel(
            JPanel panel
    ) {

        for (Component component :
                panel.getComponents()) {

            if (component instanceof JLabel label
                    && "status".equals(
                            label.getName()
                    )) {

                return label;
            }
        }

        throw new IllegalStateException(
                "Dashboard status label not found"
        );
    }

    private JPanel createAcademicPanel() {

        JPanel panel =
                createSection(
                        "Academic Performance"
                );

        backlogValue =
                addMetric(
                        panel,
                        "Backlogs",
                        "Loading..."
                );

        quizValue =
                addMetric(
                        panel,
                        "Quiz Average",
                        "Loading..."
                );

        return panel;
    }

    private JPanel createLifestylePanel() {

        JPanel panel =
                createSection(
                        "Lifestyle"
                );

        sleepValue =
                addMetric(
                        panel,
                        "Sleep",
                        "Loading..."
                );

        waterValue =
                addMetric(
                        panel,
                        "Water Intake",
                        "Loading..."
                );

        exerciseValue =
                addMetric(
                        panel,
                        "Exercise",
                        "Loading..."
                );

        screenValue =
                addMetric(
                        panel,
                        "Screen Time",
                        "Loading..."
                );

        return panel;
    }

    private JPanel createSection(
            String title
    ) {

        JPanel panel = new JPanel();

        panel.setBackground(CARD);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                14,
                                16,
                                14,
                                16
                        )
                )
        );

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel heading =
                new JLabel(title);

        heading.setForeground(TEXT);

        heading.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        17
                )
        );

        panel.add(heading);

        panel.add(
                Box.createVerticalStrut(12)
        );

        return panel;
    }

    private JLabel addMetric(
            JPanel panel,
            String name,
            String value
    ) {

        JPanel row =
                new JPanel(
                        new BorderLayout()
                );

        row.setBackground(CARD);

        row.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        34
                )
        );

        JLabel nameLabel =
                new JLabel(name);

        nameLabel.setForeground(MUTED);

        nameLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        JLabel valueLabel =
                new JLabel(value);

        valueLabel.setForeground(TEXT);

        valueLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        row.add(
                nameLabel,
                BorderLayout.WEST
        );

        row.add(
                valueLabel,
                BorderLayout.EAST
        );

        panel.add(row);

        panel.add(
                Box.createVerticalStrut(6)
        );

        return valueLabel;
    }

    private JPanel createWarningsPanel() {

        JPanel panel =
                createSection(
                        "Lifestyle Warnings"
                );

        warningsArea =
                createTextArea();

        warningsArea.setText(
                "Loading..."
        );

        panel.add(
                new JScrollPane(
                        warningsArea
                )
        );

        return panel;
    }

    private JPanel createRecommendationsPanel() {

        JPanel panel =
                createSection(
                        "Recommendations"
                );

        recommendationsArea =
                createTextArea();

        recommendationsArea.setText(
                "Loading..."
        );

        panel.add(
                new JScrollPane(
                        recommendationsArea
                )
        );

        return panel;
    }

    private JTextArea createTextArea() {

        JTextArea area =
                new JTextArea();

        area.setEditable(false);

        area.setLineWrap(true);

        area.setWrapStyleWord(true);

        area.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        area.setForeground(TEXT);

        area.setBackground(CARD);

        area.setBorder(
                new EmptyBorder(
                        5,
                        5,
                        5,
                        5
                )
        );

        return area;
    }

    private void styleButton(
            JButton button
    ) {

        button.setBackground(BLUE);

        button.setForeground(Color.WHITE);

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
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

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );
    }

    private void loadDashboardData() {

        setLoadingState();

        refreshButton.setEnabled(false);

        backendLabel.setText(
                "● Loading..."
        );

        SwingWorker<String[], Void> worker =
                new SwingWorker<>() {

            @Override
            protected String[] doInBackground()
                    throws Exception {

                String analytics =
                        ApiClient.getAnalytics(
                                studentId
                        );

                String recommendations =
                        ApiClient.getRecommendations(
                                studentId
                        );

                return new String[]{
                        analytics,
                        recommendations
                };
            }

            @Override
            protected void done() {

                try {

                    String[] result = get();

                    displayAnalytics(
                            result[0]
                    );

                    displayRecommendations(
                            result[1]
                    );

                    backendLabel.setText(
                            "● Backend Connected"
                    );

                    backendLabel.setForeground(
                            new Color(
                                    187,
                                    247,
                                    208
                            )
                    );

                } catch (Exception error) {

                    backendLabel.setText(
                            "● Backend Error"
                    );

                    backendLabel.setForeground(
                            new Color(
                                    254,
                                    202,
                                    202
                            )
                    );

                    JOptionPane.showMessageDialog(
                            Dashboard.this,
                            "Could not load dashboard data.\n\n"
                                    + "Make sure Flask is running on port 5000.\n\n"
                                    + "Error: "
                                    + rootMessage(error),
                            "Backend Connection Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                } finally {

                    refreshButton.setEnabled(true);
                }
            }
        };

        worker.execute();
    }

    private void setLoadingState() {

        cgpaValue.setText("...");
        cgpaStatus.setText("Loading");

        attendanceValue.setText("...");
        attendanceStatus.setText("Loading");

        riskValue.setText("...");
        riskScore.setText("Loading");

        backlogValue.setText("...");
        quizValue.setText("...");

        sleepValue.setText("...");
        waterValue.setText("...");
        exerciseValue.setText("...");
        screenValue.setText("...");

        warningsArea.setText("Loading...");
        recommendationsArea.setText(
                "Loading..."
        );
    }

    private void displayAnalytics(
            String json
    ) {

        String name =
                extractString(
                        json,
                        "full_name"
                );

        String department =
                extractString(
                        json,
                        "department"
                );

        String semester =
                extractNumber(
                        json,
                        "semester"
                );

        nameLabel.setText(
                "Student: "
                        + safe(
                                name,
                                "Unknown"
                        )
        );

        infoLabel.setText(
                "ID: "
                        + studentId
                        + "   •   "
                        + safe(
                                department,
                                "Unknown Department"
                        )
                        + "   •   Semester "
                        + safe(
                                semester,
                                "-"
                        )
        );

        String cgpa =
                extractNumber(
                        json,
                        "cgpa"
                );

        String academicStatus =
                extractString(
                        json,
                        "status",
                        "academic"
                );

        String attendance =
                extractNumber(
                        json,
                        "percentage"
                );

        String attendanceStatusText =
                extractString(
                        json,
                        "status",
                        "attendance"
                );

        String risk =
                extractString(
                        json,
                        "overall_risk"
                );

        String score =
                extractNumber(
                        json,
                        "risk_score"
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

        String sleep =
                extractNumber(
                        json,
                        "sleep_hours"
                );

        String water =
                extractNumber(
                        json,
                        "water_intake"
                );

        String exercise =
                extractNumber(
                        json,
                        "exercise_minutes"
                );

        String screen =
                extractNumber(
                        json,
                        "screen_time"
                );

        cgpaValue.setText(
                safe(
                        cgpa,
                        "N/A"
                )
        );

        cgpaStatus.setText(
                safe(
                        academicStatus,
                        "No Data"
                )
        );

        cgpaStatus.setForeground(
                statusColor(
                        academicStatus
                )
        );

        attendanceValue.setText(
                attendance == null
                        ? "N/A"
                        : attendance + "%"
        );

        attendanceStatus.setText(
                safe(
                        attendanceStatusText,
                        "No Data"
                )
        );

        attendanceStatus.setForeground(
                statusColor(
                        attendanceStatusText
                )
        );

        riskValue.setText(
                safe(
                        risk,
                        "NO_DATA"
                )
        );

        riskValue.setForeground(
                riskColor(risk)
        );

        riskScore.setText(
                score == null
                        ? "Risk score unavailable"
                        : "Risk Score: " + score
        );

        backlogValue.setText(
                safe(
                        backlogs,
                        "N/A"
                )
        );

        quizValue.setText(
                quiz == null
                        ? "N/A"
                        : quiz + "%"
        );

        sleepValue.setText(
                sleep == null
                        ? "N/A"
                        : sleep + " hours"
        );

        waterValue.setText(
                water == null
                        ? "N/A"
                        : water + " L"
        );

        exerciseValue.setText(
                exercise == null
                        ? "N/A"
                        : exercise + " min"
        );

        screenValue.setText(
                screen == null
                        ? "N/A"
                        : screen + " hours"
        );

        String warnings =
                extractWarnings(json);

        warningsArea.setText(
                warnings.isBlank()
                        ? "✓ No lifestyle warnings."
                        : warnings
        );
    }

    private void displayRecommendations(
            String json
    ) {

        StringBuilder output =
                new StringBuilder();

        Pattern pattern =
                Pattern.compile(
                        "\\\"category\\\"\\s*:\\s*\\\"([^\\\"]*)\\\"\\s*,\\s*"
                                + "\\\"message\\\"\\s*:\\s*\\\"([^\\\"]*)\\\"\\s*,\\s*"
                                + "\\\"priority\\\"\\s*:\\s*\\\"([^\\\"]*)\\\""
                );

        Matcher matcher =
                pattern.matcher(json);

        int count = 1;

        while (matcher.find()) {

            output.append(count++)
                    .append(". [")
                    .append(matcher.group(3))
                    .append("] ")
                    .append(matcher.group(1))
                    .append("\n")
                    .append(matcher.group(2))
                    .append("\n\n");
        }

        if (output.length() == 0) {

            output.append(
                    "No recommendations available."
            );
        }

        recommendationsArea.setText(
                output.toString()
        );

        recommendationsArea.setCaretPosition(0);
    }

    private String extractWarnings(
            String json
    ) {

        Pattern pattern =
                Pattern.compile(
                        "\\\"warnings\\\"\\s*:\\s*\\[([^\\]]*)\\]"
                );

        Matcher matcher =
                pattern.matcher(json);

        if (!matcher.find()) {
            return "";
        }

        String body =
                matcher.group(1).trim();

        if (body.isEmpty()) {
            return "";
        }

        Matcher itemMatcher =
                Pattern.compile(
                        "\\\"([^\\\"]*)\\\""
                ).matcher(body);

        StringBuilder output =
                new StringBuilder();

        while (itemMatcher.find()) {

            output.append("• ")
                    .append(
                            itemMatcher.group(1)
                    )
                    .append("\n");
        }

        return output.toString().trim();
    }

    private String extractString(
            String json,
            String key
    ) {

        return extractString(
                json,
                key,
                null
        );
    }

    private String extractString(
            String json,
            String key,
            String preferredObject
    ) {

        if (preferredObject != null) {

            Pattern objectPattern =
                    Pattern.compile(
                            "\\\""
                                    + Pattern.quote(
                                            preferredObject
                                    )
                                    + "\\\"\\s*:\\s*\\{(.*?)\\}",
                            Pattern.DOTALL
                    );

            Matcher objectMatcher =
                    objectPattern.matcher(json);

            if (objectMatcher.find()) {

                String value =
                        extractStringFromBlock(
                                objectMatcher.group(1),
                                key
                        );

                if (value != null) {
                    return value;
                }
            }
        }

        return extractStringFromBlock(
                json,
                key
        );
    }

    private String extractStringFromBlock(
            String block,
            String key
    ) {

        Pattern pattern =
                Pattern.compile(
                        "\\\""
                                + Pattern.quote(key)
                                + "\\\"\\s*:\\s*\\\"([^\\\"]*)\\\""
                );

        Matcher matcher =
                pattern.matcher(block);

        return matcher.find()
                ? matcher.group(1)
                : null;
    }

    private String extractNumber(
            String json,
            String key
    ) {

        Pattern pattern =
                Pattern.compile(
                        "\\\""
                                + Pattern.quote(key)
                                + "\\\"\\s*:\\s*(-?\\d+(?:\\.\\d+)?)"
                );

        Matcher matcher =
                pattern.matcher(json);

        return matcher.find()
                ? matcher.group(1)
                : null;
    }

    private Color statusColor(
            String status
    ) {

        if (status == null) {
            return MUTED;
        }

        String value =
                status.toUpperCase();

        if (value.contains("EXCELLENT")
                || value.equals("GOOD")) {

            return GREEN;
        }

        if (value.contains("AVERAGE")
                || value.contains("MODERATE")) {

            return ORANGE;
        }

        if (value.contains("RISK")
                || value.contains("POOR")) {

            return RED;
        }

        return MUTED;
    }

    private Color riskColor(
            String risk
    ) {

        if (risk == null) {
            return MUTED;
        }

        return switch (
                risk.toUpperCase()
        ) {

            case "LOW" -> GREEN;

            case "MODERATE" -> ORANGE;

            case "HIGH" -> RED;

            default -> MUTED;
        };
    }

    private String safe(
            String value,
            String fallback
    ) {

        return value == null
                || value.isBlank()
                ? fallback
                : value;
    }

    private String rootMessage(
            Exception error
    ) {

        Throwable cause = error;

        while (cause.getCause() != null) {
            cause = cause.getCause();
        }

        return cause.getMessage() == null
                ? cause.getClass().getSimpleName()
                : cause.getMessage();
    }
}
