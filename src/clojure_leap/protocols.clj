(ns clojure-leap.protocols
  (:refer-clojure :exclude [empty? count]))

(defprotocol LeapList
  (count [t])
  (empty? [t]))

(defprotocol EntityValidity
  (valid? [t]))

(defprotocol FingerContainer
  (fingers? [t])
  (fingers [t])
  (raw-finger [t finger-id])
  (finger [t finger-id])
  (leftmost-finger [t])
  (rightmost-finger [t])
  (highest-finger [t])
  (lowest-finger [t]))

(defprotocol ToolContainer
  (tools? [t])
  (tools [t])
  (raw-tool [t tool-id])
  (tool [t tool-id])
  (leftmost-tool [t])
  (rightmost-tool [t])
  (highest-tool [t])
  (lowest-tool [t]))

(defprotocol PointableContainer
  (pointables? [t])
  (pointables [t])
  (raw-pointable [t pointable-id])
  (pointable [t pointable-id])
  (leftmost-pointable [t])
  (rightmost-pointable [t])
  (highest-pointable [t])
  (lowest-pointable [t]))

