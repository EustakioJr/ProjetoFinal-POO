package Modelo;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cliente {
    @Id
    private String cpf;

    @Column()
    private String nome;

    @Column
    private String telefone;

    @Column
    private String endereco;

    @OneToMany
    private List<Animal> animais;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
