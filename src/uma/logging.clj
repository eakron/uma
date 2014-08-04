(ns uma.logging
  (:require [taoensso.timbre :as t]
            [taoensso.timbre.appenders.rotor :as r]
            [environ.core :refer [env]]))

(t/set-config!
  [:appenders :rotor]
  {:min-level :info
   :enabled? true
   :async? false ; should be always false for rotor
   :max-message-per-msecs nil
   :fn r/appender-fn})

(t/set-config!
  [:shared-appender-config :rotor]
  {:path "logs/app.log" :max-size 10000 :backlog 10})

(t/set-config!
  [:appenders :standard-out :enabled?]
  (or (env :logging) false))
