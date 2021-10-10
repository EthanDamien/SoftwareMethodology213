package project_2;

import org.junit.Assert;
import org.junit.Test;

public class InternationalTest {
    International international;
    private static final double NOT_ABROAD_TUITION = 35655.0;
    private static final double ABROAD_TUITION = 5918.0;

    /**
     * This Tests whether the not abroad tuition is correct
     * Test Case #1
     */
    @Test
    public void tuitionDueNotAbroad() {
        international = new International("Kanye", "CS",13,false);
        international.tuitionDue();
        Assert.assertEquals(NOT_ABROAD_TUITION, international.getTuition(), 0.1);
    }

    /**
     * This Tests whether the abroad tuition is correct
     * Test Case #1
     */
    @Test
    public void tuitionDueAbroad() {
        international = new International("Kanye", "CS",13,true);
        international.tuitionDue();
        Assert.assertEquals(ABROAD_TUITION, international.getTuition(), 0.1);
    }
}