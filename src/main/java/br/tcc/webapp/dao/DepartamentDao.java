package br.tcc.webapp.dao;

import br.tcc.webapp.model.Departament;
import org.appfuse.dao.GenericDao;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Gleison
 * Date: 19/07/13
 * Time: 13:36
 * To change this template use File | Settings | File Templates.
 */
public interface DepartamentDao extends GenericDao<Departament, Long> {
// -------------------------- OTHER METHODS --------------------------

    List<Departament> getDepartaments();

    void removeDepartament(Long departamentId);

    Departament saveDepartament(Departament departament);
}
