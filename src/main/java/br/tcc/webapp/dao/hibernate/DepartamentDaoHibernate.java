package br.tcc.webapp.dao.hibernate;

import br.tcc.webapp.dao.DepartamentDao;
import br.tcc.webapp.model.Departament;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Gleison
 * Date: 19/07/13
 * Time: 15:04
 * To change this template use File | Settings | File Templates.
 */

@Repository("departamentDao")
public class DepartamentDaoHibernate extends GenericDaoHibernate<Departament, Long> implements DepartamentDao {
// --------------------------- CONSTRUCTORS ---------------------------

    public DepartamentDaoHibernate() {
        super(Departament.class);
    }

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface DepartamentDao ---------------------

    @Override
    public List<Departament> getDepartaments() {
        Query qry = getSession().createQuery("from Departament d order by upper(d.name)");
        return qry.list();
    }

    @Override
    public void removeDepartament(Long departamentId) {
        remove(departamentId);
        getSession().flush();
    }

    @Override
    public Departament saveDepartament(Departament departament) {
        getSession().saveOrUpdate(departament);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getSession().flush();
        return departament;
    }
}
