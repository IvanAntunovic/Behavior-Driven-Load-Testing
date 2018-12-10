package parser;

import java.util.List;

public interface Generatable {

	public void generate(String fileOutputPath);
	public void preprocess(List<String> argList);
	
}
