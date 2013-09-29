package br.tcc.webapp.service.impl;

import br.tcc.webapp.dao.HistoryDao;
import br.tcc.webapp.model.History;
import br.tcc.webapp.service.HistoryManager;
import org.appfuse.service.impl.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Gleison
 * Date: 27/07/13
 * Time: 14:12
 * To change this template use File | Settings | File Templates.
 */

@Service("historyManager")
public class HistoryManagerImpl extends GenericManagerImpl<History, Long> implements HistoryManager {
// ------------------------------ FIELDS ------------------------------

    HistoryDao historyDao;

// --------------------------- CONSTRUCTORS ---------------------------

    @Autowired
    public HistoryManagerImpl(HistoryDao historyDao) {
        this.dao = historyDao;
        this.historyDao = historyDao;
    }

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface HistoryManager ---------------------

    @Override
    public History getHistory(Long historyID) {
        return historyDao.get(historyID);
    }

    @Override
    public List<History> getHistoryByIssue(Long issueId) {
        return historyDao.getHistory(issueId);
    }

    @Override
    public void removeHistory(Long historyId) {
        historyDao.removeHistory(historyId);
    }

    @Override
    public History saveHistory(History history) {
        return historyDao.saveHistory(history);
    }


    @Override
    public List<History> search(String searchTerm) {
        return super.search(searchTerm, History.class);
    }

    @Override
    public List<History> getUnreadHistory(Long userId){
        return historyDao.getUnreadHistory(userId);
    }
}
