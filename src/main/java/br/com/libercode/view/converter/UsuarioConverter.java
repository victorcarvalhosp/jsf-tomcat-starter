package br.com.libercode.view.converter;

import br.com.libercode.core.dao.ConverterPersistenceUtility;
import br.com.libercode.core.entity.UsuarioEntity;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=UsuarioEntity.class)
public class UsuarioConverter implements Converter {
 
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String string) {
        if(string == null || string.isEmpty()){
            return null;
        }
        UsuarioEntity u = new ConverterPersistenceUtility<UsuarioEntity>().buscarPorId(UsuarioEntity.class, Long.parseLong(string));
        return u;
    }
 
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) {
        UsuarioEntity usuario = (UsuarioEntity) object;
        if(usuario == null || usuario.getId() == null){
            return null;
        }
        return String.valueOf(usuario.getId());
    }
}