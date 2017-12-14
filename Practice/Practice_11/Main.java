import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

public class Main {
    private Shape selectedShape;
    private Canvas canvas;

    public Main() {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setTitle("Фигуры");

        frame.setSize(400, 400);

        canvas = new Canvas();
        canvas.setBackground(Color.BLACK);

        selectedShape = null;
        shapes = new ArrayList<Shape>();

        JButton selectCircleButton =
            new JButton("Окружность");
        selectCircleButton.addActionListener(e -> {
            selectedShape = createDefaultCircle(canvas);
            canvas.repaint();
        });

        JButton selectRectangleButton =
            new JButton("Прямоугольник");
        selectRectangleButton.addActionListener(e -> {
            selectedShape = createDefaultRectangle(canvas);
            canvas.repaint();
        });

        JPanel toolbar = new JPanel();
        toolbar.setLayout(new FlowLayout());
        toolbar.add(selectCircleButton);
        toolbar.add(selectRectangleButton);

        frame.add(canvas, BorderLayout.CENTER);
        frame.add(toolbar, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    class Canvas extends JPanel {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (selectedShape != null) {
                selectedShape.draw(g);
            }
        }
    }

    private static Shape createDefaultCircle(Canvas canvas) {
        int margin = canvas.getWidth() / 20;
        int radius = canvas.getWidth() / 30;

        return new Circle(
            margin,
            margin,
            radius,
            Color.WHITE
        );
    }

    private static Shape createDefaultRectangle(Canvas canvas) {
        int margin = canvas.getWidth() / 20;
        int size = canvas.getWidth() / 15;
        margin -= size / 2;

        return new Rectangle(
            margin,
            margin,
            size,
            size,
            Color.WHITE
        );
    }

    public static void main(String[] args) {
        new Main();
    }

}

