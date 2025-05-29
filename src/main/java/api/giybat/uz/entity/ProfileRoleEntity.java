package api.giybat.uz.entity;

import api.giybat.uz.enums.UserRoles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name="profile_role")
@Entity
@Getter
@Setter
public class ProfileRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="profile_id")
    private Integer profileId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="profile_id", insertable=false, updatable=false)
    private ProfileEntity profile;

    @Enumerated(EnumType.STRING)
    @Column(name="role_name")
    private UserRoles roleName;
    
    @Column(name="created_date")
    private LocalDateTime createdDate;
}
