(defn commonVM [op] (fn [v1 v2] (mapv op v1 v2)))
(def v+ (commonVM +))
(def v- (commonVM -))
(def v* (commonVM *))
(defn scalar [v1, v2] (apply + (v* v1 v2)))
(defn vect [v1, v2] (let [[a1 a2 a3] v1 [b1 b2 b3] v2]
                    [(- (* a2 b3) (* a3 b2)) (- (* a3 b1) (* a1 b3)) (- (* a1 b2) (* a2 b1))]))
(defn v*s [v1, s] (mapv (fn [n] (* s n)) v1))

(def m+ (commonVM v+))
(def m- (commonVM v-))
(def m* (commonVM v*))
(defn m*s [m, s] (mapv (fn [n] (v*s n s)) m))
(defn m*v [m, v] (mapv (fn [n] (scalar n v)) m))
(defn transpose [m] (apply mapv vector m))
(defn m*m [m1, m2] (mapv (fn [n] (mapv (fn [m] (scalar n m)) (transpose m2))) m1))

(defn commonDifForm [op] (fn req [x1 x2] (cond (number? x1) (op x1 x2) :else (mapv req x1 x2))))
(def s+ (commonDifForm +))
(def s- (commonDifForm -))
(def s* (commonDifForm *))

;(print (s+ [[1 2] 3] [[1 2] 3]))
;number? x1) (+ x1 x2) (s+ x1 x2)