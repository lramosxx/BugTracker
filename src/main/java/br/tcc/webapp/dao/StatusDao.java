package br.tcc.webapp.dao;

import br.tcc.webapp.model.Status;
import org.appfuse.dao.GenericDao;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Gleison
 * Date: 27/07/13
 * Time: 14:06
 * To change this template use File | Settings | File Templates.
 */
public interface StatusDao extends GenericDao<Status, Long> {
// -------------------------- OTHER METHODS --------------------------

    List<Status> getStatus();

    void removeStatus(Long statusId);

    Status saveStatus(Status status);
}
