(ns udemy.events
  (:require
   [re-frame.core :refer [reg-event-db reg-event-fx after dispatch]]
   [clojure.spec :as s]
   [udemy.db :as db :refer [app-db]]))

;; -- Interceptors ------------------------------------------------------------
;;
;; See https://github.com/Day8/re-frame/blob/master/docs/Interceptors.md
;;
(defn check-and-throw
  "Throw an exception if db doesn't have a valid spec."
  [spec db [event]]
  (when-not (s/valid? spec db)
    (let [explain-data (s/explain-data spec db)]
      (throw (ex-info (str "Spec check after " event " failed: " explain-data) explain-data)))))

(def validate-spec
  (if goog.DEBUG
    (after (partial check-and-throw ::db/app-db))
    []))

;; -- Handlers --------------------------------------------------------------

(reg-event-db
  :set-album-list
  (fn [db [_ result]]
    (js/console.log result)
    (assoc db :album-list result)))

(reg-event-db
 :http-failure
 (fn [db [_ {:keys [status status-text] :as response}]]
   (js/console.log "+++++++++++++++++++" status status-text response)
   db))

(reg-event-db
  :good-http-result
  (fn [db [_ result]]
    (js/console.log result)
    (assoc db :album-list result)))

(reg-event-db
  :bad-http-result
  (fn [db [_ result]]
    (js/console.log result)
    (assoc db :bad-result result)))


(reg-event-db
 :set-header
 (fn [db [_ value]]
   (assoc db :header value)))

(def axios (js/require "axios"))
(reg-event-db
 :initialize-db
 (fn [_ _]
   (-> (.get axios "http://rallycoding.herokuapp.com/api/music_albums")
       (.then #(as-> (.-data %) $
                   (js->clj $ :keywordize-keys true)
                   (dispatch [:set-album-list $]))))
   app-db))

