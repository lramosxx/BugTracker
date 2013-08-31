package br.tcc.webapp.controller;

import br.tcc.webapp.model.Activity;
import br.tcc.webapp.service.ActivityManager;
import org.appfuse.Constants;
import org.appfuse.dao.SearchException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/admin/activities*")
public class ActivityController {
    private ActivityManager activityManager = null;

    @Autowired
    public void setActivityManager(ActivityManager activityManager) {
        this.activityManager = activityManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleRequest(@RequestParam(required = false, value = "q") String query) throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute("activityList", activityManager.search(query));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(activityManager.getActivities());
        }
        return new ModelAndView("admin/activityList", model.asMap());
    }

    @RequestMapping("/getActivities")
    public String getActivities(String idDepartament) throws Exception {


        ObjectMapper mapper = new ObjectMapper();
        String str = activityManager.getActivitiesByDepartament(Long.parseLong(idDepartament)).toString();

        return str;
        //Map<String,Object> map = mapper.readValue(activityManager.getActivitiesByDepartament(Long.parseLong(idDepartament)).toString(),Map.class);




        //if (idDepartament != null && !idDepartament.isEmpty())
          //  return activityManager.getActivitiesByDepartament(Long.parseLong(idDepartament)).toString();



       // return null;
    }
}
