package tests;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;


public class WebServiceStepDefinition {

	Scenario mScenario;
	Given mGiven;
	
	@Before
	public void beforeHook(Scenario scenario) {
	     mScenario = scenario;
	     System.out.println("SCENARIO NAME: " + scenario.getName());
	     System.out.println("SCENARIO ID: " + scenario.getId());
	}
	
	@After
	public void afterHook() {
		System.out.println("Ending a test");
	}
	

	@Given("^the next beginning of Summer Term$")
	public void the_next_beginning_of_Summer_Term() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	  
	}
	
	@Then("^\"([^\"]*)\" the experiment for (\\d+) \"([^\"]*)\"$")
	public void the_experiment_for(String arg1, int arg2, String arg3) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    
	}
	
	@Then("^\"([^\"]*)\" a \"([^\"]*)\" should return$")
	public void a_should_return(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	 
	}
	
	@Then("^\"([^\"]*)\" a \"([^\"]*)\" is \"([^\"]*)\" than (\\d+) \"([^\"]*)\"$")
	public void a_is_than(String arg1, String arg2, String arg3, int arg4, String arg5) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	
	}





}
   