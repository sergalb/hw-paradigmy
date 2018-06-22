function Variable(name) {
    this.name = name;
    this.evaluate = function () {
        switch (name) {
            case "x" :
                return arguments[0];
            case "y" :
                return arguments[1];
            case "z" :
                return arguments[2];
            default:
                return -1;
        }
    };
}

Variable.prototype = {
    toString: function () {
        return this.name;
    },
    diff: function (dname) {
        if (this.name === dname) {
            return new Const(1);
        } else {
            return new Const(0);
        }
    }
};


function Const(value) {
    this.value = value;
    this.evaluate = function () {
        return value;
    };
}

Const.prototype = {
    toString: function () {
        return this.value.toString();
    },
    diff: function () {
        return new Const(0);
    }
};

function AbstractUnaryOperation(arg, operation, type) {
    this.arg = arg;
    this.operation = operation;
    this.type = type;
}

AbstractUnaryOperation.prototype = {
    evaluate: function () {
        return this.operation(this.arg.evaluate.apply(this.arg, arguments))
    },
    toString: function () {
        return this.arg.toString() + this.type;
    }
};


AbstractBinaryOperation.prototype = {
    evaluate: function () {
        return this.operation(
            this.firstArg.evaluate.apply(this.firstArg, arguments),
            this.secondArg.evaluate.apply(this.secondArg, arguments))
    },
    toString: function () {
        return this.firstArg.toString() + " " + this.secondArg.toString() + " " + this.type
    }
};

function AbstractBinaryOperation(firstArg, secondArg, operation, type) {
    this.firstArg = firstArg;
    this.secondArg = secondArg;
    this.operation = operation;
    this.type = type;
}


function Add(firstArg, secondArg) {
    return new AbstractBinaryOperation(firstArg, secondArg, function (x, y) {
            return x + y;
        },
        '+')
}

Add.prototype = {
    diff: function (dname) {
        return new Add(this.firstArg.diff(dname), this.secondArg.diff(dname))
    }
};
Add.prototype = AbstractBinaryOperation.prototype;

function Subtract(firstArg, secondArg) {
    return new AbstractBinaryOperation(firstArg, secondArg, function (x, y) {
        return x - y;
    }, '-');
}

Subtract.prototype = AbstractBinaryOperation.prototype;
Subtract.prototype.diff = function (dname) {
    return new Subtract(this.firstArg.diff(dname), this.secondArg.diff(dname))
};

function Multiply(firstArg, secondArg) {
    return new AbstractBinaryOperation(firstArg, secondArg, function (x, y) {
        return x * y;
    }, '*')
}

Multiply.prototype = AbstractBinaryOperation.prototype;
Multiply.prototype.diff = function (dname) {
    return new Add(new Multiply(this.firstArg.diff(dname), this.secondArg), new Multiply(this.firstArg, this.secondArg.diff(dname)))
};

function Divide(firstArg, secondArg) {
    return new AbstractBinaryOperation(firstArg, secondArg, function (x, y) {
        return x / y;
    }, '/')
}

Divide.prototype = AbstractBinaryOperation.prototype;
Divide.prototype.diff = function (dname) {
    return new Divide(new Subtract(
        new Multiply(this.firstArg.diff(dname), this.secondArg), new Multiply(this.firstArg, this.secondArg.diff(dname)))
        , new Multiply(this.secondArg, this.secondArg))
};


function Negate(arg) {
    return new AbstractUnaryOperation(arg, function (x) {
        return -x;
    }, " negate")
}

Negate.prototype = AbstractUnaryOperation.prototype;
Negate.prototype.diff = function (dname) {
    return new Negate(this.arg.diff(dname))
};

function Square(arg) {
    return new AbstractUnaryOperation(arg, function (x) {
        return Math.pow(x, 2);
    }, ' square')
}

Square.prototype = AbstractUnaryOperation.prototype;
Square.prototype = function (dname) {
    return new Multiply(new Multiply(new Const(2), this.arg), this.arg.diff(dname))
};

function Sqrt(arg) {
    return new AbstractUnaryOperation(arg, function (x) {
        return Math.sqrt(Math.abs(x))
    }, ' sqrt')
}

Sqrt.prototype = AbstractUnaryOperation.prototype;
Sqrt.prototype.diff = function (dname) {
    return new Divide(this.arg.diff(dname), new Multiply(new Const(2), this.arg))
};

function parse(expression) {
    var stack = [];
    var tokens = expression.split(" ").filter(
        function (notWhiteSpaces) {
            return notWhiteSpaces.length > 0;
        }
    );
    for (var i = 0; i < tokens.length; i++) {
        switch (tokens[i]) {
            case "negate" :
                stack.push(new Negate(stack.pop()));
                continue;
            case "square" :
                stack.push(new Square(stack.pop()));
                continue;
            case "sqrt" :
                stack.push(new Sqrt(stack.pop()));
                continue;
        }
        if (isNumeric(tokens[i])) {
            stack.push(new Const(parseInt(tokens[i])));
        } else if (tokens[i] === "x" || tokens[i] === "y" || tokens[i] === "z") {
            stack.push(new Variable(tokens[i]));
        } else {
            var secondArg = stack.pop();
            var firstArg = stack.pop();
            switch (tokens[i]) {
                case '+' :
                    stack.push(new Add(firstArg, secondArg));
                    break;
                case "-" :
                    stack.push(new Subtract(firstArg, secondArg));
                    break;
                case "*" :
                    stack.push(new Multiply(firstArg, secondArg));
                    break;
                case "/" :
                    stack.push(new Divide(firstArg, secondArg));
                    break;
            }
        }
    }
    return stack.pop();
}

function isNumeric(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}


/*
var expr = new Negate(new Negate(new Const(2)));
console.log(expr.evaluate(0, 4, 3));
*/

