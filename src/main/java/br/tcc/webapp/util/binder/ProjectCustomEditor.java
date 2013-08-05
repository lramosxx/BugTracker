package br.tcc.webapp.util.binder;

import br.tcc.webapp.model.Project;
import br.tcc.webapp.service.ProjectManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

/**
 * Created with IntelliJ IDEA.
 * User: Gleison
 * Date: 03/08/13
 * Time: 19:29
 */
@Component
public class ProjectCustomEditor extends PropertyEditorSupport {
// ------------------------------ FIELDS ------------------------------

    @Autowired
    ProjectManager projectManager;

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface PropertyEditor ---------------------

    @Override
    public String getAsText() {
        Project value = (Project) getValue();
        return (value != null ? value.getName() : "");
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Project project =  null;
        try {
            project = projectManager.get(Long.parseLong(text));
        } catch (RuntimeException ex){
            setValue(new Project());
        }

        setValue(project);
    }
}
