package br.tcc.webapp.controller;

import br.tcc.webapp.model.Status;
import br.tcc.webapp.service.StatusManager;
import org.apache.commons.lang.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
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
 * User: Gleison
 * Date: 27/07/13
 * Time: 14:20
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/admin/statusform*")
public class StatusFormController extends BaseFormController  {
    @Autowired
    private StatusManager statusManager;

    public StatusFormController() {
        setCancelView("redirect:status");
        setSuccessView("redirect:status");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Status showForm(HttpServletRequest request)
            throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return statusManager.get(new Long(id));
        }

        return new Status();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Status status, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
            throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(status, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "/admin/statusform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (status.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        try{
            if (request.getParameter("delete") != null) {
                statusManager.removeStatus(status.getId());
                saveMessage(request, getText("status.deleted", locale));
            } else {
                statusManager.saveStatus(status);
                String key = (isNew) ? "status.added" : "status.updated";
                saveMessage(request, getText(key, locale));

                if (!isNew) {
                    success = "redirect:/admin/statusform?id=" + status.getId();
                }
            }
        }
        catch(ConstraintViolationException ex){
            saveError(request, getText("item.cantbe.removed", locale));
        }

        return success;
    }
}
