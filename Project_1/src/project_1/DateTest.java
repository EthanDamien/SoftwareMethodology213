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
    private static final String[] DATE31 = {"01/31/2021","03/31/2021","05/31/2021","07/31/2021","08/31/2021","10/31/2021","12/31/2021"};
    private static final String[] DATE30 = {"04/31/2021","06/31/2021","09/31/2021","11/31/2021"};

    @Test
    public void dateBeforeTest() {
        Assert.assertEquals(false, new Date(DATEBEFORE).isValid());
    }

    @Test
    public void dateAfterTest() {
        Assert.assertEquals(false, new Date(DATEAFTER).isValid());
    }

    @Test
    public void dateFeb29Test() {
        Assert.assertEquals(true, new Date(DATEFEBLEAP).isValid());
    }

    @Test
    public void dateFeb28Test() {
        Assert.assertEquals(false, new Date(DATEFEBNONLEAP).isValid());
    }

    @Test
    public void date31Test() {
        System.out.println("These should all return true");
        for(String data: DATE31) {
            boolean actual =  new Date(data).isValid();
            System.out.println(data + ", " + actual);
            Assert.assertEquals(true,actual);
        }
    }

    @Test
    public void setDate30Test() {
        for(String data: DATE30) {
            System.out.println("These should all return false");
            boolean actual =  new Date(data).isValid();
            System.out.println(data + ", " + actual);
            Assert.assertEquals(false,actual);
        }
    }

}