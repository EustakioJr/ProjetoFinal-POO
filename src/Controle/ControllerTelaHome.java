package Controle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControllerTelaHome {

    @FXML
    private Button botaoCadastrar;

    @FXML
    private Button botaoHome;

    @FXML
    private Button botaoLogout;

    @FXML
    private Button botaoFuncionarios;

    @FXML
    private Button botaoClientes;

    @FXML
    private Button botaoAnimais;

    @FXML
    private Button botaoConsultas;

    @FXML
    private Button botaoVet;

    @FXML
    void irAnimal(ActionEvent event) {
        Visao.App.trocaTela("listaAnimal");
    }

    @FXML
    void irCadastro(ActionEvent event) {
        Visao.App.trocaTela("cadastro");
    }

    @FXML
    void irCliente(ActionEvent event) {
        Visao.App.trocaTela("listaCliente");
    }

    @FXML
    void irConsulta(ActionEvent event) {
        Visao.App.trocaTela("listaConsulta");
    }

    @FXML
    void irFuncionario(ActionEvent event) {
        Visao.App.trocaTela("listaFunc");
    }

    @FXML
    void irHome(ActionEvent event) {
        Visao.App.trocaTela("home");
    }

    @FXML
    void irVet(ActionEvent event) {
        Visao.App.trocaTela("listaVet");
    }

    @FXML
    void logout(ActionEvent event) {
        Visao.App.trocaTela("inicio");
        UsuarioLogado.getInstance().setEhAdm(false);
    }

}
