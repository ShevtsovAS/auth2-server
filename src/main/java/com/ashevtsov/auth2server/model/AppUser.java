package com.ashevtsov.auth2server.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Data
@Entity
@Table(name = "\"user\"")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUser implements UserDetails {

    private static final long serialVersionUID = -7260240953152868119L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "enabled")
    private boolean enabled;
    @Column(name = "accountnonexpired")
    private boolean accountNonExpired;
    @Column(name = "credentialsnonexpired")
    private boolean credentialsNonExpired;
    @Column(name = "accountnonlocked")
    private boolean accountNonLocked;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_user",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Stream<String> roleStream = roles.stream().map(Role::getName);
        Stream<String> permissionStream = roles.stream().flatMap(role -> role.getPermissions().stream()).map(Permission::getName);
        return Stream
                .concat(roleStream, permissionStream)
                .distinct()
                .map(SimpleGrantedAuthority::new)
                .collect(toList());
    }
}

