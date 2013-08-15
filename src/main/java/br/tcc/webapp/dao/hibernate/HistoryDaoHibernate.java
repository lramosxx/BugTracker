package br.tcc.webapp.dao.hibernate;

import br.tcc.webapp.dao.HistoryDao;
import br.tcc.webapp.model.History;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Gleison
 * Date: 13/08/13
 * Time: 21:34
 * To change this template use File | Settings | File Templates.
 */

@Repository("historyDao")
public class HistoryDaoHibernate extends GenericDaoHibernate<History, Long> implements HistoryDao {
// --------------------------- CONSTRUCTORS ---------------------------

    public HistoryDaoHibernate() {
        super(History.class);
    }

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface HistoryDao ---------------------

    @Override
    public List<History> getHistory(Long issueId) {
        Query qry = getSession().createQuery("from History h where h.issue.id = ? order by h.date");
        qry.setParameter(0,issueId);
        return qry.list();
    }

    @Override
    public void removeHistory(Long historyId) {
        remove(historyId);
        getSession().flush();
    }

    @Override
    public History saveHistory(History history) {
        getSession().saveOrUpdate(history);
        getSession().flush();
        return history;
    }
}
