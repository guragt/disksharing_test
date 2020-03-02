package guragt.disksharing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Disk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToOne
    @JsonIgnore
    private User owner;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "takenitem_id", referencedColumnName = "id")
    @JsonIgnore
    private TakenItem takenItem;

    public Disk() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public TakenItem getTakenItem() {
        return takenItem;
    }

    public void setTakenItem(TakenItem takenItem) {
        this.takenItem = takenItem;
    }
}
