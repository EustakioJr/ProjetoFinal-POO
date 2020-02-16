package Controle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
        UsuarioLogado.getInstance().setEhAdm(true);
    }

}
