public class Utils {
    public static boolean isNumeric(String str){
        boolean ans = false;
        try {
            Double.parseDouble(str);
            ans = true;
        } catch (NumberFormatException err) {
            ans = false;
        }
        return ans;
    }

    public static void clearScreen(){ // limpa a tela com o comando correto para cada sistema operacional
        String OSName = System.getProperty("os.name"), command = new String("");
        try{
            if(OSName.contains("Windows")) command = "cls";
            else command = "clear";

            new ProcessBuilder(command).inheritIO().start().waitFor();
            // ProcessBuilder(comando) é uma classe que permite iniciar processos no SO
            // inheritIO() direciona a saida para o console atual
            // start() inicia o processo
            // waitFor() espera a execução do comando e depois segue com o programa 
        }catch(Exception err){
            System.out.println("Error:" + err);
        }
    }
}
