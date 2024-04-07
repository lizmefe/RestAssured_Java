package test;

import org.testng.annotations.Test;
import org.junit.runner.RunWith;

import net.serenitybdd.annotations.Steps;
import net.serenitybdd.junit.runners.SerenityRunner;
import steps.RestfulBookerSteps;

@RunWith(SerenityRunner.class)
public class RestfulBookerTests_Serenity_Steps {

        @Steps
        RestfulBookerSteps restfulBookerSteps;

        @Test
        public void testAllOperations() {
            restfulBookerSteps.createBooking();
            restfulBookerSteps.getBooking();
            restfulBookerSteps.updateBooking();
            restfulBookerSteps.deleteBooking();
        }
}