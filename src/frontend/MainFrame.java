package frontend;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static final Color SIDEBAR =
            new Color(15, 23, 42);

    private static final Color SIDEBAR_HOVER =
            new Color(30, 41, 59);

    private static final Color BLUE =
            new Color(37, 99, 235);

    private static final Color BACKGROUND =
            new Color(245, 247, 250);

    private static final Color WHITE =
            Color.WHITE;

    private static final Color TEXT =
            new Color(31, 41, 55);

    private static final Color MUTED =
            new Color(107, 114, 128);

    private final int studentId;

    private JPanel contentPanel;
    private JLabel pageTitle;

    public MainFrame(int studentId) {

        this.studentId = studentId;

        setTitle(
                "Student Success Intelligence Platform"
        );

        setSize(
                1250,
                800
        );

        setMinimumSize(
                new Dimension(
                        1050,
                        700
                )
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        createUI();

        showDashboard();
    }


    // =====================================
    // CREATE UI
    // =====================================

    private void createUI() {

        JPanel root =
                new JPanel(
                        new BorderLayout()
                );

        root.setBackground(
                BACKGROUND
        );

        root.add(
                createSidebar(),
                BorderLayout.WEST
        );


        JPanel mainArea =
                new JPanel(
                        new BorderLayout()
                );

        mainArea.setBackground(
                BACKGROUND
        );


        // =====================================
        // TOP BAR
        // =====================================

        JPanel topBar =
                new JPanel(
                        new BorderLayout()
                );

        topBar.setBackground(
                WHITE
        );

        topBar.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        22,
                        15,
                        22
                )
        );


        pageTitle =
                new JLabel(
                        "Dashboard"
                );

        pageTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        pageTitle.setForeground(
                TEXT
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

        studentLabel.setForeground(
                MUTED
        );


        topBar.add(
                pageTitle,
                BorderLayout.WEST
        );

        topBar.add(
                studentLabel,
                BorderLayout.EAST
        );


        mainArea.add(
                topBar,
                BorderLayout.NORTH
        );


        // =====================================
        // CONTENT
        // =====================================

        contentPanel =
                new JPanel(
                        new BorderLayout()
                );

        contentPanel.setBackground(
                BACKGROUND
        );

        contentPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        18,
                        18,
                        18,
                        18
                )
        );

        mainArea.add(
                contentPanel,
                BorderLayout.CENTER
        );


        root.add(
                mainArea,
                BorderLayout.CENTER
        );


        add(root);
    }


    // =====================================
    // SIDEBAR
    // =====================================

    private JPanel createSidebar() {

        JPanel sidebar =
                new JPanel(
                        new BorderLayout()
                );

        sidebar.setPreferredSize(
                new Dimension(
                        220,
                        0
                )
        );

        sidebar.setBackground(
                SIDEBAR
        );


        // =====================================
        // LOGO
        // =====================================

        JPanel logoPanel =
                new JPanel();

        logoPanel.setLayout(
                new BoxLayout(
                        logoPanel,
                        BoxLayout.Y_AXIS
                )
        );

        logoPanel.setBackground(
                SIDEBAR
        );

        logoPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        20,
                        20,
                        20
                )
        );


        JLabel logo =
                new JLabel(
                        "SSIP"
                );

        logo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );

        logo.setForeground(
                WHITE
        );

        logo.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        JLabel subtitle =
                new JLabel(
                        "Student Success Platform"
                );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        11
                )
        );

        subtitle.setForeground(
                new Color(
                        148,
                        163,
                        184
                )
        );

        subtitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        logoPanel.add(logo);

        logoPanel.add(
                Box.createVerticalStrut(4)
        );

        logoPanel.add(subtitle);


        sidebar.add(
                logoPanel,
                BorderLayout.NORTH
        );


        // =====================================
        // NAVIGATION
        // =====================================

        JPanel navigation =
                new JPanel();

        navigation.setLayout(
                new BoxLayout(
                        navigation,
                        BoxLayout.Y_AXIS
                )
        );

        navigation.setBackground(
                SIDEBAR
        );

        navigation.setBorder(
                BorderFactory.createEmptyBorder(
                        5,
                        12,
                        10,
                        12
                )
        );


        addNavigationButton(
                navigation,
                "Dashboard",
                this::showDashboard
        );

        addNavigationButton(
                navigation,
                "Student Management",
                this::showStudentManagement
        );

        addNavigationButton(
                navigation,
                "Student Profile",
                this::showProfile
        );

        addNavigationButton(
                navigation,
                "Academic",
                this::showAcademic
        );

        addNavigationButton(
                navigation,
                "Attendance",
                this::showAttendance
        );

        addNavigationButton(
                navigation,
                "Lifestyle",
                this::showLifestyle
        );

        addNavigationButton(
                navigation,
                "Analytics",
                this::showAnalytics
        );

        addNavigationButton(
                navigation,
                "Recommendations",
                this::showRecommendations
        );

        addNavigationButton(
                navigation,
                "Reports",
                this::showReports
        );

        addNavigationButton(
                navigation,
                "Settings",
                this::showSettings
        );


        sidebar.add(
                navigation,
                BorderLayout.CENTER
        );


        // =====================================
        // LOGOUT
        // =====================================

        JPanel bottom =
                new JPanel();

        bottom.setLayout(
                new BoxLayout(
                        bottom,
                        BoxLayout.Y_AXIS
                )
        );

        bottom.setBackground(
                SIDEBAR
        );

        bottom.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        12,
                        20,
                        12
                )
        );


        JButton logout =
                createNavigationButton(
                        "Logout"
                );

        logout.addActionListener(
                event -> logout()
        );

        bottom.add(logout);


        sidebar.add(
                bottom,
                BorderLayout.SOUTH
        );


        return sidebar;
    }


    // =====================================
    // NAVIGATION BUTTON
    // =====================================

    private void addNavigationButton(
            JPanel panel,
            String text,
            Runnable action
    ) {

        JButton button =
                createNavigationButton(
                        text
                );

        button.addActionListener(
                event -> action.run()
        );

        panel.add(button);

        panel.add(
                Box.createVerticalStrut(5)
        );
    }


    private JButton createNavigationButton(
            String text
    ) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        button.setForeground(
                new Color(
                        226,
                        232,
                        240
                )
        );

        button.setBackground(
                SIDEBAR
        );

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        42
                )
        );


        button.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            java.awt.event.MouseEvent event
                    ) {

                        button.setBackground(
                                SIDEBAR_HOVER
                        );
                    }


                    @Override
                    public void mouseExited(
                            java.awt.event.MouseEvent event
                    ) {

                        button.setBackground(
                                SIDEBAR
                        );
                    }
                }
        );


        return button;
    }


    // =====================================
    // SET PAGE
    // =====================================

    private void setPage(
            String title,
            Component component
    ) {

        pageTitle.setText(title);

        contentPanel.removeAll();

        contentPanel.add(
                component,
                BorderLayout.CENTER
        );

        contentPanel.revalidate();

        contentPanel.repaint();
    }


    // =====================================
    // DASHBOARD
    // =====================================

   private void showDashboard() {

     setPage(
            "Dashboard",
            new Dashboard(studentId)
     );
  }
 

    // =====================================
    // STUDENT MANAGEMENT
    // =====================================

    private void showStudentManagement() {

        setPage(
                "Student Management",
                new StudentManagement(studentId)
        );
    }


    // =====================================
    // PROFILE
    // =====================================

     private void showProfile() {
 
     setPage(
             "Student Profile",
             new Profile(studentId)
     );
 }


    // =====================================
    // ACADEMIC
    // =====================================

      private void showAcademic() {

      setPage(
              "Academic Performance",
              new Academic(studentId)
      );
  }


    // =====================================
    // ATTENDANCE
    // =====================================

      private void showAttendance() {
 
      setPage(
              "Attendance",
              new Attendance(studentId)
       );
   }


    // =====================================
    // LIFESTYLE
    // =====================================

      private void showLifestyle() {
       setPage(
           "Lifestyle",
           new Lifestyle(studentId)
       );
   }


    // =====================================
    // ANALYTICS
    // =====================================

      private void showAnalytics() {
       setPage(
           "Analytics",
           new Analytics(studentId)
       );
   }


    // =====================================
    // RECOMMENDATIONS
    // =====================================

      private void showRecommendations() {
        setPage(
            "Recommendations",
            new Recommendation(studentId)
        );
    }


    // =====================================
    // REPORTS
    // =====================================

       private void showReports() {
        setPage(
            "Reports",
            new Reports(studentId)
        );
    }


    // =====================================
    // SETTINGS
    // =====================================
    private void showSettings() {
        setPage(
                "Settings",
                new Settings(studentId)
        );
    }

    

    private void logout() {

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to logout?",
                        "Logout",
                        JOptionPane.YES_NO_OPTION
                );


        if (
                result ==
                        JOptionPane.YES_OPTION
        ) {

            dispose();

            Login login =
                    new Login();

            login.setVisible(true);
        }
    }


    // =====================================
    // MAIN
    // =====================================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    MainFrame frame =
                            new MainFrame(12);

                    frame.setVisible(true);
                }
        );
    }
}
