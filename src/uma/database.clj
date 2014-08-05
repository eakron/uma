(ns uma.database
  (require [environ.core :refer [env]]
           [korma.core :refer [exec-raw]]
           [taoensso.timbre :as timbre]
           [korma.db :refer [defdb postgres]])
  (import [java.sql Time]))

(defdb db (postgres {:db (env :database-name)
                     :user (env :database-user)
                     :password (env :database-password)
                     :host (env :database-host)
                     :port "5432"}))

(defn create-tables! []
  (let [schema (slurp "src/uma/schema.sql")
        tables (clojure.string/split schema #";")]
    (doseq [table tables]
      (exec-raw [table]))
    (timbre/info "Database tables created.")))

(defn ensure-database! []
  (let [results (exec-raw [(str "select exists(
                                  select * from information_schema.tables
                                  where table_name='uma_user');")]
                         :results)]
    (if (:exists (first results))
      (timbre/info "Database tables found.")
      (create-tables!))))
