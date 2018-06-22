package expression.parser;

import expression.exceptions.*;
import static expression.parser.Token.*;

class Tokenizer {
    String expression;
    private Token curToken;
    int ind;
    char symbol;
    int value;
    String variable;
    int balance;

    Tokenizer(String expression) {
        this.expression = expression;
        this.curToken = START;
        this.ind = 0;
        this.balance = 0;
    }

    private void checkFunction(String funcName, int length, Token nextToken) throws MissingOperationException, IllegalTextException {
        checkLeftOperation();
        if (ind + length - 1 < expression.length() && expression.substring(ind, ind + length).equals(funcName)) {
            curToken = nextToken;
            ind += length - 1;
        } else {
            throw new IllegalTextException(ind, expression);
        }
    }

    boolean checkLeftOperand(boolean throwException) throws MissingOperandException {
        switch (curToken) {
            case CONST:
                return true;
            case VAR:
                return true;
            case RBR:
                return true;
            default:
                if (throwException) {
                    throw new MissingOperandException(ind, expression);
                }
        }
        return false;
    }

    private void checkLeftOperation() throws MissingOperationException {
        switch (curToken) {
            case CONST:
                throw new MissingOperationException(ind, expression);
            case VAR:
                throw new MissingOperationException(ind, expression);
            case RBR:
                throw new MissingOperationException(ind, expression);
        }
    }

    private void skipWhiteSpaces() {
        while (ind < expression.length() && Character.isWhitespace(expression.charAt(ind))) {
            ind++;
        }
    }

    private void nextInt(boolean negate) throws IllegalConstantException {
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
            throw new IllegalConstantException(expression.substring(start,ind), ind, expression);
        } catch (StringIndexOutOfBoundsException e) {
            System.err.println(expression);
        }
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
                        throw new MissingOperandException(ind, expression);
                    } else if (Character.isDigit(expression.charAt(ind))) {
                        nextInt(true);
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
            case '&':
                checkLeftOperand(true);
                curToken = AND;
                break;
            case '^':
                checkLeftOperand(true);
                curToken = XOR;
                break;
            case '|':
                checkLeftOperand(true);
                curToken = OR;
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
                    throw new SuperfluousClosingBracketException(ind, expression);
                }
                balance--;
                break;
            case '~':
                checkLeftOperation();
                curToken = NOT;
                break;
            case 'c':
                checkFunction("count", 5, COUNT);
                break;
            case 'l':
                checkFunction("log10", 5, LOG);
                break;
            case 'p':
                checkFunction("pow10", 5, POW);
                break;
            default:
                if (symbol == 'x' || symbol == 'y' || symbol == 'z') {
                    checkLeftOperation();
                    variable = String.valueOf(symbol);
                    curToken = VAR;
                } else if (Character.isDigit(symbol)) {
                    checkLeftOperation();
                    if (curToken == MINUS) {
                        nextInt(true);
                    } else {
                        nextInt(false);
                    }
                    ind--;
                    curToken = CONST;
                } else {
                    throw new IllegalTextException(ind, expression);
                }
                break;
        }
        ind++;
        return curToken;
    }
}
