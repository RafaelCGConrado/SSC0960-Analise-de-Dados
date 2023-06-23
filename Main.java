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
            
            Country[] maioresActive = new Country[n[1]];
            maioresActive[0]._active = -1;
            maioresActive[0]._death = -1;
            
            Country[] maioresConfirmed = new Country[n[3]];
            maioresConfirmed[0]._confirmed = -1;

            while(le_arquivo.hasNextLine()){
                String linha = le_arquivo.nextLine();
                Country c = new Country(linha);
                c.print();

                //1)
                if(c._confirmed >= n[0]) soma += c._active;
                
                //2)
                if (maioresActive.length < n[1]){
                    //escreve na primeira posicao livre
                }
                else if (c._active > maioresActive[0]._active){
                    //escrever na primeira posicao
                    maioresActive[0] = c;
                }

                //3)
                if (maioresConfirmed.length < n[3]) {
                    //escreve na primeira posicao livre
                }
                else if (c._confirmed > maioresConfirmed[0]._confirmed) {
                    //escrever na primeira posicao
                    maioresConfirmed[0] = c;
                }
                
                // Sort maioresActive por "Active" ...

                // Sort maioresConfirmed por "Confirmed" ...
                
            }
            // 1000000 10 4 2
            // 32454556
            System.out.println(soma);

            // 2)
            // Sort maioresActive por "Confirmed" ...
            // Printar os "death" dos n3 países com menor confirmed ...

            // 3)
            // Sort maioresConfirmd por "country" ...
            // Printar maioresConfirmed ...

            le_arquivo.close();
        }

        catch (FileNotFoundException erro){
            System.out.println("Nao abriu o arquivo");


        }
        
        input.close();
    }

    






}