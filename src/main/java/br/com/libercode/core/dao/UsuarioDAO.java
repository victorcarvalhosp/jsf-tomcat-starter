package br.com.libercode.core.dao;

import br.com.libercode.core.entity.UsuarioEntity;

import javax.persistence.TypedQuery;

public class UsuarioDAO extends AbstractCrudDAO<UsuarioEntity> {

    private static final long serialVersionUID = -7042987094809207437L;

    public UsuarioEntity findByLoginAndPassword(String login, String password) {
        String qlString = "SELECT tab from UsuarioEntity tab where tab.login = :login and tab.senha = :senha";
        TypedQuery<UsuarioEntity> query = entityManager.createQuery(qlString, UsuarioEntity.class).setParameter("login", login).setParameter("senha", password);
        return getSingleOrNull(query);
    }

}
