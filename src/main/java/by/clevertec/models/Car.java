package by.clevertec.models;

import by.clevertec.enums.car.CarBrand;
import by.clevertec.enums.car.CarBrandConverter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cars")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;

    @Convert(converter = CarBrandConverter.class)
    @Column(name = "brand_car")
    private CarBrand brandCar;
    private LocalDate yearOfProduction;
    private String price;

    @ManyToOne
    @JoinColumn(name = "car_showroom_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private CarShowroom showroom;

    @OneToMany(mappedBy = "cars", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<Review> review = new ArrayList<>();

    @ManyToMany(mappedBy = "cars", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @Builder.Default
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<Client> clientas = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "categori_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Category categoryes;
}
