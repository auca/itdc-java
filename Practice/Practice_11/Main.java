import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		
		frame.setSize(400, 400);
		frame.setVisible(true);
		
		JButton selectCircleButton = new JButton("Окружность");
		selectCircleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Кнопка окружность была кликнута");
			}
		});
		JButton selectRectangleButton = new JButton("Прямоугольник");
		selectRectangleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Кнопка прямоугольник была кликнута");
			}
		});
		
		JPanel canvas = new JPanel();
		canvas.setBackground(Color.BLACK);
		
		JPanel toolbar = new JPanel();
		toolbar.setLayout(new FlowLayout());
		toolbar.add(selectCircleButton);
		toolbar.add(selectRectangleButton);
		
		frame.add(canvas, BorderLayout.CENTER);
		frame.add(toolbar, BorderLayout.SOUTH);
	}

}
