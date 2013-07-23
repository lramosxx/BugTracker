package br.tcc.webapp.util.binder;

import br.tcc.webapp.model.Activity;
import br.tcc.webapp.service.ActivityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

/**
 * Created with IntelliJ IDEA.
 * User: luiza
 * Date: 22/07/13
 * Time: 19:29
 */
@Component
public class ActivityCustomEditor extends PropertyEditorSupport {
// ------------------------------ FIELDS ------------------------------

    @Autowired
    ActivityManager activityManager;

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface PropertyEditor ---------------------

    @Override
    public String getAsText() {
        Activity value = (Activity) getValue();
        return (value != null ? value.getName() : "");
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Activity activity =  null;
        try {
            activity = activityManager.get(Long.parseLong(text));
        } catch (RuntimeException ex){
            setValue(new Activity());
        }

        setValue(activity);
    }
}
