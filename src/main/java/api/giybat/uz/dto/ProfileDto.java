package api.giybat.uz.dto;

import api.giybat.uz.enums.UserRoles;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ProfileDto {
    private String name;
    private String username;
    private List<UserRoles> roleList;
    private String jwt;
}
