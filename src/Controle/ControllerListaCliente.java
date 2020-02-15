package Controle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ControllerListaCliente {

    @FXML
    private Menu menuHome;

    @FXML
    private Menu botaoLogout;

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoCPF;

    @FXML
    private Button botaoFiltrar;

    @FXML
    private TextField cpfCliente;

    @FXML
    private Button botaoAtualiza;

    @FXML
    private Button botaoDeletar;

    @FXML
    private TextField telefoneCliente;

    @FXML
    private TextField enderecoCliente;

    @FXML
    private TextField nomeCliente;

    @FXML
    private TableView<?> tabela;

    @FXML
    private TableColumn<?, ?> nomeTabela;

    @FXML
    private TableColumn<?, ?> cpfTabela;

    @FXML
    void atualiza(ActionEvent event) {

    }

    @FXML
    void deleta(ActionEvent event) {

    }

    @FXML
    void filtrar(ActionEvent event) {

    }

    @FXML
    void irHome(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) {

    }

}
