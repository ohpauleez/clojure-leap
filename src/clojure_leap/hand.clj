(ns clojure-leap.hand
  (:refer-clojure :exclude [empty?])
  (:import (com.leapmotion.leap Hand HandList
                                Finger FingerList
                                Tool ToolList
                                Pointable PointableList
                                Frame)))

;; Hand List
;;;;;;;;;;;;;
;;    We won't support IFn lookup, but it should work with `nth`
(defn empty? [^HandList hand-list]
  (.empty hand-list))

;; Hand
;;;;;;;;;;;;;
(defn ^Frame frame [^Hand hand]
  (.frame hand))

(defn valid? [^Hand hand]
  (.isValid hand))

(defn ^FingerList fingers
  "Get the FingerList for a given Hand"
  [^Hand hand]
  (.fingers hand))

(defn fingers?
  "Are there any fingers detected for a given Hand"
  [^Hand hand]
  (.empty (.fingers hand)))

(defn ^Finger raw-finger [^Hand hand finger-id]
  (.finger hand finger-id))

(defn finger [^Hand hand finger-id]
  {:pre [(integer? finger-id)]}
  (let [finger (.finger hand finger-id)]
    (when (.isValid finger)
      finger)))

(defn ^ToolList tools [^Hand hand]
  (.tools hand))

(defn tools? [^Hand hand]
  (.empty (.tools hand)))

(defn ^Tool raw-tool [^Hand hand tool-id]
  (.tool hand tool-id))

(defn tool [^Hand hand tool-id]
  {:pre [(integer? tool-id)]}
  (let [tool (.tool hand tool-id)]
    (when (.isValid tool)
      tool)))

(defn ^PointableList pointables [^Hand hand]
  (.pointables hand))

(defn pointables? [^Hand hand]
  (.empty (.pointables hand)))

(defn ^Pointable raw-pointable [^Hand hand pointable-id]
  (.pointable hand pointable-id))

(defn pointable [^Hand hand pointable-id]
  {:pre [(integer? pointable-id)]}
  (let [pointable (.pointable hand pointable-id)]
    (when (.isValid pointable)
      pointable)))

(defn direction [^Hand hand]
  (.direction hand))

(defn palm [^Hand hand]
  {:normal (.palmNormal hand) ; The vector outward/orthog
   :position (.palmPosition hand) ; The center of the palm
   :velocity (.palmVelocity hand)
   :direction (.direction hand)})

(defn sphere [^Hand hand]
  {:center (.sphereCenter hand)
   :radius (.sphereRadius hand)})

(defn rotation-since [^Hand hand ^Frame frame]
  {:angle (.rotationAngle hand frame)
   :axis (.rotationAxis hand frame)
   :matrix (.rotationMatrix hand frame)})

(defn scale-since [^Hand hand ^Frame frame]
  (.scaleFactor hand frame))

