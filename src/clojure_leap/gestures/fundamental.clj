(ns clojure-leap.gestures.fundamental
  (:require [clojure-leap.core :as leap]
            [clojure-leap.frame :as l-frame :refer [hand-kw]]))

;; These are fundamental building blocks
;; for constructing and detecting gestures.

;; (def finger-flash? (fundamental-gesture frame-vec :finger-flash
;;                      max-one-hand? flashed-fingers? [currently-no-fingers? fist?])

;; the above uses vectors as and `or` statement, default is `and`

;; `max-` is for all previous frames
;; `currently-` is for the most-recent-frame // latest-frame
;; The varargs list are functions that consume frame-maps and return gesture-maps

;; Currently Gesture detection needs to traverse the frame-vec once per gesture you're looking for -
;; O(n*m) Where n is the number of frames in the window (usually 10) and m is the number of gestures.
;; TODO: This should be improved in the future.

;; A frame-map is {:framevec original-framevec, :latest-frame x, :past-frames [], :earliest-frame y}
(defn frame-vec->frame-map [frame-vec]
  {:framevec frame-vec
   :latest-frame (first frame-vec) ;; the most recent frame delivered by the LM
   :past-frames (nnext frame-vec)
   :earliest-frame (last frame-vec)})

(defn fundamental-map [fundamental-kw frame-map]
  {:fundamental fundamental-kw
   :hand (-> (:past-frames frame-map) first hand-kw)}) ;; this isn't really that accurate

;; TODO: real hand detection
;(defn split-frame-vecs-by-hand [frame-vec]
;  (let [max-hands (max (map #(leap/count (leap/hands %))))]
;    (case max-hands
;      1 {:rightmost frame-vec :leftmost frame-vec :any frame-vec :single? true}
;      )
;    {:rightmost
;   :leftmost
;   :any}))

;; The result of the gesture detection is a vector of maps, represeting the gestures detected in the frame sequence

(defn wrap-gesture [gesture-fn frame-map]
  (if (vector? gesture-fn)
    (some identity (map #(% frame-map) gesture-fn))
    (gesture-fn frame-map)))

(defn gesture [frame-coll gesture-kw gesture-fns]
  (let [frame-map (if (map? frame-coll) frame-coll (frame-vec->frame-map frame-coll))
        predicates-vec (vec (map #(wrap-gesture % frame-map) gesture-fns))]
    (and (every? identity predicates-vec)
         {:gesture gesture-kw
          :part predicates-vec})))

(defn gesture-fn [gesture-kw & gesture-fns]
  #(gesture % gesture-kw gesture-fns))

;; The Fundamental Gestures
;; =========================

(defn max-one-hand? [frame-map]
  (and (every? leap/single-hand? (:past-frames frame-map))
       (fundamental-map :max-one-hand frame-map)))

(defn flashed-fingers? [frame-map]
  (and (>= (apply max (map #(leap/count (leap/fingers %)) (:past-frames frame-map)))
           4)
       (fundamental-map :flashed-fingers frame-map)))

(defn rightmost-flashed-fingers? [frame-map]
  (and (>= (apply max (map #(-> (leap/rightmost-hand %) leap/fingers leap/count)
                           (:past-frames frame-map)))
           4)
       (fundamental-map :rightmost-flashed-fingers frame-map)))

(defn leftmost-flashed-fingers? [frame-map]
  (and (>= (apply max (map #(-> (leap/leftmost-hand %) leap/fingers leap/count)
                           (:past-frames frame-map)))
           4)
       (fundamental-map :leftmost-flashed-fingers frame-map)))

(defn currently-no-fingers? [frame-map]
  (and (not (leap/fingers? (:latest-frame frame-map)))
       (fundamental-map :currently-no-fingers frame-map)))

