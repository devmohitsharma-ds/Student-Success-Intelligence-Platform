import frontend.Attendance;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class AttendanceTest {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JFrame frame =
                    new JFrame("SSIP - Attendance");

            frame.setDefaultCloseOperation(
                    JFrame.EXIT_ON_CLOSE
            );

            frame.setSize(
                    1100,
                    700
            );

            frame.setLocationRelativeTo(null);

            frame.add(
                    new Attendance(12)
            );

            frame.setVisible(true);
        });
    }
}
