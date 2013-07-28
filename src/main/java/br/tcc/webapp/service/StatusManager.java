package br.tcc.webapp.service;

import br.tcc.webapp.model.Status;
import org.appfuse.service.GenericManager;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Gleison
 * Date: 27/07/13
 * Time: 14:11
 * To change this template use File | Settings | File Templates.
 */
public interface StatusManager extends GenericManager<Status, Long> {
// -------------------------- OTHER METHODS --------------------------

    Status getStatus(Long statusID);

    List<Status> getStatus();

    void removeStatus(Long statusId);

    Status saveStatus(Status status);

    List<Status> search(String searchTerm);
}
