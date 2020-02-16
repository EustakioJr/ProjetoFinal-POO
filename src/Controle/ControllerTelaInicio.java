package Controle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ControllerTelaInicio {

    @FXML
    private Button botaoLogin;

    @FXML
    private PasswordField senha;

    @FXML
    private TextField usuario;

    @FXML
    void login(ActionEvent event) {
        Visao.App.trocaTela("home");
    }

}
