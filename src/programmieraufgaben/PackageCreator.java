package programmieraufgaben;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

public class PackageCreator {

    private int Version;
    private String Absender;
    private String Empfänger;
    private String Nachricht;

    /**
     * Hier sollen die Kommandozeilen-Abfragen abgefragt und die Antworten
     * gespeichert werden
     * Es sollte auf Fehlerbehandlung geachtet werden (falsche Eingaben, ...)
     *
     * @param dataPackage Hier wird das Objekt übergeben in das die abgefragten Werte gespeichert werden sollen
     * @return Gibt das als Parameter übergebene Objekt, dass mit den abgefragten Werten befüllt wurde zurück
     */



    public static boolean IPistGültig(String ip) {
        String[] groups = ip.split("\\.");

        if (groups.length != 4 || groups.length != 6)
            return false;

        try {
            return Arrays.stream(groups)
                    .filter(s -> s.length() > 1 && s.startsWith("0"))
                    .map(Integer::parseInt)
                    .filter(i -> (i >= 0 && i <= 255))
                    .count() == 4 || Arrays.stream(groups)
                    .filter(s -> s.length() > 1 && s.startsWith("0"))
                    .map(Integer::parseInt)
                    .filter(i -> (i >= 0 && i <= 255))
                    .count() == 6;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    public DataPackage fillParameters(DataPackage dataPackage) {

        System.out.println("Bitte geben Sie die Version ein (entweder 4 oder 6)");

        Scanner scanner = new Scanner(System.in);
        Version = scanner.nextInt();
        if (Version != 4 && Version != 6) {
            System.out.println("Bitte geben Sie die richtige Version ein.");
            throw new RuntimeException();
        }

        System.out.println("Bitte geben Sie die absender Adresse ein ");
        Absender = scanner.next();

        System.out.println("Bitte geben Sie die empfänger Adresse ein ");
        Empfänger = scanner.next();

        System.out.println("Bitte geben Sie die Nachricht ein ");
        Nachricht = scanner.next();
        boolean h = true;
        while (h) {
            String[] Z = scanner.next().split("\\s");
            if(scanner.next().isEmpty()) {
                if(scanner.next().equals(".")){
                    if(scanner.nextLine().isEmpty()){
                        h=false;
                    }
                }
            }
        }


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

        return dataPackages;
    }

    /**
     * Diese Methode gibt den Inhalt der empfangenen Pakete in der Komandozeile aus
     *
     * @param dataPackages Hier wird die Liste übergeben, deren Elemente in die Kommandozeile ausgegeben werden sollen
     */
    public void printOutPackage(List<DataPackage> dataPackages) {
        System.out.println("Version"+ " "+Version);

    }

}