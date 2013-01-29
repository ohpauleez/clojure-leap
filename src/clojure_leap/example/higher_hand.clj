(ns clojure-leap.example.higher-hand
  (:require [clojure-leap.core :as leap]
            [clojure-leap.vector :as v :refer [y]]))

(defn process-frame
  "Here is a more imperative, chaining-if that is more informative"
  [frame]
  (println
    (if (leap/hands? frame)
      (if (leap/single-hand? frame)
        "  -- There is only one hand --  "
        (if (leap/same-hand? (leap/highest-hand frame) (leap/rightmost-hand frame))
          "     The RIGHT HAND is higher -->>"
          "<<-- The LEFT HAND is higher "))
      "No hands are present")))

(defn -main [& args]
  (let [listener (leap/listener :frame #(process-frame (:frame %))
                                :default #(println "Toggling" (:state %) "for listener:" (:listener %)))
        [controller _] (leap/controller listener)]
    (println "Press Enter to quit")
    (read-line)
    (leap/remove-listener! controller listener)))

