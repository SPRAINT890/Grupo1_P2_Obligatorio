public class prueba {
    public static void main(String[] args) {
        String stringArray = "['F1', 'Alonso', 'RoboFia', 'Redbull', 'Mundial']";
        String[] stringElements = stringArray.substring(2, stringArray.length() - 2).split("', '");
        for (int i = 0; i < stringElements.length; i++) {
            System.out.println(stringElements);
        }
    }
}
