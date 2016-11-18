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

public class VentanaVerFueraDePlazo extends JDialog {
	private static final long serialVersionUID = 3877628763807404719L;
	
	private final JPanel contentPanel = new JPanel();
	private VentanaPrestamos ventana;
	private JTable table;

	/**
	 * Create the dialog.
	 */
	public VentanaVerFueraDePlazo(final VentanaPrestamos ventana) {
		this.ventana = ventana;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblLibrosFueraDe = new JLabel("LIBROS FUERA DE PLAZO");
			lblLibrosFueraDe.setHorizontalAlignment(SwingConstants.CENTER);
			lblLibrosFueraDe.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblLibrosFueraDe.setBounds(10, 11, 414, 14);
			contentPanel.add(lblLibrosFueraDe);
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
						"ID", "ISBN", "Usuario", "F. Devolucion", "F. Devuelto"
					}
				));
				scrollPane.setViewportView(table);
			}
		}
		{
			JButton button = new JButton("SALIR");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			button.setBounds(10, 229, 414, 23);
			contentPanel.add(button);
		}
		cargarLibros();
		setVisible(true);
	}

	private void cargarLibros() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		for(LibroPrestado libro : ventana.getPrestamos().getPrestamos()) {
			if(libro.getFechaDevolucion() != null && libro.getFechaDevolucion().compararFechas(libro.getFechaPrevista()) > 0) {
				Object[] fila = new Object[model.getColumnCount()];
				fila[0] = libro.getNumPrestamo();
				fila[1] = libro.getISBN();
				fila[2] = libro.getUsuario();
				fila[3] = libro.getFechaPrevista();
				fila[4] = libro.getFechaDevolucion();
				
				model.addRow(fila);
			}
		}
	}
}
