package br.com.intermentes.intermentesapi.model;

import br.com.intermentes.intermentesapi.model.enums.Ethnicity;
import br.com.intermentes.intermentesapi.model.enums.Gender;
import br.com.intermentes.intermentesapi.model.enums.Roles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserModel implements UserDetails {
    @Column(name = "id", nullable = false, unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email",nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "birthDate")
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "ethnicity")
    private Ethnicity ethnicity;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roleses",
            joinColumns = @JoinColumn(name = "userModel_id"),
            inverseJoinColumns = @JoinColumn(name = "roleses_name"))
    private Set<Roles> roleses = new LinkedHashSet<>();

// TODO: Implementar Imagem - Foto de Perfil

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "cpf", nullable = false, length = 11)
    private String cpf;

    @OneToOne(mappedBy = "user")
    private ProfessionalModel professional;





    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        UserModel userModel = (UserModel) o;
        return getId() != null && Objects.equals(getId(), userModel.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roleses;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
