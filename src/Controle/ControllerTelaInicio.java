package Controle;

import Dao.DaoLogin;
import Modelo.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class ControllerTelaInicio {
    String resultado;

    @FXML
    private Button botaoLogin;

    @FXML
    private PasswordField senha;

    @FXML
    private TextField usuario;

    @FXML
    private Label labelAviso;

    @FXML
    void login(ActionEvent event) {
        DaoLogin dao = new DaoLogin();
        resultado = dao.buscaLogin(usuario.getText(), senha.getText());
        if (!resultado.equals("null")){
            if (resultado.equals("Adm")){
                UsuarioLogado.getInstance().setEhAdm(true);
            }
            Visao.App.trocaTela("home");
            System.out.println(UsuarioLogado.getInstance().isEhAdm());
        }else{
            labelAviso.setText("Usuario ou Senha Invalidos!");
        }
    }

    public void limpaCampo(){
        usuario.setText("");
        senha.setText("");
    }

}
