(ns clojure-leap.example.mouse
  (:require [clojure-leap.core :as leap]
            [clojure-leap.screen :as l-screen]
            [clojure-leap.gestures :as gestures])
  (:import (java.awt Robot)))

(def active? (atom true)) ;; Should we be reacting to the leap?
(def toggle-threshold (atom 0)) ;; Remove thrashing from sliding-window gesture detection
(def robot (Robot.))

(defn toggle! [hit-it? frame]
  (when (and hit-it?
             (> (.id frame) @toggle-threshold)) ;; we're beyond the window of a previous gesture
    (reset! active? (not @active?))
    (reset! toggle-threshold (+ gestures/*threshold* (.id frame))) ;; thresholds are (* 1.5 gesture-window)
    (println "Leap Mouse active:" @active?)))

(defn process-frame [controller frame screens]
  (let [fingers (leap/fingers frame)
        toggle-switch? (gestures/finger-flash? (leap/frames controller gestures/*window*))
        _ (toggle! toggle-switch? frame)]
    (when-let [pointable (and @active? (leap/pointables? frame) (first (leap/pointables frame)))]
      (let [position-map (l-screen/intersect-position screens pointable)]
        (.mouseMove robot (:x position-map)(:y position-map))))))

(defn -main [& args]
  (let [listener (leap/listener :frame #(process-frame (:controller %) (:frame %) (:screens %))
                                :default #(println "Toggling" (:state %) "for listener:" (:listener %)))
        [controller _] (leap/controller listener)]
    (println "Press Enter to quit")
    (read-line)
    (leap/remove-listener! controller listener)))

