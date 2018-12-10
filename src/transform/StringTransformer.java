package transform;

import cucumber.api.Transformer;

public class StringTransformer extends Transformer<String>{

    public String transform(String value) {     
        return "transformed " + value;
    }    
}