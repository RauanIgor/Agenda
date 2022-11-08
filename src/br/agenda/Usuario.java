
package br.agenda;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 20161034010010
 */
public class Usuario {
    private List<Contato> contatos;
    private String cpf;
    private String nome;
    private String senha;
    public static Usuario usuario = null;

    public Usuario(String cpf, String nome, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
        contatos = new ArrayList<>();
    }

    public List<Contato> getContatos() {
        contatos.clear();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://10.49.9.174/agenda", "root", "abc123");
            PreparedStatement pst = con.prepareStatement("SELECT * FROM usuario WHERE CPF = ?");
            pst.setString(1, cpf);
            pst.executeQuery();
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                contatos.add(new Contato(rs.getString("nome"),rs.getString("telefone"),this));
            }
            rs.close();
            pst.close();
            con.close();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

        return contatos;
    }
    public void addContato(Contato c){
        c.salvar();
        
    }
    

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }
    public void salvar(){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://10.49.9.174/agenda", "root", "abc123");
            PreparedStatement pst = con.prepareStatement("INSERT INTO usuario VALUES (?, ?, ?)");
            pst.setString(1, cpf);
            pst.setString(2, nome);
            pst.setString(3, senha);
            pst.execute();
            pst.close();
            con.close();
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            
        }
    }
    public static boolean existe(String cpf) {
        boolean existe = false;
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://10.49.9.174/agenda","root","abc123");
            PreparedStatement pst = con.prepareStatement("SELECT * FROM usuario WHERE CPF = ?");
            pst.setString(1, cpf);
            pst.executeQuery();
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                existe = true;

            }
            rs.close();
            pst.close();
            con.close();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return existe;
    }
    public static Usuario autenticar(String cpf, String senha){
        Usuario u = null;
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://10.49.9.174/agenda", "root", "abc123");
            PreparedStatement pst = con.prepareStatement("SELECT * FROM usuario WHERE CPF = ? AND senha =?");
            pst.setString(1, cpf);
            pst.setString(2, senha);
            pst.executeQuery();
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                 u = new Usuario(rs.getString("cpf"),
                 rs.getString("nome"), rs.getString("senha"));

            }
            rs.close();
            pst.close();
            con.close();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return u;
    }
    
}
