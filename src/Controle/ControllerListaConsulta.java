package Controle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ControllerListaConsulta {

    @FXML
    private Menu menuHome;

    @FXML
    private Menu botaoLogout;

    @FXML
    private TextField campoNome;

    @FXML
    private Button botaoFiltrar;

    @FXML
    private TextField motivoConsulta;

    @FXML
    private Button botaoAtualiza;

    @FXML
    private Button botaoDeletar;

    @FXML
    private TextField vetConsulta;

    @FXML
    private TextField animalConsulta;

    @FXML
    private TextField dataConsulta;

    @FXML
    private CheckBox checkConsulta;

    @FXML
    private TableView<?> tabela;

    @FXML
    private TableColumn<?, ?> nomeTabela;

    @FXML
    private TableColumn<?, ?> animalTabela;

    @FXML
    private TableColumn<?, ?> vetTabela;

    @FXML
    private TableColumn<?, ?> atendidoTabela;

    @FXML
    private TextField campoNome1;

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
        Visao.App.trocaTela("home");
    }

    @FXML
    void logout(ActionEvent event) {
        Visao.App.trocaTela("inicio");
    }

}
