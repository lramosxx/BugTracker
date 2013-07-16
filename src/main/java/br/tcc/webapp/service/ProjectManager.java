package br.tcc.webapp.service;

import br.tcc.webapp.model.Project;
import org.appfuse.model.User;
import org.appfuse.service.GenericManager;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: luiza
 * Date: 15/07/13
 * Time: 18:03
 * To change this template use File | Settings | File Templates.
 */
public interface ProjectManager extends GenericManager<Project, Long> {
// -------------------------- OTHER METHODS --------------------------

    Project getProject(Long projectID);

    List<Project> getProjects();

    void removeProject(Long projectId);

    Project saveProject(Project project); //throws UserExistsException;

    List<Project> search(String searchTerm);
}
