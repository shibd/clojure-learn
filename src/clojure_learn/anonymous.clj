(ns com.baozi.anonymous
  (:require [clojure.string :as str]))

(defn indexable-word? [w] (> (count w) 2))

(defn string [val] (str val))

(prn (indexable-word? "good"))

(prn (filter (fn [w] (> (count w) 2)) (str/split "A good day it is" #"\W+")))
(prn (filter (string(str/split "A good day it is" #"\W+"))))

(prn (filter #(> (count %) 2) (str/split "A good day it is" #"\W+")))

((fn [name]  (str "hello " name)) "world")

(defn -main [& args]

  )
