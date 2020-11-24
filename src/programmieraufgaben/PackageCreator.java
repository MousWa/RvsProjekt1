package programmieraufgaben;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;
import programmieraufgaben.DataPackage;

public class PackageCreator {

    private int Version;
    private String Absender;
    private String Empfänger;
    private String Nachricht;
    private String []strings;

    /**
     * Hier sollen die Kommandozeilen-Abfragen abgefragt und die Antworten
     * gespeichert werden
     * Es sollte auf Fehlerbehandlung geachtet werden (falsche Eingaben, ...)
     *
     *
     * @param dataPackage Hier wird das Objekt übergeben in das die abgefragten Werte gespeichert werden sollen
     * @return Gibt das als Parameter übergebene Objekt, dass mit den abgefragten Werten befüllt wurde zurück
     */
    public DataPackage fillParameters(DataPackage dataPackage) {
        System.out.println("Bitte geben Sie die Version ein (entweder 4 oder 6)");
        Scanner scanner = new Scanner(System.in);
        Version = scanner.nextInt();
        if (Version != 4 && Version != 6) { System.out.println("Bitte geben Sie die richtige Version ein.");throw new RuntimeException(); }

        System.out.println("Bitte geben Sie die absender Adresse ein ");
        scanner.next();
        Absender = scanner.toString();

        System.out.println("Bitte geben Sie die empfänger Adresse ein ");
        scanner.next();
        Empfänger=scanner.toString();
        System.out.println("nachricht geben");

        scanner.next();
        boolean nextLine = true;
        while (nextLine){
            strings =scanner.next().split("");
            if (strings[strings.length-1].equals(".")){
                nextLine =false;
            }

        }
        System.out.println(strings);

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
        int result =0;
        int count=0;
        int start =0;
        DataPackage X= new DataPackage(result);
        for (int i=0 ;i<strings.length;i++) {
            if (count <= dataPackage.getDataPackageLength()) {
                if (!strings[i].equals(" ") && !strings[i].equals("\n") && !strings[i].equals("-") && !strings[i].equals("")) {
                    count++;
                } else if (strings[i].equals(" ")) {
                    count++;
                    result = count - 1;
                    start = count + 1;
                } else if (strings[i].equals("\n")) {
                    if (count + 2 <= dataPackage.getDataPackageLength()) {
                        count++;
                        count++;
                        result=   count;
                        start=count;

                    }
                    else {
                        result=count-1;
                        start=count+2;
                        count+=2;
                    }

                }
                else if (strings[i].equals("-")||strings[i].equals("/")){
                    if (count +1 <= dataPackage.getDataPackageLength()) {
                        count++;
                        result=   count;
                        start=count;

                    }
                    else {
                        count++;
                        result=count-1;
                        start=count;

                    }

                }
            }
            else {
                if (result==0)
                {
                    System.out.println("bitte geben sir richtige eingabe");
                    throw new RuntimeException();
                }
                i=start;
                X.setDataPackageLength(result);
                dataPackages.add(X);
                result=0;
                count=0;
            }
        }
        return dataPackages;
    }

    /**
     * Diese Methode gibt den Inhalt der empfangenen Pakete in der Komandozeile aus
     *
     * @param dataPackages Hier wird die Liste übergeben, deren Elemente in die Kommandozeile ausgegeben werden sollen
     */
    public void printOutPackage(List<DataPackage> dataPackages) {
        System.out.println("Es sind "+dataPackages.size()+1+" Datenpakete notwendig.");
        int aufruf =1;
        int Anzahl=0;
        for (int x =0;x<dataPackages.size();x++){
            System.out.println("Version : "+Version);
            System.out.println("Absender : "+ Absender);
            System.out.println("Empfänger : "+Empfänger);
            System.out.println("Paketlaufnummer +1 "+ aufruf);
            System.out.println("Datenteil-Länge : "+ dataPackages.get(x).getDataPackageLength());
            for (int i= Anzahl;i<= dataPackages.get(x).getDataPackageLength()+Anzahl;i++){
                System.out.println(strings[i]);

            }
            Anzahl+= dataPackages.get(x).getDataPackageLength();


            aufruf++;
        }

    }

}
