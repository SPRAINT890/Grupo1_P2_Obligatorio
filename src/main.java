import java.util.Scanner;

public class main {
    public static void top_drivers(){

    }

    public static void top_users_tweets(){

    }
    public static void amount_hastagh_on_a_day(){

    }
    public static void top_fav_accounts(){

    }
    public static void amount_tweets_with(){

    }

    public static void clear_console(){
        for (int i = 0; i<50; i++){
            System.out.println("");
        }
    }

    public static void load_csv(){

    }
    public static void main(String[] args) throws InterruptedException {
        Scanner entrada = new Scanner(System.in);
        load_csv();
        int valor = 1;
        while (valor != 0){
            clear_console();
            System.out.println("1- Listar los 10 pilotos activos en la temporada 2023 más mencionados en los tweets en un mes");
            System.out.println("2- Top 15 usuarios con más tweets");
            System.out.println("3- Cantidad de hashtags distintos para un día dado");
            System.out.println("4- Hashtag más usado para un día dado");
            System.out.println("5- Top 7 cuentas con más favoritos");
            System.out.println("6- Cantidad de tweets con una palabra o frase específicos ");
            System.out.println("0- Exit");
            try {
                valor = entrada.nextInt();
            }catch (Exception e){
                clear_console();
                System.out.println("Valor erroneo");
                Thread.sleep(4000);
                continue;
            }

            if (valor == 1){
                clear_console();
                System.out.println("1");
            }
            if (valor == 2){
                clear_console();
                System.out.println("2");
            }
            if (valor == 3){
                clear_console();
                System.out.println("3");
            }
            if (valor == 4){
                clear_console();
                System.out.println("4");
            }
            if (valor == 5){
                clear_console();
                System.out.println("5");
            }
            if (valor == 6){
                clear_console();
                System.out.println("6");
            }
        }
    }
}
