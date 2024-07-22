package com.Pharma.PharmaDelivery.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

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

}