(ns clojure-leap.frame
  (:require [clojure-leap.hand :as l-hand]
            [clojure-leap.pointable :as l-pointable]
            [clojure-leap.vector :as l-vector])
  (:import (com.leapmotion.leap Frame
                                Pointable PointableList
                                Hand HandList
                                Finger FingerList
                                Tool ToolList)))


(defn ^HandList hands [^Frame frame]
  (.hands frame))
 
(defn hands? [^Frame frame]
  (not (.empty (.hands frame))))

(defn single-hand? [^Frame frame]
  (= 1 (.count (.hands frame))))

(defn ^Hand leftmost-hand [^Frame frame]
  (when (hands? frame)
    (apply min-key #(-> % l-hand/palm-position l-vector/x) (hands frame))))

(defn ^Hand rightmost-hand [^Frame frame]
  (when (hands? frame)
    (apply max-key #(-> % l-hand/palm-position l-vector/x) (hands frame))))

(defn ^Hand highest-hand [^Frame frame]
  (when (hands? frame)
    (apply max-key #(-> % l-hand/palm-position l-vector/y) (hands frame))))

(defn ^Hand lowest-hand [^Frame frame]
  (when (hands? frame)
    (apply min-key #(-> % l-hand/palm-position l-vector/y) (hands frame))))

(defn ^Hand raw-hand [^Frame frame hand-id]
  (.hand frame hand-id))

(defn hand [^Frame frame hand-id]
  {:pre [(integer? hand-id)]}
  (let [hand (.hand frame hand-id)]
    (when (.isValid hand)
      hand)))

(defn ^FingerList fingers
  "Get the FingerList for a given Frame"
  [^Frame frame]
  (.fingers frame))

(defn fingers?
  "Are there any fingers detected for a given Frame"
  [^Frame frame]
  (not (.empty (.fingers frame))))

(defn ^Finger raw-finger [^Frame frame finger-id]
  (.finger frame finger-id))

(defn finger [^Frame frame finger-id]
  {:pre [(integer? finger-id)]}
  (let [finger (.finger frame finger-id)]
    (when (.isValid finger)
      finger)))

(defn ^Finger leftmost-finger [^Frame frame]
  (when (fingers? frame)
    (apply min-key #(-> % l-pointable/tip-position l-vector/x) (fingers frame))))

(defn ^Finger rightmost-finger [^Frame frame]
  (when (fingers? frame)
    (apply max-key #(-> % l-pointable/tip-position l-vector/x) (fingers frame))))

(defn ^Finger highest-finger [^Frame frame]
  (when (fingers? frame)
    (apply max-key #(-> % l-pointable/tip-position l-vector/y) (fingers frame))))

(defn ^Finger lowest-finger [^Frame frame]
  (when (fingers? frame)
    (apply min-key #(-> % l-pointable/tip-position l-vector/y) (fingers frame))))

(defn ^ToolList tools [^Frame frame]
  (.tools frame))

(defn tools? [^Frame frame]
  (not (.empty (.tools frame))))

(defn ^Tool raw-tool [^Frame frame tool-id]
  (.tool frame tool-id))

(defn tool [^Frame frame tool-id]
  {:pre [(integer? tool-id)]}
  (let [tool (.tool frame tool-id)]
    (when (.isValid tool)
      tool)))

(defn ^Tool leftmost-tool [^Frame frame]
  (when (tools? frame)
    (apply min-key #(-> % l-pointable/tip-position l-vector/x) (tools frame))))

(defn ^Tool rightmost-tool [^Frame frame]
  (when (tools? frame)
    (apply max-key #(-> % l-pointable/tip-position l-vector/x) (tools frame))))

(defn ^Tool highest-tool [^Frame frame]
  (when (tools? frame)
    (apply max-key #(-> % l-pointable/tip-position l-vector/y) (tools frame))))

(defn ^Tool lowest-tool [^Frame frame]
  (when (tools? frame)
    (apply min-key #(-> % l-pointable/tip-position l-vector/y) (tools frame))))

(defn ^PointableList pointables [^Frame frame]
  (.pointables frame))

(defn pointables? [^Frame frame]
  (not (.empty (.pointables frame))))

(defn ^Pointable raw-pointable [^Frame frame pointable-id]
  (.pointable frame pointable-id))

(defn pointable [^Frame frame pointable-id]
  {:pre [(integer? pointable-id)]}
  (let [pointable (.pointable frame pointable-id)]
    (when (.isValid pointable)
      pointable)))

(defn ^Pointable leftmost-pointable [^Frame frame]
  (when (pointables? frame)
    (apply min-key #(-> % l-pointable/tip-position l-vector/x) (pointables frame))))

(defn ^Pointable rightmost-pointable [^Frame frame]
  (when (pointables? frame)
    (apply max-key #(-> % l-pointable/tip-position l-vector/x) (pointables frame))))

(defn ^Pointable highest-pointable [^Frame frame]
  (when (pointables? frame)
    (apply max-key #(-> % l-pointable/tip-position l-vector/y) (pointables frame))))

(defn ^Pointable lowest-pointable [^Frame frame]
  (when (pointables? frame)
    (apply min-key #(-> % l-pointable/tip-position l-vector/y) (pointables frame))))

(defn valid? [^Frame frame]
  (.isValid frame))

(defn timestamp [^Frame frame]
  (.timestamp frame))

(defn ^Frame invalid-frame []
  (Frame.))

