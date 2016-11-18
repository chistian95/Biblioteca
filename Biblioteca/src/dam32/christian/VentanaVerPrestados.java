package dam32.christian;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class VentanaVerPrestados extends JDialog {
	private static final long serialVersionUID = 5129177994199344890L;
	
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private VentanaPrestamos ventana;

	/**
	 * Create the dialog.
	 */
	public VentanaVerPrestados(final VentanaPrestamos ventana) {
		this.ventana = ventana;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblLibrosSinDevolver = new JLabel("LIBROS SIN DEVOLVER");
			lblLibrosSinDevolver.setHorizontalAlignment(SwingConstants.CENTER);
			lblLibrosSinDevolver.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblLibrosSinDevolver.setBounds(10, 11, 414, 14);
			contentPanel.add(lblLibrosSinDevolver);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 40, 414, 178);
			contentPanel.add(scrollPane);
			{
				table = new JTable();
				table.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"ID", "ISBN", "Usuario", "F. Prestamo", "F. Devolucion"
					}
				));
				scrollPane.setViewportView(table);
			}
		}
		{
			JButton SALIR = new JButton("SALIR");
			SALIR.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			SALIR.setBounds(10, 229, 414, 23);
			contentPanel.add(SALIR);
		}
		cargarLibros();
		setVisible(true);
	}

	private void cargarLibros() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		for(LibroPrestado libro : ventana.getPrestamos().getPrestamos()) {
			if(libro.getFechaDevolucion() == null) {
				Object[] fila = new Object[model.getColumnCount()];
				fila[0] = libro.getNumPrestamo();
				fila[1] = libro.getISBN();
				fila[2] = libro.getUsuario();
				fila[3] = libro.getFechaPrestamo();
				fila[4] = libro.getFechaPrevista();
				
				model.addRow(fila);
			}
		}
	}
}
