(ns com.baozi.clj-web
  (:require [clojure.string :as cstr])
  )

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(prn (my-add [1 2 3 4]))

(prn (.indexOf "hello,clojure" "j"))

(defn my-add [numbers]
  (let [n1 (first numbers)
        n3 (nth numbers 2)]
    (+ n1 n3)))
(prn (. "hello,clojure" indexOf "j"))

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

(defn -main [& args]
  (foo "baozi")
  (chosse 10)
  (prn (peers {"n1 n2"}))
  )


