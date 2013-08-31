package br.tcc.webapp.service;

import br.tcc.webapp.model.Activity;
import org.appfuse.service.GenericManager;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Gleison
 * Date: 17/07/13
 * Time: 22:51
 * To change this template use File | Settings | File Templates.
 */
public interface ActivityManager extends GenericManager<Activity, Long> {
// -------------------------- OTHER METHODS --------------------------

    Activity getActivity(Long activityID);

    List<Activity> getActivities();

    List<Activity> getActivitiesByDepartament(Long idDepartament);

    void removeActivity(Long activityId);

    Activity saveActivity(Activity activity);

    List<Activity> search(String searchTerm);
}
