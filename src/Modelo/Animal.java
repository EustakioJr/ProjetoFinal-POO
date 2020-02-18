package Modelo;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name="Animal.BuscaId", query = "SELECT a FROM Animal a WHERE a.id = :id")
})

@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column
    private String nome;

    @Column
    private String raca;

    @Column
    private String especie;

    @Column
    private String idade;

    @Column
    private String peso;

    @ManyToOne(fetch = FetchType.EAGER)
    private Cliente dono;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public Cliente getDono() {
        return dono;
    }

    public void setDono(Cliente dono) {
        this.dono = dono;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", raca='" + raca + '\'' +
                ", especie='" + especie + '\'' +
                ", idade='" + idade + '\'' +
                ", peso='" + peso + '\'' +
                ", dono=" + dono +
                '}';
    }
}
