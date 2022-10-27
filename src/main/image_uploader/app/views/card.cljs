(ns image-uploader.app.views.card
  (:require [goog.string :as str]))

(defn card [upload-image image-preview]
  [:div.card-wrapper
   [:header
    (if @image-preview
      [:<>
       [:img {:src "/img/check-filled.svg" :alt "check" :class "check-icon"}]
       [:h3.success-heading "Uploaded Successfully!"]]

      [:<>
       [:h3 "Upload your image"]
       [:p "File should be Jpeg, Png,..."]])]

   [:label {:class "image-container" :for "uploader"}
    (if @image-preview [:img {:src @image-preview :class "image-preview"}] [:p "Drag & Drop your image here"])
    [:input {:type "file" :accept "image/*" :id "uploader" :class "uploader-input" :on-change upload-image}]]

   (if @image-preview
     [:div.success-form
      [:input {:type "text" :value (str (-> js/window .-location .-href) @image-preview) :on-change nil}]
      [:button "Copy link"]]

     [:<>
      [:span "or"]
      [:button.upload-button "Choose a file"]])])