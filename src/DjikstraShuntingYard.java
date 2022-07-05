import java.util.Stack;

public class DjikstraShuntingYard {
    static int calculatePrecedence(char c){
        if(c == '+' || c == '-'){
            return 1;
        } else if(c == '*' || c == '/'){
            return 2;
        } else if(c == '^'){
            return 3;
        } else if(c == '(') {
            return 4;
        } else {
            return 0;
        }
    }

    static boolean leftAssociative(char ch) {
        if (ch == '+' || ch == '-' || ch == '/' || ch == '*') {
            return true;
        } else {
            return false;
        }
    }


    /*
        If the incoming symbols is an operand, print it..

        If the incoming symbol is a left parenthesis, push it on the stack.

        If the incoming symbol is a right parenthesis: discard the right parenthesis,
        pop and print the stack symbols until you see a left parenthesis. Pop the left parenthesis and discard it.

        If the incoming symbol is an operator and the stack is empty or contains a left parenthesis on top,
        push the incoming operator onto the stack.

        If the incoming symbol is an operator and has either higher precedence than the operator on the top of the stack,
        or has the same precedence as the operator on the top of the stack and is right associative -- push it on the stack.

        If the incoming symbol is an operator and has either lower precedence than the operator on the top of the stack,
        or has the same precedence as the operator on the top of the stack and is left associative
        -- continue to pop the stack until this is not true. Then, push the incoming operator.

        At the end of the expression, pop and print all operators on the stack. (No parentheses should remain.)

     */
    public static double algorithm(String value){
        Stack<Double> valueStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();
        char operator;
        double val2 = 0;
        double val1 = 0;
        String[] valueArr = value.split(" ");

        for(int i = 0; i < valueArr.length; i++){
            //If it is a operator
            if(calculatePrecedence(valueArr[i].charAt(0)) != 0){
                if(valueArr[i].charAt(0) == '(') {
                    operatorStack.push('(');
                } else if(valueArr[i].charAt(0) == ')') {

                    do{
                        operator = operatorStack.pop();

                        if(operator != '('){
                            val2 = valueStack.pop();
                            val1 = valueStack.pop();
                        }
                        switch(operator){
                            case '+':
                                valueStack.push(val1 + val2);
                                break;
                            case '-':
                                valueStack.push(val1 - val2);
                                break;
                            case '/':
                                valueStack.push(val1 / val2);
                                break;
                            case '*':
                                valueStack.push(val1 * val2);
                                break;
                        }

                    } while(operator != '(');
                } else if(operatorStack.empty()
                || operatorStack.peek() == '('){
                    operatorStack.push(valueArr[i].charAt(0));

                } else if(calculatePrecedence(operatorStack.peek()) < calculatePrecedence(valueArr[i].charAt(0))
                || (!leftAssociative(valueArr[i].charAt(0))
                && calculatePrecedence(operatorStack.peek()) == calculatePrecedence(valueArr[i].charAt(0)))){
                    operatorStack.push(valueArr[i].charAt(0));

                } else if(calculatePrecedence(valueArr[i].charAt(0)) < calculatePrecedence(operatorStack.peek())
                || (leftAssociative(valueArr[i].charAt(0))
                && calculatePrecedence(operatorStack.peek()) == calculatePrecedence(valueArr[i].charAt(0)))){

                    while(calculatePrecedence(valueArr[i].charAt(0)) < calculatePrecedence(operatorStack.peek())
                    || (leftAssociative(valueArr[i].charAt(0))
                    && calculatePrecedence(operatorStack.peek()) == calculatePrecedence(valueArr[i].charAt(0)))){

                        val2 = valueStack.pop();
                        val1 = valueStack.pop();

                        switch(operatorStack.peek()){
                            case '+':
                                valueStack.push(val1 + val2);
                                break;
                            case '-':
                                valueStack.push(val1 - val2);
                                break;
                            case '/':
                                valueStack.push(val1 / val2);
                                break;
                            case '*':
                                valueStack.push(val1 * val2);
                                break;
                        }
                    }

                }

            //Else it is a parsable double
            } else {
                valueStack.push(Double.parseDouble(valueArr[i]));
            }
        }

        while(!operatorStack.empty()){
            operator = operatorStack.pop();
            val2 = valueStack.pop();
            val1 = valueStack.pop();

            switch(operator){
                case '+':
                    valueStack.push(val1 + val2);
                    break;
                case '-':
                    valueStack.push(val1 - val2);
                    break;
                case '/':
                    valueStack.push(val1 / val2);
                    break;
                case '*':
                    valueStack.push(val1 * val2);
                    break;
            }

        }

        return valueStack.peek();
    }


}