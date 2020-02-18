package Controle;

import Dao.DaoCliente;
import Modelo.Cliente;
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

public class ControllerListaCliente {

    Boolean resultado;
    DaoCliente daoCliente;
    Cliente clienteSelecionado;

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
    private TableView<Cliente> tabela;

    @FXML
    private TableColumn<Cliente, String> nomeTabela;

    @FXML
    private TableColumn<Cliente, String> cpfTabela;

    @FXML
    private Label labelAviso;

    @FXML
    void atualiza(ActionEvent event) {
        resultado = UsuarioLogado.getInstance().isEhAdm();
        if (resultado){
            Cliente c = new Cliente();

            c.setCpf(clienteSelecionado.getCpf());
            c.setNome(clienteSelecionado.getNome());
            c.setEndereco(enderecoCliente.getText());
            c.setTelefone(telefoneCliente.getText());
            daoCliente.atualizar(c);
        }else{
            labelAviso.setText("PARA ACESSAR ESSA FUNÇÃO DEVE SER ADMINISTRADOR!");
        }
    }

    @FXML
    void deleta(ActionEvent event) {
        resultado = UsuarioLogado.getInstance().isEhAdm();
        if (resultado){
         daoCliente.deletar(clienteSelecionado);
        }else{
            labelAviso.setText("PARA ACESSAR ESSA FUNÇÃO DEVE SER ADMINISTRADOR!");
        }
    }

    @FXML
    void filtrar(ActionEvent event) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT c FROM Cliente c");
        if (!campoNome.getText().isEmpty() || !campoCPF.getText().isEmpty()){
            sb.append(" WHERE");

            if (!campoNome.getText().isEmpty()) sb.append(" a.nome = '"+ campoNome.getText()+"'");
            if (!campoCPF.getText().isEmpty()) sb.append(
                    ((!campoNome.getText().isEmpty())? " AND": "")
                            +" a.dono.cpf = '"
                            + campoCPF.getText()
                            +"'"
            );

        }

        carregarTableViewCliente(daoCliente.buscaFiltragem(sb.toString()));
        limpaCampo();
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

    public void initialize(){
        daoCliente = new DaoCliente();
        carregaTabelaCompleta();
        tabela.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectItemCliente(newValue));
    }

    private List<Cliente> listCliente;
    private ObservableList<Cliente> observableListCliente;

    void carregaTabelaCompleta(){
        EntityManager em = Persistence.createEntityManagerFactory("ProjetoPoo").createEntityManager();
        em.getTransaction().begin();
        carregarTableViewCliente(new ArrayList<>(em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList()));
        em.close();
    }

    public void carregarTableViewCliente(List<Cliente> listaClientes){

        nomeTabela.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cpfTabela.setCellValueFactory(new PropertyValueFactory<>("cpf"));

        listCliente = listaClientes;

        observableListCliente = FXCollections.observableArrayList((listCliente));
        tabela.setItems(observableListCliente);
    }

    public void selectItemCliente(Cliente cliente){
        if (cliente != null){
            clienteSelecionado = cliente;
            nomeCliente.setText(cliente.getNome());
            enderecoCliente.setText(cliente.getEndereco());
            telefoneCliente.setText(cliente.getTelefone());
            cpfCliente.setText(cliente.getCpf());
        }else{
            limpaCampo();
        }
    }

    public void limpaCampo(){
        nomeCliente.setText("");
        enderecoCliente.setText("");
        telefoneCliente.setText("");
        cpfCliente.setText("");
    }

}
