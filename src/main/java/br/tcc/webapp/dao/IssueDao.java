package br.tcc.webapp.dao;

import br.tcc.webapp.model.Issue;
import org.appfuse.dao.GenericDao;


import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: luiza
 * Date: 28/07/13
 * Time: 16:47
 */
public interface IssueDao extends GenericDao<Issue, Long> {
// -------------------------- OTHER METHODS --------------------------

    List<Issue> getIssues();

    List<Issue> filterIssues(String idReporter, String idAssigned, String idProject, String summary, String idStatus);

    void removeIssue(Long issueId);

    Issue saveIssue(Issue issue);

    List<Issue> getIssuesByUser(Long idUser, Long idProject, String q);
}
