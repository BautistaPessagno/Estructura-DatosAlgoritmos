import java.util.Scanner;

public class ExpTree implements ExpressionService {

	private Node root;

	public ExpTree() {
	    System.out.print("Introduzca la expresi�n en notaci�n infija con todos los par�ntesis y blancos: ");

		// token analyzer
	    Scanner inputScanner = new Scanner(System.in).useDelimiter("\\n");
	    String line= inputScanner.nextLine();
	    inputScanner.close();

	    buildTree(line);
	}
	
	private void buildTree(String line) {	
		  // space separator among tokens
		  Scanner lineScanner = new Scanner(line).useDelimiter("\\s+");
		  root= new Node(lineScanner);
		  lineScanner.close();
		  printTree(root);
	}

	@Override
	public double eval(){
		return root.eval();
	}

	@Override
	public void preorder() {
		root.preOrdeer();
	}

	@Override
	public void inorder() {
		root.inOrdeer();
	}

	@Override
	public void postorder() {
		root.postOrdeer();
	}

	private void printTree(Node node) {
		System.out.println("Inorder:");
		inorder();
		System.out.println("\nPreorder:");
		preorder();
		System.out.println("\nPostorder:");
		postorder();
		System.out.println("\nEval: " + eval());
	}

	
	static final class Node {
		private String data;
		private Node left, right;
		
		private Scanner lineScanner;

		public Node(Scanner theLineScanner) {
			lineScanner= theLineScanner;
			
			Node auxi = buildExpression();
			data= auxi.data;
			left= auxi.left;
			right= auxi.right;
			
			if (lineScanner.hasNext() ) 
				throw new RuntimeException("Bad expression");
		}

		public void inOrdeer() {
			System.out.print(data + " ");
			if (left != null) {
				left.inOrdeer();
			}
			if (right != null) {
				right.inOrdeer();
			}
		}

		public void preOrdeer() {
			if (left != null) {
				System.out.printf("( ");
				left.preOrdeer();
			}
			System.out.print(data + " ");
			if (right != null) {
				right.preOrdeer();
				System.out.printf(") ");
			}
		}

		public void postOrdeer() {
			if (left != null) {
				left.postOrdeer();
			}
			if (right != null) {
				right.postOrdeer();
			}
			System.out.print(data + " ");
		}
		
		private Node() {
		}
		
	
		private Node buildExpression() {
			// caso 1: E->(E op E) siendo op +, -, *, /
			// caso ejemplo ( a + ( b * c ) )
			// ahora el lineScanner apunta a (
			if (lineScanner.hasNext("\\(")) { // como es ( entra
				lineScanner.next();	 //lo salteo
				Node left= buildExpression(); // llama a la izquierda con el line scanner en a -> va a retornar a terminando el lineScanner en +

				if (!lineScanner.hasNext()) { // si no hay un + es porque no hay un operador
					throw new RuntimeException("missing or invalid operator");
				}
				String op= lineScanner.next();	// se guarda el operador
				if(!Utils.isOperator(op))
					throw new RuntimeException("missing or invalid operator");

				Node right= buildExpression(); // llama a la derecha con el line scanner estando en ( va a volver a hacer todo para ( b * c )
				if(!lineScanner.hasNext("\\)")) { // si no hay un ) es porque no hay un operador
					throw new RuntimeException("missing");
				}
				lineScanner.next(); // consume )

				Node node= new Node();
				node.data= op;
				node.left= left;
				node.right= right;

				return node;
			}
			// caso 2: E->cte
			Node node = new Node();
			if (!lineScanner.hasNext()) {
				throw new RuntimeException("Bad expression");
			}
			node.data= lineScanner.next();
			if(!Utils.isConstant(node.data)) {
				throw new RuntimeException("Bad expression");
			}
			node.left= null;
			node.right= null;
			return node;
		}

		private double eval(){
			if(left == null && right == null) {
				return Utils.getDoubleConstant(data);
			}
			return operate(data, left.eval(), right.eval());
		}

		private double operate(String data, double left, double right) {
			switch (data){
				case "+": return left + right;
				case "-": return left - right;
				case "*": return left * right;
				case "/": return left / right;
				case "^": return Math.pow(left, right);
			}
			throw new RuntimeException("wrong operator");
		}



	}  // end Node class

	
	
	// hasta que armen los testeos
	public static void main(String[] args) {
		ExpressionService myExp = new ExpTree();
	
	}

}  // end ExpTree class
