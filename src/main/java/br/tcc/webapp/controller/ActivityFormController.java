package br.tcc.webapp.controller;

import br.tcc.webapp.model.Activity;
import br.tcc.webapp.service.ActivityManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: Gleison
 * Date: 20/07/13
 * Time: 20:23
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/admin/activityform*")
public class ActivityFormController extends BaseFormController  {
    @Autowired
    private ActivityManager activityManager;

    public ActivityFormController() {
        setCancelView("redirect:activities");
        setSuccessView("redirect:activities");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Activity showForm(HttpServletRequest request)
            throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return activityManager.get(new Long(id));
        }

        return new Activity();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Activity activity, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
            throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(activity, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "/admin/activityform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (activity.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            activityManager.removeActivity(activity.getId());
            saveMessage(request, getText("activity.deleted", locale));
        } else {
            activityManager.saveActivity(activity);
            String key = (isNew) ? "activity.added" : "activity.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:/admin/activityform?id=" + activity.getId();
            }
        }

        return success;
    }
}
