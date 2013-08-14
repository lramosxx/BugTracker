package br.tcc.webapp.service;

import br.tcc.webapp.model.History;
import org.appfuse.service.GenericManager;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Gleison
 * Date: 13/08/13
 * Time: 22:09
 * To change this template use File | Settings | File Templates.
 */
public interface HistoryManager extends GenericManager<History, Long> {
// -------------------------- OTHER METHODS --------------------------

    History getHistory(Long historyID);

    List<History> getHistoryByIssue(Long issueId);

    void removeHistory(Long historyId);

    History saveHistory(History history);

    List<History> search(String searchTerm);
}
