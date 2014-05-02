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
  (fn [req]
    (timbre/info "REQUEST ::" (dissoc response :body))
    (handler req)))

(defn wrap-response-logger [handler]
  (fn [req]
    (let [response (handler req)]
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

(defn wrap-json-response
  "Middleware that converts responses with a map or a vector for a body into a
   JSON response. Accepts the following options:
   :pretty - when true, pretty-print the JSON
   :escape-non-ascii - when true, non-ASCII characters are escaped with \\u"
  [handler]
  (fn [request]
    (let [response (handler request)]
      (if (coll? (:body response))
        (let [json-response (update-in response [:body] json/generate-string)]
          (if (contains? (:headers response) "Content-Type")
            json-response
            (content-type json-response "application/json; charset=utf-8")))
        response))))
