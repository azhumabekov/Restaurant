package java15.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "menu_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String imageUrl;
    int price;
    String description;
    boolean isVegetarian;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    Restaurant restaurant;

    @ManyToMany
    @JoinTable(
            name = "menu_item_cheques",
            joinColumns = @JoinColumn(name = "menu_item_id"),
            inverseJoinColumns = @JoinColumn(name = "cheque_id")
    )
    List<Cheque> cheques;

    @OneToOne
    @JoinColumn(name = "stop_list_id")
    StopList stopList;

    @OneToMany(mappedBy = "menuItem")
    List<SubCategory> subCategories;


}
