package Controle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ControllerTelaCadastro {

    Boolean resultado;

    @FXML
    private Button botaoVet;

    @FXML
    private Button botaoHome;

    @FXML
    private Button botaoLogout;

    @FXML
    private Button botaoRecep;

    @FXML
    private Button botaoClientes;

    @FXML
    private Button botaoAnimais;

    @FXML
    private Button botaoConsultas;

    @FXML
    private Label labelAviso;

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
        resultado = UsuarioLogado.getInstance().isEhAdm();
        if (resultado){
            Visao.App.trocaTela("cadastroRecep");
        }else{
            labelAviso.setText("PARA ACESSAR ESSA FUNÇÃO DEVE SER ADMINISTRADOR!");
        }
    }

    @FXML
    void irCadastroVet(ActionEvent event) {
        resultado = UsuarioLogado.getInstance().isEhAdm();
        if (resultado){
        Visao.App.trocaTela("cadastroVet");
        }else{
            labelAviso.setText("PARA ACESSAR ESSA FUNÇÃO DEVE SER ADMINISTRADOR!");
        }
    }

    @FXML
    void irHome(ActionEvent event) {
        labelAviso.setText("");
        Visao.App.trocaTela("home");
    }

    @FXML
    void logout(ActionEvent event) {
        labelAviso.setText("");
        Visao.App.trocaTela("inicio");
        UsuarioLogado.getInstance().setEhAdm(false);
    }


}
