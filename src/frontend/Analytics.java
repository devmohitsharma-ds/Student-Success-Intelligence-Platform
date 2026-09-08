package frontend;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analytics extends JPanel {

    private final int studentId;

    private JLabel cgpaValue;
    private JLabel attendanceValue;
    private JLabel sleepValue;
    private JLabel waterValue;
    private JLabel exerciseValue;
    private JLabel screenValue;
    private JLabel riskValue;
    private JLabel riskScoreValue;

    private JTextArea summaryArea;

    public Analytics(int studentId) {

        this.studentId = studentId;

        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(245, 247, 250));
        setBorder(
                BorderFactory.createEmptyBorder(
                        25, 30, 25, 30
                )
        );

        // =========================
        // HEADER
        // =========================

        JPanel header = new JPanel(
                new BorderLayout()
        );

        header.setOpaque(false);

        JLabel title = new JLabel("Analytics");

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        JLabel subtitle = new JLabel(
                "View your academic, attendance, lifestyle and risk analysis."
        );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        JPanel titlePanel = new JPanel();

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        titlePanel.setOpaque(false);

        titlePanel.add(title);
        titlePanel.add(
                Box.createVerticalStrut(5)
        );
        titlePanel.add(subtitle);

        header.add(
                titlePanel,
                BorderLayout.WEST
        );

        // =========================
        // REFRESH BUTTON
        // =========================

        JButton refreshButton =
                new JButton("Refresh");

        refreshButton.setFocusPainted(false);

        refreshButton.addActionListener(
                e -> loadAnalytics()
        );

        header.add(
                refreshButton,
                BorderLayout.EAST
        );

        // =========================
        // CARDS
        // =========================

        JPanel cardsPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                4,
                                15,
                                15
                        )
                );

        cardsPanel.setOpaque(false);

        cgpaValue = createCardValue();
        attendanceValue = createCardValue();
        sleepValue = createCardValue();
        waterValue = createCardValue();
        exerciseValue = createCardValue();
        screenValue = createCardValue();
        riskValue = createCardValue();
        riskScoreValue = createCardValue();

        cardsPanel.add(
                createCard(
                        "CGPA",
                        cgpaValue
                )
        );

        cardsPanel.add(
                createCard(
                        "Attendance",
                        attendanceValue
                )
        );

        cardsPanel.add(
                createCard(
                        "Sleep",
                        sleepValue
                )
        );

        cardsPanel.add(
                createCard(
                        "Water Intake",
                        waterValue
                )
        );

        cardsPanel.add(
                createCard(
                        "Exercise",
                        exerciseValue
                )
        );

        cardsPanel.add(
                createCard(
                        "Screen Time",
                        screenValue
                )
        );

        cardsPanel.add(
                createCard(
                        "Risk Level",
                        riskValue
                )
        );

        cardsPanel.add(
                createCard(
                        "Risk Score",
                        riskScoreValue
                )
        );

        // =========================
        // SUMMARY
        // =========================

        JPanel summaryPanel =
                new JPanel(
                        new BorderLayout()
                );

        summaryPanel.setBackground(Color.WHITE);

        summaryPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        220,
                                        225,
                                        230
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                15,
                                15,
                                15,
                                15
                        )
                )
        );

        JLabel summaryTitle =
                new JLabel(
                        "Performance Summary"
                );

        summaryTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        17
                )
        );

        summaryArea =
                new JTextArea();

        summaryArea.setEditable(false);
        summaryArea.setLineWrap(true);
        summaryArea.setWrapStyleWord(true);
        summaryArea.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        summaryArea.setText(
                "Loading analytics..."
        );

        summaryPanel.add(
                summaryTitle,
                BorderLayout.NORTH
        );

        summaryPanel.add(
                new JScrollPane(
                        summaryArea
                ),
                BorderLayout.CENTER
        );

        // =========================
        // CENTER
        // =========================

        JPanel center =
                new JPanel(
                        new BorderLayout(15, 15)
                );

        center.setOpaque(false);

        center.add(
                cardsPanel,
                BorderLayout.NORTH
        );

        center.add(
                summaryPanel,
                BorderLayout.CENTER
        );

        add(
                header,
                BorderLayout.NORTH
        );

        add(
                center,
                BorderLayout.CENTER
        );

        // =========================
        // LOAD DATA
        // =========================

        loadAnalytics();
    }

    // =====================================================
    // LOAD ANALYTICS
    // =====================================================

    private void loadAnalytics() {

        summaryArea.setText(
                "Loading analytics..."
        );

        new Thread(() -> {

            try {

                String response =
                        ApiClient.getAnalytics(
                                studentId
                        );

                double cgpa =
                        getNumber(
                                response,
                                "cgpa"
                        );

                double attendance =
                        getNumber(
                                response,
                                "percentage"
                        );

                double sleep =
                        getNumber(
                                response,
                                "sleep_hours"
                        );

                double water =
                        getNumber(
                                response,
                                "water_intake"
                        );

                double exercise =
                        getNumber(
                                response,
                                "exercise_minutes"
                        );

                double screen =
                        getNumber(
                                response,
                                "screen_time"
                        );

                double riskScore =
                        getNumber(
                                response,
                                "risk_score"
                        );

                String risk =
                        getString(
                                response,
                                "overall_risk"
                        );

                if (risk.equals("N/A")) {

                    risk =
                            getString(
                                    response,
                                    "level"
                            );
                }

                final double finalCgpa = cgpa;
                final double finalAttendance = attendance;
                final double finalSleep = sleep;
                final double finalWater = water;
                final double finalExercise = exercise;
                final double finalScreen = screen;
                final double finalRiskScore = riskScore;
                final String finalRisk = risk;

                SwingUtilities.invokeLater(() -> {

                    cgpaValue.setText(
                            String.format(
                                    "%.2f",
                                    finalCgpa
                            )
                    );

                    attendanceValue.setText(
                            String.format(
                                    "%.1f%%",
                                    finalAttendance
                            )
                    );

                    sleepValue.setText(
                            String.format(
                                    "%.1f h",
                                    finalSleep
                            )
                    );

                    waterValue.setText(
                            String.format(
                                    "%.1f L",
                                    finalWater
                            )
                    );

                    exerciseValue.setText(
                            String.format(
                                    "%.0f min",
                                    finalExercise
                            )
                    );

                    screenValue.setText(
                            String.format(
                                    "%.1f h",
                                    finalScreen
                            )
                    );

                    riskScoreValue.setText(
                            String.format(
                                    "%.0f",
                                    finalRiskScore
                            )
                    );

                    if (finalRisk.equals("N/A")) {

                        riskValue.setText(
                                calculateRisk(
                                        finalCgpa,
                                        finalAttendance,
                                        finalSleep,
                                        finalWater,
                                        finalExercise,
                                        finalScreen
                                )
                        );

                    } else {

                        riskValue.setText(
                                finalRisk
                        );
                    }

                    updateSummary(
                            finalCgpa,
                            finalAttendance,
                            finalSleep,
                            finalWater,
                            finalExercise,
                            finalScreen,
                            finalRisk
                    );
                });

            } catch (Exception ex) {

                SwingUtilities.invokeLater(() -> {

                    summaryArea.setText(
                            "Unable to load analytics.\n\n"
                                    + ex.getMessage()
                    );

                    JOptionPane.showMessageDialog(
                            this,
                            "Could not load analytics data.\n\n"
                                    + ex.getMessage(),
                            "Analytics Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                });
            }

        }).start();
    }

    // =====================================================
    // SUMMARY
    // =====================================================

    private void updateSummary(
            double cgpa,
            double attendance,
            double sleep,
            double water,
            double exercise,
            double screen,
            String risk
    ) {

        StringBuilder summary =
                new StringBuilder();

        summary.append(
                "Student ID: "
        );

        summary.append(
                studentId
        );

        summary.append(
                "\n\n"
        );

        summary.append(
                "Academic Performance:\n"
        );

        summary.append(
                "CGPA: "
        );

        summary.append(
                String.format(
                        "%.2f",
                        cgpa
                )
        );

        summary.append(
                "\n\n"
        );

        summary.append(
                "Attendance:\n"
        );

        summary.append(
                String.format(
                        "%.1f%%",
                        attendance
                )
        );

        summary.append(
                "\n\n"
        );

        summary.append(
                "Lifestyle:\n"
        );

        summary.append(
                "Sleep: "
        );

        summary.append(
                String.format(
                        "%.1f hours",
                        sleep
                )
        );

        summary.append(
                "\nWater: "
        );

        summary.append(
                String.format(
                        "%.1f L",
                        water
                )
        );

        summary.append(
                "\nExercise: "
        );

        summary.append(
                String.format(
                        "%.0f minutes",
                        exercise
                )
        );

        summary.append(
                "\nScreen Time: "
        );

        summary.append(
                String.format(
                        "%.1f hours",
                        screen
                )
        );

        summary.append(
                "\n\n"
        );

        summary.append(
                "Risk Analysis:\n"
        );

        summary.append(
                "Risk Level: "
        );

        summary.append(
                risk
        );

        summary.append(
                "\n\n"
        );

        if (cgpa < 6.0) {

            summary.append(
                    "• Academic performance needs attention.\n"
            );
        }

        if (attendance < 75) {

            summary.append(
                    "• Attendance is below the recommended level.\n"
            );
        }

        if (sleep < 7) {

            summary.append(
                    "• Try to get at least 7 hours of sleep.\n"
            );
        }

        if (water < 2) {

            summary.append(
                    "• Increase daily water intake.\n"
            );
        }

        if (exercise < 30) {

            summary.append(
                    "• Try to exercise for at least 30 minutes.\n"
            );
        }

        if (screen > 8) {

            summary.append(
                    "• Consider reducing excessive screen time.\n"
            );
        }

        if (
                cgpa >= 6
                        && attendance >= 75
                        && sleep >= 7
                        && water >= 2
                        && exercise >= 30
                        && screen <= 8
        ) {

            summary.append(
                    "• Your current indicators are within the recommended ranges."
            );
        }

        summaryArea.setText(
                summary.toString()
        );
    }

    // =====================================================
    // LOCAL RISK CALCULATION
    // =====================================================

    private String calculateRisk(
            double cgpa,
            double attendance,
            double sleep,
            double water,
            double exercise,
            double screen
    ) {

        int score = 0;

        if (cgpa < 6) {
            score++;
        }

        if (attendance < 75) {
            score++;
        }

        if (sleep < 7) {
            score++;
        }

        if (water < 2) {
            score++;
        }

        if (exercise < 30) {
            score++;
        }

        if (screen > 8) {
            score++;
        }

        if (score >= 4) {
            return "HIGH";
        }

        if (score >= 2) {
            return "MEDIUM";
        }

        return "LOW";
    }

    // =====================================================
    // JSON NUMBER
    // =====================================================

    private double getNumber(
            String json,
            String key
    ) {

        Pattern pattern =
                Pattern.compile(
                        "\"" +
                                Pattern.quote(key) +
                                "\"\\s*:\\s*(-?\\d+(?:\\.\\d+)?)"
                );

        Matcher matcher =
                pattern.matcher(json);

        if (matcher.find()) {

            return Double.parseDouble(
                    matcher.group(1)
            );
        }

        return 0.0;
    }

    // =====================================================
    // JSON STRING
    // =====================================================

    private String getString(
            String json,
            String key
    ) {

        Pattern pattern =
                Pattern.compile(
                        "\"" +
                                Pattern.quote(key) +
                                "\"\\s*:\\s*\"([^\"]*)\""
                );

        Matcher matcher =
                pattern.matcher(json);

        if (matcher.find()) {

            return matcher.group(1);
        }

        return "N/A";
    }

    // =====================================================
    // UI HELPERS
    // =====================================================

    private JLabel createCardValue() {

        JLabel label =
                new JLabel("--");

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        21
                )
        );

        return label;
    }

    private JPanel createCard(
            String title,
            JLabel value
    ) {

        JPanel panel =
                new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        panel.setBackground(
                Color.WHITE
        );

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        220,
                                        225,
                                        230
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                15,
                                15,
                                15,
                                15
                        )
                )
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        panel.add(titleLabel);

        panel.add(
                Box.createVerticalStrut(10)
        );

        panel.add(value);

        return panel;
    }
}