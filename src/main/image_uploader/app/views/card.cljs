(ns image-uploader.app.views.card)

(defn card []
  [:div.card-wrapper
   [:header
    [:h3 "Upload your image"]
    [:p "File should be Jpeg, Png,..."]]
   [:label {:class "image-container" :for "uploader"}
    [:input {:type "file" :accept "image/*" :id "uploader" :class "uploader-input"}]
    [:p "Drag & Drop your image here"]]
   [:span "or"]
   [:button.upload-button "Choose a file"]])