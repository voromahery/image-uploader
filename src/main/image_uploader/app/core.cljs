(ns image-uploader.app.core
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]
            [image-uploader.app.views.card :refer [card]]
            [image-uploader.app.views.loader :refer [loader]]))

;; --- App State ---
(def file (r/atom nil))
(def loading (r/atom false))

;; --- Utility Functions ---
(defn upload-image [event]
  (reset! loading true)
  ;; (reset! file (-> event .-target .-files (aget 0)))
  (println (.stringify js/JSON (-> event .-target .-files)))
  (js/setTimeout (fn []
                   (reset! loading false)) 2000))

;; --- App Component ---

(defn app []
  [:div.main
   [:div.wrapper
    (if (= @loading true)    [loader]  [card upload-image])]
   [:footer
    [:p
     "created by " [:a {:href "https://github.com/voromahery" :class "developer"} "H.Fabrice Daniel"] " - "
     [:a {:href "https://devchallenges.io"} " devChallenges.io"]]]])

;; --- Render App ---

(defn render []
  (rdom/render [app] (.getElementById js/document "root")))

;; `^:export` metadata prevents function name from being munged during `:advanced` release compilation
(defn ^:export main []
  (println "Initial render")
  (render))

;; `^:dev/after-load` metadata hook tells Shadow-CLJS to run this function after each hot reload
(defn ^:dev/after-load reload! []
  (println "Reload!")
  (render))
