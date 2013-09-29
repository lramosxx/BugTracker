package br.tcc.webapp.dao.hibernate;

import br.tcc.webapp.dao.HistoryDao;
import br.tcc.webapp.model.History;
import br.tcc.webapp.model.Issue;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
    public void removeHistory(Long historyId) {
        remove(historyId);
        getSession().flush();
    }

    @Override
    public List<History> getHistory(Long issueId) {
        Query qry = getSession().createQuery("from Issue i where i.id = ?");
        qry.setParameter(0,issueId);

        List<Issue> issueList = qry.list();

        if (issueList.size() > 0)
            return issueList.get(0).getHistory();

        return null;
    }

    @Override
    public History saveHistory(History history) {
        getSession().saveOrUpdate(history);
        getSession().flush();
        return history;
    }

    @Override
    public List<History> getUnreadHistory(Long userId){

        Query qry = getSession()
                .createQuery("from Issue i where i.status.id != 2 and i.history.size > 0 and" +
                " (i.reporter.id = ? or i.assigned.id = ?) order by i.id");

        qry.setParameter(0, userId);
        qry.setParameter(1, userId);

        List<Issue> issueList = qry.list();

        ArrayList<History> historyList =  new ArrayList<History>();

        for (int i = 0; i < issueList.size(); i++){
            if (issueList.get(i).getHistory().size() > 0){
                List<History> auxList = issueList.get(i).getHistory();
                for (int j = 0; j < auxList.size(); j++){
                    if (!auxList.get(j).getRead() && auxList.get(j).getAuthor().getId() != userId)
                        historyList.add(auxList.get(j));
                }
            }
        }

        return historyList;
    }

}
