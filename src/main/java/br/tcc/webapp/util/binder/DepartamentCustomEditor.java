package br.tcc.webapp.util.binder;

import br.tcc.webapp.model.Departament;
import br.tcc.webapp.service.DepartamentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

/**
 * Created with IntelliJ IDEA.
 * User: Gleison
 * Date: 03/08/13
 * Time: 19:40
 */
@Component
public class DepartamentCustomEditor extends PropertyEditorSupport {
// ------------------------------ FIELDS ------------------------------

    @Autowired
    DepartamentManager departamentManager;

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface PropertyEditor ---------------------

    @Override
    public String getAsText() {
        Departament value = (Departament) getValue();
        return (value != null ? value.getName() : "");
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Departament departament =  null;
        try {
            departament = departamentManager.get(Long.parseLong(text));
        } catch (RuntimeException ex){
            setValue(new Departament());
        }

        setValue(departament);
    }
}
