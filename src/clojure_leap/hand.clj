(ns clojure-leap.hand
  (:refer-clojure :exclude [empty? count])
  (:import (com.leapmotion.leap Hand HandList
                                Finger FingerList
                                Tool ToolList
                                Pointable PointableList
                                Frame
                                Vector)))

;; Hand List
;;;;;;;;;;;;;
;;    We won't support IFn lookup, but it should work with `nth`
(defn count [^HandList hand-list]
  (.count hand-list))

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
  (not (.empty (.fingers hand))))

(defn ^Finger raw-finger [^Hand hand finger-id]
  (.finger hand finger-id))

(defn finger [^Hand hand finger-id]
  {:pre [(integer? finger-id)]}
  (let [finger (.finger hand finger-id)]
    (when (.isValid finger)
      finger)))

(defn ^Finger leftmost-finger [^Hand hand]
  (when (fingers? hand)
    (apply min-key #(-> % l-pointable/tip-position l-vector/x) (fingers hand))))

(defn ^Finger rightmost-finger [^Hand hand]
  (when (fingers? hand)
    (apply max-key #(-> % l-pointable/tip-position l-vector/x) (fingers hand))))

(defn ^Finger highest-finger [^Hand hand]
  (when (fingers? hand)
    (apply max-key #(-> % l-pointable/tip-position l-vector/y) (fingers hand))))

(defn ^Finger lowest-finger [^Hand hand]
  (when (fingers? hand)
    (apply min-key #(-> % l-pointable/tip-position l-vector/y) (fingers hand))))

(defn ^ToolList tools [^Hand hand]
  (.tools hand))

(defn tools? [^Hand hand]
  (not (.empty (.tools hand))))

(defn ^Tool raw-tool [^Hand hand tool-id]
  (.tool hand tool-id))

(defn tool [^Hand hand tool-id]
  {:pre [(integer? tool-id)]}
  (let [tool (.tool hand tool-id)]
    (when (.isValid tool)
      tool)))

(defn ^Tool leftmost-tool [^Hand hand]
  (when (tools? hand)
    (apply min-key #(-> % l-pointable/tip-position l-vector/x) (tools hand))))

(defn ^Tool rightmost-tool [^Hand hand]
  (when (tools? hand)
    (apply max-key #(-> % l-pointable/tip-position l-vector/x) (tools hand))))

(defn ^Tool highest-tool [^Hand hand]
  (when (tools? hand)
    (apply max-key #(-> % l-pointable/tip-position l-vector/y) (tools hand))))

(defn ^Tool lowest-tool [^Hand hand]
  (when (tools? hand)
    (apply min-key #(-> % l-pointable/tip-position l-vector/y) (tools hand))))

(defn ^PointableList pointables [^Hand hand]
  (.pointables hand))

(defn pointables? [^Hand hand]
  (not (.empty (.pointables hand))))

(defn ^Pointable raw-pointable [^Hand hand pointable-id]
  (.pointable hand pointable-id))

(defn pointable [^Hand hand pointable-id]
  {:pre [(integer? pointable-id)]}
  (let [pointable (.pointable hand pointable-id)]
    (when (.isValid pointable)
      pointable)))

(defn ^Pointable leftmost-pointable [^Hand hand]
  (when (pointables? hand)
    (apply min-key #(-> % l-pointable/tip-position l-vector/x) (pointables hand))))

(defn ^Pointable rightmost-pointable [^Hand hand]
  (when (pointables? hand)
    (apply max-key #(-> % l-pointable/tip-position l-vector/x) (pointables hand))))

(defn ^Pointable highest-pointable [^Hand hand]
  (when (pointables? hand)
    (apply max-key #(-> % l-pointable/tip-position l-vector/y) (pointables hand))))

(defn ^Pointable lowest-pointable [^Hand hand]
  (when (pointables? hand)
    (apply min-key #(-> % l-pointable/tip-position l-vector/y) (pointables hand))))

(defn fist?
  "Are we detecting a possible fist? - a hand with no fingers/tools/pointables"
  [^Hand hand]
  (.empty (.pointables hand)))

(defn ^Vector direction [^Hand hand]
  (.direction hand))

(defn ^Vector palm-position [^Hand hand]
  (.palmPosition hand))

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

(defn equal? [^Hand hand ^Hand other]
  (.equals hand other))

