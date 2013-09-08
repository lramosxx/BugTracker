package br.tcc.webapp.controller;

import br.tcc.webapp.model.Activity;
import br.tcc.webapp.service.ActivityManager;
import org.appfuse.Constants;
import org.appfuse.dao.SearchException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.map.annotate.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Context;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.nio.charset.Charset;
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
    public ResponseEntity<String> getActivities(String idDepartament,HttpServletResponse response) throws Exception {


        ObjectMapper mapper = new ObjectMapper();
        List<Activity> model = activityManager.getActivitiesByDepartament(Long.parseLong(idDepartament));

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(model);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        response.setCharacterEncoding("UTF-8");

        return new ResponseEntity<String>(json, responseHeaders, HttpStatus.CREATED);

    }
}
