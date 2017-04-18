(ns udemy.android.styles)

(def styles
  {:header {:text {:font-size 20}
            :view {:background-color "lightgray"
                   :justify-content "center"
                   :align-items "center"
                   :height 60
                   :padding-top 15
                   :shadow-color "black"
                   :shadow-offset {:width 0 :height 2}
                   :shadow-opacity 0.1
                   :elevation 8
                   :position "relative"
                   }}
   :card {:border-width 1
          :border-radius 2
          :border-color "#ddd"
          :border-bottom-width 0
          :shadow-color "#000"
          :shadow-offset {:width 0 :height 2}
          :shadow-opacity 0.1
          :shadow-radius 2
          :elevation 1
          :margin-left 5
          :margin-right 5
          :margin-top 5}
   :card-section { :border-bottom-width 1
                   :padding 5
                   :background-color "#fff"
                   :justify-content "flex-start"
                   :flex-direction "row"
                   :border-color "#ddd"
                   :position "relative" }
   :thumbnail {:height 50
               :width 50 } })

