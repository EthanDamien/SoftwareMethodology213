package project_2;

/**
 * The profile of a Student which contains their name and major
 * @author Kevin Cubillos, Ethan Chang
 */
public class Profile {

    /** The name of a student */
    private String name;
    /** The major of the student */
    private String major;

    /**
     * The constructor where both name and major are known.
     * @param name the name of the student
     * @param major the major of the student
     */
    public Profile(String name, String major){
        this.name = name;
        this.major = major;
    }

    /**
     * Makes a string representation of a Student's profile
     * @return the string representation
     */
    @Override
    public String toString(){
        return name + ":" + major;
    }

    /**
     * Checks equality between two Profiles by name and major
     * @param obj other profile that is being compared with
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj){
        if(obj == this){
            return true;
        }

        if(! (obj instanceof Profile)){
            return false;
        }

        Profile temp =  (Profile) obj;
        return name.equals(temp.getName()) && major.equals(temp.getMajor());
    }

    /**
     * Getter for name
     * @return profile name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for major
     * @return profile major
     */
    public String getMajor(){
        return major;
    }

}
