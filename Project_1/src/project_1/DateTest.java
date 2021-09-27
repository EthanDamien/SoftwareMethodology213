package project_1;

import  org.junit.Assert;

import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DateTest {

    private static final String DATEBEFORE = "07/25/1969";
    private static final String DATEAFTER = "12/31/2021";
    private static final String DATEFEBLEAP = "02/29/2020";
    private static final String DATEFEBNONLEAP = "02/29/2021";


    @Test
    public void dateBeforeTest() {
        Assert.assertEquals(new Date(DATEBEFORE).isValid(), false);
    }

    @Test
    public void dateAfterTest() {
        Assert.assertEquals(new Date(DATEAFTER).isValid(), false);
    }

    @Test
    public void dateFeb29Test() {
        Assert.assertEquals(new Date(DATEFEBLEAP).isValid(), true);
    }

    @Test
    public void dateFeb28Test() {
        Assert.assertEquals(new Date(DATEFEBNONLEAP).isValid(), false);
    }


}