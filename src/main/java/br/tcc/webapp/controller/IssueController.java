package br.tcc.webapp.controller;

import br.tcc.webapp.model.Issue;
import br.tcc.webapp.model.Project;
import br.tcc.webapp.service.IssueManager;
import br.tcc.webapp.service.ProjectManager;
import br.tcc.webapp.service.StatusManager;
import br.tcc.webapp.web.VideoChatService;
import br.tcc.webapp.web.command.VideoChatCommand;
import org.appfuse.dao.SearchException;
import org.appfuse.model.User;
import org.appfuse.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


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
    private UserManager userManger;

    @Autowired
    public void setIssueManager(IssueManager issueManager) {
        this.issueManager = issueManager;
    }

    @Autowired
    private VideoChatService videoChatService;

    //@RequestMapping(method = RequestMethod.GET)
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

    //@RequestMapping("/issuesByUser")
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView issuesByUser(String idProject, String q, HttpServletRequest request) throws Exception {
        Model model = new ExtendedModelMap();
        try {
            User user = userManager.getUserByUsername(request.getRemoteUser());
            HttpSession session = request.getSession(true);
            Project project = null;

            if (idProject != null) {
                if (!idProject.isEmpty())
                    session.setAttribute("currentProject", projectManager.getProject(Long.parseLong(idProject)));
                else
                    session.removeAttribute("currentProject");
            }


            if (session.getAttribute("currentProject") != null)
                project = (Project) session.getAttribute("currentProject");

            model.addAttribute("idProject", idProject);
            model.addAttribute("issueList", issueManager.getIssuesByUser(user.getId(), (project != null && project.getId() != null) ? project.getId() : null, q));
            model.addAttribute("projectsByUserList", projectManager.getProjectsByUser(user));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(issueManager.getIssues());
        }
        return new ModelAndView("issueList", model.asMap());
    }


    @RequestMapping("/videoConference/{issueId}")
    public ModelAndView getVideoConference(@PathVariable Long issueId, HttpServletRequest request) throws IOException {

        User user = userManger.getUserByUsername(request.getRemoteUser());
        VideoChatCommand command = new VideoChatCommand();
        Issue issue = issueManager.getIssue(issueId);

        String sessionKey = videoChatService.getOrCreateVideoSession(String.valueOf(issueId), issue.getProject());
        command.setApiKey(VideoChatService.TOKBOX_API_KEY);
        command.setSessionKey(sessionKey);
        command.setUserToken(videoChatService.createUserTokenForSession(sessionKey, user));


        ModelAndView mv = new ModelAndView("issuevideoconf");
        mv.addObject("user", user);
        mv.addObject("command", command);
        mv.addObject("issue", issue);
        return mv;
    }

    @RequestMapping("/issuesSearch")
    public ModelAndView issueSearch(String idAssigned, String idProject, String idReporter, String summary, String idStatus) throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute("projectList", projectManager.getProjects());
            model.addAttribute("reporterList", userManager.getUsers());
            model.addAttribute("assignedList", userManager.getUsers());
            model.addAttribute("statusList", statusManager.getStatus());
            model.addAttribute(issueManager.filterIssues(idReporter, idAssigned, idProject, summary, idStatus));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(issueManager.getIssues());
        }
        return new ModelAndView("issueSearch", model.asMap());
    }
}
