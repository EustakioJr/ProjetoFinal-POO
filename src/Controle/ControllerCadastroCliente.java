package Controle;

import Dao.DaoCliente;
import Modelo.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class ControllerCadastroCliente {

    @FXML
    private Button botaoHome;

    @FXML
    private Button botaoLogout;

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
    private Label labelAviso;

    @FXML
    private Button botaoVoltar;

    @FXML
    void voltar(ActionEvent event) {
        limpaCampo();
        Visao.App.trocaTela("cadastro");
    }

    @FXML
    void cadastrar(ActionEvent event) {
        Boolean resultado;
        Cliente novo = new Cliente();
        novo.setCpf(campoCPF.getText());
        novo.setEndereco(campoEndereco.getText());
        novo.setNome(campoNome.getText());
        novo.setTelefone(campoTelefone.getText());

        DaoCliente dao = new DaoCliente();
        resultado = dao.salvar(novo);

        if (resultado){
            labelAviso.setText("Cadastro realizado com sucesso");
        }else{
            labelAviso.setText("Falha ao realizar Cadastro");
        }
    }

    @FXML
    void irHome(ActionEvent event) {
        limpaCampo();
        Visao.App.trocaTela("home");
    }

    @FXML
    void logout(ActionEvent event) {
        limpaCampo();
        Visao.App.trocaTela("inicio");
        UsuarioLogado.getInstance().setEhAdm(false);
    }

    public void limpaCampo(){
        campoCPF.setText("");
        campoEndereco.setText("");
        campoNome.setText("");
        campoTelefone.setText("");
    }

}
