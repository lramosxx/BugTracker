package br.tcc.webapp.controller;

import br.tcc.webapp.service.StatusManager;
import org.appfuse.dao.SearchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/admin/status*")
public class StatusController {
    private StatusManager statusManager = null;

    @Autowired
    public void setStatusManager(StatusManager statusManager) {
        this.statusManager = statusManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleRequest(@RequestParam(required = false, value = "q") String query) throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute("statusList", statusManager.search(query));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(statusManager.getStatus());
        }
        return new ModelAndView("admin/statusList", model.asMap());
    }
}
