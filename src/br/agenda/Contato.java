package br.agenda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Contato {
    private Usuario usuario;
    private int id;
    private String nome;
    private String telefone;

    public Contato(String nome, String telefone, Usuario usuario) {
        this.usuario = usuario;
        this.nome = nome;
        this.telefone = telefone;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    
    

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return nome + " (" + telefone + ")";
    }
    
    public void salvar(){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://10.42.0.1:3306/agenda", "root", "abc123");
            PreparedStatement pst = con.prepareStatement("INSERT INTO Contato(nome, telefone, cpf) VALUES (?, ?, ?)");
            pst.setString(1, nome);
            pst.setString(2, telefone);
            pst.setString(3, usuario.getCpf());
            pst.executeQuery();
            ResultSet rs = pst.executeQuery();
            pst.execute();
            pst.close();
            con.close();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    }
    
}
