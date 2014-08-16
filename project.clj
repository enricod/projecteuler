(defproject my-stuff "0.1.0-SNAPSHOT"
  :description "project euler"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :main ^:skip-aot eulerclj.p002
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
