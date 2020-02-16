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

    }

    @FXML
    void irCadastroCliente(ActionEvent event) {

    }

    @FXML
    void irCadastroConsulta(ActionEvent event) {

    }

    @FXML
    void irCadastroRecep(ActionEvent event) {
        System.out.println(UsuarioLogado.getInstance().isEhAdm());
    }

    @FXML
    void irCadastroVet(ActionEvent event) {
        System.out.println(UsuarioLogado.getInstance().isEhAdm());
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
