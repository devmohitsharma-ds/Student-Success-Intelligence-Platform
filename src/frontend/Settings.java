package frontend;

import javax.swing.*;
import java.awt.*;

public class Settings extends JPanel {

    private final int studentId;

    private JCheckBox notificationBox;
    private JCheckBox autoRefreshBox;
    private JComboBox<String> refreshIntervalBox;

    public Settings(int studentId) {

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

        JLabel title = new JLabel("Settings");

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        JLabel subtitle = new JLabel(
                "Manage your application preferences."
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
        // SETTINGS CARD
        // =========================

        JPanel settingsCard =
                new JPanel();

        settingsCard.setLayout(
                new BoxLayout(
                        settingsCard,
                        BoxLayout.Y_AXIS
                )
        );

        settingsCard.setBackground(Color.WHITE);

        settingsCard.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        220,
                                        225,
                                        230
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                25,
                                25,
                                25,
                                25
                        )
                )
        );

        settingsCard.setMaximumSize(
                new Dimension(
                        700,
                        500
                )
        );

        // =========================
        // ACCOUNT
        // =========================

        JLabel accountTitle =
                new JLabel("Account");

        accountTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        JLabel studentLabel =
                new JLabel(
                        "Student ID: " + studentId
                );

        studentLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        settingsCard.add(accountTitle);

        settingsCard.add(
                Box.createVerticalStrut(10)
        );

        settingsCard.add(studentLabel);

        settingsCard.add(
                Box.createVerticalStrut(25)
        );

        // =========================
        // NOTIFICATIONS
        // =========================

        JLabel notificationTitle =
                new JLabel("Notifications");

        notificationTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        notificationBox =
                new JCheckBox(
                        "Enable notifications"
                );

        notificationBox.setSelected(true);
        notificationBox.setOpaque(false);

        settingsCard.add(
                notificationTitle
        );

        settingsCard.add(
                Box.createVerticalStrut(10)
        );

        settingsCard.add(
                notificationBox
        );

        settingsCard.add(
                Box.createVerticalStrut(25)
        );

        // =========================
        // AUTO REFRESH
        // =========================

        JLabel refreshTitle =
                new JLabel(
                        "Dashboard Refresh"
                );

        refreshTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        autoRefreshBox =
                new JCheckBox(
                        "Enable automatic refresh"
                );

        autoRefreshBox.setSelected(true);
        autoRefreshBox.setOpaque(false);

        String[] intervals = {
                "30 seconds",
                "1 minute",
                "5 minutes",
                "10 minutes"
        };

        refreshIntervalBox =
                new JComboBox<>(
                        intervals
                );

        refreshIntervalBox.setSelectedItem(
                "5 minutes"
        );

        JPanel intervalPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT
                        )
                );

        intervalPanel.setOpaque(false);

        JLabel intervalLabel =
                new JLabel(
                        "Refresh interval:"
                );

        intervalPanel.add(
                intervalLabel
        );

        intervalPanel.add(
                refreshIntervalBox
        );

        settingsCard.add(
                refreshTitle
        );

        settingsCard.add(
                Box.createVerticalStrut(10)
        );

        settingsCard.add(
                autoRefreshBox
        );

        settingsCard.add(
                intervalPanel
        );

        settingsCard.add(
                Box.createVerticalStrut(25)
        );

        // =========================
        // SAVE BUTTON
        // =========================

        JButton saveButton =
                new JButton(
                        "Save Settings"
                );

        saveButton.setFocusPainted(false);

        saveButton.addActionListener(
                e -> saveSettings()
        );

        settingsCard.add(
                saveButton
        );

        // =========================
        // RESET BUTTON
        // =========================

        JButton resetButton =
                new JButton(
                        "Reset to Default"
                );

        resetButton.setFocusPainted(false);

        resetButton.addActionListener(
                e -> resetSettings()
        );

        settingsCard.add(
                Box.createVerticalStrut(10)
        );

        settingsCard.add(
                resetButton
        );

        // =========================
        // CENTER WRAPPER
        // =========================

        JPanel wrapper =
                new JPanel(
                        new GridBagLayout()
                );

        wrapper.setOpaque(false);

        wrapper.add(settingsCard);

        add(
                header,
                BorderLayout.NORTH
        );

        add(
                wrapper,
                BorderLayout.CENTER
        );
    }

    // =====================================================
    // SAVE SETTINGS
    // =====================================================

    private void saveSettings() {

        JOptionPane.showMessageDialog(
                this,
                "Settings saved successfully!",
                "Settings",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // =====================================================
    // RESET SETTINGS
    // =====================================================

    private void resetSettings() {

        notificationBox.setSelected(true);

        autoRefreshBox.setSelected(true);

        refreshIntervalBox.setSelectedItem(
                "5 minutes"
        );

        JOptionPane.showMessageDialog(
                this,
                "Settings restored to default.",
                "Settings",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}