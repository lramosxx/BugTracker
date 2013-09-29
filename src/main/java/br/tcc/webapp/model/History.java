package br.tcc.webapp.model;

import org.appfuse.model.User;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Gleison
 * Date: 13/08/13
 * Time: 00:14
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class History {

    private Long id;
    private String comment;
    private User author;
    private Date date;
    private boolean read;
    private Long idIssue;

    public History() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @OneToOne
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setRead(boolean read){
        this.read = read;
    }

    public boolean getRead(){
        return this.read;
    }

    public void setIdIssue(Long idIssue){
        this.idIssue = idIssue;
    }

    public Long getIdIssue(){
        return this.idIssue;
    }

    // ------------------------ CANONICAL METHODS ------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        History history = (History) o;

        if (!id.equals(history.id)) return false;
        if (!comment.equals(history.comment)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + comment.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", author=" + author +
                ", date=" + date +
                ", read=" + read +
                ", idIssue=" + idIssue +
                '}';
    }
}
