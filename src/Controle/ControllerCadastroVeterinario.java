package Controle;

import Dao.DaoLogin;
import Dao.DaoVeterinario;
import Modelo.Login;
import Modelo.Veterinario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class ControllerCadastroVeterinario {

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
    private TextField campoCRMV;

    @FXML
    private Button botaoCadastrar;

    @FXML
    private PasswordField campoSenha;

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

        Veterinario novo = new Veterinario();
        novo.setCpf(campoCPF.getText());
        novo.setCrmv(campoCRMV.getText());
        novo.setEndereco(campoEndereco.getText());
        novo.setNome(campoNome.getText());
        novo.setTelefone(campoTelefone.getText());

        Login login = new Login();
        login.setUsuario(campoCPF.getText());
        login.setSenha(campoSenha.getText());
        login.setTipo("Veterinario");

        DaoVeterinario dao = new DaoVeterinario();
        resultado = dao.salvar(novo);


        if (resultado){
            DaoLogin daoL = new DaoLogin();
            daoL.salvar(login);
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
        campoCRMV.setText("");
        campoEndereco.setText("");
        campoNome.setText("");
        campoSenha.setText("");
        campoTelefone.setText("");
    }
}
