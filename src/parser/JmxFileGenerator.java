package parser;

import static jmeter.helpers.ProjectHelper.createHttpSampler;
import static jmeter.helpers.ProjectHelper.createLoopController;
import static jmeter.helpers.ProjectHelper.createSetupThreadGroup;
import static jmeter.helpers.ProjectHelper.createTestPlan;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.jmeter.assertions.DurationAssertion;
import org.apache.jmeter.assertions.ResponseAssertion;
import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.protocol.http.sampler.HTTPSampler;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.SetupThreadGroup;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;

import jmeter.helpers.ProjectHelper;

public class JmxFileGenerator implements Generatable {

	private HTTPSampler mGetSampler;
	private final HashTree mProjectTree = new HashTree();
	private final LoopController mLoopCtrl = createLoopController(1);
	private TestPlan mTestPlan;
	private SetupThreadGroup mSetupThreadGroup;
	private HashTree mSetupThreadGroupTree;
	
	private ResponseAssertion mResponseAssertion;
	private DurationAssertion mDurationAssertion;
	
	private final static int NUM_THREADS = 25;
	private final static int RAMP_UP = 1;
	
	private final static String RUN = "run"; 
	private final static String ENSURE = "ensure"; 
	private final static String POSITIVE_ANSWER = "positive answer";
	private final static String RESPONSE_TIME = "response time";
	private final static String LESS = "less";
	
	private final static String HOURS = "hours";
	
	public JmxFileGenerator(String method, String domain, int portNum, String path, String testPlanName) {
		mGetSampler = ProjectHelper.createHttpSampler(method, domain, portNum, path);
		
		mLoopCtrl.addTestElement(mGetSampler);
		mSetupThreadGroup = createSetupThreadGroup(mLoopCtrl, NUM_THREADS, RAMP_UP);
		mTestPlan = ProjectHelper.createTestPlan(testPlanName);
	    mTestPlan.addThreadGroup(mSetupThreadGroup);
		
	}
	
	public void generate(String jmxFileOutputPath) {
						
		/** The tree is needed if you save project as jmx file **/
		HashTree projectTree = new HashTree();

		HashTree testPlanTree = projectTree.add(mTestPlan);
        HashTree setupThreadGroupTree = testPlanTree.add(mSetupThreadGroup);
        
        if (mDurationAssertion != null) {
        	setupThreadGroupTree.add(mDurationAssertion);
        }
        
        if (mResponseAssertion != null) {
        	setupThreadGroupTree.add(mResponseAssertion);
        }
        
        HashTree loopCtrlTree = setupThreadGroupTree.add(mLoopCtrl);
        loopCtrlTree.add(mGetSampler);
        
        try {
			JmxFileSaver.createJmxFile(jmxFileOutputPath, projectTree);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void preprocess(List<String> stepDefinitionArgList) {
		
		for (int index = 0; index < stepDefinitionArgList.size(); ++index) {
			
			if ( stepDefinitionArgList.get(index).equals(RUN) ) {
				
				if (index + 1 > stepDefinitionArgList.size() || index + 2 > stepDefinitionArgList.size()) {
					return;
				}
				
				if ( stepDefinitionArgList.get( index + 2 ).equals(HOURS) ) {
					mSetupThreadGroup.setDuration( Integer.parseInt( stepDefinitionArgList.get(index + 1)) * 60 * 60 );
				}
				
				
			} else if ( stepDefinitionArgList.get(index).equals(ENSURE) ) {
				
				if (index + 1 > stepDefinitionArgList.size()) {
					return;
				}
				
				if ( stepDefinitionArgList.get( index + 1 ).equals(POSITIVE_ANSWER) ) {
					
					mResponseAssertion = new ResponseAssertion();
					mResponseAssertion.setAssumeSuccess(true);
					mResponseAssertion.setName("THEN a " + stepDefinitionArgList.get( index + 1 ) + " should return");
					
				} else if ( stepDefinitionArgList.get( index + 1 ).equals(RESPONSE_TIME) ) {
					
					if (index + 2 > stepDefinitionArgList.size() || index + 3 > stepDefinitionArgList.size()) {
						return;
					}
					
					mDurationAssertion = new DurationAssertion();
					mDurationAssertion.setAllowedDuration( Integer.parseInt(stepDefinitionArgList.get( index + 3 )) );
					mDurationAssertion.setName("AND " + stepDefinitionArgList.get( index + 1 ) + " should be"
			        		+ stepDefinitionArgList.get( index + 2 ) + " than " + stepDefinitionArgList.get( index + 3 )  + " miliseconds" );
					
				}
				
			}
		}
	}
}
