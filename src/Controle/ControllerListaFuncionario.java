package Controle;

import Dao.DaoRecepcionista;
import Modelo.Recepcionista;
import Modelo.Veterinario;
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
import java.util.List;

public class ControllerListaFuncionario {

    Boolean resultado;

    Recepcionista recepSelecionado;

    DaoRecepcionista daoRecep;

    @FXML
    private Button botaoHome;

    @FXML
    private Button botaoLogout;

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoCPF;

    @FXML
    private Button botaoFiltrar;

    @FXML
    private TextField cpfFunc;

    @FXML
    private Button botaoAtualiza;

    @FXML
    private Button botaoDeletar;

    @FXML
    private TextField telefoneFunc;

    @FXML
    private TextField enderecoFunc;

    @FXML
    private TextField nomeFunc;

    @FXML
    private TableView<Recepcionista> tabela;

    @FXML
    private TableColumn<Recepcionista, String> nomeTabela;

    @FXML
    private TableColumn<Recepcionista, String> cpfTabela;

    @FXML
    private Label labelAviso;

    @FXML
    void atualiza(ActionEvent event) {
        resultado = UsuarioLogado.getInstance().isEhAdm();
        if (resultado){
            Recepcionista recep = new Recepcionista();
            recep.setCpf(recepSelecionado.getCpf());
            recep.setEndereco(enderecoFunc.getText());
            recep.setNome(nomeFunc.getText());
            recep.setTelefone(telefoneFunc.getText());
            daoRecep.atualizar(recep);
            limpaCampo();
        }else{
            labelAviso.setText("PARA ACESSAR ESSA FUNÇÃO DEVE SER ADMINISTRADOR!");
        }
        carregarTabelaCompleta();
    }

    @FXML
    void deleta(ActionEvent event) {
        resultado = UsuarioLogado.getInstance().isEhAdm();
        if (resultado){
            daoRecep.deletar(recepSelecionado);
        }else{
            labelAviso.setText("PARA ACESSAR ESSA FUNÇÃO DEVE SER ADMINISTRADOR!");
        }
        carregarTabelaCompleta();
    }

    @FXML
    void filtrar(ActionEvent event) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT r FROM Recepcionista r");
        if (!campoNome.getText().isEmpty() || !campoCPF.getText().isEmpty()){
            sb.append(" WHERE");

            if (!campoNome.getText().isEmpty()) sb.append(" r.nome = '"+ campoNome.getText()+"'");
            if (!campoCPF.getText().isEmpty()) sb.append(
                    ((!campoNome.getText().isEmpty())? " AND": "")
                            +" r.cpf = '"
                            + campoCPF.getText()
                            +"'"
            );
        }
        carregarTabelaRecepcionista(daoRecep.buscaFiltragem(sb.toString()));
        limpaCampo();
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

    private List<Recepcionista> listRecep;
    private ObservableList<Recepcionista> observableListRecep;

    public void initialize(){
        daoRecep = new DaoRecepcionista();

        carregarTabelaCompleta();

        tabela.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectItemRecep(newValue));
    }

    void carregarTabelaCompleta() {
        EntityManager em = Persistence.createEntityManagerFactory("ProjetoPoo").createEntityManager();
        em.getTransaction().begin();
        carregarTabelaRecepcionista(new ArrayList<Recepcionista>(em.createQuery("SELECT r FROM Recepcionista r", Recepcionista.class).getResultList()));
        em.close();
    }

    public void carregarTabelaRecepcionista(List<Recepcionista> lista){

        nomeTabela.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cpfTabela.setCellValueFactory(new PropertyValueFactory<>("cpf"));


        listRecep = lista;

        observableListRecep = FXCollections.observableArrayList((listRecep));
        tabela.setItems(observableListRecep);
    }

    public void selectItemRecep(Recepcionista Recep) {
        if (Recep != null) {
            recepSelecionado = Recep;
            nomeFunc.setText(Recep.getNome());
            enderecoFunc.setText(Recep.getEndereco());
            telefoneFunc.setText(Recep.getTelefone());
            cpfFunc.setText(Recep.getCpf());
        } else {
            limpaCampo();
        }
    }
    public void limpaCampo(){
        nomeFunc.setText("");
        enderecoFunc.setText("");
        telefoneFunc.setText("");
        cpfFunc.setText("");
    }
}
