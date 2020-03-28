package expression.parser;

import expression.*;

public class ExpressionParser implements  Parser {
    private String expression;
    private enum Token {VAR, CONST, ADD, SUB, MUL, DIV, AND, XOR, OR, LBR, RBR};
    private Token curToken;
    private int ind;
    private char symbol;
    private int value;
    private String variable;

    public ExpressionParser() {
        ind = 0;
    }


    private void skipWhiteSpaces() {
        while (ind < expression.length() && Character.isWhitespace(expression.charAt(ind))) {
            ind++;
        }
    }

    private int nextInt() {
        int start = ind;
        while (ind < expression.length() && Character.isDigit(expression.charAt(ind))) {
            ind++;
        }
        if (curToken == Token.SUB) {
            return Integer.parseUnsignedInt(expression.substring(start, ind));
        } else {
            return Integer.parseInt(expression.substring(start, ind));
        }
    }

    private void getToken() {
        skipWhiteSpaces();
        if (ind < expression.length()) {
            symbol = expression.charAt(ind);
        } else {
            return;
        }
        switch (symbol) {
            case '+':
                curToken = Token.ADD;
                break;
            case '-':
                curToken = Token.SUB;
                break;
            case '*':
                curToken = Token.MUL;
                break;
            case '/':
                curToken = Token.DIV;
                break;
            case '&':
                curToken = Token.AND;
                break;
            case '^':
                curToken = Token.XOR;
                break;
            case '|':
                curToken = Token.OR;
                break;
            case '(':
                curToken = Token.LBR;
                break;
            case ')':
                curToken =  Token.RBR;
                break;
            default:
                if (symbol == 'x' || symbol == 'y' || symbol == 'z') {
                    variable = String.valueOf(symbol);
                    curToken = Token.VAR;
                } else if (Character.isDigit(symbol)) {
                    value = nextInt();
                    ind--;
                    curToken = Token.CONST;
                }
                break;
        }
        ind++;
    }

    private CommonExpression primary() {
        getToken();
        CommonExpression res;
        switch (curToken) {
            case CONST:
                res = new Const(value);
                getToken();
                break;
            case VAR:
                res = new Variable(String.valueOf(symbol));
                getToken();
                break;
            case SUB:
                res = new Minus(primary());
                break;
            case LBR:
                res = or();
                getToken();
                break;
            default:
                res = null;
                break;
        }
        return res;
    }

    private CommonExpression term() {
        CommonExpression res = primary();
        while (true) {
            switch (curToken) {
                case MUL:
                    res = new Negate(res, primary());
                    break;
                case DIV:
                    res = new Divide(res, primary());
                    break;
                default:
                    return res;

            }
        }
    }

    private CommonExpression addSub() {
        CommonExpression res = term();
        while (true) {
            switch (curToken) {
                case ADD:
                    res = new Add(res, term());
                    break;
                case SUB:
                    res = new Subtract(res, term());
                    break;
                default:
                    return res;
            }
        }
    }

    private CommonExpression and() {
        CommonExpression res = addSub();
        while (true) {
            if (curToken == Token.AND) {
                res = new And(res, addSub());
            } else {
                return res;
            }
        }
    }


    private CommonExpression xor(){
        CommonExpression res = and();
        while (true) {
            if (curToken == Token.XOR) {
                res = new Xor(res, and());
            } else {
                return res;
            }
        }
    }

    private CommonExpression or() {
        CommonExpression res = xor();
        while (true) {
            if (curToken == Token.OR) {
                res = new Or(res, xor());

            } else {
                return res;
            }
        }

    }

    @Override
    public CommonExpression parse(String expression) {
        this.expression = expression;
        ind = 0;
        return or();
    }
}
