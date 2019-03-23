package thiagodnf.doupr.gui.panel;

import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.Refactoring;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ShowImagePanel extends AbstractPanel {

    private static final long serialVersionUID = 8197793862612977066L;

    private BufferedImage image;

    public ShowImagePanel(String imagePath) {
        try {
//            setLayout(new BorderLayout());

            image = ImageIO.read(new File(imagePath));

        } catch (IOException ex) {
            // handle exception...
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = (this.getWidth() - image.getWidth(null)) / 2;

        g.drawImage(image, x, 0, 1000, 850, this); // see javadoc for more info on the parameters
    }


    @Override
    public void load(ProjectObject project, List<Refactoring> refactorings) {
        // Not implemented
    }
}


