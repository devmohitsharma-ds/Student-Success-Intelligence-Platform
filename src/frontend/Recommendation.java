package frontend;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Recommendation extends JPanel {

    private final int studentId;

    private JPanel recommendationsPanel;
    private JLabel statusLabel;

    public Recommendation(int studentId) {

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

        JLabel title = new JLabel("Recommendations");
        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        JLabel subtitle = new JLabel(
                "Personalized recommendations based on your performance."
        );
        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        titlePanel.add(title);
        titlePanel.add(Box.createVerticalStrut(5));
        titlePanel.add(subtitle);

        header.add(
                titlePanel,
                BorderLayout.WEST
        );

        JButton refreshButton =
                new JButton("Refresh");

        refreshButton.setFocusPainted(false);
        refreshButton.addActionListener(
                e -> loadRecommendations()
        );

        header.add(
                refreshButton,
                BorderLayout.EAST
        );

        // =========================
        // STATUS
        // =========================

        statusLabel =
                new JLabel("Loading recommendations...");

        statusLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        // =========================
        // RECOMMENDATIONS PANEL
        // =========================

        recommendationsPanel =
                new JPanel();

        recommendationsPanel.setLayout(
                new BoxLayout(
                        recommendationsPanel,
                        BoxLayout.Y_AXIS
                )
        );

        recommendationsPanel.setBackground(
                new Color(
                        245,
                        247,
                        250
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        recommendationsPanel
                );

        scrollPane.setBorder(
                BorderFactory.createEmptyBorder()
        );

        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(16);

        // =========================
        // FOOTER
        // =========================

        JPanel footer =
                new JPanel(
                        new BorderLayout()
                );

        footer.setOpaque(false);

        footer.add(
                statusLabel,
                BorderLayout.WEST
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
                footer,
                BorderLayout.SOUTH
        );

        // =========================
        // LOAD DATA
        // =========================

        loadRecommendations();
    }

    // =====================================================
    // LOAD RECOMMENDATIONS
    // =====================================================

    private void loadRecommendations() {

        statusLabel.setText(
                "Loading recommendations..."
        );

        recommendationsPanel.removeAll();

        JLabel loading =
                new JLabel(
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

                ArrayList<String> recommendations =
                        extractRecommendations(
                                response
                        );

                SwingUtilities.invokeLater(() -> {

                    recommendationsPanel.removeAll();

                    if (recommendations.isEmpty()) {

                        addRecommendationCard(
                                "General",
                                "No specific recommendations were returned by the backend.",
                                "Continue monitoring your academic and lifestyle performance."
                        );

                    } else {

                        int number = 1;

                        for (String recommendation :
                                recommendations) {

                            addRecommendationCard(
                                    "Recommendation "
                                            + number,
                                    recommendation,
                                    "Follow this suggestion regularly and review your progress."
                            );

                            number++;
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
                            ex.getMessage()
                    );

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
    // EXTRACT RECOMMENDATIONS FROM JSON
    // =====================================================

    private ArrayList<String> extractRecommendations(
            String json
    ) {

        ArrayList<String> result =
                new ArrayList<>();

        /*
         * Expected backend format may contain:
         *
         * "recommendations": [
         *     "Improve attendance",
         *     "Increase sleep"
         * ]
         *
         * This parser also handles simple recommendation
         * string fields.
         */

        Pattern arrayPattern =
                Pattern.compile(
                        "\"recommendations\"\\s*:\\s*\\[(.*?)\\]",
                        Pattern.DOTALL
                );

        Matcher arrayMatcher =
                arrayPattern.matcher(json);

        if (arrayMatcher.find()) {

            String arrayContent =
                    arrayMatcher.group(1);

            Pattern itemPattern =
                    Pattern.compile(
                            "\"((?:\\\\.|[^\"\\\\])*)\""
                    );

            Matcher itemMatcher =
                    itemPattern.matcher(
                            arrayContent
                    );

            while (itemMatcher.find()) {

                String text =
                        itemMatcher.group(1);

                text =
                        cleanJsonText(text);

                if (!text.isEmpty()) {

                    result.add(text);
                }
            }
        }

        /*
         * If the backend returns an object/list with
         * "recommendation" instead of "recommendations".
         */

        if (result.isEmpty()) {

            Pattern singlePattern =
                    Pattern.compile(
                            "\"recommendation\"\\s*:\\s*\"((?:\\\\.|[^\"\\\\])*)\""
                    );

            Matcher matcher =
                    singlePattern.matcher(json);

            while (matcher.find()) {

                String text =
                        cleanJsonText(
                                matcher.group(1)
                        );

                if (!text.isEmpty()) {

                    result.add(text);
                }
            }
        }

        /*
         * Some APIs may return a "recommendations"
         * object containing category/message fields.
         */

        if (result.isEmpty()) {

            Pattern messagePattern =
                    Pattern.compile(
                            "\"message\"\\s*:\\s*\"((?:\\\\.|[^\"\\\\])*)\""
                    );

            Matcher matcher =
                    messagePattern.matcher(json);

            while (matcher.find()) {

                String text =
                        cleanJsonText(
                                matcher.group(1)
                        );

                if (!text.isEmpty()) {

                    result.add(text);
                }
            }
        }

        return result;
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
            String title,
            String recommendation,
            String explanation
    ) {

        JPanel card =
                new JPanel();

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
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
                        BorderFactory.createEmptyBorder(
                                18,
                                20,
                                18,
                                20
                        )
                )
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        17
                )
        );

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

        card.add(titleLabel);

        card.add(
                Box.createVerticalStrut(10)
        );

        card.add(
                recommendationLabel
        );

        card.add(
                Box.createVerticalStrut(8)
        );

        card.add(
                explanationLabel
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