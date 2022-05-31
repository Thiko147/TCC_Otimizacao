package Classes.NonUserInteractable;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class ScanPopUp implements Callable {

    private Scanner scanner = new Scanner(System.in);

    public Object call() throws IOException{
        scanner.nextLine();
        return true;
    }
}
