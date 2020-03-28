package expression.parser;

import expression.*;
import expression.exceptions.*;
import static expression.parser.Token.*;

public class ExpressionParser implements Parser {

    private Token curToken;
    private Tokenizer tokenizer;

    public ExpressionParser() {
        curToken = START;
    }

    private TripleExpression primary() throws ParsingException {
        curToken = tokenizer.getToken();
        TripleExpression res;
        switch (curToken) {
            case CONST:
                res = new Const(tokenizer.value);
                curToken = tokenizer.getToken();
                break;
            case VAR:
                res = new Variable(String.valueOf(tokenizer.symbol));
                curToken = tokenizer.getToken();
                break;
            case MINUS:
                res = new CheckedNegate(primary());
                break;
            case LBR:
                res = or();
                if (curToken != RBR) {
                    throw new SuperfluousClosingBracketException(tokenizer.ind - 1, tokenizer.expression);
                }
                curToken = tokenizer.getToken();
                break;
            case NOT:
                res = new Not(primary());
                break;
            case COUNT:
                res = new Count(primary());
                break;
            case LOG:
                res = new CheckedLg(primary());
                break;
            case POW:
                res = new CheckedPow(primary());
                break;
            default:
                throw new ParsingException("Illegal expression");
        }
        return res;
    }

    private TripleExpression term() throws ParsingException {
        TripleExpression res = primary();
        while (true) {
            switch (curToken) {
                case MUL:
                    res = new CheckedMultiply(res, primary());
                    break;
                case DIV:
                    res = new CheckedDivide(res, primary());
                    break;
                default:
                    return res;

            }
        }
    }

    private TripleExpression addSub() throws ParsingException {
        TripleExpression res = term();
        while (true) {
            switch (curToken) {
                case ADD:
                    res = new CheckedAdd(res, term());
                    break;
                case SUB:
                    res = new CheckedSubtract(res, term());
                    break;
                default:
                    return res;
            }
        }
    }

    private TripleExpression and() throws ParsingException {
        TripleExpression res = addSub();
        while (true) {
            if (curToken == AND) {
                res = new And(res, addSub());
            } else {
                return res;
            }
        }
    }


    private TripleExpression xor() throws ParsingException {
        TripleExpression res = and();
        while (true) {
            if (curToken == XOR) {
                res = new Xor(res, and());
            } else {
                return res;
            }
        }
    }

    private TripleExpression or() throws ParsingException {
        TripleExpression res = xor();
        while (true) {
            if (curToken == OR) {
                res = new Or(res, xor());

            } else {
                return res;
            }
        }

    }

    @Override
    public TripleExpression parse(String expression) throws ParsingException {
        tokenizer = new Tokenizer(expression);
        TripleExpression res = or();
        if (tokenizer.balance > 0) {
            throw new SuperfluousClosingBracketException(tokenizer.ind, tokenizer.expression);
        }
    return res;
    }

}
