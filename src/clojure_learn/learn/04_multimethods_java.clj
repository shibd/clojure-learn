(ns clojure-learn.learn.04-multimethods-java
  (:import (java.net InetSocketAddress)
           (clojure.lang ISeq)))

; define a multimethod for area with :Shape keyword.
(defmulti area :Shape)
(defn rect [wd ht] {:Shape :Rect :wd wd :ht ht})
(defn circle [radius] {:Shape :Circle :radius radius})

(defmethod area :Rect [r]
  (* (:wd r) (:ht r)))
(defmethod area :Circle [c]
  (* (. Math PI) (* (:radius c) (:radius c))))
(defmethod area :default [x] :oops)

(def r (rect 4 13))
(def c (circle 12))

(area r)
(area c)
(area {})

; define a multimethod
;(defmulti compiler :os)
;(defmethod compiler ::unix [m] (get m :c-compiler))
;(defmethod compiler ::osx [m] (get m :llvm-compiler))
;(compiler {:os :unix :c-compiler "gcc" :home "/home"})
;;; => gcc
;(compiler {:os :osx :llvm-compiler "clang" :home "/Users"}) ;; => clang
;(defmulti home :os)
;(defmethod home ::unix [m] (get m :home))
;(home unix)
;;=> "/home"
;(home osx)
;; No method in multimethod 'home' for dispatch value: :user/osx
;(derive ::osx ::unix)
;(home osx)                                                  ;=> "/Users"
;(isa? ::osx ::unix)                                         ;=> true
;(isa? ::unix ::osx)                                         ;=> false

; record  具有type的map
(defrecord TCPServer [^String host
                      ^int port
                      ^int backlog])
(TCPServer. "localhost" 8080 5)
;=> .TCPServer{:host "localhost", :port 8080, :backlog 5}
(assoc (TCPServer. "localhost" 8080 5) :port 9090)
;=> .TCPServer{:host "localhost", :port 9090, :backlog 5}
(dissoc (TCPServer. "localhost" 8080 5) :port)

; protocol
(defprotocol Service
  (reload! [service core]
    "Informs the service of a change in core.")
  (start! [service]
    "Starts a service. Must be idempotent.")
  (stop! [service]
    "Stops a service. Must be idempotent.")
  (conflict? [service1 service2]
    "Do these two services conflict with one another? Adding a service to a core *replaces* any conflicting services."))

(defrecord TCPServer [^String host
                      ^int port
                      ^int backlog]
  Service
  (reload! [this new-core] (reset! core new-core))
  (start! [this]
    (->> (InetSocketAddress. host port)
         (.bind bootstrap) (.sync)
         (.channel)
         (.add channel-group))
    (info "TCP server" host port "online"))
  (stop! [this]
    (.close ServerManager host port)
    (info "TCP server" host port "closed"))
  (conflict? [this other]
    (and (instance? TCPServer other) (= host (:host other))
         (= port (:port other)))))

; extend-type
(defprotocol StringOps
  (rev [s]))
(extend-type String
  StringOps
  (rev [s] (clojure.string/reverse s)))
(rev "Works")
;=> "skroW"

; type (轻量级的record, 不需要像defrecord一样默认实现了很多接口)
(deftype InfiniteConstant [i] ISeq
  (seq [this]
    (lazy-seq (cons i (seq this)))))
(take 3 (InfiniteConstant. 5)) ;=> (5 5 5)


; java interop
(.toUpperCase "fred")
; -> "FRED"
(.getName String)
; -> "java.lang.String"
(.-x (java.awt.Point. 1 2))
; -> 1
(System/getProperty "java.vm.version")
; -> "1.6.0_07-b06-57"
Math/PI
; -> 3.141592653589793

(doto (new java.util.HashMap) (.put "a" 1) (.put "b" 2))
; -> {a=1, b=2}

(.. System (getProperties) (get "os.name"))
(. (. System (getProperties)) (get "os.name"))
(-> (System/getProperties) (.get "os.name"))

; reify 实现一个接
;;;; This example shows how to reify a multi-arity protocol function
;;;; (note the different style in defprotocol vs reify)
;; define a multi-arity protocol function blah
(defprotocol Foo
  (blah
    [this x]
    [this x y]))

;; define an anonymous extension via reify
(def r (reify Foo
         (blah [_ x] x)
         (blah [_ x y] y)))

;; invoke blah via the r instance
(blah r 1)   ;; => 1
(blah r 1 2)   ;; => 2








