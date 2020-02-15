package Modelo;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Temporal(TemporalType.DATE)
    private Date data;

    @Column
    private String motivo;

    @ManyToOne(fetch = FetchType.EAGER)
    private Animal animal;

    @ManyToOne(fetch = FetchType.EAGER)
    private Veterinario veterinario;

    @Column
    private Boolean foiAtendido;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public Boolean getFoiAtendido() {
        return foiAtendido;
    }

    public void setFoiAtendido(Boolean foiAtendido) {
        this.foiAtendido = foiAtendido;
    }
}
