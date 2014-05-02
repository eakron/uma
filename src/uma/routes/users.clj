(ns uma.routes.users
  (require [liberator.core :refer [defresource]]
           [uma.models.users :as users]))

(defresource resource
  :allowed-methods [:get]
  :handle-ok users/get-users
  :available-media-types ["application/json"])
