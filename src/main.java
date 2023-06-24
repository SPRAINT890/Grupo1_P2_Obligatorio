import Entities.Driver;
import Entities.HashTag;
import Entities.Tweet;
import Entities.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import uy.edu.um.prog2.adt.MyHash.HashTableCerradoImpl;
import uy.edu.um.prog2.adt.MyHeap.HeapImpl;
import uy.edu.um.prog2.adt.MyMergeSort.MergeSortImpl;
import uy.edu.um.prog2.adt.Nodos.NodeHash;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;
public class main {
    static final String CSV = "f1_dataset.csv";
    static Long idUser = Long.valueOf(0);
    static Long idHastag = Long.valueOf(0);
    static Driver[] pilotosActivos = new Driver[20];
    static HashTableCerradoImpl<String, User> usuariosRegistrados = new HashTableCerradoImpl<>(10000);
    static HashTableCerradoImpl<Long, Tweet> tweetsRegistrados = new HashTableCerradoImpl<>(10000);
    public static void tenDriversOf(int mes, int año) throws IOException, InterruptedException {
        loadDrivers();
        //recorro la lista de tweets registrados
        for (NodeHash<Long, Tweet> tweet : tweetsRegistrados.getList()){
            if (tweet == null || tweet.getValue().getYear() != año || tweet.getValue().getMonth() != mes){
                continue;
            }
            //si es un tweet valido, compruebo que piloto es mencionado
            for (Driver piloto : pilotosActivos){
                //si el tweet contiene el piloto le sumo 1 al contador
                if (tweet.getValue().getContent().toLowerCase().contains(piloto.getName().toLowerCase()) || tweet.getValue().getContent().toLowerCase().contains(piloto.getLastName().toLowerCase())){
                    piloto.setMenciones(piloto.getMenciones() + 1);
                }
            }
        }
        //ordeno los pilotos por un heap
        HeapImpl<Integer, String> pilotosOrdenados = new HeapImpl<>(30);
        for (Driver piloto : pilotosActivos){
            pilotosOrdenados.insert( piloto.getMenciones(), piloto.getName() + " " + piloto.getLastName());
        }
        //imprimo los primeros 10
        for (int i=1; i<=10; i++){
            System.out.println("Nº " + i + " cantidad de veces: " + pilotosOrdenados.getList()[0].getKey() + "  Piloto: " + pilotosOrdenados.extractMax());
        }
    }
    public static void fiftenUsersWithMoreTweets() throws InterruptedException {
        User[] ranking = new User[15];
        MergeSortImpl<User> sort = new MergeSortImpl<User>();
        int c = 0;
        for (NodeHash<String, User> usuario : usuariosRegistrados.getList()){
            if (usuario == null){
                continue;
            }
            if (c<15){
                ranking[c] = usuario.getValue();
                c++;
                continue;
            }
            sort.mergesort(ranking);
            if (ranking[0].compareTo(usuario.getValue()) < 0){
                ranking[0] = usuario.getValue();
            }
        }
        int i = 1;
        for (c-- ; c>0; c--){
            System.out.println("Nº " + i + " Cantidad de Tweets: " + ranking[c].getListTweets().getSize() + " Verificado: " + ranking[c].isVerified() + " Usuario: " + ranking[c].getName());
            i++;
        }
    }
    public static void amountOfDifferentsHastaghOnADay(String date){
        String[] dateSplit = date.split("-");
        Integer yearSel = Integer.parseInt(dateSplit[0]);
        Integer monthSel = Integer.parseInt(dateSplit[1]);
        Integer daySel = Integer.parseInt(dateSplit[2]);
        HashTableCerradoImpl<Long, HashTag> hashEncontrados = new HashTableCerradoImpl<>(100);
        for (NodeHash<Long, Tweet> tweet : tweetsRegistrados.getList()){
            if (tweet == null){
                continue;
            }
            if (tweet.getValue().getDay().equals(daySel) && tweet.getValue().getMonth().equals(monthSel) && tweet.getValue().getYear().equals(yearSel)){
                for (NodeHash<Long, HashTag> hashTag : tweet.getValue().getListHastag().getList()){
                    if (hashTag == null || hashEncontrados.search(hashTag.getValue().getId()) != null){
                        continue;
                    }
                    hashEncontrados.insert(hashTag.getValue().getId(), hashTag.getValue());
                }
            }
        }
        System.out.println("Para la fecha " + date + " se encontro: " + hashEncontrados.getSize());
    }
    public static void mostHashtagsUsedOnADay(String date){
        String[] dateSplit = date.split("-");
        Integer yearSel = Integer.parseInt(dateSplit[0]);
        Integer monthSel = Integer.parseInt(dateSplit[1]);
        Integer daySel = Integer.parseInt(dateSplit[2]);

        HashTableCerradoImpl<String, Integer> hashEncontrados = new HashTableCerradoImpl<>(100);

        for (NodeHash<Long, Tweet> tweet : tweetsRegistrados.getList()){
            if (tweet == null || tweet.getValue().getYear() != yearSel && tweet.getValue().getMonth() != monthSel && tweet.getValue().getDay() != daySel){
                continue;
            }
            for (NodeHash<Long, HashTag> hashTag : tweet.getValue().getListHastag().getList()){
                if (hashTag == null || hashTag.getValue().getText().toLowerCase().contains("f1")){
                    continue;
                }

                Integer isFound = hashEncontrados.search(hashTag.getValue().getText());
                if (isFound != null){
                    hashEncontrados.setValue(hashTag.getValue().getText(), isFound+1);
                }else {
                    hashEncontrados.insert(hashTag.getValue().getText(), 1);
                }
            }
        }

        Integer maxCantidad = 0;
        String maxName = "";

        for (NodeHash<String, Integer> hashtags : hashEncontrados.getList()){
            if (hashtags == null || hashtags.getValue() < maxCantidad){
                continue;
            }
            maxCantidad = hashtags.getValue();
            maxName = hashtags.getKey();
        }
        System.out.println("Hashtag mas usado el dia " + date + " es " + maxName + " Cantidad: " + maxCantidad);
    }
    public static void sevenMostLikedUsers(){
        HeapImpl<Integer, User> ranking = new HeapImpl<>(usuariosRegistrados.getSize());
        for (NodeHash<String, User> usuario : usuariosRegistrados.getList()){
            if (usuario == null){
                continue;
            }
            ranking.insert(usuario.getValue().getLike(), usuario.getValue());
        }
        for (int i = 1; i<8; i++){
            System.out.println("Nº " + i + " Nombre " + ranking.extractMax().getName() + " Cantidad de Likes " + ranking.getList()[0].getKey());
        }
    }
    public static void cantidadDeTweetsConUnaFrase(String frase){
        Long contador = 0L;
        for (NodeHash<Long, Tweet> tweet : tweetsRegistrados.getList()){
            if (tweet == null){
                continue;
            }
            if (tweet.getValue().getContent().toLowerCase().contains(frase)){
                contador++;
            }
        }
        System.out.println(contador);
    }
    public static void clear_console(){
        for (int i = 0; i<50; i++){
            System.out.println("");
        }
    }
    public static void loadDrivers(){
        //cargo los pilotos
        System.out.println("Cargando los pilotos...");
        String fileName = "drivers.txt"; // Ruta del archivo drivers
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int c = 0;
            while ((line = br.readLine()) != null) {
                String[] nombre = line.split(" ", 2);
                Driver newPiloto = new Driver(nombre[0], nombre[1]);
                pilotosActivos[c] = newPiloto;
                c++;
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    public static void load_csv(){
        System.out.println("Cargando los tweets...");
        HashTableCerradoImpl<String, HashTag> hastagRegistrados = new HashTableCerradoImpl<>(1000);
        try {
            Reader in = new FileReader(CSV);
            Iterable<CSVRecord> records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(in);
            for (CSVRecord record : records) {
                String id = record.get("");
                String user_name = record.get("user_name");
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
                    User newUser = new User(idUser, user_name, Boolean.valueOf(user_verified));
                    if (user_favourites.contains(".")){
                        newUser.setLike(Integer.parseInt(user_favourites.split("\\.")[0]));
                    }else {
                        newUser.setLike(Integer.valueOf(user_favourites));
                    }
                    User isFoundUser = usuariosRegistrados.search(user_name);
                    if (isFoundUser == null){
                        newUser.getListTweets().insert(Long.valueOf(id), newTweet);
                        usuariosRegistrados.insert(user_name, newUser);
                        idUser++;
                        newTweet.setUsuario(newUser);
                    }else {
                        if (user_favourites.contains(".")){
                            isFoundUser.setLike(Integer.parseInt(user_favourites.split("\\.")[0]));
                        }else {
                            isFoundUser.setLike(Integer.valueOf(user_favourites));
                        }
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
                            }else{
                                HashTag newHashtag = new HashTag(idHastag, hastagConverted[i]);
                                idHastag++;
                                newTweet.getListHastag().insert(newHashtag.getId(), newHashtag);
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
    public static void main(String[] args) throws InterruptedException, IOException {
        Scanner entrada = new Scanner(System.in);

        long tempInicio = System.currentTimeMillis();
        loadDrivers();
        System.out.println((double) ((System.currentTimeMillis() - tempInicio)/1000) +" segundos");
        clear_console();

        tempInicio = System.currentTimeMillis();
        load_csv();
        System.out.println((double) ((System.currentTimeMillis() - tempInicio)/1000) +" segundos");

        clear_console();
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
                break;
            }
            if (valor == 1){
                clear_console();
                System.out.println("Ingrese el MES en formato MM");
                int mes = entrada.nextInt();
                System.out.println("Ingrese el AÑO en formato YYYY");
                int año = entrada.nextInt();
                tempInicio = System.currentTimeMillis();
                tenDriversOf(mes, año);
                System.out.println((double) ((System.currentTimeMillis() - tempInicio)/1000) +" segundos");
                Thread.sleep(4000);
            }
            if (valor == 2){
                clear_console();
                tempInicio = System.currentTimeMillis();
                fiftenUsersWithMoreTweets();
                System.out.println((double) ((System.currentTimeMillis() - tempInicio)/1000) +" segundos");
                Thread.sleep(4000);
            }
            if (valor == 3){
                clear_console();
                System.out.println("Ingrese la fecha en formato YYYY-MM-DD");
                String date = entrada.next();
                tempInicio = System.currentTimeMillis();
                amountOfDifferentsHastaghOnADay(date);
                System.out.println((double) ((System.currentTimeMillis() - tempInicio)/1000) +" segundos");
                Thread.sleep(4000);
            }
            if (valor == 4){
                clear_console();
                System.out.println("Ingrese la fecha en formato YYYY-MM-DD");
                String date = entrada.next();
                tempInicio = System.currentTimeMillis();
                mostHashtagsUsedOnADay(date);
                System.out.println((double) ((System.currentTimeMillis() - tempInicio)/1000) +" segundos");
                Thread.sleep(4000);
            }
            if (valor == 5){
                clear_console();
                tempInicio = System.currentTimeMillis();
                sevenMostLikedUsers();
                System.out.println((double) ((System.currentTimeMillis() - tempInicio)/1000) +" segundos");
                Thread.sleep(4000);
            }
            if (valor == 6){
                clear_console();
                System.out.println("Ingrese la frase que desea buscar");
                String frase = entrada.next();
                tempInicio = System.currentTimeMillis();
                cantidadDeTweetsConUnaFrase(frase.toLowerCase());
                System.out.println((double) ((System.currentTimeMillis() - tempInicio)/1000) +" segundos");
                Thread.sleep(4000);
            }
        }
    }
}
