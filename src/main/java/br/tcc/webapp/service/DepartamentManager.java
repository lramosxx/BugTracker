package br.tcc.webapp.service;

import br.tcc.webapp.model.Departament;
import org.appfuse.service.GenericManager;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Gleison
 * Date: 19/07/13
 * Time: 13:30
 * To change this template use File | Settings | File Templates.
 */
public interface DepartamentManager extends GenericManager<Departament, Long> {
// -------------------------- OTHER METHODS --------------------------

    Departament getDepartament(Long departamentID);

    List<Departament> getDepartaments();

    void removeDepartament(Long departamentId);

    Departament saveDepartament(Departament departament); //throws UserExistsException;

    List<Departament> search(String searchTerm);
}
