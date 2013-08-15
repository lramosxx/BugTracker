package br.tcc.webapp.util.binder;

import br.tcc.webapp.model.History;
import br.tcc.webapp.service.HistoryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

/**
 * Created with IntelliJ IDEA.
 * User: Gleison
 * Date: 12/08/13
 * Time: 22:51
 */
@Component
public class HistoryCustomEditor extends PropertyEditorSupport {
// ------------------------------ FIELDS ------------------------------

    @Autowired
    HistoryManager historyManager;

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface PropertyEditor ---------------------

    @Override
    public String getAsText() {
        History value = (History) getValue();
        return (value != null ? value.getComment() : "");
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        History history =  null;
        try {
            history = historyManager.get(Long.parseLong(text));
        } catch (RuntimeException ex){
            setValue(new History());
        }

        setValue(history);
    }
}
