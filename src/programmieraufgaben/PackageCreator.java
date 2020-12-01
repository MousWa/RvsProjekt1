package programmieraufgaben;


import java.util.*;


public class PackageCreator {

    private int Version; //Version Attribut
    private String Absender;//Absender Attribut
    private String Empfänger;//Empfänger Attribut
    private String Nachricht;//Nachricht Attribut für die bearbeitung des Text
    private String[] Nachricht2;//Nachricht Attribut , dort wird die ganze Text ohne bearbeitung gespeichert
    private String[] strings;// strings Array , hier wird die text per linien gespeichert
    private String[] splited;// strings Array , hier wird die text per Wörter zerlegt und gespeichert
    private int DatenTeillang;//hier wird die DatenTeillang gespeichert.

    /**
     * Hier sollen die Kommandozeilen-Abfragen abgefragt und die Antworten
     * gespeichert werden
     * Es sollte auf Fehlerbehandlung geachtet werden (falsche Eingaben, ...)
     *
     * @param dataPackage Hier wird das Objekt übergeben in das die abgefragten Werte gespeichert werden sollen
     * @return Gibt das als Parameter übergebene Objekt, dass mit den abgefragten Werten befüllt wurde zurück
     */
    public DataPackage fillParameters(DataPackage dataPackage) {
        /**
         * Scanner intialisiren , hier wird die Eingabe von User gelesen.
         * */
        Scanner scanner = new Scanner(System.in).useDelimiter("\\n");

        System.out.println("Bitte geben Sie die Version ein (entweder 4 oder 6)");
        /**
         * hier speichern wir die Version eingabe in Version Attribut
         * */

        Version = scanner.nextInt();
        /**
         * hier prüfen wir ob das Version weder 4 noch 6 ist .
         * */
        if (Version != 4 && Version != 6) {
            System.out.println("Bitte geben Sie eine richtige Version ein.");
            throw new RuntimeException();
        }

        System.out.println("Bitte geben Sie die absender Adresse ein ");
        /**
         * hier speichern wir die Absender eingabe in Absender Attribut
         * */
        Absender = scanner.next();

        System.out.println("Bitte geben Sie die empfänger Adresse ein ");
        /**
         * hier speichern wir die Empfänger eingabe in Empfänger Attribut
         * */
        Empfänger = scanner.next();

        System.out.println("Bitte geben Sie Ihre Nachricht ein");

        /**
         * hier haben wir eine Liste initialisiert , damit wir die Eingabe per Linien speichern (also jedes Linie wird in eine separate stelle gespeicher)
         * */
        List<String> LinesString = new ArrayList<>();
        /**
         * hier wird die eingabe als Text mit Linien genommen , und mit Abbruchbedingung überprüft.
         * */
        while (scanner.hasNext()) {
            Nachricht = scanner.next();
            LinesString.add(Nachricht);
            if (Nachricht.equalsIgnoreCase(".")) {
                break;
            }
        }
        /**
         * hier fügen wir die Liste Elemente in strings Array ein , und trennen wir jedes element mit "\\n".
         * mit diese for schleife können wir die linien bestimmen und jedes linie mit "\\n" trennen
         * */
        strings = new String[LinesString.size()];
        for (int j = 0; j < strings.length-1; j++) {
            if(j==strings.length-2){
                strings[j] = LinesString.get(j);
            }
            else{
                strings[j] = LinesString.get(j)+"\\n";
            }

        }
        /**
         * hier fügen wir die wider alles Array Elemente in eine liste ein
         * mit diese liste vermeiden wir die "null" elemente des Array
         *
        **/
        List<String> values = new ArrayList<String>();
        for(String data: strings) {
            if(data != null) {
                values.add(data);
            }
        }
        /**
         * hier fügen wir die Liste Elemente in target Array ein und addieren wir " " nach jedes Element
         * */
        String[] target = values.toArray(new String[values.size()]);
        /**
         * hier addieren wir alle Array element zusammen , und speiceher die Ergebniss in einer String
         *
         * */
        StringJoiner joiner1 = new StringJoiner("");
        for (int i = 0; i < target.length ; i++) {
            joiner1.add(target[i]+" ");
        }
        String str1 = joiner1.toString();

        /**
         * hier trennen wir jedes element wenn "\\n" erkennt.
         **/
        strings = str1.split("(?=\\n)|(?<=\\n)");
        System.out.println(Arrays.toString(strings));

        Nachricht2 = strings;

        /**
         * addieren wir alle Array elemnt zusammen und wenn die stelle ein "\\n"ist addieren wir nach "\\n" ein " ", damit wir das später zerlegen zu können.
         **/
        StringJoiner joiner = new StringJoiner("");
        for (int i = 0; i < strings.length ; i++) {
            if(strings[i]!="\\n"){
                joiner.add(strings[i]);
            }
            else {
                joiner.add(strings[i]+" ");
            }

        }
        String str = joiner.toString();
        /**
         * hier wird die Text getrennt durch reguläre ausdruck, damit betrachten wir jedes wort , wenn " " erkkent . und betrachten wir auch die Bindestrich/Schrägstrich
         * sowie "!" und ","zeichen.
         * jedes element oder zeichen wird alleine getrennt in die Array gespeichert
         *
         * dises ist die letztes Array ,die wir später wie wir wollen trennen können .
         * */
        splited = str.split("(?=-)|(?<=-)|(?=\\s)|(?<=\\s)|\\n|(?=/)|(?<=/)|(?=!)|(?<=!)|(?<=,)");
        return dataPackage;
    }

