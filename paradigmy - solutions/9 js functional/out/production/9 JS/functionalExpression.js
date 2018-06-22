var cnst = function (arg) {
    return function () {
        return arg;
    }
};

var variable = function (name) {
    return function (x, y, z) {
        switch (name) {
            case "x" :
                return x;
            case "y" :
                return y;
            case "z" :
                return z;
            default:
                return -1;
        }
    }
};

var binaryOperation = function (operation) {
    return function (firstArg, secondArg) {
        return function (x, y, z) {
            return operation(firstArg(x, y, z), secondArg(x, y, z));
        }
    }
};

var unaryOperation = function (operation) {
    return function (arg) {
        return function (x, y, z) {
            return operation(arg(x, y, z));
        }
    }
};

var add = binaryOperation(function (x, y) {
    return x + y;
});

var subtract = binaryOperation(function (x, y) {
    return x - y;
});
var multiply = binaryOperation(function (x, y) {
    return x * y;
});

var divide = binaryOperation(function (x, y) {
    return x / y;
});

var negate = unaryOperation(function (x) {
    return -x;
});
var cube = unaryOperation(function (x) {
    return Math.pow(x, 3);
});
var cuberoot = unaryOperation(function (x) {
    return Math.pow(x, 1/3);
});

function parse(expression) {
    var stack =[];
    var tokens = expression.split(" ").filter(
        function (wh) {
            return wh.length > 0;
        }
    );
    //console.log(tokens.length);
    for (var i = 0; i < tokens.length; i++) {
        //console.log(tokens[i]);
        if (isNumeric(tokens[i])) {
            //console.log(tokens[i]);
            stack.push(cnst(parseInt(tokens[i])));
        } else if (tokens[i] === "x" || tokens[i] === "y" || tokens[i] === "z") {
            stack.push(variable(tokens[i]));
        } else if (tokens[i] === "negate") {
            stack.push(negate(stack.pop()));
        }
        else {
            var secondArg = stack.pop();
            var firstArg = stack.pop();
            switch (tokens[i]) {
                case '+' :
                    //console.log('v');
                    stack.push(add(firstArg, secondArg));
                    break;
                case "-" :
                    stack.push(subtract(firstArg, secondArg));
                    break;
                case "*" :
                    stack.push(multiply(firstArg, secondArg));
                    break;
                case "/" :
                    stack.push(divide(firstArg, secondArg));
                    break;
            }
        }
    }
    return stack.pop();
}

function isNumeric(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}

//var expr = "          1".split();
//console.log(parse("x 2 +"));

