package dam32.christian;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class VentanaPrestamos extends JDialog {
	private static final long serialVersionUID = 7885998162486418706L;
	
	private final JPanel contentPanel = new JPanel();
	private JTable tablaDisponibles;
	private JTable tablaPrestamos;
	private Prestamos prestamos;
	private VentanaBiblioteca ventana;

	/**
	 * Create the dialog.
	 */
	public VentanaPrestamos(final VentanaBiblioteca ventana) {
		this.ventana = ventana;
		setBounds(100, 100, 668, 450);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setUndecorated(true);
		
		JLabel lblAlquilerDeLibros = new JLabel("ALQUILER DE LIBROS");
		lblAlquilerDeLibros.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlquilerDeLibros.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAlquilerDeLibros.setBounds(10, 11, 632, 14);
		contentPanel.add(lblAlquilerDeLibros);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 66, 632, 128);
		contentPanel.add(scrollPane);
		
		tablaDisponibles = new JTable();
		tablaDisponibles.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ISBN", "Autor", "Titulo", "Disponible"
			}
		));
		scrollPane.setViewportView(tablaDisponibles);
		
		JLabel lblLibrosDisponibles = new JLabel("Libros Disponibles");
		lblLibrosDisponibles.setBounds(10, 41, 632, 14);
		contentPanel.add(lblLibrosDisponibles);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 230, 632, 128);
		contentPanel.add(scrollPane_1);
		
		tablaPrestamos = new JTable();
		tablaPrestamos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "ISBN", "Usuario", "F. Prestamo", "F. Devolucion", "F. Devuelto"
			}
		));
		scrollPane_1.setViewportView(tablaPrestamos);
		
		JLabel lblLibrosPrestados = new JLabel("Libros Prestados");
		lblLibrosPrestados.setBounds(10, 205, 632, 14);
		contentPanel.add(lblLibrosPrestados);
		
		JButton btnAlquilarLibro = new JButton("ALQUILAR LIBRO");
		btnAlquilarLibro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fila = tablaDisponibles.getSelectedRow();
				if(fila == -1) {
					JOptionPane.showMessageDialog(contentPanel, "No has seleccionado ningun libro!", "Error!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(((String) tablaDisponibles.getValueAt(fila, 3)).equals("NO")) {
					JOptionPane.showMessageDialog(contentPanel, "Este libro no está disponible!", "Error!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String ISBN = (String) tablaDisponibles.getValueAt(fila, 0);
				String autor = (String) tablaDisponibles.getValueAt(fila, 1);
				String titulo = (String) tablaDisponibles.getValueAt(fila, 2);
				Libro libro = new Libro(autor, titulo, ISBN);
				ventanaNuevoPrestamo(libro);
			}
		});
		btnAlquilarLibro.setBounds(10, 378, 145, 23);
		contentPanel.add(btnAlquilarLibro);
		
		JButton btnDevolverLibro = new JButton("DEVOLVER LIBRO");
		btnDevolverLibro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = tablaPrestamos.getSelectedRow();
				if(fila == -1) {
					JOptionPane.showMessageDialog(contentPanel, "No has seleccionado ningun libro!", "Error!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				int numPrestamo = (int) tablaPrestamos.getValueAt(fila, 0);
				LibroPrestado libro = prestamos.buscaLibroID(numPrestamo);
				
				if(libro == null) {
					JOptionPane.showMessageDialog(contentPanel, "Error al borrar el libro!", "Error!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(libro.getFechaDevolucion() != null) {
					JOptionPane.showMessageDialog(contentPanel, "Este libro ya ha sido devuelto!", "Error!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				while(true) {
					String fechaString = JOptionPane.showInputDialog("Introduce la fecha de devolucion");
					if(Fecha.comprobar(fechaString)) {
						Fecha fechaDevuelto = Fecha.crearFecha(fechaString);
						libro.setFechaDevolucion(fechaDevuelto);
						refrescarPrestamos();
						refrescarLibros();
						
						Fecha fechaPrevista = libro.getFechaPrevista();
						if(fechaDevuelto.compararFechas(fechaPrevista) > 0) {
							JOptionPane.showMessageDialog(contentPanel, "El libro se ha devuelto tarde!", "Atencion!", JOptionPane.WARNING_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(contentPanel, "Libro devuelto!");
						}
						break;
					}
					JOptionPane.showMessageDialog(contentPanel, "Fecha no valida!", "Error!", JOptionPane.ERROR_MESSAGE);
				}				
			}
		});
		btnDevolverLibro.setBounds(165, 378, 145, 23);
		contentPanel.add(btnDevolverLibro);
		
		JButton btnSalir = new JButton("SALIR");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prestamos.archivaLibros();
				ventana.setVisible(true);
				dispose();
			}
		});
		btnSalir.setBounds(497, 378, 145, 23);
		contentPanel.add(btnSalir);
		
		JButton btnVerLibrosPrestados = new JButton("VER LIBROS PRESTADOS");
		btnVerLibrosPrestados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaVerPrestados();
			}
		});
		btnVerLibrosPrestados.setBounds(10, 416, 300, 23);
		contentPanel.add(btnVerLibrosPrestados);
		
		JButton btnVerLibrosFuera = new JButton("VER LIBROS FUERA DE PLAZO");
		btnVerLibrosFuera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaVerFueraDePlazo();
			}
		});
		btnVerLibrosFuera.setBounds(342, 416, 300, 23);
		contentPanel.add(btnVerLibrosFuera);
		
		prestamos = new Prestamos();
		prestamos.recuperaLibros();
		refrescarPrestamos();
		refrescarLibros();
		
		setVisible(true);
	}
	
	private void ventanaNuevoPrestamo(Libro libro) {
		new VentanaNuevoPrestamo(this, libro);
	}
	
	private void ventanaVerPrestados() {
		new VentanaVerPrestados(this);
	}
	
	private void ventanaVerFueraDePlazo() {
		new VentanaVerFueraDePlazo(this);
	}
	
	public void refrescarLibros() {
		DefaultTableModel model = (DefaultTableModel) tablaDisponibles.getModel();
		while(model.getRowCount() > 0) {
			model.removeRow(0);
		}
		
		for(Libro libro : ventana.getBiblioteca().getEstanteria()) {
			Object[] fila = new Object[model.getColumnCount()];
			fila[0] = libro.getISBN();
			fila[1] = libro.getAutor();
			fila[2] = libro.getTitulo();
			fila[3] = "SI";
			
			List<LibroPrestado> librosPrestados = prestamos.buscaLibroISBN(libro.getISBN());
			for(LibroPrestado libroPrestado : librosPrestados) {
				if(libroPrestado != null) {
					if(libroPrestado.getFechaDevolucion() == null) {
						fila[3] = "NO";
						break;
					}
				}
			}
			
			model.addRow(fila);			
		}
	}
	
	public void refrescarPrestamos() {
		DefaultTableModel model = (DefaultTableModel) tablaPrestamos.getModel();
		while(model.getRowCount() > 0) {
			model.removeRow(0);
		}
		
		for(LibroPrestado libro : prestamos.getPrestamos()) {
			Object[] fila = new Object[model.getColumnCount()];
			fila[0] = libro.getNumPrestamo();
			fila[1] = libro.getISBN();
			fila[2] = libro.getUsuario();
			fila[3] = libro.getFechaPrestamo();
			fila[4] = libro.getFechaPrevista();
			fila[5] = "";
			if(libro.getFechaDevolucion() != null) {
				fila[5] = libro.getFechaDevolucion();
			}
			
			model.addRow(fila);
		}
	}
	
	public Prestamos getPrestamos() {
		return prestamos;
	}
}
