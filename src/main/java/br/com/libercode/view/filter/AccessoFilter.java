package br.com.libercode.view.filter;

import br.com.libercode.core.entity.UsuarioEntity;
import br.com.libercode.view.navigation.Router;
import org.apache.deltaspike.core.api.provider.BeanProvider;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessoFilter implements Filter {

    private String destino;
    private static final String ACESSO = "/redirecionarLogin.xhtml";
    private static final String RESTRITO = "restrito";
    private static final String ACESSO_PROIBIDO_PAGE = "/acessoProibido.xhtml";

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    @SuppressWarnings("unchecked")
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        UsuarioEntity usuarioLogado = (UsuarioEntity) request.getSession()
                .getAttribute("usuarioLogado");

        destino = request.getServletPath();

        if (!destino.contains(".xhtml")) {
            chain.doFilter(request, response);
        } else if (destino.contains(ACESSO)) {
            if (usuarioLogado != null) {
                redirectInicio(request, response);
            } else {
                chain.doFilter(request, response);
            }
        } else if (destino.contains(RESTRITO)) {
            if (usuarioLogado == null) {
                response.sendRedirect(request.getContextPath() + ACESSO_PROIBIDO_PAGE);
            } else {
                    chain.doFilter(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    private void redirectInicio(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Router router = BeanProvider.getContextualReference(Router.class, false);
        String path = request.getContextPath() + "/"
                + router.urlLogin();
        response.sendRedirect(path);
    }

    public boolean paginasPermitidasVisitantes() {
        if (destino.equals("/site/pages/teste.xhtml") || destino.equals("/index.xhtml")
                || destino.equals("/redirecionarLogin.xhtml")) {
            return true;
        } else {
            return false;
        }
    }

}
