package io.aleksb.springjwt.ttfmain.models;

import io.aleksb.springjwt.auth.models.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "leagues",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        })
@Data
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    @NotBlank
    @Size(max = 50)
    private String name;

}
