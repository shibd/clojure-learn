(ns clojure-learn.learn.03-lazy-persistent)
;惰性求值&Persistent学习

;>>>>>>>>Call by name vs Call by value 运行不起来?
(defn some-condition
  "test some condition"
  true)
(defn do-some-magic [cheap expensive] (if (some-condition)
                                        (force expensive)
                                        cheap))
(do-some-magic "tom"
               (delay (Thread/sleep 10) "jerry"))

;>>>>>>>>infinite sequences(无穷序列)
(take 5 (range))
;;-> (0 1 2 3 4)

(def powers-of-two (iterate (partial * 2) 1))
(take 10 powers-of-two)
;;-> (1 2 4 8 16 32 64 128 256 512)

(nth powers-of-two 11)
;;-> 2048

;>>>>>>>>chunked sequences
(def gimme #(do (print \.) %))
(def foo (map gimme (range 64)))
(take 1 (map gimme (range 32)))
;;-> (0)................................

(take 4 foo)
;;-> (0 1 2 3)................................

(take 5 foo)
;;-> (0 1 2 3 5)

(nth foo 32) ;; 求第33个元素时才再次求值
;; -> ................................32


;>>>>>>>> persistent bit-partitioned vector trie
; persistent (版本数据均可见 数组 前缀树)
; bit-partitioned (位分区)
; vector (数组,连续内存)
; trie (前缀树(词频统计))



