import javax.swing.*;

public class GameFrame extends JFrame {
    GamePanel panel;
    GameFrame(){
        panel = new GamePanel();
        this.add(panel);

        this.setTitle("game");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }
}
