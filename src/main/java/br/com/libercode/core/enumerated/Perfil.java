package br.com.libercode.core.enumerated;

public enum Perfil {
	
	ADM				("Adm"),
	MODERADOR		("Moderador"),
	USUARIO				("Usuário");

	private final String label;

	private Perfil(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}

}
