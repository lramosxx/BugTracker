package br.tcc.webapp.service.impl;

import br.tcc.webapp.dao.ActivityDao;
import br.tcc.webapp.model.Activity;
import br.tcc.webapp.service.ActivityManager;
import org.appfuse.service.impl.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Gleison
 * Date: 17/07/13
 * Time: 22:54
 * To change this template use File | Settings | File Templates.
 */

@Service("activityManager")
public class ActivityManagerImpl extends GenericManagerImpl<Activity, Long> implements ActivityManager {
// ------------------------------ FIELDS ------------------------------

    ActivityDao activityDao;

// --------------------------- CONSTRUCTORS ---------------------------

    @Autowired
    public ActivityManagerImpl(ActivityDao activityDao) {
        this.dao = activityDao;
        this.activityDao = activityDao;
    }

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface ActivityManager ---------------------

    @Override
    public Activity getActivity(Long activityID) {
        return activityDao.get(activityID);
    }

    @Override
    public List<Activity> getActivities() {
        return activityDao.getAllDistinct();
    }

    @Override
    public void removeActivity(Long activityId) {
        activityDao.removeActivity(activityId);
    }

    @Override
    public Activity saveActivity(Activity activity) {
        return activityDao.saveActivity(activity);
    }

    @Override
    public List<Activity> search(String searchTerm) {
        return super.search(searchTerm, Activity.class);
    }
}
