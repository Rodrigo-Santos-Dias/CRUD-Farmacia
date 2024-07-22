package com.Pharma.PharmaDelivery.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "tb_categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "The description attribute is mandatory")
    @Size(min = 05, max = 255, message = "The description attribute must have between 05 and 255 characters")
    private String description;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "category",cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("category")
    private List<Products> products;
}