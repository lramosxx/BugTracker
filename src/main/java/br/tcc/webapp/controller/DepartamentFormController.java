package br.tcc.webapp.controller;

import br.tcc.webapp.model.Activity;
import br.tcc.webapp.model.Departament;
import br.tcc.webapp.service.ActivityManager;
import br.tcc.webapp.service.DepartamentManager;
import org.apache.avro.generic.GenericData;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Gleison
 * Date: 19/07/13
 * Time: 20:09
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/admin/departamentform*")
public class DepartamentFormController extends BaseFormController  {
    @Autowired
    private DepartamentManager departamentManager;
    @Autowired
    private ActivityManager activityManager;

    public DepartamentFormController() {
        setCancelView("redirect:departaments");
        setSuccessView("redirect:departaments");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Departament showForm(HttpServletRequest request)
            throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return departamentManager.get(new Long(id));
        }

        Departament dept = new Departament();
        Map< String, String > activityMap = new HashMap();
        List<Activity> activities = activityManager.getActivities();

        for (int i =0; i < activities.size(); i++)
        {
            activityMap.put(activities.get(i).getId().toString(),activities.get(i).getName());
        }

        request.setAttribute("newActivities",activityManager.getActivities());

        dept.setActivities(activityManager.getActivities());

        return dept;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Departament departament, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
            throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        //if (request.getParameter("activities") != null){
          //  String activities = request.getParameter("activities");
        //}

        if (validator != null) { // validator is null during testing
            validator.validate(departament, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "/admin/departamentform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (departament.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            departamentManager.removeDepartament(departament.getId());
            saveMessage(request, getText("departament.deleted", locale));
        } else {
            departamentManager.saveDepartament(departament);
            String key = (isNew) ? "departament.added" : "departament.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:/admin/departamentform?id=" + departament.getId();
            }
        }

        return success;
    }
}
