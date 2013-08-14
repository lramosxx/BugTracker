package br.tcc.webapp.dao;

import br.tcc.webapp.model.History;
import org.appfuse.dao.GenericDao;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Gleison
 * Date: 13/08/13
 * Time: 21:30
 * To change this template use File | Settings | File Templates.
 */
public interface HistoryDao extends GenericDao<History, Long> {
// -------------------------- OTHER METHODS --------------------------

    List<History> getHistory(Long issueId);

    void removeHistory(Long historyId);

    History saveHistory(History history);
}
