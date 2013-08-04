package br.tcc.webapp.util.binder;

import br.tcc.webapp.model.Status;
import br.tcc.webapp.service.StatusManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

/**
 * Created with IntelliJ IDEA.
 * User: Gleison
 * Date: 03/08/13
 * Time: 19:16
 */
@Component
public class StatusCustomEditor extends PropertyEditorSupport {
// ------------------------------ FIELDS ------------------------------

    @Autowired
    StatusManager statusManager;

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface PropertyEditor ---------------------

    @Override
    public String getAsText() {
        Status value = (Status) getValue();
        return (value != null ? value.getDescription() : "");
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Status status =  null;
        try {
            status = statusManager.get(Long.parseLong(text));
        } catch (RuntimeException ex){
            setValue(new Status());
        }

        setValue(status);
    }
}
