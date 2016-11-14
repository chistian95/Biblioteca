package dam32.christian;

import java.io.Serializable;

public class Libro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String autor;
	private String titulo;
	private String ISBN;
	
	public Libro(String ISBN) {
		this("", "", ISBN);
	}
	
	public Libro(String autor, String titulo, String ISBN) {
		super();
		this.autor = autor.toUpperCase();
		this.titulo = titulo.toUpperCase();
		this.ISBN = ISBN.toUpperCase();
	}
	
	@Override
	public String toString() {
		return "Autor: "+autor+", Titulo: "+titulo+", ISBN: "+ISBN;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ISBN == null) ? 0 : ISBN.hashCode());
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
		Libro other = (Libro) obj;
		if (ISBN == null) {
			if (other.ISBN != null)
				return false;
		} else if (!ISBN.equals(other.ISBN))
			return false;
		return true;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor.toUpperCase();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo.toUpperCase();
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN.toUpperCase();
	}
	
	
}
