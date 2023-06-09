
import java.util.Map;
import java.util.Stack;
class Evaluator implements Expression {
	private Expression syntaxTree;
	public Evaluator(String expression) {
		Stack<Expression> expressionStack = new Stack<Expression>();
		for (String token : expression.split(" ")) {
			if (token.equals("+")) {
				Expression subExpression = new Plus(expressionStack.pop(), expressionStack.pop());
				expressionStack.push( subExpression );
			} else if (token.equals("-")) {
				Expression subExpression = new Minus(expressionStack.pop(), expressionStack.pop());
				expressionStack.push( subExpression );
			}else if(token.equals("*")){
				Expression subExpression = new Mul(expressionStack.pop(), expressionStack.pop());
				expressionStack.push( subExpression );
			}else if(token.equals("!")){ 
				Expression subExpression = new Facto(expressionStack.pop());
				expressionStack.push( subExpression );
			}else if(Utils.isNumeric(token)){
				expressionStack.push( new Number(Integer.valueOf(token)));
			}else{
				expressionStack.push( new Variable(token) );
			}
		}
		syntaxTree = expressionStack.pop();
	}
	public int interpret(Context context) {
		return syntaxTree.interpret(context);
	}
}