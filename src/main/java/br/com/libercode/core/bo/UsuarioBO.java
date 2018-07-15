package br.com.libercode.core.bo;

import br.com.libercode.core.dao.UsuarioDAO;
import br.com.libercode.core.entity.UsuarioEntity;
import br.com.libercode.core.util.CriptoUtil;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class UsuarioBO extends AbstractCrudBO<UsuarioEntity> {

    private static final long serialVersionUID = 4004443660916669169L;

    public UsuarioEntity findByLoginAndPassword(String login, String password) {
        StringBuilder sb = new StringBuilder(CriptoUtil.cript(password, CriptoUtil.PROJECT_KEY));
        password = sb.substring(0, sb.length() - 1);
        return getDao().findByLoginAndPassword(login, password);
    }

    public void colocarUsuarioCondominioNaSessao(UsuarioEntity usuario) {
        ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession()
                .setAttribute("usuarioLogado", usuario);
    }


    @Override
    public UsuarioDAO getDao() {
        return (UsuarioDAO) super.getDao();
    }


}
