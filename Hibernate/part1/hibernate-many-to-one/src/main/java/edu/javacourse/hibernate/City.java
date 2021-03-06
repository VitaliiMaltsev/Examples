package edu.javacourse.hibernate;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

/**
 * @author Demo
 */
@Entity
@Table(name = "jc_city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Integer cityId;
    @Column(name = "city_name")
    private String cityName;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
//    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "region_id")
    private Region region;

    public City() {
    }

    public City(Integer cityId, String name, Region region) {
        this.cityId = cityId;
        this.cityName = name;
        this.region = region;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "City{" + "cityId=" + cityId + ", name=" + cityName + '}';
    }
}
