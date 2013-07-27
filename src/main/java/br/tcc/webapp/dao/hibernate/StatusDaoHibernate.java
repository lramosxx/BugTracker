package br.tcc.webapp.dao.hibernate;

import br.tcc.webapp.dao.StatusDao;
import br.tcc.webapp.model.Status;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Gleison
 * Date: 27/07/13
 * Time: 14:07
 * To change this template use File | Settings | File Templates.
 */

@Repository("statusDao")
public class StatusDaoHibernate extends GenericDaoHibernate<Status, Long> implements StatusDao {
// --------------------------- CONSTRUCTORS ---------------------------

    public StatusDaoHibernate() {
        super(Status.class);
    }

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface StatusDao ---------------------

    @Override
    public List<Status> getStatus() {
        Query qry = getSession().createQuery("from Status s order by upper(s.name)");
        return qry.list();
    }

    @Override
    public void removeStatus(Long statusId) {
        remove(statusId);
        getSession().flush();
    }

    @Override
    public Status saveStatus(Status status) {
        getSession().saveOrUpdate(status);
        getSession().flush();
        return status;
    }
}
