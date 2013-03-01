(defproject clojure_leap "0.1.0-SNAPSHOT"
  :description "A Clojure API for the Leap Motion"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.0"]]
  :resource-paths ["leap_lib/LeapJava.jar" "resources"]
  
  :warn-on-reflection true
  
  :jvm-opts  [~(str "-Djava.library.path=leap_lib/:" (System/getenv "LD_LIBRARY_PATH"))]
  :main clojure-leap.example.mouse)

