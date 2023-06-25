import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main{
    public static class Country{
        String _country;
        int _confirmed;
        int _death;
        int _recovery;
        int _active;

        public Country(String args){
            String[] retorno_split = args.split(",");
            this._country = retorno_split[0];
            this._confirmed = Integer.parseInt(retorno_split[1]);
            this._death = Integer.parseInt(retorno_split[2]);
            this._recovery = Integer.parseInt(retorno_split[3]);
            this._active = Integer.parseInt(retorno_split[4]);
                
        }
        
        public void print(){
            System.out.println(this._country + ", "
            + Integer.toString(this._confirmed) + ", "
            + Integer.toString(this._death) + ", "
            + Integer.toString(this._recovery) + ", "
            + Integer.toString(this._active));
        }
    }

    public static Country[] sort(Country[] arr, int size, String type){

        for (int i = 1; i < size; i++){
            int j = i-1;
            
        
            switch(type){
                case "Country":
                    while (j >= 0 && arr[j]._country.compareTo(arr[j+1]._country) > 0){
                        Country temp = arr[j + 1];
                        arr[j + 1] = arr[j];
                        arr[j] = temp;
                        j -= 1;
                    }
                    break;

                case "Confirmed":
                    while (j >= 0 && arr[j + 1]._confirmed < arr[j]._confirmed){
                        Country temp = arr[j + 1];
                        arr[j + 1] = arr[j];
                        arr[j] = temp;
                        j -= 1;
                    }
                    break;
                    
                case "Active":
                    while (j >= 0 && arr[j + 1]._active < arr[j]._active){
                        Country temp = arr[j + 1];
                        arr[j + 1] = arr[j];
                        arr[j] = temp;
                        j -= 1;
                    }
                
                    break;
            }
            
        }
        // for(int i = 0; i < size; i++) arr[i].print();
        return arr;
    }
    
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        
        //Leitura dos 4 N inteiros
        int[] n = new int[4];
        for(int i = 0; i < 4; i++){
            n[i] = input.nextInt();

        }

        // 1) int soma
        // 2) paises vecN2[n2], vecN3[n3]
        // 3) paises vecN4[n4]
     

        //As colunas do arquivo são: Country, Confirmed, Deaths, Recovery, Active.

        //String[] country = new String[];


        File arq = new File("dados.csv");
        try{
            Scanner le_arquivo = new Scanner(arq);
            int soma = 0;
            
            int activeCount = 0;
            Country[] maioresActive = new Country[n[1]];
            maioresActive[0] = new Country("NULO,-1,-1,-1,-1");
            
            int confirmedCount = 0;
            Country[] maioresConfirmed = new Country[n[3]];
            maioresConfirmed[0] = new Country("NULO,-1,-1,-1,-1");

            while(le_arquivo.hasNextLine()){
                String linha = le_arquivo.nextLine();
                Country c = new Country(linha);
                // c.print();

                //1)
                if(c._confirmed >= n[0]) soma += c._active;
                
                //2)
                if (activeCount < n[1]){
                    //escreve na primeira posicao livre
                    maioresActive[activeCount] = c;
                    activeCount++;

                }
                else if (c._active > maioresActive[0]._active){
                    //escrever na primeira posicao
                    maioresActive[0] = c;
                    


                }

                //3)
                if (confirmedCount < n[3]) {
                    //escreve na primeira posicao livre
                    maioresConfirmed[confirmedCount] = c;
                    confirmedCount++;
                }
                else if (c._confirmed > maioresConfirmed[0]._confirmed) {
                    //escrever na primeira posicao
                    maioresConfirmed[0] = c;
                }
                
                // Sort maioresActive por "Active" ...
                maioresActive = sort(maioresActive, activeCount, "Active");
                maioresConfirmed = sort(maioresConfirmed, confirmedCount, "Confirmed");
                

                // Sort maioresConfirmed por "Confirmed" ...
                
            }

            // for(int i = 0; i < n[1]; i++) maioresActive[i].print();
            // 1000000 10 4 2
            // 32454556
            System.out.println(soma);

            // 2)
            // Sort maioresActive por "Confirmed" ...
            // Printar os "death" dos n3 países com menor confirmed ...
            maioresActive = sort(maioresActive, n[1], "Confirmed");
            for(int i = 0; i < n[2]; i++) System.out.println(maioresActive[i]._death);

            // 3)
            // Sort maioresConfirmd por "country" ...
            // Printar maioresConfirmed ...
            maioresConfirmed = sort(maioresConfirmed, n[3], "Country");
            for(int i = 0; i < n[3]; i++) maioresConfirmed[i].print();

            le_arquivo.close();
        }

        catch (FileNotFoundException erro){
            System.out.println("Falha no processamento do arquivo");


        }
        
        input.close();
    }

    






}