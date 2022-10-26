(ns image-uploader.app.core
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]
            [image-uploader.app.views.card :refer [card]]))

;; --- App State ---


;; --- Utility Functions ---


;; --- App Component ---

(defn app []
  [:div.wrapper
   [card]])

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
