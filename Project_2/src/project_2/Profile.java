package project_2;

public class Profile {

    private String name;
    private Major major;

    @Override
    public String toString(){
        return name + "::" + major;
    }

    @Override
    public boolean equals(Object obj){
        return false;
    }
}
