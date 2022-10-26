(ns image-uploader.app.core
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]
            [image-uploader.app.views.card :refer [card]]
            [image-uploader.app.views.loader :refer [loader]]))

;; --- App State ---
(def file (r/atom nil))

;; --- Utility Functions ---
(defn upload-image [event]
  (reset! file (-> event .-target .-files (aget 0)))
  (println (get (-> event .-target .-files (aget 0)) :name)))


;; --- App Component ---

(println @file)

(defn app []
  [:div.wrapper
   [loader]
   [card upload-image]])

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
