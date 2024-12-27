package java15.models;

import jakarta.persistence.*;
import java15.enums.RestType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String location;
    @Enumerated(EnumType.STRING)
    RestType restType;


    int numberOfEmployees;

    String service;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    List<Employee> restaurant;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    List<MenuItem> menu;
}
