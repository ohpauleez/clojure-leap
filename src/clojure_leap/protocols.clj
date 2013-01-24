(ns clojure-leap.protocols)

(defprotocol EntityValidity
  (valid? [t]))

(defprotocol FingerContainer
  (fingers? [t])
  (fingers [t])
  (raw-finger [t finger-id])
  (finger [t finger-id]))

(defprotocol ToolContainer
  (tools? [t])
  (tools [t])
  (raw-tool [t tool-id])
  (tool [t tool-id]))

(defprotocol PointableContainer
  (pointables? [t])
  (pointables [t])
  (raw-pointable [t pointable-id])
  (pointable [t pointable-id]))

