package project.validator;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
@FacesValidator("dateValidator")
public class DateValidator implements Validator<String> {

    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);  

        if (value == null || value.trim().isEmpty()) {
            msg.setSummary("Date cannot be empty!");
            throw new ValidatorException(msg);
        }
        String valueString = value.trim();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setLenient(false);
        Date date;
        try {
            date = format.parse(valueString);
            // Check if the date is after the current date
            if (date.after(new Date())) {
                msg.setSummary("Date of birth must be before today");
                throw new ValidatorException(msg);
            }
        } catch (Exception e) {
            msg.setSummary("Invalid date format. Correct example: 12/05/2001");
            throw new ValidatorException(msg);
        }
    }
}
