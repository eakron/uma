(ns uma.server
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [uma.middleware :as middleware]
            [uma.logging]))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> (handler/api app-routes)
      (middleware/wrap-request-logger)
      (middleware/wrap-json-params)
      (middleware/wrap-response-logger)
      (middleware/wrap-json-response)
      (middleware/wrap-exception)))
