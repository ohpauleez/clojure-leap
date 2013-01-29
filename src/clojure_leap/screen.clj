(ns clojure-leap.screen
  (:refer-clojure :exclude [empty?])
  (:import (com.leapmotion.leap Screen
                                ScreenList
                                Pointable
                                Vector)))

;; Screen List
;;;;;;;;;;;;;;;
;;    We won't support IFn lookup, but it should work with `nth`
(defn closest-screen [^ScreenList screen-list ^Pointable pointable]
  (.closestScreenHit screen-list pointable))

(defn empty? [^ScreenList screen-list]
  (.empty screen-list))

;; Screen
;;;;;;;;;;;;;;;
(defn bottom-left [^Screen screen]
  (.bottomLeftCorner screen))

(defn distance-to-point [^Screen screen point-vec]
  (.distanceToPoint screen point-vec))

(defn dimensions [^Screen screen]
  {:height-px (.heightPixels screen)
   :width-px (.widthPixels screen)})

(defn axes [^Screen screen]
  {:vertical (.verticalAxis screen)
   :horizontal (.horizontalAxis screen)})

(defn normal [^Screen screen]
  (.normal screen))

(defn ^Vector intersect
  ([^Screen screen ^Pointable pointable]
   (intersect screen pointable true))
  ([^Screen screen ^Pointable pointable normalize]
   ;;normalized coordinates representing the intersection point as:
   ;; [true], a percentage of the screen's width and height.
   ;; [false], return Leap coordinates, millimeters from the Leap origin
   (.intersect screen pointable normalize))
  ([^Screen screen ^Pointable pointable normalize clamp-ratio]
   (.intersect screen pointable normalize clamp-ratio)))

(defn intersect-position
  "This gives the X,Y coordinates on the closest screen which
  pointable intersects, as a map {:x :y}
  These X, Y coordinates can be used to map to mouse movement, for example."
  [^ScreenList screen-list ^Pointable pointable]
  (let [screen (.closestScreenHit screen-list pointable)
        screen-dim (dimensions screen)
        position (.intersect screen pointable true)]
    {:x (* (.getX position) (:width-px screen-dim))
     :y (* (- 1 (.getY position)) (:height-px screen-dim))}))

(defn valid? [^Screen screen]
  (.isValid screen))

(defn equal? [^Screen screen ^Screen other]
  (.equals screen other))

