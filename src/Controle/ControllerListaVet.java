package Controle;

import Dao.DaoVeterinario;
import Modelo.Veterinario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class ControllerListaVet {

    Boolean resultado;

    Veterinario vetSelecionado;

    DaoVeterinario daoVet;

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
    private TextField crmvFunc;

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
    private TableView<Veterinario> tabela;

    @FXML
    private TableColumn<Veterinario, String> nomeTabela;

    @FXML
    private TableColumn<Veterinario, String> cpfTabela;

    @FXML
    private TableColumn<Veterinario, String> crmvTabela;

    @FXML
    private Label labelAviso;


    @FXML
    void atualiza(ActionEvent event) {
        resultado = UsuarioLogado.getInstance().isEhAdm();
        if (resultado){
            Veterinario v = new Veterinario();
            v.setTelefone(telefoneFunc.getText());
            v.setEndereco(enderecoFunc.getText());
            v.setNome(nomeFunc.getText());
            v.setCrmv(vetSelecionado.getCrmv());
            v.setCpf(vetSelecionado.getCpf());
            daoVet.atualizar(v);
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
            daoVet.deletar(vetSelecionado);
        }else{
            labelAviso.setText("PARA ACESSAR ESSA FUNÇÃO DEVE SER ADMINISTRADOR!");
        }
        carregarTabelaCompleta();
    }

    @FXML
    void filtrar(ActionEvent event) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT v FROM Veterinario v");
        if (!campoNome.getText().isEmpty() || !campoCPF.getText().isEmpty()){
            sb.append(" WHERE");

            if (!campoNome.getText().isEmpty()) sb.append(" v.nome = '"+ campoNome.getText()+"'");
            if (!campoCPF.getText().isEmpty()) sb.append(
                    ((!campoNome.getText().isEmpty())? " AND": "")
                            +" v.cpf = '"
                            + campoCPF.getText()
                            +"'"
            );
        }

        carregarTabelaVet(daoVet.buscaFiltragem(sb.toString()));
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

    public void initialize(){
        daoVet = new DaoVeterinario();

        carregarTabelaCompleta();

        tabela.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectItemVet(newValue));
    }

    private List<Veterinario> listVet;
    private ObservableList<Veterinario> observableListVet;

    void carregarTabelaCompleta() {
        EntityManager em = Persistence.createEntityManagerFactory("ProjetoPoo").createEntityManager();
        em.getTransaction().begin();
        carregarTabelaVet(new ArrayList<Veterinario>(em.createQuery("SELECT v FROM Veterinario v", Veterinario.class).getResultList()));
        em.close();
    }

    public void carregarTabelaVet(List<Veterinario> listaVet){
        nomeTabela.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cpfTabela.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        crmvTabela.setCellValueFactory(new PropertyValueFactory<>("crmv"));

        listVet = listaVet;

        observableListVet = FXCollections.observableArrayList((listVet));
        tabela.setItems(observableListVet);
    }

    public void selectItemVet(Veterinario vet){
        if (vet != null){
            vetSelecionado = vet;
            nomeFunc.setText(vet.getNome());
            enderecoFunc.setText(vet.getEndereco());
            telefoneFunc.setText(vet.getTelefone());
            crmvFunc.setText(vet.getCrmv());
            cpfFunc.setText(vet.getCpf());
        }else{
            nomeFunc.setText("");
            enderecoFunc.setText("");
            telefoneFunc.setText("");
            crmvFunc.setText("");
            cpfFunc.setText("");
        }
    }
    public void limpaCampo(){
        nomeFunc.setText("");
        enderecoFunc.setText("");
        telefoneFunc.setText("");
        crmvFunc.setText("");
        cpfFunc.setText("");
    }
}