    /**
     * Aus dem als Parameter übergebenen Paket sollen die Informationen
     * ausgelesen und in einzelne Datenpakete aufgeteilt werden
     *
     * @param dataPackage Hier wird das Objekt übergeben in das das Resultat gespeichert werden soll
     * @return Gibt das als Parameter übergebene Objekt mit den aufgeteiltet Datenpaketen zurück
     */
    public List<DataPackage> splitPackage(DataPackage dataPackage) {

        List<DataPackage> dataPackages = new LinkedList<>();
        /**
         * hier speicher wir die Datenteillang in Datenteillang Attribut
         * */
        DatenTeillang = dataPackage.getDataPackageLength();
        /**
         * hier Initialisieren wir result Array , hier wird die letzte Ergenbniss gespeichert
         * */
        String[] result = new String[splited.length];
        /**
         * mit diese for schleife prüfen wir ob ein stelle des Array länger ist als die ausgewählte datenteil , wenn nicht , dann prüfen wir die nächste stelle
         * und addieren wir die beide stelle zusammen ,und so weiter und so fort.
         * */
        int count = 0;
        for (int i = 0; i < splited.length; i++) {
            String s = "";
            for (int j = i + 1; j < splited.length; j++) {
                if (splited[i].length() + splited[j].length() <= dataPackage.getDataPackageLength()) {
                    String e = splited[j];
                    s = splited[i] + e;
                    result[count] = s;
                    i++;
                    splited[i] = result[count];
                } else {
                    result[count] = splited[i];
                    break;
                }
            }
            count++;
        }
        /**
         * hier addieren wir die letzte stelle des altes array , in letzt stellen des neue result Array,
         * */
        result[result.length - 1] = splited[splited.length - 1];
        /**
         * hier definieren wir ein liste  , und fügen wir die result array elemnte in die liste
         * */
        List<String> values = new ArrayList<String>();
        for (String data : result) {
            if (data != null) {
                values.add(data);
            }
        }
        /**
         * hier prüfen und lösen wir die "null" stelle
         * */
        String[] target = values.toArray(new String[values.size()]);

        if (target[target.length - 2] == target[target.length - 1]) {
            target[target.length - 2] = target[target.length - 1];
            target[target.length - 1] = null;
        }

        List<String> newvalues = new ArrayList<String>();
        for (String data : target) {
            if (data != null) {
                newvalues.add(data);
            }
        }
        String[] newresult = newvalues.toArray(new String[newvalues.size()]);
        strings = newresult;

        return dataPackages;
    }

    /**
     * Diese Methode gibt den Inhalt der empfangenen Pakete in der Komandozeile aus
     *
     * @param dataPackages Hier wird die Liste übergeben, deren Elemente in die Kommandozeile ausgegeben werden sollen
     */

    public void printOutPackage(List<DataPackage> dataPackages) {
        boolean t = true;
        if(strings[0]==" "||strings[strings.length-1]==" ")
        {
            System.out.println("Die Nachricht darf keine Leerzeichen am Zeilenanfang oder am Zeilenende enthalten.\nBitte geben Sie gültige eingabe ein.");
        }

        else if(t){

            for (int i = 0 ; i<strings.length;i++){
                if (strings[i].length()>DatenTeillang+2){
                    System.out.println("Die Nachricht kann nicht versendet werden, da sie ein Wort ("+strings[i]+")mit Länge :"+strings[i].length()+" > "+DatenTeillang+" enthält.");
                    t=false;
                    break;
                }
                else if(i==strings.length-1){
                    t=false;
                    break;
                }
            }
        }
        else
            System.out.println("Die maximale Datenteil-länge ist " + DatenTeillang + ".");
            System.out.println("\n");
            System.out.println("Version : " + Version);
            System.out.println("Absender : " + Absender);
            System.out.println("Empfänger : " + Empfänger);
            System.out.println("Nachricht : ");
            Arrays.stream(Nachricht2).forEach(System.out::println);
            System.out.println("\n");
            System.out.println("Es sind " + strings.length + " Datenpakete notwendig.");
            System.out.println("\n");
            int aufruf = 1;
            int Anzahl = 0;

            for (int x = 0; x < strings.length; x++) {
                System.out.println("Version : " + Version);
                System.out.println("Absender : " + Absender);
                System.out.println("Empfänger : " + Empfänger);
                System.out.println("Paketlaufnummer: " + aufruf);
                System.out.println("Datenteil-Länge : " + strings[x].length());
                System.out.println("Datenteil: " + strings[x]);
                aufruf++;
                System.out.println("\n");
            }

    }

}
