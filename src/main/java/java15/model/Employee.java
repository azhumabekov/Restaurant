package java15.model;

import jakarta.persistence.*;
import java15.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String firstName;
    String lastName;
    LocalDate dateOfBirth;
    String email;
    String password;
    String phoneNumber;
    @Enumerated(EnumType.STRING)
    Role role;

    int experience;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    List<Cheque> cheques;

    @ManyToOne
    @JoinColumn(name = "resraurant_id")
    Restaurant restaurant;
}
