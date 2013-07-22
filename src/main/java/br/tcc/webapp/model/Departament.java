package br.tcc.webapp.model;

import org.appfuse.model.BaseObject;
import org.hibernate.annotations.Fetch;
import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Gleison
 * Date: 18/07/13
 * Time: 23:04
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Departament extends BaseObject {

    private Long id;
    private String name;
    private List<Activity> activities;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Departament departament = (Departament) o;

        if (!id.equals(departament.id)) return false;
        if (!name.equals(departament.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Departament{" +
                "id=" + id +
                ", Name='" + name + '\'' +
                ", activities=" + activities +
                '}';
    }
}
