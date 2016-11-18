package dam32.christian;

import java.io.Serializable;

public class LibroPrestado implements Serializable {
	private static final long serialVersionUID = -4349475159986612728L;
	
	private int numPrestamo;
	private String ISBN;
	private String usuario;
	private Fecha fechaPrestamo;
	private Fecha fechaPrevista;
	private Fecha fechaDevolucion;
	
	public LibroPrestado(int numPrestamo, String iSBN, String usuario, Fecha fechaPrestamo, Fecha fechaPrevista, Fecha fechaDevolucion) {
		this.numPrestamo = numPrestamo;
		ISBN = iSBN;
		this.usuario = usuario;
		this.fechaPrestamo = fechaPrestamo;
		this.fechaPrevista = fechaPrevista;
		this.fechaDevolucion = fechaDevolucion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numPrestamo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LibroPrestado other = (LibroPrestado) obj;
		if (numPrestamo != other.numPrestamo)
			return false;
		return true;
	}

	public int getNumPrestamo() {
		return numPrestamo;
	}

	public void setNumPrestamo(int numPrestamo) {
		this.numPrestamo = numPrestamo;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Fecha getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(Fecha fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public Fecha getFechaPrevista() {
		return fechaPrevista;
	}

	public void setFechaPrevista(Fecha fechaPrevista) {
		this.fechaPrevista = fechaPrevista;
	}

	public Fecha getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(Fecha fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}
	
	
}
