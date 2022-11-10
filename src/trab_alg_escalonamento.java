import java.util.Scanner;
        import java.util.Random;
public class trab_alg_escalonamento {
    static int n_processos = 3;
    int[] id = new int[n_processos];

    public static void main(String[] args) {
        int[] tempo_execucao = new int[n_processos];
        int[] tempo_espera = new int[n_processos];
        int[] tempo_restante = new int[n_processos];
        int[] tempo_chegada = new int[n_processos];
        int[] prioridade = new int[n_processos];

        Scanner teclado = new Scanner (System.in);

        popular_processos(tempo_execucao, tempo_espera, tempo_restante, tempo_chegada, prioridade);
        imprime_processos(tempo_execucao, tempo_espera, tempo_restante, tempo_chegada, prioridade);
        int algoritimo;

        while(true) {
            System.out.println("---- Escolha sua opção: ----"
                    + "\n1- FCFS "
                    + "\n2- SJF Preemptivo"
                    + "\n3- SJF Nao Preemptivo"
                    + "\n0- Sair");
            algoritimo =  teclado.nextInt();

            if (algoritimo == 1) { //FCFS
                FCFS(tempo_execucao, tempo_espera, tempo_restante, tempo_chegada);
            }
            else if (algoritimo == 0) {
                break;
            }
        }
    }
    public static void popular_processos(int[] tempo_execucao, int[] tempo_espera, int[] tempo_restante, int[] tempo_chegada, int[] prioridade){
        Random random = new Random();
        Scanner teclado = new Scanner (System.in);
        int aleatorio;

        System.out.println("---- Digite uma opcao ----"
                + "\n1- Para aleatorio"
                + "\n2- Para digitar");
        aleatorio =  teclado.nextInt();

        for (int i = 0; i < n_processos; i++) {
            //Popular Processos Aleatorio
            if (aleatorio == 1){
                tempo_execucao[i] = random.nextInt(10)+1;
                tempo_chegada[i] = random.nextInt(10)+1;
                prioridade[i] = random.nextInt(10)+1;
            }
            //Popular Processos Manual
            else {
                System.out.println("Digite o tempo de execucao do processo["+i+"]:  ");
                tempo_execucao[i] = teclado.nextInt();
                System.out.println("Digite o tempo de chegada do processo["+i+"]:  ");
                tempo_chegada[i] = teclado.nextInt();
                System.out.println("Digite a prioridade do processo["+i+"]:  ");
                prioridade[i] = teclado.nextInt();
            }
            tempo_restante[i] = tempo_execucao[i];
        }
    }
    public static void imprime_processos(int[] tempo_execucao, int[] tempo_espera, int[] tempo_restante, int[] tempo_chegada, int[] prioridade){
        //Imprime lista de processos
        for (int i = 0; i < n_processos; i++) {
            System.out.println("Process["+i+"]: tempo_execucao="+ tempo_execucao[i] + " tempo_restante="+tempo_restante[i] + " tempo_chegada=" + tempo_chegada[i] + " prioridade=" + prioridade[i]);
        }
    }
    public static void imprime_stats (int[] espera) {
        int[] tempo_espera = espera.clone();
        //Calcula e imprime estatisticas
        float tempo_total = 0;
        for (int i = 0; i < n_processos; i++) {
            System.out.println("Process["+i+"]: tempo_espera="+ tempo_espera[i]);
            tempo_total = tempo_espera[i] + tempo_total;
        }
        System.out.println("Tempo medio de espera: "+tempo_total/n_processos);
    }
    public static void FCFS(int[] execucao, int[] espera, int[] restante, int[] chegada){
        int[] tempo_execucao = execucao.clone();
        int[] tempo_espera = espera.clone();
        int[] tempo_restante = restante.clone();
        int[] tempo_chegada = chegada.clone();

        int processo_em_execucao;
        processo_em_execucao = 0;
        for (int tempo = 1; tempo<= 1000; tempo++){
            try {
                if (tempo_restante[processo_em_execucao] == tempo_execucao[processo_em_execucao])
                    tempo_espera[processo_em_execucao] = tempo -1;

                if (tempo_restante[processo_em_execucao]!=1){

                    if (processo_em_execucao != (n_processos)) {
                        System.out.println("tempo["+tempo+"]: processo["+processo_em_execucao+"] restante="+(tempo_restante[processo_em_execucao]-1));
                        tempo_restante[processo_em_execucao]--;
                    }
                    else
                        break;
                }
                else {
                    System.out.println("tempo["+tempo+"]: processo["+processo_em_execucao+"] restante="+(tempo_restante[processo_em_execucao]-1));
                    tempo_restante[processo_em_execucao]--;
                    if ((processo_em_execucao+1) != (n_processos))
                        processo_em_execucao++;
                    else
                        break;
                }
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
        imprime_stats(tempo_espera);
    }
}
