package loop;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

    /*
     * Trabalho 2 de Programação Funcional - Versão com laço (sem Stream)
     *  Alunos:
     *  João Pedro Buzzo Silva - 10425191
     *  Rafael Comitre Garcia Conrado - 13671806
     *  Victor Hugo Trigolo Amaral - 12688487
     */



    
    class Main{

    //Classe utilizada para representar cada país do arquivo de dados
    public static class Country{
        String _country;
        int _confirmed;
        int _death;
        int _recovery;
        int _active;
    
        //Construtor da classe do país:
        public Country(String args){
            String[] retorno_split = args.split(",");
            this._country = retorno_split[0];
            this._confirmed = Integer.parseInt(retorno_split[1]);
            this._death = Integer.parseInt(retorno_split[2]);
            this._recovery = Integer.parseInt(retorno_split[3]);
            this._active = Integer.parseInt(retorno_split[4]);
                
        }
        
        //Método usado para printar cada atributo do país:
        public void print(){
            System.out.println(this._country + ", "
            + Integer.toString(this._confirmed) + ", "
            + Integer.toString(this._death) + ", "
            + Integer.toString(this._recovery) + ", "
            + Integer.toString(this._active));
        }
    }

    //Método da classe Country utilizado para ordenar um vetor de países conforme um campo:
    public static Country[] sort(Country[] arr, int size, String type){

        for (int i = 1; i < size; i++){
            int j = i-1;
            
            //Utilizamos um switch case para definir qual campo
            //de cada país seria usado na ordenação.
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

        return arr;
    }
    
    //Função main:
    public static void main(String[] args){
        // Objeto para input do terminal:
        Scanner input = new Scanner(System.in);
        
        //Leitura dos 4 N inteiros:
        int[] n = new int[4];
        for(int i = 0; i < 4; i++){
            n[i] = input.nextInt();
        }

        
        //Objeto para guardar o arquivo CSV:
        File arq = new File("dados.csv");
        try{
            //Objeto para ler o arquivo CSV:
            Scanner le_arquivo = new Scanner(arq);

            //Variável que será utilizada na funcionalidade 1)
            int soma = 0; 
            
            //Aloca o vetor para os países com maiores valores de 'active':
            int activeCount = 0;
            Country[] maioresActive = new Country[n[1]];
            if(n[1] != 0) maioresActive[0] = new Country("NULO,-1,-1,-1,-1");
            
            //Aloca o vetor para os países com maiores valores de 'confirmed':
            int confirmedCount = 0;
            Country[] maioresConfirmed = new Country[n[3]];
            if(n[3] != 0) maioresConfirmed[0] = new Country("NULO,-1,-1,-1,-1");

            //Loop de leitura do CSV 
            while(le_arquivo.hasNextLine()){
                String linha = le_arquivo.nextLine();
                Country c = new Country(linha);
               

                //Funcionalidade 1)
                if(c._confirmed >= n[0]) soma += c._active;
                
                //Funcionalidade 2)
                if (activeCount < n[1]){
                    //Escreve na primeira posicao livre
                    maioresActive[activeCount] = c;
                    activeCount++;

                }
                else if (c._active > maioresActive[0]._active){
                    //Escrever na primeira posicao
                    maioresActive[0] = c;
                    
                }

                // Funcionalidade 3)
                if (confirmedCount < n[3]) {
                    //Escreve na primeira posicao livre
                    maioresConfirmed[confirmedCount] = c;
                    confirmedCount++;
                }
                else if (n[3] != 0 && c._confirmed > maioresConfirmed[0]._confirmed) {
                    //Escrever na primeira posicao
                    maioresConfirmed[0] = c;
                }
                
                // Sort maioresActive por "Active" ...
                maioresActive = sort(maioresActive, activeCount, "Active");

                //Sort maioresConfirmed por "Confirmed"
                maioresConfirmed = sort(maioresConfirmed, confirmedCount, "Confirmed");
                
            }

            // Funcionalidade 1)
            System.out.println(soma);

            // Funcionalidade 2)
            // Sort maioresActive por "Confirmed"
            // Printa os "death" dos n3 países com menor confirmed ...
            maioresActive = sort(maioresActive, n[1], "Confirmed");

            if(n[2] > n[1]) n[2] = n[1];
            for(int i = 0; i < n[2]; i++) System.out.println(maioresActive[i]._death);
            

            // Funcionalidade 3)
            // Sort maioresConfirmd por "country"
            // Printa maioresConfirmed
            maioresConfirmed = sort(maioresConfirmed, n[3], "Country");
            for(int i = 0; i < n[3]; i++) System.out.println(maioresConfirmed[i]._country);

            le_arquivo.close();
        }

        catch (FileNotFoundException erro){
            System.out.println("Falha no processamento do arquivo");

        }
        
        input.close();
    }

    






}