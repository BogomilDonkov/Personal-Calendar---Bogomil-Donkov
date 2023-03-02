import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String commandString;
        String[] input;
        String command;
        boolean recognizedCommand;
        Operations operations=new Operations();

        while(true) {
            System.out.print(">");

            recognizedCommand=false;
            commandString = scanner.nextLine();
            input= commandString.split(" ");
            command = input[0];

            if(input.length>5){
                throw new IllegalArgumentException();
            }


            for (Commands operation: Commands.values()) {
                if (operation.toString().equals(command)) {
                    switch (operation) {

                        case close -> {
                            if(input.length!=1)
                                throw new Exception();

                            operations.close(null);
                        }
                        case open -> {
                            if(input.length!=2)
                                throw new Exception();
                            operations.open(input[1]);
                        }
                        case help -> {
                            operations.help();
                        }
                        case save -> {
                            //CommandLine.save();
                        }
                        case exit -> operations.exit(0);

                    }
                    recognizedCommand=true;
                    break;
                }
            }

            if(!recognizedCommand)
                System.out.println("'"+input[0]+"'"+" is not recognized as an internal or external command");
        }

    }

}

