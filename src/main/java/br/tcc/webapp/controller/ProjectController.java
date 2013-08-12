package br.tcc.webapp.controller;

import br.tcc.webapp.service.ProjectManager;
import org.appfuse.Constants;
import org.appfuse.dao.SearchException;
import org.appfuse.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: luiza
 * Date: 15/07/13
 * Time: 17:33
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin/projects*")
public class ProjectController {
// ------------------------------ FIELDS ------------------------------

    @Autowired
    private ProjectManager projectManager;
    @Autowired
    private UserManager userManager;

// -------------------------- OTHER METHODS --------------------------

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleRequest(@RequestParam(required = false, value = "q") String query) throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute("projectList", projectManager.search(query));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(projectManager.getProjects());
        }
        return new ModelAndView("admin/projects", model.asMap());
    }
}
