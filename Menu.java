import java.io.*;
import java.util.*;

public class Menu {
    public static void show() {
        try {
            File menu = new File("./menu.txt");
            Scanner MenuOutput = new Scanner(menu);

            while (MenuOutput.hasNextLine())
                System.out.println(MenuOutput.nextLine());

            MenuOutput.close();
        } catch (FileNotFoundException err) {
            System.out.printf("Arquivo não encontrado: %s", err.getMessage());
        }
    }
}
