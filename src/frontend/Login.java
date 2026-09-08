package frontend;

import java.awt.*;
import javax.swing.*;

public class Login extends JFrame {

    // =====================================
    // COLORS
    // =====================================

    private static final Color BLUE =
            new Color(37, 99, 235);

    private static final Color DARK =
            new Color(15, 23, 42);

    private static final Color GRAY =
            new Color(100, 116, 139);

    private static final Color LIGHT =
            new Color(248, 250, 252);

    private static final Color GREEN =
            new Color(22, 163, 74);

    private static final Color RED =
            new Color(220, 38, 38);


    // =====================================
    // COMPONENTS
    // =====================================

    private JTextField studentIdField;

    private JButton loginButton;

    private JLabel statusLabel;


    // =====================================
    // CONSTRUCTOR
    // =====================================

    public Login() {

        setTitle(
                "Student Success Intelligence Platform"
        );

        setSize(
                560,
                650
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setResizable(false);

        createUI();
    }


    // =====================================
    // CREATE UI
    // =====================================

    private void createUI() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(
                LIGHT
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        35,
                        40,
                        30,
                        40
                )
        );


        // =====================================
        // HEADER
        // =====================================

        JPanel header =
                new JPanel();

        header.setLayout(
                new BoxLayout(
                        header,
                        BoxLayout.Y_AXIS
                )
        );

        header.setBackground(
                LIGHT
        );


        JLabel logo =
                new JLabel(
                        "SSIP"
                );

