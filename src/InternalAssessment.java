package internalassessment;

import backend.list.List;
import backend.Plant;
import javax.swing.SwingUtilities;

/**
 *
 * @author chonk
 */
public class InternalAssessment {
    public static List<Plant> plants;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO: Set the look and feel of the window

        // Create the MainMenu window
        javax.swing.SwingUtilities.invokeLater(new frontend.MainMenu());
    }
}
