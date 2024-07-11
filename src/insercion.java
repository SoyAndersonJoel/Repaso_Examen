import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class insercion {
    private JTextField placaTF;
    private JTextField marcaTF;
    private JTextField CilidrajeTF;
    private JTextField ConbustibleTF;
    private JTextField colorTF;
    private JTextField propietarioTF;
    private JButton insertarB;
    private JButton cancelarB;
    private JButton regresarB;
    public JPanel mainPanel;

    public insercion() {
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


        insertarB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String url = "jdbc:mysql://localhost:3306/VehiculosDB";
                String user = "root";
                String password = "123456";

                String placa=placaTF.getText();
                String marca=marcaTF.getText();
                int cilindraje=Integer.parseInt(CilidrajeTF.getText());
                String tipoCombustible=ConbustibleTF.getText();
                String color=colorTF.getText();
                String propietario=propietarioTF.getText();

                String sql = "INSERT INTO Vehiculo (placa, marca, cilindraje, tipo_combustible,color,propietario) VALUES (?, ?, ?, ?,?,?)";

                try (Connection connection = DriverManager.getConnection(url, user, password)) {
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);

                    vehiculo vehiculo1 = new vehiculo(placa,marca,cilindraje,tipoCombustible,color,propietario);
                    preparedStatement.setString(1,vehiculo1.getPlaca());
                    preparedStatement.setString(2,vehiculo1.getMarca());
                    preparedStatement.setInt(3,cilindraje);
                    preparedStatement.setString(4,tipoCombustible);
                    preparedStatement.setString(5,color);
                    preparedStatement.setString(6,propietario);
                    preparedStatement.execute();
                    JOptionPane.showMessageDialog(null, "Datos insertados");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al insertar datos");
                }

            }
        });
        cancelarB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placaTF.setText("");
                marcaTF.setText("");
                CilidrajeTF.setText("");
                ConbustibleTF.setText("");
                colorTF.setText("");
                propietarioTF.setText("");

            }
        });
    }
}
