package project_2;

/**
 * The profile of a Student which contains their name and major
 * @author Kevin Cubillos, Ethan Chang
 */
public class Profile {

    /** The name of a student */
    private String name;
    /** The major of the student */
    private Major major;

    /**
     * The constructor where both name and major are known.
     * @param name the name of the student
     * @param major the major of the student
     */
    public Profile(String name, String major){
        this.name = name;

        major = major.toUpperCase();
        if(major.equals("BA")){
            this.major = Major.BA;
        }
        else if(major.equals("CS")){
            this.major = Major.CS;
        }
        else if(major.equals("EE")){
            this.major = Major.EE;
        }
        else if(major.equals("IT")){
            this.major = Major.IT;
        }
        else if(major.equals("ME")){
            this.major = Major.ME;
        }

    }

    /**
     * Makes a string representation of a Student's profile
     * @return the string representation
     */
    @Override
    public String toString(){
        return name + "::" + major;
    }

    /**
     * Checks equality between two Profiles by name and major
     * @param obj
     * @return
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
        return name.equals(temp.getName()) && major == temp.getMajor();
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
    public Major getMajor(){
        return major;
    }

    public static boolean validMajor(){
        return false;
    }

}
