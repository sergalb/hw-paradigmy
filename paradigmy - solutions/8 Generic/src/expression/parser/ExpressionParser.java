package expression.parser;

import expression.*;
import expression.exceptions.*;
import static expression.parser.Token.*;

public class ExpressionParser<T> implements Parser<T> {

    private Token curToken;
    private Tokenizer<T> tokenizer;
    private Operation<T> operation;

    public ExpressionParser(Operation<T> operation) {
        curToken = START;
        this.operation = operation;
    }

    private TripleExpression<T> primary() throws ParsingException {
        curToken = tokenizer.getToken();
        TripleExpression<T> res;
        switch (curToken) {
            case CONST:
                res = new Const<>(tokenizer.getValue());
                curToken = tokenizer.getToken();
                break;
            case VAR:
                res = new Variable<>(String.valueOf(tokenizer.getSymbol()));
                curToken = tokenizer.getToken();
                break;
            case MINUS:
                res = new Negate<>(primary(), operation);
                break;
            case LBR:
                res = addSub();
                if (curToken != RBR) {
                    throw new SuperfluousClosingBracketException(tokenizer.getInd() - 1);
                }
                curToken = tokenizer.getToken();
                break;
            default:
                throw new ParsingException("Illegal expression");
        }
        return res;
    }

    private TripleExpression<T> term() throws ParsingException {
        TripleExpression<T> res = primary();
        while (true) {
            switch (curToken) {
                case MUL:
                    res = new Multiply<>(res, primary(), operation);
                    break;
                case DIV:
                    res = new Divide<>(res, primary(), operation);
                    break;
                default:
                    return res;
            }
        }
    }

    private TripleExpression<T> addSub() throws ParsingException {
        TripleExpression<T> res = term();
        while (true) {
            switch (curToken) {
                case ADD:
                    res = new Add<>(res, term(), operation);
                    break;
                case SUB:
                    res = new Subtract<>(res, term(),operation);
                    break;
                default:
                    return res;
            }
        }
    }

    @Override
    public TripleExpression<T> parse(String expression) throws ParsingException {
        NextNumber<T> tmp = null;
        tokenizer = new Tokenizer<>(expression, operation);
        TripleExpression<T> res = addSub();
        if (tokenizer.balance > 0) {
            throw new SuperfluousClosingBracketException(tokenizer.getInd());
        }
    return res;
    }
}
