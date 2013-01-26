(ns clojure-leap.vector
  (:refer-clojure :exclude [+ - * / assoc])
  (:import (com.leapmotion.leap Vector)))

(defn truncate
  "Truncate some of the percision afforded by the Leap Motion"
  [^double d factor]
  (clojure.core// (Math/floor (clojure.core/* d factor 10)) (clojure.core/* factor 10)))

(defn angle-to [^Vector from ^Vector to]
  (.angleTo from to))
(def angle-between angle-to)

(defn distance-to [^Vector from ^Vector to]
  (.distanceTo from to))
(def distance-between distance-to)
(def distance distance-to)

(defn cross [^Vector v ^Vector x]
  (.cross v x))

(defn dot [^Vector v ^Vector x]
  (.dot v x))

(defn magnitude [^Vector v]
  (.magnitude v))

(defn magnitude-squared [^Vector v]
  (.magnitudeSquared v))

(defn normalized [^Vector v]
  (.normalized v))
(def normal normalized)

(defn valid? [^Vector v]
  (.isValid v))

(defn pitch [^Vector v]
  (.pitch v))

(defn roll [^Vector v]
  (.roll v))

(defn yaw [^Vector v]
  (.yaw v))

(defn opposite [^Vector v]
  (.opposite v))

(defn x [^Vector v]
  (.getX v))

(defn y [^Vector v]
  (.getY v))

(defn z [^Vector v]
  (.getZ v))

(defn ->float-array [^Vector v]
  (.toFloatArray v))

(defn assoc [^Vector v axis-kw new-value]
  (case axis-kw
    :x (.setX v new-value)
    :y (.setY v new-value)
    :z (.setZ v new-value)))

(defn +
  "Vector addition"
  ([] (Vector/zero))
  ([^Vector v] (cast Vector v))
  ([^Vector v ^Vector x] (.plus v x))
  ([^Vector v ^Vector x & more] (reduce + (.plus v x) more)))

;(defn -) .minus

;(defn *) .times

(defn divide
  ([^Vector v] v)
  ([^Vector v scalar] (.divide v scalar))
  ([^Vector v scalar & more] (reduce divide (.divide v scalar) more)))
(def / divide)

