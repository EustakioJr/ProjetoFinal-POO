package Controle;

import Modelo.Consulta;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ControllerListaConsulta {

    Boolean resultado;

    @FXML
    private Button botaoHome;

    @FXML
    private Button botaoLogout;

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
    private TableView<Consulta> tabela;

    @FXML
    private TableColumn<Consulta, Date> dataTabela;

    @FXML
    private TableColumn<Consulta, String> animalTabela;

    @FXML
    private TableColumn<Consulta, String> vetTabela;

    @FXML
    private TableColumn<Consulta, Boolean> atendidoTabela;

    @FXML
    private ComboBox<?> campoAtendido;

    @FXML
    private DatePicker campoData;

    @FXML
    private TextField campoCrmv;

    @FXML
    private Label labelAviso;

    private List<Consulta> listConsulta;
    private ObservableList<Consulta> observableListConsulta;

    public void initialize(){
        carregarTabelaConsultas();


        tabela.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectItemConsulta(newValue));
    }

    public void carregarTabelaConsultas(){
        EntityManager em = Persistence.createEntityManagerFactory("ProjetoPoo").createEntityManager();
        em.getTransaction().begin();
        dataTabela.setCellValueFactory(new PropertyValueFactory<>("data"));
        atendidoTabela.setCellValueFactory(new PropertyValueFactory<>("foiAtendido"));

        animalTabela.setCellValueFactory( param-> new SimpleStringProperty(param.getValue().getAnimal().getId().toString()));

        vetTabela.setCellValueFactory( param-> new SimpleStringProperty(param.getValue().getVeterinario().getCrmv()));

        listConsulta = new ArrayList<>(em.createQuery("SELECT c FROM Consulta c",Consulta.class).getResultList());

        observableListConsulta = FXCollections.observableArrayList(listConsulta);
        tabela.setItems(observableListConsulta);
        em.close();
    }

    public void selectItemConsulta(Consulta consulta){
        if (consulta != null){
            dataConsulta.setText(String.valueOf(consulta.getData()));
            animalConsulta.setText(String.valueOf(consulta.getAnimal().getId()));
            vetConsulta.setText(String.valueOf(consulta.getVeterinario().getCrmv()));
            motivoConsulta.setText(consulta.getMotivo());
            checkConsulta.setSelected(consulta.getFoiAtendido());
        }else{
            dataConsulta.setText("");
            animalConsulta.setText("");
            vetConsulta.setText("");
            motivoConsulta.setText("");
            checkConsulta.setSelected(false);
        }
    }

    @FXML
    void atualiza(ActionEvent event) {
        resultado = UsuarioLogado.getInstance().isEhAdm();
        if (resultado){

        }else{
            labelAviso.setText("PARA ACESSAR ESSA FUNÇÃO DEVE SER ADMINISTRADOR!");
        }
    }

    @FXML
    void deleta(ActionEvent event) {
        resultado = UsuarioLogado.getInstance().isEhAdm();
        if (resultado){

        }else{
            labelAviso.setText("PARA ACESSAR ESSA FUNÇÃO DEVE SER ADMINISTRADOR!");
        }
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
        UsuarioLogado.getInstance().setEhAdm(false);
    }

}
