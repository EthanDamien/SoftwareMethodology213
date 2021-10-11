package project_2;

import org.junit.Assert;
import org.junit.Test;

public class RosterTest {
    Roster roster = new Roster();

    private static final Student VALID_DEFAULT_STUDENT = new Student("Kanye", "CS", 12);
    private static final Student VALID_DEFAULT_STUDENT2 = new Student("Kanye2", "CS", 12);
    private static final Student VALID_DEFAULT_STUDENT3 = new Student("Kanye", "EE", 12);

    /**
     * This tests if the add student method works for Default Student in Roster
     */
    @Test
    public void addValidDefault() {
        Assert.assertTrue(roster.add(VALID_DEFAULT_STUDENT));
    }

    /**
     * This tests if the add student method doesn't work if it's the same student
     */
    @Test
    public void addValidSame() {
        roster.add(VALID_DEFAULT_STUDENT);
        Assert.assertFalse(roster.add(VALID_DEFAULT_STUDENT));
    }

    /**
     * This tests if the add student method works if a different student is added
     */
    @Test
    public void addValidDiff() {
        roster.add(VALID_DEFAULT_STUDENT2);
        Assert.assertTrue(roster.add(VALID_DEFAULT_STUDENT));
    }

    /**
     * This tests if the add student method works if a different student is added
     */
    @Test
    public void addValidDiffMajor() {
        roster.add(VALID_DEFAULT_STUDENT3);
        Assert.assertTrue(roster.add(VALID_DEFAULT_STUDENT));
    }

    /**
     * This tests if the remove student method works for Default Student in Roster, by adding the student first.
     */
    @Test
    public void removeValidDefault() {
        roster.add(VALID_DEFAULT_STUDENT);
        Assert.assertTrue(roster.remove(VALID_DEFAULT_STUDENT));
    }


}
