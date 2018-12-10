package parser;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;

import jmeter.projects.SimpleProject;

public class JmxFileSaver {
	
	public static void createJmxFile(String jmxFileOutputPath, HashTree projectTree) throws IOException {
		
		if (projectTree == null) {
			return;
		}
		
		loadJmeterPropertiesFromFiles();

		// TODO: replace 2nd parameter to this method call with 'jmxFileOutputPath' parameter
        saveAsJmxFile(projectTree, SimpleProject.FILE_NAME);

        System.out.println("Now you can execute the project and get Html report by command (do not try open it in GUI):");
        System.out.println("rm -r results; rm log.jlt;");
        System.out.println("jmeter -n -e -o results -l log.jlt -t " + SimpleProject.FILE_NAME +  " && ls results/index.html");
	}
	
	 private static void saveAsJmxFile(HashTree projectTree, String fileName) throws IOException {
	        SaveService.saveTree(projectTree, new FileOutputStream(fileName));
	    }

	    private static void exportResultsToHtml() {
	        //        HtmlTemplateExporter htmlTemplateExporter = new HtmlTemplateExporter();
	//
//	        SampleContext sampleContext = new SampleContext();
//	        File file = new File("report.txt");
//	        Properties reportGenerationProperties = new Properties();
//	        FileReader fileReader = new FileReader(new File("reportgenerator.properties"));
//	        reportGenerationProperties.load(fileReader);
//	        ReportGeneratorConfiguration reportGeneratorConfiguration =  ReportGeneratorConfiguration.loadFromProperties(reportGenerationProperties);
	//
	//
//	        htmlTemplateExporter.export(sampleContext, file, reportGeneratorConfiguration);
	    }

	    private static void loadJmeterPropertiesFromFiles() {
	        JMeterUtils.loadJMeterProperties("jmeter.properties");
	        JMeterUtils.loadProperties("user.properties");
	        JMeterUtils.setJMeterHome(".");
	        JMeterUtils.setProperty("saveservice_properties", "/saveservice.properties");
	    }

}
