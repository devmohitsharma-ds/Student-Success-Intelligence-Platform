package frontend;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lifestyle extends JPanel {

    private final int studentId;

    private JLabel sleepValue;
    private JLabel waterValue;
    private JLabel exerciseValue;
    private JLabel screenValue;
    private JLabel statusLabel;

    private JTextField sleepField;
    private JTextField waterField;
    private JTextField exerciseField;
    private JTextField screenField;

    private JButton updateButton;
    private JButton refreshButton;

    public Lifestyle(int studentId) {

        this.studentId = studentId;

        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(245, 247, 250));
        setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));

        // =========================
        // HEADER
        // =========================

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JLabel title = new JLabel("Lifestyle");
        title.setFont(new Font("Arial", Font.BOLD, 28));

        JLabel subtitle = new JLabel(
                "Track your daily habits and maintain a healthy student lifestyle."
        );
        subtitle.setFont(new Font("Arial", Font.PLAIN, 14));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setOpaque(false);

        titlePanel.add(title);
        titlePanel.add(Box.createVerticalStrut(5));
        titlePanel.add(subtitle);

        header.add(titlePanel, BorderLayout.WEST);

        // =========================
        // MAIN CONTENT
        // =========================

        JPanel content = new JPanel(new BorderLayout(20, 20));
        content.setOpaque(false);

        // Current values panel
        JPanel currentPanel = createCard();

        JLabel currentTitle = new JLabel("Current Lifestyle");
        currentTitle.setFont(new Font("Arial", Font.BOLD, 18));

        currentPanel.add(currentTitle);

        JPanel valuesPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        valuesPanel.setOpaque(false);

        sleepValue = createValueLabel();
        waterValue = createValueLabel();
        exerciseValue = createValueLabel();
        screenValue = createValueLabel();

        valuesPanel.add(createRow("Sleep", sleepValue));
        valuesPanel.add(createRow("Water Intake", waterValue));
        valuesPanel.add(createRow("Exercise", exerciseValue));
        valuesPanel.add(createRow("Screen Time", screenValue));

        currentPanel.add(Box.createVerticalStrut(15));
        currentPanel.add(valuesPanel);

        // Status
        statusLabel = new JLabel("Status: Loading...");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 15));

        currentPanel.add(Box.createVerticalStrut(15));
        currentPanel.add(statusLabel);

        // Update panel
        JPanel updatePanel = createCard();

        JLabel updateTitle = new JLabel("Update Lifestyle");
        updateTitle.setFont(new Font("Arial", Font.BOLD, 18));

        updatePanel.add(updateTitle);
        updatePanel.add(Box.createVerticalStrut(15));

        sleepField = new JTextField();
        waterField = new JTextField();
        exerciseField = new JTextField();
        screenField = new JTextField();

        updatePanel.add(createInputRow("Sleep Hours", sleepField));
        updatePanel.add(createInputRow("Water Intake (L)", waterField));
        updatePanel.add(createInputRow("Exercise (min)", exerciseField));
        updatePanel.add(createInputRow("Screen Time (hrs)", screenField));

        updateButton = new JButton("Update");
        refreshButton = new JButton("Refresh");

        updateButton.setFocusPainted(false);
        refreshButton.setFocusPainted(false);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setOpaque(false);

        buttonPanel.add(updateButton);
        buttonPanel.add(refreshButton);

        updatePanel.add(Box.createVerticalStrut(10));
        updatePanel.add(buttonPanel);

        content.add(currentPanel, BorderLayout.CENTER);
        content.add(updatePanel, BorderLayout.EAST);

        // =========================
        // GUIDELINES
        // =========================

        JPanel guidelines = createCard();

        JLabel guideTitle = new JLabel("Healthy Lifestyle Guidelines");
        guideTitle.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel guideText = new JLabel(
                "<html>"
                        + "• Sleep: Aim for at least 7 hours<br>"
                        + "• Water: Aim for around 2 litres per day<br>"
                        + "• Exercise: Aim for at least 30 minutes<br>"
                        + "• Screen Time: Try to keep recreational screen time below 8 hours"
                        + "</html>"
        );

        guideText.setFont(new Font("Arial", Font.PLAIN, 13));

        guidelines.add(guideTitle);
        guidelines.add(Box.createVerticalStrut(8));
        guidelines.add(guideText);

        add(header, BorderLayout.NORTH);
        add(content, BorderLayout.CENTER);
        add(guidelines, BorderLayout.SOUTH);

        // =========================
        // BUTTON ACTIONS
        // =========================

        refreshButton.addActionListener(e -> loadLifestyle());

        updateButton.addActionListener(e -> updateLifestyle());

        // Load data
        loadLifestyle();
    }

    // =========================================================
    // LOAD LIFESTYLE FROM BACKEND
    // =========================================================

    private void loadLifestyle() {

        statusLabel.setText("Status: Loading...");

        new Thread(() -> {

            try {

                String response = ApiClient.getLifestyle(studentId);

                double sleep = getNumber(response, "sleep_hours");
                double water = getNumber(response, "water_intake");
                double exercise = getNumber(response, "exercise_minutes");
                double screen = getNumber(response, "screen_time");

                SwingUtilities.invokeLater(() -> {

                    sleepValue.setText(String.format("%.1f hours", sleep));
                    waterValue.setText(String.format("%.1f L", water));
                    exerciseValue.setText(String.format("%.0f min", exercise));
                    screenValue.setText(String.format("%.1f hours", screen));

                    sleepField.setText(String.valueOf(sleep));
                    waterField.setText(String.valueOf(water));
                    exerciseField.setText(String.valueOf(exercise));
                    screenField.setText(String.valueOf(screen));

                    updateStatus(sleep, water, exercise, screen);
                });

            } catch (Exception ex) {

                SwingUtilities.invokeLater(() -> {

                    statusLabel.setText("Status: Unable to load data");

                    JOptionPane.showMessageDialog(
                            this,
                            "Could not load lifestyle data.\n\n"
                                    + ex.getMessage(),
                            "Lifestyle Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                });
            }

        }).start();
    }

    // =========================================================
    // UPDATE LIFESTYLE
    // =========================================================

    private void updateLifestyle() {

        try {

            double sleep = Double.parseDouble(sleepField.getText().trim());
            double water = Double.parseDouble(waterField.getText().trim());
            double exercise = Double.parseDouble(exerciseField.getText().trim());
            double screen = Double.parseDouble(screenField.getText().trim());

            // Validation
            if (sleep < 0 || sleep > 24) {
                showError("Sleep hours must be between 0 and 24.");
                return;
            }

            if (water < 0 || water > 20) {
                showError("Water intake must be between 0 and 20 litres.");
                return;
            }

            if (exercise < 0 || exercise > 1440) {
                showError("Exercise minutes must be between 0 and 1440.");
                return;
            }

            if (screen < 0 || screen > 24) {
                showError("Screen time must be between 0 and 24 hours.");
                return;
            }

            updateButton.setEnabled(false);

            String json =
                    "{"
                            + "\"student_id\":" + studentId + ","
                            + "\"sleep_hours\":" + sleep + ","
                            + "\"water_intake\":" + water + ","
                            + "\"exercise_minutes\":" + exercise + ","
                            + "\"screen_time\":" + screen
                            + "}";

            new Thread(() -> {

                try {

                    String response =
                            ApiClient.updateLifestyle(studentId, json);

                    SwingUtilities.invokeLater(() -> {

                        updateButton.setEnabled(true);

                        JOptionPane.showMessageDialog(
                                this,
                                "Lifestyle updated successfully!",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                        loadLifestyle();
                    });

                } catch (Exception ex) {

                    SwingUtilities.invokeLater(() -> {

                        updateButton.setEnabled(true);

                        JOptionPane.showMessageDialog(
                                this,
                                "Failed to update lifestyle.\n\n"
                                        + ex.getMessage(),
                                "Update Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    });
                }

            }).start();

        } catch (NumberFormatException ex) {

            showError("Please enter valid numeric values.");
        }
    }

    // =========================================================
    // STATUS
    // =========================================================

    private void updateStatus(
            double sleep,
            double water,
            double exercise,
            double screen
    ) {

        boolean needsImprovement =
                sleep < 7.0
                        || water < 2.0
                        || exercise < 30.0
                        || screen > 8.0;

        if (needsImprovement) {

            statusLabel.setText("Status: Needs Improvement");

        } else {

            statusLabel.setText("Status: Healthy");
        }
    }

    // =========================================================
    // JSON NUMBER PARSER
    // =========================================================

    private double getNumber(String json, String key) {

        Pattern pattern = Pattern.compile(
                "\"" + Pattern.quote(key) + "\"\\s*:\\s*(-?\\d+(?:\\.\\d+)?)"
        );

        Matcher matcher = pattern.matcher(json);

        if (matcher.find()) {
            return Double.parseDouble(matcher.group(1));
        }

        return 0.0;
    }

    // =========================================================
    // UI HELPERS
    // =========================================================

    private JPanel createCard() {

        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.setBackground(Color.WHITE);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 225, 230)
                        ),
                        BorderFactory.createEmptyBorder(
                                20, 20, 20, 20
                        )
                )
        );

        return panel;
    }

    private JLabel createValueLabel() {

        JLabel label = new JLabel("--");

        label.setFont(
                new Font("Arial", Font.BOLD, 17)
        );

        return label;
    }

    private JPanel createRow(
            String name,
            JLabel value
    ) {

        JPanel row = new JPanel(new BorderLayout());

        row.setOpaque(false);

        JLabel nameLabel = new JLabel(name);

        nameLabel.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        row.add(nameLabel, BorderLayout.WEST);
        row.add(value, BorderLayout.EAST);

        return row;
    }

    private JPanel createInputRow(
            String name,
            JTextField field
    ) {

        JPanel row = new JPanel(new BorderLayout(10, 5));

        row.setOpaque(false);

        row.setMaximumSize(
                new Dimension(350, 45)
        );

        JLabel label = new JLabel(name);

        label.setPreferredSize(
                new Dimension(150, 30)
        );

        row.add(label, BorderLayout.WEST);
        row.add(field, BorderLayout.CENTER);

        return row;
    }

    private void showError(String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Invalid Input",
                JOptionPane.WARNING_MESSAGE
        );
    }
}