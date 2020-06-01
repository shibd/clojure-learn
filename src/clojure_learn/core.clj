(ns clojure-learn.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(foo "Baozi")


; queue
(def schedule
  (conj clojure.lang.PersistentQueue/EMPTY
        :wake-up :shower :brush-teeth))

(peek schedule)

(def schedule (conj schedule :baozi))

(pop schedule)

(rest schedule)


;map
{:name "Clojure 分享" :author "包子"}

;set
#{:a :b :c}

(conj #{:a :b :c} :d)

(disj #{:a :b :c} :a)

;seq
(first [1 2 3])

(rest [1 2 3])

(cons 4 [1 2 3])

(cons 4 `(1 2 3))

(type (cons 4 `(1 2 3)))

(map inc [1 2 3])

(type (map inc [1 2 3]))

(type (first (map inc [1 2 3])))

