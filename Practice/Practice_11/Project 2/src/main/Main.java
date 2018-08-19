package main;

import main.graphics.Shape;
import main.graphics.Circle;
import main.graphics.Rectangle;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Main {

    private static final int RECTANGULAR_ROOM_TYPE = 0;
    private static final int CIRCULAR_ROOM_TYPE    = 1;

    private JPanel mainPanel;
    private JList roomList;
    private JTextField roomNameText;
    private JTextArea roomDescriptionText;
    private JButton addRoomButton;
    private JButton removeRoomButton;
    private JPanel drawingPanel;
    private JTextField roomXTextField;
    private JTextField roomYTextField;
    private JComboBox roomTypeComboBox;

    private DefaultListModel<Room> rooms;

    public Main() {
        rooms = loadRoomData();
        roomList.setModel(rooms);

        addRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!roomNameText.getText().isEmpty()) {
                    int roomType = roomTypeComboBox.getSelectedIndex();

                    int x, y;
                    try {
                        x = Integer.parseInt(roomXTextField.getText());
                        y = Integer.parseInt(roomYTextField.getText());
                    } catch (NumberFormatException exception) {
                        return;
                    }

                    // TODO: add GUI for room sizes
                    Shape shape = null;
                    switch (roomType) {
                        case RECTANGULAR_ROOM_TYPE:
                            shape = new Rectangle(x, y, 100, 100);
                            break;
                        case CIRCULAR_ROOM_TYPE:
                            shape = new Circle(x, y, 100);
                            break;
                    }
                    Room newRoom = new Room(roomNameText.getText(), shape);
                    newRoom.setDescription(roomDescriptionText.getText());
                    rooms.addElement(newRoom);

                    updateDrawingPanel();
                    // saveAllRooms();
                    insertRoom(newRoom);
                }
            }
        });
        removeRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!roomList.isSelectionEmpty()) {
                    Room roomDelete = rooms.get(roomList.getSelectedIndex());
                    rooms.remove(roomList.getSelectedIndex());

                    updateDrawingPanel();
                    // saveAllRooms();
                    deleteRoom(roomDelete);
                }
            }
        });
        roomList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!roomList.isSelectionEmpty()) {
                    int selectionIndex = roomList.getSelectedIndex();
                    Room room = rooms.get(selectionIndex);
                    roomNameText.setText(room.getName());
                    roomDescriptionText.setText(room.getDescription());

                    // TODO:
                } else {
                    roomNameText.setText("");
                    roomDescriptionText.setText("");
                    roomTypeComboBox.setSelectedIndex(0);
                    roomXTextField.setText("0");
                    roomYTextField.setText("0");
                }
            }
        });

        updateDrawingPanel();
    }

    private DefaultListModel<Room> loadRoomData() {
//        DefaultListModel<Room> rooms;
//
//        try {
//            rooms = Serializer.load(Paths.get(System.getProperty("user.home"), "room_data.bin"));
//        } catch (IOException e) {
//            rooms = new DefaultListModel<>();
//        }
//
//        return rooms;

        DefaultListModel<Room> rooms = new DefaultListModel<>();

        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection =
                DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1/map?user=default"
                );

            Statement statement = connection.createStatement();
            String query = "SELECT * FROM room";

            ResultSet recordSet = statement.executeQuery(query);
            while (recordSet.next()) {
                int id = recordSet.getInt("id");
                String name = recordSet.getString("name");
                String description = recordSet.getString("description");
                int x = recordSet.getInt("x");
                int y = recordSet.getInt("y");
                int type = recordSet.getInt("type");
                int width = recordSet.getInt("width");
                int height = recordSet.getInt("height");
                int radius = recordSet.getInt("radius");

                Shape shape = null;
                switch (type) {
                    case RECTANGULAR_ROOM_TYPE:
                        shape = new Rectangle(x, y, width, height);
                        break;
                    case CIRCULAR_ROOM_TYPE:
                        shape = new Circle(x, y, radius);
                        break;
                }
                Room newRoom = new Room(id, name, shape);
                newRoom.setDescription(description);
                rooms.addElement(newRoom);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return rooms;
    }

    private void saveAllRooms() {
//        try {
//            Serializer.save(rooms, Paths.get(System.getProperty("user.home"), "room_data.bin"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private void insertRoom(Room room) {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection =
                DriverManager.getConnection(
                    "jdbc:mysql://localhost/map?user=default"
                );

            String name = room.getName();
            String description = room.getDescription();
            int x = room.getShape().getX();
            int y = room.getShape().getY();
            int type, width = 0, height = 0, radius = 0;
            if (room.getShape() instanceof Rectangle) {
                type = RECTANGULAR_ROOM_TYPE;

                Rectangle rectangle = (Rectangle) room.getShape();
                width = rectangle.getWidth();
                height = rectangle.getHeight();
            } else {
                type = CIRCULAR_ROOM_TYPE;
                Circle circle = (Circle) room.getShape();
                radius = circle.getRadius();
            }

            Statement statement = connection.createStatement();
            String query = "INSERT INTO room SET ";
            query += "`name`='" + name + "', ";
            query += "`description`='" + description + "', ";
            query += "`x`='" + x + "', ";
            query += "`y`='" + y + "', ";
            query += "`type`='" + type + "', ";
            query += "`width`='" + width + "', ";
            query += "`height`='" + height + "', ";
            query += "`radius`='" + radius + "'";

            statement.execute(query);
        } catch(Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
    }

    private void deleteRoom(Room roomToDelete) {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection =
                DriverManager.getConnection(
                    "jdbc:mysql://localhost/map?user=default"
                );

            int id = roomToDelete.getId();

            Statement statement = connection.createStatement();
            String query = "DELETE from room WHERE `id`=" + id;

            statement.execute(query);
        } catch(Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
    }

    private void updateDrawingPanel() {
        Drawable[] thingsToDraw = new Drawable[rooms.size()];
        rooms.copyInto(thingsToDraw);

        ((DrawingPanel) drawingPanel).setThingsToDraw(thingsToDraw);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("План здания");
        frame.setContentPane(new Main().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createUIComponents() {
        drawingPanel = new DrawingPanel();
        drawingPanel.setBackground(Color.GRAY);
    }
}
