//package autocenter;

import java.util.Scanner;

// =============================================
// MÓDULO: OrdemDeServico.java
// Responsabilidade: Abertura e gestão das OS
// =============================================

public class OrdemDeServico {

    static void abrirOS(Scanner sc,
                        Mecanico[]     mecanicos,  int totalMecanicos,
                        Veiculo[]      veiculos,   int totalVeiculos,
                        Peca[]         pecas,      int totalPecas,
                        OrdemServico[] ordens,     int[] totalOrdens,
                        int MAX) {

        if (totalOrdens[0] >= MAX) { System.out.println("Limite atingido!"); return; }

        OrdemServico os = new OrdemServico();
        System.out.print("Número da OS: ");
        os.numero = sc.nextInt(); sc.nextLine();

        // Buscar veículo pela placa
        System.out.print("Placa do veículo: ");
        os.placaVeiculo = sc.nextLine().toUpperCase();

        int idxVeiculo = -1;
        for (int i = 0; i < totalVeiculos; i++) {
            if (veiculos[i].placa.equals(os.placaVeiculo)) { idxVeiculo = i; break; }
        }
        if (idxVeiculo == -1) { System.out.println("Erro: veículo não encontrado!"); return; }

        // Buscar mecânico pelo código
        System.out.print("Código do mecânico: ");
        os.codigoMecanico = sc.nextInt(); sc.nextLine();

        int idxMecanico = -1;
        for (int i = 0; i < totalMecanicos; i++) {
            if (mecanicos[i].codigo == os.codigoMecanico) { idxMecanico = i; break; }
        }
        if (idxMecanico == -1) { System.out.println("Erro: mecânico não encontrado!"); return; }

        // Buscar peça pelo código
        System.out.print("Código da peça: ");
        os.codigoPeca = sc.nextInt(); sc.nextLine();

        int idxPeca = -1;
        for (int i = 0; i < totalPecas; i++) {
            if (pecas[i].codigo == os.codigoPeca) { idxPeca = i; break; }
        }
        if (idxPeca == -1) { System.out.println("Erro: peça não encontrada!"); return; }

        // Validar estoque
        System.out.print("Quantidade a usar: ");
        os.quantidadePeca = sc.nextInt(); sc.nextLine();

        if (os.quantidadePeca > pecas[idxPeca].quantidade) {
            System.out.println("Erro: estoque insuficiente!");
            System.out.println("  Disponível: " + pecas[idxPeca].quantidade
                             + " | Solicitado: " + os.quantidadePeca);
            return;
        }

        System.out.print("Valor da mão de obra: R$ ");
        os.valorMaoDeObra = sc.nextDouble(); sc.nextLine();

        // Desconta estoque e salva
        pecas[idxPeca].quantidade -= os.quantidadePeca;
        ordens[totalOrdens[0]++] = os;

        Arquivos.salvarPecas(pecas, totalPecas);
        Arquivos.salvarOrdens(ordens, totalOrdens[0]);

        double totalPecasValor = os.quantidadePeca * pecas[idxPeca].precoUnitario;
        System.out.println("\n✔ OS aberta e salva com sucesso!");
        System.out.println("  Veículo : " + veiculos[idxVeiculo].modelo + " (" + os.placaVeiculo + ")");
        System.out.println("  Mecânico: " + mecanicos[idxMecanico].nome);
        System.out.printf("  Mão de obra : R$ %.2f%n", os.valorMaoDeObra);
        System.out.printf("  Total peças : R$ %.2f%n", totalPecasValor);
        System.out.printf("  TOTAL GERAL : R$ %.2f%n", os.valorMaoDeObra + totalPecasValor);
    }
}