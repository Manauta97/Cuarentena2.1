package Individuos;

/*
Esta clase define los atributos de Policia y hereda los de Persona
@autor Raúl
@version 0.1

*/

public final class Policia extends Persona{
	private String nPlaca;

	public Policia(String nombre, String dni, String nPlaca) {
		super(nombre, dni);
		this.nPlaca = nPlaca;
	}

	public String getnPlaca() {
		return nPlaca;
	}

	@Override
	public String toString() {
		return super.toString() + "Número Placa: " + nPlaca;
	}
	
}