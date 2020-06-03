package de.rafiei.persianevents.model;

import java.sql.Date;
import javax.persistence.*;

@Entity
@Table(
        name = "tblevent_type"
)
public class EventType {
    @Id
    @Column(
            name = "id"
    )
    private int id ;

    @Column(
            name = "name"
    )
    private String name;

    @Column(
            name = "status"
    )
    private int status ;

    public EventType(int id, String name, int status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
