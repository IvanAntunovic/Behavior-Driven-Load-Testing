package tests;

import java.util.ArrayList;
import java.util.List;

import bsh.util.Util;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import jmeter.projects.SimpleProject;
import parser.JmxFileGenerator;


public class WebServiceStepDefinition {

	private Scenario mScenario;
	private Given mGiven;
	private JmxFileGenerator mJmxFileGenerator;
	
	public static final String FILE_NAME = "simpleProject_generated.jmx";
    public static final String GOOGLE_COM = "www.google.com";
    public static final String ISTE_UNI_STUTTGART_DE_RSS = "iste.uni-stuttgart.de/rss/";
    public static final int PORT = 80;
	
	@Before
	public void beforeHook(Scenario scenario) {
	     mScenario = scenario;
	     System.out.println("SCENARIO NAME: " + scenario.getName());
	     System.out.println("SCENARIO ID: " + scenario.getId());
	  
	     mJmxFileGenerator = new JmxFileGenerator("GET", GOOGLE_COM, PORT, "/", FILE_NAME);
	}
	
	@After
	public void afterHook() {
		// TODO: Fetch jmxFileOutputPath from config file
		mJmxFileGenerator.generate(SimpleProject.FILE_NAME);
	}
	

	@Given("^the next beginning of Summer Term$")
	public void the_next_beginning_of_Summer_Term() throws Throwable {
	  
	}
	
	@Then("^\"([^\"]*)\" the experiment for (\\d+) \"([^\"]*)\"$")
	public void the_experiment_for(String arg1, int arg2, String arg3) throws Throwable {
		List<String> argList = new ArrayList<>();
	    
		argList.add( arg1 );
		argList.add( String.valueOf(arg2) );
		argList.add( arg3 );
		
		mJmxFileGenerator.preprocess(argList);
	}
	
	@Then("^\"([^\"]*)\" a \"([^\"]*)\" should return$")
	public void a_should_return(String arg1, String arg2) throws Throwable {
		List<String> argList = new ArrayList<>();
	    
		argList.add( arg1 );
		argList.add( arg2 );
		
		mJmxFileGenerator.preprocess(argList);
	 
	}
	
	@Then("^\"([^\"]*)\" a \"([^\"]*)\" is \"([^\"]*)\" than (\\d+) \"([^\"]*)\"$")
	public void a_is_than(String arg1, String arg2, String arg3, int arg4, String arg5) throws Throwable {
		List<String> argList = new ArrayList<>();

		argList.add( arg1 );
		argList.add( arg2 );
		argList.add( arg3 );
		argList.add( String.valueOf(arg4) );
		argList.add( arg5 );
		
		mJmxFileGenerator.preprocess(argList);
	
	}





}
   