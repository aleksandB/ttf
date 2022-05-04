package io.aleksb.springjwt.ttfmain.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "teams",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        })
@Data
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    @NotBlank
    @Size(max = 50)
    private String name;

}
