package frontend;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Recommendation extends JPanel {

    private final int studentId;

    private JPanel recommendationsPanel;
    private JLabel statusLabel;
    private JLabel riskLabel;

    public Recommendation(int studentId) {

        this.studentId = studentId;

        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(245, 247, 250));
        setBorder(new EmptyBorder(25, 30, 25, 30));

        // =====================================================
        // HEADER
        // =====================================================

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setOpaque(false);

        JLabel title = new JLabel("Recommendations");
        title.setFont(new Font("Arial", Font.BOLD, 28));

        JLabel subtitle = new JLabel(
                "Personalized recommendations based on your academic and lifestyle performance."
        );
        subtitle.setFont(new Font("Arial", Font.PLAIN, 14));

        titlePanel.add(title);
        titlePanel.add(Box.createVerticalStrut(5));
        titlePanel.add(subtitle);

        header.add(titlePanel, BorderLayout.WEST);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setFocusPainted(false);
        refreshButton.addActionListener(e -> loadRecommendations());

        header.add(refreshButton, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        // =====================================================
        // MAIN PANEL
        // =====================================================

        JPanel mainPanel = new JPanel(new BorderLayout(0, 15));
        mainPanel.setOpaque(false);

        // Risk summary
        JPanel riskPanel = new JPanel(new BorderLayout());
        riskPanel.setBackground(Color.WHITE);
        riskPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 225, 230)
                        ),
                        new EmptyBorder(15, 20, 15, 20)
                )
        );

        JLabel riskTitle = new JLabel("Overall Risk");
        riskTitle.setFont(new Font("Arial", Font.BOLD, 15));

        riskLabel = new JLabel("Loading...");
        riskLabel.setFont(new Font("Arial", Font.BOLD, 18));

        riskPanel.add(riskTitle, BorderLayout.WEST);
        riskPanel.add(riskLabel, BorderLayout.EAST);

        mainPanel.add(riskPanel, BorderLayout.NORTH);

        // Recommendations
        recommendationsPanel = new JPanel();
        recommendationsPanel.setLayout(
                new BoxLayout(
                        recommendationsPanel,
                        BoxLayout.Y_AXIS
                )
        );
        recommendationsPanel.setBackground(
                new Color(245, 247, 250)
        );

        JScrollPane scrollPane = new JScrollPane(
                recommendationsPanel
        );

        scrollPane.setBorder(
                BorderFactory.createEmptyBorder()
        );

        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(16);

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        add(
                mainPanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // FOOTER
        // =====================================================

        JPanel footer = new JPanel(new BorderLayout());
        footer.setOpaque(false);

        statusLabel = new JLabel(
                "Loading recommendations..."
        );

        statusLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        footer.add(
                statusLabel,
                BorderLayout.WEST
        );

        add(
                footer,
                BorderLayout.SOUTH
        );

        // =====================================================
        // LOAD DATA
        // =====================================================

        loadRecommendations();
    }

    // =====================================================
    // LOAD RECOMMENDATIONS
    // =====================================================

    private void loadRecommendations() {

        statusLabel.setText(
                "Loading recommendations..."
        );

        riskLabel.setText("Loading...");

        recommendationsPanel.removeAll();

        JLabel loading = new JLabel(
                "Loading personalized recommendations..."
        );

        loading.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        recommendationsPanel.add(loading);

        recommendationsPanel.revalidate();
        recommendationsPanel.repaint();

        new Thread(() -> {

            try {

                String response =
                        ApiClient.getRecommendations(
                                studentId
                        );

                ArrayList<RecommendationData> recommendations =
                        extractRecommendations(response);

                String overallRisk =
                        extractString(
                                response,
                                "overall_risk"
                        );

                SwingUtilities.invokeLater(() -> {

                    recommendationsPanel.removeAll();

                    if (overallRisk.isEmpty()) {
                        riskLabel.setText("N/A");
                    } else {
                        riskLabel.setText(overallRisk);
                    }

                    if (recommendations.isEmpty()) {

                        addRecommendationCard(
                                "General",
                                "No specific recommendations were returned by the backend.",
                                "Continue monitoring your academic and lifestyle performance.",
                                "LOW"
                        );

                    } else {

                        for (
                                RecommendationData recommendation
                                : recommendations
                        ) {

                            addRecommendationCard(
                                    recommendation.category,
                                    recommendation.message,
                                    "Follow this suggestion regularly and review your progress.",
                                    recommendation.priority
                            );
                        }
                    }

                    statusLabel.setText(
                            recommendations.size()
                                    + " recommendation(s) available"
                    );

                    recommendationsPanel.revalidate();
                    recommendationsPanel.repaint();
                });

            } catch (Exception ex) {

                SwingUtilities.invokeLater(() -> {

                    recommendationsPanel.removeAll();

                    addRecommendationCard(
                            "Unable to Load",
                            "Could not retrieve recommendations from the backend.",
                            ex.getMessage() == null
                                    ? "Please check that the backend is running."
                                    : ex.getMessage(),
                            "ERROR"
                    );

                    riskLabel.setText("N/A");

                    statusLabel.setText(
                            "Failed to load recommendations"
                    );

                    recommendationsPanel.revalidate();
                    recommendationsPanel.repaint();
                });
            }

        }).start();
    }

    // =====================================================
    // RECOMMENDATION DATA
    // =====================================================

    private static class RecommendationData {

        String category;
        String message;
        String priority;

        RecommendationData(
                String category,
                String message,
                String priority
        ) {

            this.category = category;
            this.message = message;
            this.priority = priority;
        }
    }

    // =====================================================
    // EXTRACT RECOMMENDATIONS
    // =====================================================

    private ArrayList<RecommendationData> extractRecommendations(
            String json
    ) {

        ArrayList<RecommendationData> result =
                new ArrayList<>();

        Pattern objectPattern =
                Pattern.compile(
                        "\\{\\s*\"category\"\\s*:\\s*\"((?:\\\\.|[^\"\\\\])*)\"\\s*,\\s*"
                                + "\"message\"\\s*:\\s*\"((?:\\\\.|[^\"\\\\])*)\"\\s*,\\s*"
                                + "\"priority\"\\s*:\\s*\"((?:\\\\.|[^\"\\\\])*)\"\\s*\\}"
                );

        Matcher matcher =
                objectPattern.matcher(json);

        while (matcher.find()) {

            String category =
                    cleanJsonText(
                            matcher.group(1)
                    );

            String message =
                    cleanJsonText(
                            matcher.group(2)
                    );

            String priority =
                    cleanJsonText(
                            matcher.group(3)
                    );

            if (!message.isEmpty()) {

                result.add(
                        new RecommendationData(
                                category,
                                message,
                                priority
                        )
                );
            }
        }

        return result;
    }

    // =====================================================
    // EXTRACT STRING
    // =====================================================

    private String extractString(
            String json,
            String key
    ) {

        Pattern pattern =
                Pattern.compile(
                        "\"" + Pattern.quote(key)
                                + "\"\\s*:\\s*\"((?:\\\\.|[^\"\\\\])*)\""
                );

        Matcher matcher =
                pattern.matcher(json);

        if (matcher.find()) {

            return cleanJsonText(
                    matcher.group(1)
            );
        }

        return "";
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

    // =====================================================
    // CREATE RECOMMENDATION CARD
    // =====================================================

    private void addRecommendationCard(
            String category,
            String recommendation,
            String explanation,
            String priority
    ) {

        JPanel card =
                new JPanel();

        card.setLayout(
                new BorderLayout(15, 10)
        );

        card.setBackground(Color.WHITE);

        card.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        220,
                                        225,
                                        230
                                )
                        ),
                        new EmptyBorder(
                                18,
                                20,
                                18,
                                20
                        )
                )
        );

        // =====================================================
        // CARD TOP
        // =====================================================

        JPanel topPanel =
                new JPanel(
                        new BorderLayout()
                );

        topPanel.setOpaque(false);

        JLabel categoryLabel =
                new JLabel(category);

        categoryLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        17
                )
        );

        JLabel priorityLabel =
                new JLabel(priority);

        priorityLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        topPanel.add(
                categoryLabel,
                BorderLayout.WEST
        );

        topPanel.add(
                priorityLabel,
                BorderLayout.EAST
        );

        // =====================================================
        // MESSAGE
        // =====================================================

        JLabel recommendationLabel =
                new JLabel(
                        "<html><div style='width:650px;'>"
                                + escapeHtml(
                                        recommendation
                                )
                                + "</div></html>"
                );

        recommendationLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        // =====================================================
        // EXPLANATION
        // =====================================================

        JLabel explanationLabel =
                new JLabel(
                        "<html><div style='width:650px;'>"
                                + escapeHtml(
                                        explanation
                                )
                                + "</div></html>"
                );

        explanationLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        JPanel content =
                new JPanel();

        content.setLayout(
                new BoxLayout(
                        content,
                        BoxLayout.Y_AXIS
                )
        );

        content.setOpaque(false);

        content.add(
                topPanel
        );

        content.add(
                Box.createVerticalStrut(10)
        );

        content.add(
                recommendationLabel
        );

        content.add(
                Box.createVerticalStrut(8)
        );

        content.add(
                explanationLabel
        );

        card.add(
                content,
                BorderLayout.CENTER
        );

        recommendationsPanel.add(card);

        recommendationsPanel.add(
                Box.createVerticalStrut(12)
        );
    }

    // =====================================================
    // HTML ESCAPE
    // =====================================================

    private String escapeHtml(
            String text
    ) {

        return text
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }
}
