(ns clojure-leap.extended-protos
  (:require [clojure-leap.protocols :as l-protocols]
            [clojure-leap.frame :as l-frame]
            [clojure-leap.hand :as l-hand]
            [clojure-leap.pointable :as l-pointable]
            [clojure-leap.screen :as l-screen])
  (:import (com.leapmotion.leap Frame
                                Hand
                                Pointable
                                Screen)))

;; All clojure-leap/protocols are extended in this file.
;; The use of protocols allows the consumer to choose between
;;  - performance (using the raw, type-hinted functions within the Namespaces)
;;  - ease (using the protocols)

(extend-type Frame
  l-protocols/EntityValidity
  (valid? [t]
    (l-frame/valid? t))

  l-protocols/FingerContainer
  (fingers? [t]
    (l-frame/fingers? t))
  (fingers [t]
    (l-frame/fingers t))
  (raw-finger [t finger-id]
    (l-frame/raw-finger t finger-id))
  (finger [t finger-id]
    (l-frame/finger t finger-id))

  l-protocols/ToolContainer
  (tools? [t]
    (l-frame/tools? t))
  (tools [t]
    (l-frame/tools t))
  (raw-tool [t tool-id]
    (l-frame/raw-tool t tool-id))
  (tool [t tool-id]
    (l-frame/tool t tool-id))
  
  l-protocols/PointableContainer
  (pointables? [t]
    (l-frame/pointables? t))
  (pointables [t]
    (l-frame/pointables t))
  (raw-pointable [t pointable-id]
    (l-frame/raw-pointable t pointable-id))
  (pointable [t pointable-id]
    (l-frame/pointable t pointable-id)))

(extend-type Hand
  l-protocols/EntityValidity
  (valid? [t]
    (l-hand/valid? t))

  l-protocols/FingerContainer
  (fingers? [t]
    (l-hand/fingers? t))
  (fingers [t]
    (l-hand/fingers t))
  (raw-finger [t finger-id]
    (l-hand/raw-finger t finger-id))
  (finger [t finger-id]
    (l-hand/finger t finger-id))

  l-protocols/ToolContainer
  (tools? [t]
    (l-hand/tools? t))
  (tools [t]
    (l-hand/tools t))
  (raw-tool [t tool-id]
    (l-hand/raw-tool t tool-id))
  (tool [t tool-id]
    (l-hand/tool t tool-id))
  
  l-protocols/PointableContainer
  (pointables? [t]
    (l-hand/pointables? t))
  (pointables [t]
    (l-hand/pointables t))
  (raw-pointable [t pointable-id]
    (l-hand/raw-pointable t pointable-id))
  (pointable [t pointable-id]
    (l-hand/pointable t pointable-id)))

(extend-type Pointable
  l-protocols/EntityValidity
  (valid? [t]
    (l-pointable/valid? t)))

(extend-type Screen
  l-protocols/EntityValidity
  (valid? [t]
    (l-screen/valid? t)))

