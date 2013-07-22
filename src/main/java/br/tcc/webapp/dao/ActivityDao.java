package br.tcc.webapp.dao;

import br.tcc.webapp.model.Activity;
import org.appfuse.dao.GenericDao;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Gleison
 * Date: 17/07/13
 * Time: 22:01
 * To change this template use File | Settings | File Templates.
 */
public interface ActivityDao extends GenericDao<Activity, Long> {
// -------------------------- OTHER METHODS --------------------------

    List<Activity> getActivities();

    void removeActivity(Long activityId);

    Activity saveActivity(Activity activity);
}
