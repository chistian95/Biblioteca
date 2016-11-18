package dam32.christian;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VentanaNuevoPrestamo extends JDialog {
	private static final long serialVersionUID = -881842872807148597L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField tfUsuario;
	private JTextField tfISBN;
	private JTextField tfFechaPrestamo;
	private JTextField tfFechaDevolucion;

	/**
	 * Create the dialog.
	 */
	public VentanaNuevoPrestamo(final VentanaPrestamos ventana, Libro libro) {
		setBounds(100, 100, 148, 329);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		tfUsuario = new JTextField();
		tfUsuario.setBounds(10, 36, 110, 20);
		contentPanel.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(10, 11, 46, 14);
		contentPanel.add(lblUsuario);
		
		tfISBN = new JTextField();
		tfISBN.setEnabled(false);
		tfISBN.setEditable(false);
		tfISBN.setColumns(10);
		tfISBN.setBounds(10, 92, 110, 20);
		contentPanel.add(tfISBN);
		
		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setBounds(10, 67, 46, 14);
		contentPanel.add(lblIsbn);
		
		tfFechaPrestamo = new JTextField();
		tfFechaPrestamo.setColumns(10);
		tfFechaPrestamo.setBounds(10, 148, 110, 20);
		contentPanel.add(tfFechaPrestamo);
		
		JLabel lblFechaPrestamo = new JLabel("Fecha Prestamo");
		lblFechaPrestamo.setBounds(10, 123, 110, 14);
		contentPanel.add(lblFechaPrestamo);
		
		tfFechaDevolucion = new JTextField();
		tfFechaDevolucion.setColumns(10);
		tfFechaDevolucion.setBounds(10, 204, 110, 20);
		contentPanel.add(tfFechaDevolucion);
		
		JLabel lblFechaDevolucion = new JLabel("Fecha Devolucion");
		lblFechaDevolucion.setBounds(10, 179, 110, 14);
		contentPanel.add(lblFechaDevolucion);
		
		JButton btnPrestar = new JButton("PRESTAR");
		btnPrestar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfUsuario.getText().length() <= 0) {
					JOptionPane.showMessageDialog(contentPanel, "Debes introducir un usuario!", "Error!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(tfFechaPrestamo.getText().length() <= 0) {
					JOptionPane.showMessageDialog(contentPanel, "Debes introducir una fecha!", "Error!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(tfFechaDevolucion.getText().length() <= 0) {
					JOptionPane.showMessageDialog(contentPanel, "Debes introducir una fecha!", "Error!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(!Fecha.comprobar(tfFechaPrestamo.getText()) || !Fecha.comprobar(tfFechaDevolucion.getText())) {
					JOptionPane.showMessageDialog(contentPanel, "La fecha no es valida!", "Error!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Fecha fechaPrestamo = Fecha.crearFecha(tfFechaPrestamo.getText());
				Fecha fechaDevolucion = Fecha.crearFecha(tfFechaDevolucion.getText());
				
				if(fechaPrestamo.compararFechas(fechaDevolucion) > 0) {
					JOptionPane.showMessageDialog(contentPanel, "La fecha del prestamo debe ser menor a la de devolucion!", "Error!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				LibroPrestado libroNuevo = new LibroPrestado(ventana.getPrestamos().getPrestamos().size(), tfISBN.getText(), tfUsuario.getText(), fechaPrestamo, fechaDevolucion, null);
				ventana.getPrestamos().añadirPrestamo(libroNuevo);
				ventana.refrescarPrestamos();
				ventana.refrescarLibros();
				JOptionPane.showMessageDialog(contentPanel, "Libro prestado!");
				dispose();
			}
		});
		btnPrestar.setBounds(10, 257, 110, 23);
		contentPanel.add(btnPrestar);
		
		JLabel lblFechasDma = new JLabel("Fechas: d/m/a");
		lblFechasDma.setEnabled(false);
		lblFechasDma.setBounds(10, 232, 110, 14);
		contentPanel.add(lblFechasDma);
		tfISBN.setText(libro.getISBN());
		setVisible(true);
	}
}
