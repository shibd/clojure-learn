(ns clojure-learn.base.clj-java
  (:require [clojure.string :as cstr])
  )

(defn my-add [numbers]
  (let [n1 (first numbers)
        n3 (nth numbers 2)]
    (+ n1 n3)))
(. "hello,clojure" indexOf "j")
(my-add [1 2 3 4])

(.indexOf "hello,clojure" "j")

(import java.awt.Point)
(let [pt (Point. 0 10)]
  (set! (.y pt) 100)
  (prn (.y pt))
  )

(defn chosse [t]
  (println
    (cond
      (instance? String t) "invalid temperature"
      (<= t 0) "freezing"
      (>= t 100) "boiling"
      true "neither")))

(defn peer-str [node]
  (str "akka://bank-account@" node ":2551"))

(defn peers
  "Constructs an initial cluster string for a test, like
  \"akka://bank-account@n0-host1:2551,akka://bank-account@n1-host2:2551...\""
  [test]
  (->> (:nodes test)
       (map (fn [node]
              (peer-str node)))
       (cstr/join ",")))

