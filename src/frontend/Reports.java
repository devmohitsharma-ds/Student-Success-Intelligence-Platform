package frontend;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reports extends JPanel {

    private final int studentId;

    private JTextArea reportArea;
    private JLabel statusLabel;

    public Reports(int studentId) {

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

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );
        titlePanel.setOpaque(false);

        JLabel title = new JLabel("Reports");

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        JLabel subtitle = new JLabel(
                "Generate and view a summary of your student performance."
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

        // =========================
        // BUTTONS
        // =========================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        buttonPanel.setOpaque(false);

        JButton generateButton =
                new JButton("Generate Report");

        JButton saveButton =
                new JButton("Save Report");

        JButton refreshButton =
                new JButton("Refresh");

        generateButton.setFocusPainted(false);
        saveButton.setFocusPainted(false);
        refreshButton.setFocusPainted(false);

        buttonPanel.add(generateButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(refreshButton);

        header.add(
                buttonPanel,
                BorderLayout.EAST
        );

        // =========================
        // REPORT AREA
        // =========================

        reportArea =
                new JTextArea();

        reportArea.setEditable(false);
        reportArea.setLineWrap(true);
        reportArea.setWrapStyleWord(true);

        reportArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        14
                )
        );

        reportArea.setText(
                "Click \"Generate Report\" to create your report."
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        reportArea
                );

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                220,
                                225,
                                230
                        )
                )
        );

        // =========================
        // FOOTER
        // =========================

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

        // =========================
        // ADD COMPONENTS
        // =========================

        add(
                header,
                BorderLayout.NORTH
        );

        add(
                scrollPane,
                BorderLayout.CENTER
        );

        add(
                statusLabel,
                BorderLayout.SOUTH
        );

        // =========================
        // ACTIONS
        // =========================

        generateButton.addActionListener(
                e -> generateReport()
        );

        refreshButton.addActionListener(
                e -> generateReport()
        );

        saveButton.addActionListener(
                e -> saveReport()
        );
    }

    // =====================================================
    // GENERATE REPORT
    // =====================================================

    private void generateReport() {

        statusLabel.setText(
                "Generating report..."
        );

        reportArea.setText(
                "Loading student data..."
        );

        new Thread(() -> {

            try {

                String analytics =
                        ApiClient.getAnalytics(
                                studentId
                        );

                String recommendations =
                        ApiClient.getRecommendations(
                                studentId
                        );

                String report =
                        buildReport(
                                analytics,
                                recommendations
                        );

                SwingUtilities.invokeLater(() -> {

                    reportArea.setText(
                            report
                    );

                    reportArea.setCaretPosition(0);

                    statusLabel.setText(
                            "Report generated successfully"
                    );
                });

            } catch (Exception ex) {

                SwingUtilities.invokeLater(() -> {

                    reportArea.setText(
                            "Unable to generate report.\n\n"
                                    + ex.getMessage()
                    );

                    statusLabel.setText(
                            "Report generation failed"
                    );
                });
            }

        }).start();
    }

    // =====================================================
    // BUILD REPORT
    // =====================================================

    private String buildReport(
            String analytics,
            String recommendations
    ) {

        double cgpa =
                getNumber(
                        analytics,
                        "cgpa"
                );

        double attendance =
                getNumber(
                        analytics,
                        "percentage"
                );

        double sleep =
                getNumber(
                        analytics,
                        "sleep_hours"
                );

        double water =
                getNumber(
                        analytics,
                        "water_intake"
                );

        double exercise =
                getNumber(
                        analytics,
                        "exercise_minutes"
                );

        double screen =
                getNumber(
                        analytics,
                        "screen_time"
                );

        double riskScore =
                getNumber(
                        analytics,
                        "score"
                );

        String risk =
                getString(
                        analytics,
                        "risk_level"
                );

        if (risk.equals("N/A")) {

            risk =
                    getString(
                            analytics,
                            "level"
                    );
        }

        if (risk.equals("N/A")) {

            risk =
                    calculateRisk(
                            cgpa,
                            attendance,
                            sleep,
                            water,
                            exercise,
                            screen
                    );
        }

        StringBuilder report =
                new StringBuilder();

        report.append(
                "============================================================\n"
        );

        report.append(
                "              STUDENT SUCCESS REPORT\n"
        );

        report.append(
                "============================================================\n\n"
        );

        report.append(
                "Student ID       : "
        );

        report.append(
                studentId
        );

        report.append(
                "\n\n"
        );

        report.append(
                "------------------------------------------------------------\n"
        );

        report.append(
                "ACADEMIC PERFORMANCE\n"
        );

        report.append(
                "------------------------------------------------------------\n"
        );

        report.append(
                "CGPA             : "
        );

        report.append(
                String.format(
                        "%.2f",
                        cgpa
                )
        );

        report.append(
                "\n"
        );

        report.append(
                "Attendance       : "
        );

        report.append(
                String.format(
                        "%.1f%%",
                        attendance
                )
        );

        report.append(
                "\n\n"
        );

        report.append(
                "------------------------------------------------------------\n"
        );

        report.append(
                "LIFESTYLE\n"
        );

        report.append(
                "------------------------------------------------------------\n"
        );

        report.append(
                "Sleep            : "
        );

        report.append(
                String.format(
                        "%.1f hours",
                        sleep
                )
        );

        report.append(
                "\n"
        );

        report.append(
                "Water Intake     : "
        );

        report.append(
                String.format(
                        "%.1f L",
                        water
                )
        );

        report.append(
                "\n"
        );

        report.append(
                "Exercise         : "
        );

        report.append(
                String.format(
                        "%.0f minutes",
                        exercise
                )
        );

        report.append(
                "\n"
        );

        report.append(
                "Screen Time      : "
        );

        report.append(
                String.format(
                        "%.1f hours",
                        screen
                )
        );

        report.append(
                "\n\n"
        );

        report.append(
                "------------------------------------------------------------\n"
        );

        report.append(
                "RISK ANALYSIS\n"
        );

        report.append(
                "------------------------------------------------------------\n"
        );

        report.append(
                "Risk Level       : "
        );

        report.append(
                risk
        );

        report.append(
                "\n"
        );

        report.append(
                "Risk Score       : "
        );

        report.append(
                String.format(
                        "%.0f",
                        riskScore
                )
        );

        report.append(
                "\n\n"
        );

        report.append(
                "------------------------------------------------------------\n"
        );

        report.append(
                "RECOMMENDATIONS\n"
        );

        report.append(
                "------------------------------------------------------------\n"
        );

        String recommendationText =
                extractRecommendations(
                        recommendations
                );

        if (recommendationText.isEmpty()) {

            report.append(
                    "No specific recommendations available.\n"
            );

        } else {

            report.append(
                    recommendationText
            );
        }

        report.append(
                "\n\n"
        );

        report.append(
                "------------------------------------------------------------\n"
        );

        report.append(
                "PERFORMANCE NOTES\n"
        );

        report.append(
                "------------------------------------------------------------\n"
        );

        boolean hasNotes = false;

        if (cgpa < 6) {

            report.append(
                    "• Academic performance requires attention.\n"
            );

            hasNotes = true;
        }

        if (attendance < 75) {

            report.append(
                    "• Attendance is below the recommended 75% level.\n"
            );

            hasNotes = true;
        }

        if (sleep < 7) {

            report.append(
                    "• Sleep duration is below the recommended level.\n"
            );

            hasNotes = true;
        }

        if (water < 2) {

            report.append(
                    "• Daily water intake can be improved.\n"
            );

            hasNotes = true;
        }

        if (exercise < 30) {

            report.append(
                    "• Daily physical activity can be increased.\n"
            );

            hasNotes = true;
        }

        if (screen > 8) {

            report.append(
                    "• Screen time is relatively high and can be reduced.\n"
            );

            hasNotes = true;
        }

        if (!hasNotes) {

            report.append(
                    "• Current indicators are within the recommended ranges.\n"
            );
        }

        report.append(
                "\n============================================================\n"
        );

        report.append(
                "Generated by Student Success Intelligence Platform\n"
        );

        report.append(
                "============================================================\n"
        );

        return report.toString();
    }

    // =====================================================
    // SAVE REPORT
    // =====================================================

    private void saveReport() {

        if (
                reportArea.getText() == null
                        || reportArea.getText().trim().isEmpty()
                        || reportArea.getText().startsWith(
                                "Click \"Generate Report\""
                        )
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please generate a report first.",
                    "No Report",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        JFileChooser chooser =
                new JFileChooser();

        chooser.setDialogTitle(
                "Save Student Report"
        );

        chooser.setSelectedFile(
                new File(
                        "Student_Report_"
                                + studentId
                                + ".txt"
                )
        );

        int result =
                chooser.showSaveDialog(
                        this
                );

        if (
                result
                        != JFileChooser.APPROVE_OPTION
        ) {

            return;
        }

        File file =
                chooser.getSelectedFile();

        try (
                FileWriter writer =
                        new FileWriter(file)
        ) {

            writer.write(
                    reportArea.getText()
            );

            statusLabel.setText(
                    "Report saved successfully"
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Report saved successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Could not save the report.\n\n"
                            + ex.getMessage(),
                    "Save Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // EXTRACT RECOMMENDATIONS
    // =====================================================

    private String extractRecommendations(
            String json
    ) {

        StringBuilder result =
                new StringBuilder();

        Pattern arrayPattern =
                Pattern.compile(
                        "\"recommendations\"\\s*:\\s*\\[(.*?)\\]",
                        Pattern.DOTALL
                );

        Matcher arrayMatcher =
                arrayPattern.matcher(json);

        if (arrayMatcher.find()) {

            String content =
                    arrayMatcher.group(1);

            Pattern itemPattern =
                    Pattern.compile(
                            "\"((?:\\\\.|[^\"\\\\])*)\""
                    );

            Matcher itemMatcher =
                    itemPattern.matcher(
                            content
                    );

            int count = 1;

            while (itemMatcher.find()) {

                String text =
                        cleanJsonText(
                                itemMatcher.group(1)
                        );

                if (!text.isEmpty()) {

                    result.append(
                            count
                    );

                    result.append(
                            ". "
                    );

                    result.append(
                            text
                    );

                    result.append(
                            "\n"
                    );

                    count++;
                }
            }
        }

        if (result.length() == 0) {

            Pattern singlePattern =
                    Pattern.compile(
                            "\"recommendation\"\\s*:\\s*\"((?:\\\\.|[^\"\\\\])*)\""
                    );

            Matcher matcher =
                    singlePattern.matcher(json);

            int count = 1;

            while (matcher.find()) {

                String text =
                        cleanJsonText(
                                matcher.group(1)
                        );

                if (!text.isEmpty()) {

                    result.append(
                            count
                    );

                    result.append(
                            ". "
                    );

                    result.append(
                            text
                    );

                    result.append(
                            "\n"
                    );

                    count++;
                }
            }
        }

        return result.toString();
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
    // LOCAL RISK
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
    // CLEAN JSON TEXT
    // =====================================================

    private String cleanJsonText(
            String text
    ) {

        return text
                .replace("\\\"", "\"")
                .replace("\\n", "\n")
                .replace("\\r", "\r")
                .replace("\\t", "\t")
                .trim();
    }
}