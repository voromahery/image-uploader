(ns image-uploader.app.views.card)

(defn card []
  [:div.wrapper
   [:header
    [:h3 "Upload your image"]
    [:p "File should be Jpeg, Png,..."]]
   [:div.image-container
    [:h3 "Image here"]]
   [:span "or"]
   [:button "Choose a file"]])