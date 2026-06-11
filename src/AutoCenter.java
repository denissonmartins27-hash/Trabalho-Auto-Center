//package autocenter;

import java.util.Scanner;

// =============================================
// MÓDULO: AutoCenter.java
// Responsabilidade: Menu principal e coordenação dos módulos
// =============================================

public class AutoCenter {

    static final int MAX = 100;

    static Mecanico[]     mecanicos = new Mecanico[MAX];
    static Veiculo[]      veiculos  = new Veiculo[MAX];
    static Peca[]         pecas     = new Peca[MAX];
    static OrdemServico[] ordens    = new OrdemServico[MAX];

    static int totalMecanicos = 0;
    static int totalVeiculos  = 0;
    static int totalPecas     = 0;
    static int totalOrdens    = 0;

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Carrega todos os dados do disco ao iniciar
        totalMecanicos = Arquivos.carregarMecanicos(mecanicos, MAX);
        totalVeiculos  = Arquivos.carregarVeiculos(veiculos, MAX);
        totalPecas     = Arquivos.carregarPecas(pecas, MAX);
        totalOrdens    = Arquivos.carregarOrdens(ordens, MAX);
        System.out.println("✔ Dados carregados do disco.");

        // Vetores de tamanho 1 para permitir alteração dentro dos módulos
        int[] arrMecanicos = { totalMecanicos };
        int[] arrVeiculos  = { totalVeiculos  };
        int[] arrPecas     = { totalPecas     };
        int[] arrOrdens    = { totalOrdens    };

        int opcao;
        do {
            Relatorios.alertaEstoqueBaixo(pecas, arrPecas[0]);

            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║    AUTO CENTER ROTA 381      ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║ 1. Cadastrar Mecânico        ║");
            System.out.println("║ 2. Cadastrar Veículo         ║");
            System.out.println("║ 3. Cadastrar Peça            ║");
            System.out.println("║ 4. Abrir Ordem de Serviço    ║");
            System.out.println("║ 5. Relatórios                ║");
            System.out.println("║ 6. Repor Estoque de Peça     ║");
            System.out.println("║ 0. Sair                      ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.print("Opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    Cadastros.cadastrarMecanico(sc, mecanicos, arrMecanicos, MAX);
                    break;
                case 2:
                    Cadastros.cadastrarVeiculo(sc, veiculos, arrVeiculos, MAX);
                    break;
                case 3:
                    Cadastros.cadastrarPeca(sc, pecas, arrPecas, MAX);
                    break;
                case 4:
                    OrdemDeServico.abrirOS(sc,
                        mecanicos, arrMecanicos[0],
                        veiculos,  arrVeiculos[0],
                        pecas,     arrPecas[0],
                        ordens,    arrOrdens,
                        MAX);
                    break;
                case 5:
                    Relatorios.menuRelatorios(sc,
                        mecanicos, arrMecanicos[0],
                        pecas,     arrPecas[0],
                        ordens,    arrOrdens[0]);
                    break;
                case 6:
                    Cadastros.reporEstoque(sc, pecas, arrPecas);
                    break;
                case 0:
                    System.out.println("Encerrando o sistema. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
}