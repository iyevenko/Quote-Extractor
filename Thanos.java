
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Thanos {
    public static void main(String[] args)throws Exception {
        String fileName = "C:\\Users\\Chiefky\\Documents\\Programming\\Thanos\\thanosReal.txt";
        String name = "Thanos"; // character name
        try (Stream<String> stream = Files.lines(Paths.get(fileName))){
            List<String> lines = stream.collect(Collectors.toList());
            //List<String> quotes = new ArrayList<>();

            PrintWriter writer = new PrintWriter("C:\\Users\\Chiefky\\Documents\\Programming\\Thanos\\thanosOut.txt", "UTF-16");

            boolean reading = false;
            boolean newLine = true;
            String thisQuote = "";
            String afterPunc = "";

            for (int i = 0; i < lines.size(); i++){
                for (String s : lines.get(i).split(" ")) {
                    if (reading == true){
                        if (s.endsWith(":") && newLine){
                            reading = false;
                            //quotes.add(thisQuote);
                            System.out.println(thisQuote);
                            writer.println(thisQuote);
                            thisQuote = "";
                            afterPunc = "";
                        }else if (s.endsWith("]") || s.endsWith(".") || s.endsWith("?") || s.endsWith("!")){
                            thisQuote += afterPunc + s + " ";
                            afterPunc = "";
                        }else if (!s.equals("")){
                            afterPunc += s + " ";
                        }
                    }else if (s.equals(name +":")){
                        thisQuote += s + " ";
                        reading = true;
                        newLine = false;
                    }
                }
                newLine = true;
            }
            //quotes.add(thisQuote);
            writer.println(thisQuote);
            System.out.println(thisQuote);
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
