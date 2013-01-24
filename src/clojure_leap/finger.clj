(ns clojure-leap.finger
  (:refer-clojure :exclude [empty?])
  (:import (com.leapmotion.leap FingerList)))

;; Finger List
;;;;;;;;;;;;;;;
;;    We won't support IFn lookup, but it should work with `nth`
(defn empty? [^FingerList finger-list]
  (.empty finger-list))

;; Finger
;;;;;;;;;;;;;;;
;; All `pointable` stuff works on Fingers and Tools too

