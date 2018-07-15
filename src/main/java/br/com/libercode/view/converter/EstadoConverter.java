package br.com.libercode.view.converter;

import br.com.libercode.core.dao.ConverterPersistenceUtility;
import br.com.libercode.core.entity.EstadoEntity;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=EstadoEntity.class)
public class EstadoConverter implements Converter {
 
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String string) {
        if(string == null || string.isEmpty()){
            return null;
        }
        
        try {
            return new ConverterPersistenceUtility<EstadoEntity>().buscarPorId(EstadoEntity.class,new Long(string));

		} catch (Exception e) {
			// TODO: handle exception
            return null;

		}
    }
 
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) {
        EstadoEntity estado = (EstadoEntity) object;
        if(estado == null || estado.getId() == null){
            return null;
        }
        return String.valueOf(estado.getId());
    }
}