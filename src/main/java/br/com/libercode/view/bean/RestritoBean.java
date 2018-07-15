package br.com.libercode.view.bean;


import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@RequestScoped
@Named("restritoBean")
public class RestritoBean {

	private static final long serialVersionUID = 1L;

	private static final String CRUD1 = "/restrito/index.xhtml";
	private static final String CRUD2 = "/restrito/crud2.xhtml";
	private static final String CRUD3 = "/restrito/crud3.xhtml";
	private static final String CRUD4 = "/restrito/crud4.xhtml";



	public boolean crud1ScreenActive(){
		if(urlAtualContemTexto(CRUD1)){
			return true;
		}
		return false;
	}

	public boolean crud2ScreenActive(){
		if(urlAtualContemTexto(CRUD2)){
			return true;
		}
		return false;
	}

	public boolean crud3ScreenActive(){
		if(urlAtualContemTexto(CRUD3)){
			return true;
		}
		return false;
	}

	public boolean crud4ScreenActive(){
		if(urlAtualContemTexto(CRUD4)){
			return true;
		}
		return false;
	}

	protected boolean urlAtualContemTexto(String texto){
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpServletRequest request = (HttpServletRequest) req;
		String destino = request.getServletPath();
		return destino.contains(texto);
	}

}
