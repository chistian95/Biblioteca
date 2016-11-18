package dam32.christian;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Prestamos {
	private List<LibroPrestado> prestamos;
	
	public Prestamos() {
		prestamos = new ArrayList<LibroPrestado>();
	}
	
	public boolean añadirPrestamo(LibroPrestado libroNuevo) {
		if(prestamos.contains(libroNuevo)) {
			return false;
		}		
		prestamos.add(libroNuevo);
		return true;
	}
	
	public int archivaLibros() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Prestamos04.obj"));
			for(LibroPrestado libro : prestamos) {
				oos.writeObject(libro);
			}
			oos.close();
			return prestamos.size();
		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el archivo!");
		} catch (IOException e) {
			System.out.println("Error al guardar libros: "+e.getMessage());
		}
		return 0;
	}
	
	public int recuperaLibros() {
		prestamos.clear();
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Prestamos04.obj"));
			LibroPrestado libro = null;
			while((libro = (LibroPrestado) ois.readObject()) != null) {
				prestamos.add(libro);
			}
			ois.close();			
		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el archivo!");
		} catch (IOException e) {
			System.out.println("Error al cargar libros: "+e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Error al leer libro: "+e.getMessage());
		}
		return prestamos.size();
	}
	
	public boolean borraLibro(int numPrestamo) {
		for(LibroPrestado libro : prestamos) {
			if(libro.getNumPrestamo() == numPrestamo) {
				prestamos.remove(libro);
				return true;
			}
		}
		return false;
	}
	
	public LibroPrestado buscaLibroID(int numPrestamo) {
		for(LibroPrestado libro : prestamos) {
			if(libro.getNumPrestamo() == numPrestamo) {
				return libro;
			}
		}
		return null;
	}
	
	public List<LibroPrestado> buscaLibroISBN(String ISBN) {
		List<LibroPrestado> libros = new ArrayList<LibroPrestado>();
		for(LibroPrestado libro : prestamos) {
			if(libro.getISBN().equals(ISBN)) {
				libros.add(libro);
			}
		}
		return libros;
	}
	
	public List<LibroPrestado> getPrestamos() {
		return prestamos;
	}
}
