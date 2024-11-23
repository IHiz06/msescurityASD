package com.codigo.ms_seguridad.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.query.sqm.FetchClauseType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder //Patron creacional para crear objetos y evitar el uso de set al guardar
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombres;
    private String apellidos;
    private String email;
    private String password;
    private String tipoDoc;
    private String numDoc;
    //Obligatorios al tener Spring Security (no hay problema si no se declara)
    private Boolean isAccountNonExpired;
    private Boolean isAccountNonLocked;
    private Boolean isCredentialsNonExpired;
    private Boolean isEnabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol",
    joinColumns = @JoinColumn(name = "id_usuario"),
    inverseJoinColumns = @JoinColumn(name = "id_rol"))
    private Set<Rol> roles = new HashSet<>();

    @Override
// Sobrescribe el método getAuthorities() de la interfaz UserDetails para proporcionar las autorizaciones del usuario.
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convierte la lista de roles en una lista de objetos GrantedAuthority.
        return roles.stream()
                // Para cada objeto rol en la lista, crea una instancia de SimpleGrantedAuthority con el nombre del rol.
                .map(rol -> new SimpleGrantedAuthority(rol.getNombreRol()))
                // Recoge todos los resultados del stream y los convierte en una lista.
                .collect(Collectors.toList());
    }


    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
        //return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
