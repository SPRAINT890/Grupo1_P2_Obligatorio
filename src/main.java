import Entities.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import uy.edu.um.prog2.adt.MyHash.HashTableCerradoImpl;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

import static java.lang.Long.parseLong;


public class main {
    static Long idTweet = Long.valueOf(0);
    static Long idHastag = Long.valueOf(0);
    static HashTableCerradoImpl<Long, User> listaUsuariosRegistrados = new HashTableCerradoImpl<>(10);
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
        try {
            Reader in = new FileReader("f1_dataset_test.csv");
            Iterable<CSVRecord> records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(in);
            for (CSVRecord record : records) {
                String id = record.get("");
                String user_name = record.get("user_name");
                String user_location = record.get("user_location");
                String user_description = record.get("user_description");
                String user_created = record.get("user_created");
                String user_followers = record.get("user_followers");
                String user_friends = record.get("user_friends");
                String user_favourites = record.get("user_favourites");
                String user_verified = record.get("user_verified");
                String date = record.get("date");
                String text = record.get("text");
                String hashtags = record.get("hashtags");
                String source = record.get("source");
                String is_retweet = record.get("is_retweet");

                System.out.println("id " + id);
                System.out.println("nombre " + user_name);
                System.out.println(user_location);
                System.out.println(user_description);
                System.out.println(user_created);
                System.out.println(user_followers);
                System.out.println(user_friends);
                System.out.println(user_favourites);
                System.out.println(user_verified);
                System.out.println(text);
                System.out.println(hashtags);
                System.out.println(source);
                System.out.println(is_retweet);
                System.out.println("-------------------------");

                if (id != null){
                    User newUser = new User(parseLong(id), user_name);
                    listaUsuariosRegistrados.insert(parseLong(id), newUser);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
