/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Endereco;

/**
 *
 * @author jonat
 */
public class DaoEndereco {

    public static void inserir(Endereco endereco)
            throws SQLException, Exception {

        String sql = "INSERT INTO Endereco(logradouro, bairro, cidade, estado) "
                + "VALUES (?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Conexao.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, endereco.getLogradouroEndereco());
            statement.setString(2, endereco.getBairroEndereco());
            statement.setString(3, endereco.getCidadeEndereco());
            statement.setString(4, endereco.getEstadoEndereco());
            System.out.println(statement.toString());

            System.out.println("Executando COMANDO SQL: " + sql);
            statement.execute();
        } finally {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    //Deleta um cliente na tabela "cliente" do banco de dados
    public static void deletar(Long id)
            throws SQLException, Exception {
        //Monta a string de remoção de um cliente no BD,
        //utilizando os dados do clientes passados como parâmetro
        String sql = "DELETE FROM Endereco "
                + "WHERE id = ?; ";

        //Conexão para abertura e fechamento
        Connection connection = null;
        //Statement para obtenção através da conexão, execução de
        //comandos SQL e fechamentos
        PreparedStatement statement = null;
        try {
            //Abre uma conexão com o banco de dados
            connection = Conexao.getConnection();
            //Cria um statement para execução de instruções SQL
            statement = connection.prepareStatement(sql);

            statement.setLong(1, id);

            //Exibe no console o que será executado no banco de dados
            System.out.println("Executando COMANDO SQL: " + sql);
            //Executa o comando no banco de dados
            statement.execute();
        } finally {
            //Se o statement ainda estiver aberto, realiza seu fechamento
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
            //Se a conexão ainda estiver aberta, realiza seu fechamento
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    public static void alterar(Endereco endereco, Long id)
            throws SQLException, Exception {
        String sql = "UPDATE Endereco SET Logradouro = ?, Bairro = ?, Cidade = ?, Estado = ? "
                + "WHERE id = ?;";

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Conexao.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, endereco.getLogradouroEndereco());
            statement.setString(2, endereco.getBairroEndereco());
            statement.setString(3, endereco.getCidadeEndereco());
            statement.setString(4, endereco.getEstadoEndereco());
            statement.setLong(4, id);
            System.out.println(statement.toString());

            System.out.println("Executando COMANDO SQL: " + sql);
            statement.execute();
        } finally {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    public static Endereco obter(Long id)
            throws SQLException, Exception {
        String sql = "SELECT * FROM Endereco WHERE id = ?;";

        PreparedStatement statement = null;
        Connection connection = null;
        Endereco endereco = new Endereco();
        connection = Conexao.getConnection();
        statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        System.out.println(statement.toString());
        ResultSet result = null;
        result = statement.executeQuery();

        while (result.next()) {

            endereco.setLogradouroEndereco(result.getString("logradouro"));
            endereco.setBairroEndereco(result.getString("bairro"));
            endereco.setCidadeEndereco(result.getString("cidade"));
            endereco.setEstadoEndereco(result.getString("estado"));
            endereco.setCodigoEndereco(result.getLong("id"));
        }

        return endereco;
    }

    public static List<Endereco> executarConsulta(String sql) throws SQLException, Exception {
        List<Endereco> lista = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        try {
            connection = Conexao.getConnection();
            statement = connection.createStatement();
            System.out.println("Executando CONSULTA SQL: " + sql);
            result = statement.executeQuery(sql);
            while (result.next()) {
                if (lista == null) {
                    lista = new ArrayList<Endereco>();
                }
                Endereco endereco = new Endereco();
                endereco.setLogradouroEndereco(result.getString("logradouro"));
                endereco.setBairroEndereco(result.getString("bairro"));
                endereco.setCidadeEndereco(result.getString("cidade"));
                endereco.setEstadoEndereco(result.getString("estado"));
                endereco.setCodigoEndereco(result.getLong("id"));
                lista.add(endereco);
            }
        } finally {
            if (result != null && !result.isClosed()) {
                result.close();
            }
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        return lista;
    }

    public static List<Endereco> listar()
            throws SQLException, Exception {
        String sql = "SELECT * FROM Endereco;";

        return executarConsulta(sql);
    }

    public static Endereco retornarEndereco(Long id) throws
            SQLException, Exception {
        String sql = "SELECT * FROM Endereco "
                + " WHERE id = ?";
        Endereco endereco = new Endereco();
        Connection connection = null;
        PreparedStatement statement = null;

        connection = Conexao.getConnection();
        statement = connection.prepareStatement(sql);

        statement.setLong(1, id);
        System.out.println(statement.toString());
        System.out.println("Executando CONSULTA SQL: " + sql);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            endereco.setLogradouroEndereco(result.getString("logradouro"));
            endereco.setBairroEndereco(result.getString("bairro"));
            endereco.setCidadeEndereco(result.getString("cidade"));
            endereco.setEstadoEndereco(result.getString("estado"));
            connection.close();
            return endereco;
        }

        return null;
    }
}
