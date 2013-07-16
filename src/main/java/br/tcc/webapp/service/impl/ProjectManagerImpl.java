package br.tcc.webapp.service.impl;

import br.tcc.webapp.dao.ProjectDao;
import br.tcc.webapp.model.Project;
import br.tcc.webapp.service.ProjectManager;
import org.appfuse.model.User;
import org.appfuse.service.impl.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: luiza
 * Date: 15/07/13
 * Time: 18:03
 * To change this template use File | Settings | File Templates.
 */

@Service("projectManager")
public class ProjectManagerImpl extends GenericManagerImpl<Project, Long> implements ProjectManager {
// ------------------------------ FIELDS ------------------------------

    ProjectDao projectDao;

// --------------------------- CONSTRUCTORS ---------------------------

    @Autowired
    public ProjectManagerImpl(ProjectDao projectDao) {
        this.dao = projectDao;
        this.projectDao = projectDao;
    }

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface ProjectManager ---------------------

    @Override
    public Project getProject(Long projectID) {
        return projectDao.get(projectID);
    }

    @Override
    public List<Project> getProjects() {
        return projectDao.getAllDistinct();
    }

    @Override
    public void removeProject(Long projectId) {
        projectDao.removeProject(projectId);
    }

    @Override
    public Project saveProject(Project project) {
        return projectDao.saveProject(project);
    }

    @Override
    public List<Project> search(String searchTerm) {
        return super.search(searchTerm, Project.class);
    }
}
