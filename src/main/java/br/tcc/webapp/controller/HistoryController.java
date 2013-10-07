package br.tcc.webapp.controller;

import br.tcc.webapp.model.History;
import br.tcc.webapp.service.HistoryManager;
import org.appfuse.dao.SearchException;
import org.appfuse.model.User;
import org.appfuse.service.UserManager;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.hibernate.context.spi.CurrentSessionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequestMapping("/history/*")
public class HistoryController {
    private HistoryManager historyManager = null;

    @Autowired
    private UserManager userManager = null;

    @Autowired
    public void setHistoryManager(HistoryManager historyManager) {
        this.historyManager = historyManager;
    }

    @RequestMapping("/getUnreadtHistory")
    public ResponseEntity<String> geUnreadtHistory(String idUser, HttpServletRequest request,HttpServletResponse response) throws Exception {

        User user = userManager.getUserByUsername(request.getRemoteUser());

        ObjectMapper mapper = new ObjectMapper();
        List<History> model = historyManager.getUnreadHistory((idUser == null || idUser.isEmpty()) ? user.getId() : Long.parseLong(idUser));

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(model);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        response.setCharacterEncoding("ISO-8859-1");

        return new ResponseEntity<String>(json, responseHeaders, HttpStatus.CREATED);

    }


    @RequestMapping("/checkAsRead")
    public void checkAsRead(String idHistory, HttpServletRequest request,HttpServletResponse response) throws Exception {
        History hist = null;

        if (idHistory != null && !idHistory.isEmpty())
            hist = historyManager.getHistory(Long.parseLong(idHistory));

        if (hist != null){
            hist.setRead(true);
            historyManager.saveHistory(hist);
        }

        request.setAttribute("id",idHistory);
        response.sendRedirect("/issueform?id="+hist.getIdIssue());
    }
}
