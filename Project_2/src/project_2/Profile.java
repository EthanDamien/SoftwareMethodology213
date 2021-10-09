package project_2;


public class Profile {

    private String name;
    private Major major;

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

    @Override
    public String toString(){
        return name + "::" + major;
    }

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

    public String getName() {
        return name;
    }

    public Major getMajor(){
        return major;
    }

    public static boolean validMajor(){
        return false;
    }

}
