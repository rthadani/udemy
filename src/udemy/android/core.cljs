(ns udemy.android.core
  (:require [reagent.core :as r :refer [atom]]
            [re-frame.core :refer [subscribe dispatch dispatch-sync]]
            [udemy.events]
            [udemy.subs]
            [udemy.shared.reactcomponents :refer [text scrollview view image app-registry]]
            [udemy.android.styles :as s]))

(defn header-component
  []
  (let [header-text (subscribe [:get-header]) ]
    (fn []
      [view {:style (get-in s/styles [:header :view])}
       [text {:style (get-in s/styles [:header :text])} @header-text]])))

(defn card-section
  [& components]
  [view {:style (:card-section s/styles)}
    components])

(defn card
  [& components]
  [view {:style (:card s/styles)}
   components])

(defn album-detail
  [{title :title artist :artist thumbnail-image :thumbnail_image artwork :image}]
  [card
   [card-section
    [view {:style {:justify-content "center" :align-items "center" :margin-left 10 :margin-right 10}}
     [image {:source {:uri thumbnail-image} :style (:thumbnail s/styles)}]]
    [view {:style {:flex-direction "column"
                   :justify-content "space-around" }}
      [text {:key title :style {:font-size 18}} title]
      [text artist] ]]
   [card-section
    [image {:source {:uri artwork}
            :style {:height 300 :flex 1 :width nil} }] ] ])

(defn album-list
  []
  (let [album-list (subscribe [:get-album-list])]
    (fn []
      [scrollview
       (map album-detail @album-list)])))

(defn app-root []
  (fn []
    [view {:style {:flex 1}}
     [header-component]
     [album-list]]))

(defn init []
      (dispatch-sync [:initialize-db])
      (.registerComponent app-registry "udemy" #(r/reactify-component app-root)))
