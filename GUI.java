import javax.swing.*;
import javax.swing.SwingUtilities;
import java.io.IOException;

public class GUI {
    static int width = 740;
    static int height = 640;

    private JFrame window;
    public GUI(){

        window = new JFrame ("ParserText");
        window.setSize(width, height);
        try {
            window.add(new Panel());
        } catch (IOException e) {
            e.printStackTrace();
        }
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

    }

    public static void gui() {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                new GUI();
            }
        });
    }
}
