(defproject uma "0.0.0"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.6"]
                 [cheshire "5.3.1"]
                 [com.taoensso/timbre "3.1.6"]]
  :plugins [[lein-ring "0.8.10"]]
  :ring {:handler uma.server/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
