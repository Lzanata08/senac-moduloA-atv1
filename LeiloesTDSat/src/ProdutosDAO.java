/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import com.mysql.cj.util.StringUtils;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    /**
     * Metodo para conectar no banco
     * @return boolean - true para sucesso
     */
    public boolean conectar(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/leilao","root", "150208");
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return false;
        }
    }
    
     public void desconectar(){
        try {
            conn.close();
        } catch (SQLException ex) {
            //pode-se deixar vazio para evitar uma mensagem de erro desnecessária ao usuário
        }
    }
    
    public int cadastrarProduto (ProdutosDTO produto){
        int status;
        try {
            
            prep = conn.prepareStatement("INSERT INTO produtos (nome, valor, status) VALUES(?,?,?)");
            prep.setString(1,produto.getNome());
            prep.setInt(2,(produto.getValor()));
            prep.setString(3,produto.getStatus());            
            status = prep.executeUpdate();
            return status; //retornar 1
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }
        
    }
    public ResultSet consultar(){
        
        try {
            
            
                prep = conn.prepareStatement("select * from produtos" );
           
             
            ResultSet rs = prep.executeQuery();
            return rs; //retornar 1
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            
        }
        return null;
    }

    int venderProduto(int id) {
         int status;
        try {
            
            prep = conn.prepareStatement("UPDATE produtos SET status = ? where id = ?");
            prep.setString(1,"Vendido");
                   
            prep.setLong(2, id);   
            status = prep.executeUpdate();
            return status;
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }
    }

    ResultSet consultarVendidos() {
           try {
            
            
                prep = conn.prepareStatement("select * from produtos where status = Vendido" );
           
             
            ResultSet rs = prep.executeQuery();
            return rs; //retornar 1
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            
        }
        return null;
    }
    
    
    
        
}

