//package autocenter;

import java.util.Scanner;

// =============================================
// MÓDULO: Relatorios.java
// Responsabilidade: Relatórios e alerta de estoque baixo
// =============================================

public class Relatorios {

    static final int LIMITE_ESTOQUE = 3;

    // --- DIFERENCIAL: Alerta de estoque baixo ---
    static void alertaEstoqueBaixo(Peca[] pecas, int totalPecas) {
        boolean temAlerta = false;
        for (int i = 0; i < totalPecas; i++) {
            if (pecas[i].quantidade <= LIMITE_ESTOQUE) {
                if (!temAlerta) {
                    System.out.println("\n ================================================");
                    System.out.println("  ATENÇÃO! PEÇAS COM ESTOQUE BAIXO:");
                    System.out.println("  ================================================");
                    temAlerta = true;
                }
                if (pecas[i].quantidade == 0) {
                    System.out.printf("  [ZERADO] Cód: %d | %-25s | Qtd: %d%n",
                        pecas[i].codigo, pecas[i].descricao, pecas[i].quantidade);
                } else {
                    System.out.printf("  [BAIXO ] Cód: %d | %-25s | Qtd: %d%n",
                        pecas[i].codigo, pecas[i].descricao, pecas[i].quantidade);
                }
            }
        }
        if (temAlerta) {
            System.out.println("  ================================================ ");
        }
    }

    // --- Menu de relatórios ---
    static void menuRelatorios(Scanner sc,
                                Mecanico[]     mecanicos,  int totalMecanicos,
                                Peca[]         pecas,      int totalPecas,
                                OrdemServico[] ordens,     int totalOrdens) {
        System.out.println("\n--- RELATÓRIOS ---");
        System.out.println("1. Comissão da Equipe");
        System.out.println("2. Inventário Crítico (estoque zero)");
        System.out.println("3. Faturamento de Peças");
        System.out.print("Opção: ");
        int op = sc.nextInt(); sc.nextLine();
        switch (op) {
            case 1: relatorioComissao(mecanicos, totalMecanicos, ordens, totalOrdens);    break;
            case 2: relatorioInventario(pecas, totalPecas);                               break;
            case 3: relatorioFaturamento(pecas, totalPecas, ordens, totalOrdens);         break;
            default: System.out.println("Opção inválida!");
        }
    }

    // --- Relatório A: Comissão da equipe ---
    static void relatorioComissao(Mecanico[] mecanicos, int totalMecanicos,
                                   OrdemServico[] ordens, int totalOrdens) {
        System.out.println("\n=== COMISSÃO DA EQUIPE ===");
        for (int i = 0; i < totalMecanicos; i++) {
            double totalMao = 0;
            for (int j = 0; j < totalOrdens; j++) {
                if (ordens[j].codigoMecanico == mecanicos[i].codigo)
                    totalMao += ordens[j].valorMaoDeObra;
            }
            System.out.printf("%-20s | %-12s | Mão de Obra: R$ %.2f%n",
                mecanicos[i].nome, mecanicos[i].especialidade, totalMao);
        }
    }

    // --- Relatório B: Inventário crítico ---
    static void relatorioInventario(Peca[] pecas, int totalPecas) {
        System.out.println("\n=== INVENTÁRIO CRÍTICO (Estoque Zero) ===");
        boolean achou = false;
        for (int i = 0; i < totalPecas; i++) {
            if (pecas[i].quantidade == 0) {
                System.out.printf("Código: %d | %s%n", pecas[i].codigo, pecas[i].descricao);
                achou = true;
            }
        }
        if (!achou) System.out.println("Nenhuma peça zerada. Estoque OK!");
    }

    // --- Relatório C: Faturamento de peças ---
    static void relatorioFaturamento(Peca[] pecas, int totalPecas,
                                      OrdemServico[] ordens, int totalOrdens) {
        System.out.println("\n=== FATURAMENTO DE PEÇAS ===");
        double totalGeral = 0;
        for (int j = 0; j < totalOrdens; j++) {
            for (int i = 0; i < totalPecas; i++) {
                if (pecas[i].codigo == ordens[j].codigoPeca) {
                    double sub = ordens[j].quantidadePeca * pecas[i].precoUnitario;
                    System.out.printf("OS #%d | %-20s | Qtd: %d | R$ %.2f%n",
                        ordens[j].numero, pecas[i].descricao, ordens[j].quantidadePeca, sub);
                    totalGeral += sub;
                    break;
                }
            }
        }
        System.out.printf("%nTOTAL FATURADO EM PEÇAS: R$ %.2f%n", totalGeral);
    }
}