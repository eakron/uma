(ns uma.database
  (require [environ.core :refer [env]]))

(def spec {:classname "org.postgresql.Driver"
           :subprotocol "postgresql"
           :subname (env :database-address)
           :user (env :database-user)
           :password (env :database-password)})
