package com.conexaoComBanco;

import java.util.Scanner;

public class ConnectMySQL {
	
	public static MyConnection conexao = new MyConnection();
	static int opcao = 9;
	static Scanner entrada = new Scanner(System.in);

	public static void menu(){
		
		System.out.println("Escolha a opcao desejada:");
		System.out.println("1 - Busca por ID");
		System.out.println("2 - Busca Paginada");
		System.out.println("3 - Cadastro");
		System.out.println("4 - Atualizacao");
		System.out.println("5 - Remocao");
		System.out.println("0 - Sair");

		opcao = entrada.nextInt();
				
		switch(opcao){
		case 1:
			conexao.buscaPorID();
			break;
		case 2:
			conexao.buscaPaginada();
			break;
		case 3:
			conexao.cadastro();
			break;
		case 4:
			conexao.atualizacao();
			break;
		case 5:
			conexao.remocao();
			break;
		case 0:
			break;
		default:
			System.out.println("Opcao invalida");
		}
	}
	
	public static void main(String[] args) {

		String driverName = "com.mysql.jdbc.Driver";                        

		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		conexao.conectar();
		
		if(conexao.getConexaoMySQL() == null)
			System.out.println("\nErro na conexao!\n");
		else{
			System.out.println("\nConectado!\n");
			while (opcao != 0){
				menu();
			}
			conexao.desconectar();
		}
		entrada.close();
	}

}
