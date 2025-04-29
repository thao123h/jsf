package project.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.application.FacesMessage;

@FacesConverter("dateConverter")
public class DateConverter implements Converter<Date> {

    private static final String DATE_PATTERN = "dd/MM/yyyy";
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);

    @Override
    public Date getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;  // có thể xử lý theo yêu cầu
        }
        try {
            return dateFormat.parse(value);
        } catch (ParseException e) {
            FacesMessage message = new FacesMessage("Invalid date format. Expected format: dd/MM/yyyy");
            context.addMessage(component.getClientId(context), message);
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Date value) {
        if (value == null) {
            return null;  // hoặc "" tùy theo yêu cầu
        }
        return dateFormat.format(value);
    }
}