        logo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        42
                )
        );

        logo.setForeground(
                BLUE
        );

        logo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        JLabel title =
                new JLabel(
                        "Student Success"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        27
                )
        );

        title.setForeground(
                DARK
        );

        title.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        JLabel subtitle =
                new JLabel(
                        "Intelligence Platform"
                );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        19
                )
        );

        subtitle.setForeground(
                GRAY
        );

        subtitle.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        header.add(
                logo
        );

        header.add(
                Box.createVerticalStrut(8)
        );

        header.add(
                title
        );

        header.add(
                Box.createVerticalStrut(4)
        );

        header.add(
                subtitle
        );


        mainPanel.add(
                header,
                BorderLayout.NORTH
        );


        // =====================================
        // LOGIN CARD
        // =====================================

        JPanel card =
                new JPanel();

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        card.setBackground(
                Color.WHITE
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        226,
                                        232,
                                        240
                                ),
                                1
                        ),
                        BorderFactory.createEmptyBorder(
                                30,
                                35,
                                30,
                                35
                        )
                )
        );


        JLabel welcome =
                new JLabel(
                        "Welcome Back"
                );

        welcome.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        welcome.setForeground(
                DARK
        );

        welcome.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        JLabel instruction =
                new JLabel(
                        "Enter your Student ID to continue"
                );

        instruction.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        instruction.setForeground(
                GRAY
        );

        instruction.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        card.add(
                welcome
        );

        card.add(
                Box.createVerticalStrut(8)
        );

        card.add(
                instruction
        );

        card.add(
                Box.createVerticalStrut(28)
        );


        // =====================================
        // STUDENT ID LABEL
        // =====================================

        JLabel idLabel =
                new JLabel(
                        "Student ID"
                );

        idLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        idLabel.setForeground(
                DARK
        );

        idLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        card.add(
                idLabel
        );

        card.add(
                Box.createVerticalStrut(8)
        );


        // =====================================
        // STUDENT ID FIELD
        // =====================================

        studentIdField =
                new JTextField();

        studentIdField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        17
                )
        );

        studentIdField.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        45
                )
        );

        studentIdField.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        card.add(
                studentIdField
        );

        card.add(
                Box.createVerticalStrut(22)
        );


        // =====================================
        // LOGIN BUTTON
        // =====================================

        loginButton =
                new JButton(
                        "LOGIN"
                );

        loginButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        loginButton.setForeground(
                Color.WHITE
        );

        loginButton.setBackground(
                BLUE
        );

        loginButton.setFocusPainted(
                false
        );

        loginButton.setBorderPainted(
                false
        );

        loginButton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        loginButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        loginButton.setMaximumSize(
                new Dimension(
                        190,
                        45
                )
        );


        loginButton.addActionListener(
                event -> login()
        );


        card.add(
                loginButton
        );

        card.add(
                Box.createVerticalStrut(18)
        );


        // =====================================
        // STATUS
        // =====================================

        statusLabel =
                new JLabel(
                        "Ready"
                );

        statusLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        statusLabel.setForeground(
                GRAY
        );

        statusLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        card.add(
                statusLabel
        );


        // =====================================
        // CARD WRAPPER
        // =====================================

        JPanel cardWrapper =
                new JPanel(
                        new GridBagLayout()
                );

        cardWrapper.setBackground(
                LIGHT
        );

        cardWrapper.add(
                card
        );


        mainPanel.add(
                cardWrapper,
                BorderLayout.CENTER
        );


        // =====================================
        // FOOTER
        // =====================================

        JLabel footer =
                new JLabel(
                        "Student | Academic | Attendance | Lifestyle | Analytics"
                );

        footer.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        11
                )
        );

        footer.setForeground(
                GRAY
        );

        footer.setHorizontalAlignment(
                SwingConstants.CENTER
        );


        mainPanel.add(
                footer,
                BorderLayout.SOUTH
        );


        // =====================================
        // ENTER KEY
        // =====================================

        studentIdField.addActionListener(
                event -> login()
        );


        add(
                mainPanel
        );
    }


    // =====================================
    // LOGIN
    // =====================================

    private void login() {

        String text =
                studentIdField
                        .getText()
                        .trim();


        // =====================================
        // EMPTY CHECK
        // =====================================

        if (
                text.isEmpty()
        ) {

            showError(
                    "Please enter your Student ID."
            );

            return;
        }


        int studentId;


        // =====================================
        // NUMBER CHECK
        // =====================================

        try {

            studentId =
                    Integer.parseInt(
                            text
                    );

        } catch (
                NumberFormatException error
        ) {

            showError(
                    "Student ID must be a number."
            );

            return;
        }


        // =====================================
        // POSITIVE CHECK
        // =====================================

        if (
                studentId <= 0
        ) {

            showError(
                    "Student ID must be greater than 0."
            );

            return;
        }


        // =====================================
        // DISABLE BUTTON
        // =====================================

        loginButton.setEnabled(
                false
        );

        statusLabel.setText(
                "Connecting to backend..."
        );

        statusLabel.setForeground(
                BLUE
        );


        final int id =
                studentId;


        // =====================================
        // BACKEND CONNECTION
        // =====================================

        Thread thread =
                new Thread(
                        () -> {

                            try {

                                String response =
                                        ApiClient.healthCheck();


                                if (
                                        response == null
                                        || response.trim().isEmpty()
                                ) {

                                    throw new Exception(
                                            "Backend returned no response."
                                    );
                                }


                                SwingUtilities.invokeLater(
                                        () -> {

                                            statusLabel.setText(
                                                    "Backend connected!"
                                            );

                                            statusLabel.setForeground(
                                                    GREEN
                                            );


                                            Timer timer =
                                                    new Timer(
                                                            500,
                                                            event -> {

                                                                dispose();


                                                                Dashboard dashboard =
                                                                        new Dashboard(
                                                                                id
                                                                        );


                                                                dashboard.setVisible(
                                                                        true
                                                                );
                                                            }
                                                    );


                                            timer.setRepeats(
                                                    false
                                            );


                                            timer.start();
                                        }
                                );


                            } catch (
                                    Exception error
                            ) {

                                SwingUtilities.invokeLater(
                                        () -> {

                                            loginButton.setEnabled(
                                                    true
                                            );

                                            statusLabel.setText(
                                                    "Backend connection failed."
                                            );

                                            statusLabel.setForeground(
                                                    RED
                                            );


                                            JOptionPane.showMessageDialog(
                                                    this,

                                                    "Unable to connect to backend.\n\n"
                                                            + error.getMessage()
                                                            + "\n\n"
                                                            + "Make sure Flask is running on port 5000.",

                                                    "Backend Connection Error",

                                                    JOptionPane.ERROR_MESSAGE
                                            );
                                        }
                                );
                            }

                        }
                );


        thread.start();
    }


    // =====================================
    // ERROR MESSAGE
    // =====================================

    private void showError(
            String message
    ) {

        statusLabel.setText(
                message
        );

        statusLabel.setForeground(
                RED
        );


        JOptionPane.showMessageDialog(
                this,
                message,
                "Login",
                JOptionPane.WARNING_MESSAGE
        );
    }


    // =====================================
    // MAIN
    // =====================================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    Login login =
                            new Login();

                    login.setVisible(
                            true
                    );
                }
        );
    }
}
