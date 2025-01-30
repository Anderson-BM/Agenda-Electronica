package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;


public class Agenda {

	private JFrame frame;
	private JTextField textNombre;
	private JTable table;
	private JTextField textApellido;
	private JTextField textTelefono;
	private JTextField textDireccion;
	private JTextField textEvento;
	private JTextField textBorrarEvento;
	private JTextField textBuscarEvento;

//-------------------------------------------------------------------------------------------
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Agenda window = new Agenda();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//-------------------------------------------------------------------------------------------
	public Agenda() {
		initialize();
		
	}
	//-------------------------------------------------------------------------------------------
	private void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(102, 204, 153));
		frame.setBounds(100, 100, 1097, 668);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textNombre = new JTextField();
		textNombre.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		textNombre.setBounds(132, 171, 188, 37);
		frame.getContentPane().add(textNombre);
		textNombre.setColumns(10);
		//-------------------------------------------------------------------------------------------	
		DefaultTableModel modo = new DefaultTableModel(
				
				
				new Object[][] {,},
				
				new String [] {"ID","Evento","Nombres","apellido","telefono","direcion"}
				
				
				);
		
		JButton btnAgregar = new JButton("Agregar Eventos");
		btnAgregar.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		btnAgregar.setBackground(new Color(0, 102, 0));
		btnAgregar.setForeground(new Color(255, 255, 255));
		btnAgregar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				
			 try {
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda", "root", "23abm");
		            Statement statement = con.createStatement();

		            String Evento = textEvento.getText();
		            String Nombre = textNombre.getText();
		            String apellido = textApellido.getText();
		            String telefono = textTelefono.getText();
		            String direccion = textDireccion.getText();

		            // Construcción correcta de la consulta SQL
		            String query = "INSERT INTO eventos (Evento, Nombre, apellido, telefono, direccion) VALUES ('" +
		                           Evento + "',  '" + Nombre + "', '" + apellido + "', '" + telefono + "', '" + direccion + "')";
		            
		            // Ejecutar la consulta de inserción
		            statement.executeUpdate(query);

		            con.close();

		            // Mostrar mensaje de éxito
		            JOptionPane.showMessageDialog(null, "Eventos agregado con éxito.");

		            // Limpiar los campos de texto
		            textEvento.setText("");
		            textNombre.setText("");
		            textApellido.setText("");
		            textTelefono.setText("");
		            textDireccion.setText("");
		            
		        } catch (ClassNotFoundException e1) {
		            e1.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Error al cargar el driver de la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
		        } catch (SQLException e1) {
		            e1.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Error al interactuar con la base de datos: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		btnAgregar.setBounds(10, 445, 310, 45);
		frame.getContentPane().add(btnAgregar);
		
		JButton btnNewButton_1 = new JButton("Modificar Eventos\r\n");
		btnNewButton_1.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(Color.DARK_GRAY);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda","root","23abm");
					
					Statement statement=con.createStatement();
					
					String id = textNombre.getText();
					 
					statement.executeUpdate("update usuarios set Nombre='Anderson'  where ideventos="+id);
					con.close();
					
					
					
				}catch (ClassNotFoundException e1) {
					e1.printStackTrace();
					
				
				}catch (SQLException e1) {
					e1.printStackTrace();
					
				}
				
				
				textNombre.setText("");
				
				
				   boolean usuarioAgregadoConExito = true; // Cambia esto a tu lógica real
			        
			        if (usuarioAgregadoConExito) {
			            JOptionPane.showMessageDialog(null, "Usuario Modificado usuario");
			        } else {
			            JOptionPane.showMessageDialog(null, "Error al modficar usuario.");
			        }
			}
		});
		btnNewButton_1.setBounds(780, 449, 120, 45);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Borrar por ID");
		btnNewButton_2.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setBackground(new Color(255, 0, 0));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda","root","23abm");
					
					Statement statement=con.createStatement();
					
					String id = textBorrarEvento.getText();
					 
					statement.executeUpdate("delete from eventos where ideventos="+id);
					con.close();
					
					
				}catch (ClassNotFoundException e1) {
					e1.printStackTrace();
					
				
				}catch (SQLException e1) {
					e1.printStackTrace();
					
				}
				
				textNombre.setText("");
				
				   boolean usuarioAgregadoConExito = true; // Cambia esto a tu lógica real
			        
			        if (usuarioAgregadoConExito) {
			            JOptionPane.showMessageDialog(null, "Evento Eliminado");
			        } else {
			            JOptionPane.showMessageDialog(null, "Error al eliminar Evento.");
			        }
				
			}
		});
		btnNewButton_2.setBounds(10, 560, 120, 53);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Buscar por ID");
		btnNewButton_3.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda","root","23abm");
					
					Statement statement=con.createStatement();
					
					String id = textBuscarEvento.getText();
					 modo.setRowCount(0);
					
					ResultSet resultset = statement.executeQuery("select ideventos, Evento, Nombre, apellido, telefono, direccion from eventos where ideventos= " +id);
					
				
					
                       if(resultset.next()) {
						
						modo.addRow(new Object []  {resultset.getInt("ideventos"), resultset.getString("Evento"), resultset.getString("Nombre"),resultset.getString("apellido"),resultset.getString("telefono"),resultset.getString("direccion"),});
					}
					
					
					con.close();
					
					
				}catch (ClassNotFoundException e1) {
					e1.printStackTrace();
					
				
				}catch (SQLException e1) {
					e1.printStackTrace();
					
				}
				
				
				textBuscarEvento.setText("");
				
				   boolean usuarioEncintrado = true; // Cambia esto a tu lógica real
			        
			        if (usuarioEncintrado) {
			            JOptionPane.showMessageDialog(null, "Evento encontrado");
			        } else {
			            JOptionPane.showMessageDialog(null, "El Evento no Existe");
			        }
			}
		});
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.setBackground(new Color(0, 128, 255));
		btnNewButton_3.setBounds(453, 562, 120, 49);
		frame.getContentPane().add(btnNewButton_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		scrollPane.setBounds(391, 89, 682, 356);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable(modo);
		scrollPane.setViewportView(table);
		
		JButton btnMostrarTodo = new JButton("Mostrar Eventos");
		btnMostrarTodo.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		btnMostrarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda","root","23abm");
					
					Statement statement=con.createStatement();
					
					
					 modo.setRowCount(0);
					
					ResultSet resultset = statement.executeQuery("select ideventos, Evento, Nombre, apellido, telefono, direccion from eventos");
					
				
					
                       while(resultset.next()) {
						
						modo.addRow(new Object []  {resultset.getInt("ideventos"), resultset.getString("Evento"), resultset.getString("Nombre"), resultset.getString("apellido"), resultset.getString("telefono"), resultset.getString("direccion")});
					}
					
					
					con.close();
					
					
				}catch (ClassNotFoundException e1) {
					e1.printStackTrace();
					
				
				}catch (SQLException e1) {
					e1.printStackTrace();
					
				}
				
				  
			
				  
			  

			    }
			
		});
		btnMostrarTodo.setForeground(Color.WHITE);
		btnMostrarTodo.setBackground(Color.DARK_GRAY);
		btnMostrarTodo.setBounds(940, 449, 120, 45);
		frame.getContentPane().add(btnMostrarTodo);
		
		textApellido = new JTextField();
		textApellido.setColumns(10);
		textApellido.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		textApellido.setBounds(132, 248, 188, 37);
		frame.getContentPane().add(textApellido);
		
		textTelefono = new JTextField();
		textTelefono.setColumns(10);
		textTelefono.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		textTelefono.setBounds(132, 318, 188, 37);
		frame.getContentPane().add(textTelefono);
		
		textDireccion = new JTextField();
		textDireccion.setColumns(10);
		textDireccion.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		textDireccion.setBounds(132, 385, 188, 37);
		frame.getContentPane().add(textDireccion);
		
		JLabel lblNombre = new JLabel("Persona:");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNombre.setBounds(10, 163, 104, 53);
		frame.getContentPane().add(lblNombre);
		
		JLabel lblApellido = new JLabel("Fecha:");
		lblApellido.setForeground(Color.WHITE);
		lblApellido.setFont(new Font("Verdana", Font.BOLD, 20));
		lblApellido.setBounds(10, 244, 104, 45);
		frame.getContentPane().add(lblApellido);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setForeground(Color.WHITE);
		lblTelefono.setFont(new Font("Verdana", Font.BOLD, 20));
		lblTelefono.setBounds(9, 318, 113, 45);
		frame.getContentPane().add(lblTelefono);
		
		JLabel lblDirecion = new JLabel("Direcion:");
		lblDirecion.setForeground(Color.WHITE);
		lblDirecion.setFont(new Font("Verdana", Font.BOLD, 20));
		lblDirecion.setBounds(10, 381, 104, 45);
		frame.getContentPane().add(lblDirecion);
		
		JLabel lblEvento = new JLabel("Evento:");
		lblEvento.setForeground(Color.WHITE);
		lblEvento.setFont(new Font("Verdana", Font.BOLD, 20));
		lblEvento.setBounds(10, 97, 104, 45);
		frame.getContentPane().add(lblEvento);
		
		textEvento = new JTextField();
		textEvento.setColumns(10);
		textEvento.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		textEvento.setBounds(132, 101, 188, 37);
		frame.getContentPane().add(textEvento);
		
		textBorrarEvento = new JTextField();
		textBorrarEvento.setBackground(Color.WHITE);
		textBorrarEvento.setColumns(10);
		textBorrarEvento.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		textBorrarEvento.setBounds(154, 572, 188, 37);
		frame.getContentPane().add(textBorrarEvento);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 502, 1063, 4);
		frame.getContentPane().add(panel);
		
		textBuscarEvento = new JTextField();
		textBuscarEvento.setBackground(Color.WHITE);
		textBuscarEvento.setColumns(10);
		textBuscarEvento.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		textBuscarEvento.setBounds(602, 572, 188, 37);
		frame.getContentPane().add(textBuscarEvento);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Administrador\\Downloads\\agenda.png"));
		lblNewLabel_1.setBounds(919, 504, 120, 127);
		frame.getContentPane().add(lblNewLabel_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.DARK_GRAY);
		panel_3.setBounds(0, 0, 1083, 80);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("AGENDA ELECTRONICA\r\n  DE EVENTOS");
		lblNewLabel.setBounds(10, 28, 512, 27);
		panel_3.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 22));
		
		JLabel lblNewLabel_2 = new JLabel("EVENTOS GUARDADOS");
		lblNewLabel_2.setBounds(574, 29, 274, 27);
		panel_3.add(lblNewLabel_2);
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 21));
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(877, 10, 77, 73);
		panel_3.add(lblNewLabel_3);
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\Administrador\\Downloads\\usuario (1).png"));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 78, 1073, 9);
		frame.getContentPane().add(panel_2);
	}
}
