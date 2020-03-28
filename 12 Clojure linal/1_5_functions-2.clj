
(defn lecture [name]
  (println)
  (let [line (clojure.string/join (repeat (+ 16 (count name)) "="))]
    (println line)
    (println "=== Lecture" name "===")
    (println line)))

(defn chapter [name]
  (println)
  (println "==========" name "=========="))

(defn section [name]
  (println)
  (println "---" name "---"))


(defn safe-eval [expression]
  (try
    (str (eval expression))
    (catch Throwable e (str (.getSimpleName (type e)) ": " (.getMessage e)))))

(defn example' [prefix & expressions]
  (letfn [(out [expression]
            (str expression " -> " (safe-eval expression)))]
    (println (clojure.string/join "\n        " (cons (str "    " prefix) (map out expressions))))))

(defn example [description & expressions]
  {:pre [(not (empty? expressions))]}
  (apply example' (cons (str description ": ") expressions)))
(chapter "Custom Functions")

(section "Simple Functions")
(example "Define function"
         '(defn square [x] (* x x)))
(example "Use function" '(square 8))
(example "Nullary function"
         '(defn nullary [] 10)
         '(nullary))
(example "Anonymous functions"
         '((fn [x] (+ x x)) 10)
         '(#(+ % %) 10)
         '(#(+ %1 %2) 10 20))
(example "Functions as values"
         '(defn twice [f] (fn [a] (f (f a))))
         '((twice square) 3))

(section "Recursive Functions")

(example "Recursive Fibonacci"
         '(defn rec-fib [n]
            (cond
              (== 0 n) 1
              (== 1 n) 1
              :else (+ (rec-fib (- n 1)) (rec-fib (- n 2)))))
         '(rec-fib 40))

(example "Memoized Fibonacci"
         '(def mem-fib
            (memoize
              (fn [n]
                (cond
                  (== 0 n) 1
                  (== 1 n) 1
                  :else (+ (mem-fib (- n 1)) (mem-fib (- n 2)))))))
         '(mem-fib 90))

(example "Tail-recursive Fibonacci"
         '(defn iter-fib [n]
            (letfn [(iter-fib' [n a b]
                      (if (== 0 n)
                        a
                        (iter-fib' (- n 1) b (+' a b))))]
              (iter-fib' n 1 1)))
         '(iter-fib 90))

(example "Explicit loop Fibonacci"
         '(defn loop-fib [n]
            (loop [n n a 1 b 1]
              (if (== 0 n)
                a
                (recur (- n 1) b (+ a b)))))
         '(loop-fib 90))

(section "Pre and Post conditions")
(example "Fast power"
         '(defn power [a b]
            {:pre [(<= 0 b)]
             :post [(or (== 0 b) (== 0 a) (== 0 (rem % a)))]}
            (cond
              (= 0 b) 1
              (= 1 b) a
              (even? b) (power (* a a) (quot b 2))
              (odd? b) (* a (power a (- b 1))))))
(example "Pre and posconditions ok"
         '(power 2 5)
         '(power 2 0)
         '(power 0 2))
(example "Precondition violated"
         '(power 2 -5))

(example "Invalid postcondition"
         '(defn ipower
            [a b]
            {:pre [(<= 0 b)]
             :post [(= 0 (rem % a)) (<= 0 %)]}
            (power a b)))
(example "First part of invalid postcondition violated"
         '(ipower 2 0)
         '(power -2 3))
(example "Second part of invalid postcondition violated"
         '(ipower -2 3))
(chapter "Lists")

(example "Lists"
         '(list 1 2)
         '(list 1 2 "Hello" 3 4)
         '(list))
(example "List variable" '(def lst (list 1 2 "Hello" 3 4)))
(example "List test"
         '(list? lst)
         '(list? (list 1))
         '(list? ()))

(section "Operations")
(example "Length" '(count lst))
(example "Head" '(first lst))
(example "Tail" '(rest lst))
(example "Last" '(last lst))
(example "Indexing"
         '(nth lst 0)
         '(nth lst 1)
         '(nth lst 2)
         '(nth lst 10))
(example "Add element" '(cons 0 lst))
(example "Add elements" '(conj lst 0 -1))
(example "Emptiness test"
         '(empty? (rest (list 1)))
         '(empty? (list))
         '(empty? ())
         '(empty? lst))

(section "Folds")

(example "Left fold"
         '(defn foldLeft
            "Applies a binary operator f to a zero value and all elements of the list, going left to right"
            [zero f items]
            (if (empty? items)
              zero
              (foldLeft (f zero (first items)) f (rest items))))
         '(foldLeft 0 + (list 1 2 3 4)))

(example "Right fold"
         '(defn foldRight [zero f items]
            "Applies a binary operator f to a zero value and all elements of the list, going right to left"
            (if (empty? items)
              zero
              (f (first items) (foldRight zero f (rest items)))))

         '(foldRight 1 * (list 1 2 3 4)))

(example "Tail-call optimised left fold"
         '(defn foldLeft' [zero f items]
            (if (empty? items)
              zero
              (recur (f zero (first items)) f (rest items))))
         '(count (range 1000000))
         '(foldLeft 0 + (range 1000000))
         '(foldLeft' 0 + (range 1000000)))

(chapter "Vectors")

(example "Vectors"
         '(vector 1 2)
         '(vector 1 2 "Hello" 3 4)
         '[1 2]
         '(def vect [1 2 "Hello" 3 4]))

(example "Queries"
         '(count vect)
         '(nth vect 2)
         '(vect 2)
         '(vect 10))

(example "Modifications"
         '(conj [1 2] 3 4)
         '(peek [1 2])
         '(pop [1 2])
         '(assoc [1 2] 0 10)
         '(assoc [1 2 3] 0 10 2 20)
         )


(chapter "High-order Functions")

(section "Ordinary functions")
(example "Identity function" '(identity [1 2 3]))
(example "Constant function" '((constantly 10) 20 30))

(section "High-order functions")
(example "Function composition" '((comp square square square) 2))
(example "Currying"
         (def sum (partial foldLeft' 0 +))
         (sum [1 2 3]))
(example "Application" (apply + [1 2 3]))
(example "Map" (mapv (fn [n] (+ 1 n)) [1 2 3]))
(example "Juxtaposition" '((juxt + - * /) 1 2 3 4))

(section "Variable-argument functions")
(example "Sum of squares"
         '(defn sumSquares [& xs] (apply + (map square xs)))
         '(sumSquares 3 4))
(chapter "JavaScript-like objects")

(example "Maps as objects"
         '(def point {:x 10 :y 20})
         'point
         '(point "x"))

(section "Prototypes")
(example "Object with prototype"
         '(def shifted-point {:prototype point :dx 1 :dy 2 :y 100}))
(example "Get with prototype"
         (defn proto-get [obj key]
           (cond
             (contains? obj key) (obj key)
             :else (proto-get (:prototype obj) key))))
(example "Own property"
         '(proto-get shifted-point :dx))
(example "Inherited property"
         '(proto-get shifted-point :x))
(example "Overridden property"
         '(proto-get shifted-point :y))

(section "Methods")
(example "Points with methods"
         (def point
           {:x 10
            :y 20
            :getX (fn [this] (proto-get this :x))
            :getY (fn [this] (proto-get this :y))
            })
         (def shifted-point
           {:prototype point
            :dx 1
            :dy 2
            :getX (fn [this] (+ (proto-get this :x) (proto-get this :dx)))
            :getY (fn [this] (+ (proto-get this :y) (proto-get this :dy)))
            :add (fn [this a b] (+ a b))
            }))
(example "Call method"
         (defn proto-call [this key & args]
           (apply (proto-get this key) (cons this args))))

(example "Own method"
         (proto-call point :getX))
(example "Overridden method"
         (proto-call shifted-point :getX))
(example "Multi-argument method"
         (proto-call shifted-point :add 2 3))

(section "Syntactic sugar")
(example "Field declaration"
         (defn field [key]
           (fn [name] (proto-get name key))))
(example "Method declaration"
         (defn method [key]
           (fn [this & args] (apply proto-call this key args))))
(example "Fields"
         (def _x (field :x))
         (def _y (field :y))
         (def _dx (field :dx))
         (def _dy (field :dy)))
(example "Methods"
         (def _getX (method :getX))
         (def _getY (method :getY))
         (def _add (method :add)))
(example "Points"
         (def point
           {:x 10
            :y 20
            :getX (fn [this] (_x this))
            :getY (partial _y)
            })
         (def shifted-point
           {:prototype point
            :dx 1
            :dy 2
            :getX (fn [this] (+ (_x this) (_dx this)))
            :getY (fn [this] (+ (_y this) (_dy this)))
            :add (fn [this a b] (+ a b))
            }))
(example "Fields usage"
         (_x point)
         (_x shifted-point)
         (_dx shifted-point))
(example "Methods usage"
         (_getX point)
         (_getY shifted-point)
         (_add shifted-point 2 3))

(section "Constructors")
(example "Constructor declaration"
         (defn constructor [ctor prototype]
           (fn [& args] (let [map (apply ctor {:prototype prototype} args)]
                          (let [me (fn [key & args] (apply key map args))]
                            me)))))
(example "Supertype"
         (def PointPrototype
           {:getX (partial _x)
            :getY (partial _y)
            })
         (defn Point [this x y]
           (assoc this
                  :x x
                  :y y))
         (def new:Point (constructor Point PointPrototype)))
(example "Subtype"
         (def ShiftedPointPrototype
           (assoc PointPrototype
                  :getX (fn [this] (+ (_x this) (_dx this)))
                  :getY (fn [this] (+ (_y this) (_dy this)))
                  :add (fn [this a b] (+ a b))))
         (defn ShiftedPoint [this x y dx dy]
           (assoc (Point this x y)
                  :dx dx
                  :dy dy
                  ))
         (def new:ShiftedPoint (constructor ShiftedPoint ShiftedPointPrototype)))

(example "Instances"
         (def point (new:Point 10 20))
         (def shifted-point (new:ShiftedPoint 10 20 1 2))
         (point _getX)
         (shifted-point _getX)
         (shifted-point _add 2 3)
         (point _x)
         (shifted-point _x)
         (shifted-point _dx))

