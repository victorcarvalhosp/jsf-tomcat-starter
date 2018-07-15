package br.com.libercode.view.util;

import br.com.libercode.core.entity.UsuarioEntity;

import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@RequestScoped
@Named("jsfUtil")
public class JSFUtil {

	public static String getDataFormatada(Date data){
		return new SimpleDateFormat("dd/MM/yyyy").format(data);
	}

	public static UsuarioEntity getUsuarioLogado() {
		UsuarioEntity usuarioLogado;
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        usuarioLogado = (UsuarioEntity)((HttpServletRequest) externalContext.getRequest()).getSession().getAttribute("usuarioLogado");
		return usuarioLogado;

	}

	public static Set<String> getRecursos() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Set<String> recursos = (Set<String>) ((HttpServletRequest) externalContext.getRequest()).getSession().getAttribute("recursos");
		return recursos;
	}

	public static String carregarPalavraBundle(String key){
		return "";
	}

	public static int compare(Number n1, Number n2) {
	    long l1 = n1.longValue();
	    long l2 = n2.longValue();
	    if (l1 != l2)
	        return (l1 < l2 ? -1 : 1);
	    return Double.compare(n1.doubleValue(), n2.doubleValue());
	}

	public String getCurrentPage() {
		HttpServletRequest request = (HttpServletRequest) getExternalContext().getRequest();
		return request.getRequestURL().toString();
	}

	public static void redirect(String uri) {
		try {
			String url = getBaseUrl() + uri;
			getExternalContext().redirect(url);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String caminhoRedirect(String uri) {
		return getBaseUrl() + uri;
	}

	public static String getBaseUrl() {
		HttpServletRequest request = (HttpServletRequest) getExternalContext().getRequest();
		String url = request.getRequestURL().toString();
		return url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";
	}

	private static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	public static ExternalContext getExternalContext() {
		return getFacesContext().getExternalContext();
	}

	private static HttpSession getSession() {
		FacesContext fc = getFacesContext();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		return session;
	}

}
