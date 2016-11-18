package dam32.christian;

import java.io.Serializable;

public class Fecha implements Serializable {
	private static final long serialVersionUID = 1770575993189348029L;
	
	private int año;
	private int mes;
	private int dia;
	
	public Fecha() {
		this(1, 1, 1);
	}
	
	public Fecha(int dia, int mes, int año) {
		this.año = año;
		this.mes = mes;
		this.dia = dia;
	}
	
	public int compararFechas(Fecha fecha) {		
		if(año < fecha.año) {
			return -1;
		}
		if(año > fecha.año) {
			return 1;
		}
		
		if(mes < fecha.mes) {
			return -1;
		}
		if(mes > fecha.mes) {
			return 1;
		}
		
		if(dia < fecha.dia) {
			return -1;
		}
		if(dia > fecha.dia) {
			return 1;
		}
		
		return 0;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + año;
		result = prime * result + dia;
		result = prime * result + mes;
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
		Fecha other = (Fecha) obj;
		if (año != other.año)
			return false;
		if (dia != other.dia)
			return false;
		if (mes != other.mes)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return dia+"/"+mes+"/"+año;
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}
	
	public static Fecha crearFecha(String fecha) {
		String[] romper = fecha.split("/");
		if(romper.length != 3) {
			return null;
		}
		
		int dia = 0, mes = 0, año = 0;
		try {
			dia = Integer.parseInt(romper[0]);
			mes = Integer.parseInt(romper[1]);
			año = Integer.parseInt(romper[2]);
		} catch(Exception e) {
			return null;
		}
		
		if(dia < 1 || dia > 30) {
			return null;
		}
		if(mes < 1 || mes > 12) {
			return null;
		}
		if(año < 0) {
			return null;
		}
		
		return new Fecha(dia, mes, año);
	}
	
	public static boolean comprobar(String fecha) {
		String[] romper = fecha.split("/");
		if(romper.length != 3) {
			return false;
		}
		
		int dia = 0, mes = 0, año = 0;
		try {
			dia = Integer.parseInt(romper[0]);
			mes = Integer.parseInt(romper[1]);
			año = Integer.parseInt(romper[2]);
		} catch(Exception e) {
			return false;
		}
		
		if(dia < 1 || dia > 30) {
			return false;
		}
		if(mes < 1 || mes > 12) {
			return false;
		}
		if(año < 0) {
			return false;
		}
		
		return true;
	}
}
