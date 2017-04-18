(ns udemy.subs
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
  :get-header
  (fn [db _]
    (:header db)))

(reg-sub
 :get-album-list
 (fn [db _]
   (:album-list db))
 )
