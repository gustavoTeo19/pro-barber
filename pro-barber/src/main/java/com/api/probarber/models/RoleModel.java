package com.api.probarber.models;

import com.api.probarber.enums.RoleName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_ROLE")
public class RoleModel implements GrantedAuthority, Serializable {

    private static final long serialVersionUID = 1L;
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID roleId;
    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleName roleName;

    @Override
    public String getAuthority() {
        // TODO Auto-generated method stub
        return this.roleName.toString();
    }

    public UUID getRoleId() {
        return this.roleId;
    }

    public void setRoleId(UUID roleId) {
        this.roleId = roleId;
    }

    public RoleName getRoleName() {
        return this.roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

}
