package Controle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class ControllerCadastroAnimal {

    @FXML
    private Menu menuHome;

    @FXML
    private Menu botaoLogout;

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoRaca;

    @FXML
    private TextField campoCor;

    @FXML
    private TextField campoPeso;

    @FXML
    private Button botaoCadastrar;

    @FXML
    private PasswordField campoIdade;

    @FXML
    private PasswordField campoDono;

    @FXML
    void cadastrar(ActionEvent event) {

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
