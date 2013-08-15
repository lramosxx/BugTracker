package br.tcc.webapp.controller;

import br.tcc.webapp.model.Issue;
import br.tcc.webapp.service.IssueManager;
import br.tcc.webapp.service.ProjectManager;
import br.tcc.webapp.service.StatusManager;
import org.appfuse.dao.SearchException;
import org.appfuse.model.User;
import org.appfuse.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/issues*")
public class IssueController {
    private IssueManager issueManager = null;

    @Autowired
    private ProjectManager projectManager;
    @Autowired
    private UserManager userManager;

    @Autowired
    private StatusManager statusManager;

    @Autowired
    public void setIssueManager(IssueManager issueManager) {
        this.issueManager = issueManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleRequest(@RequestParam(required = false, value = "q") String query, HttpServletRequest request) throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute("issueList", issueManager.search(query));
            model.addAttribute("projectsByUserList", projectManager.getProjectsByUser(userManager.getUserByUsername(request.getRemoteUser())));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(issueManager.getIssues());
        }
        return new ModelAndView("issueList", model.asMap());
    }

    @RequestMapping("/issuesByUser")
    public ModelAndView issuesByUser(String idProject,HttpServletRequest request) throws Exception {
        Model model = new ExtendedModelMap();
        try {
            User user = userManager.getUserByUsername(request.getRemoteUser());
            model.addAttribute("idProject", idProject);
            model.addAttribute("issueList", issueManager.getIssuesByUser(user.getId(), (idProject != null && !idProject.isEmpty()) ? Long.parseLong(idProject) : null));
            model.addAttribute("projectsByUserList", projectManager.getProjectsByUser(user));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(issueManager.getIssues());
        }
        return new ModelAndView("issueList", model.asMap());
    }

    @RequestMapping("/issuesSearch")
    public ModelAndView issueSearch(String idAssigned, String idProject, String idReporter, String summary, String idStatus) throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute("projectList", projectManager.getProjects());
            model.addAttribute("reporterList", userManager.getUsers());
            model.addAttribute("assignedList", userManager.getUsers());
            model.addAttribute("statusList",statusManager.getStatus());
            model.addAttribute(issueManager.filterIssues(idReporter, idAssigned, idProject, summary, idStatus));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(issueManager.getIssues());
        }
        return new ModelAndView("issueSearch", model.asMap());
    }
}
