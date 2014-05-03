(ns uma.database
  (require [environ.core :refer [env]]
           [korma.db :refer [defdb postgres]]))

(defdb db (postgres {:db (env :database-name)
                     :user (env :database-user)
                     :password (env :database-password)
                     :host (env :database-host)
                     :port "5432"}))
