(ns image-uploader.app.core
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]
            [goog.string :as str]
            [komponentit.clipboard :as clipboard]
            [image-uploader.app.views.card :refer [card]]
            [image-uploader.app.views.loader :refer [loader]]))

;; --- App State ---
(def loading (r/atom false))
(def image-preview (r/atom nil))
(def url (r/atom ""))
(def is-copied (r/atom false))

;; --- Utility Functions ---
(defn upload-image [event]
  (let [file (-> event .-target .-files (aget 0))
        file-reader (js/FileReader.)]
    (set! (.-onload file-reader)
          (fn [event]
            (reset! image-preview (-> event .-target .-result))
            (reset! url (str (-> js/window .-location .-href) (-> event .-target .-result)))))
    (.readAsDataURL file-reader file)))

(defn copy-url []
  (clipboard/copy-text @url)
  (reset! is-copied true))

;; --- App Component ---
(defn app []
  [:div.main
   [:div.wrapper
    (if (= @loading true)
      [loader]
      [card upload-image image-preview copy-url url is-copied])]
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