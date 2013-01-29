(ns clojure-leap.pointable
  (:import (com.leapmotion.leap Pointable
                                Vector)))

(defn frame [^Pointable pointable]
  (.frame pointable))

(defn hand [^Pointable pointable]
  (.hand pointable))

(defn valid? [^Pointable pointable]
  (.isValid pointable))

(defn equal? [^Pointable pointable ^Pointable other]
  (.equals pointable other))

(defn finger? [^Pointable pointable]
  (.isFinger pointable))

(defn tool? [^Pointable pointable]
  (.isTool pointable))

(defn length [^Pointable pointable] ; in millimeters
  (.length pointable))

(def millimeters-long length)
(def milli-long length)

(defn width [^Pointable pointable]
  (.width pointable)) ; in millimeters

(def millimeters-wide width)
(def milli-wide width)

(defn size [^Pointable pointable]
  {:length-mm (.length pointable)
   :width-mm (.width pointable)})

(defn direction [^Pointable pointable]
  (.direction pointable))

(defn tip-position [^Pointable pointable]
  (.tipPosition pointable))

(defn tip-velocity [^Pointable pointable]
  (.tipVelocity pointable))

(defn tip-map [^Pointable pointable]
  {:direction (.direction pointable)
   :position (.tipPosition pointable)
   :velocity (.tipVelocity pointable)})

