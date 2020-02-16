package Controle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;



public class ControllerTelaHome {

    @FXML
    private Button botaoCadastrar;

    @FXML
    private Menu menuHome;

    @FXML
    private Menu botaoLogout;

    @FXML
    private Button botaoFuncionarios;

    @FXML
    private Button botaoClientes;

    @FXML
    private Button botaoAnimais;

    @FXML
    private Button botaoConsultas;

    @FXML
    void irAnimal(ActionEvent event) {

    }

    @FXML
    void irCadastro(ActionEvent event) {

    }

    @FXML
    void irCliente(ActionEvent event) {

    }

    @FXML
    void irConsulta(ActionEvent event) {

    }

    @FXML
    void irFuncionario(ActionEvent event) {

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