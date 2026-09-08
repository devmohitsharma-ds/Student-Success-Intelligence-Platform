package frontend;

import javax.swing.*;

public class AcademicTest {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame(
                    "SSIP - Academic Performance"
            );

            frame.setDefaultCloseOperation(
                    JFrame.EXIT_ON_CLOSE
            );

            frame.setSize(
                    1100,
                    700
            );

            frame.setLocationRelativeTo(null);

            frame.setContentPane(
                    new Academic(12)
            );

            frame.setVisible(true);
        });
    }
}
