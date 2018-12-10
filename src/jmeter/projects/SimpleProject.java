package jmeter.projects;

import org.apache.jmeter.assertions.DurationAssertion;
import org.apache.jmeter.assertions.ResponseAssertion;
import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.protocol.http.sampler.HTTPSampler;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.SetupThreadGroup;
import org.apache.jorphan.collections.HashTree;

import java.io.IOException;

import static jmeter.helpers.ProjectHelper.*;

public class SimpleProject {

    public static final String FILE_NAME = "simpleProject_generated.jmx";
    public static final String GOOGLE_COM = "www.google.com";
    public static final String ISTE_UNI_STUTTGART_DE_RSS = "iste.uni-stuttgart.de/rss/";
    public static final int PORT = 80;
    public static final int DURATION_OF_TEST_PLAN_EXECUTION_FOUR_HRS = 14400;

    public static HashTree create() throws IOException {

        HashTree projectTree = new HashTree();

        HTTPSampler googleGetSampler = createHttpSampler("GET", ISTE_UNI_STUTTGART_DE_RSS, PORT, "/");
        LoopController loopCtrl = createLoopController(1);
//        IfController ifController = createIfController("");
        loopCtrl.addTestElement(googleGetSampler);
        SetupThreadGroup setupThreadGroup = createSetupThreadGroup(loopCtrl, 25, 1);
        TestPlan testPlan = createTestPlan("Simple Test Plan");
        testPlan.addThreadGroup(setupThreadGroup);
        
        setupThreadGroup.setDuration(DURATION_OF_TEST_PLAN_EXECUTION_FOUR_HRS);
        
        DurationAssertion durationAssertion = new DurationAssertion();
        durationAssertion.setAllowedDuration(5000);
        durationAssertion.setName("AND response time should be lower than 5000 miliseconds");
        
        ResponseAssertion responseAssertion = new ResponseAssertion();
        responseAssertion.setAssumeSuccess(true);
        responseAssertion.setName("THEN a positive answer should return");

        /** The tree is needed if you save project as jmx file **/
        HashTree testPlanTree = projectTree.add(testPlan);
        HashTree setupThreadGroupTree = testPlanTree.add(setupThreadGroup);
        setupThreadGroupTree.add(durationAssertion);
        setupThreadGroupTree.add(responseAssertion);
        HashTree loopCtrlTree = setupThreadGroupTree.add(loopCtrl);
        loopCtrlTree.add(googleGetSampler);
        return projectTree;
    }

}