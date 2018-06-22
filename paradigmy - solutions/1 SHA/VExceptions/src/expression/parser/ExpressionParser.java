package expression.parser;

import expression.*;
import expression.exceptions.*;

public class ExpressionParser implements Parser {
    private String expression;
    private int ind;
    private enum Token {CONST, VARIABLE, ADD, SUB, DIV, MUL, NULL, END, MINUS, LBR, RBR}
    private Token currentToken = Token.NULL;
    private int value;
    private char variableName;
    private int balance;

    public ExpressionParser() {
        currentToken = Token.NULL;
        ind = 0;
        balance = 0;
    }

    private boolean isOperation(Token token) {
        return token == Token.ADD || token == Token.SUB || token == Token.MUL || token == Token.DIV;
    }
    private boolean isUnary(Token token) {
        return token == Token.CONST || token == Token.VARIABLE || token == Token.RBR;
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
            throw new IllegalConstantException(expression.substring(start,ind), ind);
        }
        currentToken = Token.CONST;
    }

    private void skipWhiteSpace() {
        while (ind < expression.length() && Character.isWhitespace(expression.charAt(ind))) {
            ind++;
        }
    }

    private void getToken() throws ParsingException {
        skipWhiteSpace();
        if (ind >= expression.length()) {
            currentToken = Token.END;
            return;
        }

        char ch = expression.charAt(ind);
        switch (ch) {
            case '+':
                currentToken = Token.ADD;
                break;
            case '-':
                if (isUnary(currentToken)) {
                    currentToken = Token.SUB;
                } else {
                    ind++;
                    skipWhiteSpace();
                    if (ind >= expression.length()) {
                        throw new MissingOperandException("last", expression, ind);
                    } else if (Character.isDigit(expression.charAt(ind))) {
                        nextInt(true);
                    } else {
                        currentToken = Token.MINUS;
                    }
                    ind--;
                }
                break;
            case '*':
                currentToken = Token.MUL;
                break;
            case '/':
                currentToken = Token.DIV;
                break;
            case '(':
                currentToken = Token.LBR;
                balance++;
                break;
            case ')':
                currentToken = Token.RBR;
                balance--;
                if (balance < 0)
                    throw new SuperfluousOpeningBracketsException(expression, ind);
                break;
            default:
                if (Character.isDigit(ch)) {
                    nextInt(false);
                    ind--;
                } else if (ch == 'x' || ch == 'y' || ch == 'z') {
                    currentToken = Token.VARIABLE;
                    variableName = ch;
                } else {
                    currentToken = Token.NULL;
                    throw new UnknownIdentifierException(expression, ind);
                }
        }
        ind++;
    }

    private TripleExpression prim() throws ParsingException {
        Token prevToken = currentToken;
        getToken();
        TripleExpression result;
        switch (currentToken) {
            case CONST:
                result = new Const(value);
                getToken();
                break;
            case VARIABLE:
                result = new Variable(variableName);
                getToken();
                break;
            case MINUS:
                result = new CheckedNegate(prim());
                break;
            case LBR:
                result = addSub();
                if (currentToken != Token.RBR) {
                    throw new SuperfluousClosingBracketsException(expression, ind - 1);
                }
                getToken();
                break;
            default:
                if (currentToken != Token.END) {
                    String e;
                    if (isOperation(prevToken) && isOperation(currentToken)) {
                        e = "middle";
                    } else if (isOperation(prevToken) && !isOperation(currentToken)) {
                        e = "last";
                    } else if (!isUnary(prevToken) && isOperation(currentToken)) {
                        e = "first";
                    } else {
                        e = "Incorrect expression";
                    }
                    throw new MissingOperandException(e, expression, ind);
                }
                else
                    throw new MissingOperandException("last", expression, ind);

        }
        if (isUnary(prevToken) && isUnary(currentToken) && currentToken != Token.RBR) {
            throw new MissingOperationException(expression + prevToken + currentToken, ind);
        }
        return result;
    }

    private TripleExpression term() throws ParsingException{
        TripleExpression result = prim();
        while (true) {
            switch (currentToken) {
                case MUL:
                    result = new CheckedMultiply(result, prim());
                    break;
                case DIV:
                    result = new CheckedDivide(result, prim());
                    break;
                default:
                    return result;
            }
        }
    }

    private TripleExpression addSub() throws ParsingException {
        TripleExpression result = term();
        while (true) {
            switch(currentToken) {
                case ADD:
                    result = new CheckedAdd(result, term());
                    break;
                case SUB:
                    result = new CheckedSubtract(result, term());
                    break;
                default:
                    return result;
            }
        }
    }

    public TripleExpression parse(String expression) throws ParsingException {
        this.expression = expression;
        currentToken = Token.NULL;
        ind = 0;
        balance = 0;
        TripleExpression result = addSub();
        if (balance > 0)
            throw new SuperfluousClosingBracketsException(expression + balance, ind);

        return result;
    }
}