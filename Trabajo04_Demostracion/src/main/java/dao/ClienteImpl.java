package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;

public class ClienteImpl extends Conexion implements ICRUD<Cliente> {

    @Override
    public void registrar(Cliente cli) throws Exception {

        String sql = "insert into cliente (NOMCLI,APECLI,DNICLI,ESTCLI) values (?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, cli.getNOMCLI());
            ps.setString(2, cli.getAPECLI());
            ps.setString(3, cli.getDNICLI());
            ps.setString(4, "A");
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al Ingresar ClienteImpl " + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    public List listarTodos(int tipo) throws Exception {
        List<Cliente> listadoCli = null;
        Cliente cli;
        String sql = "";
        switch (tipo) {
            case 1:
                sql = "SELECT * FROM cliente where ESTCLI = 'A' ORDER BY IDCLI DESC";
                break;
            case 2:
                sql = "SELECT * FROM cliente where ESTCLI = 'I' ORDER BY IDCLI DESC'";
                break;
            case 3:
                sql = "SELECT * FROM cliente";
                break;
        }
        try {
            listadoCli = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cli = new Cliente();
                cli.setIDCLI(rs.getInt("IDCLI"));
                cli.setNOMCLI(rs.getString("NOMCLI"));
                cli.setAPECLI(rs.getString("APECLI"));
                cli.setDNICLI(rs.getString("DNICLI"));
                listadoCli.add(cli);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en listarTodosD:" + e.getMessage());
        } finally {
            this.cerrar();
        }
        return listadoCli;
    }

    @Override
    public void modificar(Cliente obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Cliente obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }





}
