package Controle;

import Modelo.Login;
import Modelo.Recepcionista;
import Modelo.Veterinario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class ControllerCadastroRecepcionista {

    @FXML
    private Menu menuHome;

    @FXML
    private Menu botaoLogout;

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoCPF;

    @FXML
    private TextField campoTelefone;

    @FXML
    private TextField campoEndereco;

    @FXML
    private Button botaoCadastrar;

    @FXML
    private PasswordField campoSenha;

    @FXML
    void cadastrar(ActionEvent event) {
        EntityManager em = Persistence.createEntityManagerFactory("ProjetoPoo").createEntityManager();
        em.getTransaction().begin();

        Recepcionista novo = new Recepcionista();
        novo.setCpf(campoCPF.getText());
        novo.setEndereco(campoEndereco.getText());
        novo.setNome(campoNome.getText());
        novo.setTelefone(campoTelefone.getText());

        Login login = new Login();
        login.setUsuario(campoCPF.getText());
        login.setSenha(campoSenha.getText());
        login.setTipo("Recepcionista");

        em.persist(novo);
        em.persist(login);
        em.getTransaction().commit();

        em.close();
    }

    @FXML
    void irHome(ActionEvent event) {
        Visao.App.trocaTela("home");
    }

    @FXML
    void logout(ActionEvent event) {
        Visao.App.trocaTela("inicio");
    }

}
