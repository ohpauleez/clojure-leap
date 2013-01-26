(ns clojure-leap.gestures
  (:require [clojure-leap.core :as leap]))

;; This is a preliminary library for basic gestures that
;; developers would commonly want to detect.
;;
;; THIS IS ALPHA AND COULD BE REMOVED IN THE FUTURE

(def ^:dynamic *window* 10)
(def ^:dynamic *threshold* (* 1.5 *window*))

(defn finger-flash?
  "Can we detect a user flashing all fingers on a single hand?
  We need to be at a frame with 0 fingers,
  where there 4 or more fingers in a previous frames.
  This is essentially the motion of flashing your fingers out,
  and then making a fist"
  [frame-vec]
  (let [latest-frame (first frame-vec)
        past-frames (nnext frame-vec) ;; We skip the second frame to filter our noise
        one-max-hand? (= 1 (apply max (map #(leap/count (leap/hands %)) past-frames)))
        flashed-fingers? (>= (apply max (map #(leap/count (leap/fingers %)) past-frames))
                           4)
        currently-no-fingers? (not (leap/fingers? latest-frame))]
    (and one-max-hand? flashed-fingers? currently-no-fingers?)))
 
