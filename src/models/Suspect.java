// Suspect.java
package models;

public class Suspect {
    private int id;
    private String name;
    private String alias;
    private String age;
    private String description;
    private String crimeType; // Added crimeType field

    // Constructor with all fields
    public Suspect(int id, String name, String alias, String age, String description, String crimeType) {
        this.id = id;
        this.name = name;
        this.alias = alias;
        this.age = age;
        this.description = description;
        this.crimeType = crimeType;
    }

    // Constructor without ID for creating new suspect
    public Suspect(String name, String alias, String age, String description, String crimeType) {
        this.name = name;
        this.alias = alias;
        this.age = age;
        this.description = description;
        this.crimeType = crimeType;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCrimeType() {
        return crimeType;
    }

    public void setCrimeType(String crimeType) {
        this.crimeType = crimeType;
    }
}