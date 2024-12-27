package java15.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "cheques")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cheque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long priceAverage;
    LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    Employee employee;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "cheque_menu_items",
            joinColumns = @JoinColumn(name = "cheque_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_item_id")
    )
    List<MenuItem> menuItem;

    @PrePersist
    public void PrePersist() {
        createdAt = LocalDate.now();
    }

    @PreUpdate
    void PreUpdate() {
        this.createdAt = LocalDate.now();
    }

}
