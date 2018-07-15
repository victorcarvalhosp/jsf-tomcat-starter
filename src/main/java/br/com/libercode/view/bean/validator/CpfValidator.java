package br.com.libercode.view.bean.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.io.Serializable;

@FacesValidator("br.com.libercode.CpfValidator")
public class CpfValidator extends DocValidator implements Validator, Serializable {

    private static final int[] peso = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

    public boolean isValidCPF(String cpf) {
        cpf = normalizar(cpf);
        if (cpf == null || cpf.isEmpty()) {
            return true;
        }
        if (cpf.length() != 11) {
            return false;
        }
        final Integer digito1 = calcularDigito(cpf.substring(0,9), peso);
        final Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, peso);
        return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (!isValidCPF((String) value)) {
            throwError();
        }
    }

    private String normalizar(String value) {
        String normalizado = value.replace(".", "")
                .replace("_", "")
                .replace("-", "");
        while (normalizado.length() < 11) {
            normalizado = "0".concat(normalizado);
        }
        return normalizado;
    }

    @Override
    public void throwError() {
        final FacesMessage msg = new FacesMessage("Cpf Inválido");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        throw new ValidatorException(msg);
    }
}
