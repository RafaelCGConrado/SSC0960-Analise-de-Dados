import java.util.*;
import java.util.function.*;
import java.io.*;

class Main{


    public static void main(String[] args){

        Scanner input = new Scanner(System.in);
        int n1, n2, n3, n4;
        
        n1 = input.nextInt();
        n2 = input.nextInt();
        n3 = input.nextInt();
        n4 = input.nextInt();


        File arq = new File("dados.csv");
        try {
            Scanner le_arquivo = new Scanner(arq);
            
            if(le_arquivo.hasNextLine()){
                le_arquivo.nextLine();
            }

            // List<String> inputList = new ArrayList<String>();

            // InputStream inputFS = new FileInputStream(arq);

            // BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));

            // inputList = br.lines().collect(Collectors.toList());

            le_arquivo.close();
        }

        

        catch(FileNotFoundException e1){
            System.out.println("Falha no processamento do arquivo");
        }

    



    }


}