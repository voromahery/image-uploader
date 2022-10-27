(ns image-uploader.app.core
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]
            [image-uploader.app.views.card :refer [card]]
            [image-uploader.app.views.loader :refer [loader]]))

;; --- App State ---
(def img-id "uploaded-image")
(def loading (r/atom false))
(def image-preview (r/atom nil))

;; --- Utility Functions ---
(defn upload-image [event]
  (let [file (-> event .-target .-files (aget 0))
        file-reader (js/FileReader.)]
    (set! (.-onload file-reader)
          (fn [event]
            (reset! image-preview (-> event .-target .-result))))
    (.readAsDataURL file-reader file)))

;; --- App Component ---

(defn app []
  [:div.main
   [:div.wrapper
    (if (= @loading true)
      [loader]
      [card upload-image image-preview])]
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
