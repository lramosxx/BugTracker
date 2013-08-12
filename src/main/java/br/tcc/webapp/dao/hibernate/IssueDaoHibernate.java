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
    public List<Issue> getIssuesByUser(Long idUser, Long idProject) {
        Query qry = null;

        if (idProject != null){
            qry = getSession().createQuery("from Issue i where i.project.id = ? and i.assigned.id = ? order by i.id");
            qry.setParameter(0,idProject);
            qry.setParameter(1,idUser);
        }
        else{
            qry = getSession().createQuery("from Issue i where i.assigned.id = ? order by i.id");
            qry.setParameter(0,idUser);
        }
        return qry.list();
    }

    @Override
    public List<Issue> filterIssues(String idReporter, String idAssigned, String idProject, String summary, String idStatus) {
        String query = "from Issue i where";
        boolean idFirstTerm = true;

        if ((idReporter == null || idReporter.isEmpty()) && (idAssigned == null || idAssigned.isEmpty()) && (idProject == null || idProject.isEmpty()))
        {
            Query qry = getSession().createQuery("from Issue i order by i.id");
            return qry.list();
        }
        if (idReporter != null && !idReporter.isEmpty()){
            query += " i.reporter.id = '" + idReporter + "'";
            idFirstTerm = false;
        }
        if (idAssigned != null && !idAssigned.isEmpty()){
            query += (!idFirstTerm) ? " and i.assigned.id = '" + idAssigned + "'" : " i.assigned.id = '" + idAssigned + "'";
            idFirstTerm = false;
        }
        if (idProject != null && !idProject.isEmpty()){
            query += (!idFirstTerm) ? " and i.project.id = '" + idProject + "'" : " i.project.id = '" + idProject + "'";
            idFirstTerm = false;
        }
        if (summary != null && !summary.isEmpty()){
            query += (!idFirstTerm) ? " and i.summary = '%" + summary + "%'" : " i.project.id = '" + summary + "'";
            idFirstTerm = false;
        }
        if (idStatus != null && !idStatus.isEmpty()){
            query += (!idFirstTerm) ? " and i.status.id = '" + idStatus + "'" : " i.status.id = '" + idStatus + "'";
        }

        Query qry = getSession().createQuery(query +" order by i.id");
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
