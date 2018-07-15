package br.com.libercode.core.enumerated;

public enum Perfil {
	
	INBIT				("Inbit"),
	ADMINISTRADORA		("Administradora"),
	SINDICO				("Sindico"),
	CONDOMINO_MORADOR	("Condomino Morador"),
	CONDOMINO			("Condomino"),
	MORADOR				("Morador");
	
	private final String label;

	public boolean isCondomino() {
		return this.equals(CONDOMINO) || this.equals(CONDOMINO_MORADOR);
	}
	
	public boolean isMorador() {
		return this.equals(MORADOR) || this.equals(CONDOMINO_MORADOR);
	}

	public boolean isSindico() {
		return this.equals(SINDICO);
	}

	private Perfil(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}
	
	public String getPasta(){
		return label.toLowerCase();
	}

	public static Perfil[] getPerfisPublicos(){
		Perfil[] perfisPublicos = new Perfil[5];
		perfisPublicos[0] = ADMINISTRADORA;
		perfisPublicos[1] = SINDICO;
		perfisPublicos[2] = CONDOMINO_MORADOR;
		perfisPublicos[3] = CONDOMINO;
		perfisPublicos[4] = MORADOR;
		return perfisPublicos;
	}

}
