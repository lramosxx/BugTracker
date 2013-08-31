package br.tcc.webapp.controller;

import br.tcc.webapp.model.Departament;
import br.tcc.webapp.service.DepartamentManager;
import org.appfuse.dao.SearchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/admin/departaments*")
public class DepartamentController {
    private DepartamentManager departamentManager = null;

    @Autowired
    public void setDepartamentManager(DepartamentManager departamentManager) {
        this.departamentManager = departamentManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleRequest(@RequestParam(required = false, value = "q") String query) throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute("departamentList", departamentManager.search(query));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(departamentManager.getDepartaments());
        }
        return new ModelAndView("admin/departamentList", model.asMap());
    }

}
