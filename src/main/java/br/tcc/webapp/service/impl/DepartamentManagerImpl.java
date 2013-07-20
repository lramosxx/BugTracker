package br.tcc.webapp.service.impl;

import br.tcc.webapp.dao.DepartamentDao;
import br.tcc.webapp.model.Departament;
import br.tcc.webapp.service.DepartamentManager;
import org.appfuse.service.impl.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Gleison
 * Date: 19/07/13
 * Time: 13:31
 * To change this template use File | Settings | File Templates.
 */

@Service("departamentManager")
public class DepartamentManagerImpl extends GenericManagerImpl<Departament, Long> implements DepartamentManager {
// ------------------------------ FIELDS ------------------------------

    DepartamentDao departamentDao;

// --------------------------- CONSTRUCTORS ---------------------------

    @Autowired
    public DepartamentManagerImpl(DepartamentDao departamentDao) {
        this.dao = departamentDao;
        this.departamentDao = departamentDao;
    }

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface DepartamentManager ---------------------

    @Override
    public Departament getDepartament(Long departamentID) {
        return departamentDao.get(departamentID);
    }

    @Override
    public List<Departament> getDepartaments() {
        return departamentDao.getAllDistinct();
    }

    @Override
    public void removeDepartament(Long departamentId) {
        departamentDao.removeDepartament(departamentId);
    }

    @Override
    public Departament saveDepartament(Departament departament) {
        return departamentDao.saveDepartament(departament);
    }

    @Override
    public List<Departament> search(String searchTerm) {
        return super.search(searchTerm, Departament.class);
    }
}
