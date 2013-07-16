package br.tcc.webapp.controller;

import br.tcc.webapp.model.Project;
import br.tcc.webapp.service.ProjectManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: luiza
 * Date: 15/07/13
 * Time: 23:56
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/admin/projectform*")
public class ProjectFormController extends BaseFormController  {
    @Autowired
    private ProjectManager projectManager;

    public ProjectFormController() {
        setCancelView("redirect:projects");
        setSuccessView("redirect:projects");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Project showForm(HttpServletRequest request)
            throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return projectManager.get(new Long(id));
        }

        return new Project();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Project project, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
            throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(project, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "/admin/projectform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (project.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            projectManager.removeProject(project.getId());
            saveMessage(request, getText("project.deleted", locale));
        } else {
            projectManager.saveProject(project);
            String key = (isNew) ? "project.added" : "project.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:/admin/projectform?id=" + project.getId();
            }
        }

        return success;
    }
}
