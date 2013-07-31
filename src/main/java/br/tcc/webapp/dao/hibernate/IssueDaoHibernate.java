package br.tcc.webapp.dao.hibernate;

import br.tcc.webapp.dao.IssueDao;
import br.tcc.webapp.model.Issue;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: luiza
 * Date: 28/07/13
 * Time: 16:50
 */

@Repository("issueDao")
public class IssueDaoHibernate extends GenericDaoHibernate<Issue, Long> implements IssueDao {
// --------------------------- CONSTRUCTORS ---------------------------

    public IssueDaoHibernate() {
        super(Issue.class);
    }

// ------------------------ INTERFACE METHODS ------------------------

    @Override
    public List<Issue> getIssues() {
        Query qry = getSession().createQuery("from Issue i order by i.id");
        return qry.list();
    }

    @Override
    public void removeIssue(Long issueId) {
        remove(issueId);
        getSession().flush();
    }

    @Override
    public Issue saveIssue(Issue issue) {
        getSession().saveOrUpdate(issue);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getSession().flush();
        return issue;
    }
}
