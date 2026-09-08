import frontend.Lifestyle;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class LifestyleTest {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JFrame frame =
                    new JFrame("SSIP - Lifestyle");

            frame.setDefaultCloseOperation(
                    JFrame.EXIT_ON_CLOSE
            );

            frame.setSize(
                    1100,
                    750
            );

            frame.setLocationRelativeTo(null);

            frame.add(
                    new Lifestyle(12)
            );

            frame.setVisible(true);
        });
    }
}
