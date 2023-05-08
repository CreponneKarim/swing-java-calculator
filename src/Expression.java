
import java.util.Map;

import javax.swing.text.AbstractDocument.Content;
interface Expression {
	public int interpret(Context variables);
}
class Number implements Expression {
	private int number;
	public Number(int number) { this.number = number; }
	public int interpret(Context variables) {
		return number; 
	}
}
class Mul implements Expression{
	Expression leftOperand;
	Expression rightOperand;
	public Mul(Expression left, Expression right){

		leftOperand = left;
		rightOperand = right;
	}
	public int interpret(Context variables){
		return leftOperand.interpret(variables) * rightOperand.interpret(variables);
	}
}

class Facto implements Expression{
	Expression operand;
	public Facto(Expression operand){
		this.operand = operand;
	}
	public int interpret(Context variables){
		int toRet=1;
		for(int i=2; i<=this.operand.interpret(variables);i++){
			toRet*=i;
		}
		return toRet;
	}
}


class Plus implements Expression {
	Expression leftOperand;
	Expression rightOperand;
	public Plus(Expression left, Expression right) {
		leftOperand = left;
		rightOperand = right;
	}
	public int interpret(Context variables) {
		return leftOperand.interpret(variables) + rightOperand.interpret(variables);
	}
}
class Minus implements Expression {
	Expression leftOperand;
	Expression rightOperand;
	public Minus(Expression left, Expression right) {
		leftOperand = left;
		rightOperand = right;
	}
	public int interpret(Context variables) {
		return leftOperand.interpret(variables) - rightOperand.interpret(variables);
	}
}
class Variable implements Expression {
	private String name;
	public Variable(String name) { this.name = name; }
	public int interpret(Context variables) {
		return variables.getElement(name).interpret(variables);
	}
}