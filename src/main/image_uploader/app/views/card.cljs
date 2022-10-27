(ns image-uploader.app.views.card)

(defn card [upload-image image-preview]
  [:div.card-wrapper
   [:header
    [:h3 "Upload your image"]
    [:p "File should be Jpeg, Png,..."]]
   [:label {:class "image-container" :for "uploader"}
    (if @image-preview [:img {:src @image-preview :class "image-preview"}] [:p "Drag & Drop your image here"])
    [:input {:type "file" :accept "image/*" :id "uploader" :class "uploader-input" :on-change upload-image}]]
   [:span "or"]
   [:button.upload-button "Choose a file"]])