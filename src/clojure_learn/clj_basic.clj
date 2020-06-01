(ns clojure-learn.clj-basic)

(defn foo [x] (prn x "Hello,Clojure!"))

(foo "LightSword Say:")

;; 定义自然数序列
(defn naturals [] (iterate inc 1))
;; 定义奇数序列
(defn odds [] (filter odd? (naturals)))
;; 定义偶数序列
(defn evens [] (filter even? (naturals)))
;; 定义斐波那契数列 (defn fib []
(defn fib []
  (defn fib-iter [a b] (lazy-seq (cons a (fib-iter b (+ a b)))) )
  (fib-iter 0 1)
  )

(defn fib [] (map first (iterate (fn [[a b]] [b (+ a b)]) [0 1])))

(prn (take 10 ((fn [[a b]] [b (+ a b)]) [0 1])))
(prn (take 10 (iterate (fn [[a b]] [b (+ a b)]) [0 1])))
(prn (take 10 (naturals)))
(prn (take 10 (odds)))
(prn (take 10 (evens)))
(prn (take 10 (fib)))
(prn (take 10 (map * (naturals) (drop 1 (naturals)))))

(prn (.indexOf "hello,clojure" "j"))
(prn (. "hello,clojure" indexOf "j"))



(defn -main [& args]
  "this is main"
  )


