package br.tcc.webapp.service;

import br.tcc.webapp.model.Issue;
import br.tcc.webapp.model.Project;
import org.appfuse.model.User;
import org.appfuse.service.GenericManager;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: luiza
 * Date: 28/07/13
 * Time: 16:00
 */
public interface IssueManager extends GenericManager<Issue, Long> {
// -------------------------- OTHER METHODS --------------------------

    Issue getIssue(Long issueId);

    List<Issue> filterIssues(String idReporter, String idAssigned, String idProject, String summary, String idStatus);

    List<Issue> getIssues();

    void removeIssue(Long issueId);

    Issue saveIssue(Issue issue);

    List<Issue> search(String searchTerm);

    List<Issue> getIssuesByUser(Long idUser, Long idProject);
}
