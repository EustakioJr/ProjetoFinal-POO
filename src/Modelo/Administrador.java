package Modelo;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Objects;

@Table
public class Administrador {

    @Column
    private String login;

    @Column
    private String senha;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Administrador that = (Administrador) o;
        return senha.equals(that.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(senha);
    }
}