import java.io.*;
import java.net.*;

class pingTeste{
    public static void solicitacaoPing(String enderecoIP)
    throws UnknownHostException, IOException{
        InetAddress vivver = InetAddress.getByName(enderecoIP);
        System.out.println("Enviando solicitação de ping para " + enderecoIP);
        if(vivver.isReachable(5000)){
            System.out.println("Host disponível");
        }else{
            System.out.println("Ping não alcançável");
        }
    }

    public static void main(String[] args)
    throws UnknownHostException, IOException{

        File arquivo = new File("clientes.txt");
        try{
            if(!arquivo.exists()){
                System.out.println("Arquivo não encontrado");
            }
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);

            while(br.ready()){
                String linha = br.readLine();
                URL url = new URL(linha);
                try{
                    InetAddress endereco = InetAddress.getByName(url.getHost());
                    String temp = endereco.toString();
                    String ip = temp.substring(temp.indexOf("/")+1, temp.length());
                    System.out.println(url);
                    solicitacaoPing(ip);
                }catch(UnknownHostException e){
                    System.out.println(url + " - Host inválido");
                    //System.out.println(e);
                }
            }
            br.close();
            fr.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}