package dam32.christian;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VentanaBiblioteca extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private Biblioteca biblioteca;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaBiblioteca frame = new VentanaBiblioteca();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaBiblioteca() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 764, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBiblioteca = new JLabel("BIBLIOTECA");
		lblBiblioteca.setHorizontalAlignment(SwingConstants.CENTER);
		lblBiblioteca.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblBiblioteca.setBounds(0, 11, 738, 14);
		contentPane.add(lblBiblioteca);
		
		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setBounds(132, 54, 46, 14);
		contentPane.add(lblIsbn);
		
		textField = new JTextField();
		textField.setBounds(197, 51, 119, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setBounds(413, 54, 46, 14);
		contentPane.add(lblAutor);
		
		textField_1 = new JTextField();
		textField_1.setBounds(469, 51, 119, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnConsultar = new JButton("CONSULTAR");
		btnConsultar.setBounds(299, 94, 148, 23);
		contentPane.add(btnConsultar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 144, 728, 231);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Autor", "ISBN", "T\u00EDtulo"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnNuevo = new JButton("NUEVO");
		btnNuevo.setBounds(10, 410, 134, 23);
		contentPane.add(btnNuevo);
		
		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.setBounds(604, 410, 134, 23);
		contentPane.add(btnModificar);
		
		JButton btnConsultar_1 = new JButton("ELIMINAR");
		btnConsultar_1.setBounds(310, 410, 134, 23);
		contentPane.add(btnConsultar_1);
		
		biblioteca = new Biblioteca();
		biblioteca.recuperaLibros();
		refrescarTabla();
	}
	
	public void refrescarTabla() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		while(model.getRowCount() > 0) {
			model.removeRow(0);
		}
		for(Libro libro : biblioteca.getEstanteria()) {
			Object[] fila = new Object[model.getColumnCount()];
			fila[0] = libro.getAutor();
			fila[1] = libro.getISBN();
			fila[2] = libro.getTitulo();
			
			model.addRow(fila);
		}
	}
}