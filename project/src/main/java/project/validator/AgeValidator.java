package project.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
@FacesValidator("ageValidator")
public class AgeValidator implements Validator<Integer> {

    @Override
    public void validate(FacesContext context, UIComponent component, Integer value) throws ValidatorException {
        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);

        if (value == null || value == 0 ) {
            msg.setSummary("Age cannot be 0!");
            throw new ValidatorException(msg);
        }
     
           if (value< 18 || value> 100) {
                msg.setSummary("Age must be between 18 and 100!");
                throw new ValidatorException(msg);    
    }
	
}
}
