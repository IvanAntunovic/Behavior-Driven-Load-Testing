package jmeter.helpers;

import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.report.config.ConfigurationException;
import org.apache.jmeter.report.dashboard.ExportException;
import org.apache.jmeter.report.dashboard.GenerationException;
import org.apache.jmeter.report.dashboard.ReportGenerator;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import jmeter.projects.SimpleProject;

import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ExportException, ConfigurationException, IOException {

        loadJmeterPropertiesFromFiles();

        HashTree projectTree = SimpleProject.create();

        saveAsJmxFile(projectTree, SimpleProject.FILE_NAME);

        System.out.println("Now you can execute the project and get Html report by command (do not try open it in GUI):");
        System.out.println("rm -r results; rm log.jlt;");
        System.out.println("jmeter -n -e -o results -l log.jlt -t " + SimpleProject.FILE_NAME +  " && ls results/index.html");
        
        // Run the Test Plan
        StandardJMeterEngine jmeterEngine = buildJMeterEngine(projectTree);
        System.out.println("Running test suite, please wait...\n");
        jmeterEngine.run();
        System.out.println("\n... Test suite has finished.");
        
        //We generate the HTML Report
        //Runtime.getRuntime().exec("/../bin/jmeter -g stresstest.csv -o " + REPORT_FOLDER);
        ReportGenerator generator = new ReportGenerator("stresstest.csv", null);
        try {
			generator.generate();
		} catch (GenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//        StandardJMeterEngine jm = new StandardJMeterEngine();
//        jm.configure(projectTree);
//        jm.run();
//        exportResultsToHtml();
    }

    private static void saveAsJmxFile(HashTree projectTree, String fileName) throws IOException {
        SaveService.saveTree(projectTree, new FileOutputStream(fileName));
    }

    private static void exportResultsToHtml() {
        //        HtmlTemplateExporter htmlTemplateExporter = new HtmlTemplateExporter();
//
//        SampleContext sampleContext = new SampleContext();
//        File file = new File("report.txt");
//        Properties reportGenerationProperties = new Properties();
//        FileReader fileReader = new FileReader(new File("reportgenerator.properties"));
//        reportGenerationProperties.load(fileReader);
//        ReportGeneratorConfiguration reportGeneratorConfiguration =  ReportGeneratorConfiguration.loadFromProperties(reportGenerationProperties);
//
//
//        htmlTemplateExporter.export(sampleContext, file, reportGeneratorConfiguration);
    }

    private static void loadJmeterPropertiesFromFiles() {
        JMeterUtils.loadJMeterProperties("jmeter.properties");
        JMeterUtils.loadProperties("user.properties");
        JMeterUtils.setJMeterHome(".");
        JMeterUtils.setProperty("saveservice_properties", "/saveservice.properties");
    }
    
    private static StandardJMeterEngine buildJMeterEngine(HashTree testPlanTree) {
        StandardJMeterEngine jmeterEngine = new StandardJMeterEngine();
        jmeterEngine.configure(testPlanTree);
        return jmeterEngine;
    }

}