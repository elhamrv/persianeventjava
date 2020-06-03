package de.rafiei.persianevents.model;

import java.sql.Date;
import javax.persistence.*;

@Entity
@Table(
        name = "tblevents"
)
public class Events {
    @Id
    @Column(
            name = "id"
    )
    private int id ;

    @Column(
            name = "eventtypeid"
    )
    private int eventtypeid ;

    @Column(
            name = "title"
    )
    private String title;


    @Column(
            name = "event_date"
    )
    private Date event_date;

    @Column(
            name = "description"
    )
    private String description;

    @Column(
            name = "imgpath"
    )
    private String imgpath;

    @Column(
            name = "cityid"
    )
    private int cityid ;

    @Column(
            name = "address"
    )
    private String address;

    @Column(
            name = "status"
    )
    private int status ;

    public Events() {
    }

    public Events(int id, int eventtypeid, String title, Date event_date, String description, String imgpath , int cityid, String address, int status) {
        this.id = id;
        this.eventtypeid = eventtypeid;
        this.title = title;
        this.event_date = event_date;
        this.description = description;
        this.imgpath = imgpath;
        this.cityid = cityid;
        this.address = address;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventtypeid() {
        return eventtypeid;
    }

    public void setEventtypeid(int eventtypeid) {
        this.eventtypeid = eventtypeid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getEvent_date() {
        return event_date;
    }

    public void setEvent_date(Date event_date) {
        this.event_date = event_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public int getCityid() {
        return cityid;
    }

    public void setCityid(int cityid) {
        this.cityid = cityid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
