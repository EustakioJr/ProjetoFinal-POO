package Controle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;

public class ControllerTelaCadastro {

    @FXML
    private Button botaoVet;

    @FXML
    private Menu menuHome;

    @FXML
    private Menu botaoLogout;

    @FXML
    private Button botaoRecep;

    @FXML
    private Button botaoClientes;

    @FXML
    private Button botaoAnimais;

    @FXML
    private Button botaoConsultas;

    @FXML
    void irCadastroAnimal(ActionEvent event) {
        Visao.App.trocaTela("cadastroAnimal");
    }

    @FXML
    void irCadastroCliente(ActionEvent event) {
        Visao.App.trocaTela("cadastroCliente");
    }

    @FXML
    void irCadastroConsulta(ActionEvent event) {Visao.App.trocaTela("cadastroConsulta");    }

    @FXML
    void irCadastroRecep(ActionEvent event) {
        Visao.App.trocaTela("cadastroRecep");
    }

    @FXML
    void irCadastroVet(ActionEvent event) { Visao.App.trocaTela("cadastroVet");    }

    @FXML
    void irHome(ActionEvent event) {
        Visao.App.trocaTela("home");
    }

    @FXML
    void logout(ActionEvent event) {
        Visao.App.trocaTela("inicio");
    }

}
