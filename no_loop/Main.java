package no_loop;
import java.util.*;
import java.util.stream.Collectors;
import java.util.function.*;
import java.io.*;

/*
     * Trabalho 2 de Programação Funcional - Versão sem laço (com stream)
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

        // Função que retorna o nome do país
        public String getCountry(){
            return this._country;
        }

        // Função que retorna o campo Active do país
        public int getActive(){
            return this._active;
        }
        
        
        // Função que retorna o campo Confirmed do país
        public int getConfirmed(){
            return this._confirmed;
        }
        
        
        // Função que retorna o campo Recovery do páis
        public int getRecovery(){
            return this._recovery;
        }

        // Função que retorna o campo Death do país 
        public int getDeath(){
            return this._death;
        }
        
        //Função usada para printar cada atributo do país:
        public void print(){
            System.out.println(this._country + ", "
            + Integer.toString(this._confirmed) + ", "
            + Integer.toString(this._death) + ", "
            + Integer.toString(this._recovery) + ", "
            + Integer.toString(this._active));
        }
    }       

    // Função que recebe uma linha e a transforma em um objeto Country:
    private static Function<String, Country> mapToCountry = (line) ->{
        Country c = new Country(line);
        
        return c;
    };

    //Método responsável por ler o stream do arquivo de dados
    //e retornar em uma lista do tipo Country.
    private static List<Country> processInput(String inputFile){
        
        //Cria a lista de Country
        List<Country> inputList = new ArrayList<Country>();

        try{
            
            File inputf = new File(inputFile);
            InputStream inputfs = new FileInputStream(inputf);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputfs));

            inputList = br.lines().map(mapToCountry).collect(Collectors.toList());
            br.close();

        }

        catch(IOException e){
            System.out.println("Erro no processamento");
        }

        return inputList;

    }


    
    public static void main(String[] args){

        //Leitura dos 4 números
        Scanner input = new Scanner(System.in);
        int n1, n2, n3, n4;
        
        n1 = input.nextInt();
        n2 = input.nextInt();
        n3 = input.nextInt();
        n4 = input.nextInt();

        input.close();

        //Leitura do arquivo e criação da lista countries
        List<Country> countries = processInput("dados.csv");
        
        // Funcionalidade 1)
        int sum = 0;
        
        sum = countries
                .stream()
                .filter(c -> (c._confirmed > n1))
                .map(c -> c._active)
                .reduce(0, (ans, i) -> ans+i);

        System.out.println(sum);        
    
        // Funcionalidade 2)
        countries
            .stream()
            .sorted(Comparator.comparing(Country::getActive).reversed()) //ordena inversamente
            .limit(n2)
            .sorted(Comparator.comparing(Country::getConfirmed)) //ordena por confirmed
            .limit(n3) 
            .forEach((Country c) -> System.out.println(c.getDeath()));
        
        
        // Funcionalidade 3)
        countries
            .stream()
            .sorted(Comparator.comparing(Country::getConfirmed).reversed()) //ordena inversamente
            .limit(n4)
            .sorted(Comparator.comparing(Country::getCountry)) //Ordena por country
            .forEach((Country c) -> System.out.println(c.getCountry()));

        
    }


}