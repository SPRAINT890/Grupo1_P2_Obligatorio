import Entities.HashTag;
import Entities.Tweet;
import Entities.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import uy.edu.um.prog2.adt.MyHash.HashTableCerradoImpl;
import uy.edu.um.prog2.adt.MyHeap.HeapImpl;
import uy.edu.um.prog2.adt.Nodos.NodeHash;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;


public class main {
    static Long idUser = Long.valueOf(0);
    static Long idHastag = Long.valueOf(0);
    static HashTableCerradoImpl<String, User> usuariosRegistrados = new HashTableCerradoImpl<>(1000000);
    static HashTableCerradoImpl<String, HashTag> hastagRegistrados = new HashTableCerradoImpl<>(1000000);
    static HashTableCerradoImpl<Long, Tweet> tweetsRegistrados = new HashTableCerradoImpl<>(1000000);
    public static void topDriversOf(int mes, int año) throws IOException, InterruptedException {
        NodeHash<Long, Tweet>[] listTweets = tweetsRegistrados.getList();
        HashTableCerradoImpl<String, Integer> pilotos = new HashTableCerradoImpl<>(30);

        String fileName = "drivers.txt"; // Ruta del archivo
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                pilotos.insert(line, 0);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        for (NodeHash<Long, Tweet> tweet : listTweets){
            if (tweet == null){
                continue;
            }
            if (tweet.getValue().getYear() != año || tweet.getValue().getMes() != mes){
                continue;
            }
            for (NodeHash<String, Integer> piloto : pilotos.getList()){
                if (piloto == null){
                    continue;
                }
                if (tweet.getValue().getContent().contains(piloto.getKey())){
                    piloto.setValue(piloto.getValue() + 1);
                }
            }
        }
        HeapImpl<Integer, String> pilotosOrdenados = new HeapImpl<>(30);
        for (NodeHash<String, Integer> piloto : pilotos.getList()){
            if (piloto == null){
                continue;
            }
            pilotosOrdenados.insert(piloto.getValue(), piloto.getKey());
        }
        for (int i=0; i<10; i++){
            System.out.println("Cantidad de veces: " + pilotosOrdenados.getList()[0].getKey() + "  Piloto: " + pilotosOrdenados.extractMax());
        }
        Thread.sleep(4000);
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
            Reader in = new FileReader("f1_dataset.csv");
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

                try {
                    //cargo el tweet sin usuario ni hashtag
                    Tweet newTweet = new Tweet(Long.valueOf(id), text, source, Boolean.valueOf(is_retweet), Integer.valueOf(date.substring(0, 4)), Integer.valueOf(date.substring(5, 7)), Integer.valueOf(date.substring(8, 10)));
                    tweetsRegistrados.insert(Long.valueOf(id), newTweet);

                    //verifico si el usuario existe y le agrego el tweet
                    User newUser = new User(idUser, user_name);
                    User isFoundUser = usuariosRegistrados.search(user_name);
                    if (isFoundUser == null){
                        newUser.getListTweets().insert(Long.valueOf(id), newTweet);
                        usuariosRegistrados.insert(user_name, newUser);
                        idUser++;
                        newTweet.setUsuario(newUser);
                    }else {
                        isFoundUser.getListTweets().insert(Long.valueOf(id), newTweet);
                        newTweet.setUsuario(isFoundUser);
                    }

                    //verifico si el hashtag existe
                    if (hashtags.contains("[")){
                        String[] hastagConverted = hashtags.substring(2, hashtags.length() - 2).split("', '");
                        for (int i = 0; i<hastagConverted.length; i++){
                            HashTag isFoundHashtag = hastagRegistrados.search(hastagConverted[i]);
                            if (isFoundHashtag != null){
                                newTweet.getListHastag().insert(isFoundHashtag.getId(), isFoundHashtag);
                                isFoundHashtag.getListTweetUsed().insert(newTweet.getId(), newTweet);
                            }else{
                                HashTag newHashtag = new HashTag(idHastag, hastagConverted[i]);
                                idHastag++;
                                newTweet.getListHastag().insert(newHashtag.getId(), newHashtag);
                                newHashtag.getListTweetUsed().insert(newTweet.getId(), newTweet);
                                hastagRegistrados.insert(newHashtag.getText(), newHashtag);
                            }
                        }
                    }
                }catch (NumberFormatException el){
                    continue;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void loadHashtag(String hashtags){
        String[] hastagConverted = hashtags.substring(2, hashtags.length() - 2).split("', '");
        for (int i = 0; i<hastagConverted.length; i++){
            HashTag isFound = hastagRegistrados.search(hastagConverted[i]);
            if (isFound != null){

            }
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
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
                System.out.println("Ingrese el MES en formato MM");
                int mes = entrada.nextInt();
                System.out.println("Ingrese el AÑO en formato YYYY");
                int año = entrada.nextInt();
                topDriversOf(mes, año);
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
