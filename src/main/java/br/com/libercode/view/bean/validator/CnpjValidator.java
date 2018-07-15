package br.com.libercode.view.bean.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.io.Serializable;

@FacesValidator("br.com.libercode.CnpjValidator")
public class CnpjValidator extends DocValidator implements Validator, Serializable {
    private static final int[] peso = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    @Override
    void throwError() {
        final FacesMessage msg = new FacesMessage("Cnpj Inválido");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        throw new ValidatorException(msg);
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (!isValidCNPJ((String) value)) {
            throwError();
        }
    }

    private String normalizar(String cnpj) {
        String normalizado = cnpj.replace(".", "")
                .replace("_", "")
                .replace("/", "")
                .replace("-", "");
        while (normalizado.length() < 14) {
            normalizado = "0".concat(normalizado);
        }
        return normalizado;
    }

    public boolean isValidCNPJ(String cnpj) {
        cnpj = normalizar(cnpj);
        if (cnpj == null || cnpj.isEmpty()) {
            return true;
        }
        if (cnpj.length() != 14) {
            return false;
        }
        final Integer digito1 = calcularDigito(cnpj.substring(0,12), peso);
        final Integer digito2 = calcularDigito(cnpj.substring(0,12) + digito1, peso);
        return cnpj.equals(cnpj.substring(0,12) + digito1.toString() + digito2.toString());
    }
}
