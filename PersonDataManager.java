import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PersonDataManager {
    /**
     * The PersonDataManager class manages an array of person objects and provides methods to manipulate and retrieve data from the array.
     * currentCapacity is used to check how many people are currently in the array of person objects.
     */
    private static Person[]people;
    private static int currentCapacity;

    /**
     * Default constructor for the PersonDataManger
     */
    public PersonDataManager(){

    }

    /**
     * Constructor that takes an array of Person Objects as an argument.
     * @param people
     */
    public PersonDataManager(Person[]people){
        this.people = people;
    }

    /**
     * Method that reads data from a csv file and constructs a PersonDataManager Object
     * @param location String representing the path of the file
     * @return Person Data Manager Object
     * @throws IllegalArgumentException if the format is invalid
     */
    public static PersonDataManager buildFromFile(String location) throws IllegalArgumentException{
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(location));
            int numOfLines = 0;
            String line = fileReader.readLine();
            while(line != null){
                line = fileReader.readLine();
                numOfLines++;
            }
            numOfLines = numOfLines-2;
            people = new Person[numOfLines];

            fileReader = new BufferedReader(new FileReader(location));
            line = fileReader.readLine();
            currentCapacity = 0;


            while (!((line = fileReader.readLine()).equals(""))) {

                String[] columns = line.split(",");
                if (columns.length != 5) {
                    System.out.println("error in column length");
                    throw new IllegalArgumentException(" Invalid data format");
                }
                String name = columns[0].trim().replaceAll("\"", "");
                int age;
                double height;
                double weight;
                String gender = columns[1].trim().replaceAll("\"", "");

                try {


                    age = Integer.parseInt(columns[2].trim());
                    if (age < 0) {
                        throw new IllegalArgumentException("Age cannot be negative");
                    }
                    height = Double.parseDouble(columns[3].trim());
                    if (height <= 0) {
                        throw new IllegalArgumentException("Height must be greater than zero");
                    }
                    weight = Double.parseDouble(columns[4].trim());
                    if (weight <= 0) {
                        throw new IllegalArgumentException("Weight must be greater than zero");
                    }
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("Invalid data format");
                }
                Person person = new Person(age, height, weight, name, gender);
                people[currentCapacity] = person;
                currentCapacity++;
            }


        }
        catch (IOException ex){
            ex.getMessage();

        }

        return new PersonDataManager(people);

    }

    /**
     * Adds a new person to the array of person objects
     * @param newPerson new person object
     * @throws PersonAlreadyExistsException if the person is already listed in the array
     */
    public void addPerson(Person newPerson) throws PersonAlreadyExistsException{

        if(currentCapacity== people.length){
            Person[]biggerArray = new Person[people.length+1];
            System.arraycopy(people,0,biggerArray,0,people.length);
            people = biggerArray;
        }
        for(int i = 0; i< people.length-1;i++) {
            if (people[i].getName().equals(newPerson.getName())) {
                throw new PersonAlreadyExistsException("The person is already on the list");
            }
        }
        int index = people.length-1;
        for(int i = 0; i< people.length-1;i++){
            if(newPerson.getName().compareToIgnoreCase(people[i].getName())<0){
                index = i;
                break;
            }
        }
        for(int i = people.length-1;i>index;i--){
            people[i] = people[i-1];
        }
        people[index] = newPerson;
        index++;

    }
    public void getPerson(String name)throws PersonDoesNotExistsException{
        boolean PersonFound = false;
        for(int i = 0;i< people.length;i++){
            if(people[i].getName().equals(name)){
                System.out.println(people[i].getName()+" is a " + people[i].getAge()+" year old "+ people[i].getGender() + " (M=male, F=female)" + " who is " +people[i].getHeight()+ " inches tall and weighs " + people[i].getWeight()+" pounds.");
                PersonFound = true;
                break;
            }
        }
        if(!PersonFound){
            throw new PersonDoesNotExistsException(" Person does not exist!");
        }

    }

    /**
     * Removes the person from the array of person objects
     * @param name name of the person to be removed
     * @throws PersonDoesNotExistsException person does not exist in the list
     */
    public void removePerson(String name) throws PersonDoesNotExistsException{
        boolean isFound = false;
        int indexOfPerson=-1;
        for(int i=0; i<people.length;i++){
            if(people[i].getName().equals(name)){
                indexOfPerson = i;
                isFound = true;
                break;
            }
        }
        if(!isFound){
            throw new PersonDoesNotExistsException("Person does not exist!");
        } else{
            people[indexOfPerson] = null;
        }

    }

    /**
     * Creates a new csv file
     * @param nameOfFile name of the csv file to be created
     */
    public void saveToFile(String nameOfFile){
        try {
            FileWriter fileWriter = new FileWriter(nameOfFile);
            fileWriter.write("Name,Sex, Age, Height (in),Weight (lbs)\n");
            for (int i = 0; i < people.length-1; i++) {
                fileWriter.write(people[i].toString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints the PersonDataManager in tabular form.
     */
    public void printTable() {
        String formattedStr=String.format("%-20s%-20s%-20s%-20s%-20s\n", "Name","Age","Gender","Height","Weight");

        System.out.println(formattedStr);
        for(int i=0;i<people.length;i++) {
            if(people[i]!=null)
                System.out.println(people[i].toString());
        }
    }
}
