import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class busqueda {
    private JTextField placaBuscada;
    private JButton buscarB;
    private JButton regresarB;
    public JPanel mainPanel;
    private JLabel placaTxt;
    private JLabel marcaTxt;
    private JLabel cilindrajeTxt;
    private JLabel combustibleTxt;
    private JLabel colorTxt;
    private JLabel propietarioTxt;

    public busqueda() {
        regresarB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Mi aplicación ");
                frame.setContentPane(new Bienvenida().mainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setSize(700, 400);
                frame.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(mainPanel)).dispose();

            }
        });
        buscarB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/VehiculosDB";
                String user = "root";
                String password = "123456";
                String placa=placaBuscada.getText();
                String sql = "SELECT * FROM Vehiculo WHERE placa = ?";
                try (Connection connection = DriverManager.getConnection(url, user, password)) {
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1,placa );

                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        placaTxt.setText(resultSet.getString("placa"));
                        marcaTxt.setText(resultSet.getString("marca"));
                        cilindrajeTxt.setText(resultSet.getString("cilindraje"));
                        combustibleTxt.setText(resultSet.getString("tipo_combustible"));
                        colorTxt.setText(resultSet.getString("color"));
                        propietarioTxt.setText(resultSet.getString("propietario"));

                    } else {
                        JOptionPane.showMessageDialog(null, "Estudiante no encontrado");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al buscar datos");
                }


            }
        });
    }
}
