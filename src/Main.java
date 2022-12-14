// Import java swing to create what user see
import javax.swing.JFrame;

public class Main{

    public static void main(String[] args) {

        JFrame window = new JFrame();

        // User when close the game screen when click the X button
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Not allow to resize the game screen
        window.setResizable(false);

        // Set the title of the game
        window.setTitle("Monopoly Universe");

        GamePanels gamePanels = new GamePanels();
        window.add(gamePanels);
        window.pack(); // Causes this window to be sized to fit the preferred size and layouts of its subcomponents (GamePanels)

        // Displayed at the center of the screen due to null parameter.
        window.setLocationRelativeTo(null);

        // Set to see this window
        window.setVisible(true);

        gamePanels.startGameThread();

    }

}