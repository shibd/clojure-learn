(ns clojure-learn.learn.00-clojure-base
  (:require [clojure.string :as str]))
;展示clojure中如何定义函数、如何声明变量等各种操作,不再课程中内

;; 定义函数 def、defn
(defn string [val] (str val))

(defn foo [x] (prn x "Hello,Clojure!"))
(foo "LightSword Say:")

(defn indexable-word? [w]
  (> (count w) 2))
(indexable-word? "good")

;; 定义抽象函数 fn、#
(filter
  (fn [w] (> (count w) 2))
  (str/split "A good day it is" #"\W+"))
(filter #(> (count %) 2) (str/split "A good day it is" #"\W+"))
((fn [name] (str "hello " name)) "world")


;;函数内声明 fn
(defn my-add [numbers]
  (let [n1 (first numbers)
        n3 (nth numbers 2)]
    (+ n1 n3)))
(my-add [1 2 3 4])
