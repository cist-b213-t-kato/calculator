package calculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * ReversePolishNotation
 * 逆ポーランド記法による数式の計算
 **/
public class RPN {

	public static BigDecimal calc(String origin){
		return calc(origin.split(" "));
	}

	public static BigDecimal calc(String[] origin){
		List<String> formatted = new ArrayList<>(Arrays.asList(origin));
		return calc(formatted);
	}

	public static BigDecimal calc(List<String> origin){
        Node<List<String>> node = new Node<>();
        node.data = origin;
        RPN.transform(node);
        List<String> list = new ArrayList<>();
        list.add(RPN.traverse(node, list));
        return RPN.rpncalc(list);
	}

    private static BigDecimal rpncalc(List<String> o){

        Stack<BigDecimal> o_stack = new Stack<>();

        o.stream().forEach(t->{
            if(NumberUtils.isNumber(t)){
                o_stack.push(new BigDecimal(t));
            }else{
                BigDecimal b = o_stack.pop();
                BigDecimal a = o_stack.pop();
                if(t.equals("+")){
                    o_stack.push(a.add(b));
                }else if(t.equals("-")){
                    o_stack.push(a.subtract(b));
                }else if(t.equals("*")){
                    o_stack.push(a.multiply(b));
                }else if(t.equals("/")){
                    o_stack.push(a.divide(b, 10, BigDecimal.ROUND_HALF_UP));
                }else if(t.equals("^")){
                	o_stack.push(a.pow(Integer.valueOf(b.intValue())));
                }
            }
        });

        return o_stack.peek();
    }

    /**
     *   標準の数式から逆ポーランド記法への変換をする
     */
    private static void transform( Node<List<String>> node ){
    	int lowPriorityIndex = -1;

    	if("(".equals(node.data.get(0))
    			&& ")".equals(node.data.get(node.data.size()-1))){
    		node.data.remove(0);
    		node.data.remove(node.data.size()-1);
    	}

    	for(int i=node.data.size()-1; i>=0; i--){
    		String o = node.data.get(i);

    		if(")".equals(o)){
    			while(!"(".equals(node.data.get(i))){
    				i--;
    			}
    		}else if(!NumberUtils.isNumber(o)){
    			if(lowPriorityIndex == -1
    					|| checkPriority(o)<checkPriority(node.data.get(lowPriorityIndex))){
        			lowPriorityIndex = i;
        			continue;
        		}
    			if(checkPriority(o)>checkPriority(node.data.get(lowPriorityIndex))
    					|| ")".equals(o)){
    				break;
    			}
    		}
    	}

    	if(lowPriorityIndex==-1){
    		return;
    	}

    	node.left = new Node<>(new ArrayList<>());
    	node.right = new Node<>(new ArrayList<>());
		for(int i=0; i<lowPriorityIndex; i++){
			node.left.data.add(node.data.get(i));
		}
		transform(node.left);
		for(int i=lowPriorityIndex+1; i<node.data.size(); i++){
			node.right.data.add(node.data.get(i));
		}
		transform(node.right);

		String str = node.data.get(lowPriorityIndex);
		node.data = new ArrayList<>();
		node.data.add(str);

    }

    private static String traverse(Node<List<String>> node, List<String> list){

    	if(node.left!=null){
        	list.add(traverse(node.left, list));
    	}
    	if(node.right!=null){
    		list.add(traverse(node.right, list));
    	}

    	return node.data.get(0);

    }

    private static int checkPriority(String str){
    	switch(str){
    	case "=":
    		return 1;
    	case "+":
		case "-":
    		return 2;
    	case "*":
		case "/":
    		return 3;
		case "^":
			return 4;
		default:
			throw new RuntimeException("保障されていない演算子が使われました");
    	}
    }

}