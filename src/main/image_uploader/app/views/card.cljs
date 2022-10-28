(ns image-uploader.app.views.card)

(defn card [upload-image image-preview copy-url url is-copied]
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
    (if @image-preview [:img {:src @image-preview :class "image-preview"}] [:p "Drag & Drop your image here"])]
   [:input {:type "file" :accept "image/*" :id "uploader" :class "uploader-input" :on-change upload-image}]

   (if @image-preview
     [:div.success-form
      [:input {:type "text" :value @url :on-change nil}]
      [:button {:on-click copy-url}  (if @is-copied "Copied!" "Copy link")]]

     [:<>
      [:span "or"]
      [:label {:class "upload-button" :for "uploader"} "Choose a file"]])])