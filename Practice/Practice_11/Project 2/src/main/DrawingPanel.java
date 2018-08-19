package main;

import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel {

    private Drawable[] thingsToDraw;
    public void setThingsToDraw(Drawable[] thingsToDraw) {
        this.thingsToDraw = thingsToDraw;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (thingsToDraw != null) {
            Graphics2D g2 = (Graphics2D) g;
            for (Drawable thing : thingsToDraw) {
                thing.draw(g2);
            }
        }
    }
}
