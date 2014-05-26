(ns uma.server
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [uma.middleware :as middleware]
            [ring.middleware.json :refer [wrap-json-response wrap-json-params]]
            [uma.logging]
            [uma.database :refer [ensure-database!]]
            [uma.utilities :as utilities]
            [uma.routes.user :as user]
            [uma.routes.horse :as horse]
            [uma.routes.occasion :as occasion]
            [uma.routes.course :as course]))

(ensure-database!)

(defroutes app-routes
  (context "/users" [] user/routes)
  (context "/horses" [] horse/routes)
  (context "/courses" [] course/routes)
  (context "/occasions" [] occasion/routes)
  (utilities/not-found))

(def app
  (-> (handler/api app-routes)
      (middleware/wrap-request-logger)
      (wrap-json-params)
      (wrap-json-response)
      (middleware/wrap-response-logger)
      (middleware/wrap-exception)))
