package br.tcc.webapp.dao.hibernate;

import br.tcc.webapp.dao.ProjectDao;
import br.tcc.webapp.model.Project;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: luiza
 * Date: 15/07/13
 * Time: 17:43
 * To change this template use File | Settings | File Templates.
 */

@Repository("projectDao")
public class ProjectDaoHibernate extends GenericDaoHibernate<Project, Long> implements ProjectDao {
// --------------------------- CONSTRUCTORS ---------------------------

    public ProjectDaoHibernate() {
        super(Project.class);
    }

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface ProjectDao ---------------------

    @Override
    public List<Project> getProjects() {
        Query qry = getSession().createQuery("from Project p order by upper(p.description)");
        return qry.list();
    }

    @Override
    public void removeProject(Long projectId) {
        remove(projectId);
        getSession().flush();
    }

    @Override
    public Project saveProject(Project project) {
        getSession().saveOrUpdate(project);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getSession().flush();
        return project;
    }
}
