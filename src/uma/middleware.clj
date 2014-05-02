(ns uma.middleware
  (:require [taoensso.timbre :as timbre]
            [cheshire.core :as json]
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

;; JSON Middleware

(defn- json-request? [request]
  (when-let [type (:content-type request)]
    (not (empty? (re-find #"^application/json" type)))))

(defn- read-json [request]
  (if (json-request? request)
    (when-let [body (:body request)]
      (json/parse-string (slurp body) true))))

(defn wrap-json-params
  "Middleware that converts request bodies in JSON format to a map of
   parameters, which is added to the request map on the :json-params and
   :params keys."
  [handler]
  (fn [request]
    (if-let [json (read-json request)]
      (handler (-> request
                   (assoc :json-params json)
                   (update-in [:params] merge json)))
      (handler request))))
