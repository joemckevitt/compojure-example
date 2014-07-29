(ns compojure.example.views
  (:use [hiccup core page]))

(defn local-addresses []
   (->> (java.net.NetworkInterface/getNetworkInterfaces)
        enumeration-seq
        (map bean)
        (filter (complement :loopback))
        (mapcat :interfaceAddresses)
        ;; hopefully this eliminates ipv6 addresses
        (filter #(some (fn [x] (= x (.getNetworkPrefixLength %))) [8,16,24]))
        (map #(.. % (getAddress) (getHostAddress)))))

(defn index-page []
  (html5
   [:head
    [:title "Hello World"]
    (include-css "/css/style.css")]
   [:body
    [:h1 (str "Hello World from: "
              (apply str (interpose ", " (local-addresses))))]]))
