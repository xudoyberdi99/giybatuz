package api.giybat.uz.entity;

import api.giybat.uz.enums.GeneralStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Table(name="profile")
@Entity
@Getter
@Setter
public class ProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private GeneralStatus genstatus;
    @Column(name="visible")
    private Boolean visible=Boolean.TRUE;
    @Column(name="created_date")
    private LocalDateTime createdDate;


//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
    //    public GeneralStatus getGenstatus() {
    //        return genstatus;
    //    }
    //
//    public void setGenstatus(GeneralStatus genstatus) {
//        this.genstatus = genstatus;
//    }
//
//    public Boolean getVisible() {
//        return visible;
//    }
//
//    public void setVisible(Boolean visible) {
//        this.visible = visible;
//    }
//
//    public LocalDateTime getCreatedDate() {
//        return createdDate;
//    }
//
//    public void setCreatedDate(LocalDateTime createdDate) {
//        this.createdDate = createdDate;
//    }
}
