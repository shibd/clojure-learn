(ns clojure-learn.base.fib-seq)

;; 定义斐波那契数列 (defn fib []
(defn fib []
  (defn fib-iter [a b]
    (lazy-seq (cons a (fib-iter b (+ a b)))))
  (fib-iter 0 1))


(take 10 (fib))


