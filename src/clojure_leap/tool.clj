(ns clojure-leap.tool
  (:refer-clojure :exclude [empty? count])
  (:import (com.leapmotion.leap ToolList)))

;; Tool List
;;;;;;;;;;;;;;;
;;    We won't support IFn lookup, but it should work with `nth`
(defn count [^ToolList tool-list]
  (.count tool-list))

(defn empty? [^ToolList tool-list]
  (.empty tool-list))

;; Tool
;;;;;;;;;;;;;;;
;; All `pointable` stuff works on Fingers and Tools too

