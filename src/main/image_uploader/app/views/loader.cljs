(ns image-uploader.app.views.loader)

(defn loader []
  [:div.progress-container
   [:h3 "uploading..."]
   [:div.progress-wrapper
    [:div.progress]]])