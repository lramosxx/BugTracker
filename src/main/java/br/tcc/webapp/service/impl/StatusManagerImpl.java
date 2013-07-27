package br.tcc.webapp.service.impl;

import br.tcc.webapp.dao.StatusDao;
import br.tcc.webapp.model.Status;
import br.tcc.webapp.service.StatusManager;
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

@Service("statusManager")
public class StatusManagerImpl extends GenericManagerImpl<Status, Long> implements StatusManager {
// ------------------------------ FIELDS ------------------------------

    StatusDao statusDao;

// --------------------------- CONSTRUCTORS ---------------------------

    @Autowired
    public StatusManagerImpl(StatusDao statusDao) {
        this.dao = statusDao;
        this.statusDao = statusDao;
    }

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface StatusManager ---------------------

    @Override
    public Status getStatus(Long statusID) {
        return statusDao.get(statusID);
    }

    @Override
    public List<Status> getStatus() {
        return statusDao.getAllDistinct();
    }

    @Override
    public void removeStatus(Long statusId) {
        statusDao.removeStatus(statusId);
    }

    @Override
    public Status saveStatus(Status status) {
        return statusDao.saveStatus(status);
    }

    @Override
    public List<Status> search(String searchTerm) {
        return super.search(searchTerm, Status.class);
    }
}
