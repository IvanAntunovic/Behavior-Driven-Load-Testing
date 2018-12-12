package jmeter.projects;

import java.io.FileOutputStream;

import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.control.gui.LoopControlPanel;
import org.apache.jmeter.control.gui.TestPlanGui;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.control.gui.HttpTestSampleGui;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.threads.gui.ThreadGroupGui;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;


public class JMeterJMXGenerationExample {

	public static void main(String[] argv) throws Exception {
        // Initialize the configuration variables
        String jmeterHome = "D:\\apache-jmeter-4.0";
        JMeterUtils.setJMeterHome(jmeterHome);
        JMeterUtils.loadJMeterProperties(JMeterUtils.getJMeterBinDir()
                + "\\jmeter.properties");
        JMeterUtils.initLogging();
        JMeterUtils.initLocale();

        // TestPlan
        TestPlan testPlan = new TestPlan();
        testPlan.setName("Test Plan");
        testPlan.setEnabled(true);
        testPlan.setProperty(TestElement.TEST_CLASS, TestPlan.class.getName());
        testPlan.setProperty(TestElement.GUI_CLASS, TestPlanGui.class.getName());

        // ThreadGroup controller
        LoopController loopController = new LoopController();
        loopController.setEnabled(true);
        loopController.setLoops(1);
        loopController.setProperty(TestElement.TEST_CLASS,
                LoopController.class.getName());
        loopController.setProperty(TestElement.GUI_CLASS,
                LoopControlPanel.class.getName());

        // ThreadGroup
        ThreadGroup threadGroup = new ThreadGroup();
        threadGroup.setName("Thread Group");
        threadGroup.setEnabled(true);
        threadGroup.setSamplerController(loopController);
        threadGroup.setNumThreads(50);
        threadGroup.setRampUp(10);
        threadGroup.setProperty(TestElement.TEST_CLASS,
                ThreadGroup.class.getName());
        threadGroup.setProperty(TestElement.GUI_CLASS,
                ThreadGroupGui.class.getName());

        /*
        // JavaSampler
        JavaSampler javaSampler = new JavaSampler();
        javaSampler.setClassname("my.example.sampler");
        javaSampler.setEnabled(true);
        javaSampler.setProperty(TestElement.TEST_CLASS,
                JavaSampler.class.getName());
        javaSampler.setProperty(TestElement.GUI_CLASS,
                JavaTestSamplerGui.class.getName());
        */
        
        // First HTTP Sampler - open example.com
        HTTPSamplerProxy examplecomSampler = new HTTPSamplerProxy();
        examplecomSampler.setDomain("www.bug.hr");
        examplecomSampler.setPort(80);
        examplecomSampler.setPath("/");
        examplecomSampler.setMethod("GET");
        examplecomSampler.setName("Open www.bug.hr");
        examplecomSampler.setProperty(TestElement.TEST_CLASS, HTTPSamplerProxy.class.getName());
        examplecomSampler.setProperty(TestElement.GUI_CLASS, HttpTestSampleGui.class.getName());

        // Create TestPlan hash tree
        HashTree testPlanHashTree = new HashTree();
        testPlanHashTree.add(testPlan);

        // Add ThreadGroup to TestPlan hash tree
        HashTree threadGroupHashTree = new HashTree();
        threadGroupHashTree = testPlanHashTree.add(testPlan, threadGroup);

        // Add Java Sampler to ThreadGroup hash tree
        HashTree javaSamplerHashTree = new HashTree();
        javaSamplerHashTree = threadGroupHashTree.add(examplecomSampler);

        // Save to jmx file
        SaveService.saveTree(testPlanHashTree, new FileOutputStream(
                "d:\\test.jmx"));
        
        System.out.println("Saved JMX file to: d:\\test.jmx");
         
        //runTest(testPlanHashTree);
        
    }

	private static void runTest(HashTree testPlanHashTree) {
    
        StandardJMeterEngine jm = new StandardJMeterEngine();
        jm.configure(testPlanHashTree);
        jm.run();
        System.out.println("Ran JMeter Engine");
		
	}
	
	



}


