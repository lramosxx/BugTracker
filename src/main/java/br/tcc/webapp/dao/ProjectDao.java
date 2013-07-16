package br.tcc.webapp.dao;

import br.tcc.webapp.model.Project;
import org.appfuse.dao.GenericDao;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: luiza
 * Date: 15/07/13
 * Time: 17:43
 * To change this template use File | Settings | File Templates.
 */
public interface ProjectDao extends GenericDao<Project, Long> {
// -------------------------- OTHER METHODS --------------------------

    List<Project> getProjects();

    void removeProject(Long projectId);

    Project saveProject(Project project);
}
