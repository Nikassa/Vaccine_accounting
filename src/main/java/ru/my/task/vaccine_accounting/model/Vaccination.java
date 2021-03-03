package ru.my.task.vaccine_accounting.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "vaccination")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Vaccination {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "vaccinesIdSeq", sequenceName = "vaccines_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vaccinesIdSeq")
    private Integer id;

    @ApiModelProperty("Препарат")
    @Pattern(regexp = "Эджерикс|Вианвак|АКДС|БЦЖ")
    @Column(name = "vaccine")
    private String vaccine;

    @ApiModelProperty("Согласие на прививку")
    @Pattern(regexp = "Y|N")
    @Column(name = "permission")
    private String permission;

    @ApiModelProperty("Дата проведения")
    @Column(name = "vaccination_date")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date vaccinationDate;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "vaccinations")
    private List<Patient> patients;

    public Vaccination() {
    }

    public Vaccination(String vaccine, String permission, Date vaccinationDate, List<Patient> patients) {
        this.vaccine = vaccine;
        this.permission = permission;
        this.vaccinationDate = vaccinationDate;
        this.patients = patients;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Date getVaccinationDate() {
        return vaccinationDate;
    }

    public void setVaccinationDate(Date vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    @Override
    public String toString() {
        return "Vaccination{" +
                "id=" + id +
                ", vaccine='" + vaccine + '\'' +
                ", permission='" + permission + '\'' +
                ", vaccinationDate=" + vaccinationDate +
                '}';
    }

    public String toStringWithoutId() {
        return "Vaccination{" +
                "vaccine='" + vaccine + '\'' +
                ", permission='" + permission + '\'' +
                ", vaccinationDate=" + vaccinationDate +
                '}';
    }
}
