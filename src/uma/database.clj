(ns uma.database
  (require [environ.core :refer [env]]
           [clojure.data.json :as json]
           [korma.db :refer [defdb postgres]])
  (import [java.sql Time]))

(defdb db (postgres {:db (env :database-name)
                     :user (env :database-user)
                     :password (env :database-password)
                     :host (env :database-host)
                     :port "5432"}))

(extend-type java.sql.Time
  json/JSONWriter
  (-write [date out]
    (json/-write (str date) out)))

(extend-type java.sql.Timestamp
  json/JSONWriter
  (-write [date out]
    (json/-write (str date) out)))
