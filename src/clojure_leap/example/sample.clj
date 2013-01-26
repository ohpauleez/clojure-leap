(ns clojure-leap.example.sample
  (:require [clojure-leap.core :as leap]
            [clojure-leap.hand :as l-hand]
            [clojure-leap.vector :as v]
            [clojure-leap.pointable :as l-pointable :refer [tip-position]]))

(defn process-frame [frame]
  (let [_ (println "Frame id:" (.id frame) "timestamp:" (.timestamp frame)
                   "hands:" (leap/count (leap/hands frame)) "fingers:" (leap/count (leap/fingers frame)) "tools:" (leap/count (leap/tools frame)))]
    (when-let [hand (and (leap/hands? frame) (first (leap/hands frame)))]
      (let [fingers (leap/fingers hand)
            avg-pos (v/divide (apply v/+ (map tip-position fingers)) 
                       (leap/count fingers))
            palm (l-hand/palm hand)
            sphere (l-hand/sphere hand)]
        (println "The hand has" (leap/count fingers) "fingers, with an average tip position of:" avg-pos)
        (println "Sphere as a radius of" (:radius sphere) "millimeters, with a position of:" (:position palm))
        (println "The hand is facing" (:direction palm) "and moving with a velocity of:" (:velocity palm) "\n")))))

(defn -main [& args]
  (let [;[controller listener] (leap/controller {:frame (fn [{:keys [frame]}] (process-frame frame))})
        ; The above if totally valid, but we can spell it out too
        listener (leap/listener :frame #(process-frame (:frame %))
                                :default #(println "Toggling" (:state %) "for listener:" (:listener %)))
        [controller _] (leap/controller listener)]
    (println "Press Enter to quit")
    (read-line)
    (leap/remove-listener! controller listener)))

