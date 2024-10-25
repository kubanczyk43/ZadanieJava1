import java.util.Scanner;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Gracz {
    
    private String nick ;
    
    public Gracz(String nick)
    {
        this.nick = nick;
    }
    ///metoda zapisywanie do pliku danych 
    public static void ZapisywanieDoPliku(String nick, int i) throws IOException {
    String nazwa = nick;
    File plik = new File("C:\\Users\\jakub\\Desktop\\"+nick+".txt");
    
    Pattern wzorzec = Pattern.compile("\\d+");
    
    
    if(plik.exists()) //sprawdzanie czy istnieje plik
    {
        try (BufferedReader odczyt = new BufferedReader(new FileReader(plik))) {
            String dane;
            while ((dane = odczyt.readLine()) != null) {
                Matcher matcher = wzorzec.matcher(dane);
                while (matcher.find()) {
                    String liczbaStr = matcher.group();
                    int wynikPlik = Integer.parseInt(liczbaStr); // Konwersja na int
                    if(wynikPlik<i)
                    {
                        try (
            
                            FileWriter fileWriter = new FileWriter("C:\\Users\\jakub\\Desktop\\"+nick+".txt")) {
                            fileWriter.write("nazwa gracza: " + nazwa + "\r\n Najlepszy wynik: " + wynikPlik );
                            System.out.println("Nie udało ci się pobić najlepszego wyniku");
                            } 
                        catch (IOException e) 
                        {
                            System.out.println("Wystąpił błąd.");
                            e.printStackTrace();
                        }
                    }
                    else if(wynikPlik>i)
                    {
                        try (
            
                            FileWriter fileWriter = new FileWriter("C:\\Users\\jakub\\Desktop\\"+nick+".txt")) {
                            fileWriter.write("nazwa gracza: " + nazwa + "\r\n Najlepszy wynik: " + i );
                            System.out.println("Pomyślnie zapisano gre z nowym najlepszym wynikiem.");
                            } 
                        catch (IOException e) 
                        {
                            System.out.println("Wystąpił błąd.");
                            e.printStackTrace();
                        }
                    }
                   
                }
            }
        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas odczytu pliku: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Błąd konwersji liczby: " + e.getMessage());
        }
        
        
    
    }
    else
    {
        
        plik.createNewFile(); ///tworzenie nowego pliku
        try (
            
            FileWriter fileWriter = new FileWriter("C:\\Users\\jakub\\Desktop\\"+nick+".txt")) {
        fileWriter.write("nazwa gracza: " + nazwa + "\r\n Najlepszy wynik: " + i );
        System.out.println("Pomyślnie zapisano gre.");
    } catch (IOException e) {
        System.out.println("Wystąpił błąd." + e.getMessage());
        e.printStackTrace();
    }
    }
    
    
}
    
    public static String odczytDanych(String nick) throws IOException
    {
        File plik = new File("C:\\Users\\jakub\\Desktop\\"+nick+".txt");
        String sciezka = "C:\\Users\\jakub\\Desktop\\"+nick+".txt";
        if(plik.exists())
        {
            try (BufferedReader odczyt = new BufferedReader(new FileReader(sciezka))) {
            String dane;
            while ((dane = odczyt.readLine()) != null) {
                System.out.println(dane); // Wyświetla odczytane dane nick oraz najlepszy wynik
            }
        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas odczytu pliku: " + e.getMessage());
        }
        
        }
        return "";
    }
    ///losowanie liczby oraz zgadywanie
    public static int Gra(String nick) throws IOException
    {
        
        System.out.println("Zgadnij jaką wylosowało liczbę");
        Scanner scan = new Scanner(System.in);
        Random liczbyLosowe = new Random();
        int liczba = liczbyLosowe.nextInt(101);
        System.out.println(liczba);
        for(int i=1; i<101;i++)
        {
           
            String liczbaUzytkownika  = scan.nextLine();
            int liczbaUz = Integer.valueOf(liczbaUzytkownika);
            if(liczbaUz == liczba)
            {
                ZapisywanieDoPliku(nick,i);
                System.out.println("Chcesz zagrać ponownie (tak/nie) ");
                String odpowiedz = scan.next();
                if (odpowiedz.equalsIgnoreCase("tak")) {
                    Gra(nick);
                }
                else if(odpowiedz.equalsIgnoreCase("nie"))
                {
                    System.out.println("Dziękujemy za grę!");
                    scan.close();
                    break;
                }
                
            }
            else
            {
                 System.out.println("Próbuj dalej");
                 
            }
        }
        
        
    }
    
    public static void main(String[] args) throws IOException
    {
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj swój nickname: ");
        String nick = scan.nextLine();
        System.out.println("Twój nickname: " + nick );
        System.out.println(odczytDanych(nick));
        System.out.println(Gra(nick));
    }
    
}
