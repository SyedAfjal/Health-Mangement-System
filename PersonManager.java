import java.util.InputMismatchException;
import java.util.Scanner;

public class PersonManager {
    public static void main(String[] args) {
        PersonDataManager personDataManager = new PersonDataManager();
        Scanner input = new Scanner(System.in);
        String option = "";
        while(option!="Q"){
            mainMenu();
            System.out.println("Please select an option:");
            option = input.nextLine().toUpperCase();
            switch (option){
                case "I":
                    System.out.println("Please enter a location:");
                    String link = input.nextLine();
                    personDataManager.buildFromFile(link);
                    System.out.println("Loading...\n" +
                            "Person data loaded successfully!");
                    break;
                case "A":
                    try{
                        System.out.println("Please enter the name of the person: ");
                        String name1 = input.nextLine();
                        System.out.println("Please enter the age: ");
                        int age1 = input.nextInt();
                        System.out.println("Please enter the gender (M or F):");
                        String gender1 = input.nextLine();
                        input.nextLine();
                        System.out.println("Please enter the height (in inches): ");
                        double height1 = input.nextDouble();
                        input.nextLine();
                        System.out.println("Please enter the weight (in lbs):");
                        double weight1 = input.nextDouble();
                        input.nextLine();

                        Person p=new Person(age1, height1, weight1, name1, gender1);
                        try {
                            personDataManager.addPerson(p);
                            System.out.println(name1 + " has been added to the list!");
                        } catch (PersonAlreadyExistsException e) {
                            System.out.println("Person already exists!");
                        }

                    }catch (InputMismatchException ex){
                        System.out.println("The input you entered is incorrect. Please try again!");
                    }

                    break;
                case "R":
                    System.out.println("Please enter the name of the person:");
                    String name2 = input.nextLine();
                    try {
                        personDataManager.removePerson(name2);
                        System.out.println(name2+" has been removed!");
                    } catch (PersonDoesNotExistsException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case"G":
                    System.out.println("Please enter the name of the person:");
                    String name3 = input.nextLine();
                    try {
                        personDataManager.getPerson(name3);
                    } catch (PersonDoesNotExistsException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case"P":
                    personDataManager.printTable();
                    break;
                case "S":
                    System.out.println("Please select the name for the file:");
                    String fileName = input.nextLine();
                    personDataManager.saveToFile(fileName);
                    System.out.println("A file named "+fileName+" has been created!");
                    break;
                case"Q":
                    System.out.println("Sorry to see you go!");
                    break;


            }
        }
    }

    public static void mainMenu(){
        System.out.println(
                "Menu:\n" +
                        "o (I) – Import from File\n" +
                        "o (A) – Add Person\n" +
                        "o (R) – Remove Person\n" +
                        "o (G) – Get Info on Person\n" +
                        "o (P) – Print Table\n" +
                        "o (S) – Save to File\n" +
                        "o (Q) – Quit");
    }
}
