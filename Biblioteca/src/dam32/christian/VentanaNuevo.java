package dam32.christian;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class VentanaNuevo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private VentanaBiblioteca ventanaBiblioteca;
	private JTextField tf_ISBN;
	private final JLabel lblIsbn = new JLabel("ISBN");
	private final JTextField tf_Autor = new JTextField();
	private final JLabel lblAutor = new JLabel("Autor");
	private final JTextField tf_Titulo = new JTextField();
	private final JLabel lblTtulo = new JLabel("T\u00EDtulo");
	private final JButton btnAadir = new JButton("A\u00D1ADIR");

	/**
	 * Create the dialog.
	 */
	public VentanaNuevo(VentanaBiblioteca ventana) {
		this.ventanaBiblioteca = ventana;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNuevoLibro = new JLabel("NUEVO LIBRO");
			lblNuevoLibro.setHorizontalAlignment(SwingConstants.CENTER);
			lblNuevoLibro.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNuevoLibro.setBounds(10, 11, 414, 14);
			contentPanel.add(lblNuevoLibro);
		}
		{
			tf_ISBN = new JTextField();
			tf_ISBN.setBounds(151, 61, 139, 20);
			contentPanel.add(tf_ISBN);
			tf_ISBN.setColumns(10);
		}
		{
			lblIsbn.setBounds(151, 36, 46, 14);
			contentPanel.add(lblIsbn);
		}
		{
			tf_Autor.setColumns(10);
			tf_Autor.setBounds(151, 117, 139, 20);
			contentPanel.add(tf_Autor);
		}
		{
			lblAutor.setBounds(151, 92, 46, 14);
			contentPanel.add(lblAutor);
		}
		{
			tf_Titulo.setColumns(10);
			tf_Titulo.setBounds(151, 173, 139, 20);
			contentPanel.add(tf_Titulo);
		}
		{
			lblTtulo.setBounds(151, 148, 46, 14);
			contentPanel.add(lblTtulo);
		}
		{
			btnAadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(tf_ISBN.getText().length() <= 0) {
						JOptionPane.showMessageDialog(contentPanel, "Debes rellenar el ISBN!", "Error!", JOptionPane.ERROR_MESSAGE);
						return;
					}
					Libro libroNuevo = new Libro(tf_Autor.getText(), tf_Titulo.getText(), tf_ISBN.getText());
					if(ventanaBiblioteca.getBiblioteca().añadirLibro(libroNuevo)) {
						JOptionPane.showMessageDialog(contentPanel, "Libro añadido!");
						ventanaBiblioteca.refrescarTabla();
						dispose();
					} else {
						JOptionPane.showMessageDialog(contentPanel, "Ya existe un libro con ese ISBN", "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			btnAadir.setBounds(151, 227, 139, 23);
			contentPanel.add(btnAadir);
		}
		setVisible(true);
	}
}
