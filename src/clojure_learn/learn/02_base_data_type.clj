(ns clojure-learn.learn.02-base-data-type
  (:import (clojure.lang PersistentQueue)))
;基本数据结构学习

;>>>>>>>>base
(type 2)
;;=> java.lang.Long boot.user
(type 1/3)
;;=> clojure.lang.Ratio boot.user
(type 3.14)
;;=> java.lang.Double
(type "hello")
;;=> java.lang.String boot.user
(type \a)
;;=> java.lang.Character
(type true)
;;=> java.lang.Boolean boot.user
(type false)
;;=> java.lang.Boolean
(type 'a)
;;=> clojure.lang.Symbol  todo 重点
(type :a)
;;=> clojure.lang.Keyword todo 重点

;>>>>>>>>list
'(1 2 3)
(= (list 1 2) (list 1 2))
;;=> true
(type '(1 2 3))
;;=> clojure.lang.PersistentList
(conj '(1 2 3) 4)
;;=> (4 1 2 3)  从头加

;>>>>>>>>vector as stack
[1 2 3]
(type [1 2 3])
(vector 1 2 3)
(vec (list 1 2 3))
(conj [1 2 3] 4)
([:a "ss" :c] 1)

;>>>>>>>>queue
(def schedule
  (conj PersistentQueue/EMPTY
        :wake-up :shower :brush-teeth))

(peek schedule)

(def schedule (conj schedule :baozi))

(pop schedule)

(rest schedule)


;>>>>>>>>map
{:name "Clojure 分享" :author "包子"}
(assoc {:name "Clojure 分享" :author "jiacai"} :rates "五星")
(dissoc {:name "Clojure 分享" :author "jiacai" :rates "五星"} :rates)
(get {:name "Clojure 分享" :author "jiacai"} :name)
(:name {:name "Clojure 分享" :author "jiacai"})


;>>>>>>>>set
#{:a :b :c}

(conj #{:a :b :c} :d)

(disj #{:a :b :c} :a)

(contains? #{1 2 3} 3)

;>>>>>>>>seq
(first [1 2 3])

(rest [1 2 3])

(cons 4 [1 2 3])

(cons 4 `(1 2 3))

(type (cons 4 `(1 2 3)))

(map inc [1 2 3])

(type (map inc [1 2 3]))

(type (first (map inc [1 2 3])))

;>>>>>>>>>seq接口
;;(first seq)
;;(rest seq)
;;(cons item seq)

