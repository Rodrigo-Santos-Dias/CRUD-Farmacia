package com.Pharma.PharmaDelivery.model;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.util.*;

@Getter
@Setter
@Table(name = "tb_user")
@Entity
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O atributo nome é obrigatório")
    @Size(max = 255, message = "O atributo nome deve conter no máximo 255 caracteres")
    private String name;


    private String photo;

    @NotBlank(message = "O atributo email é obrigatório")
    @Size(max = 255, message = "O atributo email deve conter no máximo 255 caracteres")
    @Column(name = "email", unique = true)
    @Schema(example = "email@email.com.br")
    @Email(message = "E-mail deve ser válido")
    private String email;



    private Role role;


    @NotBlank(message = "O atributo senha é obrigatório")
    @Size(max = 255, message = "O atributo senha deve conter no máximo 255 caracteres")
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("user")
    private List<Products> product;

    private boolean enabled;




    @Override
    public boolean equals(Object object){
        if (this == object) return true;
        if (object == null||getClass() != object.getClass()) return false;
        User user = (User)object;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode(){
        return Objects.hash(email);
    }

    public User (String name,String email,String password, boolean enabled,Role role){
        this.name = name;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
    }

    public User(){

    }

}
