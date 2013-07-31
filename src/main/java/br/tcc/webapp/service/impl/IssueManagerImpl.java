package br.tcc.webapp.service.impl;

import br.tcc.webapp.dao.IssueDao;
import br.tcc.webapp.model.Issue;
import br.tcc.webapp.service.IssueManager;
import org.appfuse.service.impl.GenericManagerImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: luiza
 * Date: 28/07/13
 * Time: 16:01
 */
@Service("issueManager")
public class IssueManagerImpl extends GenericManagerImpl<Issue, Long> implements IssueManager {
// ------------------------------ FIELDS ------------------------------

    IssueDao issueDao;

// --------------------------- CONSTRUCTORS ---------------------------

    public IssueManagerImpl(IssueDao issueDao) {
        this.dao = issueDao;
        this.issueDao = issueDao;
    }

// --------------------- Interface IssueManager ---------------------

    @Override
    public Issue getIssue(Long issueId) {
        return issueDao.get(issueId);
    }

    @Override
    public List<Issue> getIssues() {
        return issueDao.getAllDistinct();
    }

    @Override
    public void removeIssue(Long issueId) {
        issueDao.remove(issueId);
    }

    @Override
    public Issue saveIssue(Issue issue) {
        return issueDao.saveIssue(issue);
    }

    @Override
    public List<Issue> search(String searchTerm) {
        return super.search(searchTerm, Issue.class);
    }
}
