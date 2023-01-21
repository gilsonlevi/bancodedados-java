package ConexaoComBancoDeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) throws SQLException {

		Scanner teclado = new Scanner(System.in);

		Connection conexao = DriverManager.getConnection("jdbc:mysql://10.225.0.4/20201164010004_mercado",
				"20201164010004", "20201164010004+alves");

		while (true) {
			System.out.println("0. Finalizar programa");
			System.out.println("1. Adicionar produto");
			System.out.println("2. Buascar produto pelo nome");
			System.out.println("3. Listar todos os produtos");
			int opcao = teclado.nextInt();
			teclado.nextLine();

			if (opcao == 0) {
				System.out.println("Finalizando programa...");
				break;
			} else if (opcao == 1) {
				System.out.println("Digite o nome do produto: ");
				String nome = teclado.nextLine();

				System.out.println("Digite o preço do produto: ");
				Double preco = teclado.nextDouble();

				System.out.println("Digite a quantidade do produto: ");
				int quantidade = teclado.nextInt();

				System.out.println("Digite a medida: ");
				String medida = teclado.nextLine();

				PreparedStatement stmt = conexao
						.prepareStatement("insert into produto (nome. medida, preco, quantidade) values (?,?,?,?)");
				stmt.setString(1, nome);
				stmt.setDouble(2, opcao);
				stmt.setDouble(3, quantidade);
				stmt.setString(4, medida);

				try {
					if (stmt.executeUpdate() == 1) {
						System.out.println("Produto adicionado com sucesso");
					} else
						System.out.println("Erro ao adicionar produto!");
				} catch (SQLException e) {
					System.out.println("O produto informado já está cadastrado");
				}

			} else if (opcao == 2) {
				System.out.println("Digite o nome do produto: ");
				String nome = teclado.nextLine();

				PreparedStatement stmt = conexao.prepareStatement("select * from produto where nome = ?");
				stmt.setString(1, nome);

				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					System.out.println("ID: " + rs.getInt("id"));
					System.out.println("Nome: " + rs.getString("nome"));
					System.out.println("Preço: " + rs.getDouble("preco"));
					System.out.println("Quantidade: " + rs.getInt("quantidade"));
					System.out.println("Medida:" + rs.getString("medida"));
				} else {
					System.out.println("Não há produto");
				}

			} else if (opcao == 3) {
				PreparedStatement stmt = conexao.prepareStatement("select * from produto");
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					System.out.println("ID: " + rs.getInt("id"));
					System.out.println("Nome: " + rs.getString("nome"));
					System.out.println("Preço: " + rs.getDouble("preco"));
					System.out.println("Quantidade: " + rs.getInt("quantidade"));
					System.out.println("Medida:" + rs.getString("medida"));
				}

			} else {
				System.out.println("Opcção invalida");
			}
		}

		teclado.close();
		conexao.close();
	}
}
