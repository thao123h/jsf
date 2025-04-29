package project.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("nameValidator")
public class NameValidator implements Validator<String> {

    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);

        if (value == null || value.trim().isEmpty()) {
            msg.setSummary("Name cannot be empty!");
            throw new ValidatorException(msg);
        }

        String valueString = value.trim();

        if (valueString.length() < 2 || valueString.length() > 50) {
            msg.setSummary("The name must be between 2 and 50 characters!");
            throw new ValidatorException(msg);
        }

        if (valueString.matches(".*\\d.*")) {
            msg.setSummary("The name cannot contain numbers!");
            throw new ValidatorException(msg);
        }
    }
}
