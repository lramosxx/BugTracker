package br.tcc.webapp.dao.hibernate;

import br.tcc.webapp.dao.ActivityDao;
import br.tcc.webapp.model.Activity;
import br.tcc.webapp.model.Departament;
import br.tcc.webapp.service.DepartamentManager;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Gleison
 * Date: 17/07/13
 * Time: 22:30
 * To change this template use File | Settings | File Templates.
 */

@Repository("activityDao")
public class ActivityDaoHibernate extends GenericDaoHibernate<Activity, Long> implements ActivityDao {
    @Autowired
    private DepartamentManager deptoManager;

// --------------------------- CONSTRUCTORS ---------------------------

    public ActivityDaoHibernate() {
        super(Activity.class);
    }

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface ActivityDao ---------------------

    @Override
    public List<Activity> getActivities() {
        Query qry = getSession().createQuery("from Activity a order by upper(a.name)");
        return qry.list();
    }
    @Override
    public List<Activity> getActivitiesByDepartament(Long idDepartament){
        Departament depto = deptoManager.getDepartament(idDepartament);

        if (depto != null && depto.getActivities() != null)
            return depto.getActivities();

        return null;
    }

    @Override
    public void removeActivity(Long activityId) {
        remove(activityId);
        getSession().flush();
    }

    @Override
    public Activity saveActivity(Activity activity) {
        getSession().saveOrUpdate(activity);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getSession().flush();
        return activity;
    }
}
