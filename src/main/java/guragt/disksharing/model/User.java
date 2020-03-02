package guragt.disksharing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    @JsonIgnore
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private List<Disk> userDisks;

    @OneToMany
    @JoinColumn(name = "holder_id")
    private List<TakenItem> takenItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Disk> getUserDisks() {
        return userDisks;
    }

    public void setUserDisks(List<Disk> userDisks) {
        this.userDisks = userDisks;
    }

    public List<TakenItem> getTakenItems() {
        return takenItems;
    }

    public void setTakenItems(List<TakenItem> takenItems) {
        this.takenItems = takenItems;
    }
}
