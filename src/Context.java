import java.util.HashMap;
import java.util.Map;

class Context{
	HashMap<String,Number> variables;
	public Context(HashMap<String,Number> variables){
		this.variables = variables;
	}
	public Context(){
		this.variables = new HashMap<String,Number>();
	}

	public Number getElement(String key){
		return this.variables.get(key);
	}
	public void addElement(String key, Number value){
		this.variables.put(key, value);
	}
}