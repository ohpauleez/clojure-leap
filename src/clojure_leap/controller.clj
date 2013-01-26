(ns clojure-leap.controller
  (:import (com.leapmotion.leap Controller
                                Frame
                                Screen ScreenList)))

(def history-count-max 60)

(defn ^Frame frame [^Controller controller & [history-count]]
  (.frame controller (if history-count
                       (min history-count history-count-max)
                       0)))

;; Perhaps a future API will suport this natively
(defn frames [^Controller controller history-count]
  (vec (keep #(try (frame controller %) (catch Exception e nil)) (range history-count))))

(defn ^ScreenList screens [^Controller controller]
  (.calibratedScreens controller))

(defn ^Screen screen [^Controller controller]
  ;; There is a guarantee that there is at least one screen
  (first (.calibratedScreens controller)))

(defn connected? [^Controller controller]
  (.isConnected controller))

(defn controller-map
  ([^Controller controller]
   (controller-map controller nil))
  ([^Controller controller listener]
   {:controller controller
    :frame (.frame controller)
    :screens (screens controller)
    :state :frame
    :listener listener}))

