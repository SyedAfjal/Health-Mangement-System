/**
 * The Person class represents a person with their biological statistics
 * This variable of person includes age, height, weight, name and gender.
 */
public class Person {
    private int age;
    private double height;
    private double weight;
    private String name;
    private String gender;

    /**
     * Constructor of an empty person object.
     */
    public Person(){

    }

    /**
     * Constructor with specified biological statistics
     * @param age
     * @param height
     * @param weight
     * @param name
     * @param gender
     */
    public Person(int age, double height, double weight, String name, String gender){
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.name = name;
        this.gender = gender;
    }

    /**
     * Sets the age of the person
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Return the age of the person
     * @return
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the height of the person
     * @param height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Return the height of the person
     * @return
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets the weight of person
     * @param weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Return the weight of person
     * @return
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets the name of the person
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the name of the person
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the gender of the person
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Return the gender of the person
     * @return
     */
    public String getGender() {
        return gender;
    }

    /**
     * Return a string representation of the person's biological statistics in tabular format.
     * @return
     */
    public String toString() {
        StringBuilder str=new StringBuilder();
        String formattedStr=String.format("%-20s%-20s%-20s%-20s%-20s\n", name,age,gender,height,weight);
        str.append(formattedStr);
        return str.toString();
    }
}
