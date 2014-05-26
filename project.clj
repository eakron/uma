(defproject uma "0.0.0"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.6"]
                 [cheshire "5.3.1"]
                 [com.taoensso/timbre "3.1.6"]
                 [liberator "0.11.0"]
                 [postgresql "9.3-1101.jdbc4"]
                 [environ "0.5.0"]
                 [korma "0.3.1"]
                 [ring/ring-json "0.3.1"]
                 [log4j "1.2.15" :exclusions [javax.mail/mail
                                              javax.jms/jms
                                              com.sun.jdmk/jmxtools
                                              com.sun.jmx/jmxri]]]
  :plugins [[lein-ring "0.8.10"]]
  :ring {:handler uma.server/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
