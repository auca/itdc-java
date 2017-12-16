import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Color;

import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.event.MouseInputAdapter;

public class Main {
    private static final String SAVE_STATE_PATH = "shapes.bin";

	private static final String DATABASE_HOST = "localhost";
	private static final String DATABASE_NAME = "shapes";
	private static final String DATABASE_USER = "shapes_user";

    private Shape selectedShape;
    private ArrayList<Shape> shapes;

    private Canvas canvas;
    
    private static Connection dbConnection = null;
    private static Connection getConnection() {
    	if (dbConnection == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				dbConnection =
					DriverManager.getConnection(
						"jdbc:mysql://" + DATABASE_HOST +
						"/"             + DATABASE_NAME +
						"?user="        + DATABASE_USER 
					);
			} catch(Exception e) {
				System.err.println("Не смогли подключиться к базе данных");
				e.printStackTrace();
			}
    	}
		
		return dbConnection;
    }
    
    private static void closeConnection() {
    	try {
			if (dbConnection != null) {
				dbConnection.close();
			}
		} catch (SQLException e) {
			System.err.println("Не смогли закрыть подключение к базе данных");
		}
    }

    public Main() {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setTitle("Фигуры");

        frame.setSize(400, 400);

        selectedShape = null;
        shapes = loadShapesFromDB();

        canvas = new Canvas();
        canvas.setBackground(Color.BLACK);

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

        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            	saveShapesToDB(shapes);
            	closeConnection();

                System.exit(0);
            }
        });

        frame.add(canvas, BorderLayout.CENTER);
        frame.add(toolbar, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    class Canvas extends JPanel {
		private static final long serialVersionUID = 1L;

		public Canvas() {
            MouseInputAdapter adapter = new MouseInputAdapter() {
                public void mouseDragged(MouseEvent e) {
                    addShape(e.getX(), e.getY());
                }

                public void mousePressed(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        addShape(e.getX(), e.getY());
                    }
                }
            };

            addMouseListener(adapter);
            addMouseMotionListener(adapter);

            repaint();
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            for (Shape shape : shapes) {
                shape.draw(g);
            }

            if (selectedShape != null) {
                selectedShape.draw(g);
            }
        }

        private void addShape(int x, int y) {
            if (selectedShape != null) {
                Shape shape = (Shape) selectedShape.clone();
                shape.setX(x);
                shape.setY(y);
                shapes.add(shape);

                repaint();
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

	private static ArrayList<Shape> loadShapesFromDB() {
        ArrayList<Shape> shapes = new ArrayList<Shape>();
        
        Connection connection = null;
		try {
			ResultSet recordSet =
				getConnection().createStatement().executeQuery("SELECT * FROM circles");

			while (recordSet.next()) {
				int id = recordSet.getInt("id");
				int x = recordSet.getInt("x");
				int y = recordSet.getInt("y");
				int radius = recordSet.getInt("radius");
				
				Circle circle = new Circle(x, y, radius);
				circle.setId(id);
				
				shapes.add(circle);
			}
			
			recordSet =
				getConnection().createStatement().executeQuery("SELECT * FROM rectangles");

			while (recordSet.next()) {
				int id = recordSet.getInt("id");
				int x = recordSet.getInt("x");
				int y = recordSet.getInt("y");
				int width = recordSet.getInt("width");
				int height = recordSet.getInt("height");
				
				Rectangle rectangle = new Rectangle(x, y, width, height);
				rectangle.setId(id);
				
				shapes.add(rectangle);
			}
		} catch(Exception e) {
			System.err.println("Не смогли загрузить информацию из базы данных");
			e.printStackTrace();
		}

        return shapes;
    }

    private static void saveShapesToDB(ArrayList<Shape> shapes) {
		try {
			for (Shape shape : shapes) {
				getConnection().createStatement().execute(
					"INSERT INTO " + shape
				);
			}
		} catch(Exception e) {
			System.err.println("Не смогли сохранить информацию в базу данных");
			e.printStackTrace();
		}
    }
    
	private static ArrayList<Shape> loadShapes(String path) {
        ArrayList<Shape> shapes = new ArrayList<Shape>();

        ObjectInputStream inputStream = null;
        try {
            inputStream =
                new ObjectInputStream(new FileInputStream(path));

            shapes = (ArrayList<Shape>) inputStream.readObject();
        } catch (IOException e) {
            System.err.println("Не смогли загрузить файл.");
        } catch (ClassNotFoundException e) {
            System.err.println("Не смогли декодировать файл в объект.");
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    System.err.println("Не смогли закрыть файл.");
                }
            }
        }

        return shapes;
    }

    private static void saveShapes(ArrayList<Shape> shapes, String path) {
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(path));
            outputStream.writeObject(shapes);
        } catch (IOException e) {
            System.err.println("Не смогли сохранить файл.");
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    System.err.println("Не смогли закрыть файл.");
                }
            }
        }
    }

    public static void main(String[] args) {
        new Main();
    }

}
