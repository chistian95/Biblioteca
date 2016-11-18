package dam32.christian;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class VentanaBiblioteca extends JFrame {
	private static final long serialVersionUID = 8046773707753642956L;
	
	private JPanel contentPane;
	private JTextField tf_ISBN;
	private JTextField tf_Autor;
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
		setUndecorated(true);
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
		
		tf_ISBN = new JTextField();
		tf_ISBN.setBounds(197, 51, 119, 20);
		contentPane.add(tf_ISBN);
		tf_ISBN.setColumns(10);
		
		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setBounds(413, 54, 46, 14);
		contentPane.add(lblAutor);
		
		tf_Autor = new JTextField();
		tf_Autor.setBounds(469, 51, 119, 20);
		contentPane.add(tf_Autor);
		tf_Autor.setColumns(10);
		
		JButton btnConsultar = new JButton("CONSULTAR");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refrescarTabla();
			}
		});
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
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ventanaNuevo();
			}
		});
		btnNuevo.setBounds(10, 386, 134, 23);
		contentPane.add(btnNuevo);
		
		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				if(fila == -1) {
					JOptionPane.showMessageDialog(contentPane, "No has seleccionado ninguna fila!", "Error!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String autor = (String) table.getValueAt(fila, 0);
				String ISBN = (String) table.getValueAt(fila, 1);
				String titulo = (String) table.getValueAt(fila, 2);
				Libro libro = new Libro(autor, titulo, ISBN);
				ventanaModificar(libro);
			}
		});
		btnModificar.setBounds(299, 386, 134, 23);
		contentPane.add(btnModificar);
		
		JButton btnConsultar_1 = new JButton("ELIMINAR");
		btnConsultar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fila = table.getSelectedRow();
				if(fila == -1) {
					JOptionPane.showMessageDialog(contentPane, "No has seleccionado ninguna fila!", "Error!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(JOptionPane.showConfirmDialog(contentPane, "¿Desea borrar este libro?") == 0) {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					String ISBN = (String) model.getValueAt(fila, 1);
					if(biblioteca.borraLibro(ISBN)) {
						JOptionPane.showMessageDialog(contentPane, "Libro borrado!");
						model.removeRow(fila);
					} else {
						JOptionPane.showMessageDialog(contentPane, "Error al borrar libro!", "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnConsultar_1.setBounds(154, 386, 134, 23);
		contentPane.add(btnConsultar_1);
		
		JButton btnSalir = new JButton("SALIR");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				biblioteca.archivaLibros();
				System.exit(0);
			}
		});
		btnSalir.setBounds(299, 449, 135, 23);
		contentPane.add(btnSalir);
		
		JButton btnPrestamos = new JButton("PRESTAMOS");
		btnPrestamos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ventanaPrestamos();
			}
		});
		btnPrestamos.setBounds(604, 386, 134, 23);
		contentPane.add(btnPrestamos);
		
		biblioteca = new Biblioteca();
		biblioteca.recuperaLibros();
		refrescarTabla();
	}
	
	public void refrescarTabla() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		while(model.getRowCount() > 0) {
			model.removeRow(0);
		}
		if(tf_ISBN.getText().length() <= 0 && tf_Autor.getText().length() <= 0) {
			for(Libro libro : biblioteca.getEstanteria()) {
				Object[] fila = new Object[model.getColumnCount()];
				fila[0] = libro.getAutor();
				fila[1] = libro.getISBN();
				fila[2] = libro.getTitulo();
				
				model.addRow(fila);
			}
		} else if(tf_ISBN.getText().length() > 0) {
			Libro libro = biblioteca.buscaLibroISBN(tf_ISBN.getText().toUpperCase());
			if(libro != null) {
				Object[] fila = new Object[model.getColumnCount()];
				fila[0] = libro.getAutor();
				fila[1] = libro.getISBN();
				fila[2] = libro.getTitulo();
				
				model.addRow(fila);
			}
		} else if(tf_Autor.getText().length() > 0) {
			List<Libro> libros = biblioteca.buscaLibroAutor(tf_Autor.getText().toUpperCase());
			for(Libro libro : libros) {
				Object[] fila = new Object[model.getColumnCount()];
				fila[0] = libro.getAutor();
				fila[1] = libro.getISBN();
				fila[2] = libro.getTitulo();
				
				model.addRow(fila);
			}
		}
		tf_ISBN.setText("");
		tf_Autor.setText("");
	}
	
	private void ventanaNuevo() {
		new VentanaNuevo(this, null);
	}
	
	private void ventanaModificar(Libro libro) {
		new VentanaNuevo(this, libro);
	}
	
	private void ventanaPrestamos() {
		this.setVisible(false);
		new VentanaPrestamos(this);
	}
	
	public Biblioteca getBiblioteca() {
		return biblioteca;
	}
}
