(ns uma.middleware
  (:require [taoensso.timbre :as timbre]
            [ring.util.response :refer [content-type]]))

;; Exception handling

(defn wrap-exception [f]
  (fn [request]
    (try (f request)
      (catch Exception e
        (timbre/error e)
        {:status 500
         :body "{\"error\": \"Something went wrong\"}"}))))

;; Logging Middleware

(defn wrap-request-logger [handler]
  (fn [request]
    (timbre/info "REQUEST ::" (dissoc request :body))
    (handler request)))

(defn wrap-response-logger [handler]
  (fn [request]
    (let [response (handler request)]
      (timbre/info "RESPONSE ::" (dissoc response :body))
      response)))
