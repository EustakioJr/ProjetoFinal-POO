package Controle;

import Dao.DaoConsulta;
import Modelo.Consulta;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;
import Enum.FoiAtendido;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ControllerListaConsulta {

    Boolean resultado;
    DaoConsulta daoConsulta;
    Consulta consultaSelecionada;

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
    private ChoiceBox<FoiAtendido> campoAtendido;

    @FXML
    private DatePicker campoData;

    @FXML
    private TextField campoCrmv;

    @FXML
    private Label labelAviso;

    private List<Consulta> listConsulta;
    private ObservableList<Consulta> observableListConsulta;

    public void initialize(){
        campoAtendido.getItems().addAll(FoiAtendido.values());
        carregaTabelaCompleta();

        daoConsulta = new DaoConsulta();

        tabela.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectItemConsulta(newValue));
    }

    void carregaTabelaCompleta(){
        EntityManager em = Persistence.createEntityManagerFactory("ProjetoPoo").createEntityManager();
        em.getTransaction().begin();
        carregarTabelaConsultas(new ArrayList<>(em.createQuery("SELECT c FROM Consulta c",Consulta.class).getResultList()));
        em.close();
    }

    public void carregarTabelaConsultas(List<Consulta> listaConsultas){

        dataTabela.setCellValueFactory(new PropertyValueFactory<>("data"));
        atendidoTabela.setCellValueFactory(new PropertyValueFactory<>("foiAtendido"));

        animalTabela.setCellValueFactory( param-> new SimpleStringProperty(param.getValue().getAnimal().getId().toString()));

        vetTabela.setCellValueFactory( param-> new SimpleStringProperty(param.getValue().getVeterinario().getCrmv()));

        listConsulta = listaConsultas;

        observableListConsulta = FXCollections.observableArrayList(listConsulta);
        tabela.setItems(observableListConsulta);
    }

    public void selectItemConsulta(Consulta consulta){
        if (consulta != null){
            consultaSelecionada = consulta;
            dataConsulta.setText(String.valueOf(consulta.getData()));
            animalConsulta.setText(String.valueOf(consulta.getAnimal().getId()));
            vetConsulta.setText(String.valueOf(consulta.getVeterinario().getCrmv()));
            motivoConsulta.setText(consulta.getMotivo());
            checkConsulta.setSelected(consulta.getFoiAtendido());
        }else{
            limpaCampos();
        }
    }

    void limpaCampos(){
        dataConsulta.setText("");
        animalConsulta.setText("");
        vetConsulta.setText("");
        motivoConsulta.setText("");
        checkConsulta.setSelected(false);
    }

    @FXML
    void atualiza(ActionEvent event) {
        resultado = UsuarioLogado.getInstance().isEhAdm();
        if (resultado){
            Consulta c = new Consulta();

            c.setId(consultaSelecionada.getId());
            c.setData(consultaSelecionada.getData());
            c.setMotivo(consultaSelecionada.getMotivo());
            c.setAnimal(consultaSelecionada.getAnimal());
            c.setVeterinario(consultaSelecionada.getVeterinario());
            c.setFoiAtendido(checkConsulta.isSelected());

            daoConsulta.atualizar(c);
        }else{
            labelAviso.setText("PARA ACESSAR ESSA FUNÇÃO DEVE SER ADMINISTRADOR!");
        }
        limpaCampos();
        carregaTabelaCompleta();
    }

    @FXML
    void deleta(ActionEvent event) {
        resultado = UsuarioLogado.getInstance().isEhAdm();
        if (resultado){
            daoConsulta.deletar(consultaSelecionada);
        }else{
            labelAviso.setText("PARA ACESSAR ESSA FUNÇÃO DEVE SER ADMINISTRADOR!");
        }
        limpaCampos();
        carregaTabelaCompleta();
    }

    @FXML
    void filtrar(ActionEvent event) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT c FROM Consulta c");
        if (!campoNome.getText().isEmpty() || !campoCrmv.getText().isEmpty() || campoData.getValue() != null || campoAtendido.getValue() != null){
            sb.append(" WHERE");

            if (!campoNome.getText().isEmpty()) sb.append(" c.animal.id = '"+ campoNome.getText()+"'");
            if (!campoCrmv.getText().isEmpty()) sb.append(
                    ((!campoNome.getText().isEmpty())? " AND": "")
                            +" c.veterinario.crmv = '"
                            + campoCrmv.getText()
                            +"'"
            );
            if (campoData.getValue() != null) sb.append(
                    ((!campoNome.getText().isEmpty() || !campoCrmv.getText().isEmpty())? " AND": "")
                    +" c.data = '"
                    + java.sql.Date.valueOf(campoData.getValue())
                    +"'"
            );
            if (campoAtendido.getValue() != null) sb.append(
                    ((!campoNome.getText().isEmpty() || !campoCrmv.getText().isEmpty() || campoData.getValue() != null)? " AND": "")
                    +" c.foiAtendido = "
                    + (campoAtendido.getValue() == FoiAtendido.ATENDIDO)
            );

        }

        carregarTabelaConsultas(daoConsulta.buscaFiltragem(sb.toString()));
        limpaCampos();
    }

    @FXML
    void irHome(ActionEvent event) {
        limpaCampos();
        Visao.App.trocaTela("home");
    }

    @FXML
    void logout(ActionEvent event) {
        limpaCampos();
        Visao.App.trocaTela("inicio");
        UsuarioLogado.getInstance().setEhAdm(false);
    }

}
