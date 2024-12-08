package by.clevertec.models;

import by.clevertec.enums.category.CarCategory;
import by.clevertec.enums.category.CarCategoryConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categoryes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = CarCategoryConverter.class)
    @Column(name = "name_category_car")
    private CarCategory carCategory;

    @OneToMany(mappedBy = "categoryes", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Car> cars = new ArrayList<>();
}
