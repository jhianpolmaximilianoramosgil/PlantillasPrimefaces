package controlador;

import dao.ClienteImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Cliente;
import lombok.Data;

@Data

//Notación CDI
@Named(value = "clienteC")
//@Dependent
@SessionScoped
public class ClienteC implements Serializable {

    private Cliente cliente;
    private ClienteImpl dao;
    private List<Cliente> listadoClientes;
    private int tipo = 1;

    public ClienteC() {
        cliente = new Cliente();
        dao = new ClienteImpl();
        listadoClientes = new ArrayList<>();
    }

    public void registrar() throws Exception {
        try {
            dao.registrar(cliente);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Registrado con Exito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en registrarC " + e.getMessage());
        }
    }

    public void listar() {
        try {
            listadoClientes = dao.listarTodos(tipo);
        } catch (Exception e) {
            System.out.println("Error en listarC " + e.getMessage());
        }
    }

    public void limpiar() {
        cliente = new Cliente();
    }


    @PostConstruct
    public void construir() {
        listar();
    }

}
