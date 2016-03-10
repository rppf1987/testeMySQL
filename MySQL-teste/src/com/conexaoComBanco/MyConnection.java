package com.conexaoComBanco;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

class MyConnection {
	private String serverName = "localhost";
	private String mydatabase = "employees";
	private String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
	private String username = "root"; 
	private String password = "adminteste";
	
	static DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	
	static Scanner entrada = new Scanner(System.in);
	
	private Connection conexao = null;

	public void conectar(){
		try {
			conexao = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Erro na conexao");
		}
	}
	
	public void desconectar(){
		try {
			conexao.close();
			entrada.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConexaoMySQL(){
		return conexao;
	}
	
	public void buscaPorID(){
		Statement stmt = null;
		int emp_no;
	    String sql, first = null, last = null,
	    		gender = null, birth = null, hire = null;
	    ResultSet rs = null;
	    
	    System.out.println("Digite id: ");
	    emp_no = entrada.nextInt();
	    	    
	    sql = "SELECT * FROM employees WHERE emp_no = " + emp_no;
		try {
			stmt = conexao.createStatement();
		    rs = stmt.executeQuery(sql);

			while(rs.next()){
				emp_no  = rs.getInt("emp_no");
			    first = rs.getString("first_name");
			    last = rs.getString("last_name");
			    gender = rs.getString("gender");
			    birth = rs.getString("birth_date");
			    hire = rs.getString("hire_date");
			}

			System.out.println("\nID: " + emp_no);
			System.out.println("Nome: " + first + " "+ last);
			System.out.println("Genero: " + gender);
			System.out.println("Nascimento: " + birth);
			System.out.println("Contratacao: " + hire + "\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void buscaPaginada(){
		Statement stmt = null;
	    String sql, busca, first, last, gender, birth, hire;
	    ResultSet rs = null;
	    int emp_no, count = 1;
	    
	    System.out.println("Buscar: ");
	    busca = entrada.nextLine();
	    sql = "SELECT * FROM employees WHERE first_name LIKE '" + busca + "' or " +
	    									 "last_name LIKE '" + busca + "' or " +
	    									 "gender LIKE '" + busca + "' or " +
	    									 "birth_date LIKE '" + busca + "' or " +
	    									 "hire_date LIKE '" + busca + "'";
	    
	    try {
			stmt = conexao.createStatement();
		    rs = stmt.executeQuery(sql);
	    } catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    try {
				while(rs.next()){
					if(count%10 == 0){
						System.out.println("Pressione Enter para continuar");
						System.in.read();
					}
				else{
					emp_no  = rs.getInt("emp_no");
					first = rs.getString("first_name");
					last = rs.getString("last_name");
					gender = rs.getString("gender");
					birth = rs.getString("birth_date");
					hire = rs.getString("hire_date");
					System.out.println("\nID: " + emp_no);
					System.out.println("Nome: " + first + " "+ last);
					System.out.println("Genero: " + gender);
					System.out.println("Nascimento: " + birth);
					System.out.println("Contratacao: " + hire + "\n");
				}
				count++;
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void cadastro(){
		Statement stmt = null;

	    String sql = null, first, last, gender, birth;

	    int emp_no;
	    
	    System.out.println("Digite id: ");
	    emp_no = entrada.nextInt();
	    
	    System.out.println("Digite primeiro nome: ");
	    first = entrada.nextLine();
	    
	    System.out.println("Digite segundo nome: ");
	    last = entrada.nextLine();
	    
	    System.out.println("Digite genero: ");
	    gender = entrada.nextLine();
	    
	    System.out.println("Digite data de nascimento: ");
	    birth = entrada.nextLine();
	    
	    sql = "INSERT INTO employees VALUES ('" + emp_no + "', '" + birth + "', '" + 
	    	  first + "', '" + last + "', '" + gender + "', CURDATE())";
	    
	    try {
			stmt = conexao.createStatement();
		    stmt.execute(sql);
			System.out.print("ID: " + emp_no + " cadastrado\n\n");
	    } catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void atualizacao(){
		Statement stmt = null;
	    String sql = null, newdata;
	    int id, field;
	    
	    System.out.println("Digite ID a ser atualizado: ");
	    id = entrada.nextInt();
	    
	    System.out.println("Selecione dado a ser modificado: ");
	    System.out.println("1 - Primeiro nome");
	    System.out.println("2 - Segundo nome");
	    System.out.println("3 - Data de nascimento");
	    System.out.println("4 - Data de contratacao");
	    System.out.println("5 - Genero");
	    
	    field = entrada.nextInt();
	    
	    System.out.println("Digite novo dado: ");
	    newdata = entrada.next();
	    switch (field) {
		case 1:
		    sql = "UPDATE employees SET first_name = '" + newdata + "' WHERE emp_no =  '" + id + "'";
			break;
		case 2:
		    sql = "UPDATE employees SET last_name = '" + newdata + "' WHERE emp_no =  '" + id + "'";
			break;
		case 3:
		    sql = "UPDATE employees SET birth_date = '" + newdata + "' WHERE emp_no = '" + id + "'";
			break;
		case 4:
		    sql = "UPDATE employees SET hire_date = '" + newdata + "' WHERE emp_no = '" + id + "'";
			break;
		case 5:
		    System.out.println(newdata);
		    sql = "UPDATE employees SET gender = '" + newdata + "' WHERE emp_no = '" + id + "'";
			break;
		default:
			System.out.println("Opcao invalida");
			break;
		}
	    
	    try {
			stmt = conexao.createStatement();
		    stmt.execute(sql);
		    System.out.println("\nAlteracao realizada\n");
	    } catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void remocao(){
		Statement stmt = null;
		String sql;
		int emp_no;
		
	    System.out.println("Digite id a ser removido: ");
	    emp_no = entrada.nextInt();
		
		sql = "DELETE FROM employees WHERE emp_no = '" + emp_no + "'";
		
		try {
			stmt = conexao.createStatement();
		    stmt.execute(sql);

			System.out.print("\nID: " + emp_no + " removido\n");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
