(defn AbstractBinaryOperation [operation]
  (fn [firstArg secondArg]
    (fn [nameVar] (operation
                    (firstArg nameVar)
                    (secondArg nameVar)))))

(defn AbstractUnaryOperation [operation]
  (fn [arg]
    (fn [nameVar] (operation
                    (arg nameVar)))))

(def negate (AbstractUnaryOperation
              (fn [x] (- x))))
(def add (AbstractBinaryOperation
           (fn [x y] (+ x y))))
(def subtract (AbstractBinaryOperation
                (fn [x y] (- x y))))
(def multiply (AbstractBinaryOperation
                (fn [x y] (* x y))))
(def divide (AbstractBinaryOperation
              (fn [x y] (/ (double x) (double y)))))

(defn constant [val] (fn [& args] val))

(defn variable [name] (fn [mapping] (mapping name)))
(def sinh (AbstractUnaryOperation
            (fn [x] (Math/sinh x))))
(def cosh (AbstractUnaryOperation
              (fn [x] (Math/cosh x))))

(def operations {'+ add '- subtract '* multiply '/ divide 'negate negate 'sinh sinh 'cosh cosh})

(defn parseFunction [expr]
  (letfn [(parse [split] (cond
                (number? split) (constant split)
                (symbol? split) (variable (str split))
                (list? split) (let [[first & other] split, op (operations first)]
                               (apply op (map parse other)))))]
    (parse (read-string expr))))


;(print (type (read-string "(def)")))
;
;(def expr
;  (subtract
;    (multiply
;      (constant 2)
;      (variable "x"))
;    (constant 3)))
;
;(def expr (variable "z"))
;(def expr (divide (negate (variable "x")) (constant 2.0)))
;(print (expr {"x" 1 "z" 0 "y" 0}))

;(def tmp- -)
;(def tmp* *)
;(def tmp-div /)
;(def + add)
;(def - subtract)
;(def * multiply)
;(def / divide)
;
;(defn parse [split-expr] (cond
;                           (number? split-expr) (constant split-expr)
;                           (symbol? split-expr) (variable (str split-expr))
;                           (list? split-expr) (let [[first & other] split-expr]
;                                                (apply first (map parse other)))))
;(defn parseFunction [expr] (parse (read-string expr)))
;(def expression (parseFunction "(* 2 x)"))
;(print (expression {"x" 5}))
;(print (= (str (read-string "(+ 1 1)"))))
;(def x (str (read-string "(x)")))
;(print (identical? x "x"))
;(def vr (variable x))
;(def vr (variable "x"))
;(print (vr {"x" 1}))

;(def expr (variable "x"))
;(print (expr {"x" 2}))
;(print (identical? expression expr))
;
;(def tmp+ +)
;(def + add)
;(def +)
;(print (tmp+ 1 2))
;(def ex (eval (+ (constant 10) (constant 12))))
;(print (ex {"x" 1}))
;(def + tmp+)
;(print (+ 3 4))



;(def expr (divide (constant 5.0) (constant 0)))
;(print (identical? ((constant 5.0) {"z" 0}) 5.0))
;(print (/ 5.0 0))
;(print (expr {"z" 0}))

