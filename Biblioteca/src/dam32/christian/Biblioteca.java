package dam32.christian;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
	private List<Libro> estanteria = new ArrayList<Libro>();
	
	public Biblioteca() {
		estanteria = new ArrayList<Libro>();
	}
	
	public boolean añadirLibro(Libro libroNuevo) {
		if(estanteria.contains(libroNuevo)) {
			return false;
		}		
		estanteria.add(libroNuevo);
		return true;
	}
	
	public int archivaLibros() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("biblioteca.dat"));
			for(Libro libro : estanteria) {
				oos.writeObject(libro);
			}
			oos.close();
			return estanteria.size();
		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el archivo!");
		} catch (IOException e) {
			System.out.println("Error al guardar libros: "+e.getMessage());
		}
		return 0;
	}
	public int recuperaLibros() {
		estanteria.clear();
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("biblioteca.dat"));
			Libro libro = null;
			while((libro = (Libro) ois.readObject()) != null) {
				estanteria.add(libro);
			}
			ois.close();
			return estanteria.size();
		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el archivo!");
		} catch (IOException e) {
			System.out.println("Error al cargar libros: "+e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Error al leer libro: "+e.getMessage());
		}
		return 0;
	}
	
	public boolean borraLibro(String ISBN) {
		for(Libro libro : estanteria) {
			if(libro.getISBN().equals(ISBN)) {
				estanteria.remove(libro);
				return true;
			}
		}
		return false;
	}
	
	public Libro buscaLibroISBN(String ISBN) {
		for(Libro libro : estanteria) {
			if(libro.getISBN().equals(ISBN)) {
				return libro;
			}
		}
		return null;
	}
	
	public List<Libro> buscaLibroAutor(String autor) {
		List<Libro> libros = new ArrayList<Libro>();
		for(Libro libro : estanteria) {
			if(libro.getAutor().equals(autor)) {
				libros.add(libro);
			}
		}
		return libros;
	}
	
	public boolean modificaLibro(Libro libroMod) {
		for(Libro libro : estanteria) {
			if(libro.equals(libroMod)) {
				libro.setAutor(libroMod.getAutor());
				libro.setTitulo(libroMod.getTitulo());
				return true;
			}
		}
		return false;
	}
	
	public List<Libro> getEstanteria() {
		return estanteria;
	}
}
