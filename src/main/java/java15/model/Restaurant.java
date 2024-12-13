package java15.model;

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
    RestType restType;
    int numberOfEmployees;
    String service;

    @OneToMany(mappedBy = "restaurant")
    List<Employee> restaurant;

    @OneToMany(mappedBy = "restaurant")
    List<MenuItem> menu;
}
