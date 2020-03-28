package expression.parser;

import expression.Operation;
import expression.exceptions.*;

import static expression.parser.Token.*;

class Tokenizer<T> {
    private String expression;
    private Token curToken;
    private int ind;
    private char symbol;
    private NextNumber<T> nextNumber;
    private Operation<T> operation;
    private T value;
    String variable;
    int balance;

    Tokenizer(String expression, Operation<T> operation) {
        this.expression = expression;
        //this.nextNumber = nextNumber;
        this.operation = operation;
        curToken = START;
        ind = 0;
        balance = 0;
    }

    public int getInd() {
        return ind;
    }

    char getSymbol() {
        return symbol;
    }

    T getValue() {
        return value;
    }

    private void checkFunction(String funcName, int length, Token nextToken) throws MissingOperationException, IllegalTextException {
        checkLeftOperation();
        if (ind + length - 1 < expression.length() && expression.substring(ind, ind + length).equals(funcName)) {
            curToken = nextToken;
            ind += length - 1;
        } else {
            throw new IllegalTextException(ind);
        }
    }

    private boolean checkLeftOperand(boolean throwException) throws MissingOperandException {
        switch (curToken) {
            case CONST:
                return true;
            case VAR:
                return true;
            case RBR:
                return true;
            default:
                if (throwException) {
                    throw new MissingOperandException(ind);
                }
        }
        return false;
    }

    private void checkLeftOperation() throws MissingOperationException {
        switch (curToken) {
            case CONST:
                throw new MissingOperationException(ind);
            case VAR:
                throw new MissingOperationException(ind);
            case RBR:
                throw new MissingOperationException(ind);
        }
    }

    private void skipWhiteSpaces() {
        while (ind < expression.length() && Character.isWhitespace(expression.charAt(ind))) {
            ind++;
        }
    }

    /*private void nextInt(boolean negate) throws IllegalConstantException {
        int start = ind;
        while (ind < expression.length() && Character.isDigit(expression.charAt(ind))) {
            ind++;
        }
        try {
            if (negate) {
                value = Integer.parseInt("-" + expression.substring(start, ind));
            } else {
                value = Integer.parseInt(expression.substring(start, ind));
            }
        } catch (NumberFormatException e) {
            throw new IllegalConstantException(expression.substring(start,ind), ind);
        }
    }*/

    private String getStrNumber() {
        int start = ind;
        char curSymbol = expression.charAt(ind);
        while (ind < expression.length() && (Character.isDigit(curSymbol)) || curSymbol == '.' || curSymbol == 'e') {
            ind++;
            if (ind < expression.length()) {
                curSymbol = expression.charAt(ind);
            }
        }
        return expression.substring(start, ind);
    }

    Token getToken() throws ParsingException {
        skipWhiteSpaces();
        if (ind < expression.length()) {
            symbol = expression.charAt(ind);
        } else {
            return END;
        }
        switch (symbol) {
            case '+':
                checkLeftOperand(true);
                curToken = ADD;
                break;
            case '-':
                if (checkLeftOperand(false)) {
                    curToken = SUB;
                } else {
                    ind++;
                    skipWhiteSpaces();
                    if (ind >= expression.length()) {
                        throw new MissingOperandException(ind);
                    } else if (Character.isDigit(expression.charAt(ind))) {
                        value = nextNumber.nextNumber("-" + getStrNumber());
                        curToken = CONST;
                    } else {
                        curToken = MINUS;
                    }
                    ind--;
                }
                break;
            case '*':
                checkLeftOperand(true);
                curToken = MUL;
                break;
            case '/':
                checkLeftOperand(true);
                curToken = DIV;
                break;
            case '(':
                checkLeftOperation();
                curToken = LBR;
                balance++;
                break;
            case ')':
                checkLeftOperand(true);
                curToken = RBR;
                if (balance == 0) {
                    throw new SuperfluousClosingBracketException(ind);
                }
                balance--;
                break;
            default:
                if (symbol == 'x' || symbol == 'y' || symbol == 'z') {
                    checkLeftOperation();
                    variable = String.valueOf(symbol);
                    curToken = VAR;
                } else if (Character.isDigit(symbol)) {
                    checkLeftOperation();
                    if (curToken == MINUS) {
                        value = operation.nextNumber("-" + getStrNumber());
                    } else {
                        value = operation.nextNumber(getStrNumber());
                    }
                    ind--;
                    curToken = CONST;
                } else {
                    throw new IllegalTextException(ind);
                }
                break;
        }
        ind++;
        return curToken;
    }
}
